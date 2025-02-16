import java.util.LinkedList;
import java.util.Arrays;
import java.util.ListIterator;

public class IterateLinkedList {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple"));

        System.out.println("Using for-each:\n");
        for (String item : list) {
            System.out.println(item);
        }

        System.out.println("\nUsing ListIterator:\n");
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }
}