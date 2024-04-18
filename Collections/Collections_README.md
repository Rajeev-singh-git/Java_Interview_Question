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

[Collection Interface]()

![c5](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/b6d65cab-7c75-48b1-9232-c093f8b9fea2)


[Map Interface]()

![c6](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/2feddfb1-71d6-490e-bbf8-967029dbe129)
