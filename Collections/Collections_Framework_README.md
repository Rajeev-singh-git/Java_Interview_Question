# Introduction

# Arrays in Java

- An array is an indexed collection of a fixed number of homogeneous data elements, all of the same data type.
- Arrays in Java represent a group of elements of the same data type.
- Main advantages of using arrays is that they allow us to represent a large number of elements using a single variable. This improves the readability of the code.

## Limitation of Object[ ] Array

1. Arrays are fixed in size, meaning once an array is created, its size cannot be increased or decreased based on requirements. Therefore, when using arrays, it is necessary to know the size in advance, which may not always be feasible.
2. Arrays can only hold homogeneous (same type) data elements.

   For instance, in the following code:


```java
  Student[] s = new Student[10000];
   s[0] = new Student(); // valid
   s[1] = new Customer(); // invalid (compile-time error)
```

The attempt to assign a **`Customer`** object to an element of **`Student[]`** array results in a compile-time error because **`Customer`** is not a subclass of **`Student`**.

1. To overcome the restriction of holding only homogeneous data, we can use an array of type **`Object[]`**:

```java
Object[] o = new Object[10000];
o[0] = new Student();
o[1] = new Customer();

```

Here, **`Object[]`** can store any type of object since every class in Java is a subclass of **`Object`**

4.  Arrays in Java are not implemented based on specific data structures, which means there are no built-in methods tailored to array manipulation. For each specific requirement, custom code needs to be written explicitly.

To overcome the above limitations we should go for collections concept.

## Why Collections?

1. Collections  are dynamic and growable in nature, allowing us to increase or decrease their size based on requirements. This flexibility in size management is advantageous from a memory perspective.
2. Collections can hold both homogeneous (same type) and heterogeneous (different types) objects.
3. Every collection class in Java is implemented based on a specific standard data structure (e.g., ArrayList is based on resizable arrays, LinkedList is based on linked nodes). This design ensures that each collection class comes with a rich set of ready-made methods tailored to the underlying data structure. As a programmer, this means we can leverage these built-in methods directly for various operations without having to implement the functionality ourselves.

### Difference between Array and Collections

| Arrays | Collections |
| --- | --- |
|  1.) Arrays are fixed in size.  | 1.) Collections are growable in nature. |
|  2.) Arrays are not recommended for memory efficiency.  | 2.) Collections are highly recommended for memory efficiency. |
| 3.) Arrays are recommended for better performance. |  3.) Collections are not recommended for optimal performance. |
| 4.) Arrays can hold only homogeneous data type elements. | 4.) Collections can hold both homogeneous and heterogeneous elements. |
| 5.) Arrays do not have an underlying data structure, and hence lack ready-made method support. |  5.) Every collection class is implemented based on some standard data structure, providing ready-made method support. | |
|  6.) Arrays can hold both primitive and object types |  6.) Collections can only hold objects but not primitives. |

# Collection

If we want to represent a group of objects as single entity then we should go for
collections. When we need to work with multiple objects together, collections provide a convenient way to manage and manipulate them as a unified whole.

## Collection Framework

It defines a set of classes and interfaces that allow us to work with collections of objects efficiently.

### 9 key Interfaces of Collection Framework

1. Collection
2. List
3. Set
4. SortedSet
5. NavigableSet
6. Queue
7. Map
8. SortedMap
9. NavigableMap

# Collection

1. Collections in Java represent a group of individual objects as a single entity.
2. The `Collection` interface is the `root interface` of the Collection Framework.
3. It defines common methods applicable to any collection object, such as adding, removing, and iterating over elements.
4. No concrete class directly implements the `Collection` interface; instead, specialized interfaces like `List`, `Set`, and `Queue` extend it.

### List Interface

1. `List` is a child interface of `Collection`.
2. Use the `List` interface in Java when you want to represent a group of individual objects as a single entity where ***`duplicates are allowed, and the insertion order of elements must be preserved`***.

![c1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/a62d7aa4-b963-49ab-bd18-1ac5dcb4fbbe)


## Set Interface

1. `Set` is a child interface of `Collection`.
2. Use the `Set` interface in Java when you want to represent a group of individual objects as a single entity ***`where duplicates are not allowed, and the insertion order of elements is not preserved`***.

![c2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/31767a76-1865-4d5d-bbdd-b7294571e608)


### SortedSet Interface

1. `SortedSet` is a child interface of `Set`.
2. Use the `SortedSet` interface in Java when you want to represent a group of unique objects ***`where duplicates are not allowed, and all objects are stored in a sorted order based on a natural or custom sorting criterion`***.

### NavigableSet Interface

1. `NavigableSet` is a child interface of `SortedSet`.
2. Use the `NavigableSet` interface in Java when you want to represent a sorted set of unique elements. It extends the `SortedSet` interface and provides additional methods for navigation purposes, such as finding elements based on their relationship to other elements (e.g., finding the closest lower or higher element).

## Queue  Interface

1. `Queue` is a child interface of `Collection`.
2. Use the `Queue` interface in Java when you want to represent a collection of individual objects that follow the FIFO (First-In-First-Out) order, suitable for scenarios where objects need to be processed in a specific sequence.

