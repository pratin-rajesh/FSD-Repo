public class PrimitiveReferenceUse {
    public static void modifyValues(int number, int[] numbersArray) {
        number += 10;
        numbersArray[0] += 10;
    }

    public static void main(String[] args) {
        int number = 5;
        int[] numbersArray = {5};

        modifyValues(number, numbersArray);

        System.out.println("Number after method call: " + number);
        System.out.println("Array after method call: " + numbersArray[0]);
    }
}
