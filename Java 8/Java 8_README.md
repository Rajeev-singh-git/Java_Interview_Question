# Java 8 New features :

1. `**Lambda Expression**` : These concise anonymous functions allow you to write cleaner and more functional code. They are particularly useful for short, well-defined operations on collections.
2. `**Functional Interface**` : Functional interfaces are interfaces that have exactly one abstract method. They can be annotated with the **`@FunctionalInterface`** annotation for clarity and can be used as the target for lambda expressions and method references.
3. **`Default Methods`** :  Interfaces can now have method implementations, allowing you to add new functionality to existing interfaces without breaking backward compatibility.
4. **`Predicates`** : These are functional interfaces that take one argument and return a boolean value (`true` or `false`). They are often used with the Stream API to filter collections based on certain conditions.  For example, a predicate can be used to filter a list of numbers to only keep even numbers.
5. `**Functions**` :The **`Function`** interface represents a function that accepts one argument and produces a result. It's widely used for transforming elements in collections or streams. Functions can be composed and chained together to perform multiple transformations sequentially.
6. `**Double Colon Operator (::)**`: Also known as the method reference operator, it's used to reference methods or constructors without invoking them. Method references provide a shorthand syntax for lambda expressions when the lambda expression simply calls an existing method. They improve code readability and reduce boilerplate.
7. **`Stream API`** : Streams provide a new abstraction for processing sequences of elements, supporting functional-style operations such as map, filter, reduce, and more. Streams enable concise and declarative code for working with collections.
8.  **`Date and Time API:`** Java 8 introduced a new Date and Time API (java.time package) that offers improved handling of date and time values. It provides classes such as LocalDate, LocalTime, LocalDateTime, ZonedDateTime, etc., for representing dates, times, and intervals.
9.  **`Consumer`**: The **`Consumer`** interface represents an operation that accepts a single input argument and returns no result. It's commonly used for performing actions on elements in collections or streams, such as printing, logging, or updating state.

# Lambda (λ) Expression

A lambda expression, denoted by the symbol "λ", is a feature introduced in programming languages to enable the creation of anonymous functions or closures. In essence, lambda expressions allow you to define a block of code that can be passed around as a variable, just like any other data type.

The term "lambda" comes from lambda calculus, a mathematical notation for expressing functions. In programming languages like Java, Python, and JavaScript, lambda expressions provide a more concise syntax for creating simple functions without the need for explicit method definitions.

## What is Lambda Expression (λ) ?

- Lambda expressions are anonymous (nameless) function. That means the function which doesn’t have names, return types, and access modifiers.
- They are also referred to as anonymous functions or closures.

Example 1: Without Lambda Expression

```java
 public void m1() {
	 System.out.println("hello");
 }
```

With Lambda Expression

```java
()-> System.out.println("hello");
```

Example 2: Without Lambda Expression

```java
public void add(int a, int b) {
  System.out.println(a + b);
}
```

- If the compiler can infer the parameter types based on the context, types can be omitted.
- The Lambda expression above can be rewritten as

```java
(a, b) -> System.out.println(a + b);
```

Example 3: Without Lambda Expression

```java
 public String str(String str) {
  return str;
 }
```

With Lambda Expression

If lambda expression is taking only one input value (one parameter) , then parameter parenthesis also becomes optional.

```java
str -> str
```

### Conclusions

1. Lambda expressions can have zero or more parameters.

   Example:

    ```java
    () -> System.out.println("hello");
    (int a) -> System.out.println(a);
    (int a, int b) -> return a + b;
    ```

2. Parameter types are usually specified, but if the compiler can infer them, they can be omitted.

   Example:

    ```java
    (int a, int b) -> System.out.println(a + b);
    (a, b) -> System.out.println(a + b);
    ```

3. Multiple parameters are separated by commas (,).
4. If no parameters are present, empty parentheses () are used.

   Example :

    ```java
    () -> System.out.println("hello");
    ```

5. If only one parameter is present and the compiler can infer the type, type and parentheses can be omitted.

   Example:

    ```java
    (int a) -> System.out.println(a);
    (a) -> System.out.println(a);
    a -> System.out.println(a);
    ```

6. Lambda expression bodies can contain multiple statements, enclosed in curly braces. If only one statement is present, curly braces are optional.
7.  Once defined, Lambda expressions can be called like methods, requiring functional interfaces.
8. If lambda expression return something then we can remove return keyword

# Functional Interfaces

