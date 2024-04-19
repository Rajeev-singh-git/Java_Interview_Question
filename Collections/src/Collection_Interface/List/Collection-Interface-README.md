# Collection Interface

# Collection Interface

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/1881e970-981a-4220-9c21-9a38d67a734c/Untitled.png)

If we want to represent a group of individual objects as a single entity then we
should go for Collection interface. This interface defines the most common general methods which can be applicable for any Collection object.

List of methods present in Collection interface.

```java
boolean add(Object o); - Add object to collection.
boolean addAll(Collection c); - Add all from collection.
boolean remove(Object o); - Remove object.
boolean removeAll(Collection c); - Remove all from collection.
boolean retainAll(Collection c); - Retain common elements.
void clear(); - Clear collection.
boolean contains(Object o); - Check if contains.
boolean containsAll(Collection c); - Check if contains all.
boolean isEmpty(); - Check if empty.
int size(); - Get size.
Object[] toArray(); - Convert to array.
Iterator iterator(); - Get iterator.
```

Code Example :

```java
package ArrayList;

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

```

Output :

```java

[Orange, Apple, Mango, Banana, Grapes]
After adding another list : [Orange, Apple, Mango, Banana, Grapes, Guava, Pineapple, Grapes, Apple, Mango]
After removing Grapes :[Orange, Apple, Mango, Banana, Guava, Pineapple, Grapes, Apple, Mango]
After removing another list : [Orange, Banana]
Contains 'Banana': true
Contains all from anotherList: false
Check if list is empty: false
Size of the list: 2
Array representation of the list: [Orange, Banana]
Elements in the list using Iterator:
Orange
Banana

Process finished with exit code 0

```

There is no concrete class which implements Collection interface directly.

# List Interface

The **`List`** interface in Java extends the **`Collection`** interface and represents an ordered collection of elements where duplicates are allowed and insertion order is preserved. Key features of the **`List`** interface include:

- **Indexed Access**: Elements in a **`List`** can be accessed and manipulated based on their indices.
- **Key Methods**

```java
1.) boolean add(int index, Object o):
Inserts the specified element at the specified position in the list, shifting existing elements to the right.
2.) boolean addAll(int index, Collection c):
Inserts all elements of the specified collection into the list at the specified position, shifting existing elements to the right as needed.
3.) Object get(int index):
Returns the element at the specified position in the list.
4.) Object remove(int index):
Removes the element at the specified position in the list, shifting subsequent elements to the left.
5.) Object set(int index, Object new):
Replaces the element at the specified position with the specified new element, returning the previous element at that position.
6.) int indexOf(Object o):
Returns the index of the first occurrence of the specified element in the list, or -1 if not found.
7.) int lastIndexOf(Object o):
Returns the index of the last occurrence of the specified element in the list, or -1 if not found.
8.) ListIterator listIterator():
Returns a ListIterator over the elements in the list, starting at the beginning.
```

Code Example :→

```java
import java.util.ArrayList;
import java.util.ListIterator;

public class ListInterfaceExample {

    public static void main(String [] args){

        ArrayList<String> fruitList = new ArrayList<>();

        //1. boolean add(int index, Object o):
        fruitList.add(0, "Apple");
        fruitList.add(1, "Orange");
        fruitList.add(2,"Banana");

        System.out.println("Fruit List are : " +fruitList);

        ArrayList<String> moreFruits = new ArrayList<>();

        //2. boolean addAll(int index, Collection c):
        moreFruits.add("Grapes");
        moreFruits.add("Mango");

        fruitList.addAll(3, moreFruits);

        System.out.println("Updated Fruit List : " +fruitList);

        //3.Object get(int index):
        System.out.println("Fruit at index 2 is : "+fruitList.get(2));

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

```

Output :→

```java
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
```

## ArrayList

1. The underlying data structure is resizable array (or) growable array.
2. Duplicate objects are allowed.
3. Insertion order is preserved.
4. Heterogeneous objects are allowed.(except TreeSet , TreeMap every where
   heterogeneous objects are allowed).
