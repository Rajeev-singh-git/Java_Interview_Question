# Static and Instance Control Flow in Java

## Table of Contents

1. [Static Control Flow](#static-control-flow)
   - [Static Order of Execution](#-order-of-execution)
   - [Static Control Flow Example](#static-control-flow-example-)
   - [Read Indirectly, Write Only (RIWO)](#read-indirectly-write-only-riwo)
   - [Illegal Forward Reference (Direct Read)](#-illegal-forward-reference-direct-read)
   - [Indirect Read Is Allowed (RIWO)](#-indirect-read-is-allowed-riwo)
   - [Write Access Before Declaration — Legal](#-write-access-before-declaration--legal)
   - [Static Control Flow: Parent → Child](#-static-control-flow-parent--child)
   - [Order of Execution When Inheritance Is Involved](#order-of-execution-when-inheritance-is-involved)
   - [Static control flow inheritance example](#static-control-flow--inheritance-example)
   - [ Static Blocks](#-static-blocks)
2. [Instance Control Flow](#-instance-control-flow)
   - [Order of Execution (Single Class)](#-order-of-execution-single-class)
   - [Example: Single Class](#-example-single-class)
   - [nstance Control Flow in Parent-Child Classes](#-instance-control-flow-in-parent-child-classes)
   - [ Execution Flow (Parent → Child)](#execution-flow-parent--child)
3. [Static vs Instance Control Flow](#-static-vs-instance-control-flow)
   - [Examples with Execution Order](#-examples-with-execution-order)
   - [Static vs Instance Access Rules](#-static-vs-instance-access-rules-quick-note)

---

# Static Control Flow

---

Static control flow refers to the **order in which static variables and blocks are executed** at the time of **class loading**, before the `main()` method runs.

---

## 🚦 Order of Execution

1. **Identification of static members** (from top to bottom).

2. **Execution of static variable assignments and static blocks** (in order).

3. **Execution of `main()` method** (if present).

---

##### **Static Control Flow Example :**

```java
public class staticControlFlow {

    static int i = 10;

    static {
        methodOne();
        System.out.println("First Static Block");
    }

    public static void main(String[] args) {
        methodOne();
        System.out.println("Main method");
    }

    private static void methodOne() {
        System.out.println(j);
    }

    static {
        System.out.println("Second Static Block");
    }

    static int j = 20;
}
```

<img src="https://github.com/user-attachments/assets/161de626-0ade-4b47-9223-d0a8e8ecfe8c" width="600" height="650">
---

## Read Indirectly, Write Only (RIWO)

---

> **RIWO** = **Read Indirect, Write Only**

This is a temporary state a static variable is in **after being identified** but **before being explicitly assigned**.

- ✅ You can **call a method** that reads the variable

- ❌ You **cannot read it directly** in a static block above its declaration

- Default value is used during indirect reads

---

**📊 Access Summary**

| Access Type       | Description                                                      | Valid?    | Output              |
| ----------------- | ---------------------------------------------------------------- | --------- | ------------------- |
| **Direct Read**   | Tries to directly read static variable before its declaration.   | ❌ Illegal | Compile-time Error  |
| **Indirect Read** | Calls a method that accesses the variable before its assignment. | ✅ Allowed | 0                   |
| **Write Access**  | Writes a value to the static variable before its declaration     | ✅ Allowed | Writes successfully |

---

### ❗ Illegal Forward Reference (Direct Read)

---

> 🔴 **Direct access to a static variable is only allowed *after its declaration appears physically in the code*** — even if you're still in the identification phase.

```java
class Test {
    static {
        System.out.println(i); // ❌ Direct read before declaration
    }

    static int i = 10;
}
```

⛔ **Compile-time Error**: `illegal forward reference`

- If you try to **directly read a static variable** *before it’s declared in the file*, the compiler throws an error.

---

### ✅ Indirect Read Is Allowed (RIWO)

---

```java
class Test {
    static {
        methodOne(); // ✅ Indirect read
    }

    public static void methodOne() {
        System.out.println(i); // Reads i
    }

    static int i = 10;
}
```

🟢 Output:

```java
0
```

**Why?**

- During the call to `methodOne()`, `i` is in the **RIWO state**.

- Indirect read is allowed → `i` is not yet assigned explicitly → default value `0` is printed.

---

### ✅ Write Access Before Declaration — Legal

---

```java
class Test {
    static {
        i = 20; // ✅ Write access before declaration
        System.out.println("Written i = 20");
    }

    static int i = 10;
}
```

🧠 **Explanation Notes:**

- During the **static identification phase**, the compiler identifies all static variables — so `i` is already known before any static block runs.

- ✅ You're writing to that identified static variable `i`, and **write access is always allowed**, even if the declaration appears below.

- ❌ This is **not a local variable** — since no `int i` is declared inside the block, the compiler links this to the class-level static `i`.

- ⚠️ Contrast with a local variable:

```java
class Test {
    public static void main(String[] args) {
        int i = 20; // This is a local variable
    }
}
```

- In the local case above, `i` is a **method-local variable**, scoped only within `main()`

---

## 🔁 **Static Control Flow: Parent → Child**

When  we execute a **child class**, the JVM loads and initializes classes in the following strict order:

---

### 🚦Order of Execution When Inheritance Is Involved

---

1. **Identification of static members** (from top to bottom, Parent to Child).

2. **Execution of static variable assignments and static blocks** (from top to bottom, Parent to Child).

3. **Execution of `main()` method** (of the Child class)

---

📝 **Note:*

* Whenever we load the **child class**, the **parent class is automatically loaded**.

* Whenever we load the **child class**, the **parent class is automatically loaded**.

---

##### Static control flow  inheritance example

```java
class Base {
    static int i = 10;

    static {
        methodOne();                    
        System.out.println("base static block");
    }

    public static void main(String[] args) {
        methodOne();                     
        System.out.println("base main");
    }

    public static void methodOne() {
        System.out.println(j);
    }

    static int j = 20;
}

class Derived extends Base {
    static int x = 100;

    static {
        methodTwo();                     
        System.out.println("derived first static block");
    }

    public static void main(String[] args) {
        methodTwo();                     
        System.out.println("derived main");
    }

    public static void methodTwo() {
        System.out.println(y);
    }

    static {
        System.out.println("derived second static block");
    }

    static int y = 200;
}


```

<img src="https://github.com/user-attachments/assets/7bb51189-5516-4484-9787-2789b0b107ff" width="600" height="650"> 

---

## 🧱 Static Blocks

- A **static block** is executed **once** at the time of **class loading**.

- Use it to perform **class-level initialization logic** that needs to run **before `main()` or object creation**.

- A class can have **multiple static blocks**, which are executed in the order they appear (top to bottom).

---

### 💡 Common Use Cases

- **Loading native libraries**

- **JDBC driver registration**

- **Printing without `main()` (up to Java 1.6)**

---

#### 🔧 Examples

##### ✅ Example 1: Loading Native Library

```java
class Test {
    static {
        System.loadLibrary("native library path");
    }
}
```

##### ✅ Example 2: JDBC Driver Registration

```java
class Driver {
    static {
        // Register this driver with DriverManager
    }
}
```

---

#### ❓Can we print to console without a `main()` method?

**✅ Yes**, by using a static block.

```java
class Google {
    static {
        System.out.println("hello i can print");
        System.exit(0);
    }
}
```

**Output:**

```java
hello i can print
```

📝 **Note:**  
This works **only in Java 1.6 and earlier**.  
From Java 1.7 onward, a `main()` method is **mandatory** for execution.

---

# 🧬 Instance Control Flow

---

Whenever we **create an object**, Java follows a strict order to initialize **instance-level data**.

Unlike static control flow (which runs **once per class load**), **instance control flow runs every time** an object is created.

---

### 🚦 Order of Execution (Single Class)

---

When we create an object:

1. JVM **identifies all instance members** (variables, blocks, methods) — *top to bottom*

2. **Execution of instance variable assignments and instance blocks** — *top to bottom*

3. **Execution of constructor**

🔁 These steps happen **every time** an object is created.

🧠 **Note**: During method calls in instance blocks, variables not yet initialized show `0`

> 🔔 This sequence repeats for **each object** (unlike static control flow which happens once at class loading).

---

#### ✅ Example: Single Class

```java
class Parent {
    int i = 10;

    {
        methodOne();
        System.out.println("first instance block");
    }

    Parent() {
        System.out.println("Parent class constructor");
    }

    public void methodOne() {
        System.out.println(j);
    }

    {
        System.out.println("second instance block");
    }

    int j = 20;

    public static void main(String[] args) {
        Parent p = new Parent();
        System.out.println("main method");
    }
}
```

## 🔍 Output:

```java
0
first instance block
second instance block
Parent class constructor
main method
```
<img src="https://github.com/user-attachments/assets/9d4a143a-103d-4802-aa92-ddcd0b963cf1" width="650" height="750"> 
---

## 👨‍👩‍👧 Instance Control Flow in Parent-Child Classes

---

When we create a **child object**, this is the order:

---

### 🚦 **Execution Flow (Parent → Child):**

- **Identify instance members** (top to bottom — Parent first, then Child)

- **Execute instance blocks and variable assignments** in **Parent**

- **Call Parent constructor**

- **Execute instance blocks and variable assignments** in **Child**

- **Call Child constructor**

---

#### ✅ Example: Parent → Child

```java
class Parent {
    int x = 10;

    {
        methodOne();
        System.out.println("Parent first instance block");
    }

    Parent() {
        System.out.println("Parent class constructor");
    }

    public void methodOne() {
        System.out.println(y);  // prints 0 (not initialized yet)
    }

    int y = 20;
}

class Child extends Parent {
    int i = 100;

    {
        methodTwo();
        System.out.println("Child first instance block");
    }

    {
        System.out.println("Child second instance block");
    }

    Child() {
        System.out.println("Child class constructor");
    }

    public void methodTwo() {
        System.out.println(j);  // prints 0 (not initialized yet)
    }

    int j = 200;

    public static void main(String[] args) {
        Child c = new Child();
        System.out.println("Child class main method");
    }
}

```

---

##### 🔍 Output:

```java
0
Parent first instance block
Parent class constructor
0
Child first instance block
Child second instance block
Child class constructor
Child class main method
```
<img src="https://github.com/user-attachments/assets/d076c36b-9639-42ff-b217-474a9298a1b8" width="650" height="750"> 


> 🔸 Both `methodOne()` and `methodTwo()` access instance variables **before they're initialized**, so output is `0`.

---

## 💡 Static vs Instance Control Flow

| Aspect     | Static Control Flow      | Instance Control Flow                   |
| ---------- | ------------------------ | --------------------------------------- |
| When?      | At class loading time    | At every object creation                |
| What?      | Static variables, blocks | Instance variables, blocks, constructor |
| Runs once? | ✅ Yes                    | ❌ No (runs for every object)            |

---

#### 🧪 Examples with Execution Order

---

✅ **Example 1:**

```java
public class Initialization {
    private static String methodOne(String msg) {
        System.out.println(msg);
        return msg;
    }

    public Initialization() {  // constructor
        m = methodOne("1");
    }

    {
        m = methodOne("2");
    }

    String m = methodOne("3");

    public static void main(String[] args) {
        Object obj = new Initialization();
    }
}


```

###### Output

```java
2
3
1
```

**Why?**

| Element                      | Meaning                                                                |
| ---------------------------- | ---------------------------------------------------------------------- |
| `public Initialization()`    | Constructor (executes after instance blocks and field initializations) |
| `{ m = methodOne("2"); }`    | Instance block (runs first)                                            |
| `String m = methodOne("3");` | Instance variable initializer (runs after block)                       |

- **Instance blocks** execute **before** variable initializations — **regardless of their textual position in code**

- The **constructor body** always runs **last**

---

### ✅ **Example 2 (Static Control Flow Focus)**

```java
public class Initialization {
    private static String methodOne(String msg) {
        System.out.println(msg);
        return msg;
    }

    static String m = methodOne("1");

    static {
        m = methodOne("3");
    }

    {
        m = methodOne("2");
    }

    public static void main(String[] args) {
        Object obj = new Initialization();
    }
}

```

**🔍 Output:**

```java
1
3
2
```

> 🔸 Static variables and static blocks execute at class loading  
> 🔸 Instance block runs when object is created

---

### 🧠 Static vs Instance Access Rules (Quick Note)

- ❌ **Static context** (like `main` or static blocks) **cannot access instance variables/methods directly**  
  → Because **no object may exist yet** when static code runs.

- ✅ **Instance context** (like non-static methods or blocks) **can access both static and instance members**  
  → Because static members are already loaded, and `this` refers to the object.

- ✅ **Static members** can be accessed **from anywhere**, since they are loaded during **class loading time**.

---

##### 🧪 Example – Invalid Access from Static Context:

```java
class Example {
    int x = 10; // instance variable

    public static void main(String[] args) {
        System.out.println(x); // ❌ Compile-time error
    }
}
```

**❌ Error:**

```java
non-static variable x cannot be referenced from a static context
```

---

##### ✅ Correct Access – Use an Object:

```java
class Example {
    int x = 10;

    public static void main(String[] args) {
        Example obj = new Example();
        System.out.println(obj.x); // ✅ Works fine
    }
}
```

---

##### 🧬 Instance Context Can Access Everything

From an **instance method or block**, both static and instance members are accessible.

```java
class Example {
    int a = 5;
    static int b = 10;

    void instanceMethod() {
        System.out.println(a); // ✅ instance
        System.out.println(b); // ✅ static
    }
}
```

---

##### 🛠 Static Context Can Only Access Static Members Directly

```java
class Example {
    int a = 5;
    static int b = 10;

    static void staticMethod() {
        // System.out.println(a); ❌ Not allowed
        System.out.println(b); // ✅ Allowed
    }
}
```
