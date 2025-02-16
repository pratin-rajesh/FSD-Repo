import java.io.File;
import java.util.Arrays;

public class ListFilesAndDirectories {
    public static void main(String[] args) {
        String directoryPath = "E:/Java Programs/techmahindra/Week 3 Assignments/Task 3";
        File directory = new File(directoryPath);
        String[] contents = directory.list();
        if (contents != null) {
            System.out.println("Contents of directory: " + directoryPath);
            Arrays.stream(contents).forEach(System.out::println);
        } else {
            System.out.println("Directory does not exist or is empty");
        }
    }
}