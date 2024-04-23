import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CollectionsDemo2 {

    public static void main(String[] args) {

        ArrayList<String> al = new ArrayList<>();
        al.add("Z");
        al.add("B");
        al.add("A");

        System.out.println("Before Sorting : " + al); // [Z, B, A]

        Collections.sort(al, new MyComparator());

        System.out.println("After Sorting : " + al); // [A, B, Z]
    }
}

class MyComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        // Implement custom comparison logic here
        return o1.compareTo(o2); // Default behavior: sort strings lexicographically
    }
}
