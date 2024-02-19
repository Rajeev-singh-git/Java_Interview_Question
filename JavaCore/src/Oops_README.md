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

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverloadingExample.java)

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

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverridingExample.java)

### Can we override static method in java explain with proper simple example?

**No, static methods cannot be overridden in Java.** Here's a clear explanation and an example to illustrate why:

**Key Concepts:**

- **Static Methods:** Belong to the class itself, not individual objects. They're called directly using the class name, not on instances of the class.
- **Overriding:** Redefining a method in a subclass to provide a specialized implementation for that subclass. Relies on dynamic binding at runtime to determine which method to call based on the object's actual type.

**Why Static Methods Can't Be Overridden:**

- **Static Binding:** Static methods are bound to their implementation at compile time, meaning the compiler decides which method to call based on the type of the reference variable used to call it, not the object's actual type.
- **Dynamic Binding:** Overriding requires dynamic binding, where the specific method to call is determined at runtime based on the object's type. This allows for polymorphism, where different objects can respond differently to the same message.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/MethodHiding.java)

In an interface, the JVM determines the exact method to call based on the object's type at runtime, not the reference type.

```java
OopsConcept.Interf I = new OopsConcept.Demo();
I.methodOne();  // This calls the methodOne implementation in OopsConcept.Demo class

OopsConcept.Interf I2 = new OopsConcept.Demo2();
I2.methodOne();  // This calls the methodOne implementation in OopsConcept.Demo2 class
```

[Complete Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Interf.java)

## Method Hiding

| Method Overriding | Method Hiding |
| --- | --- |
| Both Parent and Child class method should be non-static. | Both Parent and Child class method should be static. |
| Method Resolution is always taken care by JVM based on runtime objects. | Method Resolution is always taken care by compiler based on reference type. |
| Overriding is also considered as Runtime Polymorphism (or) Dynamic Polymorphism (or) late binding. | Method hiding is also considered as compile time polymorphism (or) static polymorphism (or) early binding. |
| [Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverloadingExample.java) | [Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/MethodHiding.java) |




## Overriding with respect to var-arg method.

A var-arg method should be overriden with  var-arg method only. If we are trying to override with normal method then it will become overloading not overriding.

[Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverridingVarAgMethod.java)



![Screenshot 2024-02-16 234543](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/1b23160d-d395-475f-adae-018124b4c748)


# Constructor

Whenever an object is created in java, some piece of code will be automatically executed to perform initialization of the object. This piece of code which did initialization is called Constructor.

Main Objective of constructor is to perform initialization.

```java
class Student {
    String name;
    int rollno;

    // Constructor to initialize name and rollno
    Student(String name, int rollno) {
        this.name = name; 
        this.rollno = rollno;
    }

    // Main method where objects are created
    public static void main(String[] args) {
        // Creating student objects with different names and roll numbers
        Student s1 = new Student("vijayabhaskar", 101);
        Student s2 = new Student("bhaskar", 102);
    }
}
```

## Constructor vs Instance Block

Both Constructor and instance block will be executed automatically for every object creation, but instance block 1st followed by the constructor.

Other than initialization, if we want to perform any activity for each created object, we should define that in instance block.

Instance blocks can take arguments, whereas constructor can not take arguments, so we can not replace one concept by other.

Code Example :→

```java
class Test {
    static int count = 0; // Static variable to count instances of Test

    {
        count++; // Instance initialization block increments count whenever an instance is created
    }

    Test() {
        // Default constructor
    }

    Test(int i) {
        // Parameterized constructor
    }

    public static void main(String[] args) {
        // Creating instances of Test class
        Test t1 = new Test();
        Test t2 = new Test(10);
        Test t3 = new Test();

        // Printing the value of count, which should be 3 since three instances are created
        System.out.println(count); // Output: 3
    }
}
```

## Rules to write Constructor :→

1. Name of the Constructor and Name of the class must be same.
2. Return type concept is not applicable for constructor. If we are declaring return type for constructor we won’t get compile time or Runtime error simply it will be treated as a method.
3. Although having the same name for a method and a class is allowed, it is not recommended.
4. The only applicable modifier for constructor are public, default, private, protected.

## Default Constructor :→

