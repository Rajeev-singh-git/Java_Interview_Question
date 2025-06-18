# Types of Class Relationships (IS-A vs HAS-A)

## Table of Contents

1. [Inheritance (IS-A Relationship)](#inheritance)
      - [Key Concepts](#-inheritance-key-concepts)
      - [Advantages of Inheritance](#-advantages-of-inheritance-)
      - [How Inheritance Behaves](#-conclusion-on-inheritance-behavior-in-java)
      - [System-wide Inheritance Examples](#inheritance-system-wide-examples)
   - [Multiple Inheritance](#multiple-inheritance-)
      - [Why Java disallows it with classes](#-why-java-wont-provide-support-for-multiple-inheritance)
      - [Diamond Problem](#-the-diamond-problem)
      - [How Interfaces Solve It](#-interfaces-to-the-rescue)
3. [HAS-A Relationship (Composition & Aggregation)](#-has-a-relationship)
   - [Composition (Strong Association)](#-composition)
   - [Aggregation (Weak Association)](#aggregation)
   - [Composition vs Aggregation](#-composition-vs-aggregation--key-differences)
   - [When to use what](#-when-to-use-what)
4. [Method Signature in Java](#-method-signature-in-java)
   - [Overloading Rules](#-valid-overloading-example)

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

