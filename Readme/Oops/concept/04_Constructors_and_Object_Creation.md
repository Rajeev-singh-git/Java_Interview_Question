# Constructors and Object Creation

## Table of Contents

1. [Object creation in Java](#-object-creation-in-java)
     - [Number of ways to create an object](#-how-many-ways-can-we-create-an-object-in-java)
     - [Object creation summary](#-summary-table)
  
2. [Constructor](#-constructor)
     - [Rules to write Constructor](#rules-to-write-constructor-)
     - [Constructor vs Instance Block](#constructor-vs-instance-block)
     - [Default Constructor](#default-constructor-)
         - [Prototype of Default Constructor](#prototype-of-default-constructor-)
     - [super() vs this()](#-super-vs-this)
         - [super() vs super & this() vs this](#super-vs-super--this-vs-this)
     - [Constructor Chaining](#-constructor-chaining)
          - [1. Within Same Class — using this()](#-1-within-same-class--using-this)
          - [2. Across Class Hierarchy — using super()](#-2-across-class-hierarchy--using-super)
          - [Recursive Constructor Calls](#recursive-constructor-calls)
     - [Missing No-Arg Parent Constructor](#-missing-no-arg-parent-constructor)
     - [Overloaded Constructors](#-overloaded-constructors-)
     - [Constructor rule with respect to Inheritance](#constructor-rule-with-respect-to-inheritance)
           - [Why Do Abstract Classes Have Constructors?](#-why-do-abstract-classes-have-constructors)
           - [Exception Handling in Constructors](#exception-handling-in-constructors)
      - [Constructor Behavior](#constructor-behavior-compiler-logic)
      - [Parameterized Parent Constructors](#caveat-parameterized-parent-constructors)
       
---

# 🧱 Object creation in Java

**Object creation isn't just about `new` — it's about controlling instantiation behavior, object lifecycle, memory handling, and flexibility.** Each method reflects a different paradigm of design.

---

## 🧱 How Many Ways Can We Create an Object in Java?

Java offers **five primary ways** to create or retrieve an object:

---

### 1️⃣ **Using `new` Operator (Standard Instantiation)**

---

➡️ The most common and straightforward way.

```java
Test t = new Test();
```

✅ **When to use**: Regular object creation with constructor logic. 

---

### 2️⃣ **Using Reflection (`newInstance()` or Constructor API)*

---

➡️ Dynamically loads and instantiates class using reflection.

```java
Test t = (Test) Class.forName("Test").newInstance(); // Deprecated in Java 9+
```

📝 *From Java 9 onwards, `newInstance()` is deprecated.*

✅ Modern alternative:

```java
Test t = Test.class.getDeclaredConstructor().newInstance();
```

✅ **When to use**: Frameworks, dynamic class loading, dependency injection containers.

⚠️ **Pitfalls**: Must handle checked exceptions (`IllegalAccessException`, `InvocationTargetException`).

---

### 3️⃣ **Using `clone()` Method (Cloning)*

---

➡️ Creates a **copy** of an existing object.

```java
Test t1 = new Test();
Test t2 = (Test) t1.clone();
```

🔒 Requirements:

- Class must `implement Cloneable`

- Override `clone()` from `Object`

✅ **When to use**: Copying objects when constructors are expensive or unavailable.

⚠️ **Caution**: `clone()` performs a **shallow copy** by default. Manual deep copy is often preferred.

---

### 4️⃣ **Using Factory Methods**

---

➡️ Static method returns an instance instead of using `new` directly.

```java
Runtime r = Runtime.getRuntime();
DateFormat df = DateFormat.getInstance();
```

✅ **When to use**: Singleton enforcement, abstract return types, or controlled instantiation.

🧠 Often used in **design patterns** (Factory, Singleton, Builder).

---

### 5️⃣ **Using Deserialization (Object Stream)**

--- 

➡️ Reads a previously serialized object from a file/stream.

➡️ Reconstructs an object from a serialized form.

```java
FileInputStream fis = new FileInputStream("abc.ser");
ObjectInputStream ois = new ObjectInputStream(fis);
Test t = (Test) ois.readObject();
```

✅ **When to use**: Persistent state recovery, distributed systems.

🧠 **Note**: Constructor is **not called** during deserialization — internal JVM logic restores state.*

---

## 🔁 Summary Table

---

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

# 🧱 Constructor

---

In Java, whenever an object is created, a special block of code runs automatically to initialize the new object. This block of code is called  **constructor**.

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

> A **constructor** is a special block of code that gets executed automatically when an object is created.

---

## Rules to write Constructor :→

---

1. The **constructor name must match the class name** (case-sensitive).
2. Constructors **do not have a return type**, not even `void`. If you declare a return type, the compiler treats it as a **regular method**, not a constructor. This means the class will have no constructor matching that signature, which can lead to errors when creating objects.
3. Although Java allows you to write a **method with the same name as the class**, this is discouraged because it can cause confusion—such a method is **not a constructor**.
4. Allowed modifiers: `public`, `protected`, default (no-modifier), `private`.
5. **Not allowed**: `static`, `final`, `abstract`.

---

## Constructor vs Instance Block

---

- Both **instance blocks** and **constructors** execute automatically every time an object is created. The instance block runs **first**, followed by the constructor.

- Instance blocks are typically used to perform common initialization or actions for all constructors, without duplicating code.

- **Important:** Instance blocks **cannot take arguments**, while constructors can. Therefore, constructors and instance blocks serve different purposes and cannot replace each other.

Code Example :→

```java
class Test {

    // Static variable to count instances of Test
    static int count = 0; 

    // Instance initialization block: runs before every constructor
    { count++; }

    Test() {} // Default constructor
    Test(int i) {}  // Parameterized constructor 

   public static void main(String[] args) {
        Test t1 = new Test();
        Test t2 = new Test(10);
        Test t3 = new Test();
        System.out.println(count); // Output: 3
    }
}
```

| Feature              | Constructor                      | Instance Block                 |
| -------------------- | -------------------------------- | ------------------------------ |
| Executes when?       | On object creation               | Before constructor, every time |
| Can take parameters? | ✅ Yes                            | ❌ No                           |
| Purpose              | Initialize object-specific state | Shared/common initialization   |

---

## Default Constructor :→

---

- Every class in Java, including abstract classes, can have constructors. Although abstract classes cannot be instantiated directly, their constructors are called during the creation of subclass objects.

- If **no constructor** is explicitly written in a class, the Java compiler automatically generates a **default (no-argument) constructor** for that class.

- If the programmer **writes any constructor** (with or without parameters), the compiler **does not** generate a default constructor. Hence, a class contains either the compiler-generated default constructor or the programmer-defined constructor(s), but **not both simultaneously**.
  
  ```java
  class A {
      A() {
          System.out.println("Custom constructor");
      }
  }
  // No default constructor here because one is explicitly defined.
  ```

### Prototype of Default Constructor :→

1. The default constructor is always a **no-argument constructor**.
2. The access modifier of the default constructor **matches the access modifier of the class**. This applies only if the class is either `public` or has default (package-private) access.
3. The default constructor contains a single statement:  
   `super();` — a no-argument call to the superclass constructor.

![wmremove-transformed](https://github.com/user-attachments/assets/1731e5ac-bf6f-4d38-9df4-e31b585a25ca)

---

## 🔄 `super()` vs `this()`

---

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

| Aspect                       | `super()`                    | `this()`                                    |
| ---------------------------- | ---------------------------- | ------------------------------------------- |
| Purpose                      | Calls **parent constructor** | Calls **another constructor in same class** |
| Must be first line?          | ✅ Yes                        | ✅ Yes                                       |
| Compiler inserts if missing? | ✅ `super()`                  | ❌                                           |

```kotlin
Inside any constructor:

┌────────────┬─────────────────────────────────────────────┐
│ `this()`   │ Calls another constructor in the same class │
├────────────┼─────────────────────────────────────────────┤
│ `super()`  │ Calls a constructor in the parent class     │
└────────────┴─────────────────────────────────────────────┘

⚠️ Only one can appear, and must be the first statement.
```

---

### What happens if you don’t explicitly call `super()` or `this()` in the first line of a constructor?

---

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

Output : ->

```java
Parent constructor
Child int-arg constructor: 10
Child no-arg constructor
```

--- 

### `super()` vs `super` & `this()` vs `this`

---

These are **frequently confused pairs** — even though they look similar, their usage and behavior differ significantly:

| Keyword   | Type             | Purpose                                         | Where Can You Use It?                                     |
| --------- | ---------------- | ----------------------------------------------- | --------------------------------------------------------- |
| `this()`  | Constructor Call | Calls **another constructor** in the same class | Inside a constructor                                      |
| `this`    | Reference        | Refers to **current object**                    | Anywhere inside the class (methods, constructors, blocks) |
| `super()` | Constructor Call | Calls **parent constructor**                    | Inside a constructor                                      |
| `super`   | Reference        | Refers to **parent object**                     | Inside child class methods or constructors                |

---

#### 📍 `this()` → Calls another constructor

---

```java
class Demo {
    Demo() {
        this(10); // calls Demo(int)
    }

    Demo(int x) {
        System.out.println("Constructor with int: " + x);
    }
}
```

#### 📍 `this` → Refers to current object

---

```java
class Demo {
    int x;

    void setX(int x) {
        this.x = x; // left = instance, right = parameter
    }
}
```

---

#### 📍 `super()` → Calls parent constructor

---

```java
class Parent {
    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    Child() {
        super(); // optional, compiler adds it
        System.out.println("Child constructor");
    }
}
```

#### 📍 `super` → Refers to parent object (non-constructor context)

---

```java
class Parent {
    int x = 10;
}

class Child extends Parent {
    int x = 20;

    void show() {
        System.out.println(this.x);   // 20
        System.out.println(super.x);  // 10
    }
}
```

###### ⚠️ Key Rules Recap

- ❗ Both `this()` and `super()` must be **first statements** in a constructor.

- ❌ You **cannot use both** in the same constructor.

- ✅ `this` and `super` (without parentheses) can be used **anywhere** inside the class.

-----

## 🔗 Constructor Chaining

---

Constructor chaining refers to the process of calling one constructor from another within the same class or from a subclass constructor.

- Within the **same class** using `this(...)`

- From a **subclass to superclass** using `super(...)`

It helps avoid code duplication and ensures consistent initialization.

---

##### Why Use Constructor Chaining?

- 🔁 **Code reuse** — centralize common logic.

- 🔒 **Consistent initialization** — avoid errors.

- 🧬 **Inheritance** — superclass constructors must run before subclass ones.

---

### 🔄 **1. Within Same Class** — using `this()`

---

```java
class A {
    A() {
        this(10);                  // calls A(int)
        System.out.println("Default Constructor");
    }

    A(int x) {
        System.out.println("Parameterized Constructor: " + x);
    }

    public static void main(String[] args) {
        new A();
    }
}
```

🟢 **Output:**

```java
Parameterized Constructor: 10
Default Constructor
```

✅ `this()` must be the **first statement** in the constructor.

---

### 🧬 **2. Across Class Hierarchy** — using `super()`

---

```java
class Parent {
    Parent() {
        System.out.println("Parent Constructor");
    }
}

class Child extends Parent {
    Child() {
        super();                    // calls Parent()
        System.out.println("Child Constructor");
    }

    public static void main(String[] args) {
        new Child();
    }
}
```

🟢 **Output:**

```java
Parent Constructor
Child Constructor
```

✅ `super()` is used to call the **parent class constructor**, and is **implicitly added** if not specified.

---

### 🧪Recursive Constructor Calls

---

###### 🔂 Common Mistake

```java
class A {
    A() {
        this(); // ❌ Infinite recursion
    }
}
```

🚫 ❌ **Compile-time error**: Constructor is calling itself — leads to **recursive call without termination**, which is illegal.

**🔁 Indirect Recursion**

```java
class Test {
    Test(int i) {
        this(); // calls Test()
    }

    Test() {
        this(10); // calls Test(int)
    }

    // ❌ Compile-Time Error: Recursive constructor invocation
}
```

> 🔁 Java detects infinite constructor calls **at compile time**, not runtime.

✅ Fix:

```java
Test() {
    // no this() here
}
```

---

### 🚫 Missing No-Arg Parent Constructor

---

##### What happens if parent class lacks a no-arg constructor?

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

Output : ⛔ Compile-time Error:

```java
constructor Parent in class Parent cannot be applied to given types;
  required: int
  found: no arguments
```

---

## 🔃 Overloaded Constructors :→

---

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
        Test t1 = new Test();      // Chained: no-arg → int → double
        Test t2 = new Test(10);    // Chained: int → double
        Test t3 = new Test(10.5);  // Direct: double
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

## Constructor rule with respect to Inheritance

---

###### ✅ Constructors Are Not Inherited

- Constructors are **not inherited** from parent classes in Java.

- Therefore, **constructor overriding is not possible**.

- However, constructors **can be overloaded** within the same class.

###### ✅ Constructors Exist in Classes, Not Interfaces

- You can define constructors in any class, including **abstract classes**.

- But **interfaces cannot have constructors** — because they aren’t meant for object instantiation.

---

### 🚫 Why Do Abstract Classes Have Constructors?

---

We **cannot directly create objects** of an abstract class.  
But abstract classes **can have constructors** to initialize the **inherited part** of a subclass object.

#### 🧠 Example: `hashCode()` shows only one object is created

```java
abstract class Parent {
    Parent() {
        System.out.println(this.hashCode()); // Refers to Child object
    }
}

class Child extends Parent {
    Child() {
        System.out.println(this.hashCode()); // Same as above
    }
}

class Test {
    public static void main(String[] args) {
        Child c = new Child();
        System.out.println(c.hashCode()); // All three same
    }
}
```

Output →

```java
1072408673
1072408673
1072408673
```

###### ✅ Key Insight:

- All three `hashCode()` calls print the **same value**, proving that **only one object (of `Child`) is created**.

- The abstract class constructor executes to **initialize the Parent part** of the `Child` object.

---

#### 🔍 Why is it the *Child’s* hashCode?

---

- When you call `new Child()`, Java starts creating an object of **type `Child`**.

- Before the `Child` constructor runs, Java **first calls the `Parent` constructor** (via implicit `super()`).

- Inside the `Parent` constructor, you're calling `this.hashCode()` — but `this` refers to the actual object being created, which is of type `Child`.

```java
abstract class Parent {
    Parent() {
        System.out.println(this.hashCode());  // 👈 this is a Child object
    }
}
```

Even though the call is in the `Parent` class, it’s **executing on the Child object**, because **Java constructs only one object** — the most-derived type (`Child` in this case).

---

🔁 All 3 Calls Use the Same Object

```java
Child c = new Child();

System.out.println(this.hashCode()); // in Parent() → Child object
System.out.println(this.hashCode()); // in Child()  → Child object
System.out.println(c.hashCode());    // in main     → Child object
```

They all print the **same number**, because it's all the **same object in memory**.

---

#### 🔐 Side Note on `hashCode()`

- `Object.hashCode()` by default returns a **memory-based value (object identity)**.

- If you don't override `hashCode()` in `Child`, the default one is used.

- So when you print `this.hashCode()` in any constructor, you're printing the **JVM identity hashCode** for the same object.

---

### Exception Handling in Constructors

---

❗ Constructor Exception Rule

```java
class Parent {
    Parent() throws IOException {}
}

class Child extends Parent {
    Child() throws Exception {
        super(); // ✅ Valid: Exception is broader than IOException
    }
}
```

🧠 Java allows the child constructor to declare a **broader checked exception**, but not a **narrower or unrelated one**.

```java
// ❌ Invalid
class Child extends Parent {
    Child() throws SQLException {
        super(); // CE: SQLException not thrown by parent
    }
}
```

###### ✅ Summary:

| Rule                                                          | Allowed? |
| ------------------------------------------------------------- | -------- |
| Child throws broader checked exception (e.g., Exception)      | ✅ Yes    |
| Child throws unrelated checked exception (e.g., SQLException) | ❌ No     |
| Child throws narrower exception                               | ❌ No     |

---

## Constructor Behavior (Compiler Logic)

---

### 🧱 What the Java Compiler Does Automatically

- ✅ If **no constructor** is defined → inserts a **default no-arg constructor**

- ✅ If a constructor is defined and doesn’t call `super()` or `this()` → inserts **`super()` automatically**

- ❌ Detects and prevents **recursive constructor calls** (direct or indirect)

###### ❌ Invalid Example: Recursive Constructor Invocation

```java
class Test {
    Test(int i) {
        this(); // Calls no-arg constructor
    }

    Test() {
        this(10); // Calls parameterized constructor → infinite loop
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}

// CE: Recursive constructor invocation
```

**✅ Corrected Version:**

```java
class Test {
    Test(int i) {
        this(); // OK: Calls no-arg constructor
    }

    Test() {
        // No recursion
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
```

---

## Caveat: Parameterized Parent Constructors

---

#### ⚠️ Constructor Inheritance Caveat

If the **parent class defines only parameterized constructors**, then:

- The **child class must explicitly call** a matching constructor using `super(args...)`.

- ❌ If no call is made, and the parent has **no no-arg constructor**, the code **will not compile**.

> ✅ **Best practice**: Always define a **default constructor** if there’s a parameterized one — unless you're absolutely sure subclasses will always pass arguments.

---

#### 💡 Recap of Exception Example:

```java
class Parent {
    Parent() throws java.io.IOException {
    }
}

class Child extends Parent {
    Child() throws Exception {
        super(); // Valid
    }
}
```

- ✅ This works because `Exception` is the **superclass of IOException**

- ✅ Java permits **broader checked exceptions** in child constructors

---

#### ✅ Summary Table: Validity Rules

---

| Case                                                               | Valid? | Why                                |
| ------------------------------------------------------------------ | ------ | ---------------------------------- |
| Child throws broader exception (e.g., Exception)                   | ✅      | Covers IOException                 |
| Child throws narrower exception                                    | ❌      | Parent may not throw it            |
| Child throws unrelated exception (e.g., SQLException)              | ❌      | Parent doesn't declare it          |
| Child doesn't call `super()` and parent has no default constructor | ❌      | Compiler requires an explicit call |
| Recursive constructor call via `this()`                            | ❌      | Compiler error                     |
