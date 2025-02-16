import java.util.ArrayList;
import java.util.Arrays;

public class RemoveElementInArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple"));
        System.out.println("Before: " + list);
        list.remove(2);
        System.out.println("After: " + list);
    }
}