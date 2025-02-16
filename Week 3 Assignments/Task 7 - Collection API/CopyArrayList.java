import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CopyArrayList {
    public static void main(String[] args) {
        ArrayList<String> array1 = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple"));
        ArrayList<String> array2 = new ArrayList<>(
                Arrays.asList("apple", "banana", "pomegranate", "orange", "mango"));
        System.out.println("Before:");
        System.out.println("Array 1: " + array1);
        System.out.println("Array 2: " + array2);
        Collections.copy(array2, array1);
        System.out.println("After:");
        System.out.println("Array 1: " + array1);
        System.out.println("Array 2: " + array2);
    }
}