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

**Abstraction** is the process of **hiding complex internal implementation** details and exposing only the **essential features** to the user.  
It allows users to interact with a system without needing to understand *how* it works internally — only *what* it does.

> In simple words:  
> **"Tell me *what* it does, not *how* it does it."**

---

###### Example : ATM Machine

You interact with the ATM screen to:

- Withdraw 💵

- Check Balance 📊

- Deposit Cash 🏦

But you **don’t see**:

- Bank server calls

- Transaction verification

- Logging and auditing mechanisms

👉 You get the service, without knowing the system's inner machinery.

---

### ✅ Advantages of Abstraction

| Benefit                       | Description                                                 |
| ----------------------------- | ----------------------------------------------------------- |
| ✅ **Simplifies Complexity**   | Users deal only with essential functionality, not internals |
| 🔐 **Improves Security**      | Sensitive internal details are hidden from misuse           |
| 🔁 **Boosts Maintainability** | Internal changes don’t break external use                   |
| ♻️ **Encourages Reusability** | Abstract structures work across implementations             |
| 🔗 **Enables Loose Coupling** | Components are more independent and swappable               |

---

### ⚙️ How Java Achieves Abstraction?

Java supports abstraction via:

- **Abstract Classes**

- **Interfaces**

Let’s break each one down.

---

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

---

### 🧱 Abstract Class Key Features (JDK Timeline)

| Since   | What Was Added / Enhanced                            | Quick Note                                                                    |
| ------- | ---------------------------------------------------- | ----------------------------------------------------------------------------- |
| Java 5  | **Generics support**                                 | Abstract classes can be generic and support type-safe hierarchies.            |
| Java 8+ | **Functional-style usage**                           | Can be extended to support lambda-friendly designs via inheritance.           |
| Java 17 | **Sealed abstract classes** (`sealed`, `non-sealed`) | Control subclassing and enable safer, exhaustive logic like pattern matching. |

**🚫 Things Abstract Classes Cannot Do**

| ❌ Not Allowed                   | Why?                                                                        |
| ------------------------------- | --------------------------------------------------------------------------- |
| Instantiation directly          | They are incomplete by design. Must be subclassed.                          |
| Multiple inheritance of classes | Java doesn't allow a class to extend more than one class (abstract or not). |

---

[Abstract class code example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/Abstract_Class_Code_Example_README.md)

---

## 🧩 2. Interface

An **interface** in Java is a blueprint of a class that defines a set of **abstract behaviors** (methods) that a class must implement. It provides a way to achieve **full abstraction** and supports **multiple inheritance of type**.

A class can **implement multiple interfaces**, allowing it to inherit behaviors from multiple sources — something not possible with classes alone.

> Think of it as a **contract** that all implementing classes must fulfill.

```java
interface Greeter {
    void sayHello();
}

class EnglishGreeter implements Greeter {
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}
```

---

### 🧩 Interface Key Features (JDK Timeline)

| Since      | What Was Added                                            | Quick Note                                                                                                         |
| ---------- | --------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| Java 1.0–7 | Abstract method declarations only                         | All methods are implicitly `public abstract`.                                                                      |
| Java 8     | `default` methods  `static` methods                       | `default`: Has body; implementing classes may override. <br>`static`: Belongs to the interface, **not inherited**. |
| Java 9     | `private` & `private static` methods                      | Internal utilities for reuse inside `default` or `static` methods.                                                 |
| Java 15/17 | **Sealed interfaces** (`sealed`, `non-sealed`, `permits`) | Restrict which classes are allowed to implement the interface.                                                     |

**🚫 Things Interfaces Cannot Have**

| ❌ Not Allowed                          | Why?                                                         |
| -------------------------------------- | ------------------------------------------------------------ |
| Constructors                           | Interfaces cannot be instantiated directly.                  |
| Instance variables                     | Interfaces cannot hold per-object state.                     |
| Mutable fields                         | All fields must be final constants.                          |
| `protected` or package-private methods | Only `public`, `private`, and `static` methods are permitted |

**🍒 Optional Extras (Modern Java Goodies)**

- **Nested Types**  
  Interfaces can contain nested interfaces, classes, enums, and records.  
  Useful for grouping related contracts.

- **Example – Private Helper in Interface**

```java
interface Calculator {
    private static int clamp(int x, int y) {
        return Math.max(x, y);
    }

    default int sum(int a, int b) {
        return clamp(a, b) + clamp(b, a);
    }
}
```

- **Sealed Interfaces & Pattern Matching**  
  Work seamlessly with **exhaustive switch expressions**, enabling safer and cleaner branching logic.**

