import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

public class InsertSomeElementsInLinkedList {
    public static void main(String[] args) {
        // inserting elements at a specific positions in linkedlist
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Start", "End"));
        List<String> elementsToAdd = Arrays.asList("Hello", "Everyone", "!!!");
        System.out.println("Before: " + list);
        list.addAll(1, elementsToAdd);
        System.out.println("After: " + list);
    }
}