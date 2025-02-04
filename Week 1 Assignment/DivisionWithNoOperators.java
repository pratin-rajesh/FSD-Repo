public class DivisionWithNoOperators {
    public static void main(String[] args) {
        int dividend = 25;
        int divisor = 4;
        int quotient = 0;
        int remainder = dividend;

        while (remainder >= divisor) {
            remainder -= divisor;
            quotient++;
        }

        System.out.println("Quotient: " + quotient);
        System.out.println("Remainder: " + remainder);
    }
}