5. Null insertion is possible.

### Constructors

```java
1. ArrayList a = new ArrayList();
   - Creates an empty `ArrayList` with a default initial capacity of `10`. 
     If the `ArrayList` reaches its maximum capacity, a new `ArrayList` 
      object is created with a new capacity calculated as 
                
                 New Capacity = (current capacity * 3/2) + 1.

2. ArrayList a = new ArrayList(int initialCapacity);` 
   - Creates an empty `ArrayList` with the specified initial capacity.

3. ArrayList a=new ArrayList(collection c); 
  
   - Creates an `ArrayList` containing the elements of the specified collection `c`.
    This constructor allows easy conversion or interconversion between different collection objects.

```

Code

```java
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

```

Output

```java
ArrayList elements: [Apple, 123, 3.14, true, null, Apple]
Size of ArrayList: 6
Element at index 2: 3.14
More Fruits: [Banana, Orange, Mango, Pineapple, Grapes]

```

Serialization:
Collections are commonly used to store and transfer objects between different application tiers. Many collection classes support serialization, which allows objects to be converted into a stream of bytes for storage or transmission using the `Serializable` interface.

Cloneable Interface:
Cloneable interface is not widely used in collection classes. Unlike serialization, which is commonly supported, not all collection classes implement `Cloneable` for cloning objects.

-RandomAccess Interface:**
`ArrayList` and `Vector` implement the `RandomAccess` interface from the `java.util` package. This interface acts as a marker to indicate that these classes provide efficient random access to elements by index.

`ArrayList` is particularly well-suited for retrieval operations due to its constant-time access (O(1) complexity) for accessing elements by index.

However, not all collection classes support `RandomAccess`; for example, `LinkedList` does not implement this interface and may not be optimal for scenarios requiring frequent random access.

Code

```java
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
```

### Differences Between ArrayList and Vector

| Feature | Feature | Vector |
| --- | --- | --- |
| Synchronization | No method is synchronized. | Every method is synchronized.     |
| Thread Safety | Not thread-safe; multiple threads can operate on ArrayList simultaneously. | Thread-safe; only one thread is allowed to operate on Vector at a time. |
| Performance | Relatively high performance because threads are not required to wait for synchronization. | Relatively lower performance because threads are required to wait for synchronization. |
| Legacy | Not legacy, introduced in 1.2V. | Legacy, introduced in 1.0 V. |

### Getting Synchronized Version of ArrayList

The `Collections` class provides methods to obtain synchronized versions of `List`, `Set`, and `Map` objects:

- `public static List synchronizedList(List l);`
- `public static Set synchronizedSet(Set s);`
- `public static Map synchronizedMap(Map m);`

```java
 List<String> list = new ArrayList<>();
 List<String> synchronizedList = Collections.synchronizedList(list);
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/dbb9744f-aa46-4cb2-b3f5-f5f9b4b8beb2/Untitled.png)

## LinkedList

1. The underlying data structure is double LinkedList
2. If our frequent operation is insertion (or) deletion in the middle then LinkedList is the best choice.
3. If our frequent operation is retrieval operation then LinkedList is worst choice.
4. Duplicate objects are allowed.
5. Insertion order is preserved.
6. Heterogeneous objects are allowed.
7. Null insertion is possible.
8. Implements Serializable and Cloneable interfaces but not RandomAccess.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/47dc3151-bb7f-4a87-a5d2-ca7f1479be66/Untitled.png)

Usually we can use LinkedList to implement Stacks and Queues.
To provide support for this requirement LinkedList class defines the following 6 specific methods

```java
1. void addFirst(Object o);
2. void addLast(Object o);
3. Object getFirst();
4. Object getLast();
5. Object removeFirst();
6. Object removeLast()
```

### Constructors

```java
1. LinkedList l=new LinkedList(); 
Creates an empty LinkedList object.

2. LinkedList l=new LinkedList(Collection c); 
To create an equivalent LinkedList object for the given collection. 
```