- For every class in java including abstract class constructor concept is applicable.
- If we are not writing constructor, then compiler will generate default constructor.
- Hence every class either contain compiler generated constructor or programmer written constructor but not both simultaneously.

### Prototype of Default Constructor :→

1. It is always no argument constructor.
2. The access modifier of the default constructor is same as class modifier. (This
   rule is applicable only for public and default).
3. Default constructor contains only one line. super(); it is a no argument call to
   super class constructor.

![Screenshot 2024-02-17 140907](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/c013800f-33dc-4136-b20e-fbf8620fc36b)


## Super() vs this()

1. First line inside constructor should be either super() or this(), if we are not writing anything compiler will generate super().
2. We should take super() or this() only in first line of Constructor, if we are taking anywhere else it will cause compile time error.
3. Super() or this() can be used only inside constructor ,using them anywhere else will result in a compile-time error
4. We can call a constructor directly from other constructor only.

![Screenshot 2024-02-17 143913](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/deb1dafc-2f29-4a02-a010-f6a4091b143a)

![Screenshot 2024-02-17 143532](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/4bd6c50c-e5b8-4b29-bfda-b22ebade5701)

## Overloaded Constructors :→

A class can contain more than one constructor and all these constructors having the same name but different arguments and hence these constructors are considered as overloaded constructors.

```java
class Test { 
    Test(double d) { 
        System.out.println("double-argument constructor"); 
    } 

    Test(int i) { 
        this(10.5); // Calls the double-argument constructor
        System.out.println("int-argument constructor"); 
    } 

    Test() { 
        this(10); // Calls the int-argument constructor
        System.out.println("no-argument constructor"); 
    } 

    public static void main(String[] args) { 
        Test t1 = new Test(); // Calls no-argument constructor, int-argument constructor, and double-argument constructor
        Test t2 = new Test(10); // Calls int-argument constructor and double-argument constructor
        Test t3 = new Test(10.5); // Calls double-argument constructor
    } 
}
```

1. Parent class constructor by default won't available to the Child. Hence
   Inheritance concept is not applicable for constructors and hence overriding
   concept also not applicable to the constructors. But constructors can be
   overloaded.
2. We can take constructor in any java class including abstract class also but we
   can't take constructor inside interface.

![Screenshot 2024-02-19 134501](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/e33ea274-2ab6-4b4f-a152-9751fea1e7ec)


### We can't create object for abstract class but abstract class can contain constructor what is the need ?

Abstract class constructor will be executed for every child class object creation to perform initialization of child class object only.

### Which of the following statement is true ?

1. Whenever we are creating child class object then automatically parent class
   object will be created.(false)
2. Whenever we are creating child class object then parent class constructor will be executed.(true)

```java
abstract class Parent {
    Parent() {
        System.out.println(this.hashCode()); // Outputs hash code of Child object
    }
}

class Child extends Parent {
    Child() {
        System.out.println(this.hashCode()); // Outputs hash code of Child object
    }
}

class Test {
    public static void main(String[] args) {
        Child c = new Child();
        System.out.println(c.hashCode()); // Outputs hash code of Child object
    }
}
```

### Note :→

1. Compiler will check whether the programmer wrote any constructor or not. If he didn't write at least one constructor then compiler will generate default constructor.
2. If the programmer wrote any constructor then compiler will check whether he wrote super() or this() in the 1st line or not. If his not writing any of these compiler will always write (generate) super().
3. Compiler will check is there any chance of recursive constructor invocation. If there is a possibility then compiler will raise compile time error.

```java
class Test {
    Test(int i) {
        this(); // Calls the no-argument constructor, leading to recursive invocation
    }
 
    Test() {
        this(10); // Calls the parameterized constructor, leading to recursive invocation
    }
 
    public static void main(String[] args) {
        System.out.println("hello");
    }
}

//CE : recursive constructor invocation
```

Correct Code : →

```java
class Test {
    Test(int i) {
        this(); // Calls the no-argument constructor
    }
 
    Test() {
        // No recursive constructor invocation here
    }
 
    public static void main(String[] args) {
        System.out.println("hello");
    }
}
```

- If the Parent class contains any argument constructors while writing Child
  classes we should takes special care with respect to constructors.