[Interface Code Examples](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/Interface_Code_Example_README.md)

---

## 🆚 Abstract Class vs Interface in Java

| 🔹 **Feature**                    | 🧱 **Abstract Class**                               | 🧩 **Interface**                                                            |
| --------------------------------- | --------------------------------------------------- | --------------------------------------------------------------------------- |
| ✅ **Purpose**                     | Partial abstraction + shared behavior               | Full abstraction + multiple type inheritance                                |
| 🧪 **Methods Support**            | Abstract + Concrete methods                         | Abstract (Java 7 and below) <br> + `default`, `static`, `private` (Java 8+) |
| 📦 **Variables**                  | Instance, static, final, etc.                       | Only `public static final` (constants)                                      |
| 🛠 **Constructors**               | ✅ Allowed                                           | ❌ Not allowed                                                               |
| ♻️ **Multiple Inheritance**       | ❌ Not supported(only one class can be extended)     | ✅ Supported(implements multiple interfaces)                                 |
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

[Abstraction Interview Questions](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/Abstraction%20Interview%20Question.md)

---

# 🔐 Encapsulation

Encapsulation is the act of bundling data (fields) and the **methods** **that operate on that data** into a single unit, -- usually a class, while restricting direct access to the data using access control modifiers like `private`, `protected`, and `public`.

When a class follows the principles of data hiding and abstraction, it is called an **encapsulated class**.

🧠 **In Simple Words:**

> "Don't let outsiders touch your internal data directly — give them controlled access through methods."

---

**📺 Analogy:**

> **Encapsulation is like a TV remote:**  
> You don’t open the remote and press its circuit board to change the channel.  
> You use the buttons outside — a safe, limited interface — while the internal mechanics are hidden.
> 
> 👉 Just like the remote hides internal circuits, encapsulation hides internal data.

---

**🔑 Core Principles**

1. **`Private Fields:`** Fields are usually marked `private` to prevent external access.
2. **Public Method**  **(Getters and Setters)** :  Getters/setters allow safe access or updates to private fields.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Encapsulation.java)

---

### ⭐ **Advantages of Encapsulation**

| Benefit             | Description                                                               |
| ------------------- | ------------------------------------------------------------------------- |
| 🔒 Data Protection  | Prevents unauthorized access or accidental modification of internal data. |
| 🔁 Easy Maintenance | Internal implementation can change without affecting outside code.        |
| 📦 Logical Grouping | Keeps related data and methods together.                                  |
| ✅ Validation Logic  | Allows validation before modifying the data.                              |

---

### ⚙️ **Access Modifiers Recap**

| Modifier    | Scope                                                                   |
| ----------- | ----------------------------------------------------------------------- |
| `private`   | Accessible only within the class.                                       |
| `default`   | (No modifier) — Accessible within the same package.                     |
| `protected` | Accessible in the same package and subclasses (even in other packages). |
| `public`    | Accessible from anywhere.                                               |

---

## 🔐 Tightly Encapsulated Class in Java

A class is said to be **tightly encapsulated** if **all its variables (fields) are declared as `private`** — regardless of whether getter and setter methods exist or not.

```java
class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }
}
```

- `balance` is `private` → ✅ tightly encapsulated

- Getter presence doesn't affect encapsulation status.

---

### ❌ Not Tightly Encapsulated Examples

> **If even one field in the class (or any of its parent classes) is not private, the class is *not* tightly encapsulated.**

This is because child classes inherit all **non-private** fields from parent classes — breaking encapsulation at the root.

```java
class A {
    int x = 10; // ❌ Not private
}

class B extends A {
    private int y = 20; // ✅ But inherits x → ❌
}

class C extends B {
    private int z = 30; // ✅ But still inherits x → ❌
}
```

Even though `y` and `z` are private, the inherited `x` breaks encapsulation — none of these classes are tightly encapsulated.

---

## 📌 Encapsulation Summary:

- ✅ All fields must be `private`.

- 🚫 It doesn’t matter if the class has `public`/`protected` methods or not.

- 🧬 If the **parent class is not tightly encapsulated**, **no subclass can be tightly encapsulated** either.

---

[Encapsulation Interview Questions](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/Encapsulation%20Interview%20Question.md)

---

# 🧬 Inheritance (is-a)

Inheritance allows a class (child/subclass) to **acquire the fields and methods** of another class (parent/superclass). It helps in **code reuse, logical hierarchy**, and supports **runtime polymorphism**.

> Inheritance means *child classes automatically get the features of their parents*.

---

