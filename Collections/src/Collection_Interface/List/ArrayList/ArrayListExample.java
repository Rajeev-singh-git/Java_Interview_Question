package ArrayList;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {

    public static void main(String [] args){

        List<Object> arrayList = new ArrayList<>();

        // 1. Adding elements to the ArrayList (Resizable array)
        arrayList.add("Apple"); // String
        arrayList.add(123);     // Integer (Autoboxing)
        arrayList.add(3.14);    // Double (Autoboxing)
        arrayList.add(true);    // Boolean (Autoboxing)
        arrayList.add(null);    // Null element is allowed

        // 2. Duplicate objects are allowed
        arrayList.add("Apple");

        // 3. Insertion order preserved
        System.out.println("ArrayList elements: " + arrayList);

        // 4. Heterogeneous objects are allowed
        System.out.println("Size of ArrayList: " + arrayList.size());

        // 5. Accessing elements by index
        System.out.println("Element at index 2: " + arrayList.get(2));

        // Creating ArrayList with specific initial capacity
        List<String> fruits = new ArrayList<>(5); // Initial capacity of 5

        // Adding elements to the ArrayList with specified initial capacity
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");

        // Creating ArrayList from another Collection
        List<String> moreFruits = new ArrayList<>(fruits); // Creating from Collection

        // Adding elements to the new ArrayList
        moreFruits.add("Pineapple");
        moreFruits.add("Grapes");

        // Displaying the new ArrayList
        System.out.println("More Fruits: " + moreFruits);


    }
}


/*  Output
ArrayList elements: [Apple, 123, 3.14, true, null, Apple]
Size of ArrayList: 6
Element at index 2: 3.14
More Fruits: [Banana, Orange, Mango, Pineapple, Grapes]
*/