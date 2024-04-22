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

/*
{key=value2}
HashMap size: 1
IdentityHashMap size: 2
{key=value2, key=value1}
HashMap Key: key, Value: value2
IdentityHashMap Key: key, Value: value2
IdentityHashMap Key: key, Value: value1
 */