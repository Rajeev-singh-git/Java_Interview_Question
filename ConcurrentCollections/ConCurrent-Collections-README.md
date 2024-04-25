# Need for Concurrent Collection

The need for concurrent collections in Java arises from the challenges posed by traditional collections (like ArrayList, HashMap, etc.) in a multi-threaded environment:

1. **Thread Safety**: Traditional collections are not inherently thread-safe. When accessed by multiple threads simultaneously, they can lead to data inconsistency problems, such as race conditions or data corruption.
2. **Performance**: While traditional collections can be made thread-safe using synchronization (e.g., Vector, Hashtable, synchronizedList(), synchronizedSet(), synchronizedMap()), this comes at a performance cost. Synchronization can lead to decreased performance due to the overhead of managing locks.
3. **Blocking Behavior**: Synchronized collections typically allow only one thread at a time to access or modify the collection. This means that even read operations can incur waiting times, as each operation must acquire and release locks.
4. **Concurrent Modification**: Traditional collections can throw **`ConcurrentModificationException`** if one thread modifies the collection while another is iterating over it. This constraint limits the scalability and flexibility of multi-threaded applications.

```java
import java.util.ArrayList;
import java.util.Iterator;

public class Test {

    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();
        al.add("A");
        al.add("B");
        al.add("C");
        Iterator itr = al.iterator();
        while (itr.hasNext()) {
            String s = (String) itr.next();
            System.out.println(s);
     //       al.add("D");   ConcurrentModificationException
        }

    }
}

```

To address these challenges, Java introduced concurrent collections starting from Java 1.5. These collections are designed specifically for multi-threaded environments and offer several advantages:

- **Thread Safety**: Concurrent collections are designed to be safely accessed by multiple threads without explicit synchronization.
- **Performance**: They are optimized for concurrent access, allowing multiple threads to read and write simultaneously with minimal contention and locking overhead.
- **Iterating and Modifying**: Concurrent collections typically allow for safe iteration while the collection is being modified by other threads. They use different strategies (like iterators based on snapshots of the collection) to handle concurrent modifications.
- **Scalability**: Concurrent collections are suitable for scalable multi-threaded applications where high concurrency is required. They aim to minimize blocking and contention between threads.

Some commonly used concurrent collections in Java include **`ConcurrentHashMap`**, **`ConcurrentSkipListMap`**, **`ConcurrentLinkedQueue`**, **`CopyOnWriteArrayList`**, and others. These collections provide efficient and thread-safe alternatives to traditional collections for concurrent programming scenarios.

# ConcurrntMap


![a1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/5d4e72e8-24ff-4e7c-b3e4-86f2d6267f64)


It defines 3 specific methods.

1) **`Object putIfAbsent(Object Key, Object Value)`**

   To Add Entry to the Map if the specified Key is Not Already available.

```java
Object putIfAbsent(Object key, Object value)
if (!map.containsKey(key)) {
map.put(key, value);
}
else {
returnmap.get(key);
}
```

| put() | putIfAbsent() |
| --- | --- |
| - If the key is already present, replaces the old value with the new value and returns the old value.      -If the key is not present, adds the key-value pair to the map and returns null. | - If the key is already present, does not add a new entry and returns the existing value associated with the key.                                            -If the key is not present, adds the key-value pair to the map and returns null. |

Code: →

```java
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args){

        ConcurrentHashMap<Integer,String> m = new ConcurrentHashMap<>();
        m.put(101,"Ravi");
        m.put(102,"Raj");
        System.out.println(m);        //{101=Ravi, 102=Raj}
        m.putIfAbsent(101,"Siva");
        System.out.println(m);        //101=Ravi, 102=Raj}
    }
}

```

1. **`boolean remove(Object key, Object value)`**
   Removes the Entry if the Key associated with specified Value Only.

```java
if ( map.containsKey (key) &&map.get(key).equals(value) ) {
		map.remove(key);
		return true;
}
else {
		return false;
}
```

