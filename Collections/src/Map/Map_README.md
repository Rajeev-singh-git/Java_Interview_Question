# Map

The **`Map`** interface in Java is used to represent a collection of objects as key-value pairs.

- **Purpose**: Represents a group of objects as key-value pairs.
- **Key and Value**: Both key and value are objects.
- **Duplicate Keys**: Not allowed, but values can be duplicated.
- **Entry**: Each key-value pair in a **`Map`** is referred to as one entry.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/bbd9311f-7b54-4346-959e-a62694b81933/Untitled.png)

Map is considered as collection of Entry Object.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/e15cb0d0-6490-4249-8f7a-46161cb3ed32/Untitled.png)

**`Map`** is not a child interface of **`Collection`**, so Collection methods cannot be applied directly to **`Map`**.

## Methods

- **`put(Object key, Object value)`**: Adds an entry to the map. If the key is already present, the old value is replaced with the new value, and the old value is returned.

```java
HashMap<String, String> map = new HashMap<>();
map.put("100", "vijay");
System.out.println(map); // {100=vijay}
map.put("100", "ashok");
System.out.println(map); // {100=ashok}

```

- **`putAll(Map m)`**: Copies all entries from the specified map (**`m`**) into this map.
- **`get(Object key)`**: Returns the value associated with the specified key, or **`null`** if the key is not present.
- **`remove(Object key)`**: Removes the entry associated with the specified key and returns the corresponding value.
- **`containsKey(Object key)`**: Checks if the map contains the specified key.
- **`containsValue(Object value)`**: Checks if the map contains the specified value.
- **`isEmpty()`**: Returns **`true`** if the map contains no entries.
- **`size()`**: Returns the number of entries (key-value pairs) in the map.
- **`clear()`**: Removes all entries from the map.
- **`keySet()`**: Returns a **`Set`** containing all keys in the map.
- **`values()`**: Returns a **`Collection`** containing all values in the map.
- **`entrySet()`**: Returns a **`Set`** containing all key-value pairs (entries) in the map.

**Note**: The **`Map`** interface provides powerful methods for manipulating key-value data, enabling efficient storage, retrieval, and modification of mappings. Implementations of **`Map`** include **`HashMap`**, **`TreeMap`**, **`LinkedHashMap`**, and others, each with specific characteristics suitable for different use cases.

Example Code : →

```java
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

```

### Entry Interface

The **`Entry`** interface is defined within the **`Map`** interface and represents a single key-value entry.

### **Methods in `Entry` Interface:**

- **`Object getKey()`**: Returns the key of this entry.
- **`Object getValue()`**: Returns the value associated with this entry.
- **`Object setValue(Object newValue)`**: Sets the value of this entry to the specified new value and returns the previous value.

```java

public class MapEntryDemo {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

}

/*  Output :->
Key: one, Value: 1
Key: two, Value: 2
 */

```

## HashMap

1. The underlying data structure is Hashtable.
2. Duplicate keys are not allowed but values can be duplicated.
3. Insertion order is not preserved and it is based on hash code of the keys.
4. Heterogeneous objects are allowed for both key and value.
5. Null is allowed for keys(only once) and for values(any number of times).
6. It is best suitable for Search operations.

### **HashMap vs Hashtable**

| Feature | HashMap | Hashtable |
| --- | --- | --- |
| Underlying Data Structure | Utilizes a hash table (array of buckets) | Also uses a hash table (array of buckets) |
| Duplicate Keys | Not allowed; keys must be unique | Not allowed; keys must be unique |
| Insertion Order | Does not preserve insertion order; keys are stored based on hash codes | Does not preserve insertion order; keys are stored based on hash codes |
| Heterogeneous Objects | Supports heterogeneous objects for keys and values | Supports heterogeneous objects for keys and values |
| Null Handling | Permits null keys (once) and null values (any number of times) | Does not allow null keys or values; using null leads to NullPointerException |
| Thread Safety | Methods are not synchronized; suitable for non-thread-safe environments | All methods are synchronized, ensuring thread safety |
| Performance | Relatively higher performance due to lack of synchronization | Relatively slower performance due to synchronization overhead |
| Legacy Status | Non-legacy class; introduced in Java 1.2 | Legacy class; introduced in Java 1.0 |

### How to get synchronized version of HashMap ?

To obtain a synchronized version of a **`HashMap`** object, you can use the **`synchronizedMap`** method provided by the **`Collections`** class. This method wraps your existing **`HashMap`** instance with a synchronized wrapper, ensuring thread safety when multiple threads access the map concurrently.

Code : → Synchronized version of HashMap

```java
package Map;

import java.util.*;

public class SynchronizedHashMapExample {

    public static void main(String[] args) {
        // Create a HashMap
        Map<String, String> hashMap = new HashMap<>();

        // Add elements to the HashMap
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        // Obtain synchronized version of the HashMap
        Map<String, String> synchronizedHashMap = Collections.synchronizedMap(hashMap);

        // Now synchronizedHashMap is thread-safe for concurrent access
        // You can perform operations on synchronizedHashMap in a multi-threaded environment
        synchronizedHashMap.put("key3", "value3");

        // Iterate over synchronizedHashMap (safely in a multi-threaded context)
        synchronized (synchronizedHashMap) {
            for (Map.Entry<String, String> entry : synchronizedHashMap.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }
}

```

• **ConcurrentHashMap:** If you need a more efficient concurrent map implementation with better performance than a synchronized `HashMap`, consider using `ConcurrentHashMap` from the `java.util.concurrent` package. It's designed for concurrent access without explicit synchronization.