- Whenever we are writing any argument constructor it is highly recommended to write no argument constructor also.
- If the Parent class contains any argument constructors while writing Child
  classes we should takes special care with respect to constructors.

```java
class Parent {
    Parent() throws java.io.IOException {
    }
}

class Child extends Parent {
    Child() throws Exception {
        super(); // Calls the parent class constructor
    }
}
```

# Singleton Class

- Factory methods are typically invoked using the class name, rather than through the constructor. This allows for more flexibility in object creation and enables certain design patterns, like the Factory Method pattern.
- **Examples**:
   - **`Runtime.getRuntime()`**: Returns the current **`Runtime`** object, allowing access to the runtime environment.
   - **`DateFormat.getInstance()`**: Returns a **`DateFormat`** object based on the default locale and time zone, providing a convenient way to obtain a date format instance.
- **Usage**:
   - Factory methods are useful when object creation needs to adhere to certain constraints or conditions.
   - They can encapsulate complex instantiation logic or enforce specific rules during object creation.
   - Factory methods are often used in conjunction with design patterns such as the Factory Method pattern or the Singleton pattern.

# Factory Method

- Factory methods are typically invoked using the class name, rather than through the constructor. This allows for more flexibility in object creation and enables certain design patterns, like the Factory Method pattern.
- **Examples**:
    - **`Runtime.getRuntime()`**: Returns the current **`Runtime`** object, allowing access to the runtime environment.
    - **`DateFormat.getInstance()`**: Returns a **`DateFormat`** object based on the default locale and time zone, providing a convenient way to obtain a date format instance.
- **Usage**:
    - Factory methods are useful when object creation needs to adhere to certain constraints or conditions.
    - They can encapsulate complex instantiation logic or enforce specific rules during object creation.
    - Factory methods are often used in conjunction with design patterns such as the Factory Method pattern or the Singleton pattern.


# Static Control Flow

1. Identification of static member from top to bottom.
2. Execution of static variable assignments and static block from top to bottom.
3. Execution of main method.

## RIWO state

RIWO concept (Read Indirectly Write Out) refers to a specific state that a static variable experiences during initialization. Here's what you need to know:

**When does RIWO occur?**

- RIWO arises when a static variable is declared but **before it is assigned a value**.
- This typically happens within static blocks or before the declaration of the main method in a class.
- During this phase, the variable exists in memory but cannot be directly accessed using its name.

**Why is RIWO important?**

- RIWO ensures initialization safety for static variables.
- Without it, you could potentially read an uninitialized value, leading to unpredictable behavior or errors.
- By restricting direct access, RIWO forces explicit initialization before usage.

**How do you access a variable in RIWO state?**

- Since direct access is not allowed, you need to use an **indirect method call** to initialize the variable.
- This usually involves a method defined within the same class that assigns a value to the variable.

**Example:**

**Java**

```java
class MyClass {
    static int value; // Declared but not yet initialized

    static void initialize() {
        value = 10; // Indirectly assigning a value
    }

    public static void main(String[] args) {
        // RIWO state: accessing `value` directly here would cause an error
        //System.out.println(value);

        initialize(); // Indirectly write to the variable

        // Now, value can be accessed and used normally
        System.out.println(value); // Output: 10
    }
}

```

**Key points to remember:**

- RIWO is a temporary state for static variables during initialization.
- Avoid directly accessing static variables before they are assigned a value.
- Use indirect methods within the class to properly initialize them.
- Understanding RIWO ensures safe and correct use of static variables in Java.

## Static control flow parent to child relationship :→

Whenever we are executing Child class the following sequence of events will be
performed automatically.

1. Identification of static members from Parent to Child.
2. Execution of static variable assignments and static blocks from Parent to
   Child
3. Execution of Child class main() method.
   Note : When ever we are loading child class automatically the parent class will be loaded but when ever we are loading parent class the child class won't be loaded automatically.

## Static block

- **Execution Time**: Static blocks are executed at the time of class loading. This makes them suitable for performing activities that need to be done during class initialization.
- **Multiple Static Blocks**: Within a class, you can have multiple static blocks, and they will be executed in the order they appear, from top to bottom.
- **Example 1**: Loading native libraries is a common activity that needs to be done at the time of class loading. Defining this activity inside a static block ensures it's executed when the class is loaded.
- **Example 2**: JDBC driver classes often contain a static block to register the driver with the **`DriverManager`**. This registration is essential for JDBC functionality and is typically done automatically without the programmer needing to explicitly register the driver.

