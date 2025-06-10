# Abstraction Interview Questions

## Table of Contents

1. [What exactly is abstraction in Java, and how is it different from encapsulation](#-1-what-exactly-is-abstraction-in-java-and-how-is-it-different-from-encapsulation)
2. [How does Java achieve abstraction? Why does Java need both abstract classes and interfaces?](#2-how-does-java-achieve-abstraction-why-does-java-need-both-abstract-classes-and-interfaces)
3. [Suppose I create an abstract class with only concrete methods and no abstract method. Is that valid? Why would I do that?](#-3-suppose-i-create-an-abstract-class-with-only-concrete-methods-and-no-abstract-method-is-that-valid-why-would-i-do-that)
4. [Can a constructor be present in an abstract class or an interface? If yes, how are they used or behave?](#-4-can-a-constructor-be-present-in-an-abstract-class-or-an-interface-if-yes-how-are-they-used-or-behave)

---

# 🎯 **Core Abstraction Interview Questions**

---

# ✅ 1. What exactly is abstraction in Java, and how is it different from encapsulation?

- Follow-up:
  
  > Can a class achieve abstraction without using abstract classes or interfaces?

**Answer  -->**

---

**Abstraction** is the process of **hiding complex internal implementation** details and exposing only the **essential features** to the user.  
It allows users to interact with a system without needing to understand *how* it works internally — only *what* it does.

Encapsulation is the act of bundling data (fields) and the methods that operate on that data into a single unit, usually a class, while restricting direct access to the data using access control modifiers like `private`, `protected`, and `public`.

When a class follows the principles of data hiding and abstraction, it is called an **encapsulated class**.

**Abstraction is more about behavior, while encapsulation is about structure/data.**

- Abstraction → *focus on “what” to do*

- Encapsulation → *focus on “how” to restrict access*

> Example analogy:
> 
> - **Abstraction:** Using an ATM to withdraw money — you interact via simple options without knowing the internal banking operations.
> 
> - **Encapsulation:** You can't open the ATM and manipulate its machinery; the system protects its internal state and lets you interact only through controlled interfaces (buttons).

**Follow-up:**  
✅ *Yes, a class can achieve a degree of abstraction without abstract classes or interfaces — for example, by using access modifiers and hiding internal helper methods. But for full abstraction and polymorphic behavior, interfaces or abstract classes are typically used.*

```java
class Engine {
    public void start() {
        injectFuel();
        igniteSpark();
    }

    private void injectFuel() { /* internal */ }
    private void igniteSpark() { /* internal */ }
}
```

- No abstract class or interface involved.

- But abstraction is achieved — the user just calls `start()`, no idea what goes on inside.

---

# 2. How does Java achieve abstraction? Why does Java need both **abstract classes** and **interfaces**?

- Follow-up:
  
  > Can you give real-world scenarios where an abstract class would be more appropriate than an interface, and vice versa?
  
  ---

**Answer --->**

Java achieves **abstraction** using:

1. **Abstract Classes**

2. **Interfaces**

Both allow you to define *what* should be done, but not *how*, thereby hiding implementation details from the user and exposing only relevant features.

---

**✅ Why does Java need both abstract classes and interfaces ?**

Although both are used to achieve abstraction, they serve **different design purposes**:

| Aspect              | Abstract Class                         | Interface                                  |
| ------------------- | -------------------------------------- | ------------------------------------------ |
| Inheritance model   | Single inheritance                     | Multiple inheritance                       |
| Method types        | Can have abstract and concrete methods | From Java 8+, can have default/static too  |
| Constructor support | Yes                                    | No                                         |
| State/fields        | Can have instance variables            | Can only have `public static final` fields |
| Use case            | For closely related classes            | For loosely related or capability-based    |

Java needs both because:

- **Abstract classes** are ideal when you want to provide a base class with **shared code and partial implementation**.

- **Interfaces** are perfect for defining **contract-based behavior**, especially when **multiple inheritance of type** is required.

---

**🔄 Follow-up: Real-world scenarios**

✅ **Use Abstract Class When:**

> You have a **base entity** that multiple subclasses share a **common identity or behavior**.

**Example:**

```java
abstract class Animal {
    void breathe() { System.out.println("Breathing"); }
    abstract void makeSound();
}
class Dog extends Animal {
    void makeSound() { System.out.println("Bark"); }
}
```

CopyEdit

- But each has a different sound (abstract method).

- Abstract class fits well here as there's **shared code and identity.**

---

✅ **Use Interface When:**

> You want to define **capabilities** or **roles** that can be added to any class, regardless of its position in the class hierarchy.

```java
interface Flyable {
    void fly();
}
class Bird implements Flyable {
    public void fly() { System.out.println("Flying high"); }
}
class Drone implements Flyable {
    public void fly() { System.out.println("Hovering"); }
}

```

- A Bird and a Drone are **completely unrelated**, yet both can fly.

- Interface enables **capability modeling**, without affecting class hierarchy.

---

**🧠 Summary:**

> Use **abstract classes** when you need **shared base functionality and identity**.  
> Use **interfaces** when you need to define **capabilities** that can apply across unrelated classes.

**Analogy:**

- A **driving license**, which anyone can apply for — Doctor, Engineer, Teacher, Chef, no matter their background.

- A **family inheritance** — specific things passed down within a particular family (like heirlooms or family recipes).

---

# ✅ 3. Suppose I create an abstract class with only concrete methods and no abstract method. Is that valid? Why would I do that?

Yes, an abstract class with only concrete methods is valid.

**Why?**

Declaring a class as abstract **prevents it from being instantiated directly**, even if it has full method implementations. This is useful when:

- You want to **enforce inheritance**, i.e., the class is designed only to be extended.

- You want to provide **common functionality** (via concrete methods) to all subclasses.

- You want to **prevent direct usage**, even though you’re not forcing subclasses to override anything.

**Real-World Example:**

`javax.swing.AbstractAction` in Swing. It's abstract to prevent direct instantiation, but provides concrete methods for listeners, enabled state, etc., that subclasses like `MySaveAction` can reuse.



---

# ✅ 4. Can a constructor be present in an abstract class or an interface? If yes, how are they used or behave?

> 🔍 **Intent**: Clarifies **constructor rules** — a frequent confusion in interviews.

- Follow-up:
  
  > What actually happens when you instantiate a subclass of an abstract class — in what order do constructors run?

**Answer --->**

✅ **Yes**, an abstract class **can have a constructor**, but an interface **cannot**.

The purpose of a constructor in an abstract class is to **initialize common state or perform setup logic** needed by all subclasses.

Even though you **cannot create an object of an abstract class**, its **constructor is still called** when you instantiate a concrete subclass.

❌ **Interfaces don’t have constructors** because they don’t maintain state — they’re meant to define *behavioral contracts*, not object structure.

---

✅ **Constructor execution order:**

When a subclass of an abstract class is instantiated:

1. The **abstract class constructor runs first**.

2. Then the **subclass constructor runs**.

This follows Java's top-down constructor chaining (super → sub).

---
