import java.util.Arrays;

public class CopyAltElements {
    public static void main(String[] args) {
        int[] originalArray = { 10, 20, 30, 40, 50, 60, 70, 80 };

        int newSize = (originalArray.length + 1) / 2;
        int[] altArray = new int[newSize];

        // Copy alt elements
        int index = 0;
        for (int i = 0; i < originalArray.length; i += 2) {
            altArray[index++] = originalArray[i];
        }

        // Print the original and copied arrays
        System.out.println("Original Array: " + Arrays.toString(originalArray));
        System.out.println("Alternate Elements Array: " + Arrays.toString(altArray));
    }
}
