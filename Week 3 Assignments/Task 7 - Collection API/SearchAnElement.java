import java.util.ArrayList;
import java.util.Arrays;

public class SearchAnElement {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple"));
        String searchElement = "Blue";
        int index = list.indexOf(searchElement);
        System.out.println("List: " + list);
        System.out.println("Element '" + searchElement + "' found at index: " + index);
    }
}