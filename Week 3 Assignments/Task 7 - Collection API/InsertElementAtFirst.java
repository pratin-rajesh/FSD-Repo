import java.util.ArrayList;
import java.util.Arrays;

public class InsertElementAtFirst {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("One", "Two", "Three"));
        System.out.println("Before: " + list);
        list.add(0, "Zero");
        System.out.println("After: " + list);
    }
}