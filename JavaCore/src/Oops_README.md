# Oops

## Table of Contents

1. [Abstraction](#abstraction)
   - [Abstract Classes:](#-1-abstract-classes)
   - [Interface](#2-interface-)
2. [Encapsulation](#-encapsulation)
   - [Tightly Encapsulated Class](#-tightly-encapsulated-class-in-java)
3. [Inheritance](#inheritance)
   - [Inheritance Behaviour](#-conclusion-on-inheritance-behavior-in-java)
   - [Multiple Inheritance](#multiple-inheritance-)
4. [HAS-A Relationship](#-has-a-relationship)
   - [Composition](#-composition)
   - [Aggregation](#aggregation)
5. [Method Signature in Java](#-method-signature-in-java)
6. [Polymorphism concept](#polymorphism)
   - [Method Overloading (Compile-time Polymorphism) ](#1-overloading-compile-time-polymorphism)
     - [Rules for Overloading](#-cases-in-method-overloading) 
       - [Method Overriding (Runtime Polymorphism)](#2-overriding-runtime-polymorphism)
     - [Rules for Overriding](#rules-for-overridng-)
     - [Method Hiding](#-what-is-method-hiding)
7. [Overloading vs Overriding](#-overloading-vs-overriding)
8. [Different ways to create Object in Java](#-how-many-ways-can-we-create-an-object-in-java)
9. [Constructor](#constructor)
10. [Constructor vs Instance Block](#constructor-vs-instance-block)
11. [Rules to write Constructor :→](#rules-to-write-constructor-→)
12. [Default Constructor :→](#default-constructor-→)
    - [Prototype of Default Constructor :→](#prototype-of-default-constructor-→)
13. [Super() vs this()](#super-vs-this)
14. [Overloaded Constructors :→](#overloaded-constructors-→)
    - [We can't create object for abstract class but abstract class can contain constructor what is the need ?](#we-cant-create-object-for-abstract-class-but-abstract-class-can-contain-constructor-what-is-the-need-)
    - [Which of the following statement is true ?](#which-of-the-following-statement-is-true-)
    - [Note :→](#note-→)
15. [Singleton Class](#singleton-class)
    - [Creation of our own Singleton Class](#creation-of-our-own-singleton-class)
    - [We are not allowed to create child class but class is not final , How it is Possible ?](#we-are-not-allowed-to-create-child-class-but-class-is-not-final--how-it-is-possible-)
16. [Factory Method](#factory-method)
17. [Static Control Flow](#static-control-flow)
18. [RIWO state](#riwo-state)
19. [Static control flow parent to child relationship :→](#static-control-flow-parent-to-child-relationship-→)
20. [Static block](#static-block)
21. [Instance Control Flow](#instance-control-flow)
22. [Instance control flow in Parent to Child relationship](#instance-control-flow-in-parent-to-child-relationship)
23. [Type Casting :→](#type-casting-→)
    - [Type Casting Syntax](#type-casting-syntax)
    - [Runtime Checking](#runtime-checking)
24. [Cohesion](#cohesion)

---

# Abstraction

**Abstraction** is the process of hiding complex internal implementation details and exposing only the essential features to the user.  
It allows users to interact with a system without needing to understand *how* it works internally — only *what* it does.

> In simpler terms, abstraction shows **what** an object does, not **how** it does it.

###### Example:

> **ATM GUI:**  
> An ATM screen shows essential options like *withdraw*, *deposit*, or *check balance* — while hiding the internal operations like database queries, network calls, or transaction processing logic.

This separation of **interface and implementation** helps in simplifying complex systems, improving usability, and promoting cleaner code architecture.

#### ✅ Advantages of Abstraction

1. **Simplifies Complexity**  
   Users interact only with the **essential features**, not the internal workings.

2. **Enhances Security**  
   Hides internal implementation details, reducing the chance of misuse or errors.

3. **Improves Code Maintainability**  
   Changes to internal logic **don’t affect external code** that uses the abstraction.

4. **Encourages Reusability**  
   Abstract classes and interfaces can be reused across multiple implementations.

5. **Supports Loose Coupling**  
   Code becomes less dependent on concrete implementations, making it easier to extend and modify.

Abstraction can be achieved using : Abstrcat classes and Interface

## 🧱 **1.) Abstract Class:**

An **abstract class** in Java is a class that **cannot be instantiated directly**, meaning you cannot create objects from it.

It may contain:

- **Abstract methods** (methods without a body), which **must** be implemented by its subclasses.

- **Concrete methods** (with a body), which provide **default behavior** shared by all subclasses.

Abstract classes are used to define **common structure and behavior** across multiple related classes, serving as a partial blueprint.

```java
abstract class Shape {
    abstract void draw(); // Abstract method (does not have a body)
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing Circle");
    }
}
```

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/AbstractionExample.java)

## 2.) Interface :

An **interface** in Java is a blueprint of a class that defines a set of **abstract behaviors** (methods) that a class must implement. It provides a way to achieve **full abstraction** and supports **multiple inheritance of type**.

> A class can **implement multiple interfaces**, allowing it to inherit behaviors from multiple sources — something not possible with classes alone.

###### ✅ Interface Key Features:

- All methods are **abstract by default** (until Java 7).

- From **Java 8**, interfaces can also have:
  
  - `default` methods (with body)
  
  - `static` methods

- From **Java 9**, interfaces can have:
  
  - `private` methods (to support reuse inside default methods)

- All fields in an interface are **implicitly**:  
  `public static final` (i.e., constants)

- Interfaces **cannot have constructors or instance variables**.

[Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/AbstractionExampleUsingInterface.java)

---

## 🆚 Abstract Class vs Interface in Java

| 🔹 **Feature**                    | 🧱 **Abstract Class**                               | 🧩 **Interface**                                                            |
| --------------------------------- | --------------------------------------------------- | --------------------------------------------------------------------------- |
| ✅ **Purpose**                     | Partial abstraction + shared behavior               | Full abstraction + multiple type inheritance                                |
| 🧪 **Methods Support**            | Abstract + Concrete methods                         | Abstract (Java 7 and below) <br> + `default`, `static`, `private` (Java 8+) |
| 📦 **Variables**                  | Instance, static, final, etc.                       | Only `public static final` (constants)                                      |
| 🛠 **Constructors**               | ✅ Allowed                                           | ❌ Not allowed                                                               |
| ♻️ **Multiple Inheritance**       | ❌ Not supported                                     | ✅ Supported                                                                 |
| 🔒 **Access Modifiers (Methods)** | `public`, `protected`, `default`, `private`         | Implicitly `public` (until Java 8)                                          |
| 🧬 **Code Reusability**           | ✅ Yes (via concrete methods)                        | ✅ From Java 8 (`default`, `static` methods only)                            |
| ⚙️ **Use Case**                   | Shared base logic + partial abstraction             | Define a contract across unrelated classes                                  |
| 🔁 **Inheritance Syntax**         | `extends` (only one class)                          | `implements` (multiple interfaces)                                          |
| 🔑 **Keyword**                    | `abstract class`                                    | `interface`                                                                 |
| 🔧 **Partial Implementation?**    | ✅ Yes                                               | ❌ Not before Java 8                                                         |
| 🧱 **State Management**           | ✅ Supports instance/static fields                   | ❌ Only constants allowed                                                    |
| ⏩ **Evolution Over Versions**     | More rigid, changes require inheritance or refactor | More flexible post-Java 8 & 9 (default, static, private methods)            |

---

## 🔍 When to Use Interface or Abstract class?

| Situation                                                        | Best Choice       |
| ---------------------------------------------------------------- | ----------------- |
| You want to provide **common base logic** to subclasses          | 🧱 Abstract Class |
| You want to **define a contract** for multiple unrelated classes | 🧩 Interface      |
| You need to support **multiple inheritance**                     | 🧩 Interface      |
| You want to evolve your class in a controlled hierarchy          | 🧱 Abstract Class |

---

### 🚫 Why Interfaces Don't Have Constructors?

Because interfaces **cannot hold instance state** — and constructors are meant to **initialize instance variables**.  
But **methods in interfaces** can still accept parameters and use them **at the time of method call**, not at the time of object construction.



# 🔐 Encapsulation

Encapsulation is the act of bundling data (fields) and the methods that operate on that data into a single unit, usually a class, while restricting direct access to the data using access control modifiers like `private`, `protected`, and `public`.

When a class follows the principles of data hiding and abstraction, it is called an **encapsulated class**.

###### Analogy:

> **Encapsulation is like a TV remote:**  
> You don’t open the remote to press the buttons directly on its circuit board; instead, you press buttons on the outside to control the TV.  
> The internal electronics (data) are hidden, but you have a controlled interface (methods) to operate the TV safely and easily.

1. **`Private Fields:`** The attributes of a class are often declared as `private`, meaning they can only be accessed within the class itself.
2. **`Public Methods` (Getters and Setters):** Public methods are provided to allow controlled access to the private attributes. These methods are often referred to as getter methods (for retrieving the values) and setter methods (for modifying the values).

[Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Encapsulation.java)

#### ⭐ Main Advantages of Encapsulation

1. **Data Hiding**  
   Protects internal state by restricting direct access to fields using access modifiers (`private`, `protected`).

2. **Improved Security**  
   Prevents external interference and misuse by exposing only what’s necessary via controlled methods (getters/setters).

3. **Code Maintainability**  
   Internal changes don’t affect external code as long as the public interface remains the same.

4. **Validation Control**  
   Allows you to add logic in setters to validate data before updating fields.

5. **Modularity and Reusability**  
   Promotes well-defined, self-contained classes — easier to reuse, test, and debug.

## 🔐 Tightly Encapsulated Class in Java

A class is said to be **tightly encapsulated** if **all its variables (fields) are declared as `private`** — regardless of whether getter and setter methods exist or not.

> ✅ The focus is only on **access modifiers of fields**, not on whether public accessors are provided.

---

✅ Key Rule:

> **If even one field in the class (or any of its parent classes) is not private, the class is *not* tightly encapsulated.**

This is because child classes inherit all **non-private** fields from parent classes — breaking encapsulation at the root.

---

##### 🧪 Example of a Tightly Encapsulated Class:

```java
class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }
}
```

- `balance` is private → ✅ tightly encapsulated

- Whether a getter exists or not → doesn't matter

---

##### ❌ Examples (Not Tightly Encapsulated):

```java
class A {
    int x = 10; // ❌ Not private
}

class B extends A {
    private int y = 20; // ✅ private, but inherits x (non-private) → ❌
}

class C extends B {
    private int z = 30; // ✅ private, but again inherits x → ❌
}
```

📌 **None of these are tightly encapsulated** because class `A` has a non-private field, and all subclasses inherit it.

---

###### 📌 Summary:

- ✅ All fields must be `private`.

- 🚫 It doesn’t matter if the class has `public`/`protected` methods or not.

- 🧬 If the **parent class is not tightly encapsulated**, **no subclass can be tightly encapsulated** either.

---

# Inheritance

**Inheritance** in object-oriented programming allows a class (called the **child class** or **subclass**) to inherit the **properties** (fields) and **behaviors** (methods) of another class (called the **parent class** or **superclass**).  
This enables **code reuse**, **extensibility**, and a natural hierarchy between types.

---

###### Inheritance Key Concepts:

- **`IS-A Relationship:`**  
  Inheritance models an *"is-a"* relationship.  
  For example:  
  `Dog extends Animal` → A Dog **is an** Animal.

- **`extends` Keyword:**  
  In Java, the `extends` keyword is used to create a subclass that inherits from a superclass.
  
  ---

###### ⭐ Main Advantages of Inheritance :

- **🔁 Code Reusability**  
  Common code written in the parent class can be reused by child classes, reducing redundancy and development effort.

- **🧬 Method Overriding**  
  Child classes can redefine parent class methods to provide specific behavior, enabling runtime polymorphism.

- **🧠 Polymorphism Support**  
  Parent references can point to child objects, allowing flexible, scalable, and loosely coupled code.

- **🧩 Logical Hierarchy**  
  Establishes a clear and maintainable structure that reflects real-world relationships between entities.

- **🛠️ Easier Maintenance & Scalability**  
  Changes made in the parent class automatically apply to all child classes, streamlining updates.

- **🌿 Extensibility**  
  New features can be added to child classes without modifying existing code, supporting open/closed principle.

- **🧱 Framework Foundation**  
  Forms the basis for many frameworks and design patterns where base functionality is extended by subclasses.

---

###### 📺 Real-world Analogy:

> **Inheritance is like a child inheriting traits from parents.**  
> For example, a child may inherit eye color or language from their parents — but can also develop their own personality.  
> Similarly, a subclass can use parent behavior **as-is**, or **override** it with its own version.

[Inheritance Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/InheritanceExample.java)

### ✅ Conclusion on Inheritance Behavior in Java

1. **Child Inherits from Parent, Not the Other Way Around**
   
   - Whatever the parent class has is automatically available to the child.
   
   - But the child class's specific members are **not accessible** via the parent class.
   
   - So:
     
     - A **child reference** can access **both parent and child members**.
     
     - A **parent reference** can access **only parent members**, even if it holds a child object.

2. **Parent Reference Can Hold Child Object**
   
   - This is called **upcasting**.
   
   - However, using the parent reference, we can **only access parent class methods** — not child-specific methods.

3. **Child Reference Cannot Hold Parent Object**
   
   - This is not allowed — it's a **type mismatch** and results in a compile-time error.

---

🧪 Example: Loan System

Let’s say multiple loan types (Housing, Vehicle, Education) share common operations. These shared methods should be defined in a base class, like this:

```java
class Loan {
    // Common methods for all loan types
}

class HousingLoan extends Loan {
    // Specific methods for housing loan
}

class EducationLoan extends Loan {
    // Specific methods for education loan
}
```

This structure promotes **code reuse** and aligns with the **“is-a” relationship** principle of inheritance:

> `HousingLoan` **is a** `Loan`, `EducationLoan` **is a** `Loan`.

#### Inheritance System-Wide Examples:

- In Java, the most commonly required functionality for all classes is defined in the **`Object`** class. Hence, `Object` serves as the **root class** for all Java classes.

- Similarly, for all exceptions and errors, the common functionality is defined in the **`Throwable`** class, making it the **root of the exception hierarchy** in Java.

### Multiple Inheritance :

**Multiple Inheritance** refers to a situation where a class inherits from **more than one parent class** at the same level.  
In other words, a single subclass has **multiple direct superclasses**.

### 💥 Why java won't provide support for multiple inheritance?

Java **does not allow multiple inheritance with classes** to avoid ambiguity and complexity in method resolution — specifically to prevent the infamous **Diamond Problem**.

---

###### 🧱 The Diamond Problem:

```java
        A
      /   \
     B     C
      \   /
        D
```

- Imagine `Class B` and `Class C` both inherit from `Class A`.

- Now, `Class D` inherits from both `B` and `C`.

> ❓ If `Class A` has a method `display()`, and both `B` and `C` override it…  
> Then `D.display()` — **which version should be called?**  
> This is the **diamond problem**: **ambiguity** and **unpredictable behavior**.

###### ✅ Interfaces to the Rescue:

Java **does allow multiple inheritance via interfaces** because:

- Interfaces (until Java 7) could only declare method signatures — **no implementation**, so no conflict.

- From **Java 8 onwards**, default methods are allowed in interfaces, but Java still resolves conflicts **explicitly** using rules like:
  
  - Subclass overrides > implemented interface default > compiler error if ambiguous.

# 🔗 HAS-A Relationship

A **HAS-A relationship** represents an **association** between two classes, where **one class contains a reference to another class**. This is typically implemented through **composition**.

---

## 🔧 Composition

> **Composition** is a design principle where one object is composed of one or more other objects.  
> It represents a **strong association**, where the contained object **cannot exist independently** of the container.

---

##### ✅ HAS-A Relationship Key Points:

- A HAS-A relationship means one class *"has"* another as a part of itself.

- It is implemented using **member variables** that hold references to other objects.

- The contained object’s **lifecycle is managed by** the container class.

- This forms a strong bond: if the container is destroyed, so is the contained part.

---

### 🔍 Real-world Analogy:

> A **Car** HAS-A **Engine**.  
> You can’t have a working car without its engine, and the engine belongs to that specific car.  
> The car controls the lifecycle of the engine.

###### **HAS-A Relationship Code Example:**

```java
class Engine {
    / Engine behavior
}

class Car {
    private Engine engine;  // HAS-A relationship: Car HAS-A Engine

    public Car(Engine engine) {
        this.engine = engine;
    }

   // Other car logic
}
```

- `Car` is the **composite** (container) class.
- `Engine` is the **component** (contained) class.

Without an existing car, the engine cannot exist. The **`Car`** manages the creation and destruction of the **`Engine`**, and the lifecycle of the **`Engine`** is tightly bound to the lifecycle of the **`Car`**. This is a clear example of a strong association through composition.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Composition.java)

## Aggregation

**Aggregation** represents a *"HAS-A"* relationship where one class contains a reference to another class, **but the contained object can exist independently of the container**.

> 🔄 In simpler terms:  
> Even if the container is destroyed, the contained object **can still exist**.

---

###### 🔍 Aggregation Key Points:

- Aggregation is a **weaker form of association** compared to composition.

- The **lifecycle** of the contained object is **not tightly bound** to the container.

- The contained object can be **shared** among multiple containers.

- It promotes **flexibility** and **loose coupling** between classes.

---

###### 💡 Aggregation Analogy:

> **A teacher and a department**:  
> A `Teacher` can belong to a `Department`, but if the department is removed, the teacher still exists — possibly in another department.

---

###### ✅ Summary:

> Use **aggregation** when:
> 
> - Objects have an ownership relationship,
> 
> - But **independent existence** is allowed and expected.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Aggregation.java)

### 🔍 Core Difference: Composition vs Aggregation

| Criteria                 | Composition 🧱                                   | Aggregation ⚙️                                |
| ------------------------ | ------------------------------------------------ | --------------------------------------------- |
| **Lifecycle Dependency** | Contained object **cannot** exist independently. | Contained object **can** exist independently. |
| **Ownership**            | Strong ownership — container *owns* the part.    | Weak ownership — container *uses* the part.   |
| **Used For**             | “Part-of” relationships with tight coupling.     | “Has-a” relationships with looser coupling.   |
| **Example**              | Heart inside a human 🫀                          | Student in a university 🎓                    |

# 🧾 Method Signature in Java

In Java, the **method signature** is composed of:

> 🔹 **Method name**  
> 🔹 **Parameter types (in order)**

```java
public void methodOne() {}
public int methodOne() { return 10; }  // ❌ Compile-time error
```

---

###### 🚫 Return Type Is *Not* Part of the Signature

Even if two methods differ only by return type, it causes a **compile-time error** — because their signatures are considered the same.

```java
public void methodOne() {}
public int methodOne() { return 10; }  // ❌ Compile-time error
```

---

###### 🔍 Why Is Signature Important?

- The **compiler uses method signature** to **resolve method calls**.

- Ensures **method overloading** is unambiguous.

---

### ✅ Valid Example

```java
class Test {
    public void m1(double d) { }
    public void m2(int i) { }

    public static void main(String[] args) {
        Test t = new Test();
        t.m1(10.5);   // OK
        t.m2(10);     // OK
        t.m3(10.5);   // ❌ Compile-time Error: m3 not defined
    }
}
```

---

### 📌 Important Rule

> **Within the same class**, you **cannot** define multiple methods with the same signature — even if their return types differ.

# Polymorphism

**Polymorphism** means *"many forms."*  
It allows the same name or reference to represent different behaviors or objects, depending on context.

---

### ✅ Examples of Polymorphism:

- **Same method name, with different parameter types**  
  👉 *(Compile-Time Polymorphism / Method Overloading)*

```java
Math.abs(int i);
Math.abs(long l);
Math.abs(float f);
Math.abs(double d);
```

We can use the same `abs()` method name for different data types.  
The compiler resolves which version to call based on the argument types.

---

- **Same parent reference used for different object types**  
  👉 *(Runtime Polymorphism / Interface or Inheritance-based)*

```java
List l;

l = new ArrayList<>();
l = new LinkedList<>();
l = new Vector<>();
l = new Stack<>();
```

We can use the same `List` reference to hold different child class objects.  
This enables flexibility and decoupling in code design.

###### Polymorphism Analogy

> A **boy begins love** with the word **“friendship”**,  
> while a **girl ends love** with the same word — **“friendship.”**
> 
> The **word is the same**, but the **intention is different**.  
> That, my friend, is the **essence of Polymorphism** —  
> **Same name, different behavior.** 💔➡️❤️

---

There are two main types of polymorphism in Java: compile-time polymorphism (also known as static or method overloading) and runtime polymorphism (also known as dynamic or method overriding).

```java
                           Polymorphism 
                               / \
                              /   \    
                             /     \
                            /       \
                           /         \
                          /           \
                         /             \
                        /               \
                       /                 \
                      /                   \
 Compiletime/static/earlybinding        Runtime/dynmic/latebinding 
              / \                                  |
             /   \                                 |
            /     \                                |
           /       \                               |
          /         \                              |  
   Overloading   Methodhiding                  Overiding
```

## 1.) ⚡Overloading (Compile-time Polymorphism)

- Method **overloading** occurs when a class defines **multiple methods with the same name** but with **different parameter lists** (type, number, or order).

- In **method overloading**, the **compiler** is responsible for method resolution (decision) based on the **reference type** — **not** the runtime object.  
  Hence, **overloading** is also known as:
  
  - **Compile-time Polymorphism**
  
  - **Static Polymorphism**
  
  - **Early Binding**

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

- In this example, both methods are named `add` but handle different data types (int vs. double).

- The compiler chooses the appropriate version based on the argument types used during the method call.

> ✅ This form of polymorphism improves **readability**, supports **method specialization**, and avoids the need for different method names for similar logic.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverloadingExample.java)

---

### Overloading key concepts :-->

- Return type **is not** part of method signature.

- Overloaded methods must differ by **argument types/order/number**, not return type.

- If an exact match isn’t found, Java performs **automatic type promotion**.

```java
byte -> short 
             \
              int -> long -> float -> double
            /
         char               
```

- Method resolution is always based on the **reference type**, not the runtime object.

### 🧪 CASES in Method Overloading

### ⚡ Case 1: **Automatic Type Promotion**

```java
class Test {
  public void methodOne(int i) { System.out.println("int"); }
  public void methodOne(float f) { System.out.println("float"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne('a');     // char → int → int method
    t.methodOne(10L);     // long → float → float method
    t.methodOne(10.5);    // double → no match → ❌ Compile-Time Error
  }
}
```

> Java tries to promote types step by step to match a method.  
> If no match even after all promotions, it gives a compile-time error.

---

### 🧊 Case 2: **Object vs String Overload**

```java
class Test {
  public void methodOne(int i) { System.out.println("int"); }
  public void methodOne(float f) { System.out.println("float"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne('a');     // char → int → int method
    t.methodOne(10L);     // long → float → float method
    t.methodOne(10.5);    // double → no match → ❌ Compile-Time Error
  }
}
```

> - ✅ **Exact match always gets the highest priority** during method resolution.
> 
> - ✅ **If an exact match isn't found**, Java will look for **compatible types**—but:
>   
>   - **Child class types are preferred over parent class types** during this resolution.

---

### ❗ Case 3: **Ambiguity with Same Level Types**

```java
class Test {
  public void methodOne(String s) { System.out.println("String"); }
  public void methodOne(StringBuffer sb) { System.out.println("SB"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne(null);   // ❌ Compile-Time Error — ambiguous
  }
}
```

> If both candidates are equally specific, Java throws **ambiguity error**.

```java
                            Object
                             /  \
                            /    \
                           /      \
                          /        \   
                      String    StringBuffer     
```

### 🔄 Case 4: **Same Number of Args, Different Types**

```java
class Test {
  public void methodOne(int i, float f) { System.out.println("int-float"); }
  public void methodOne(float f, int i) { System.out.println("float-int"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne(10, 10.5f);  // int-float
    t.methodOne(10.5f, 10);  // float-int
    t.methodOne(10, 10);     // ❌ Compile-Time Error — ambiguous
  }
}
```

> Same number of arguments but type order causes ambiguity if both are applicable.

---

### 🌌 Case 5: **Var-Args vs Fixed Args**

```java
class Test {
  public void methodOne(int i) { System.out.println("Fixed"); }
  public void methodOne(int... i) { System.out.println("Var-arg"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne();           // Var-arg
    t.methodOne(10);         // Fixed
    t.methodOne(10, 20);     // Var-arg
  }
}
```

> - In general, **var-arg methods have the *lowest* priority** during method resolution.
> 
> - If **no other method matches**, **only then** the var-arg method will be chosen.
> 
> - It behaves **almost like the `default` case in a `switch` statement** — used as a *fallback*

---

### 🧬 Case 6: **Parent vs Child References**

```java
class Animal {}
class Monkey extends Animal {}

class Test {
  public void methodOne(Animal a) { System.out.println("Animal"); }
  public void methodOne(Monkey m) { System.out.println("Monkey"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne(new Animal());       // Animal 
    t.methodOne(new Monkey());       // Monkey 

    Animal a = new Monkey();
    t.methodOne(a);                  // Animal (based on ref type)
  }
}
```

> In **overloading**, **reference type** is used for method resolution, not the actual object.

---

## 2. Overriding (Runtime Polymorphism):

- **Method Overriding** is a feature in Java that allows a **subclass (child class)** to provide its **own specific implementation** of a method that is already defined in its **superclass (parent class)**.

- The method in the **parent class** that is being redefined is called the **overridden method**.

- The method in the **child class** that redefines the parent’s method is called the **overriding method**.

- The **method call is resolved at runtime** based on the **actual (runtime) type of the object**, not the reference type. This behavior is known as **runtime polymorphism**.

- To explicitly indicate that a method in the subclass is intended to **override** a method in the superclass, we use the **`@Override`** annotation.  
  This helps the compiler catch mistakes like mismatched method signatures or typos.

- Example:
  
  ```java
  class Parent {
      public void property() {
          System.out.println("Cash + Land + Gold");
      }
  
      public void marry() {
          System.out.println("Subbalakshmi");
      }
  }
  
  class Child extends Parent {
      @Override
      public void marry() {
          System.out.println("Trisha / Nayanthara / Anushka");
      }
  }
  
  public class Test {
      public static void main(String[] args) {
          Parent p = new Parent();
          p.marry(); // Subbalakshmi
  
          Child c = new Child();
          c.marry(); // Trisha / Nayanthara / Anushka
  
          Parent p1 = new Child();
          p1.marry(); // Trisha / Nayanthara / Anushka
      }
  }
  ```

- ✅ Method resolution is **based on runtime object**, not reference type.  
  This is why `p1.marry()` invokes the **child's** method.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverridingExample.java)

### 🧩Rules for Overridng :

---

### 1.)   Signature Must Match

- Method **name and parameter types** must match exactly.

```java
// Valid overriding
void display(int x)

// Invalid (different signature)
int display(float x)
###
```

---

### 2. ✅ Covariant Return Types (Since Java 1.5)

- Allowed: Child class method returns a **subtype** of parent method's return type.

```java
class Parent {
    public Object getData() { return null; }
}

class Child extends Parent {
    public String getData() { return null; }
}
```

> ❌ Not allowed for primitives. Only applicable for reference types.

---

### 3. ❌ Private Methods Cannot Be Overridden

- They're not inherited → re-declaring creates a **new method**, not overriding.

---

### 4. ❌ Final Methods Cannot Be Overridden

```java
class Parent {
    public final void show() {}
}

class Child extends Parent {
    public void show() {} // ❌ Compile-time error
}
```

---

### 5. ✅ You Can Override a Non-final Method as Final

```java
class Parent {
    public void show() {}
}

class Child extends Parent {
    public final void show() {} // ✅ Legal
}
```

---

### 6. ✅ You Must Override Abstract Methods

```java
abstract class Parent {
    public abstract void show();
}

class Child extends Parent {
    public void show() {} // ✅ Must override
}
```

---

### 7. ⚠️ You Can Make a Concrete Method Abstract in Subclass

- Useful when **blocking further access** to parent method in next-level subclasses.

```java
class Parent {
    public void show() {}
}

abstract class Child extends Parent {
    public abstract void show(); // ✅ Legal
}
```

---

### 🔐 Access Modifier Restrictions While Overriding in Java

When overriding a method, the access modifier in the child class **cannot be more restrictive** than in the parent class.

| Parent Modifier | Allowed in Child Class           | ✅ / ❌ | Reason                                 |
| --------------- | -------------------------------- | ----- | -------------------------------------- |
| `private`       | ❌ Cannot override                | ❌     | Not inherited; method is class-private |
| *(default)*     | `default`, `protected`, `public` | ✅     | Visible only within the same package   |
| `protected`     | `protected`, `public`            | ✅     | Wider visibility allowed               |
| `public`        | `public` only                    | ✅     | Already the widest access              |

---

### 💡 Key Notes

- A `private` method is **not inherited**, so **overriding is not possible** — defining a method with the same name in the child is **method hiding**, not overriding.

- You can **increase visibility** (e.g., `protected` → `public`), but you **cannot decrease** it (e.g., `public` → `protected`).
  
  ```java
   // scope of access modifier
   private < default < protected < public
  ```

- The compiler enforces this to maintain **Liskov Substitution Principle** — anywhere a parent class is used, the child class should seamlessly fit in.

#### Other Modifiers

- ✅ `synchronized`, `strictfp`, `native`, etc. — **do not affect** overriding.

- ✅ Overriding **native methods** is allowed.

#### 🔄 Dynamic Method Dispatch

> The process where method call is resolved at runtime **based on actual object**, not reference type.

```java
Parent p = new Child();
p.show(); // Child's method called at runtime
```

### Overriding vs Overloading Behavior

```java
// Overloading
Reference Type → Compiler decides at compile time

// Overriding
Runtime Object → JVM decides at runtime
```

---

### 🔚 Overiding Summary

- ✅ Method Signature must match

- ✅ Return type can be covariant

- ❌ Can't override private/final methods

- ✅ Must override abstract methods

- ✅ Can override concrete method as abstract

- ✅ Overridden method's visibility can't be reduced

- ✅ JVM handles method resolution based on runtime object

- ✅ Enables runtime polymorphism (dynamic dispatch)

## ⚠️ Overriding vs  static method

###### ❌ Can We Override Static Methods?

**No. Static methods cannot be overridden in Java.**

---

**✅ Why?**

- **Overriding** is based on **runtime polymorphism**, which depends on the **object**.

- **Static methods** belong to the **class**, not the object. So they are **resolved at compile time**.

- Therefore, **static methods can’t be overridden**, but they **can be hidden**.

---

###### 🔍 CASE 1: Trying to Override a Static Method as Non-static ❌

```java
class Parent {
    public static void methodOne() {
        System.out.println("Parent static method");
    }
}

class Child extends Parent {
    public void methodOne() { // ❌ non-static
        System.out.println("Child instance method");
    }
}
```

🔴 **Compile-time Error:**

```java
methodOne() in Child cannot override methodOne() in Parent; 
overridden method is static
```

---

###### 🔍 CASE 2: Trying to Override a Non-static Method as Static ❌

```java
class Parent {
    public void methodOne() {
        System.out.println("Parent instance method");
    }
}

class Child extends Parent {
    public static void methodOne() { // ❌ static
        System.out.println("Child static method");
    }
}
```

🔴 **Compile-time Error:** Same reason — method type mismatch (instance vs static).

---

###### ✅ CASE 3: Defining Static Method in Both Parent and Child (No Error)

```java
class Parent {
    public static void methodOne() {
        System.out.println("Parent static method");
    }
}

class Child extends Parent {
    public static void methodOne() {
        System.out.println("Child static method");
    }
}
```

🟢 This is **valid**.  
But this is **not overriding** — it is **method hiding**.

---

### 🤔 What is Method Hiding?

If a static method is **redefined** in a child class (with the same signature), it's called **method hiding**, not overriding.

📌 Resolution is based on **reference type**, not the runtime object.

---

###### 🧪 Example: Method Hiding Behavior

```java
class Parent {
    public static void methodOne() {
        System.out.println("Parent static");
    }
}

class Child extends Parent {
    public static void methodOne() {
        System.out.println("Child static");
    }
}

public class Test {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.methodOne(); // Output: Parent static

        Child c = new Child();
        c.methodOne(); // Output: Child static

        Parent ref = new Child();
        ref.methodOne(); // Output: Parent static ❗
    }
}
```

> 🔎 Even though `ref` refers to a `Child` object, static method is resolved using **reference type** → so `Parent.methodOne()` gets called.

---

###### 🧠 Conclusion

| Static Method Behavior                                                    | Is It Overriding? | Resolved At          |
| ------------------------------------------------------------------------- | ----------------- | -------------------- |
| Child defines static with same signature                                  | ❌ Method Hiding   | Compile Time (Class) |
| Child defines instance method with same signature as static parent method | ❌ Error           | Compile Error        |
| Child defines static method with same signature as instance parent method | ❌ Error           | Compile Error        |

> 🔑 **Static methods are class-level. Overriding works only with instance methods.**

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/MethodHiding.java)

[Complete Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Interf.java)

## Overriding with respect to var-arg method.

A **var-arg method** must be **overridden with another var-arg method** only.

❌ If you override a var-arg method with a **normal method**, it becomes **method overloading**, **not overriding**.

---

🔍 Example 1: **Valid Overriding** (Both are var-arg)

```java
class Parent {
    public void show(int... x) {
        System.out.println("Parent var-arg method");
    }
}

class Child extends Parent {
    @Override
    public void show(int... x) {
        System.out.println("Child var-arg method");
    }
}
```

🟢 **This is valid overriding** — method signatures match (var-arg to var-arg).

---

🔍 Example 2: **Not Overriding** (Var-arg vs Regular Method)

```java
class Parent {
    public void show(int... x) {
        System.out.println("Parent var-arg method");
    }
}

class Child extends Parent {
    public void show(int x) {
        System.out.println("Child normal method");
    }
}
```

⚠️ **This is overloading**, not overriding.

- `show(int x)` is a normal method with a single `int` argument.

- `show(int... x)` is a var-arg method (internally treated as `int[]`).

- Since their parameter types differ, **no overriding happen.

---

###### 🧠 Summary

| Parent Method      | Child Method       | Result        |
| ------------------ | ------------------ | ------------- |
| `void m(int... x)` | `void m(int... x)` | ✅ Overriding  |
| `void m(int... x)` | `void m(int x)`    | ❌ Overloading |

> 🔑 Always match var-arg with var-arg to achieve **true overriding**.

[Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverridingVarAgMethod.java)

## Method Hiding

| Method Overriding                                                                                                                 | Method Hiding                                                                                                               |
| --------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| Both Parent and Child class method should be non-static.                                                                          | Both Parent and Child class method should be static.                                                                        |
| Method Resolution is always taken care by JVM based on runtime objects.                                                           | Method Resolution is always taken care by compiler based on reference type.                                                 |
| Overriding is also considered as Runtime Polymorphism (or) Dynamic Polymorphism (or) late binding.                                | Method hiding is also considered as compile time polymorphism (or) static polymorphism (or) early binding.                  |
| [Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverloadingExample.java) | [Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/MethodHiding.java) |

---

# 🔁 Overloading vs Overriding

| 🔧 **Property**                          | ⚙️ **Overloading**                                      | 🔄 **Overriding**                                            |
| ---------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------------ |
| 1️⃣ Method Name                          | Must be same                                            | Must be same                                                 |
| 2️⃣ Argument Types                       | Must differ (type, number, or order)                    | Must be exactly same                                         |
| 3️⃣ Method Signature                     | Must be different                                       | Must be same                                                 |
| 4️⃣ Return Type                          | No restriction                                          | Must be same until Java 1.4, <br>Co-variant allowed from 1.5 |
| 5️⃣ `private`, `static`, `final` methods | Can be overloaded                                       | **Cannot** be overridden                                     |
| 6️⃣ Access Modifier                      | No restriction                                          | Can't weaken the access modifier                             |
| 7️⃣ Throws Clause                        | No restriction                                          | Checked exceptions must match or be a subclass               |
| 8️⃣ Method Resolution                    | Done by **compiler** at compile-time based on reference | Done by **JVM** at runtime based on object                   |
| 9️⃣ Also Known As                        | Compile-time / Static / Early Binding                   | Runtime / Dynamic / Late Binding                             |

---

## 🧠 **Key Conceptual Difference**

- **Overloading** = Same method name, different arguments (focus on parameter list).

- **Overriding** = Redefining the **same method** of the parent class **in child class** to change behavior.

#### ✅ **Practice: Validity of Methods in Child Class**

Given:

```java
class Parent {
  public void methodOne(int i) throws IOException;
}
```

Which of the following in Child are valid?

| Method in Child                                  | Result    | Reason                                          |
| ------------------------------------------------ | --------- | ----------------------------------------------- |
| `public void methodOne(int i)`                   | ✅ Valid   | Overrides method and removes exception          |
| `private void methodOne()`                       | ✅ Valid   | Overloaded (different signature)                |
| `public native void methodOne(int i)`            | ✅ Valid   | Overrides with native                           |
| `public static void methodOne(double d)`         | ✅ Valid   | Overloaded (different type)                     |
| `public static void methodOne(int i)`            | ❌ Invalid | Tries to override non-static method with static |
| `public static abstract void methodOne(float f)` | ❌ Invalid | abstract + static not allowed                   |

### ✅ Difference Between List vs ArrayList

```java
ArrayList al = new ArrayList();    // Like: Child c = new Child();
List list = new ArrayList();       // Like: Parent p = new Child();
```

| **Aspect**         | `ArrayList al = new ArrayList();`                         | `List l = new ArrayList();`                                        |
| ------------------ | --------------------------------------------------------- | ------------------------------------------------------------------ |
| **Reference Type** | `ArrayList` (Concrete class)                              | `List` (Interface)                                                 |
| **Runtime Object** | `ArrayList`                                               | `ArrayList`                                                        |
| **Flexibility**    | ❌ Tight coupling to `ArrayList`                           | ✅ Flexible: can change to `LinkedList`, `Vector`, etc.             |
| **Access**         | ✅ Can access both `List` and `ArrayList` specific methods | ❌ Can only access methods declared in `List` interface             |
| **Best Use Case**  | When you **know** you'll use only `ArrayList` features    | When you **program to interface**, for flexibility and abstraction |

---

##### 🤔 Why Use: `List l = new ArrayList();`

If we’re creating an `ArrayList`, but limiting ourselves to List methods — **what’s the point?**

### ✅ The Benefit Comes From: **Polymorphism and Flexibility**

#### ✅ 1. **Easier to Switch Implementations**

You're not locking yourself to `ArrayList`. You can easily switch to `LinkedList`, `Vector`, or any other `List` implementation **without changing the variable type**.

```java
List l = new ArrayList();
// Later...
l = new LinkedList();  // No change needed in rest of the code
```

This is powerful in large codebases or APIs where **you don’t care how the list is implemented**, only that it behaves like a `List`.

---

# 🧱 **How Many Ways Can We Create an Object in Java?**

There are **five main ways** to create or get an object in Java:

---

### 1️⃣ **Using `new` Operator (Standard Instantiation)**

➡️ The most common and straightforward way.

```java
Test t = new Test();
```

✅ **When to use**: Regular object creation with constructor logic. 

---

### 2️⃣ **Using `newInstance()` Method (Reflection)**

➡️ Dynamically loads and instantiates class using reflection.

```java
Test t = (Test) Class.forName("Test").newInstance();
```

📝 *From Java 9 onwards, `newInstance()` is deprecated.*

Use:

```java
Test t = Test.class.getDeclaredConstructor().newInstance();
```

✅ **When to use**: Frameworks, dynamic loading, dependency injection containers.

---

### 3️⃣ **Using `clone()` Method (Cloning)**

➡️ Creates a **copy** of an existing object.

```java
Test t1 = new Test();
Test t2 = (Test) t1.clone();
```

🔒 Requires:

- `implements Cloneable`

- `override clone()` from `Object` class

✅ **When to use**: Object copying where constructor is costly or unavailable.

---

### 4️⃣ **Using Factory Methods**

➡️ Static methods that return an instance.

```java
Runtime r = Runtime.getRuntime();
DateFormat df = DateFormat.getInstance();
```

✅ **When to use**: Singleton, utility, or abstract object creation patterns.

🧠 *Internally, factory methods may also use `new`, `clone`, or even caching.*

---

### 5️⃣ **Using Deserialization (Object Stream)**

➡️ Reads a previously serialized object from a file/stream.

```java
FileInputStream fis = new FileInputStream("abc.ser");
ObjectInputStream ois = new ObjectInputStream(fis);
Test t = (Test) ois.readObject();
```

✅ **When to use**: Persistent object recovery, especially in distributed systems.

🧠 *Does not call constructor — directly rehydrates the object state.*

---

## 🔁 Summary Table

| #️⃣ | **Way**         | **Example**                     | **Use Case**                         |
| --- | --------------- | ------------------------------- | ------------------------------------ |
| 1️⃣ | `new` Operator  | `new Test()`                    | Normal instantiation                 |
| 2️⃣ | Reflection      | `Class.forName().newInstance()` | Dynamic instantiation                |
| 3️⃣ | Cloning         | `t2 = (Test) t1.clone()`        | Copying an object                    |
| 4️⃣ | Factory Methods | `Runtime.getRuntime()`          | Singleton/controlled object creation |
| 5️⃣ | Deserialization | `ois.readObject()`              | Restore from stream/file             |

---

## ⚠️ Interview Tip

You can say:

> There are **five primary ways** to create objects in Java: using `new`, reflection, cloning, factory methods, and deserialization. Each suits different use cases — from general instantiation to restoring serialized state.

---

---

---



# Constructor

In Java, whenever an object is created, a special block of code runs automatically to initialize the new object. This block of code is called a **constructor**.

The primary purpose of a constructor is to initialize the object’s state.

```java
class Student {
    String name;
    int rollno;

    // Constructor to initialize name and rollno
    Student(String name, int rollno) {
        this.name = name; 
        this.rollno = rollno;
    }

    public static void main(String[] args) {
        // Creating student objects with different names and roll numbers
        Student s1 = new Student("vijayabhaskar", 101);
        Student s2 = new Student("bhaskar", 102);
    }
}
```

## Constructor vs Instance Block

- Both **instance blocks** and **constructors** execute automatically every time an object is created. The instance block runs **first**, followed by the constructor.

- Instance blocks are typically used to perform common initialization or actions for all constructors, without duplicating code.

- **Important:** Instance blocks **cannot take arguments**, while constructors can. Therefore, constructors and instance blocks serve different purposes and cannot replace each other.

Code Example :→

```java
class Test {

    // Static variable to count instances of Test
    static int count = 0; 

    // Instance initialization block: runs before every constructor
    {
        count++;
    }

    Test() {
        // Default constructor
    }

    Test(int i) {
        // Parameterized constructor
    }

   public static void main(String[] args) {
        Test t1 = new Test();
        Test t2 = new Test(10);
        Test t3 = new Test();

        // Printing the value of count, which should be 3 since three instances are created
        System.out.println(count); // Output: 3
    }
}
```

## Rules to write Constructor :→

1. The **constructor name must exactly match the class name** (case-sensitive).
2. Constructors **do not have a return type**, not even `void`. If you declare a return type, the compiler treats it as a **regular method**, not a constructor. This means the class will have no constructor matching that signature, which can lead to errors when creating objects.
3. Although Java allows you to write a **method with the same name as the class**, this is discouraged because it can cause confusion—such a method is **not a constructor**.
4. The only modifiers allowed for constructors are the access modifiers: **`public`**, **default** (no modifier), **`private`**, and **`protected`**. Other modifiers like `static`, `final`, `abstract` are **not allowed** for constructors.

## Default Constructor :→

- Every class in Java, including abstract classes, can have constructors. Although abstract classes cannot be instantiated directly, their constructors are called during the creation of subclass objects.

- If **no constructor** is explicitly written in a class, the Java compiler automatically generates a **default (no-argument) constructor** for that class.

- If the programmer **writes any constructor** (with or without parameters), the compiler **does not** generate a default constructor. Hence, a class contains either the compiler-generated default constructor or the programmer-defined constructor(s), but **not both simultaneously**.

### Prototype of Default Constructor :→

1. The default constructor is always a **no-argument constructor**.
2. The access modifier of the default constructor **matches the access modifier of the class**. This applies only if the class is either `public` or has default (package-private) access.
3. The default constructor contains a single statement:  
   `super();` — a no-argument call to the superclass constructor.



![Screenshot 2024-02-17 140907](C:\Users\rajee\Downloads\wmremove-transformed.png)

## 🔄 `super()` vs `this()`

- **First line rule:**  
  The first statement inside any constructor must be either `super()` or `this()`.  
  If neither is written explicitly, the compiler inserts `super()` by default.

- **Position constraint:**  
  `super()` or `this()` **must be the first line** inside the constructor.  
  Placing them anywhere else results in a **compile-time error**.

- **Usage restriction:**  
  Both `super()` and `this()` can **only be used within constructors**.  
  Using them in methods, blocks, or elsewhere leads to a **compile-time error**.

- **Constructor-to-constructor call:**  
  `this()` is used to **call one constructor from another within the same class**,  
  while `super` is used to **call a constructor from the parent class**.

---

![Screenshot 2024-02-17 143913](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/deb1dafc-2f29-4a02-a010-f6a4091b143a)

![Screenshot 2024-02-17 143532](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/4bd6c50c-e5b8-4b29-bfda-b22ebade5701) 



---

### Constructor Code Example

---

#### Constructor Chaining code

```java
class Parent {
    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    Child() {
        this(10); // Calls another constructor in the same class
        System.out.println("Child no-arg constructor");
    }

    Child(int x) {
        super(); // Calls the constructor of Parent class
        System.out.println("Child int-arg constructor: " + x);
    }

    public static void main(String[] args) {
        Child c = new Child();
    }
}
```

**Output**

```java
Parent constructor
Child int-arg constructor: 10
Child no-arg constructor
```

---

### Code

> If you don’t explicitly call `super()` or `this()` in the first line of a constructor, the **compiler automatically inserts a call to the no-arg `super()` constructor**.

```java
class Parent {
    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    Child() {
        this(10);
        System.out.println("Child no-arg constructor");
    }

    Child(int x) {
        // super(); ← even if you don’t write this, Java will insert it
        System.out.println("Child int-arg constructor: " + x);
    }

    public static void main(String[] args) {
        Child c = new Child();
    }
}
```

**Output**

```java
Parent constructor
Child int-arg constructor: 10
Child no-arg constructor
```

---

**Example that causes error**

```java
class Parent {
    Parent(int x) {
        System.out.println("Parent constructor: " + x);
    }
}

class Child extends Parent {
    Child() {
        // Compiler tries to insert super(); but there's no no-arg constructor in Parent
        System.out.println("Child constructor");
    }

    public static void main(String[] args) {
        Child c = new Child(); // ❌ Compile-time error
    }
}

```

**Output : ❌ Error:**

```java
constructor Parent in class Parent cannot be applied to given types;
  required: int
  found: no arguments
```

---

**Example: No explicit `super()` in `Child(int x)`**

```java
class Parent {
    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    Child(int x) {
        // No super() or this() → compiler will insert super()
        System.out.println("Child int-arg constructor: " + x);
    }

    public static void main(String[] args) {
        Child c = new Child(42);
    }
}
```

Output :-->

```java
Parent constructor
Child int-arg constructor: 42
```

---

## Overloaded Constructors :→

A class can define multiple constructors with the same name but different parameter lists—these are known as **overloaded constructors**.

Constructor overloading provides flexibility to initialize objects in different ways based on passed arguments.

In Java, we can call one constructor from another using `this(...)`, and this is known as **constructor chaining**. It must always be the first statement inside a constructor.

```java
class Test {

    Test(double d) {
        System.out.println("double-argument constructor");
    }

    Test(int i) {
        this(10.5); // Calls double-arg constructor
        System.out.println("int-argument constructor");
    }

    Test() {
        this(10); // Calls int-arg constructor
        System.out.println("no-argument constructor");
    }

    public static void main(String[] args) {
        Test t1 = new Test();       // Calls: no-arg → int-arg → double-arg
        Test t2 = new Test(10);     // Calls: int-arg → double-arg
        Test t3 = new Test(10.5);   // Calls: double-arg
    }
}

```

Output : ->

```java
double-argument constructor
int-argument constructor
no-argument constructor
double-argument constructor
int-argument constructor
double-argument constructor
```

---

### 🧱 Key Rules About Constructors

1. **Constructors Are Not Inherited**
   
   - In Java, constructors are **not inherited** from the parent class.
   
   - This means **constructor overriding is not possible**.
   
   - However, constructors **can be overloaded** within the same class.

2. **Constructors Exist in Classes, Not Interfaces**
   
   - We can define constructors in any class, including **abstract classes**.
   
   - But interfaces **cannot have constructors**, because they are not intended for object instantiation.

![Screenshot 2024-02-19 134501](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/e33ea274-2ab6-4b4f-a152-9751fea1e7ec)

---

##### 🚫 Why Do Abstract Classes Have Constructors?

We **cannot create objects** of an abstract class directly, but an **abstract class can have constructors**.

### 🤔 Why?

Whenever we create an object of a subclass that extends an abstract class, **the constructor of the abstract class is automatically invoked**—**not to create an abstract object**, but to perform **initialization for the abstract part of the child object**.

---

##### ✅ Which of the following statements is true?

| Statement                                                                              | True / False |
| -------------------------------------------------------------------------------------- | ------------ |
| 1. Whenever we create a child class object, a separate parent class object is created. | ❌ False      |
| 2. Whenever we create a child class object, the parent class constructor is executed.  | ✅ True       |

```java

abstract class Parent {
    Parent() {
        System.out.println(this.hashCode()); // Refers to the Child object
    }
}

class Child extends Parent {
    Child() {
        System.out.println(this.hashCode()); // Same hashCode as above
    }
}

class Test {
    public static void main(String[] args) {
        Child c = new Child();               // Triggers Parent and Child constructors
        System.out.println(c.hashCode());    // All print same hashCode
    }
}

```

**Output ->**

```java
1072408673
1072408673
1072408673
```

### 🧠 Key Insight:

- All three `hashCode()` calls print the **same value**, because **only one object is created**—an instance of `Child`, which includes the state of its `Parent`.



### 📝 Constructor Behavior in Java

- **Compiler-generated constructor:**  
  If the programmer does **not define any constructor**, then the compiler **automatically inserts a default no-argument constructor**.

- **Implicit `super()` call:**  
  If the programmer defines a constructor **but does not explicitly call `super()` or `this()` in the first line**, the compiler **automatically inserts `super()`** as the first statement.

- **Recursive constructor invocation check:**  
  The compiler checks for **any possibility of recursive constructor calls** (e.g., `this()` calling itself directly or indirectly).  
  If such a loop is detected, it throws a **compile-time error** to prevent infinit

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

---

### ⚠️ Constructor Inheritance Caveat

- If the **parent class defines only parameterized constructors** (i.e., no default constructor), then the **child class must explicitly call** one of them using `super(args...)` — otherwise, the code will not compile.

- Hence, it is **highly recommended** that whenever you define a **parameterized constructor** in the parent class, you **also provide a no-argument constructor**, unless you’re certain all subclasses will always call a specific constructor.

---

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

### ✅ **Is This Code Valid?**

Yes — this code compiles and runs just fine **because**:

- `Exception` is the **superclass** of `IOException`.

- Java allows the child constructor to declare a **broader checked exception** than the parent constructor.

---

If the `Parent` constructor had declared a **narrower** checked exception than the one in `Child`, like:

```java
class Parent {
    Parent() throws IOException {}
}

class Child extends Parent {
    Child() throws SQLException { // ❌ Invalid
        super();
    }
}

```

This would cause a **compile-time error**, because the child constructor would be declaring an exception that the parent doesn't throw.

