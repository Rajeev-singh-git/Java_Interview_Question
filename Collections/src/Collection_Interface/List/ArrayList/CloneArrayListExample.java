package ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CloneArrayListExample {

    public static void main(String[] args) {
        List<String> originalList = new ArrayList<>(Arrays.asList("Apple", "Banana", "Orange"));

        System.out.println("Original List: " + originalList);


        // Method 1: Using addAll()
        List<String> clonedList1 = new ArrayList<>();
        clonedList1.addAll(originalList);

        System.out.println("clonedList1 (addAll()): " + clonedList1);


        // Method 2: Using List.copyOf() (Java 10+)
        List<String> clonedList3 = List.copyOf(originalList);



        System.out.println("clonedList3 (List.copyOf()): " + clonedList3);
      //  System.out.println("clonedList4 (loop): " + clonedList4);
    }
}
