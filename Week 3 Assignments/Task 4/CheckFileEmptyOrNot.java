import java.io.File;
import java.io.IOException;

public class CheckFileEmptyOrNot {
    public static void checkIfFileIsEmpty(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.length() == 0) {
            throw new IOException("The file is empty: " + filePath);
        }
        System.out.println("The file is not empty: " + filePath);
    }

    public static void main(String[] args) {
        try {
            checkIfFileIsEmpty("E:/Java Programs/techmahindra/Week 3 Assignments/Task 4/Task4.txt");
        } catch (IOException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}