###### 🔑 Inheritance Key Concepts:

| Concept                | Description                                                                                    |
| ---------------------- | ---------------------------------------------------------------------------------------------- |
| **IS-A Relationship**  | Models a real-world **"is-a"** relationship. <br>🧪 `Dog extends Animal` → Dog *is an* Animal. |
| **`extends` Keyword**  | Used to inherit from a class in Java.                                                          |
| **Single Inheritance** | Java supports only single inheritance with classes (but multiple with interfaces).             |

---

###### 📺 Analogy

> Inheritance is like a child inheriting genes from parents.  
> The child may inherit features like eye color, but can also develop their own personality (method overriding).

---

### ⭐ Advantages of Inheritance :

| Benefit                 | Description                                                     |
| ----------------------- | --------------------------------------------------------------- |
| 🔁 Code Reuse           | Common methods written in the parent class can be reused.       |
| 🧬 Method Overriding    | Subclasses can redefine parent methods for specific behavior.   |
| 🧠 Polymorphism         | Parent references can point to child objects for flexible code. |
| 🧩 Logical Hierarchy    | Organizes classes in a structured, real-world way.              |
| 🌿 Extensibility        | Add features via subclasses without changing existing code.     |
| 🧱 Framework Foundation | Powers major OOP frameworks and design patterns.                |

---

**🔀 Behavior in Java**

```java
class Animal {
    void eat() { System.out.println("eating..."); }
}
class Dog extends Animal {
    void bark() { System.out.println("barking..."); }
}
```

🔼 Upcasting (Parent Ref = Child Obj)

```java
Animal a = new Dog(); 
a.eat();      // ✅ allowed
a.bark();     // ❌ not allowed — reference type rules
```

🔽 Downcasting (Child Ref = Parent Obj)

```java
Dog d = new Animal();  // ❌ Compile-time error
```

[Inheritance Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/InheritanceExample.java)

---

### ✅ Conclusion on Inheritance Behavior

##### 1. **Child Inherits from Parent — Not the Other Way Around**

- Everything in the parent class is automatically available to the child.

- But child-specific members are **not accessible via the parent class**.

🔍 So:

- ✅ A **child reference** can access **both** parent and child members.

- ⚠️ A **parent reference** can access **only** parent members, even if it refers to a child object.

---

##### 2. **Parent Reference Can Hold Child Object**

- ✅ This is called **upcasting**.

- ⚠️ Only the methods and variables defined in the parent class are accessible via the parent reference.

- 🧠 **However**, if a **parent method is overridden** by the child class, and we invoke that method through the parent reference, **the child’s version is executed**.  
  This is because of **dynamic method dispatch** (covered in detail in point 4).

```java
class Animal {
    void sound() { System.out.println("Animal sound"); }
}

class Dog extends Animal {
    void sound() { System.out.println("Dog barks"); }
}

Animal a = new Dog();  
a.sound();  // Output: Dog barks ✅ (child method runs due to overriding)

```

- ❗ But **child-specific methods** (not present in the parent class) are **not accessible** through the parent reference.

```java
a.bark();  // ❌ Compile-time error: bark() not in Animal
```

---

##### 3. **Child Reference Cannot Hold Parent Object**

- ❌ This causes a **compile-time error** — it's a **type mismatch**.

```java
Dog d = new Animal();  // ❌ Not allowed
```

---

##### 4. **Dynamic Method Dispatch (Runtime Polymorphism)**

- 🧠 When you call a **non-static, non-final, non-private** method using a **parent reference**, Java checks:
  
  - ✅ **If the method is overridden in the child**, the **child’s version** is executed.
  
  - 🔁 If not overridden, the **parent's version** is used.

```java
class Animal {
    void sound() { System.out.println("Generic animal sound"); }
}

class Dog extends Animal {
    void sound() { System.out.println("Bark"); }
}

Animal a = new Dog();
a.sound();  // Output: Bark → ✅ Child method called
```

---

##### Inheritance System-Wide Examples:

- In Java, the most commonly required functionality for all classes is defined in the **`Object`** class. Hence, `Object` serves as the **root class** for all Java classes.

- Similarly, for all exceptions and errors, the common functionality is defined in the **`Throwable`** class, making it the **root of the exception hierarchy** in Java.

---

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

---

###### ✅ Interfaces to the Rescue:

Java **does allow multiple inheritance via interfaces** because:

- Interfaces (until Java 7) could only declare method signatures — **no implementation**, so no conflict.

- From **Java 8 onwards**, default methods are allowed in interfaces, but Java still resolves conflicts **explicitly** using rules like:
  
  - Subclass overrides > implemented interface default > compiler error if ambiguous.