```java
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo2 {

    public static void main(String[] args){

        ConcurrentHashMap<Integer,String> m = new ConcurrentHashMap<>();
        m.put(101,"Ravi");
        m.remove(101,"Raj");
        System.out.println(m);        //{101=Ravi, 102=Raj}
        m.remove(101,"Ravi");
        System.out.println(m);        //{}
    }
}

```

3) `**boolean replace(Object key, Object oldValue, Object newValue)**`

```java
if ( map.containsKey (key) &&map.get(key).equals(oldvalue) ) 
{
	map.put(key, newValue);
	return true;
}
else {
	return false;
}
```

```java
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo3 {

    public static void main(String[] args){

        ConcurrentHashMap<Integer,String> m = new ConcurrentHashMap<>();
        m.put(101,"Ravi");
        m.replace(101,"Raj","Shiva");
        System.out.println(m);        //{101=Ravi}
        m.replace(101,"Ravi","Shiva");
        System.out.println(m);        //{101=Shiva}
    }
}

```

# ConcurrentHashMap

1. **Underlying Data Structure**: **`ConcurrentHashMap`** internally uses a hashtable data structure, similar to **`Hashtable`**. However, unlike **`Hashtable`**, **`ConcurrentHashMap`** does not use a global lock for all operations.
2. **Concurrent Read and Thread-Safe Update Operations**: **`ConcurrentHashMap`** supports concurrent read operations without requiring locks. Write (update) operations are also thread-safe but use a different approach to manage concurrency efficiently.
3. **Fine-grained Locking**: To allow concurrent updates, **`ConcurrentHashMap`** divides the underlying data structure into smaller segments or "buckets". Each bucket is independently locked, enabling multiple threads to update different parts of the map simultaneously.
4. **Concurrency Level**: The concurrency level specifies the number of segments (buckets) in the hashmap. By default, **`ConcurrentHashMap`** has a concurrency level of 16, meaning it can handle up to 16 simultaneous write operations without contention.
5. **Null Values**: **`ConcurrentHashMap`** does not allow **`null`** keys or values. Attempting to insert **`null`** as a key or value will result in a **`NullPointerException`**.
6. **Iteration and Updates**: Unlike some other collections, **`ConcurrentHashMap`** does not throw **`ConcurrentModificationException`** when one thread is iterating over the map while another thread modifies it. This is achieved through its internal locking mechanism and segmented structure.

## Constructors

| Constructor | Description |
| --- | --- |
| ConcurrentHashMap() | Creates an empty ConcurrentHashMap with default initial capacity (16), default fill ratio (0.75), and default concurrency level (16). |
| ConcurrentHashMap(int initialCapacity) | Creates an empty ConcurrentHashMap with the specified initial capacity, default fill ratio (0.75), and default concurrency level (16). |
| ConcurrentHashMap(int initialCapacity, float fillRatio) | Creates an empty ConcurrentHashMap with the specified initial capacity and fill ratio, and default concurrency level (16). |
| ConcurrentHashMap(int initialCapacity, float fillRatio, int concurrencyLevel) | Creates an empty ConcurrentHashMap with the specified initial capacity, fill ratio, and concurrency level. |
| new ConcurrentHashMap(Map m) | Creates a ConcurrentHashMap containing the mappings in the specified map. |

Code : Program to show it is possible to perform Modification while first thread is operating.

```java
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MyThread extends Thread{

    static ConcurrentHashMap<Integer,String> m = new ConcurrentHashMap<>();

    public void run(){
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){}
        System.out.println("Child Thread updating Map");
        m.put(103, "C");
    }

    public static void main(String [] args) throws InterruptedException{
        m.put(101,"A");
        m.put(102,"B");

    MyThread t = new MyThread();
    t.start();

    Set<Integer> s = m.keySet();
    Iterator<Integer> itr  = s.iterator();
    while(itr.hasNext()){
        Integer I1 = itr.next();
        System.out.println("Main Thread iterating and Current Entry is: " + I1 + "..............." + m.get(I1));
        Thread.sleep(3000);

    }
     System.out.println(m);
    }
}
```

