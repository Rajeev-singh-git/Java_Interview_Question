# Oops 


## Abstraction

Abstraction is a process of hiding the internal implementation details and showing only functionality to the user. Another way, it shows only essential things to the user and hides the internal details


Ex : ATM GUI screen only highlights 

Abstraction can be achieved using

1.) Abstract Classes:

An abstract class is a class that cannot be instantiated on its own and may contain abstract methods (methods without a body) that are meant to be implemented by its subclasses.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/AbstractionExample.java)

2.) Using Interface:

Interfaces:

An interface in Java is a collection of abstract methods. A class can implement multiple interfaces.

[Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/AbstractionExampleUsingInterface.java)



## Encapsulation

The binding of data and the methods that operate on that data into a single unit is called encapsulation. When a Java class follows the principles of data hiding and abstraction, it can be referred to as an encapsulated class.

1. **`Private Fields:`** The attributes of a class are often declared as private, meaning they can only be accessed within the class itself.
2. **`Public Methods` (Getters and Setters):** Public methods are provided to allow controlled access to the private attributes. These methods are often referred to as getter methods (for retrieving the values) and setter methods (for modifying the values).

[Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Encapsulation.java)

## Inheritance

Inheritance in object-oriented programming allows a child class  to access the properties (fields and methods) of a parent class.  This enables the child class to reuse, extend, and build upon the functionality provided by the parent class.

Inheritance in object-oriented programming establishes an "is-a" relationship between the subclass and the superclass. This relationship is implemented in Java using the **`extends`** keyword.



- **`IS-A Relationship:`** Inheritance represents an "is-a" relationship, meaning that a subclass is a specialized version of its superclass.
- **`extends` Keyword in Java:** The **`extends`** keyword is used to create a subclass that inherits from a superclass.

[Inheritance](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/InheritanceExample.java)

### Why java won't provide support for multiple inheritance?

**Diamond Problem:**

- Multiple inheritance introduces the "diamond problem," a situation where a class inherits from two classes that have a common ancestor. This creates ambiguity when the compiler tries to resolve which version of a method or field to use.
- Resolving the diamond problem becomes complex and can lead to unpredictable behavior. Java's design emphasizes simplicity and avoiding such complexities.


## HAS-A Relationship

In Java, a "HAS-A" relationship refers to a form of association between classes, where one class contains an instance of another class as a member or field. This relationship is based on composition, and it is often used to represent a stronger relationship than a simple "uses" or "knows" relationship.

Here are the key points about the HAS-A relationship:

### Composition:
    - The HAS-A relationship is achieved through composition, where one class contains an object of another class as a member variable.
    - The class that contains the object is often referred to as the "container" or "composite" class, and the class being contained is referred to as the "component" or "part" class.
    - Without Existing the container object if there is no chance of existing contained object. The relationship between container object and contained object is called composition which is a strong association.

### **Code Example:**

```java
class Engine {
    // Engine-related code
}

class Car {
    private Engine engine;  // OopsConcept.Composition: Car HAS-A relationship with Engine

    public Car(Engine engine) {
        this.engine = engine;
    }

    // Other car-related code
}
```

- The **`Car`** class is the container object.
- The **`Engine`** class is the contained object.

Without an existing car, the engine cannot exist. The **`Car`** manages the creation and destruction of the **`Engine`**, and the lifecycle of the **`Engine`** is tightly bound to the lifecycle of the **`Car`**. This is a clear example of a strong association through composition.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Composition.java)

# Aggregation

Without an existing container object, if there is a chance of the existing contained object, such a type of relationship is called aggregation."

In other words, the lifecycle of the contained object is not strictly tied to the lifecycle of the container object in aggregation. The contained object can exist on its own or be associated with multiple containers. This weaker relationship provides more flexibility and independence between the objects involved in the aggregation.


[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Aggregation.java)


## Polymorphism concept

Polymorphism, meaning "many forms," is a powerful concept in Java that allows you to treat objects of different types in the same way. It enhances code flexibility and reusability, making your programs cleaner and more efficient.

There are two main types of polymorphism in Java: compile-time polymorphism (also known as static or method overloading) and runtime polymorphism (also known as dynamic or method overriding).

1. **Compile-time Polymorphism (Method Overloading):**
    - Method overloading occurs when a class has multiple methods with the same name but different parameters (number, type, or order of parameters).
    - The decision on which method to call is made at compile time based on the method signature.
    - Example:

    ```java
    
    public class Calculator {
        public int add(int a, int b) {
            return a + b;
        }
    
        public double add(double a, double b) {
            return a + b;
        }
    }
    
    ```

   In this example, there are two **`add`** methods with different parameter types, allowing the same method name to be used for both integers and doubles.

   Method resolution in method overloading is determined at compile-time based on the reference type of the object (also known as static or compile-time polymorphism). This means that the compiler decides which method to call by examining the reference type of the object at compile time.


2. **Runtime Polymorphism (Method Overriding):**
   - Method overriding occurs when a subclass provides a specific implementation for a method that is already defined in its superclass.
   - The decision on which method to call is made at runtime based on the actual type of the object.
   - To achieve runtime polymorphism, you use the **`@Override`** annotation in the subclass to indicate that the method is intended to override a method in the superclass.
   - Example:

    ```java
    javaCopy code
    class Animal {
        void sound() {
            System.out.println("Animal makes a sound");
        }
    }
    
    class Dog extends Animal {
        @Override
        void sound() {
            System.out.println("Dog barks");
        }
    }
    
    class Cat extends Animal {
        @Override
        void sound() {
            System.out.println("Cat meows");
        }
    }
    
    ```

   In this example, both **`Dog`** and **`Cat`** classes override the **`sound`** method from the **`Animal`** class. The specific implementation is determined at runtime based on the actual object type.


Polymorphism in Java helps achieve flexibility and extensibility in code, making it easier to work with diverse types of objects in a unified manner.


### Can we override static method in java explain with proper simple example?

**No, static methods cannot be overridden in Java.** Here's a clear explanation and an example to illustrate why:

**Key Concepts:**

- **Static Methods:** Belong to the class itself, not individual objects. They're called directly using the class name, not on instances of the class.
- **Overriding:** Redefining a method in a subclass to provide a specialized implementation for that subclass. Relies on dynamic binding at runtime to determine which method to call based on the object's actual type.

**Why Static Methods Can't Be Overridden:**

- **Static Binding:** Static methods are bound to their implementation at compile time, meaning the compiler decides which method to call based on the type of the reference variable used to call it, not the object's actual type.
- **Dynamic Binding:** Overriding requires dynamic binding, where the specific method to call is determined at runtime based on the object's type. This allows for polymorphism, where different objects can respond differently to the same message.


In an interface, the JVM determines the exact method to call based on the object's type at runtime, not the reference type.

```java
OopsConcept.Interf I = new OopsConcept.Demo();
I.methodOne();  // This calls the methodOne implementation in OopsConcept.Demo class

OopsConcept.Interf I2 = new OopsConcept.Demo2();
I2.methodOne();  // This calls the methodOne implementation in OopsConcept.Demo2 class
```

[Complete Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/OopsConcept.Interf.java)
