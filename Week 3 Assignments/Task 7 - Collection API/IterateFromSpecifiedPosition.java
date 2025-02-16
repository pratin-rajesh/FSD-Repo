import java.util.LinkedList;
import java.util.Arrays;
import java.util.ListIterator;

public class IterateFromSpecifiedPosition {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple"));
        System.out.println("Full list: " + list);
        System.out.println("Iterating from position 3:");
        ListIterator<String> positionIterator = list.listIterator(3);
        while (positionIterator.hasNext()) {
            System.out.println(positionIterator.next());
        }
    }
}