LinkedList Example

```java

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<Object> l = new LinkedList<>();

        // Adding elements to LinkedList
        l.add("ashok");
        l.add(30);
        l.add(null);
        l.add("ashok");
        System.out.println(l); // [ashok, 30, null, ashok]

        // Modifying elements in LinkedList
        l.set(0, "software");
        System.out.println(l); // [software, 30, null, ashok]

        l.set(0, "venky");
        System.out.println(l); // [venky, 30, null, ashok]

        // Removing elements from LinkedList
        l.removeLast();
        System.out.println(l); // [venky, 30, null]

        // Adding element at the beginning of LinkedList
        l.addFirst("vvv");
        System.out.println(l); // [vvv, venky, 30, null]
    }
}
```

## Vector

1. The underlying data structure is resizable array (or) growable array.
2. Duplicate objects are allowed.
3. Insertion order is preserved.
4. Heterogeneous objects are allowed.
5. Null insertion is possible.
6. Implements Serializable, Cloneable and RandomAccess interfaces.

Every method present in Vector is synchronized and hence Vector is Thread safe.

### Vector Specific Methods :

To add Object :

```java
1. add(Object o);-----Collection
2. add(int index,Object o);-----List 
3. addElement(Object o);-----Vector
```

To Remove Elements :

```java
1. remove(Object o);--------Collection 
2. remove(int index);--------------List 
3. removeElement(Object o);----Vector 
4. removeElementAt(int index);-----Vector 
5. removeAllElements();-----Vector 
6. clear();-------Collection
```

To Get Objects:

```java
1. Object get(int index);---------------List 
2. Object elementAt(int index);-----Vector 
3. Object firstElement();--------------Vector 
4. Object lastElement();---------------Vector
```

Other Methods :

```java
1. Int size();//How many objects are added 
2. Int capacity();//Total capacity 
3. Enumeration elements(); 
```

### Constructors :

```java
1. Vector v=new Vector(); 
o Creates an empty Vector object with default initial capacity 10.
o Once Vector reaches its maximum capacity then a new Vector object will be 
created with double capacity. That is "newcapacity=currentcapacity*2".
2. Vector v=new Vector(int initialcapacity);
3. Vector v=new Vector(int initialcapacity, int incrementalcapacity);
4. Vector v=new Vector(Collection c);
```

Code Example :→

```java

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

```

### Stack

1. It  is the child class of Vector.
2. Use **`Stack`** when Last-In-First-Out (LIFO) order of elements is required.

Methods :

```java
1. Object push(Object o);
Inserts an element o into the stack.
2. Object pop();
Removes and returns the top element from the stack.
3. Object peek();
Returns the top element of the stack without removing it.
4. boolean empty();
Returns true if the stack is empty; otherwise, returns false.
5. int search(Object o);
Returns the 1-based position of the element o in the stack (topmost element is position 1), or -1 if the element is not found.
```

### Stack Constructor

```java
// contains only one constructor
Stack s = new Stack();
```

Methods

```java
1.) Object push(Object o);
Inserts an element o into the stack.
2.) Object pop();
Removes and returns the top element from the stack.
3.) Object peek();
Returns the top element of the stack without removing it.
4.) boolean empty();
Returns true if the stack is empty; otherwise, returns false.
5.) int search(Object o);
Returns the 1-based position of the element o in the stack (topmost element is position 1), or -1 if the element is not found.
```

Code Vector Demo

```java

import java.util.*;

public class StackDemo {
    public static void main(String[] args) {

        Stack<String> s = new Stack<>();

        // Push elements onto the stack
        s.push("A");
        s.push("B");
        s.push("C");
        System.out.println(s); // Output: [A, B, C]

        // Pop the top element from the stack
        System.out.println(s.pop()); // Output: C
        System.out.println(s); // Output: [A, B]

        // Peek at the top element of the stack
        System.out.println(s.peek()); // Output: B

        // Search for elements in the stack
        System.out.println(s.search("A")); // Output: 2 (position of "A" in the stack)
        System.out.println(s.search("Z")); // Output: -1 (element not found)

        // Check if the stack is empty
        System.out.println(s.empty()); // Output: false (stack is not empty)
    }
}
```