**`ConcurrentHashMap`** is optimized for scenarios where multiple threads need to read and update a shared map concurrently. It achieves thread safety and high concurrency by using fine-grained locks at the bucket level, allowing efficient and scalable concurrent operations without the need for a global lock or extensive synchronization. Additionally, its design ensures that common operations like iteration and updates can be performed concurrently without risking data corruption or concurrency issues. However, it's important to note that while **`ConcurrentHashMap`** is suitable for many concurrent use cases, developers should still be aware of its specific behaviors and limitations, such as the restriction on **`null`** keys and values.

- Use **`ConcurrentHashMap`** when you need thread-safe concurrent access without explicit synchronization.
- **`ConcurrentHashMap`** provides predictable iteration behavior by creating a read-only snapshot of the map during iteration.
- Avoid using non-thread-safe collections like **`HashMap`** in concurrent scenarios to prevent **`ConcurrentModificationException`** and ensure thread safety.

## Comparison between **`HashMap`** and **`ConcurrentHashMap`**

| Property | HashMap | ConcurrentHashMap |
| --- | --- | --- |
| Thread Safety | Not thread-safe | Thread-safe |
| Performance | Relatively high | Relatively low |
| Concurrent Operations | Concurrent operations not supported | Supports concurrent read and write operations |
| Concurrent Modification | ConcurrentModificationException may occur during iteration | Supports concurrent modification during iteration |
| Iteration Behavior | Fail-Fast iterator (may throw ConcurrentModificationException) | Fail-Safe iterator (does not throw ConcurrentModificationException) |
| Null Keys and Values | Allowed | Not allowed (throws NullPointerException) |
| Introduced Version | Introduced in Java 1.2 | Introduced in Java 1.5 |

## Comparison between `ConcurrentHashMap`, `synchronizedMap()`, and `Hashtable`:

| Property | ConcurrentHashMap | synchronizedMap() | Hashtable |
| --- | --- | --- | --- |
| Thread Safety | Thread-safe with bucket-level locking | Thread-safe with whole map object locking | Thread-safe with whole map object locking |
| Concurrent Operations | Supports concurrent read and write operations | Only one thread can perform operations at a time | Only one thread can perform operations at a time |
| Read Operations | Can be performed without locking | Requires lock on whole map object for read operations | Requires lock on whole map object for read operations |
| Write Operations | Requires bucket-level lock for write operations | Requires lock on whole map object for write operations | Requires lock on whole map object for write operations |
| Concurrent Modification | Allows modifications by other threads during iteration | Does not allow modifications by other threads during iteration | Does not allow modifications by other threads during iteration |
| Iterator Behavior | Fail-Safe (does not throw ConcurrentModificationException) | Fail-Fast (throws ConcurrentModificationException) | Fail-Fast (throws ConcurrentModificationException) |
| Null Keys and Values | Not allowed for both keys and values | Allowed for both keys and values | Not allowed for both keys and values |
| Introduced Version | Introduced in Java 1.5 | Introduced in Java 1.2 | Introduced in Java 1.0 |

# CopyOnWriteArrayList

![a2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/735e2bcf-a854-4a9e-b035-607a1cd911dc)


- `CopyOnWriteArrayList` is a thread-safe alternative to `ArrayList`. It achieves this by creating a copy of the underlying array whenever a modification (add, set, remove) occurs.
- This copy is used for updates, while the original array remains unchanged. Threads performing read operations continue to access the original array, unaffected by concurrent modifications.

**Performance:**

- Copying the entire array for updates can be expensive. `CopyOnWriteArrayList` is ideal for scenarios with frequent reads and infrequent writes.

**Key Characteristics:**

- **Insertion Order:** Maintains the order elements were added.
- **Duplicates:** Allows duplicate elements.
- **Heterogeneity:** Can hold objects of different types.
- **Null Values:** Allows null elements.
- **Interfaces:** Implements `Serializable`, `Cloneable`, and `RandomAccess`.
- **Fail-Safe Iterators:** Iterating over a `CopyOnWriteArrayList` while modifying it won't cause a `ConcurrentModificationException`. The iterator works on a snapshot of the data at the time of creation.
- **Iterator Limitations:** Unlike `ArrayList` iterators, `CopyOnWriteArrayList` iterators cannot modify the list through `remove` methods (they throw `UnsupportedOperationException`).