---

## 🔗 HAS-A Relationship (Composition & Aggregation)

A **HAS-A relationship** models **association** between two classes, where **one class holds a reference to another**. It’s used to represent **ownership or usage**, and is commonly implemented via:

- 🧱 **Composition** – strong association (tight coupling)

- ⚙️ **Aggregation** – weak association (loose coupling)

---

### 🧱 Composition – Strong HAS-A

> **Composition** means one class is **composed of** one or more other classes.  
> It models a **strong "part-of" relationship**, where the contained object’s **existence depends** on the container.

---

##### ✅ HAS-A Relationship Key Points :

- A HAS-A relationship means one class *"has"* another as a part of itself.

- It is implemented using **member variables** that hold references to other objects.

- The contained object’s **lifecycle is managed by** the container class.

- This forms a strong bond: if the container is destroyed, so is the contained part.

---

### 🔍 Real-world Analogy:

> A **Car** HAS-A **Engine**.  
> The engine is part of the car. If the car is destroyed, the engine loses purpose.
> 
> The **car controls** when the engine is created and destroyed.

---

###### **HAS-A Relationship Code Example:**

```java
class Engine {
    void start() {
        System.out.println("Engine started.");
    }
}

class Car {
    private Engine engine; // HAS-A relationship

    public Car() {
        this.engine = new Engine(); // Car owns Engine
    }

    public void drive() {
        engine.start();
        System.out.println("Car is moving.");
    }
}
```

- `Car` is the **composite** (container) class.
- `Engine` is the **component** (contained) class.

Without an existing car, the engine cannot exist. The **`Car`** manages the creation and destruction of the **`Engine`**, and the lifecycle of the **`Engine`** is tightly bound to the lifecycle of the **`Car`**. This is a clear example of a strong association through composition.

[👉 Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Composition.java)

---

### ⚙️ Aggregation – Weak HAS-A

**Aggregation** represents a *"HAS-A"* relationship where one class contains a reference to another class, **but the contained object can exist independently of the container**.

> Even if the container is destroyed, the contained object **can still exist**.

---

###### 🔍 Aggregation Key Points:

- Aggregation is a **weaker form of association** compared to composition.

- The **lifecycle** of the contained object is **not tightly bound** to the container.

- The contained object can be **shared** among multiple containers.

- It promotes **flexibility** and **loose coupling** between classes.

---

###### 💡 Aggregation Analogy:

> A **Teacher** belongs to a **Department**,  
> but the teacher exists even if the department is dissolved.  
> A new department can reuse the same teacher.

---

###### ✅ Summary:

```java
class Department {
    String name;

    Department(String name) {
        this.name = name;
    }
}

class Teacher {
    private Department department; // Aggregated object

    public Teacher(Department department) {
        this.department = department; // Department is passed in, not created inside
    }

    public void showDetails() {
        System.out.println("Belongs to Department: " + department.name);
    }
}

```

- `Teacher` doesn’t own `Department`.

- `Department` can be shared by multiple `Teacher` instances.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Aggregation.java)

---

**✅ Summary:**

> Use **aggregation** when:
> 
> - Objects have an ownership relationship,
> 
> - But **independent existence** is allowed and expected.

---

##### 🧠 Composition vs Aggregation – Key Differences

| Feature               | Composition 🧱                  | Aggregation ⚙️                  |
| --------------------- | ------------------------------- | ------------------------------- |
| **Dependency**        | Part cannot exist without whole | Part can exist independently    |
| **Lifecycle Control** | Container manages part          | Part is managed externally      |
| **Coupling**          | Tight                           | Loose                           |
| **Ownership**         | Strong (has full control)       | Weak (only references)          |
| **Example**           | Heart in a body 🫀              | Student in a university 🎓      |
| **Instantiation**     | Container creates the part      | Part is passed to the container |

---

##### ✅ When to Use What?

| Scenario                                                     | Use                 |
| ------------------------------------------------------------ | ------------------- |
| The part is **exclusive and tightly bound** to the container | Use **Composition** |
| The part is **shared, reusable, or independent**             | Use **Aggregation** |

---

# 🧾 Method Signature in Java

In Java, the **method signature** is made up of:

> 🔹 The method’s **name**  
> 🔹 The **parameter types**, in order

```java
public void methodOne() {}
public int methodOne() { return 10; } // ❌ Error: Same signature, only return type differs

```

> ❗**Within the same class**, you cannot define two methods with the **same name and parameter types**, even if their **return types differ**.

---