# The 3 cursor of Java

Cursors in Java are used to iterate over elements in a collection sequentially. There are three types of cursors available:

1. Enumeration
2. Iterator
3. ListIterator

## Enumeration

- Used with legacy collection classes like **`Vector` to get objects one by one**.
- Created using the **`elements()`** method of the collection.

**`Enumeration`** interface defines following two methods

```java
public boolean hasMoreElements(); - Returns true if the enumeration contains more elements.
public Object nextElement(); - Returns the next element in the enumeration.
```

Code Example

```java

public class EnumerationDemo {

    public static  void main(String []args){

        Vector<Integer> IntVector = new Vector<>();

        for(int i=0;i<=10;i++){
            IntVector.addElement(i);
        }

        System.out.println(IntVector); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        Enumeration<Integer> e =  IntVector.elements();

        while (e.hasMoreElements())
        {
            Integer i = e.nextElement();
            if(i%2==0)
                System.out.println(i);

            /*
            0
            2
            4
            6
            8
            10
             */
        }

        System.out.println(IntVector); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    }
}

```

### Limitation of Enumeration

1. We can apply Enumeration concept only for legacy classes and it is not a universal cursor.
2. By using Enumeration we can get only read access and we can't perform remove operations.

## Iterator

1. We can use Iterator to get objects one by one from any collection object.
2. We can apply Iterator concept for any collection object and it is a universal cursor.
3. While iterating the objects by Iterator we can perform both read and remove operations.

We can get Iterator object by using iterator() method of Collection interface.

```java
Collection<Integer> c = new ArrayList<>();
Iterator<Integer> itr = c.iterator();
```

### Iterator Interface defines the following 3 methods.

```java
public boolean hasNext(); - Returns true if the iteration has more elements.
public Object next(); - Returns the next element in the iteration.
public void remove(); - Removes the last element returned by next() from the underlying collection (optional operation).
```

Code Example

```java
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorDemo {

    public static void main(String [] args){

        ArrayList<Integer> al = new ArrayList<>();

        for(int i=0;i<=10;i++){
            al.add(i);
        }

        System.out.println(al); //[0,1,2,3,4,5,6,7,8,9,10]

        Iterator<Integer> itr = al.iterator();

        while(itr.hasNext())
        {
            Integer i = itr.next();
            if(i%2==0)
                System.out.println(i);
            else
                itr.remove();

        }

        System.out.println(al); //[0, 2, 4, 6, 8, 10]

    }
}

```

### Limitation of Iterator

1. **Single Directional Cursor:**
   Both **`Enumeration`** and **`Iterator`** are single-direction cursors, allowing movement only in the forward direction.
2. **Limited Operations:`Iterator`** supports only read and remove operations during iteration. Operations like replacement and addition of new objects are not directly supported.

## ListIterator

1.  `**ListIterator**` is the child interface of Iterator.
2. **Bi-Directional Cursor:**  **`ListIterator`** allows traversal in both forward and backward directions within a list.
3. **Extended Operations :** In addition to read and remove operations, **`ListIterator`** supports element replacement (**`set`**) and insertion (**`add`**) operations during traversal.

### **Using ListIterator**

Use the **`listIterator()`** method of the **`List`** interface to obtain a **`ListIterator`** object.

```java
List<String> list = new ArrayList<>();
ListIterator<String> itr = list.listIterator();

```

### ListIterator Mehods

The **`ListIterator`** interface defines the following methods:

