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

/*
key1 : value1
key2 : value2
key3 : value3
 */