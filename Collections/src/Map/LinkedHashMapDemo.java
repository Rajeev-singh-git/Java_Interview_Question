package Map;

import java.util.*;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        // Creating a LinkedHashMap to store student names and their corresponding scores
        LinkedHashMap<String, Integer> studentScores = new LinkedHashMap<>();

        // Adding some entries to the LinkedHashMap
        studentScores.put("Alice", 85);
        studentScores.put("Bob", 90);
        studentScores.put("Charlie", 78);
        studentScores.put("David", 95);

        // Iterating over the LinkedHashMap using a for-each loop
        System.out.println("Student Scores:");
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            String studentName = entry.getKey();
            int score = entry.getValue();
            System.out.println(studentName + ": " + score);
        }

        // Accessing a specific value by key
        String nameToFind = "Charlie";
        if (studentScores.containsKey(nameToFind)) {
            int charlieScore = studentScores.get(nameToFind);
            System.out.println(nameToFind + "'s score is: " + charlieScore);
        } else {
            System.out.println(nameToFind + " not found in the map.");
        }
    }
}


/*
Student Scores:
Alice: 85
Bob: 90
Charlie: 78
David: 95
Charlie's score is: 78
 */