###### 🚫 Return Type Is *Not* Part of the Signature

In Java, **return type is ignored** when resolving method signatures.

Even if two methods differ **only** by return type, it's a **compile-time error** because the compiler sees them as **duplicate signatures**.

```mathematica
Signature = MethodName + ParameterTypes
NOT INCLUDED: Return Type, Parameter Names, Access Modifiers
```

---

###### 🔍 Why Is Signature Important?

- It’s how the **compiler identifies which method to call**.

- It forms the basis for **method overloading resolution**.

- Ensures clarity and **prevents ambiguity**.

> 🧠 Think of method signature like a person’s *full name and birthday* — not just the name. The return type is like their favorite color — nice to know, but not used to identify them in legal paperwork.

---

### ✅ Valid Overloading Example

```java
class Test {
    public void m1(double d) { }
    public void m2(int i) { }

    public static void main(String[] args) {
        Test t = new Test();
        t.m1(10.5);   // ✅ OK: matches m1(double)
        t.m2(10);     // ✅ OK: matches m2(int)
        t.m3(10.5);   // ❌ Error: m3 doesn't exist
    }
}
```

> Notice: **Different method names or parameter types = valid.**  
> But **same name + same parameter types** = compile error, regardless of return type.

---

### 📌 Important Rule

> **Within the same class**, you **cannot** define multiple methods with the same signature — even if their return types differ.

---

# 🧬 Polymorphism

**Polymorphism** means *"many forms."*  
It enables the same method or interface to exhibit **different behaviors** depending on the context — such as **parameter types** (compile-time) or **object type** (runtime).

> ✅ In Java, polymorphism is **achieved via:**
> 
> - **Method Overloading** (Compile-Time Polymorphism)
> 
> - **Method Overriding** (Runtime Polymorphism)

---

###### 🧠 Real-World Analogy

> 🧑 A person named "Alex" can be a teacher at school, a father at home, and a customer at a store.  
> Same name, different roles. That’s **polymorphism**.

**Polymorphism Analogy**

> A **boy begins love** with the word **“friendship”**,  
> while a **girl ends love** with the same word — **“friendship.**
> 
> The **word is the same**, but the **intention is different**.  
> That, my friend, is the **essence of Polymorphism** —  
> **Same name, different behavior.** 💔➡️❤️

---

### 🔀 Types of Polymorphism

| Type             | Also Known As          | Resolved When?     | How?                               |
| ---------------- | ---------------------- | ------------------ | ---------------------------------- |
| **Compile-Time** | Static / Early Binding | During Compilation | Method Overloading,  Method Hiding |
| **Runtime**      | Dynamic / Late Binding | During Execution   | Method Overriding                  |

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
 Compile-time/Static/Early binding        Runtime/Dynamic/Late binding 
              / \                                  |
             /   \                                 |
            /     \                                |
           /       \                               |
          /         \                              |  
Method Overloading   Method Hiding          Method Overriding
```

---

## 1.) ⚡Overloading (Compile-time Polymorphism)

---

- **Method overloading** occurs when a class defines **multiple methods with the same name** but with **different parameter lists** (type, number, or order).

- In **method overloading**, the **compiler** determines which method to call based on the **reference type** and **method signature**. The runtime object is irrelevant in this case.

Hence, method overloading is also called:

- **Compile-time Polymorphism**

- **Static Polymorphism**

- **Early Binding**

---

**✅ Example:**

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

- Both methods are named `add` but operate on different data types.

- During compilation, Java selects the correct version based on the arguments used.

> ✅ This improves **readability**, supports **method specialization**, and avoids the need for creating multiple method names for logically similar behavior

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverloadingExample.java)

---

### 🔑 Key Concepts of Overloading :

- The **return type is not** part of the method signature.

- Overloading requires a difference in **number**, **type**, or **order** of parameters.

- Method resolution is based on the **reference type**, not the object type.

- If an exact match isn’t found, Java performs **automatic type promotion**.

**🔄 Type Promotion Chain in Java:**

```java
byte -> short 
             \
              int -> long -> float -> double
            /
         char               