If an interface contains only one abstract method, it is categorized as a functional interface, and the method is referred to as a functional method or a Single Abstract Method (SAM).

**Examples**:

1. **`Runnable`**: Contains only the **`run()`** method.
2. **`Comparable`**: Contains only the **`compareTo()`** method.
3. **`ActionListener`**: Contains only the **`actionPerformed()`** method.
4. **`Callable`**: Contains only the **`call()`** method.

In a functional interface, besides the single abstract method (SAM), any number of default and static methods can be included.

Example:

```java
interface Interf { 
    void m1(); 
    default void m2() { 
        System.out.println("hello"); 
    } 
}

```

In Java 8, Sun Microsystems introduced the **`@FunctionalInterface`** annotation to specify that an interface is a functional interface.

```java
@FunctionalInterface
interface Interf {
    void m1();
}

```

Inside a functional interface, only one abstract method is allowed. Attempting to define more than one abstract method will result in a compilation error.

**Example**:

```java
@FunctionalInterface
interface Interf {
    void m1(); // This code will cause a compilation error.
    void m2();
}

```

Inside a Functional Interface, it's mandatory to declare exactly one abstract method. Failure to declare this method will result in a compilation error.

Example :

```java
@FunctionalInterface
interface Interface { // Compilation error
}
```

## Functional Interface with respect to Inheritance :

If an interface extends a Functional Interface and the child interface doesn't declare any new abstract methods, then the child interface is also considered a Functional Interface.

```java
@FunctionalInterface
interface A {
    void methodOne();
}

@FunctionalInterface
interface B extends A {
}

```

In the child interface, it's permissible to define the exact same abstract method as in the parent interface.

```java
@FunctionalInterface
interface A {
    void methodOne();
}

@FunctionalInterface
interface B extends A {
    void methodOne();
}

```

However, if the child interface defines any new abstract methods, it ceases to be a Functional Interface. Attempting to annotate it with **`@FunctionalInterface`** will result in a compilation error.

Example

```java
@FunctionalInterface
interface A {
    void methodOne();
} 

@FunctionalInterface
interface B extends A {
    void methodTwo(); // compiletime error
}

```

In both the parent and child interfaces, any number of default methods can be defined without restrictions. Restrictions only apply to the addition of new abstract methods.

```java
@FunctionalInterface
interface A {
    void methodOne();
} 

interface B extends A {
    void methodTwo(); // This is a normal interface, so the code compiles without error
}

```

## **Functional Interface Vs Lambda Expressions:**

When utilizing Lambda expressions to invoke functionality, a Functional Interface is required. Lambda expressions can be referenced using Functional Interface references. Wherever the concept of Functional Interface is applicable, Lambda Expressions can be employed.

**Example 1 without Lambda Expression:**

```java
interface Interf { 
    void methodOne();
}

public class Demo implements Interf { 
    public void methodOne() { 
        System.out.println("method one execution"); 
    } 
}

public class Test { 
    public static void main(String[] args) { 
        Interf i = new Demo(); 
        i.methodOne(); 
    } 
}

```

Example 1 with Lambda Expression :

```java
interface Interf { 
    void methodOne(); 
}

public class Test { 
    public static void main(String[] args) { 
        Interf i = () -> System.out.println("MethodOne Execution"); 
        i.methodOne(); 
    } 
}

```

**Example 2 without Lambda Expression:**

```java
interface Interf { 
    void sum(int a, int b); 
}

class Demo implements Interf { 
    public void sum(int a, int b) { 
        System.out.println("The sum: " + (a + b)); 
    } 
}

public class Test { 
    public static void main(String[] args) { 
        Interf i = new Demo(); 
        i.sum(20, 5); 
    } 
}

```

Example 2 with Lambda Expression:

```java
interface Interf { 
    void sum(int a, int b); 
}

public class Test { 
    public static void main(String[] args) { 
        Interf i = (a, b) -> System.out.println("The Sum: " + (a + b)); 
        i.sum(5, 10); 
    } 
}

```

**Example 3 without Lambda Expression:**

```java
interface Interf { 
    int square(int x); 
}

class Demo implements Interf { 
    public int square(int x) { 
        return x * x; 
    } 
}

class Test { 
    public static void main(String[] args) { 
        Interf i = new Demo(); 
        System.out.println("The Square of 7 is: " + i.square(7)); 
    } 
}

```

Example 3 with Lambda Expression