### HashMap Constructor

```java
1. HashMap m = new HashMap(); 
   Creates an empty HashMap object with a default initial capacity of
   16 and a default load factor of 0.75.
2. HashMap m = new HashMap(int initialCapacity);
   Creates an empty HashMap object with the specified initial capacity.
3. HashMap m = new HashMap(int initialCapacity, float loadFactor);
   Creates an empty HashMap object with the specified initial capacity
   and load factor java.
4. HashMap m = new HashMap(Map m);
   Creates a new HashMap object containing the same key-value mappings 
   as the specified Map.
```

## LinkedHashMap

It is exactly same as HashMap except the following differences :

| Property | HashMap | LinkedHashMap |
| --- | --- | --- |
| Underlying Data Structure | Hashtable | Hashtable + LinkedList |
| Insertion Order | Not preserved | Preserved |
| Introduced | 1.2v | 1.4v |

Note: In general we can use LinkedHashSet and LinkedHashMap for implementing cache applications.

Code Example

```java
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

```

## IdentityHashMap

It is exactly same as HashMap except the following differences:

- In **`HashMap`**, duplicate keys are identified using the **`.equals()`** method, which compares keys based on content.
- In **`IdentityHashMap`**, duplicate keys are identified using **`==`** (double equal operator), which compares keys based on reference.

```java
package Map;

import java.util.*;

public class IdentityHashMapDemo {

    public static void main(String[] args) {
        // Using HashMap
        HashMap<String, String> hashMap = new HashMap<>();
        String key1 = new String("key");
        String key2 = new String("key");

        hashMap.put(key1, "value1");
        hashMap.put(key2, "value2");

        System.out.println(hashMap);

        System.out.println("HashMap size: " + hashMap.size()); // Output: 2

        // Using IdentityHashMap
        Map<String, String> identityHashMap = new IdentityHashMap<>();
        String key3 = new String("key");
        String key4 = new String("key");

        identityHashMap.put(key3, "value1");
        identityHashMap.put(key4, "value2");

        System.out.println("IdentityHashMap size: " + identityHashMap.size()); // Output: 2

        System.out.println(identityHashMap);

        // Checking entries in HashMap
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println("HashMap Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // Checking entries in IdentityHashMap
        for (Map.Entry<String, String> entry : identityHashMap.entrySet()) {
            System.out.println("IdentityHashMap Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}

```

## WeakHashMap

It is exactly same as HashMap except the following differences:

- In the case of normal HashMap, an object is not eligible for GC even though itdoesn't have any references if it is associated with HashMap. That is HashMap dominates garbage collector.
- But in the case of WeakHashMap if an object does not have any references then it's always eligible for GC even though it is associated with WeakHashMap that isgarbage collector dominates WeakHashMap.

```java
package Map;

import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void main(String[] args) {
        WeakHashMap<Temp, String> m = new WeakHashMap<>(); // Use generics for clarity
        Temp t = new Temp();
        m.put(t, "ashok");
        System.out.println(m); // {Temp=ashok}

        t = null; // Remove the strong reference to Temp

        System.out.println(m); // May or may not be empty depending on GC
    }
}

class Temp
{
    public String toString()
    {
        return "Temp";
    }
}
```

## TreeMap

1. The underlying data structure is RED-BLACK Tree.
2. Duplicate keys are not allowed but values can be duplicated.
3. Insertion order is not preserved and all entries will be inserted according to some sorting order of keys.
4. Default natural sorting: Keys need to be homogeneous (same class) and `Comparable` if relying on the default natural ordering. Otherwise, you'll get a `ClassCastException`.
5. Custom sorting with `Comparator`: Keys can be heterogeneous (different classes) and don't necessarily need to be `Comparable` themselves if you define a `Comparator` for custom sorting logic. However, they must be comparable according to the defined `Comparator` criteria.
6. Values: There are no restrictions on values. They can be heterogeneous and don't need to be `Comparable`.
7. A null key is **not** allowed in a `TreeMap` . You'll get a `NullPointerException` if you try to insert a null key.
8. Null key in non-empty `TreeMap`: **Correct.** Inserting a null key into a non-empty `TreeMap` will result in a `NullPointerException`.
9. Null values: There are no restrictions on null values. You can have null values in a `TreeMap`.

### TreeMap Constructor

1. **`TreeMap t = newTreeMap()`**: Creates an empty **`TreeMap`** with default natural sorting order.
2. **`TreeMap t = TreeMap(Comparator c)`**: Creates an empty **`TreeMap`** with a specified custom sorting order using a **`Comparator`**.
3.  **`TreeMap t = TreeMap(SortedMap m)`**: Creates a **`TreeMap`** with the same mappings as the specified **`SortedMap`**, sorted according to the natural ordering of its keys.
4.  **`TreeMap t= new TreeMap(Map m)`**: Creates a **`TreeMap`** with the same mappings as the specified **`Map`**, sorted according to the natural ordering of its keys.

Code : TreeMap using Natural Sorting Order

```java
package Map;

import java.util.*;

public class TreeMapExample {

    public static void main(String[] args) {
        // Creating a TreeMap to store student grades (String key, Integer value)
        TreeMap<String, Integer> studentGrades = new TreeMap<>();

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

/*  Output : ->
Student Grades (Sorted by Name):
Name: Alice, Grade: 85
Name: Bob, Grade: 90
Name: Charlie, Grade: 78
Name: David, Grade: 95
Charlie's Grade: 78
Updated Charlie's Grade: 80

 */
```

Code : TreeMap using Customized Sorting Order

```java
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
```