```

*Java automatically promotes smaller types to match a method signature when needed.*

---

### 🧪 Overloading Case Studies

---

#### ⚡ Case 1: **Automatic Type Promotion**

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

> Java promotes smaller types step by step to find a matching method.  
> If none matches even after all promotions → **Compile-Time Error**.

---

#### 🧊 Case 2: **Match Priority – Exact vs Compatible**

```java
class Test {
  public void methodOne(Object o) { System.out.println("Object"); }
  public void methodOne(String s) { System.out.println("String"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne("Hello");  // ✅ Exact match: String
    t.methodOne(new Object()); // ✅ Exact match: Object
  }
}

```

> - ✅ **Exact match always has the highest priority.**
> 
> - ✅ If no exact match, Java looks for the closest compatible match.
> 
> - ✅ **Child types** are preferred over **parent types**.

---

#### ❗ Case 3: **Ambiguity with Same Level Types**

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

---

#### 🔄 Case 4: Same Count, Different Order (Ambiguity)

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

> Overloading by **reordering argument types** can cause ambiguity when both combinations are valid.

---

#### 🌌 Case 5: **Var-Args vs Fixed Args**

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

> - **Var-arg** methods have the *lowest* priority** during method resolution.
> 
> - If **no other method matches**, **only then** the var-arg method will be chosen.
> 
> - It behaves **almost like the `default` case in a `switch` statement** — used as a *fallback*

---

#### 🧬 Case 6: **Parent vs Child References**

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

### 📊 Summary Table – Overloading Case Highlights

| Case | Focus                       | Key Takeaway                                                 |
| ---- | --------------------------- | ------------------------------------------------------------ |
| 1    | Type Promotion              | Smaller types promoted to match method                       |
| 2    | Exact Match Priority        | Exact > Compatible; child type preferred over parent         |
| 3    | Null Ambiguity              | Ambiguity with sibling types like `String` vs `StringBuffer` |
| 4    | Same Count, Different Order | Ambiguity when both overloads match by argument count        |
| 5    | Var-Args Fallback           | Var-arg used only when no fixed match is found               |
| 6    | Ref vs Object Type          | Overload resolution is based on reference type               |



---

## 2. 🔁 Method Overriding (Runtime Polymorphism)

---

##### 🧠 What is Method Overriding ?

- **Method Overriding**  allows a **subclass (child class)** to provide its **own specific implementation** of a method that is already defined in its **superclass (parent class)**.

- The method in the **parent class** that is being redefined is called the **overridden method**.

- The method in the **child class** that redefines the parent’s method is called the **overriding method**.

- The **overriding method** in the child class must have the **same signature** as the method in the parent class.

- It enables **runtime polymorphism**: the **method call is resolved at runtime**, based on the **actual object**, not the reference type.

- To explicitly indicate that a method in the subclass is intended to **override** a method in the superclass, we use the **`@Override`** annotation.  
  This helps the compiler catch mistakes like mismatched method signatures or typos.

---

**📦Basic Example:**

```java
class Parent {
    public void property() {
        System.out.println("Cash + Land + Gold");
    }

    public void marry() {
        System.out.println("Arrange marriage");
    }
}

class Child extends Parent {
    @Override
    public void marry() {
        System.out.println("Love marriage");
    }
}

public class Test {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.marry(); // Arrange marriage

        Child c = new Child();
        c.marry(); // Love marriage

        Parent p1 = new Child();
        p1.marry(); // Love marriage
    }
}
```

> In **method overriding**, method resolution is based on the **runtime object**, not the reference type.  
> This is why `p1.marry()` (where `p1` is a parent reference pointing to a child object) invokes the **child’s overridden method**.

However, when using a **parent reference to hold a child object**:

- ✅ You can call **overridden methods** — this is resolved at **runtime** (dynamic method dispatch).

- ✅ You can call **parent-exclusive methods** — resolved at **compile-time** using the **reference type**.

- ❌ You **cannot call child-exclusive methods** — i.e., methods that exist only in the child class and are not declared in the parent class.

- ❌ You **cannot access private methods** of the parent using the parent reference — because private methods are not visible outside their own class and are **not inherited**.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverridingExample.java)

---

### 🧩Rules for Overridng :

---

#### 1.)   Signature Must Match

- Method **name and parameter types** must match exactly in parent and child class.

```java
void display(int x) // Valid overriding

int display(float x) // ❌ not overriding — different signature
```

---

#### 2. ✅ Covariant Return Types (Since Java 1.5)

- Overriding method can return a **subtype** of parent’s return type.

```java
class Parent {
    public Object getData() { return null; }
}

class Child extends Parent {
    public String getData() { return null; }
}
```

> ❌ Only for reference types — not allowed for primitives.

---

#### 3. ❌ Private Methods Cannot Be Overridden

- Not inherited by subclass.

- A same-signature method in subclass is a **new, unrelated method**, not overriding or hiding.

```java
class Parent {
    private void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    private void show() {
        System.out.println("Child");
    }
}

