# Design Patterns & Special Classes**

## Table of Contents

1. [Abstraction](#abstraction)
   - [Abstract Classes:](#-1-abstract-classes)
   - [Interface](#2-interface-)
2. [Encapsulation](#-encapsulation)(#constructor-vs-instance-block)

--- 

# 🧱  Singleton Classes

---

A **Singleton class** is a design pattern that ensures **only one instance** of the class exists **throughout the application**  lifecycle. It also provides a global access point to this single instance.

---

#### 🎯 Why Use Singleton?

---

- ✅ To control access to **shared resources** like database connections, file systems, or logging services

- ✅ To save memory and processing by avoiding unnecessary object creation

- ✅ To maintain a **consistent state** or configuration across the system

---

## Singleton Classes Example

---

1. `Runtime`

2. `ActionServlet` (used in Struts framework)

3. `ServiceLocator`

4. `BusinessDelegate`

---

```java
Runtime r1 = Runtime.getRuntime(); // factory method
Runtime r2 = Runtime.getRuntime();
Runtime r3 = Runtime.getRuntime();

System.out.println(r1 == r2); // true
System.out.println(r1 == r3); // true
```

✅ All references point to the **same object**.

---

## ✅ Advantages of Singleton

---

| Benefit                      | Description                                                                     |
| ---------------------------- | ------------------------------------------------------------------------------- |
| **Memory Efficient**         | Only one object is created, saving memory.                                      |
| **Global Access Point**      | Centralized access to shared resources (e.g., DB connection, config).           |
| **Controlled Instantiation** | You control when and how the instance is created.                               |
| **Thread-Safe (with care)**  | Can be made thread-safe (e.g., using `synchronized` or double-checked locking). |

---

### 🏗️ Creating Our Own Singleton Class

---

Singleton ensures only **one instance** of a class is created and provides a **global access point** to it.

#### 🔧 Key Components:

| 🔧 Component                              | 📋 Purpose                                             |
| ----------------------------------------- | ------------------------------------------------------ |
| **Private Constructor**                   | Prevents external instantiation of the class.          |
| **Static Variable**                       | Holds the **single instance** of the class.            |
| **Public Static Method** (Factory Method) | Provides **controlled access** to the single instance. |

---

##### ✅ Singleton Example: Creating our own Singleton class (Lazy Singleton)

---

```java
public class Singleton {

    // 1️⃣ Static variable to hold the single instance
    private static Singleton instance;

    // 2️⃣ Private constructor to prevent external instantiation
    private Singleton() {
        System.out.println("Singleton instance created");
    }

    // 3️⃣ Public static factory method to return the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();  // Lazy initialization
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

---

```java
public class Demo {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        obj1.showMessage();
        System.out.println("obj1 hashCode: " + obj1.hashCode());

        Singleton obj2 = Singleton.getInstance();
        System.out.println("obj2 hashCode: " + obj2.hashCode());

        System.out.println("Are both instances same? " + (obj1 == obj2)); // true
    }
}
```

**Output Example:**

```java
Singleton instance created
Hello from Singleton!
obj1 hashCode: 12345678
obj2 hashCode: 12345678
Are both instances same? true
```

--- 

## 🧱 Types of Singleton Implementations in Java

| Type                          | Description                           | Thread-Safe             | Instantiation Time |
| ----------------------------- | ------------------------------------- | ----------------------- | ------------------ |
| **1. Lazy Initialization**    | Creates instance **only when needed** | ❌ (unless synchronized) | Delayed            |
| **2. Eager Initialization**   | Creates instance **at class loading** | ✅                       | Immediate          |
| **3. Thread-Safe Singleton**  | Lazy + `synchronized` method/block    | ✅                       | Delayed            |
| **4. Double-Checked Locking** | Optimized thread-safe lazy init       | ✅                       | Delayed            |
| **5. Bill Pugh Singleton**    | Uses static inner class               | ✅                       | Delayed, efficient |
| **6. Enum Singleton**         | Best practice in Java                 | ✅                       | Immediate          |

---

#### ✅ 1. Lazy Initialization

---

🔹 *Creates the instance only when it is first requested (on-demand).*

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton(); // Lazy: only when needed
        }
        return instance;
    }
}
```

> ❌ Not thread-safe in multi-threaded applications.

---

#### 🚀 2. Eager Initialization

---

🔹 *Instance is created at the time of class loading, regardless of whether it's used.*

```java
public class Singleton {
    private static final Singleton instance = new Singleton(); // Eager init

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

> ✅ Thread-safe, but may waste memory if not used.

---

#### 🔒 3. Thread-Safe Singleton (`synchronized` method)

---

🔹 *Adds synchronization to make lazy initialization thread-safe.*

```java
private Singleton() {}

public static synchronized Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }
    return instance;
 }
}
```

> ✅ Safe for multithreaded use but slower due to synchronization.

---

#### ⚙️ 4. Double-Checked Locking Singleton

---

🔹 *Optimized thread-safe version that avoids unnecessary locking.*

```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

> ✅ High performance + thread safety — commonly used.

---

#### 🧪 5. Bill Pugh Singleton (Static Inner Class)

---

```java
public class Singleton {
    private Singleton() {}

    private static class Helper {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Helper.INSTANCE;
    }
}
```

> ✅ Lazy, thread-safe, and avoids synchronization overhead.

---

#### 🏆 6. Enum Singleton (Best Practice)

---

🔹 *Uses Java’s `enum` to provide inherent serialization + thread safety.*

```java
public enum Singleton {
    INSTANCE;

    public void showMessage() {
        System.out.println("Hello from Enum Singleton!");
    }
}
```

**Usage:**

```java
Singleton.INSTANCE.showMessage();
```

