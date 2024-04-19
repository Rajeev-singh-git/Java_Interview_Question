package Vector;

import java.util.*;

public class VectorDemo {

    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>(); // Create a new Vector object

        System.out.println(v.capacity()); //  10 (default is 10)

        // Add integers from 1 to 10 to the Vector
        for (int i = 1; i <= 11; i++) {
            v.addElement(i); // Add each integer to the Vector
        }

        System.out.println(v.capacity()); // 20


        System.out.println(v); //[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
    }
}