public class Test {
    public static void main(String[] args) {
        Child c = new Child();
        c.show(); // ✅ Works: calls Child's private method → Output: "Child"

        Parent p = new Child();
        p.show(); // ❌ Compile-time error: show() has private access in Parent
    }
}
```

> ✅ `Child.show()` is unrelated to `Parent.show()` — no overriding or hiding.  
> ❌ `Parent.show()` is private and not accessible via reference, even if object is Child.



---

#### 4. ❌ Final Methods Cannot Be Overridden

**Example 1 : -- >**

```java
class Parent {
    public final void show() {}
}

class Child extends Parent {
    public void show() {} // ❌ Compile-time error
}
```

Example 2 : -- >

```java
class Parent {
    public final void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    public final void show() { // ❌ Compile-time error: cannot override final method from Parent
        System.out.println("Child");
    }
}

public class Test {
    public static void main(String[] args) {
        Child c = new Child();
        c.show(); // ❌ Won’t compile

        Parent p = new Child();
        p.show(); // ❌ Won’t compile
    }
}

```

> ❌ `final` method in parent **cannot be overridden**, even with `final` keyword in child.  
> ✅ To fix it, either **remove the `final`** from the parent method, or **don’t redeclare** the method in child.

---

#### 5. ✅ Non-final Method can be overriden as Final

```java
class Parent {
    public void show() {}
}

class Child extends Parent {
    public final void show() {} // ✅ Legal
}
```

---

#### 6. ✅  Abstract Methods must be Overriden

```java
abstract class Parent {
    public abstract void show();
}

class Child extends Parent {
    public void show() {} // ✅ Must override
}
```

---

#### 7. ⚠️ Concrete Method can be made Abstract in Subclass

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

#### 8. Var-arg Method can only be overriden with Another Var-arg Method

```java
class Parent {
    public void show(int... x) {
        System.out.println("Parent var-arg method");
    }
}
class Child extends Parent {
    public void show(int... x) {
        System.out.println("Child var-arg method");
    }
}
```

🟢 This is valid overriding.

---

##### ⚠️ Not Overriding: Var-arg vs Regular Method

```java
class Child extends Parent {
    public void show(int x) {
        System.out.println("Child regular method");
    }
}
```

❌ This is **overloading**, not overriding — the signatures differ (`int...` vs `int`).

| Parent Method      | Child Method       | Result        |
| ------------------ | ------------------ | ------------- |
| `void m(int... x)` | `void m(int... x)` | ✅ Overriding  |
| `void m(int... x)` | `void m(int x)`    | ❌ Overloading |

---

### 🔐 Access Modifier Rules in Overriding

When overriding a method, the access modifier in the child class **cannot be more restrictive** than in the parent class.

| Parent Modifier | Allowed in Child Class           | ✅ / ❌ | Reason                                 |
| --------------- | -------------------------------- | ----- | -------------------------------------- |
| `private`       | ❌ Cannot override                | ❌     | Not inherited; method is class-private |
| *(default)*     | `default`, `protected`, `public` | ✅     | Visible only within the same package   |
| `protected`     | `protected`, `public`            | ✅     | Wider visibility allowed               |
| `public`        | `public` only                    | ✅     | Already the widest access              |

You can **increase visibility** (e.g., `protected` → `public`), but you **cannot decrease** it (e.g., `public` → `protected`).

```java
 // scope of access modifier
 private < default < protected < public
```

---

### Example : -- >

#### ✅ Overriding with respect to list

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

##### ✅ The Benefit Comes From: **Polymorphism and Flexibility**

##### ✅ 1. **Easier to Switch Implementations**

You're not locking yourself to `ArrayList`. You can easily switch to `LinkedList`, `Vector`, or any other `List` implementation **without changing the variable type**.

```java
List l = new ArrayList();
// Later...
l = new LinkedList();  // No change needed in rest of the code
```

This is powerful in large codebases or APIs where **you don’t care how the list is implemented**, only that it behaves like a `List`.



---

## ⏬ Overriding vs Overloading

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

## 🧱 Method Hiding

> In Java, when a **static method** in a subclass has the **same signature** as one in its superclass, it's called **method hiding**, not overriding.

---

### ⚠️ Static Methods and Overriding

---

#### ❌  Can We Override Static Methods?

**No.** Static methods **cannot** be overridden — they are **class-level**, not object-level.

- Instead, if you define a static method with the **same signature**, it’s called **method hiding**.

- Resolution is done at **compile time**, based on the **reference type**.

---

###### 🧪 Method Hiding Example

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
        Parent p = new Child();
        p.methodOne(); // Output: Parent static (⚠️ based on reference)
    }
}
```

