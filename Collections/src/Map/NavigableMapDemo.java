package Map;

import java.util.NavigableMap;
import java.util.TreeMap;

public class NavigableMapDemo {

    public static void main(String[] args){

        // Create a NavigableMap (TreeMap in this case)
        NavigableMap<Integer, String> navigableMap = new TreeMap<>();

        // Add elements to the NavigableMap
        navigableMap.put(1, "One");
        navigableMap.put(3, "Three");
        navigableMap.put(5, "Five");
        navigableMap.put(7, "Seven");
        navigableMap.put(9, "Nine");

        // Print the NavigableMap
        System.out.println("NavigableMap: " + navigableMap);

        // Demonstrate ceilingEntry() method
        System.out.println("Ceiling entry for key 4: " + navigableMap.ceilingEntry(4));
        System.out.println("Ceiling entry for key 6: " + navigableMap.ceilingEntry(6));

        // Demonstrate floorEntry() method
        System.out.println("Floor entry for key 4: " + navigableMap.floorEntry(4));
        System.out.println("Floor entry for key 6: " + navigableMap.floorEntry(6));

        // Demonstrate higherEntry() method
        System.out.println("Higher entry for key 4: " + navigableMap.higherEntry(4));
        System.out.println("Higher entry for key 6: " + navigableMap.higherEntry(6));

        // Demonstrate lowerEntry() method
        System.out.println("Lower entry for key 4: " + navigableMap.lowerEntry(4));
        System.out.println("Lower entry for key 6: " + navigableMap.lowerEntry(6));

    }
}
