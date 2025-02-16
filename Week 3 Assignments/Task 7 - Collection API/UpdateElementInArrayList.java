import java.util.ArrayList;
import java.util.Arrays;

public class UpdateElementInArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("orange", "orange", "banana"));
        System.out.println("Before update: " + list);
        list.set(1, "apple");
        System.out.println("After update: " + list);
    }
}