import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CollectionFrameworkExample {

    public static void main(String [] args) {

        // Create an ArrayList to work with
        ArrayList<String> list = new ArrayList<>();

        // boolean add(Object o); - Add object to collection.
        list.add("Orange");
        list.add("Apple");
        list.add("Mango");
        list.add("Banana");
        list.add("Grapes");

        System.out.println(list);  // [Orange, Apple, Mango, Banana, Grapes]

        // Creating another ArrayList
        ArrayList<String> anotherList = new ArrayList<>();
        anotherList.add("Guava");
        anotherList.add("Pineapple");
        anotherList.add("Grapes");
        anotherList.add("Apple");
        anotherList.add("Mango");

        //boolean addAll(Collection : Add all from collection
        list.addAll(anotherList);

        System.out.println("After adding another list : " + list); // After adding another list : [Orange, Apple, Mango, Banana, Grapes, Guava, Pineapple, Grapes, Apple, Mango]


        //boolean remove(Object o); - Remove object.
        list.remove("Grapes");

        System.out.println("After removing Grapes :" + list);  //After removing Orange :[Apple, Mango, Banana, Grapes, Guava, Pineapple, Grapes, Apple, Mango]

        //boolean removeAll(Collection c); - Remove all from collection.
        list.removeAll(anotherList);

        System.out.println("After removing another list : " + list);  // After removing another list : [Orange, Banana]


        //boolean contains(Object o); - Check if contains.
        System.out.println("Contains 'Banana': " + list.contains("Banana"));  // Contains 'Banana': true

        //boolean containsAll(Collection c); - Check if contains all.
        System.out.println("Contains all from anotherList: " + list.containsAll(anotherList));  // Contains all from anotherList: false

        //boolean isEmpty(); - Check if empty.
        System.out.println("Check if list is empty: " + list.isEmpty());  // Check if list is empty: false

        //int size(): Get size
        System.out.println("Size of the list: " + list.size());  // Size of the list: 2

        //Object[] toArray(): Convert to array
        Object[] array = list.toArray();
        System.out.println("Array representation of the list: " + Arrays.toString(array));  //Array representation of the list: [Orange, Banana]

        // 12. Iterator iterator(): Get iterator
        Iterator<String> iterator = list.iterator();
        System.out.println("Elements in the list using Iterator:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        /*Elements in the list using Iterator:
            Orange
            Banana
        */

    }
}
