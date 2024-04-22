package Map;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapComparatorExample {
public static void main(String[] args) {
// Creating a TreeMap to store student grades (String key, Integer value)
TreeMap<String, Integer> studentGrades = new TreeMap<>(new MyComp());

        // Adding entries to the TreeMap
        studentGrades.put("Alice", 85);
        studentGrades.put("Bob", 90);
        studentGrades.put("Charlie", 78);
        studentGrades.put("David", 95);

        // Displaying the TreeMap (automatically sorted by keys)
        System.out.println("Student Grades (Sorted by Name):");
        for (Map.Entry<String, Integer> entry : studentGrades.entrySet()) {
            System.out.println("Name: " + entry.getKey() + ", Grade: " + entry.getValue());
        }

        // Retrieving and updating values based on keys
        String studentName = "Charlie";
        if (studentGrades.containsKey(studentName)) {
            int charlieGrade = studentGrades.get(studentName);
            System.out.println(studentName + "'s Grade: " + charlieGrade);

            // Update Charlie's grade
            studentGrades.put(studentName, 80);
            System.out.println("Updated " + studentName + "'s Grade: " + studentGrades.get(studentName));
        } else {
            System.out.println(studentName + " not found in the TreeMap.");
        }
    }
}

class MyComp implements Comparator<String>{


    @Override
    public int compare(String s1, String s2) {
         return s2.compareTo(s1);
    }
}

/* Output : ->
Student Grades (Sorted by Name):
Name: David, Grade: 95
Name: Charlie, Grade: 78
Name: Bob, Grade: 90
Name: Alice, Grade: 85
Charlie's Grade: 78
Updated Charlie's Grade: 80


*/