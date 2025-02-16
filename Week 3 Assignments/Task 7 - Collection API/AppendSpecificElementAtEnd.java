import java.util.LinkedList;
import java.util.Arrays;

public class AppendSpecificElementAtEnd {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple"));
        System.out.println("Before: " + list);
        list.add("Black");
        System.out.println("After: " + list);
    }
}