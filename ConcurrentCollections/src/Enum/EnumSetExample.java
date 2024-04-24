package Enum;

import java.util.EnumSet;

public class EnumSetExample {

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        // Create an EnumSet of Day enum
        EnumSet<Day> weekdays = EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY);

        // Iterate over the EnumSet (in ordinal order)
        for (Day day : weekdays) {
            System.out.println(day);
        }
    }
}

/*  Output : ->
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
 */