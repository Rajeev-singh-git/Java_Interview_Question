# Map

The **`Map`** interface in Java is used to represent a collection of objects as key-value pairs.

- **Purpose**: Represents a group of objects as key-value pairs.
- **Key and Value**: Both key and value are objects.
- **Duplicate Keys**: Not allowed, but values can be duplicated.
- **Entry**: Each key-value pair in a **`Map`** is referred to as one entry.

![c1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/7ec820c4-81a4-44d7-9910-5264db93fa12)


Map is considered as collection of Entry Object.

![c2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/b6ca4b51-caf5-4322-94f9-b85d87dd2f03)

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

[Example Code : →](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/MapMethodDemo.java)



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

[Code : → Synchronized version of HashMap](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/SynchronizedHashMapExample.java)



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

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/LinkedHashMapDemo.java)


## IdentityHashMap

It is exactly same as HashMap except the following differences:

- In **`HashMap`**, duplicate keys are identified using the **`.equals()`** method, which compares keys based on content.
- In **`IdentityHashMap`**, duplicate keys are identified using **`==`** (double equal operator), which compares keys based on reference.

  [code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/IdentityHashMapDemo.java)




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

[Code : TreeMap using Natural Sorting Order](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/TreeMapExample.java)


[Code : TreeMap using Customized Sorting Order](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/TreeMapComparatorExample.java)
