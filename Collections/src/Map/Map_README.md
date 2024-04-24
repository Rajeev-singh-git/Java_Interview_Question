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



• **ConcurrentHashMapDemo:** If you need a more efficient concurrent map implementation with better performance than a synchronized `HashMap`, consider using `ConcurrentHashMapDemo` from the `java.util.concurrent` package. It's designed for concurrent access without explicit synchronization.

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

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/IdentityHashMapDemo.java)




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

## HashTable

1. The underlying data structure is Hashtable.
2. Insertion order is not preserved and it is based on hash code of the keys.
3. Heterogeneous objects are allowed for both keys and values.
4. Null key (or) null value is not allowed otherwise we will get NullPointerException.
5. Duplicate keys are not allowed but values can be duplicated.
6. Every method present inside Hashtable is syncronized and hence Hashtable objet is Thread-safe.

### Constructor

1. **`HashTable h  = new Hashtable()`**: Creates an empty **`Hashtable`** object with a default initial capacity of 11 and a default fill ratio (load factor) of 0.75.
2. **`HashTable h = new Hashtable(int initialCapacity)`**: Creates an empty **`Hashtable`** object with the specified initial capacity and the default fill ratio (load factor) of 0.75.
3. **`HashTable h = new Hashtable(int initialCapacity, float loadFactor)`**: Creates an empty **`Hashtable`** object with the specified initial capacity and load factor (fill ratio).
4. **`HashTable h = new Hashtable(Map m)`**: Creates a **`Hashtable`** with the same mappings as the specified **`Map`**. The initial capacity of the **`Hashtable`** is set to twice the size of the specified map or 11 (whichever is greater), and the default load factor (0.75) is used.

Code Example

```java
package Map;

import java.util.HashMap;
import java.util.Map;

public class HashTableDemo {

    public static void main(String [] args){

        Map<Temporary, String> h = new HashMap<>();

        h.put(new Temporary(5), "A");
        h.put(new Temporary(2), "B");
        h.put(new Temporary(6), "C");
        h.put(new Temporary(15), "D");
        h.put(new Temporary(23), "E");
        h.put(new Temporary(16), "F");

        System.out.println(h); //{6=C, 16=F, 5=A, 15=D, 2=B, 23=E}

    }
}

class Temporary{
    int i;

    Temporary(int i){
        this.i =i;
    }

    @Override
    public int hashCode() {
        return i;
    }

    @Override
    public String toString() {
        return String.valueOf(i);
    }
}

```
![s1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/bc2b5ca3-6081-4903-8a40-5c35294d7b96)

- Total 11 boxes are there because default initial capacity is of HashTable is 11.
- Each key is stored to their corresponding box no. Ex : Key 6 is stored in box no 6, key 15 is stored in box 4 because box no 15 is not there. So, it follow rule 15 % 11. 15 is divided by total no of box and whatever the remainder comes, key is stored into that box number. Here 4 is remainder, so key 15 is added to remainder box number 4.
- While printing preference will be top to bottom and  right to left in same box.

### Properties

1. Properties class is the child class of Hashtable.
2. In our program if anything which changes frequently like DBUserName, Password etc., such type of values not recommended to hardcode in java application because for every change we have to recompile, rebuild and redeploy the application and even server restart also required sometimes it creates a big business impact to the client.
3. Such type of variable  we have to hardcode in property files and we have to read the values from the property files into java application.
4. The main advantage in this approach is if there is any change in property files automatically those changes will be available to java application just redeployment is enough.
5. By using Properties object we can read and hold properties from property files into java application.

### Constructor

```java
Properties p = new Properties();
```

In properties both key and value "should be String type only”.

Methods

1. **`String getProperty(String propertyName)`**
    - Returns the value associated with the specified **`propertyName`**.
2. **`String setProperty(String propertyName, String propertyValue)`**
    - Sets a new property with the specified **`propertyName`** and **`propertyValue`**.
3. **`Enumeration propertyNames()`**
    - Returns an enumeration of all the property names in this **`Properties`** object.
4. **`void load(InputStream is) throws IOException`**
    - Loads properties from the given **`InputStream`** into this **`Properties`** object.
5. **`void store(OutputStream os, String comment) throws IOException`**
    - Stores the properties represented by this **`Properties`** object into the specified **`OutputStream`**, along with an optional comment.

Code

```java
import java.util.*;
import java.io.*;

public class PropertiesFileDemo {

    public static void main(String[] args) throws Exception {

        // Create a Properties object
        Properties p = new Properties();

        // Load properties from the file "abc.properties"
        FileInputStream fis = new FileInputStream("abc.properties");
        p.load(fis);
        fis.close(); // Close the input stream

        // Print all properties
        System.out.println("Properties loaded from abc.properties:");
        System.out.println(p);

        // Get a specific property value
        String venkiValue = p.getProperty("venki");
        System.out.println("Value of 'venki': " + venkiValue);

        // Set a new property
        p.setProperty("rag", "181818");

         // Get a specific property value
         String ragValue = p.getProperty("rag");
         System.out.println("Value of 'rag': " + ragValue);

        // Enumerate through all property names
        System.out.println("All property names:");
        Enumeration<?> e = p.propertyNames();
        while (e.hasMoreElements()) {
            String propertyName = (String) e.nextElement();
            System.out.println(propertyName);
        }

        // Store the updated properties back to the file
        FileOutputStream fos = new FileOutputStream("abc.properties");
        p.store(fos, "Updated by Ashok for SCJP demo class");
        fos.close(); // Close the output stream
    }   
}
```

Output :

```java
Properties loaded from abc.properties:
{password=tiger,, venki=999, nag=181818, user=scott, }
Value of 'venki': 999
Value of 'rag': 181818
All property names:
user
nag
password
venki
rag
```
## Navigable Map

The **`NavigableMap`** interface  extends the **`SortedMap`** interface and provides methods for navigating and manipulating sorted maps based on key order.

![s1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/ce847f30-6312-4c6b-9737-052e5b301f46)


### **Key Methods**

1. **`ceilingKey(e)`**:
   - Returns the least key greater than or equal to the specified key **`e`**.
2. **`higherKey(e)`**:
   - Returns the least key strictly greater than the specified key **`e`**.
3. **`floorKey(e)`**:
   - Returns the greatest key less than or equal to the specified key **`e`**.
4. **`lowerKey(e)`**:
   - Returns the greatest key strictly less than the specified key **`e`**.
5. **`pollFirstEntry()`**:
   - Removes and returns the entry associated with the lowest key in the map.
6. **`pollLastEntry()`**:
   - Removes and returns the entry associated with the highest key in the map.
7. **`descendingMap()`**:
   - Returns a **`NavigableMap`** containing the entries of the original map in reverse order.


[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/NavigableMapDemo.java)
