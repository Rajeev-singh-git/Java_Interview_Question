import java.util.TreeSet;
public class TreeSetDemo {
    public static void main(String[] args) {
        // Create a TreeSet to store strings (elements will be sorted based on natural ordering)
        TreeSet<String> t = new TreeSet<>();

        // Add strings to the TreeSet
        t.add("A");
        t.add("a");
        t.add("B");
        t.add("Z");
        t.add("L");

        // TreeSet does not allow duplicates or null elements
        // Uncommenting the following lines will cause exceptions:
        // t.add(new Integer(10)); // ClassCastException (cannot add integer to set of strings)
        // t.add(null); // NullPointerException (cannot add null element to set)

        // Print the TreeSet (elements will be printed in sorted order)
        System.out.println(t); // Output: [A, B, L, Z, a]
    }
}
