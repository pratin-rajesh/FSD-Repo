import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadPlainTextFile {
    public static void main(String[] args) {
        String pathname = "E:/Java Programs/techmahindra/Week 3 Assignments/Task 3/Task3.txt";
        try {
            String content = Files.readString(Paths.get(pathname));
            System.out.println("File content: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}