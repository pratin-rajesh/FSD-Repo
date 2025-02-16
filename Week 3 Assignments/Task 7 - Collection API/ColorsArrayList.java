import java.util.ArrayList;

public class ColorsArrayList {
    public static void main(String[] args) {
        System.out.println("=== Create and print colors ArrayList ===");
        ArrayList<String> colors = new ArrayList<>();
        String[] colorNames = { "Red", "Green", "Blue", "Yellow", "Purple" };
        // using enhanced for to add color names to arraylist
        for (String name : colorNames) {
            colors.add(name);
        }
        System.out.println("Colors list: " + colors);
    }
}
