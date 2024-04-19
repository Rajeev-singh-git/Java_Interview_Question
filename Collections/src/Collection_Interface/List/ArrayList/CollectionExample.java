package ArrayList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.RandomAccess;

public class CollectionExample {
    public static void main(String[] args) {
        ArrayList<Object> a1 = new ArrayList<>();
        LinkedList<Object> a2 = new LinkedList<>();

        // Check if ArrayList implements Serializable
        System.out.println(a1 instanceof Serializable); // true

        // Check if LinkedList implements Cloneable
        System.out.println(a2 instanceof Cloneable); // true

        // Check if ArrayList implements RandomAccess
        System.out.println(a1 instanceof RandomAccess); // true

        // Check if LinkedList implements RandomAccess
        System.out.println(a2 instanceof RandomAccess); // false
    }
}

