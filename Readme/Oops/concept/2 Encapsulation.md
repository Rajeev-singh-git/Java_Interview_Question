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
10. [Constructor vs Instance Block](#constructor-vs-instance-block

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

# 