## Instance Control Flow

Whenever we are executing a java class static control flow will be executed. In the Static control flow whenever we are creating an object the following sequence of events will be performed automatically.

1. Identification of instance members from top to bottom.
2. Execution of instance variable assignments and instance blocks from top to
   bottom.
3. Execution of constructor.
   Note: static control flow is one time activity and it will be executed at the time of class loading.
   But instance control flow is not one time activity for every object creation it will be executed.


## Instance control flow in Parent to Child relationship

Whenever we are creating child class object the following sequence of events will be executed automatically.

1. Identification of instance members from Parent to Child.
2. Execution of instance variable assignments and instance block only in Parent class.
3. Execution of Parent class constructor.
4. Execution of instance variable assignments and instance blocks in Child class.
5. Execution of Child class constructor.
   Note: Object creation is the most costly operation in java and hence if there is no specific requirement never recommended to crate objects.

We can't access instance variables directly from static area because at the time of execution of static area JVM may not identify those members.

 But from the instance area we can access instance members directly.
 Static members we can access from anywhere directly because these are
identified already at the time of class loading only.

# Type Casting :→

In java, Parent class reference can be used to hold Child class object but by using that reference we can't call Child specific methods.

```java
Object o = new String("ashok"); // Valid
System.out.println(o.hashCode()); // Valid
System.out.println(o.length()); // Compile-time error
```

To resolve this issue, you can either:

1. Cast the **`o`** reference to the **`String`** type before calling the **`length()`** method:

    ```java
    System.out.println(((String) o).length());
    ```

2. Declare the **`o`** reference variable as a **`String`** type:

    ```java
    String o = new String("ashok");
    System.out.println(o.length());
    ```

   ## Type Casting Syntax

   ![Screenshot 2024-02-19 181822](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/e3b60918-568d-4f28-8d98-ae1372eb9746)


   Rule 1 : The type of "d" and "c" must have some relationship [either Child to Parent (or) Parent to Child (or) same type] otherwise we will get compile time error saying inconvertible types.


![Screenshot 2024-02-19 182020](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/0c9b1fed-f640-4314-88a1-c2f8d4340f82)

   Rule 2: "C" must be either same (or) derived type of "A" otherwise we will get compile time error saying incompatible types.
   Found: C
   Required: A
![Screenshot 2024-02-19 182107](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/f6567961-8393-415d-9ab1-2c5817d41875)


   ## Runtime Checking

   The underlying object type of "d" must be either same (or) derived type of "C" otherwise we will get runtime exception saying ClassCastException.

  ![Screenshot 2024-02-19 182638](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/cc7637b2-23ad-46bf-8934-2fa1c8bec501)


   Through Type Casting we are not create any new objects for the existing objects we are providing another type of reference variable(mostly Parent type).

    ```java
    package OopsConcept;
    
    public class TypeCasting {
    }
    
    class Parent1{
    
        public void methodOne(){
            System.out.println("Parent Class : A");
        }
    }
    
    class Child1 extends Parent1{
        public void methodOne(){
            System.out.println("Child Class : B");
        }
    
        public void methodTwo(){
            System.out.println("Child Class : C");
        }
    
        public static void main(String[] args){
            Child1 c1 = new Child1();
            c1.methodOne();  //Child Class : B
            c1.methodTwo();  //Child Class : C
            ((Parent1)c1).methodOne(); //Child Class : B
        }
    }
    
    /*
    Child Class : B
    Child Class : C
    Child Class : B
    */
    ```

   # Cohesion

   For every component we have to maintain a clear well defined functionality such type of component is said to be follow high cohesion.

![Screenshot 2024-02-19 183928](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/7085dc39-adcb-4335-9714-0665167569eb)

   High cohesion is always good programming practice because it has several advantages.

    1. Without effecting remaining components we can modify any component hence enhancement will become very easy.
    2. It improves maintainability of the application.
    3. It promotes reusability of the application.(where ever validation is required we can reuse the same validate servlet without rewriting )

   Note: It is highly recommended to follow loosely coupling and high cohesion
