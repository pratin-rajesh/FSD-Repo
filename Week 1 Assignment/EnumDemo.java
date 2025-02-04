// enum is used for values that are constant in nature.
enum DaysOfWeek {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

public class EnumDemo {
    public static void main(String[] args) {
        DaysOfWeek day = DaysOfWeek.SUNDAY;

        if (day == DaysOfWeek.SUNDAY || day == DaysOfWeek.SATURDAY) {
            System.out.println("Holiday");
        } else {
            System.out.println("Not Holiday");
        }
    }
}
