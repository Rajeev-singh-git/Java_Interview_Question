package Map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMethodDemo {

    public static void main(String [] args){

        // Create a HashMap
        Map<String, String> fruits = new HashMap<>();

        // 1. put(key, value) - Add entries
        fruits.put("Apple", "Red");
        fruits.put("Banana", "Yellow");
        fruits.put("Orange", "Orange"); // Overwrites existing "Orange" with no value

        // Print the Map
        System.out.println("Fruits Map: " + fruits);

        // 14. putAll(Map m) - Add entries from another Map
        Map<String, String> moreFruits = new HashMap<>();
        moreFruits.put("Grape", "Purple");
        moreFruits.put("Mango", "Yellow");
        fruits.putAll(moreFruits);
        System.out.println("Fruits Map after putAll: " + fruits);

        // 15. get(key) - Retrieve value by key
        String appleColor = fruits.get("Apple");
        System.out.println("Color of Apple: " + appleColor);

        // 16. remove(key) - Remove entry and return value
        String removedFruit = fruits.remove("Banana");
        System.out.println("Removed fruit: " + removedFruit);
        System.out.println("Fruits Map after remove: " + fruits);

        // 17. containsKey(key) - Check if key exists
        boolean hasMango = fruits.containsKey("Mango");
        System.out.println("Does map contain 'Mango' key? " + hasMango);

        // 18. containsValue(value) - Check if value exists
        boolean hasYellowFruit = fruits.containsValue("Yellow");
        System.out.println("Does map contain a fruit with value 'Yellow'? " + hasYellowFruit);

        // 19. isEmpty() - Check if map is empty
        boolean isEmpty = fruits.isEmpty();
        System.out.println("Is the map empty? " + isEmpty);


        // 20. size() - Get the number of entries
        int mapSize = fruits.size();
        System.out.println("Number of entries in the map: " + mapSize);

        // 21. keySet() - Get a set of all keys
        // (Cannot modify the original map through the keySet)
        if (!fruits.isEmpty()) { // Avoid potential exception on empty map
            Set<String> fruitKeys = fruits.keySet();
            System.out.println("Set of keys: " + fruitKeys);
        }

        // 22. values() - Get a collection of all values
        // (Cannot modify the original map through the values collection)
        if (!fruits.isEmpty()) { // Avoid potential exception on empty map
            Collection<String> fruitValues = fruits.values();
            System.out.println("Collection of values: " + fruitValues);
        }

        // 23. entrySet() - Get a set of all key-value pairs (Entry objects)
        // (Can modify the original map through the entrySet)
        if (!fruits.isEmpty()) { // Avoid potential exception on empty map
            Set<Map.Entry<String, String>> entries = fruits.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        }

        // 24. clear() - Remove all entries
        fruits.clear();
        System.out.println("Fruits Map after clear: " + fruits);

    }
}

/*
Fruits Map: {Apple=Red, Orange=Orange, Banana=Yellow}
Fruits Map after putAll: {Apple=Red, Grape=Purple, Mango=Yellow, Orange=Orange, Banana=Yellow}
Color of Apple: Red
Removed fruit: Yellow
Fruits Map after remove: {Apple=Red, Grape=Purple, Mango=Yellow, Orange=Orange}
Does map contain 'Mango' key? true
Does map contain a fruit with value 'Yellow'? true
Is the map empty? false
Number of entries in the map: 4
Set of keys: [Apple, Grape, Mango, Orange]
Collection of values: [Red, Purple, Yellow, Orange]
Key: Apple, Value: Red
Key: Grape, Value: Purple
Key: Mango, Value: Yellow
Key: Orange, Value: Orange
Fruits Map after clear: {}
 */