import java.util.LinkedList;
import java.util.Arrays;

public class InsertAtSpecifiedPositionInLinkedList {
    public static void main(String[] args) {
        // inserting elements at a specific position in linkedlist
        LinkedList<String> list = new LinkedList<>(Arrays.asList("1", "2", "4"));
        System.out.println("Before: " + list);
        list.add(2, "3");
        System.out.println("After: " + list);
    }
}