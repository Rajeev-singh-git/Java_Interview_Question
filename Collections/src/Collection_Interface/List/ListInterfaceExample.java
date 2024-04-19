import java.util.ArrayList;
import java.util.ListIterator;

public class ListInterfaceExample {

    public static void main(String [] args){


        ArrayList<String> fruitList = new ArrayList<>();

        //1. boolean add(int index, Object o):
        fruitList.add(0, "Apple");
        fruitList.add(1, "Orange");
        fruitList.add(2,"Banana");

        System.out.println("Fruit List are : " +fruitList);  // Fruit List are : [Apple, Orange, Banana]

        ArrayList<String> moreFruits = new ArrayList<>();

        //2. boolean addAll(int index, Collection c):
        moreFruits.add("Grapes");
        moreFruits.add("Mango");

        fruitList.addAll(3, moreFruits);

        System.out.println("Updated Fruit List : " +fruitList);  //Updated Fruit List : [Apple, Orange, Banana, Grapes, Mango]

        //3.Object get(int index):
        System.out.println("Fruit at index 2 is : "+fruitList.get(2));  //Fruit at index 4 is : Banana

        //4. Object set(int index, Object new): Replace an element at a specific index
        fruitList.set(1, "Pineapple");
        System.out.println("List after replacing at index 1: " + fruitList);

        //5. int indexOf(Object o): Get the index of a specific element
        int indexOrange = fruitList.indexOf("Orange");
        System.out.println("Index of orange is "+indexOrange);


        //6. int lastIndexOf(Object o): Get the last index of a specific element
        int lastIndexGrapes = fruitList.lastIndexOf("Grapes");
        System.out.println("Last index of 'Grapes': " + lastIndexGrapes);

        //7. Object remove(int index): Remove an element at a specific index
        String removedFruit = fruitList.remove(3);
        System.out.println("Removed fruit at index 3: " + removedFruit);
        System.out.println("List after removal: " + fruitList);

        //8. ListIterator listIterator(): Iterate over the list using ListIterator
        System.out.println("Iterating over the list using ListIterator:");
        ListIterator<String> iterator = fruitList.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}


/*
Output:->

Fruit List are : [Apple, Orange, Banana]
Updated Fruit List : [Apple, Orange, Banana, Grapes, Mango]
Fruit at index 4 is : Banana
List after replacing at index 1: [Apple, Pineapple, Banana, Grapes, Mango]
Index of orange is -1
Last index of 'Grapes': 3
Removed fruit at index 3: Grapes
List after removal: [Apple, Pineapple, Banana, Mango]
Iterating over the list using ListIterator:
Apple
Pineapple
Banana
Mango
*/