![c3](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/85eb55f6-fd52-4aa4-9fec-f8dc3271aa6e)


Note: All the above interfaces (Collection, List, Set, SortedSet, NavigableSet, and
Queue) meant for representing a group of individual objects.
If we want to represent a group of objects as key-value pairs then we should go for Map.

# Map Interface

1. `Map` is not a child interface of `Collection`.
2. Use the `Map` interface in Java when you want to represent a group of objects as key-value pairs. This allows efficient storage and retrieval of data using unique keys.
3. In a `Map`, duplicate keys are not allowed, but values can be duplicated. Each key in a map is associated with a unique value.

![c4](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/8b7558c6-4f3c-44dc-92b3-0539f4cac7d2)


## Sorted Map

1. `SortedMap` is a child interface of `Map`.
2. Use the `SortedMap` interface in Java when you want to represent a group of key-value pairs sorted according to the natural or custom ordering of keys. Sorting in a `SortedMap` is based on the keys, not on the values.

### NavigableMap Interface

1. `NavigableMap` is a child interface of `SortedMap` and extends it.
2. Use the `NavigableMap` interface, when you want to represent a sorted map of key-value pairs. It provides additional methods for navigation purposes, allowing efficient querying of entries based on keys.

# Collection vs Collections ?

- **Collection (Interface):**`Collection` is an interface in Java that represents a group of objects as a single entity. It defines a set of methods that can be implemented by collection classes to provide common functionality for managing collections of objects.
- **Collections (Utility Class):**`Collections` is a utility class in the `java.util` package that contains various static methods to operate on objects of type `Collection`. These utility methods include sorting, searching, synchronizing, and performing other operations on collections. It provides convenient tools for manipulating and working with collection objects.

In summary:

- `Collection` is an interface used to represent collections of objects.
- `Collections` is a utility class that provides static methods for working with `Collection` objects in Java.

In collection framework the following are legacy characters.

1. Enumeration(I)
2. Dictionary(AC)
3. Vector(C)
4. Stack(C)
5. Hashtable(C)
6. Properties(C

# In-Detail

[Collection Interface](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Collection_Interface/Collection-Interface-README.md)

![c5](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/b6d65cab-7c75-48b1-9232-c093f8b9fea2)


[Map Interface](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/Map/Map_README.md)

![c6](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/2feddfb1-71d6-490e-bbf8-967029dbe129)


# Collection Class :

The **`Collections`** class in Java provides utility methods for working with collection objects like sorting, searching, reversing etc.

## Sorting element of a List

Collections class defines the following methods to sort elements of a List.

`**public static void sort(List l);**`

- To sort the elements of List according to default natural sorting order in this case the elements should be homogeneous and comparable otherwise we will get ClassCastException.
- The List should not contain null otherwise we will get NullPointerException.

**`public static void sort(List l,Comparator c);`**

- To sort the elements of list according to customized sorting order.

[Code : Sort the element of list according to natural sorting order](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/CollectionDemo.java)



[Code : Sort the element of list according to customized sorting order](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/CollectionsDemo2.java)



## Searching the Elements of a List :

Collections class defines the following methods to search the elements of a List.

`**public static int binarySearch(List l,Object obj);**`

- If the List is sorted according to default natural sorting order then we have to use this method.

**`public static int binarySearch(List l,Object obj,Comparator c);`**

- If the List is sorted according to Comparator then we have to use this method.

[Code : Search Element of a List using BS for natural Sorting](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/CollectionsSearchDemo.java)

```java
import java.util.ArrayList;
import java.util.Collections;

public class CollectionsSearchDemo {

    public static void main(String [] args){

        ArrayList <String> al = new ArrayList<>();
        al.add("Z");
        al.add("A");
        al.add("M");
        al.add("K");
        al.add("a");
        System.out.println(al);   // [Z, A, M, K, A]
        Collections.sort(al);
        System.out.println(al);   // [A, A, K, M, Z]

        System.out.println(Collections.binarySearch(al,"Z")); // 4
        System.out.println(Collections.binarySearch(al,"J")); //-2
		}
}
```

![c1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/0a77e733-6627-48a5-8741-841b7815e00c)


[Code : Search Element of a List using BS for customized sorting](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Collections/src/CollectionSearchDemo2.java)

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CollectionSearchDemo2 {

    public static void main(String [] args){

        ArrayList <Integer> al = new ArrayList<>();
        al.add(15);
        al.add(0);
        al.add(20);
        al.add(10);
        al.add(5);
        System.out.println(al);   //
        Collections.sort(al, new MyComp());
        System.out.println(al);   //

        System.out.println(Collections.binarySearch(al, 10, new MyComp())); //
        System.out.println(Collections.binarySearch(al,13, new MyComp())); //

    }

}

class MyComp implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
}
```

![c2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/9e06732b-3f2d-40ff-bb26-14efc67fdc25)


Code : To reverse element of  a list

```java
import java.util.ArrayList;
import java.util.Collections;

public class CollectionsReverseDemo {

