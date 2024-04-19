import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetExample {
    public static void main(String[] args) {
        SortedSet<Integer> sortedSet = new TreeSet<>(Arrays.asList(3, 1, 2, 5, 4));

        // Displaying elements in sorted order
        System.out.println("SortedSet: " + sortedSet); // Output: SortedSet: [1, 2, 3, 4, 5]

        // Accessing first and last elements
        System.out.println("First Element: " + sortedSet.first()); // Output: First Element: 1
        System.out.println("Last Element: " + sortedSet.last());   // Output: Last Element: 5

        // Creating subsets based on elements
        SortedSet<Integer> headSet = sortedSet.headSet(3); // Elements < 3
        SortedSet<Integer> tailSet = sortedSet.tailSet(3); // Elements >= 3
        SortedSet<Integer> subSet = sortedSet.subSet(2, 5); // Elements >= 2 and < 5

        System.out.println("HeadSet: " + headSet); // Output: HeadSet: [1, 2]
        System.out.println("TailSet: " + tailSet); // Output: TailSet: [3, 4, 5]
        System.out.println("SubSet: " + subSet);   // Output: SubSet: [2, 3, 4]
    }
}