```java
interface Interf { 
    int square(int x); 
}

class Test { 
    public static void main(String[] args) { 
        Interf i = x -> x * x; 
        System.out.println("The Square of 5 is: " + i.square(5)); 
    } 
}

```

Example 4 with without Lambda Expression

```java
class MyRunnable implements Runnable { 
    public void main() { 
        for(int i = 0; i < 10; i++) { 
            System.out.println("Child Thread"); 
        } 
    } 
}

class ThreadDemo { 
    public static void main(String[] args) { 
        Runnable r = new MyRunnable(); 
        Thread t = new Thread(r); 
        t.start(); 
        for(int i = 0; i < 10; i++) { 
            System.out.println("Main Thread"); 
        } 
    } 
}

```

Example 4 with Lambda Expression

```java
class ThreadDemo { 
    public static void main(String[] args) { 
        Runnable r = () -> { 
            for(int i = 0; i < 10; i++) { 
                System.out.println("Child Thread"); 
            } 
        }; 
        Thread t = new Thread(r); 
        t.start(); 
        for(int i = 0; i < 10; i++) { 
            System.out.println("Main Thread"); 
        } 
    } 
}

```

[All Codes](https://github.com/Rajeev-singh-git/Java_Interview_Question/tree/main/Java%208/src/Code/Functional)

## Anonymous inner classes Vs Lambda Expressions:

Wherever anonymous inner classes are used, there's often an opportunity to employ Lambda expressions to reduce code length and complexity.

**Example with Anonymous Inner Class:**

```java
class Test { 
    public static void main(String[] args) { 
        Thread t = new Thread(new Runnable() { 
            public void run() { 
                for(int i = 0; i < 10; i++) { 
                    System.out.println("Child Thread"); 
                } 
            } 
        }); 
        t.start(); 
        for(int i = 0; i < 10; i++) { 
            System.out.println("Main thread"); 
        } 
    } 
}

```

**Example with Lambda Expression:**

```java
class Test { 
    public static void main(String[] args) { 
        Thread t = new Thread(() -> { 
            for(int i = 0; i < 10; i++) { 
                System.out.println("Child Thread"); 
            } 
        }); 
        t.start(); 
        for(int i = 0; i < 10; i++) { 
            System.out.println("Main Thread"); 
        } 
    } 
}

```

## **Advantages of Lambda Expressions:**

- Lambda expressions help reduce the length of code, thereby improving code readability.
- They simplify the complexity associated with anonymous inner classes.
- Lambda expressions can be used in place of objects.
- They can be passed as arguments to methods.

Notes :

- Anonymous inner classes can extend concrete classes, abstract classes, or implement interfaces with any number of methods.
- Lambda expressions can only implement interfaces with a single abstract method (Functional Interface).
- Anonymous inner classes and Lambda expressions are not equivalent.
- Inside an anonymous inner class, instance variables can be declared.
- Inside a Lambda expression, instance variables cannot be declared; instead, variables act as local variables.
- Within a Lambda expression, the **`this`** keyword refers to the current outer class object reference.

Examples :

```java
interface Interf { 
    void m1(); 
} 

class Test { 
    int x = 777; 
    public void m2() { 
        Interf i = () -> { 
            int x = 888; 
            System.out.println(x); // Output: 888
            System.out.println(this.x); // Output: 777
        }; 
        i.m1(); 
    } 
    public static void main(String[] args) { 
        Test t = new Test(); 
        t.m2(); 
    } 
} 

```

From lambda expressions, enclosing class variables and enclosing method variables can be accessed directly.

Local variables referenced from lambda expressions are implicitly final, hence re-assignment of those local variables is not allowed, resulting in a compile-time error.

```java
interface Interf { 
    void m1(); 
} 

class Test { 
    int x = 10; 
    public void m2() { 
        int y = 20; 
        Interf i = () -> { 
            System.out.println(x); // Output: 10
            System.out.println(y); // Output: 20
            x = 888; 
            y = 999; // Compile Error
        }; 
        i.m1(); 
        // y = 777; // CE - Not allowed
    } 
    public static void main(String[] args) { 
        Test t = new Test(); 
        t.m2(); 
    } 
} 

```

## Differences between anonymous inner classes and Lambda expression :

|  | Anonymous Inner Class | Lambda Expression |
| --- | --- | --- |
| Naming | A class without a name | A method without a name (anonymous function) |
| Extending | Can extend abstract and concrete classes | Cannot extend abstract or concrete classes |
| Implementing Interfaces | Can implement interfaces with any number of abstract methods | Can implement interfaces with a single abstract method (Functional Interface) |
| Instance Variables | Can declare instance variables | Cannot declare instance variables, only local variables |
| this Reference | Refers to the current anonymous inner class object | Refers to the current outer class object |
| Instantiation | Can be instantiated | Cannot be instantiated |
| Best Use Case | Suitable for handling multiple methods | Suitable for handling interfaces with a single abstract method (Functional Interface) |
| Compilation | Generates a separate .class file | No .class file generated, converts into a private method in the outer class |
| Memory Allocation | Allocated on demand when creating an object | Resides in permanent memory of JVM (Method Area) |

# Default Methods :

- Until version 1.7, interfaces could only contain public abstract methods and public static final variables. All methods inside interfaces were implicitly public and abstract.
- Every variable declared inside an interface is always public static final, whether explicitly declared or not.
- From version 1.8 onwards, in addition to the above, interfaces can declare default concrete methods, also known as defender methods.
- Default methods are declared using the keyword **`default`**, as shown below:

    ```java
    
    default void m1(){
        System.out.println("Default Method");
    }
    ```

- Default methods in interfaces are by default available to all implementation classes. Implementation classes can use these default methods directly or override them if needed.

    ```java
    interface Interf {
        default void m1() {
            System.out.println("Default Method");
        }
    }
    class Test implements Interf {
        public static void main(String[] args) {
            Test t = new Test();
            t.m1();
        }
    }
    ```

- Default methods are also known as defender methods or virtual extension methods.
- The main advantage of default methods is that new functionality can be added to an interface without affecting implementation classes, ensuring backward compatibility.
- Object class methods cannot be overridden as default methods inside interfaces; attempting to do so results in a compilation error.This would result in a compile-time error because Object class methods are already available to every Java class, and it is not necessary to bring them in through default methods.

    ```java
    interface Interf {
        default int hashCode() {
            return 10;
        }
    }
    ```


## Default method vs multiple inheritance :

- When two interfaces contain default methods with the same signature, it can lead to an ambiguity problem, often referred to as the diamond problem.
- To avoid this ambiguity problem, it is necessary to override the default method in the implementation class or call a specific default method from one of the interfaces using the syntax **`interfacename.super.methodName()`**; otherwise, a compile-time error will occur.

```java
// Interface Left
interface Left { 
    default void m1() { 
        System.out.println("Left Default Method"); 
    } 
} 

// Interface Right
interface Right { 
    default void m1() { 
        System.out.println("Right Default Method"); 
    } 
} 

// Implementation Class Test
class Test implements Left, Right {}

```

In this example, both **`Left`** and **`Right`** interfaces have a default method **`m1()`**. If the **`Test`** class does not override the **`m1()`** method, a compilation error will occur due to the ambiguity of which default method to inherit.

### **Overriding Default Methods in Implementation Class:**

- In the implementation class, it's possible to provide a completely new implementation for a default method from one of the interfaces it implements.
- It's also possible to call a specific default method from one of the interfaces using the syntax **`interfacename.super.methodName()`**.

Example:

```java
class Test implements Left, Right { 
    public void m1() { 
        System.out.println("Test Class Method"); // New implementation
        // or call Left interface's default method
        // Left.super.m1();
        // Right.super.m1();
    } 
    public static void main(String[] args) { 
        Test t = new Test(); 
        t.m1(); 
    } 
}

```

In this example, the **`Test`** class implements both **`Left`** and **`Right`** interfaces, each having a default method **`m1()`**. The **`Test`** class provides a new implementation for **`m1()`** method. Alternatively, you can call **`Left`** interface's default method using **`Left.super.m1();` or**  you can call `**Right**` interface's default method using **`Right.super.m1()`.**

## Differences between interface with default methods and abstract class

| Aspect | Interface with Default Methods | Abstract Class |
| --- | --- | --- |
| Variable Declarations | Variables are always public static final; no instance variables | May contain instance variables required by child classes |
| Object State | Does not define or discuss object state | Can define and manage object state |
| Constructors | Cannot declare constructors | Can declare constructors |
| Initialization Blocks | Cannot declare instance or static initialization blocks | Can declare instance and static initialization blocks |
| Lambda Expressions | Functional interfaces with default methods can refer to lambda expressions | Cannot refer to lambda expressions |
| Object Class Method Override | Cannot override Object class methods | Can override Object class methods |

In summary, although interfaces with default methods provide a form of adding concrete methods to interfaces, they do not equate to abstract classes. Abstract classes offer more flexibility in terms of managing state, constructors, initialization blocks, and overriding Object class methods.

# **Static Methods Inside Interface:**

- From Java 1.8 onwards, static methods can be defined inside interfaces to provide utility functions.
- Interface static methods are not by default available to implementation classes; they must be called using the interface name.

    ```java
    javaCopy code
    interface Interf {
        public static void sum(int a, int b) {
            System.out.println("The Sum:" + (a + b));
        }
    }
    class Test implements Interf {
        public static void main(String[] args) {
            Test t = new Test();
            //t.sum(10, 20); // Compilation Error
            //Test.sum(10, 20); // Compilation Error
            Interf.sum(10, 20); // Valid
        }
    }
    
    ```

- Overriding is not applicable for interface static methods, as they are not inherited by implementation classes.
- It's valid to define exactly the same method in the implementation class, but it's not considered overriding.

    ```java
    interface Interf {
        public static void m1() {}
    }
    class Test implements Interf {
        public static void m1() {}
    }
    ```

- In Java 8 and later versions, it's possible to define the **`main()`** method inside an interface, allowing the interface to be run directly from the command prompt.

    ```java
    
    interface Interf {
        public static void main(String[] args) {
            System.out.println("Interface Main Method");
        }
    }
    
    ```


Running the interface from the command prompt:

```
javac Interf.java
java Interf
```

# Predicates

- A predicate is a function with a single argument that returns a boolean value.
- In Java 1.8, Oracle introduced the Predicate interface (**`Predicate<T>`**) to implement predicate functions.
- The Predicate interface is located in the **`java.util.function`** package.
- It is a functional interface containing only one method, **`test()`**.

    ```java
    
    interface Predicate<T> {
        boolean test(T t);
    }
    ```

- As a functional interface, Predicate can refer to lambda expressions.

**Examples:**

1. Writing a predicate to check whether the given integer is greater than 10 or not.

    ```java
    Predicate<Integer> p = i -> i > 10;
    System.out.println(p.test(100)); // true
    System.out.println(p.test(7));   // false
    ```

2. Writing a predicate to check the length of a given string is greater than 3 or not.

    ```java
    Predicate<String> p = s -> s.length() > 3;
    System.out.println(p.test("rvkb")); // true
    System.out.println(p.test("rk"));   // false
    ```

3. Writing a predicate to check whether the given collection is empty or not.

```java
Predicate <Collection> p = c -> c.isEmpty();
```

## Predicate Joining

It’s possible to join Predicates.

The **`and()`**, **`or()`**, and **`negate()`** methods of the **`Predicate`** interface serve similar purposes to the logical AND, OR, and NOT operators respectively.

- **`and()`**: Combines two predicates with a logical AND operation.
- **`or()`**: Combines two predicates with a logical OR operation.
- **`negate()`**: Negates the result of a predicate.

Example Code :

```java
import java.util.function.*;

class Test {
    public static void main(String[] args) {
        int[] x = {0, 5, 10, 15, 20, 25, 30}; 
        Predicate<Integer> p1 = i -> i > 10; 
        Predicate<Integer> p2 = i -> i % 2 == 0; 
        
        System.out.println("The Numbers Greater Than 10:"); 
        m1(p1, x); 
        
        System.out.println("The Even Numbers Are:"); 
        m1(p2, x); 
        
        System.out.println("The Numbers Not Greater Than 10:"); 
        m1(p1.negate(), x); 
        
        System.out.println("The Numbers Greater Than 10 And Even Are:"); 
        m1(p1.and(p2), x); 
        
        System.out.println("The Numbers Greater Than 10 OR Even:"); 
        m1(p1.or(p2), x); 
    } 
    
    public static void m1(Predicate<Integer> p, int[] x) { 
        for (int x1 : x) { 
            if (p.test(x1)) 
                System.out.println(x1); 
        } 
    } 
}

```
[Predicate In-Detail](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Code/Predicate/Predicates_README.md)

# Functions

- Functions are similar to predicates but can return any type of result.
- A function should (can) return only one value, which can be of any type as per the requirement.
- In Java 1.8, Oracle introduced the Function interface to implement functions.
- The Function interface is located in the **`java.util.function`** package.
- It is a functional interface containing only one method, **`apply()`**.

    ```java
    javaCopy code
    interface Function<T, R> {
        R apply(T t);
    }
    
    ```


Write a function to find the length of a given input string.

```java
import java.util.function.*;

class Test {
    public static void main(String[] args) {
        Function<String, Integer> f = s -> s.length();
        System.out.println(f.apply("Durga")); // Output: 5
        System.out.println(f.apply("Soft"));  // Output: 4
    }
}
```

Note: Function is a functional interface and hence can refer to lambda expressions.

## Difference between predicate and Function

Here's a concise comparison between Predicate and Function:

| Predicate | Function |
| --- | --- |
| Used for conditional checks. | Used for performing operations and returning results. |
| Takes one type parameter representing the input argument type. | Takes two type parameters: the input argument type and the return type. |
| Predicate<T> | Function<T, R> |
| Defines one method: test(). | Defines one method: apply(). |
| Returns a boolean value. | Can return any type of value. |
| Default methods and(), or(), negate() available for combining predicates. |  |

Predicates are suitable for conditional checks and return boolean values, while functions perform operations and can return values of any type. Each has its own interface with methods tailored to its specific purpose.

# Method and Constructor references by using :: (double colon) operator :

- Functional interface methods can be mapped to specified methods using the `**::**` (double colon) operator, known as method reference.
- Specified methods can be either static or instance methods.
- The argument types of the functional interface method and specified method should match, while other aspects like return type, method name, and modifiers are not required to match.
- Syntax:
    - For static methods: **`ClassName::methodName`**
    - For instance methods: **`ObjRef::methodName`**
- Functional interfaces can refer to lambda expressions or method references, offering an alternative syntax to lambda expressions.

**Example: With Lambda Expression**

```java
class Test {
    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println("Child Thread");
            }
        };
        Thread t = new Thread(r);
        t.start();
        for (int i = 0; i <= 10; i++) {
            System.out.println("Main Thread");
        }
    }
}

```

**With Method Reference**

```java
class Test {
    public static void m1() {
        for (int i = 0; i <= 10; i++) {
            System.out.println("Child Thread");
        }
    }

    public static void main(String[] args) {
        Runnable r = Test::m1;
        Thread t = new Thread(r);
        t.start();
        for (int i = 0; i <= 10; i++) {
            System.out.println("Main Thread");
        }
    }
}

```

In the above example, the **`Runnable`** interface's **`run()`** method refers to the static method **`m1()`** of the **`Test`** class.

## Method reference to instance method:

```java
interface Interf {
    public void m1(int i);
}

class Test {
    public void m2(int i) {
        System.out.println("From Method Reference:" + i);
    }

    public static void main(String[] args) {
        Interf f = i -> System.out.println("From Lambda Expression:" + i);
        f.m1(10);
        Test t = new Test();
        Interf i1 = t::m2;
        i1.m1(20);
    }
}

```

In the above example, the functional interface method **`m1()`** refers to the instance method **`m2()`** of the **`Test`** class.

# Constructor Reference

- Constructor references can also be made using the **`::`** (double colon) operator.
- Syntax: **`ClassName::new`**

```java
class Sample {
    private String s;

    Sample(String s) {
        this.s = s;
        System.out.println("Constructor Executed:" + s);
    }
}

interface Interf {
    public Sample get(String s);
}

class Test {
    public static void main(String[] args) {
        Interf f = s -> new Sample(s);
        f.get("From Lambda Expression");
        Interf f1 = Sample::new;
        f1.get("From Constructor Reference");
    }
}

```

- In method and constructor references, the argument types must match.

# Streams

To process objects from a collection, Java introduced the Streams concept in version 1.8. However, it's essential to understand the distinctions between **`java.util.streams`** and **`java.io`** streams.

## **Differences between `java.util.streams` and `java.io` Streams:**

- **java.util.streams**:
    - Meant for processing objects from collections.
    - Represents a stream of objects from a collection.
- **java.io Streams**:
    - Meant for processing binary and character data with respect to files.
    - Represents a stream of binary or character data from a file.

Thus, **`java.io`** streams and **`java.util.streams`** are fundamentally different due to their purposes and the types of data they handle.

## **Difference between Collection and Stream:**

- **Collection**:
    - Represents a group of individual objects as a single entity.
    - Used for storing and managing a group of objects.
- **Stream**:
    - Used for processing a group of objects from a collection.
    - Created from a collection using the **`stream()`** method, which is a default method added to the Collection interface in version 1.8.
    - Represents a sequence of elements that support aggregate operations.


**`default Stream stream()`**

```java
Stream s = c.stream();
```

- Stream is an interface present in java.util.stream. Once we got the stream, by using that we can process objects of that collection.
- We can process the objects in the following 2 phases :
    1. Configuration
    2. Processing

## 1. Configuration

We can configure either by using filter mechanism or by using map mechanism.

### **Filtering:**

Use the **`filter()`** method of the **`Stream`** interface to select elements from the collection based on a boolean condition. For example:

```java

Stream<T> s = c.stream();
Stream<T> s1 = s.filter(i -> i % 2 == 0);
```

This method is suitable for filtering elements based on a boolean condition.

### Mapping

- To create new objects for each element in the collection based on a specific requirement, use the **`map()`** method of the **`Stream`** interface. For example:

```java

Stream<T> s = c.stream();
Stream<T> s1 = s.map(i -> i + 10);
```

This method transforms each element according to a provided function or lambda expression.

## 2. Processing

### **I.) Processing by `collect()` method**

The **`collect()`** method is used to gather the elements from the stream and add them to the specified collection indicated by the argument.

**Example 1:** Collecting only even numbers from an ArrayList

**Approach-1:** Without Streams

```java
import java.util.*;

class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        for(int i = 0; i <= 10; i++) {
            list1.add(i);
        }
        System.out.println(list1);

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        for(Integer i : list1) {
            if(i % 2 == 0)
                list2.add(i);
        }
        System.out.println(list2);
    }
}

```

**Approach-2:** With Streams

```java
import java.util.*;
import java.util.stream.*;

class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        for(int i = 0; i <= 10; i++) {
            list1.add(i);
        }
        System.out.println(list1);

        List<Integer> list2 = list1.stream()
                                   .filter(i -> i % 2 == 0)
                                   .collect(Collectors.toList());
        System.out.println(list2);
    }
}

```

Transform each element of the list to uppercase.

```java
import java.util.*;
import java.util.stream.*;

class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("rvk"); 
        list.add("rk"); 
        list.add("rkv"); 
        list.add("rvki"); 
        list.add("rvkir"); 
        System.out.println(list); 

        List<String> list2 = list.stream()
                                 .map(s -> s.toUpperCase())
                                 .collect(Collectors.toList()); 
        System.out.println(list2); 
    } 
}

```

### II.) Processing by count() method

This method returns the number of elements present in the stream.

```java
public long count()
```

Example:

```java
long count = l.stream().filter(s -> s.length() == 5).count();
System.out.println("The number of 5 length strings is: " + count);
```

### III.)  Processing by sorted() method

If we want to sort the elements present inside the stream, we should use the sorted() method.
Sorting can be either default natural sorting order or a customized sorting order specified by a comparator.

- sorted() - default natural sorting order
- sorted(Comparator c) - customized sorting order
  Example:

```java
List<String> l3 = l.stream().sorted().collect(Collectors.toList());
System.out.println("According to default natural sorting order: " + l3);

List<String> l4 = l.stream().sorted((s1, s2) -> -s1.compareTo(s2)).collect(Collectors.toList());
System.out.println("According to customized sorting order: " + l4);
```

### IV.) Processing by min() and max() methods

- min(Comparator c): Returns the minimum value according to the specified comparator.
- max(Comparator c): Returns the maximum value according to the specified comparator.
  Example:

```java

String min = l.stream().min((s1, s2) -> s1.compareTo(s2)).get();
System.out.println("Minimum value is: " + min);

String max = l.stream().max((s1, s2) -> s1.compareTo(s2)).get();
System.out.println("Maximum value is: " + max);

```

### V.) forEach() method

This method does not return anything. It takes a lambda expression as an argument and applies that lambda expression to each element present in the stream.

Example:

```java
l.stream().forEach(s -> System.out.println(s));
l3.stream().forEach(System.out::println);
```

```java
import java.util.*;
import java.util.stream.*;

class Test1 {
    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<Integer>();
        l1.add(0); l1.add(15); l1.add(10); l1.add(5); l1.add(30); l1.add(25); l1.add(20);
        System.out.println(l1);
        ArrayList<Integer> l2 = l1.stream().map(i -> i + 10).collect(Collectors.toList());
        System.out.println(l2);
        long count = l1.stream().filter(i -> i % 2 == 0).count();
        System.out.println(count);
        List<Integer> l3 = l1.stream().sorted().collect(Collectors.toList());
        System.out.println(l3);
        Comparator<Integer> comp = (i1, i2) -> i1.compareTo(i2);
        List<Integer> l4 = l1.stream().sorted(comp).collect(Collectors.toList());
        System.out.println(l4);
        Integer min = l1.stream().min(comp).get();
        System.out.println(min);
        Integer max = l1.stream().max(comp).get();
        System.out.println(max);
        l3.stream().forEach(i -> System.out.println(i));
        l3.stream().forEach(System.out::println);
    }
}

```

### VI.) toArray() method

We can use the toArray() method to copy elements present in the stream into a specified array.

```java
Integer[] ir = l1.stream().toArray(Integer[]::new);
for (Integer i : ir) {
    System.out.println(i);
}
```

### VII.) Stream.of() method

This method can be used to apply a stream for a group of values and for arrays.
Example:

```java
Stream s = Stream.of(99, 999, 9999, 99999);
s.forEach(System.out::println);

Double[] d = {10.0, 10.1, 10.2, 10.3};
Stream s1 = Stream.of(d);
s1.forEach(System.out::println);

```

# Date and Time API: (Joda-Time API)

Until Java 1.7, the classes in the **`java.util`** package for handling Date and Time (like **`Date`**, **`Calendar`**, **`TimeZone`**, etc.) were not satisfactory in terms of convenience and performance. To address this issue in Java 1.8, Oracle introduced the Joda-Time API, developed by joda.org, which is available in Java in the form of the **`java.time`** package.

### **Program to Display System Date and Time**

```java
import java.time.*;

public class DateTime {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        LocalTime time = LocalTime.now();
        System.out.println(time);
    }
}
```

Output:

```makefile
2015-11-23
12:39:26.587
```

After obtaining a **`LocalDate`** object, we can call methods such as **`getDayOfMonth()`**, **`getMonthValue()`**, and **`getYear()`** to retrieve day, month, and year values separately.

### **Program to Retrieve Date Components**

```java

import java.time.*;

class Test {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        int dd = date.getDayOfMonth();
        int mm = date.getMonthValue();
        int yy = date.getYear();
        System.out.println(dd + "..." + mm + "..." + yy);
        System.out.printf("\n%d-%d-%d", dd, mm, yy);
    }
}
```

Similarly, after obtaining a **`LocalTime`** object, we can call methods such as **`getHour()`**, **`getMinute()`**, **`getSecond()`**, and **`getNano()`** to retrieve time components.

### **Program to Retrieve Time Components**

```java

import java.time.*;

class Test {
    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        int h = time.getHour();
        int m = time.getMinute();
        int s = time.getSecond();
        int n = time.getNano();
        System.out.printf("\n%d:%d:%d:%d", h, m, s, n);
    }
```

To represent both Date and Time, we use the **`LocalDateTime`** object.

```java

LocalDateTime dt = LocalDateTime.now();
System.out.println(dt);
```

Output: **`2015-11-23T12:57:24.531`**

A specific Date and Time can be represented using the **`LocalDateTime`** object as follows:

```java
LocalDateTime dt1 = LocalDateTime.of(1995, Month.APRIL, 28, 12, 45);
System.out.println(dt1);
```

```java
LocalDateTime dt1 = LocalDateTime.of(1995, 04, 28, 12, 45);
System.out.println(dt1);
System.out.println("After six months:" + dt.plusMonths(6));
System.out.println("Before six months:" + dt.minusMonths(6));
```

### TO Represent Zone:

Zone can be represented using the **`ZoneId`** object.

```java
import java.time.*;

class ProgramOne {
    public static void main(String[] args) {
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
    }
}
```

A **`ZoneId`** can be created for a particular zone as follows:

```java
ZoneId la = ZoneId.of("America/Los_Angeles");
ZonedDateTime zt = ZonedDateTime.now(la);
System.out.println(zt);
```

The **`Period`** object is used to represent a quantity of time.

```java

LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(1989, 06, 15);
Period p = Period.between(birthday, today);
System.out.printf("age is %d years %d months %d days", p.getYears(), p.getMonths(), p.getDays());
```

### **Program to Check Leap Year**

```java
import java.time.*;

public class LeapYear {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Year y = Year.of(n);
        if (y.isLeap())
            System.out.printf("%d is a Leap year", n);
        else
            System.out.printf("%d is not a Leap year", n);
    }
}

```

[All Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/tree/main/Java%208/src/Code/Basic)
