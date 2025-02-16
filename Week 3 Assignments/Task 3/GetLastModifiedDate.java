import java.io.File;
import java.util.Date;

public class GetLastModifiedDate {
    public static void main(String[] args) {
        String pathname = "E:/Java Programs/techmahindra/Week 3 Assignments/Task 3/Task3.txt";
        File file = new File(pathname);
        Date lastModified = new Date(file.lastModified());
        System.out.println("Last modified: " + lastModified);
    }
}