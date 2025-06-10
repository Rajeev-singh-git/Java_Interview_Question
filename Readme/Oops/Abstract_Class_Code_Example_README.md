# 🧱 Abstract Class – Conceptual Code Examples

## Table of Contents

1. [Abstraction](#abstraction)
   - [Abstract Classes:](#-1-abstract-classes)
   - [Interface](#2-interface-)
2. [Encapsulation](#-encapsulation)
   - [Tightly Encapsulated Class](#-tightly-encapsulated-class-in-java)

---

# 🧱 Conceptual Code Examples

These examples are structured for **progressive understanding** — starting from the core concept and slowly introducing advanced behavior and common interview pitfalls.

---

## ✅ 1. Core Concept: What is an Abstract Class?

```java
abstract class Animal {
    abstract void makeSound();
}

// Subclass must implement the abstract method
class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof!");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();     
        d.makeSound();         // Output: Woof!

       // Animal a = new Animal(); // ❌ Error: Cannot instantiate abstract class
    }
}


```

Output ->

```java
 Woof!
```

**✅ Concepts Highlighted**

- ✅ **Abstract class**: Cannot be instantiated; can have abstract methods.

- ✅ **Subclass obligation**: Must implement all abstract methods.

- ✅ **Basic inheritance**: Child class uses `extends` to inherit and override.



---

## 🧠 2. Behavior Deep Dive: Constructor, Final Method, Polymorphism

```java
abstract class Animal {
    Animal() {
        System.out.println("Animal constructor");
    }

    abstract void makeSound();     // Must be implemented

    void sleep() {
        System.out.println("Sleeping...");
    }

    final void breathe() {
        System.out.println("Breathing...");
    }
}

class Cat extends Animal {
    Cat() {
        System.out.println("Cat constructor");
    }

    @Override
    void makeSound() {
        System.out.println("Meow!");
    }

    // void breathe() {} // ❌ Error: breathe() is final
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Cat();  // ✅ Polymorphic reference
        a.makeSound();         // Meow!
        a.sleep();             // Sleeping...
        a.breathe();           // Breathing...    
    }
}


```

Output :-->

```java
Animal constructor 
Cat constructor
Meow!
Sleeping...
Breathing...
```

**🔍 Concepts Highlighted**

- ✅ **Abstract class**: Cannot be instantiated; can have both abstract and concrete methods.

- ✅ **Constructor chaining**: `Animal()` constructor runs **before** `Cat()` constructor.

- ✅ **Polymorphism**: Reference type is `Animal`, object type is `Cat`.

- ✅ **Final method**: `breathe()` cannot be overridden.

- ✅ **Concrete method (`sleep`)**: Inherited as-is, can be called by subclass instances.



**✅ Key Principle**

> **Parent reference *can point to* child object, but it can only *access* methods declared in the parent class/interface.**  
> However, if those methods are overridden in the child class, the **child’s version will be called at runtime**.

This is the essence of **dynamic method dispatch**.

---

## 🚧 3. Interview Pitfall: Missing Abstract Method Implementation

```java
abstract class Animal {
    abstract void makeSound();
}

// ❌ Compilation Error: Class must implement abstract method
class Bird extends Animal {
    // No makeSound() provided
}

public class Main {
    public static void main(String[] args) {
        Bird b = new Bird();  // ❌ Error!
    }
}

```

#### 🧠 Concepts Highlighted

- ⚠️ **Unimplemented abstract method**: Subclass must implement or be declared abstract.

- 🚫 **Cannot instantiate**: Compilation fails if subclass skips required implementation.

- 🧩 **Enforced contract**: Abstract methods create a contract that must be fulfilled


