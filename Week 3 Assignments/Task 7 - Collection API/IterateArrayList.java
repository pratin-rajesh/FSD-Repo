import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class IterateArrayList {
    public static void main(String[] args) {
        ArrayList<String> items = new ArrayList<>(Arrays.asList("apple", "banana", "pomegranate", "lemon"));

        System.out.println("Using for:\n");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        System.out.println("\nUsing enhanced for:\n");
        for (String item : items) {
            System.out.println(item);
        }

        System.out.println("\nUsing iterator:\n");
        Iterator<String> iterator = items.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}