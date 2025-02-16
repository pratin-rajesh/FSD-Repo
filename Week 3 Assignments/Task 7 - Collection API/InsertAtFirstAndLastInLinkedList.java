import java.util.LinkedList;
import java.util.Arrays;

public class InsertAtFirstAndLastInLinkedList {
    public static void main(String[] args) {
        // inserting elements at first and last in LinkedList
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Middle"));
        System.out.println("Before: " + list);
        list.addFirst("First");
        list.addLast("Last");
        System.out.println("After: " + list);
    }
}