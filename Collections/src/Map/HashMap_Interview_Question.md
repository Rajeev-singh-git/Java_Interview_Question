# Set

## Does set allow duplicates?

No

```java
public class App {

    public static void main(String [] args){

        HashSet<Integer> integersSet = new HashSet<>();
        integersSet.add(10);
        integersSet.add(20);
        integersSet.add(30);
        integersSet.add(40);

        System.out.println(integersSet);
    }

}
```

Output

```java
[20, 40, 10, 30]
```

Code

```java
public class App {

    public static void main(String [] args){

        HashSet<Integer> integersSet = new HashSet<>();

        System.out.println(integersSet.add(10)); //true
        System.out.println(integersSet.add(20)); //true
        System.out.println(integersSet.add(30)); //true
        System.out.println(integersSet.add(40)); //true

        System.out.println(integersSet);        //[20, 40, 10, 30]
        
        System.out.println(integersSet.add(40));  //false

        System.out.println(integersSet);       // [20, 40, 10, 30]
    }

}
```

### Important points ***

When we tried to add duplicates in set, it didn’t got added and when we tried to do sysout while adding it returned false.

- Initially, elements 10, 20, 30, and 40 are added to the HashSet using the **`add`** method. The method returns **`true`** for each addition, indicating that the elements were added successfully.
- When printing the HashSet (**`System.out.println(integersSet)`**), you can see that the elements are not necessarily in the order in which they were added. This is because HashSet does not guarantee any specific order of elements.
- Then, an attempt is made to add the element 40 again. Since 40 is already present in the set, the **`add`** method returns **`false`**.
- Printing the HashSet again shows that it remains unchanged, and the duplicate 40 was not added.

## I**s a Set Designed to Not Allow Duplicates?**

Person.java

```java
package HashMap;

public class Person {

    private String name;
    private int voterId;

    public Person(String name, int voterId) {
        this.name = name;
        this.voterId = voterId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", voterId=" + voterId +
                '}';
    }

}
```

App.java

```java
package HashMap;

import java.util.HashSet;

public class App {

    public static void main(String [] args){

        HashSet<Person> personHashSet = new HashSet<>();
        System.out.println(personHashSet.add(new Person("Rajeev",101)));
        personHashSet.add(new Person("Raj",102));
        personHashSet.add(new Person("Rahul",103));
        System.out.println(personHashSet.add(new Person("Rajeev",101)));

        System.out.println(personHashSet);

    }

}
```

Output

```java
true
true
[Person{name='Rahul', voterId=103}, Person{name='Rajeev', voterId=101}, Person{name='Rajeev', voterId=101}, Person{name='Raj', voterId=102}]
```

### Even though, we created a HashSet of  type Person, It allowed duplicate persons to be added  without returning **`false,`Why?**

It's essential to override the **`equals`** and **`hashCode`** methods in your custom class.

In your original **`Person`** class, since you didn't provide custom implementations for **`equals`** and **`hashCode`**, it uses the default implementations from the **`Object`** class. The default **`equals`** method checks for reference equality, and the default **`hashCode`** method generates hash codes based on the memory address of the object.

To achieve the desired behavior, you need to override these methods in your **`Person`** class. This allows the **`HashSet`** to correctly identify and handle equality based on the attributes of your **`Person`** objects.

If you don't override these methods, the default behavior might not match your expectations when it comes to comparing instances of your class for equality, leading to unexpected results like allowing duplicates in a **`HashSet`**.

### **Overriding `equals` and `hashCode` Methods in Person.java**

```java
@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return voterId == person.voterId && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, voterId);
    }
```

Now, the output will correctly reflect the behavior of the HashSet, not allowing duplicates based on the **`equals`** and **`hashCode`** implementation.

Updated Person.java

```java
package HashMap;

import java.util.Objects;

public class Person {

    private String name;
    private int voterId;

    public Person(String name, int voterId) {
        this.name = name;
        this.voterId = voterId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", voterId=" + voterId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return voterId == person.voterId && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, voterId);
    }
}
```

Now, Output

```java
true
false
[Person{name='Rajeev', voterId=101}, Person{name='Raj', voterId=102}, Person{name='Rahul', voterId=103}]
```

Duplicates are not allowed and SysOut return false when trying to add duplicates.

### Why is it necessary to override the **`hashCode`** and **`equals`** methods when creating a **`HashSet`** for a custom class like 'Person,' whereas it is not required for the built-in integer type?

In the case of **`Integer`**, the **`equals`** and **`hashCode`** methods are already overridden to compare the values of the integers.