> Even though `p` refers to a `Child`, static method is **not overridden** — so **Parent's** version runs.

---

###### 🔍 CASE 1:  Static Method cannot be overriden as Non-static ❌

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

###### 🔍 CASE 2:  Non-static Method can be overriden as Static ❌

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

#### 🔬 Method Hiding vs Method Overriding

| Feature       | Overriding               | Method Hiding             |
| ------------- | ------------------------ | ------------------------- |
| Method Type   | Instance methods         | Static methods            |
| Resolved By   | JVM at runtime           | Compiler at compile time  |
| Polymorphism  | Runtime Polymorphism     | Compile-time polymorphism |
| Required?     | Must be inherited        | Inheritance not mandatory |
| Real Use Case | Enables dynamic behavior | Not polymorphic           |

---

[Code Example 1](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/MethodHiding.java)

[Code Example 2](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Interf.java)

[Code Example 3](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverridingVarAgMethod.java) 

---



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



---

# ✨ **Checked vs Unchecked Exceptions**

---

### ✅ What are Checked Exceptions?

- **Checked exceptions** are those which the compiler checks at **compile-time** to ensure smooth execution at runtime.

- These must be either caught using a `try-catch` block or declared in the method signature using `throws`.

📝 **Examples:**  
`IOException`, `SQLException`, `ClassNotFoundException`

---

### ❌ What are Unchecked Exceptions?

- **Unchecked exceptions** are **not checked at compile time**. These typically indicate programming errors that occur at runtime.

- All exceptions that are subclasses of `RuntimeException` and `Error` are unchecked.

📝 **Examples:**  
`NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`, `StackOverflowError`

---

### 🔁 Exception Rules with respect to overriding

**Rule (✅ for Checked Exceptions):**

If a **child class overrides a method** that declares a checked exception, then:

- The child method can throw:
  
  - The **same checked exception**, or
  
  - A **subclass of it**

- It **cannot** throw:
  
  - A **broader checked exception**
  
  - A **new checked exception** if the parent method doesn’t declare one

**Rule (✅ for Unchecked Exceptions):**

No restrictions apply. You can throw any number of unchecked exceptions regardless of the parent method.

---

**💻 Example Code: Compile-Time Error with Checked Exception**

```java
class Parent {
    public void methodOne() {} // Does not declare any checked exception
}

class Child extends Parent {
    public void methodOne() throws Exception {} // ❌ Compile-time error
}
```

🛑 **Error:**

```java
methodOne() in Child cannot override methodOne() in Parent;
overridden method does not throw java.lang.Exception
```

---

### 🔢 Overriding Examples: Valid vs Invalid

| No. | Parent Method Signature               | Child Method Signature                                                               | ✅ Valid?                                                           | Reason                                                                                                                 |
| --- | ------------------------------------- | ------------------------------------------------------------------------------------ | ------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------------- |
| 1   | `void methodOne() throws Exception`   | `void methodOne()`                                                                   | ✅ Yes                                                              | Child throws nothing (which is okay)                                                                                   |
| 2   | `void methodOne()`                    | `void methodOne() throws Exception`                                                  | ❌ No                                                               | Child throws new checked exception not declared by parent                                                              |
| 3   | `void methodOne() throws Exception`   | `void methodOne() throws Exception`                                                  | ✅ Yes                                                              | Same exception                                                                                                         |
| 4   | `void methodOne() throws IOException` | `void methodOne() throws Exception`                                                  | ❌ No                                                               | Exception is broader than IOException                                                                                  |
| 5   | `void methodOne() throws IOException` | `void methodOne() throws EOFException, FileNotFoundException`                        | ❌ No      ⚠️ Technically valid, but discouraged unless necessary.* | Declares multiple subclasses — though valid in Java, it introduces ambiguity and complicates exception handling logic. |
| 6   | `void methodOne() throws IOException` | `void methodOne() throws EOFException, InterruptedException`                         | ❌ No                                                               | InterruptedException is unrelated                                                                                      |
| 7   | `void methodOne() throws IOException` | `void methodOne() throws EOFException, ArithmeticException`                          | ❌ No                                                               | ArithmeticException (✅ unchecked), but EOFException is okay only                                                       |
| 8   | `void methodOne()`                    | `void methodOne() throws NullPointerException, RuntimeException, ClassCastException` | ✅ Yes                                                              | All are unchecked — no restriction                                                                                     |

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

![wmremove-transformed](https://github.com/user-attachments/assets/1731e5ac-bf6f-4d38-9df4-e31b585a25ca)

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
