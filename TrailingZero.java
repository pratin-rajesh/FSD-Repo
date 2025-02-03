package javasolving;

public class TrailingZero {
    public static void main(String[] args) {
        int fact = 925;
        int temp = 0;
        while (fact != 1) {
            temp += fact / 5;
            fact /= 5;
        }
        System.out.println("Zeros: " + temp);
    }
}
