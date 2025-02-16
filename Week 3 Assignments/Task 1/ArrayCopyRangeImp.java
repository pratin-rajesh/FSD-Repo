import java.util.Arrays;

public class ArrayCopyRangeImp {
    public static void main(String[] args) {
        int[] original = { 10, 20, 30, 40, 50, 60 };
        int[] copiedRange = Arrays.copyOfRange(original, 1, 4);

        System.out.println("Original Array: " + Arrays.toString(original));
        System.out.println("Copied Range: " + Arrays.toString(copiedRange));
    }
}