> ✅ Most robust and recommended by *Effective Java*.

---

## 🤹‍♀️ Multi-ton Variants (Doubleton, Tripleton...)

---

A **Multiton pattern** allows **a fixed number** of object instances — like Singleton, but more than one, e.g., 2 (Doubleton), 3 (Tripleton), etc.

---

## ✅ 1. Doubleton Pattern

---

🔹 *Allows only two instances of the class and reuses them in round-robin style.*

```java
public class Doubleton {

    private static final Doubleton[] instances = new Doubleton[2];
    private static int counter = 0;

    private Doubleton() {
        System.out.println("Doubleton instance created");
    }

    public static Doubleton getInstance() {
        int index = counter % 2;
        if (instances[index] == null) {
            instances[index] = new Doubleton();
        }
        counter++;
        return instances[index];
    }

    public void printHash() {
        System.out.println("Instance Hash: " + this.hashCode());
    }
}
```

**🧪 Test Class :**

```java
public class Test {
    public static void main(String[] args) {
        Doubleton a = Doubleton.getInstance();
        Doubleton b = Doubleton.getInstance();
        Doubleton c = Doubleton.getInstance();

        a.printHash();
        b.printHash();
        c.printHash();

        System.out.println(a == b); // false
        System.out.println(a == c); // true
    }
}
```

---

###### 📌 Notes:

- This version uses **eager initialization** — both instances are created when the class loads. If memory optimization is a concern, you can revert to lazy logic.

- For most practical Doubleton use cases, **eager is fine** and simplifies logic, especially in single-threaded or simple applications.

---

## ✅ 3. Tripleton Pattern

🔹 *Extends the same logic to allow exactly three reusable instances.*

```java
public class Tripleton {

    private static final Tripleton[] instances = new Tripleton[3];
    private static int counter = 0;

    private Tripleton() {
        System.out.println("Tripleton instance created");
    }

    public static Tripleton getInstance() {
        int index = counter % 3;
        if (instances[index] == null) {
            instances[index] = new Tripleton();
        }
        counter++;
        return instances[index];
    }

    public void printHash() {
        System.out.println("Instance Hash: " + this.hashCode());
    }
}
```

**🔍 Test Usage:**

```java
public class TestTripleton {
    public static void main(String[] args) {
        Tripleton a = Tripleton.getInstance();
        Tripleton b = Tripleton.getInstance();
        Tripleton c = Tripleton.getInstance();
        Tripleton d = Tripleton.getInstance(); // Will reuse 'a'

        a.printHash();  // Same as d
        b.printHash();
        c.printHash();
        d.printHash();

        System.out.println(a == d); // true
    }
}
```

---

### 📚 Summary: Singleton vs Multiton

| Pattern   | Max Instances | Use Case Example            |
| --------- | ------------- | --------------------------- |
| Singleton | 1             | Logger, config, cache       |
| Doubleton | 2             | Alternate resource pools    |
| Tripleton | 3             | Limited background threads  |
| Multiton  | N             | Object pool, shard managers |

---

## ❓ **How can we prevent inheritance without using `final`?

---

Even if a class is **not marked as `final`**, we can still **prevent other classes from extending it**.

###### ✅ **How?**

By declaring **all constructors as `private`**.

```java
class Parent {
    private Parent() {
        // private constructor: no one outside can call it
    }
}
```

### 🔒 Why does this prevent inheritance?

- In Java, when a child class is created, its constructor must **implicitly or explicitly call a constructor of its parent class**.

- But if **all constructors in the parent class are `private`**, the **child class can't call them**, hence **compilation fails**.

---

# 🏭 Factory Method

---

A **Factory Method** is a static method that:

- Is called using the **class name**

- **Returns an instance** of that class (or its subclass)

> In simple terms:  
> **If a static method returns an object of the same class**, it's a factory method.

---

✅ **Example 1: `Runtime.getRuntime()`**

```java
Runtime r = Runtime.getRuntime();
```

- `getRuntime()` is a static method of the `Runtime` class.

- It **returns the singleton instance** of `Runtime`.

- You **cannot directly create** a `Runtime` object using `new`.

---

✅ **Example 2: `DateFormat.getInstance()`**

```java
DateFormat df = DateFormat.getInstance();
```

- `getInstance()` returns a concrete subclass object of the abstract class `DateFormat`.

- You **don’t know or care** which exact subclass is returned.

- The object creation is **abstracted behind the factory method**.

---

## 🎯 Why use a Factory Method?

Factory methods are useful when:

- Object creation is **complex or involves logic**

- You want to **reuse an existing object** (like Singleton)

- You want to **hide object creation logic**

- You need to **enforce constraints** during object creation

---

## 🔍 Factory Method vs Constructor

| Feature                    | Factory Method                  | Constructor                  |
| -------------------------- | ------------------------------- | ---------------------------- |
| Can have a name            | ✅ Yes (`getInstance()`, `of()`) | ❌ No (must match class name) |
| Can return existing object | ✅ Yes                           | ❌ Always returns new object  |
| Can return subtype         | ✅ Yes                           | ❌ No                         |
| Supports abstraction       | ✅ Yes                           | ❌ No                         |
| Can control instance count | ✅ Yes (e.g., Singleton, Pool)   | ❌ No control                 |




---

### 🛠️ Custom Factory Method Example

```java
class Test {
    private static Test instance;

    private Test() {} // private constructor

    public static Test getInstance() {
        if (instance == null) {
            instance = new Test();
        }
        return instance;
    }
}
```

Usage:

```java
Test t1 = Test.getInstance();
Test t2 = Test.getInstance();

System.out.println(t1 == t2); // true — same object
```
