package techmahindra;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Product Class
class Product implements Serializable {
    private String productID, name;
    private int quantity;
    private Location location;

    public Product(String productID, String name, int quantity, Location location) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Location getLocation() {
        return location;
    }

    public void reduceQuantity(int amount) throws OutOfStockException {
        if (amount > quantity)
            throw new OutOfStockException("Insufficient stock for " + name);
        quantity -= amount;
    }

    @Override
    public String toString() {
        return name + " (ID: " + productID + ", Quantity: " + quantity + ", Location: " + location + ")";
    }
}

// Location Class
class Location implements Serializable {
    private int aisle, shelf, bin;

    public Location(int aisle, int shelf, int bin) {
        this.aisle = aisle;
        this.shelf = shelf;
        this.bin = bin;
    }

    @Override
    public String toString() {
        return "Aisle " + aisle + ", Shelf " + shelf + ", Bin " + bin;
    }
}

// Order Class
class Order implements Comparable<Order>, Serializable {
    private String orderID;
    private List<String> productIDs;
    private Priority priority;

    public enum Priority {
        STANDARD, EXPEDITED
    }

    public Order(String orderID, List<String> productIDs, Priority priority) {
        this.orderID = orderID;
        this.productIDs = productIDs;
        this.priority = priority;
    }

    public List<String> getProductIDs() {
        return productIDs;
    }

    @Override
    public int compareTo(Order other) {
        return other.priority.compareTo(this.priority); // Expedited orders come first
    }

    @Override
    public String toString() {
        return "Order " + orderID + " [" + priority + "]";
    }
}

// Custom Exceptions
class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}

class InvalidLocationException extends Exception {
    public InvalidLocationException(String message) {
        super(message);
    }
}

// Inventory Manager
class InventoryManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();
    private PriorityQueue<Order> orderQueue = new PriorityQueue<>();
    private transient Lock lock = new ReentrantLock(); // Transient to avoid serialization issues

    public InventoryManager() {
        this.lock = new ReentrantLock();
    }

    public void addProduct(Product product) {
        products.put(product.getProductID(), product);
    }

    public void placeOrder(Order order) {
        synchronized (orderQueue) {
            orderQueue.offer(order);
        }
    }

    public void processOrders() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        while (true) {
            Order order;
            synchronized (orderQueue) {
                order = orderQueue.poll();
            }

            if (order == null)
                break;

            executor.execute(() -> {
                try {
                    fulfillOrder(order);
                } catch (Exception e) {
                    System.out.println("Order processing error: " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fulfillOrder(Order order) throws OutOfStockException {
        lock.lock();
        try {
            System.out.println("Processing " + order);
            for (String productID : order.getProductIDs()) {
                Product product = products.get(productID);
                if (product == null) {
                    throw new OutOfStockException("Product " + productID + " not found!");
                }
                product.reduceQuantity(1);
            }
            System.out.println(order + " fulfilled!");
        } finally {
            lock.unlock();
        }
    }

    public void saveInventory(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(products);
            System.out.println("Inventory saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked") // used this notation in order to suppress the unchecked warning in line 189
    public void loadInventory(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            products = (ConcurrentHashMap<String, Product>) in.readObject();
            System.out.println("Inventory loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing inventory file found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// Main Class
public class WarehouseInventorySystem {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();

        // Adding sample products
        inventoryManager.addProduct(new Product("P001", "Laptop", 10, new Location(1, 2, 3)));
        inventoryManager.addProduct(new Product("P002", "Phone", 5, new Location(2, 1, 4)));

        // Creating sample orders
        inventoryManager.placeOrder(new Order("O001", Arrays.asList("P001", "P002"), Order.Priority.EXPEDITED));
        inventoryManager.placeOrder(new Order("O002", Arrays.asList("P002"), Order.Priority.STANDARD));

        // Process orders with multithreading
        inventoryManager.processOrders();

        // Save and load inventory
        inventoryManager.saveInventory("inventoryData.ser");
        inventoryManager.loadInventory("inventoryData.ser");
    }
}
