import java.util.Scanner;

public class ExceptionHandling {
    private static final String ENTER_NUMBER_MESSAGE = "Enter a number: ";
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Please enter a valid number.";
    private static final String ZERO_DIVISION_MESSAGE = "Reciprocal of zero is undefined.";

    public static void processInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ENTER_NUMBER_MESSAGE);
        
        try {
            double number = Double.parseDouble(scanner.nextLine());
            if (number == 0) {
                System.out.println(ZERO_DIVISION_MESSAGE);
            } else {
                System.out.println("Reciprocal: " + (1 / number));
            }
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        } finally {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        processInput();
    }
}