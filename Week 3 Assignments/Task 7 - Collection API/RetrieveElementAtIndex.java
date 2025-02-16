import java.util.ArrayList;
import java.util.Arrays;

public class RetrieveElementAtIndex {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("First", "Second", "Third", "Fourth"));
        System.out.println("Full list: " + list);
        System.out.println("Element at index 3: " + list.get(3));
    }
}