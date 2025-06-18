# Constructors and Object Creation

## Table of Contents

7. [Overloading vs Overriding](#-overloading-vs-overriding)
8. [Different ways to create Object in Java](#-how-many-ways-can-we-create-an-object-in-java)
9. [Constructor](#constructor)
10. [Constructor vs Instance Block](#constructor-vs-instance-block)
    
    

---

# 🧱 Object creation in Java

**Object creation isn't just about `new` — it's about controlling instantiation behavior, object lifecycle, memory handling, and flexibility.** Each method reflects a different paradigm of design.

---

## 🧱 How Many Ways Can We Create an Object in Java?

Java offers **five primary ways** to create or retrieve an object:

---

### 1️⃣ **Using `new` Operator (Standard Instantiation)**

➡️ The most common and straightforward way.

```java
Test t = new Test();
```

✅ **When to use**: Regular object creation with constructor logic. 

---

### 2️⃣ **Using Reflection (`newInstance()` or Constructor API)**

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

### 3️⃣ **Using `clone()` Method (Cloning)**

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

➡️ Static method returns an instance instead of using `new` directly.

```java
Runtime r = Runtime.getRuntime();
DateFormat df = DateFormat.getInstance();
```

✅ **When to use**: Singleton enforcement, abstract return types, or controlled instantiation.

🧠 Often used in **design patterns** (Factory, Singleton, Builder).

---

### 5️⃣ **Using Deserialization (Object Stream)**

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

## Rules to write Constructor :→

1. The **constructor name must  match the class name** (case-sensitive).
2. Constructors **do not have a return type**, not even `void`. If you declare a return type, the compiler treats it as a **regular method**, not a constructor. This means the class will have no constructor matching that signature, which can lead to errors when creating objects.
3. Although Java allows you to write a **method with the same name as the class**, this is discouraged because it can cause confusion—such a method is **not a constructor**.
4. Allowed modifiers: `public`, `protected`, default (no-modifier), `private`.
5. **Not allowed**: `static`, `final`, `abstract`.

---

## Default Constructor :→

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

![Screenshot 2024-02-17 143913](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/deb1dafc-2f29-4a02-a010-f6a4091b143a)

![Screenshot 2024-02-17 143532](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/4bd6c50c-e5b8-4b29-bfda-b22ebade5701) 

### `super()` vs `super` & `this()` vs `this`

These are **frequently confused pairs** — even though they look similar, their usage and behavior differ significantly:

| Keyword   | Type             | Purpose                                         | Where Can You Use It?                                     |
| --------- | ---------------- | ----------------------------------------------- | --------------------------------------------------------- |
| `this()`  | Constructor Call | Calls **another constructor** in the same class | Inside a constructor                                      |
| `this`    | Reference        | Refers to **current object**                    | Anywhere inside the class (methods, constructors, blocks) |
| `super()` | Constructor Call | Calls **parent constructor**                    | Inside a constructor                                      |
| `super`   | Reference        | Refers to **parent object**                     | Inside child class methods or constructors                |

---

#### 📍 `this()` → Calls another constructor

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

### Constructor Chaining

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

#### What happens if you don’t explicitly call `super()` or `this()` in the first line of a constructor?

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

#### What happens if parent class lacks a no-arg constructor?

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

**Output : ⛔ Compile-time Error:

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

## 🔃 Overloaded Constructors :→

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

### Recursive Constructor Calls

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

✅ **Fix:**

```java
Test() {
    // no this() here
}
```

---

### 👨‍👧 Constructor Behavior in Inheritance

- A **child class object** does **not** create a separate parent object.

- Instead, it includes the **parent’s state**.

- Hence, **parent constructor always runs first**.

```java
abstract class Parent {
    Parent() {
        System.out.println(this.hashCode()); // refers to Child
    }
}

class Child extends Parent {
    Child() {
        System.out.println(this.hashCode()); // same as above
    }
}

public class Demo {
    public static void main(String[] args) {
        Child c = new Child();
        System.out.println(c.hashCode()); // all 3 values same
    }
}
```

---

#### ❗ Constructor Exception Rule

```java
class Parent {
    Parent() throws IOException {}
}

class Child extends Parent {
    Child() throws Exception {
        super(); // valid: Exception is broader than IOException
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

#### 🚫 Why Do Abstract Classes Have Constructors?

We **cannot create objects** of an abstract class directly, but an **abstract class can have constructors**.

##### 🤔 Why?

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
