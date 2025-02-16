import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileLineByLine {
    public static void main(String[] args) {
        String pathname = "E:/Java Programs/techmahindra/Week 3 Assignments/Task 3/Task3.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(pathname))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}