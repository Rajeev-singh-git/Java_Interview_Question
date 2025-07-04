# Encapsulation and Abstraction

## Table of Contents

- [Abstraction](#abstraction)
  - [Abstract Classes:](#-1-abstract-class)
  - [Interface](#-interface-key-features-jdk-timeline)
  - [Abstract Class vs Interface](#-abstract-class-vs-interface-in-java)
  - [When to use what](#-when-to-use-interface-or-abstract-class)
  - [Interface Interview Questions](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/interview/Abstraction%20Interview%20Question.md)

- [Encapsulation](#-encapsulation)
  - [Tightly Encapsulated Class](#-tightly-encapsulated-class-in-java)
  - [Encapsulation Interview Questions](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/interview/Encapsulation%20Interview%20Question.md)

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

[Abstract class code example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/code/Abstract_Class_Code_Example_README.md)

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

[Interface Code Examples](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/code/Interface_Code_Example_README.md)

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

[Abstraction Interview Questions](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/interview/Abstraction%20Interview%20Question.md)

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

[Encapsulation Interview Questions](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Oops/interview/Encapsulation%20Interview%20Question.md)

---


