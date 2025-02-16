import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;

public class IterateLinkedListInReverse {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("1", "2", "3"));
        System.out.println("Original list: " + list);
        System.out.println("Printing in reverse:");
        Iterator<String> reverseIterator = list.descendingIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }
    }
}