**In Summary:**

`CopyOnWriteArrayList` provides a thread-safe solution for collections with frequent reads and less frequent writes. While copying the array adds overhead, it guarantees thread safety and avoids `ConcurrentModificationException` issues.

## Constructor

1. **`CopyOnWriteArrayList l = new CopyOnWriteArrayList();`**
    - Creates an empty **`CopyOnWriteArrayList`**.
2. **`CopyOnWriteArrayList l = new CopyOnWriteArrayList(Collection c);`**
    - Creates a **`CopyOnWriteArrayList`** containing the elements of the specified collection, in the order they are returned by the collection's iterator.
3. **`CopyOnWriteArrayList l = new CopyOnWriteArrayList(Object[] a);`**
    - Creates a **`CopyOnWriteArrayList`** containing the elements of the specified array, in the order they appear in the array.

### Methods

1. `**booleanaddIfAbsent(Object o)**`: The Element will be Added if and Only if List doesn’t contain this Element.

```java
public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> l = new CopyOnWriteArrayList<>();
        
        l.add("A");
        l.add("A");
        l.addIfAbsent("B");
        l.addIfAbsent("B");
        
        System.out.println(l); // Output: [A, A, B]
    }
}
```

1. **`addAllAbsent(Collection c)`** method of **`CopyOnWriteArrayList`** adds all elements from the specified collection to the list if they are absent in the list. It returns the number of elements that were added to the list.

```java
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> l = new ArrayList<>();
        l.add("A");
        l.add("B");

        CopyOnWriteArrayList<String> l1 = new CopyOnWriteArrayList<>();
        l1.add("A");
        l1.add("C");
        System.out.println("Original List: " + l1); // Output: [A, C]

        // Adding elements from ArrayList l to l1
        l1.addAll(l);
        System.out.println("After adding all elements from ArrayList l: " + l1); // Output: [A, C, A, B]

        ArrayList<String> l2 = new ArrayList<>();
        l2.add("A");
        l2.add("D");

        // Adding absent elements from ArrayList l2 to l1
        int numAdded = l1.addAllAbsent(l2);
        System.out.println("After adding absent elements from ArrayList l2: " + l1); // Output: [A, C, A, B, D]
        System.out.println("Number of elements added: " + numAdded); // Output: 1
    }
}

```

## Difference between `ArrayList` and `CopyOnWriteArrayList`

| Feature | ArrayList | CopyOnWriteArrayList |
| --- | --- | --- |
| Thread Safety | Not thread-safe; concurrent modifications may result in ConcurrentModificationException | Not thread-safe; each update operation is performed on a separate cloned copy of the array |
| Concurrent Iteration | Concurrent modifications during iteration may result in ConcurrentModificationException | Allows other threads to modify the list safely during iteration without throwing ConcurrentModificationException |
| Iterator Behavior | Fail-Fast (may throw ConcurrentModificationException if modified during iteration) | Fail-Safe (does not throw ConcurrentModificationException even if modified during iteration) |
| Iterator Remove Operation | Supports remove operation (remove() method) during iteration | Does not support remove operation (remove() method) during iteration; calling remove() results in UnsupportedOperationException |
| Introduction Version | Introduced in Java 1.2 | Introduced in Java 1.5 |

## Differences between `CopyOnWriteArrayList`, `synchronizedList()` and `vector()`

| Feature | CopyOnWriteArrayList | synchronizedList() | Vector |
| --- | --- | --- | --- |
| Thread Safety | Each update operation is performed on a separate cloned copy of the array; allows concurrent access without ConcurrentModificationException | Thread-safe; only one thread can access the list at a time | Thread-safe; only one thread can operate on the vector at a time |
| Concurrent Access | Allows multiple threads to operate concurrently on CopyOnWriteArrayList | Allows only one thread to operate on the list at a time | Allows only one thread to operate on the vector at a time |
| Concurrent Iteration | Allows other threads to modify the list during iteration without ConcurrentModificationException | Other threads are not allowed to modify the list during iteration | Other threads are not allowed to modify the vector during iteration |
| Iterator Behavior | Fail-Safe (does not raise ConcurrentModificationException even if modified during iteration) | Fail-Fast (raises ConcurrentModificationException if modified during iteration) | Fail-Fast (raises ConcurrentModificationException if modified during iteration) |
| Iterator Remove Operation | Does not support remove operation (remove() method) during iteration; calling remove() results in UnsupportedOperationException | Supports remove operation (remove() method) during iteration | Supports remove operation (remove() method) during iteration |
| Introduction Version | Introduced in Java 1.5 | Introduced in Java 1.2 | Introduced in Java 1.0 |

