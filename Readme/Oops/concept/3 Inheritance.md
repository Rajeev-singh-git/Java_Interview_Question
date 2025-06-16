# Oops

## Table of Contents


3. [Inheritance](#inheritance)
   - [Inheritance Behaviour](#-conclusion-on-inheritance-behavior-in-java)
   - [Multiple Inheritance](#multiple-inheritance-)


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