```java

1.) public boolean hasNext();
 - Returns true if there is another element in the forward direction.
2.) public Object next(); 
 - Returns the next element in the forward direction.
3.) public int nextIndex();
  - Returns the index of the element that would be returned by the next call to next().
4.) public boolean hasPrevious();
  - Returns true if there is another element in the backward direction.
5.) public Object previous();
  - Returns the previous element in the backward direction.
6.) public int previousIndex(); 
 - Returns the index of the element that would be returned by the next call to previous().
7.) public void remove(); 
 - Removes the last element returned by next() or previous() from the list.
8.) public void set(Object newElement);
  - Replaces the last element returned by next() or previous() with the specified newElement.
9.) public void add(Object newElement); 
 - Inserts the specified newElement into the list at the current position.
```

Code Example

```java
import java.util.LinkedList;
import java.util.ListIterator;

public class ListIteratorDemo {
    public static void main(String [] args){

        LinkedList<String> l = new LinkedList<>();
        l.add("BalaKrishna");
        l.add("Ravi");
        l.add("Shyam");
        l.add("Krishna");

        System.out.println(l);

        ListIterator<String> itr = l.listIterator();
        while (itr.hasNext()){
            String s = itr.next(); //[BalaKrishna, Ravi, Shyam, Krishna]
            if(s.equals("Ravi")){
                itr.remove();
            }
        }
        System.out.println(l); //[BalaKrishna, Shyam, Krishna]

        ListIterator<String> itr2 = l.listIterator();
        while (itr2.hasNext()){
            String s = itr2.next();   
            if(s.equals("BalaKrishna")){
                itr2.set("AlmightyKrishna");
            }
        }
        System.out.println(l);  // [[AlmightyKrishna, Shyam, Krishna]

        ListIterator<String> itr3 = l.listIterator();
        while (itr3.hasNext()){
            String s = itr3.next();
            if(s.equals("Shyam")){
                itr3.add("RadhaShyam");
            }
        }
        System.out.println(l);  //[AlmightyKrishna, Shyam, RadhaShyam, Krishna]

    }

}

```

## Comparison table summarizing the properties of **`Enumeration`**, **`Iterator`**, and **`ListIterator`** in Java:

| Property | Enumeration | Iterator | ListIterator |
| --- | --- | --- | --- |
| Legacy? | Yes | No | No |
| Applicable for | Only legacy classes | Any collection object | Only list objects |
| Movement | Single direction cursor (forward) | Single direction cursor (forward) | Bi-directional |
| How to get it? | By using elements() method | By using iterator() method | By using listIterator() method |
| Accessibility | Only read | Both read and remove | Read/remove/replace/add |
| Methods | hasMoreElements(), nextElement() | hasNext(), next(), remove() | 9 methods including hasPrevious(), previous(), remove(), add(), etc. |
- **Enumeration** is a legacy interface applicable only to legacy classes, providing a single-direction cursor with read-only access.
- **Iterator** is applicable to any collection object, offering a single-direction cursor with read and remove operations.
- **ListIterator** is specific to list objects, providing a bi-directional cursor with read, remove, replace, and add operations.

# Set Interface

1. It is the child interface of Collection.
2. If we want to represent a group of individual objects as a single entity where duplicates are not allow and insertion order is not preserved then we should go for Set interface.
3. Set interface does not contain any new method we have to use only Collection interface methods.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/d67bc415-3004-4e06-bef0-d41881f50a1b/Untitled.png)

## HashSet

- **Underlying Data Structure:** Hashtable
- **Insertion Order:** Not preserved; based on hash code of objects
- **Duplicates:** Not allowed; adding duplicate objects returns **`false`** without errors
- **Heterogeneous Objects:** Allowed
- **Null Insertion:** Possible (only once)
- **Interfaces Implemented:** Serializable, Cloneable (but not RandomAccess)
- **Best Suitable For:** Search operations due to fast retrieval

### Constructors