However, when you use custom classes (e.g., your **`Person`** class), the default implementation of **`equals`** and **`hashCode`** in the **`Object`** class compares references, not the content of the objects. If you want to define equality based on the content (i.e., the values of fields) for your custom class, you need to override **`equals`** and **`hashCode`**.

Here's why overriding **`equals`** and **`hashCode`** is important for custom classes:

1. **Equality Definition:** You get to define what it means for two instances of your class to be equal. In your **`Person`** class, equality is based on the **`name`** and **`voterId`** fields.
2. **HashSet Behavior:** When using a **`HashSet`**, it relies on **`equals`** and **`hashCode`** to determine whether an object is already present in the set. Without proper overrides, it may not behave as expected.

In summary, for primitive types or classes where the default **`equals`** and **`hashCode`** behavior is sufficient (e.g., **`Integer`**), you don't need to override them. For custom classes where equality is based on the content of the objects, it's crucial to override **`equals`** and **`hashCode`**.

## Does `hashset` internally use `hashmap`?

Yes, the **`HashSet`** class in Java is implemented on top of a **`HashMap`**. Internally, a **`HashSet`** is backed by a **`HashMap`** instance where the elements of the set are stored as keys, and the associated values are a constant placeholder (often **`null`**).

Here's a brief explanation of how it works:

1. **Backing HashMap:** When you add an element to a **`HashSet`** using the **`add`** method, the **`HashSet`** internally calls the **`put`** method of the underlying **`HashMap`**. It adds the element as a key in the **`HashMap`** and associates it with a constant value (often **`null`**). The **`HashMap`** takes care of handling the uniqueness of keys, which ensures that the **`HashSet`** does not allow duplicate elements.
2. **No Duplicate Keys:** Since a **`HashMap`** does not allow duplicate keys, when you attempt to add an element to a **`HashSet`** that is already present, the **`put`** method in the **`HashMap`** will not add a new entry, preserving the uniqueness property of the **`Set`**.

```java
public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {

    // The backing HashMap
    private transient HashMap<E,Object> map;

    // Dummy value to associate with an Object in the backing HashMap
    private static final Object PRESENT = new Object();

    // Constructors, methods, etc.

    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    // Other methods...
}
```

### Equal Method

