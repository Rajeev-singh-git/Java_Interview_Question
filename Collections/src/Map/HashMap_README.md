#  HashMap in Java

In Java, HashMap is a part of Java’s collection since Java 1.2. This class is found in java.util package. It provides the basic implementation of the Map interface of Java. HashMap in Java stores the data in (Key, Value) pairs, and you can access them by an index of another type (e.g. an Integer). One object is used as a key (index) to another object (value). If you try to insert the duplicate key in HashMap, it will replace the element of the corresponding key. 

# What is HashMap

**`HashMap`** is a class that implements the **`Map`** interface from the Java Collections Framework. It stores key-value pairs and provides constant-time performance for basic operations like get and put, assuming that the hash function disperses the elements properly among the buckets. This class makes no guarantees as to the order of the map. To use this class and its methods, you need to import java.util.HashMap package or its superclass.

## Key Points

1. **Key-Value Pairs**: HashMap stores data in the form of key-value pairs. Each key is unique and can map to at most one value.
2. **Hashing**: HashMap uses a hash table to store the key-value pairs. It uses the hashCode() method of the keys to determine their storage location in the hash table.
3. **Performance**: HashMap offers constant-time performance for basic operations like get and put, on average. However, the performance can degrade to O(n) in the worst case if there are too many hash collisions.
4. **Null Keys and Values**: HashMap allows one null key and multiple null values. Null keys are stored at the 0th index of the array. 
5. **Fail-Fast Iterator**: The iterators returned by HashMap are fail-fast, meaning they throw a ConcurrentModificationException if the map is structurally modified at any time after the iterator is created.
6. **Not Synchronized**: HashMap is not synchronized by default. If multiple threads access a HashMap concurrently and at least one of the threads modifies the map structurally, it must be synchronized externally.
7. **Iterating Over Entries**: HashMap provides methods to iterate over its entries, such as keySet(), values(), and entrySet().
8.  **Order**: Doesn't maintain insertion order of elements.  it is based on the hash code of the keys
9.  The underlying data structure is a Hashtable.
10. It is best suited for search operations.
11. It implements Serializable and Cloneable interfaces but does not support random access.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/HashMapDemo.java)
    
![HashMapinJava](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/b9bbffac-7132-4d45-9504-5964b1247f4f)



## **Use Cases:**

- Caching data for fast retrieval.
- Building configuration files or user preferences.
- Implementing dictionary-like functionality.
- Representing relationships between objects.

**1. HashMap Creation:**

`HashMap<String, Integer> map = new HashMap<>();`

- **Initial buckets:** When you create a HashMap, it starts with an internal array of 16 buckets (not 4). Think of these as empty compartments for storing key-value pairs.
- **Load factor threshold:** The HashMap keeps track of a "load factor," which is 0.75 by default. This means it starts considering resizing when the number of elements reaches 12 (0.75 * 16).

We can also customize the size while creation of HashMap.

`HashMap<String, Integer> map = new HashMap<>(16, 0.75f);`

**2. Adding a Key-Value Pair:**

- **Hashing:** When you put a key-value pair into the HashMap (e.g., `map.put("physics", 34)`), the key's `hashCode()` method is called to generate a unique integer value (the "hash").
- **Index calculation:** The hash is used to determine the index of the bucket where the key-value pair should be stored. This is done using a formula that ensures a relatively even distribution of keys across buckets.
- **Node creation:** If the bucket is empty, a new node is created to hold the key-value pair. The node contains three things: the key, the value, and a reference to the next node (if any).
- **Collision handling:** If multiple keys hash to the same index (a collision), the HashMap uses a linked list to store them within the bucket. The `equals()` method is used to compare keys and ensure no duplicates.
- If content are same. Then value will get updated.

**3. Retrieving a Value:**

- **Hashing again:** When you want to retrieve a value based on a key (e.g., `map.get("physics")`), the HashMap again calls the key's `hashCode()` method to find the appropriate bucket.
- **Linked list traversal:** If there's a linked list in the bucket (due to collisions), the HashMap uses the `equals()` method to compare keys with each node until it finds a match.
- **Value return:** If the key is found, the corresponding value is returned. If not, `null` is returned.

**4. Maintaining Efficiency:**

- **Resizing:** If the load factor threshold is reached (meaning too many collisions are happening), the HashMap resizes its internal array to double the size. This helps spread out the elements and reduce collisions, improving performance.
- **Hash function importance:** The quality of the `hashCode()` implementation for your keys is crucial. A good hash function should distribute keys evenly across buckets to minimize collisions.

**Remember:**

- **Hash is not stored in nodes:** Nodes only store the key, value, and next node reference, not the hash itself. The hash is only used for initial bucket calculation.
- **Resizing strategy:** The HashMap typically doubles the array size when resizing, not just adding a few buckets
1. **Threshold and Resize:**
    - The threshold is calculated as the product of the load factor and the current capacity. When the number of elements exceeds the threshold, the **`HashMap`** is resized, and the number of buckets is doubled.
    - The resizing operation involves rehashing all existing key-value pairs to the new, larger array of buckets.

```java

int threshold = (int) (capacity * loadFactor);

```

If null value is entered in key, it get stored at bucket or index 0.

![h2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/5fa39ba3-67f6-4426-87f7-c673765cd953)


# Keys in HashMap

keys must support a method to generate a **consistent integer hashcode**, which is a numerical representation used for efficient mapping and retrieval. Here's how it works:

1. **Hashcode Generation:**
    - Each key must have a `hashCode()` method (or a similar mechanism in different languages) that returns an integer value based on the key's content.
    - This hashcode doesn't replace the key itself; it's a separate value derived from it.
    - The hashcode should be consistent for equal keys, meaning the same key always produces the same hashcode.