# CopyOnWriteArraySet :



![a3](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/c7f68df8-ed68-4d3d-8f8c-02c3b35763db)



- It is a Thread Safe Version of Set.
- Internally Implement by CopyOnWriteArrayList.
- Insertion Order is Preserved.
- Duplicate Objects are Not allowed.
- Multiple Threads can perform Read Operation simultaneously but for Every
  update Operation a Separate cloned Copy will be Created.
- As for Every Update Operation a Separate cloned Copy will be Created which is Costly Hence if Multiple Update Operation are required then it is Not recommended to use CopyOnWriteArraySet.
- While One Thread iterating Set the Other Threads are allowed to Modify Set and we won’t get ConcurrentModificationException.
- Iterator of CopyOnWriteArraySet can PerformOnly Read Operation and won’t Perform.
- Remove Operation. Otherwise we will get RuntimeException:
  UnsupportedOperatonException.

## Constructor

1. **`CopyOnWriteArraySet s = new CopyOnWriteArraySet();`**
    - Creates an empty **`CopyOnWriteArraySet`** object.
2. **`CopyOnWriteArraySet s = new CopyOnWriteArraySet(Collection c);`**
    - Creates a **`CopyOnWriteArraySet`** object that contains elements equivalent to the specified collection **`c`**.


Methods : Whatever Methods Present in Collection and Set Interfaces are the Only Methods Applicable for CopyOnWriteArraySet and there are No Special Methods.

## Difference between **`CopyOnWriteArraySet()` and `synchronizedSet()`**

| Feature | CopyOnWriteArraySet() | synchronizedSet() |
| --- | --- | --- |
| Thread Safety | Thread safe due to separate cloned copy | Thread safe, allows one thread at a time |
| Concurrent Modification | Allows modifications while iterating | ConcurrentModificationException on modification |
| Iterator Behavior | Fail-safe iterator | Fail-fast iterator |
| Iterator Remove Operation | Read-only iterator, remove operation unsupported | Supports both read and remove operations |
| Introduced In | Introduced in Java 5 | Introduced in Java 7 |

# **Fail-Fast Iterator and Fail-Safe Iterator:**

## **Fail-Fast Iterator :**

- **Definition**: A fail-fast iterator immediately throws a **`ConcurrentModificationException`** if it detects any structural modification (such as adding, removing, or updating elements) in the underlying collection while iterating.
- **Implementation**: Internally, fail-fast iterators use a modification count (often called **`modCount`**) to track the number of structural modifications made to the collection. During iteration, the iterator checks this modification count. If the count changes unexpectedly (indicating a concurrent modification by another thread), it throws **`ConcurrentModificationException`**.
- **Behavior**:
    - Concurrent modifications during iteration can lead to **`ConcurrentModificationException`**.
    - Designed for early detection of concurrent modifications to ensure fail-fast behavior.
- **Example**: Iterators of collections like **`ArrayList`**, **`HashMap`**, **`HashSet`**, etc., are generally fail-fast.

```java
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
Iterator<Integer> iterator = list.iterator();

while (iterator.hasNext()) {
    Integer number = iterator.next();
    System.out.println(number);
    // Concurrent modification - adding element while iterating
    list.add(4); // Throws ConcurrentModificationException
}
```

## **Fail-Safe Iterator:**

