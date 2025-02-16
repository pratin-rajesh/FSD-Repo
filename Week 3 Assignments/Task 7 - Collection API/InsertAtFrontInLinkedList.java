import java.util.LinkedList;
import java.util.Arrays;

public class InsertAtFrontInLinkedList {
    public static void main(String[] args) {
        // inserting elements at first in linked list
        LinkedList<String> list = new LinkedList<>(Arrays.asList("World"));
        System.out.println("Before: " + list);
        list.offerFirst("Hello");
        System.out.println("After: " + list);
    }
}