Commented equal method in [Person.java](http://Person.java) and wrote this code in App.java

```java
Person Rajeev = new Person("Rajeev", 105);
Person DuplicateRajeev = new Person("Rajeev",105);

System.out.println(Rajeev.equals(DuplicateRajeev));  //false

Integer i1 = 6;
Integer i2 = 6;

System.out.println(i1.equals(i2));   //true
```

The **`equals`** method of the **`Person`** class is not overridden, so it uses the default implementation from the **`Object`** class. The default implementation in the **`Object`** class compares references (memory locations), and since **`Rajeev`** and **`DuplicateRajeev`** are different objects with different memory locations, **`equals`** returns **`false`**.  Now, Rajeev and DuplicateRajeev both are pointing to different memory location in heap area.

In contrast, the **`equals`** method of the **`Integer`** class is overridden to compare the content (values).

If you want to achieve content-based comparison for your custom class (like **`Person`**), you need to override the **`equals`**  and method in the class and provide your own logic for equality based on the content of the objects.

Overwriting equals method in Person.java

```java
@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return voterId == person.voterId && Objects.equals(name, person.name);
    }
```

Now, Output of App.java

```java
Person Rajeev = new Person("Rajeev", 105);
Person DuplicateRajeev = new Person("Rajeev",105);

System.out.println(Rajeev.equals(DuplicateRajeev));  //true

Integer i1 = 6;
Integer i2 = 6;

System.out.println(i1.equals(i2));   //true
```

### HashCode

**The primary goal of the hashCode method in Java is to generate a unique integer value, called a hash code, that serves as a compact representation of an object's contents.** This hash code is used by various data structures and algorithms, primarily those that rely on hashing, to efficiently organize and locate objects.

**Here's a breakdown of its key purposes:**

1. **Efficient retrieval in hash-based collections:**
    - Collections like HashSet, HashMap, and Hashtable utilize hash codes for rapid object storage and retrieval.
    - When you try to add an object to these collections, its hash code is calculated and used to determine its placement within the collection's internal structure.
    - Later, when you search for the object, its hash code is again used to quickly narrow down its potential location, significantly speeding up the lookup process.
2. **Distributed caching and load balancing:**
    - Hash codes are crucial in distributed systems for efficiently distributing data across multiple servers or cache nodes.
    - By hashing objects to specific nodes, load can be balanced, and data can be quickly retrieved regardless of which server it resides on.
3. **Data integrity checks:**
    - Hash codes can be used to detect accidental changes or corruption in data.
    - By calculating and comparing hash codes before and after data manipulation, you can ensure that the data has remained consistent.
4. **Implementing algorithms like Bloom filters and consistent hashing:**
    - These algorithms rely heavily on hash codes to achieve their functionality, such as probabilistic data membership tests (Bloom filters) or efficient partitioning of data across multiple machines (consistent hashing).

```java
System.out.println(new String("Abhilash").hashCode()); // 1472285324
System.out.println(new String("Ramya").hashCode());  //78727046
```

Each object has its own distinct memory location in the heap, and the memory allocated for one object doesn't overlap with the memory allocated for another object. This separation ensures that each object has its own space and can hold its specific data.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/0cd220aa-f01c-4b44-bf9e-742e0d52f7b8/Untitled.png)

**While it's common to associate hash codes with object addresses, it's crucial to clarify that modern hash code implementations in Java typically don't directly use object addresses.** Here's a more accurate explanation:

**1. Object Addresses and Identity Hash Codes:**

- Every object in Java has a unique memory address assigned by the JVM (Java Virtual Machine).
- The default `hashCode` method inherited from the `Object` class might have used this address in older Java versions, leading to the misconception. However, this approach had limitations:
    - Address-based hash codes could change during object relocation in memory, affecting hash-based collections' behavior.
    - It didn't consider the object's actual content for equality checks.

**2. Content-Based Hash Codes:**

- Modern Java implementations generally use algorithms that generate hash codes based on the object's significant fields and values. This approach offers several advantages:
    - Produces more consistent hash codes that are less prone to changes due to memory management.
    - Better aligns with the logical equality of objects, as determined by the `equals` method.

### Can two different object in java can have same hashcode ?

Yes, while hash codes should ideally be unique for distinct objects, it's not always possible to guarantee absolute uniqueness.

```java
System.out.println(new String("FB").hashCode());   //2236
System.out.println(new String("Ea").hashCode());   //2236
```

If content of two object are same, even if they have distinct address in heap memory, hashcode will be same.

```java
System.out.println(new String("Rajeev").hashCode()); //-1854537221
System.out.println(new String("Rajeev").hashCode()); //-1854537221

Integer n1 = 101;
Integer n2 = 101;

System.out.println("HashCode of n1 :-> " + n1.hashCode() +
                            " HashCode of n2 :-> " +n2.hashCode());
//HashCode of n1 :-> 101 HashCode of n2 :-> 101
```

### But, for custom class Person, even though content is same but hashcode is not same, why?

```java
Person Abhi = new Person("Abhishek", 101);
Person AbhiDuplicate = new Person("Abhishek", 101);

System.out.println("HashCode of Abhi :-> " + Abhi.hashCode() +
     " HashCode of AbhiDuplicate :-> " + AbhiDuplicate.hashCode());
//HashCode of Abhi :-> 1349393271 HashCode of AbhiDuplicate :-> 1338668845
```

**The reason you're observing different hash codes for objects with seemingly identical content is that the `Person` class likely hasn't overridden the `hashCode` method (and potentially the `equals` method as well).** Here's a detailed explanation:

**1. Default `hashCode` Behavior:**

- When a class doesn't provide its own `hashCode` implementation, it inherits the default one from the `Object` class.
- The default implementation often relies on the object's memory address, leading to distinct hash codes for different objects, even if their content is the same.

Overriding  **`hashCode` method in Person.java**

```java
@Override
    public int hashCode() {
        return Objects.hash(name, voterId);
    }
```

Now, HashCode is same

```java
Person Abhi = new Person("Abhishek", 101);
Person AbhiDuplicate = new Person("Abhishek", 101);

System.out.println("HashCode of Abhi :-> " + Abhi.hashCode() +
     " HashCode of AbhiDuplicate :-> " + AbhiDuplicate.hashCode());
//HashCode of Abhi :-> -1597134327 HashCode of AbhiDuplicate :-> -1597134327
```

### Imp point :→

- While hash codes should ideally be unique for distinct objects, it's not always possible to guarantee absolute uniqueness.
- However, properly implemented hash code methods should strive to minimize collisions (cases where different objects have the same hash code) to ensure optimal performance of hash-based collections.
- The hashCode method is often used in conjunction with the equals method, which determines logical equality between objects. It's crucial for these methods to be consistent: if two objects are considered equal by the equals method, they must have the same hash code.

### ***Contract between `hashCode` and `equals:`***

- In Java, there's a fundamental contract between the `hashCode` and `equals` methods:
    - If two objects are considered equal by the `equals` method (meaning they have the same logical content), they must have the same hash code.
    - The reverse is not necessarily true: objects with the same hash code might not be equal according to `equals`