2. **Bucket Mapping:**
    - The hashmap uses the hashcode, not the key itself, to determine which internal bucket to store or retrieve the key-value pair from.
    - It applies a mathematical operation (usually modulo) to the hashcode to get a bucket index.
3. **Equality Comparison:**
    - When searching for a key, the hashmap first finds the appropriate bucket using the hashcode.
    - Within the bucket, it compares the actual keys using the `equals()` method to locate the exact key-value pair.

**So, while hashcodes are integers, the keys themselves can be of any type, as long as they meet the following requirements:**

- **Hashcode Generation:** They must have a method to generate a consistent integer hashcode.
- **Equality Comparison:** They must have a method to determine if two keys are equal.

**Common hashable key types include:**

- **Primitive integers:** `int`, `long`, etc. (which directly serve as their own hashcodes)
- **String objects:** Each string has a unique hashcode based on its character content.
- **Custom objects:** You can define `hashCode()` and `equals()` methods for custom objects to make them hashable.

**Key points to remember:**

- Hashability is about the ability to generate a hashcode, not about converting keys to integers.
- Hashcodes are used for efficient bucket mapping, not for replacing keys.
- Equality comparison is still essential for accurate retrieval within buckets.

# Collision

When two or more **different keys** end up with the **same hash code**. This means they get mapped to the same **bucket** within the HashMap's internal structure. Here's a breakdown:

**1. Hashing and Buckets:**

- When you add a key-value pair to a HashMap, the key's `hashCode()` method is called to generate a numerical value representing the key's data.
- This hash code acts as a "quick guide" to pinpoint the appropriate bucket within the HashMap's internal array where the key-value pair should be stored.
- However, with a limited number of buckets and a potentially infinite number of possible keys, collisions are inevitable.

**2. Consequences of Collision:**

- If two keys have the same hash code, they land in the same bucket, despite being different keys.
- This can lead to retrieval problems: searching for one key might mistakenly return the other key in the same bucket.
- Performance can also be affected, as the HashMap needs to perform additional checks to differentiate between the collided keys within the same bucket.

**3. Collision Resolution:**

- Java's HashMap employs **chaining** to handle collisions. When a collision occurs, the colliding key-value pair is not simply overwritten, but is appended to a linked list within the corresponding bucket.
- Searching for a key involves iterating through this linked list and using the `equals()` method to compare each key with the search key until the desired key is found.

**4. Key Takeaways:**

- Collisions are a natural phenomenon in hashmaps, but efficient collision resolution mechanisms like chaining ensure their functionality.
- The `equals()` method plays a crucial role in distinguishing between collided keys within the same bucket and identifying the correct one.
- Understanding collisions and how they are handled is essential for effectively using HashMaps in Java.

# What if generated hahsCode is very large

Dividing by 10 and keeping the remainder can reduce the size of a hashcode, but

Example :

If the hashcode is a large integer but the hashmap's internal array has a size of, say, 100 slots, you can reduce the hashcode's size by calculating **`hashcode % 100`**. This operation ensures that the resulting hash value falls within the range of array indices, allowing efficient storage and retrieval of key-value pairs in the hashmap.

**it's not always a good idea for several reasons**:

**1. Collision risk:** Hash functions should spread keys out evenly across the available buckets. Dividing by 10 is a very simple hashing strategy and is likely to cluster many keys with the same remainder, leading to **collisions**. This would negate the efficiency benefits of hashing and slow down lookups in your HashMap.

**2. Information loss:** Discarding the majority of the hashcode throws away valuable information about the key. A good hash function should consider all aspects of the key to create a unique and spread-out distribution. By only using the remainder, you're losing most of the key's fingerprint and reducing the effectiveness of the hashcode.

**3. Ineffectiveness for large hashcodes:** While dividing by 10 might work for small integers, it may not significantly reduce the size of very large hashcodes. In extreme cases, it might even make the remainder larger due to carry-over during division.

**4. Alternatives:** Instead of simply dividing, consider better hashing strategies that:

- **Mix different parts of the key:** Take different bits or bytes from the key and combine them using XOR, rotations, or other operations to create a more unique and distributed hashcode.
- **Use built-in functions:** Many programming languages offer built-in hash functions specifically designed for different data types. These functions are usually more efficient and reliable than simple division.
- **Adapt based on data:** If you have specific knowledge about your data distribution, you can design a custom hashing strategy that takes advantage of those properties.

# **Complexity of HashMap in Java**

HashMap provides constant time complexity for basic operations, get and put if the hash function is properly written and it disperses the elements properly among the buckets. Iteration over HashMap depends on the capacity of HashMap and the number of key-value pairs. Basically, it is directly proportional to the capacity + size. Capacity is the number of buckets in HashMap. So it is not a good idea to keep a high number of buckets in HashMap initially.

![h1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/02616baf-5131-49ac-b8ea-828219937c4e)


# **HashMap vs. Hashtable in Java**

| Feature | HashMap | Hashtable |
| --- | --- | --- |
| Thread Safety | Non-synchronized (requires manual synchronization) | Synchronized (thread-safe for concurrent access) |
| Null Values | Allows one null key and any number of null values | Doesn't allow null keys or values (throws NullPointerException) |
| Performance | Faster due to lack of synchronization | Slower due to synchronization overhead |
| Iteration | Uses Iterator | Uses both Iterator and Enumeration (recommended to use Iterator) |
| Inheritance | Extends AbstractMap class | Extends legacy Dictionary class |
| Use Cases | Non-threaded applications where performance is crucial | Multithreaded applications requiring thread safety |
| Alternatives | ConcurrentHashMapDemo (thread-safe HashMap with better concurrency), LinkedHashMap (predictable iteration order) | N/A |


