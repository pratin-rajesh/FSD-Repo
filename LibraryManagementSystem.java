package techmahindra;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryManager libManager = new LibraryManager();

        // Initialize data
        libManager.addUser(new User("Alice", "U001"));
        libManager.addUser(new User("Bob", "U002"));
        libManager.addBook(new Book("Java Basics", "John Doe", "ISBN001"));
        libManager.addBook(new Book("OOP Principles", "Jane Smith", "ISBN002"));
        libManager.addBook(new Book("Concurrency in Java", "Mike Johnson", "ISBN003"));

        // Demonstrate operations
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Simulate concurrent operations
        executor.execute(() -> {
            try {
                libManager.borrowBook("ISBN001", "U001");
                System.out.println("User U001 borrowed ISBN001");
            } catch (Exception e) {
                System.out.println("Error in borrowing: " + e.getMessage());
            }
        });

        executor.execute(() -> {
            try {
                libManager.returnBook("ISBN001", "U001");
                System.out.println("User U001 returned ISBN001");
            } catch (Exception e) {
                System.out.println("Error in returning: " + e.getMessage());
            }
        });

        executor.execute(() -> {
            try {
                libManager.borrowBook("ISBN002", "U002");
                System.out.println("User U002 borrowed ISBN002");
            } catch (Exception e) {
                System.out.println("Error in borrowing: " + e.getMessage());
            }
        });

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Save and load data
        try {
            libManager.saveData("library.dat");
            libManager.loadData("library.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File error: " + e.getMessage());
        }

        // Display final state
        System.out.println("\nFinal Library State:");
        libManager.books.forEach(b -> System.out.println("Book: " + b.getTitle()));
        libManager.users.forEach(u -> System.out.println("User: " + u.getName()));
    }

    static class Book implements Serializable {
        private String title;
        private String author;
        private String ISBN;

        public Book(String title, String author, String ISBN) {
            this.title = title;
            this.author = author;
            this.ISBN = ISBN;
        }

        // Getters
        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getISBN() {
            return ISBN;
        }
    }

    static class User implements Serializable {
        private String name;
        private String userID;
        private List<Book> borrowedBooks = new ArrayList<>();

        public User(String name, String userID) {
            this.name = name;
            this.userID = userID;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getUserID() {
            return userID;
        }

        public List<Book> getBorrowedBooks() {
            return borrowedBooks;
        }
    }

    interface ILibrary {
        void borrowBook(String ISBN, String userID)
                throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException;

        void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException;

        void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException;

        Book searchBook(String title);
    }

    static abstract class LibrarySystem implements ILibrary {
        protected List<Book> books = new ArrayList<>();
        protected List<User> users = new ArrayList<>();

        public abstract void addBook(Book book);

        public abstract void addUser(User user);
    }

    static class LibraryManager extends LibrarySystem {
        private static final int MAX_BOOKS = 3;

        @Override
        public synchronized void addBook(Book book) {
            books.add(book);
        }

        @Override
        public synchronized void addUser(User user) {
            users.add(user);
        }

        @Override
        public synchronized void borrowBook(String ISBN, String userID)
                throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException {
            User user = users.stream()
                    .filter(u -> u.getUserID().equals(userID))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            Book book = books.stream()
                    .filter(b -> b.getISBN().equals(ISBN))
                    .findFirst()
                    .orElseThrow(() -> new BookNotFoundException("Book not found"));

            synchronized (user) {
                if (user.getBorrowedBooks().size() >= MAX_BOOKS) {
                    throw new MaxBooksAllowedException("Max books reached");
                }
                user.getBorrowedBooks().add(book);
                books.remove(book);
            }
        }

        @Override
        public synchronized void returnBook(String ISBN, String userID)
                throws BookNotFoundException, UserNotFoundException {
            User user = users.stream()
                    .filter(u -> u.getUserID().equals(userID))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            Book book = user.getBorrowedBooks().stream()
                    .filter(b -> b.getISBN().equals(ISBN))
                    .findFirst()
                    .orElseThrow(() -> new BookNotFoundException("Book not borrowed"));

            synchronized (user) {
                user.getBorrowedBooks().remove(book);
                books.add(book);
            }
        }

        @Override
        public Book searchBook(String title) {
            return books.stream()
                    .filter(b -> b.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
            // Implementation similar to borrow with reservation logic
        }

        public void saveData(String filename) throws IOException {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(books);
                oos.writeObject(users);
            }
        }

        @SuppressWarnings("unchecked")
        public void loadData(String filename) throws IOException, ClassNotFoundException {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                books = (List<Book>) ois.readObject();
                users = (List<User>) ois.readObject();
            }
        }
    }

    // Custom Exceptions
    static class BookNotFoundException extends Exception {
        public BookNotFoundException(String message) {
            super(message);
        }
    }

    static class UserNotFoundException extends Exception {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    static class MaxBooksAllowedException extends Exception {
        public MaxBooksAllowedException(String message) {
            super(message);
        }
    }
}
