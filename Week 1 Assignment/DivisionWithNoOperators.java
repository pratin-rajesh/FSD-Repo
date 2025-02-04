public class DivisionWithNoOperators {
    public static void main(String[] args) {
        int dividend = 25;
        int divisor = 4;
        int quotient = 0;
        int remainder = dividend;
        /* while loop is used since we dont know when the number is divided till the end */
        while (remainder >= divisor) {
            remainder -= divisor;
            quotient++;
        }

        System.out.println("Quotient: " + quotient);
        System.out.println("Remainder: " + remainder);
    }
}