```java
1. HashSet()
	Creates an empty HashSet object with default initial capacity of 
	16 and default load factor of 0.75 (fill ratio).
2. HashSet(int initialCapacity)
	Creates an empty HashSet object with the specified initial capacity
	and default load factor of 0.75.
3. HashSet(int initialCapacity, float loadFactor)
	Creates an empty HashSet object with the specified initial capacity
	and load factor (fill ratio).
4. HashSet(Collection<? extends E> c)
  Creates a HashSet containing the elements of the specified 
  collection c.
```

- The load factor (or fill ratio) determines when the **`HashSet`** should resize and rehash its elements internally.
- The default load factor for a **`HashSet`** is 0.75, which means the **`HashSet`** will be resized when it is 75% full.

Code Example

```java
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
```

## LinkedHashSet

1. It is the child class of HashSet.
2. **`LinkedHashSet`** is exactly same as HashSet except the following differences.

| Property | HashSet | LinkedHashSet |
| --- | --- | --- |
| Underlying Data Structure | Hashtable | Combination of Hashtable and LinkedList |
| Insertion Order | Not preserved | Preserved |
| Introduced Version | Java 1.2 | Java 1.4 |

Code

```java
public class LinkedHashSetDemo {
    public static void main(String[] args) {
        
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("Java");
        set.add("Python");
        set.add("C++");
        set.add("Python");
        
        System.out.println(set);  //[Java, Python, C++]

    }
}
```

Note: **`LinkedHashSet`** and `**LinkedHashMap**` commonly used for implementing "cache applications" where insertion order must be preserved and duplicates are not allowed.

## SortedSet

- **Parent Interface:** **`Set`**

### **Purpose:**

- Represents a collection of unique objects where elements are maintained in a sorted order.

### **Key Features:**

1. **Sorting Order:**
    - Elements are stored according to a specific sorting order, which can be either default natural sorting or a customized sorting order defined by a **`Comparator`**.

### Methods

```java
1. Object first():
   Returns the first (lowest) element in the sorted set.
2. Object last():
   Returns the last (highest) element in the sorted set.
3. SortedSet headSet(Object obj):
   Returns a subset of the sorted set containing elements less than the specified obj.
4. SortedSet tailSet(Object obj):
   Returns a subset of the sorted set containing elements greater than or equal to the specified obj.
5. SortedSet subSet(Object o1, Object o2):
   Returns a subset of the sorted set containing elements that are greater than or equal to o1 and strictly less than o2.
6. Comparator comparator():
   Returns the Comparator object used to define the sorting order, or null if using the default natural sorting order.
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/1486743f-ca7d-40ce-a1af-218a8a8d350a/Untitled.png)

Code

```java
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

```

## TreeSet

- **Underlying Data Structure:** Balanced Tree (Red-Black Tree)
- **Duplicates:** Not allowed
- **Insertion Order:** Not preserved; based on natural or customized sorting order
- **Heterogeneous Objects:** Not allowed; may result in **`ClassCastException`**
- **Null Insertion:** Possible (only once)

### Constructors:

```java
1. TreeSet t = new TreeSet()
   Creates an empty TreeSet object where elements are inserted 
   according to the default natural sorting order.
2. TreeSet t=new TreeSet(Comparator c)
	 Creates an empty TreeSet object where elements are inserted
	 according to a customized sorting order specified by the provided
	 Comparator object.
3. TreeSet t=new TreeSet(SortedSet s); 
	 Creates a TreeSet object initialized with the elements from the
	 specified sorted set s.
4. TreeSet t=new TreeSet(Collection c);
	 Creates a TreeSet object initialized with the elements from the
	 specified collection c.
```

Code

```java
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

```

 Exception in thread "main" java.lang.ClassCastException: java.lang.StringBuffer cannot be cast to java.lang.Comparable
 If we are depending on default natural sorting order compulsory the objects should be homogeneous and Comparable otherwise we will get ClassCastException.
 An object is said to be Comparable if and only if the corresponding class
implements Comparable interface.
 String class and all wrapper classes implements Comparable interface but
StringBuffer class doesn't implement Comparable interface hence in the above
program we are getting ClassCastException.