    public static void  main(String [] args){

        ArrayList <Integer> al = new ArrayList<>();

        al.add(6);
        al.add(12);
        al.add(15);
        al.add(30);
        al.add(18);
        al.add(21);

        System.out.println("Before Reverse : -> ");
        System.out.println(al);                     // [6, 12, 15, 30, 18, 21]
        Collections.reverse(al);
        System.out.println("After Reverse : -> ");
        System.out.println(al);                     // [21, 18, 30, 15, 12, 6]
    }
}

```

# Array Class

The **`Arrays`** class provides various utility methods for arrays.

Sorting the elements of an array:

- **`public static void sort(primitive[] p);`**
  Sorts the elements of a primitive array according to the default natural sorting order.
- **`public static void sort(Object[] o);`**
  Sorts the elements of an **`Object[]`** array according to the default natural sorting order. The objects in the array should be homogeneous and **`Comparable`**.
- **`public static void sort(Object[] o, Comparator c);`**
  Sorts the elements of an **`Object[]`** array according to a customized sorting order specified by the **`Comparator`**.

Note: **`Object[]`** arrays can be sorted either by default natural sorting order or a customized sorting order using a **`Comparator`**, whereas primitive arrays can only be sorted using the default natural sorting order.

Code : Sort Array

```java
package Array;

import java.util.Arrays;
import java.util.Comparator;

public class ArraySortDemo {

    public static void main(String[]args){

        int [] a = {10,5,20,11,6};

        System.out.println("Primitive Array before Sorting");

        for(int a1:a){
            System.out.println(a1);
        }

        Arrays.sort(a);

        System.out.println("Primitive Array after Sorting");

        for(int a1:a){
            System.out.println(a1);
        }

        String [] s = {"A", "Z", "B"};

        System.out.println("Object Array before Sorting");

        for(String s1:s){
            System.out.println(s1);
        }

        Arrays.sort(s);

        System.out.println("Object Array after Sorting");

        for(String s1:s){
            System.out.println(s1);
        }

        Arrays.sort(s, new MyCom());

        System.out.println("Object Array after Sorting by Comparator");

        for(String s1:s){
            System.out.println(s1);
        }

    }

}

class MyCom implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}

/* Output :->
Primitive Array before Sorting
10
5
20
11
6
Primitive Array after Sorting
5
6
10
11
20
Object Array before Sorting
A
Z
B
Object Array after Sorting
A
B
Z
Object Array after Sorting by Comparator
Z
B
A

*/
```

Searching the elements of an array:

The **`Arrays`** class provides the following methods to search for elements in an array:

1. **`public static int binarySearch(primitive[] p, primitive key);`**
   Performs a binary search on a primitive array **`p`** to locate the specified **`key`**.
2. **`public static int binarySearch(Object[] p, Object key);`**
   Performs a binary search on an **`Object[]`** array **`p`** to locate the specified **`key`**.
3. **`public static int binarySearch(Object[] p, Object key, Comparator c);`**
   Performs a binary search on an **`Object[]`** array **`p`** to locate the specified **`key`** using a custom **`Comparator`** **`c`**.

All rules of the **`Arrays`** class **`binarySearch()`** method are exactly the same as those of the **`Collections`** class **`binarySearch()`** method.

Code : Search the element of the Array

```java
package Array;

import java.util.Arrays;

public class ArraySearchDemo {

    public static void main(String[]args){

        int [] a = {10,5,20,11,6};
        Arrays.sort(a);
        System.out.println(Arrays.binarySearch(a,6));  //1
        System.out.println(Arrays.binarySearch(a,14)); //-5

        String [] s = {"A","Z","B"};
        Arrays.sort(s);

        System.out.println(Arrays.binarySearch(s,"Z")); //2
        System.out.println(Arrays.binarySearch(s,"S")); //-3

    }
}

```

Converting an array to a List:

The **`Arrays`** class defines the following method to view an array as a **`List`**:

```java
public static List asList(Object[] o);
```

Strictly speaking, this method does not create an independent **`List`** object; it allows you to view the array in a **`List`** form.

Changes made through the **`List`** reference will be reflected in the underlying array, and vice versa.

However, attempting to perform size-changing operations using the **`List`** reference will result in a runtime exception (**`UnsupportedOperationException`**).

Similarly, trying to insert heterogeneous objects into the **`List`** reference will result in a runtime exception (**`ArrayStoreException`**).

Code : To View Array in List Form

```java
package Array;

import java.util.Arrays;
import java.util.List;

public class ArrayAsListDemo {

    public static void main(String[] args){

        String [] s = {"A","Z","B"};
        List l = Arrays.asList(s);
        System.out.println(l);        // [A, Z, B]
        s[0]="K";
        System.out.println(l);        // [K, Z, B]
        l.set(1,"L");

        for(String s1: s){
            System.out.println(s1);
        }

        //l.add("ashok");//UnsupportedOperationException
        //l.remove(2);//UnsupportedOperationException
        //l.set(1,new Integer(10));//ArrayStoreException
    }
}

/*
Output : ->

[A, Z, B]
[K, Z, B]
K
L
B
 */
```