- **Definition**: A fail-safe iterator does not throw **`ConcurrentModificationException`** even if the underlying collection is structurally modified during iteration by other threads.
- **Implementation**: Fail-safe iterators typically work on a cloned or snapshot of the collection's state at the time the iterator was created. Any structural modifications made to the original collection after iterator creation do not affect the iterator's state.
- **Behavior**:
    - Modifications to the collection during iteration do not affect the fail-safe iterator.
    - Iteration continues on the original snapshot or cloned copy of the collection.
- **Example**: Iterators provided by concurrent collections like **`CopyOnWriteArrayList`**, **`CopyOnWriteArraySet`**, **`ConcurrentHashMap`**, etc., are generally fail-safe.

```java
CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3));
Iterator<Integer> iterator = list.iterator();

while (iterator.hasNext()) {
    Integer number = iterator.next();
    System.out.println(number);
    // Concurrent modification - adding element while iterating
    list.add(4); // No ConcurrentModificationException
}

System.out.println(list); // [1, 2, 3, 4]
```

| Property | Fail Fast | Fail Safe |
| --- | --- | --- |
| Throws ConcurrentModificationException? | Yes | No |
| Creates Cloned Copy? | No | Yes |
| Memory Considerations | Potential memory problems due to immediate failure | More memory consumption due to cloning |
| Examples | ArrayList, Vector, HashMap, HashSet | ConcurrentHashMap, CopyOnWriteArrayList, CopyOnWriteArraySet |

# Enum with Collection

![a4](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/a0e48959-0509-4e13-9eb0-0bda6c83e9bd)


### **EnumSet:**

- **Definition**: **`EnumSet`** is a specialized **`Set`** implementation intended for use with enum types. It internally uses bit vectors for efficient storage and performance.
- **Key Points**:
    - Specifically designed to work with enum constants.
    - Internally implemented using bit vectors for optimal performance.
    - Provides type safety by ensuring that all elements belong to the same enum type.
    - Elements are stored and iterated in the natural order of enum constants (ordinal order).
    - Iterators returned by **`EnumSet`** are fail-safe and do not throw **`ConcurrentModificationException`**.
    - Does not allow **`null`** elements (throws **`NullPointerException`** if attempted).
    - **`EnumSet`** is an abstract class with factory methods for creating instances (**`of()`**, **`range()`**, **`copyOf()`**, etc.).
    - Chooses between **`RegularEnumSet`** and **`JumboEnumSet`** internally based on the size of the enum set (for optimization).

### **EnumMap:**

- **Definition**: **`EnumMap`** is a specialized **`Map`** implementation that uses enum constants as keys.
- **Key Points**:
    - Designed specifically for enum keys, providing type safety.
    - Implemented internally using arrays (bit vectors) for performance optimization.
    - All keys must belong to the same enum type to ensure type safety.
    - **`EnumMap`** implements **`Serializable`** and **`Cloneable`** interfaces.
    - Iteration over **`EnumMap`** follows the order of enum constants (ordinal value).
    - **`null`** keys are not allowed and will result in **`NullPointerException`**.
    - Iterators of **`EnumMap`** are fail-safe and do not throw **`ConcurrentModificationException`**.

# Constructors

1. **`EnumMap m = new EnumMap(Class<KeyType> keyType)`**
    - Creates an empty EnumMap with the specified key type.
2. **`EnumMap m = new EnumMap(EnumMap<K, V> m1)`**
    - Creates an EnumMap with the same key type as **`m1`** and containing the same mappings.
3. **`EnumMap m = new EnumMap(Map<? extends Enum<K>, ? extends V> m1)`**
    - Creates an EnumMap equivalent to the given map **`m1`**, using the key type of **`m1`**.

### Methods :

EnumMap doesn’t contain any New Methods. We have to Use General Map Methods only.

### **Example Usage of `EnumSet` and `EnumMap`:**

### **EnumSet Example:**

[Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/ConcurrentCollections/src/Enum/EnumSetExample.java)

```java

import java.util.EnumSet;

public class EnumSetExample {

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        // Create an EnumSet of Day enum
        EnumSet<Day> weekdays = EnumSet.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY);

        // Iterate over the EnumSet (in ordinal order)
        for (Day day : weekdays) {
            System.out.println(day);
        }
    }
}

```

### **EnumMap Example:**

[Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/ConcurrentCollections/src/Enum/EnumMapExample.java)

```java

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

```

### **Summary:**

- **EnumSet** and **EnumMap** are specialized collections optimized for working with enum types.
- They provide type safety and efficient storage using internal implementations based on bit vectors (arrays).
- **`EnumSet`** is designed for set operations (collections of unique enum constants), while **`EnumMap`** is designed for mapping enum keys to values.
- Both collections ensure performance benefits and type safety by leveraging the ordinal order of enum constants.
- They are powerful tools for representing and working with enum-based data structures in Java applications. Choose **`EnumSet`** for sets of enum constants and **`EnumMap`** for mappings with enum keys. Use them when dealing with fixed, predefined sets of elements represented by enums to leverage their benefits in terms of performance and type safety.

# Queue

**Queues in Java**

- **Queue:** A fundamental data structure that follows the First-In-First-Out (FIFO) principle. Elements are inserted at the back (tail) and removed from the front (head). It's a child interface of `Collection` and is suitable for representing a sequence of items to be processed in order.
- **PriorityQueue:** An implementation of `Queue` that prioritizes elements based on a natural ordering or a custom comparator. High-priority elements are removed first. It's ideal for scenarios where elements need processing based on urgency or importance.

**BlockingQueue: Thread-Safe Queues for Producer-Consumer Problems**

- **BlockingQueue:** A specialized interface (not a child of `Queue`) residing in the `java.util.concurrent` package. It offers thread-safe queue operations with blocking behavior.
- **Thread Safety:** Ensures that multiple threads can access and modify the queue concurrently without data corruption.
- **Blocking Mechanism:**
    - `put()` blocks the producer thread if the queue is full, waiting until space becomes available.
    - `take()` blocks the consumer thread if the queue is empty, waiting until an element is added.
- **Producer-Consumer Pattern:** Well-suited for scenarios where one thread (producer) creates items and inserts them into the queue, while another thread (consumer) retrieves and processes them. The blocking mechanism ensures efficient coordination.

**TransferQueue: Synchronization for Message Passing**

- **TransferQueue:** Extends `BlockingQueue` and provides an additional method `transfer()`.
- **`transfer()`:** Blocks the producer thread not only until space is available but also until a consumer thread is ready to receive the element. This enforces strict message passing, ensuring an item is not added unless a consumer is present to handle it.
- **Message Passing Applications:** Useful for scenarios where guaranteed delivery of messages is crucial, such as in inter-thread communication or distributed systems.

**Deque: Double-Ended Queues for Flexibility**

- **Deque:** Represents a queue where insertion and removal can occur from both ends (front and back). It also extends `Collection`.
- **Double-Ended Operations:** Provides methods for `addFirst()`, `addLast()`, `removeFirst()`, and `removeLast()` alongside standard queue operations.
- **Flexibility:** Offers greater control over element positioning and retrieval compared to regular queues.
    - Example: A backtracking algorithm could use a `Deque` to store and restore intermediate states.

**BlockingDeque: Blocking Double-Ended Queues**

- **BlockingDeque:** Combines functionalities of `BlockingQueue` and `Deque`.
- **Thread-Safe Blocking Operations:** Inherits blocking behavior from `BlockingQueue` for thread-safe access.
- **Double-Ended Access:** Provides methods like `putFirst()`, `takeFirst()`, `putLast()`, and `takeLast()` for flexible insertion and removal from both ends.
- **Complex Producer-Consumer Scenarios:** Useful when you need thread-safe blocking operations on a double-ended queue, enabling fine-grained control and potentially different priorities for insertion and removal.

**Key Points:**

- Use `Queue` and `PriorityQueue` for simple FIFO or priority-based ordering.
- Employ `BlockingQueue` for thread-safe queue operations with producer-consumer patterns.
- Leverage `TransferQueue` for synchronized message passing scenarios.
- Utilize `Deque` when double-ended access is required.
- Opt for `BlockingDeque` for thread-safe double-ended queues with blocking behavior.

[All Code used](https://github.com/Rajeev-singh-git/Java_Interview_Question/tree/main/ConcurrentCollections/src)
