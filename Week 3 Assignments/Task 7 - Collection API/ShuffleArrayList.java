import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ShuffleArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple"));
        System.out.println("Before shuffling: " + list);
        Collections.shuffle(list);
        System.out.println("After shuffling: " + list);
    }
}