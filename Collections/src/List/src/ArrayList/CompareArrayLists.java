package ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class CompareArrayLists {

    public static void main(String[] args) {
        // Create two ArrayLists with sample data
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("Apple", "Banana", "Orange"));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("Apple", "Mango", "Orange"));

        // Comparison methods:

        // 1. Using equals() method (checks for exact equality, including order)
        boolean areEqual = list1.equals(list2);
        System.out.println("Lists equal using equals(): " + areEqual);

        // 2. Using containsAll() method (checks if one list contains all elements of the other)
        boolean containsAll = list1.containsAll(list2);
        System.out.println("List1 contains all elements of list2: " + containsAll);

        // 3. Using retainAll() method (finds common elements)
        ArrayList<String> commonElements = new ArrayList<>(list1);
        commonElements.retainAll(list2);
        System.out.println("Common elements: " + commonElements);

        // 4. Using streams (Java 8+)
        boolean haveSameElements = list1.stream().allMatch(list2::contains);
        System.out.println("Lists have the same elements (order might differ): " + haveSameElements);
    }
}
