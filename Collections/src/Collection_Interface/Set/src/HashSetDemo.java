import java.util.Arrays;
import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        // Creating HashSet using different constructors
        HashSet<String> set1 = new HashSet<>(); // Default capacity (16) and load factor (0.75)
        HashSet<Integer> set2 = new HashSet<>(20); // Custom initial capacity (20), default load factor (0.75)
        HashSet<Double> set3 = new HashSet<>(30, 0.6f); // Custom initial capacity (30) and load factor (0.6)

        // Creating HashSet from a collection
        HashSet<String> set4 = new HashSet<>(Arrays.asList("Java", "Python", "C++"));
        System.out.println(set4);  // [Java, C++, Python]
        System.out.println(set4.add("Python")); //false
    }
}
