import java.util.LinkedList;
import java.util.Arrays;

public class InsertAtEndInLinkedList {
    public static void main(String[] args) {
        // inserting elements in linkedlist at the end
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Hello"));
        System.out.println("Before: " + list);
        list.offerLast("World");
        System.out.println("After: " + list);
    }
}