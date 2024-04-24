package Enum;


import java.util.EnumMap;

public class EnumMapExample {

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        // Create an EnumMap with Day enum as keys and String as values
        EnumMap<Day, String> dayActivities = new EnumMap<>(Day.class);

        // Add entries to the EnumMap
        dayActivities.put(Day.MONDAY, "Work");
        dayActivities.put(Day.TUESDAY, "Gym");
        dayActivities.put(Day.WEDNESDAY, "Study");
        dayActivities.put(Day.THURSDAY, "Meetings");
        dayActivities.put(Day.FRIDAY, "Relax");

        // Iterate over the EnumMap (in ordinal order)
        for (Day day : dayActivities.keySet()) {
            System.out.println(day + ": " + dayActivities.get(day));
        }
    }
}

/* Output : ->
MONDAY: Work
TUESDAY: Gym
WEDNESDAY: Study
THURSDAY: Meetings
FRIDAY: Relax

 */

