# Java Variables & Execution Fundamentals

## Table of Contents

1. [Variable Classification Overview](#-java-variable-types)
2. [Primitive vs Reference Variables](#-division-1-based-on-the-type-of-value-they-store)
3. [Scope-Based Variable Types](d#-division-2-based-on-scope-lifetime-and-position-of-declaration)

    - [Instance Variables](#1-instance-variables)
    - [Static Variables](#2-static-variables)
    - [Local Variables](#3-local-variables)
      
4. [Default Values and Uninitialized Arrays](#uninitialized-arrays)
5. ️[Variable Argument Methods (Var-Args)](#-var-arg-methods-variable-number-of-arguments--java-15)
6. ️[main() Method Rules & JVM Behavior  ](#main-method)
     - [Method Overloading](#main-method-overloading)
     - [Inheritance Behavior](#inheritance-and-the-main-method)
     - [Java 1.7 Enhancements](#-17-version-enhancements-with-respect-to-main-method)
7. [Command Line Arguments](#-command-line-arguments-in-java)
8. [Java Coding Standards](#-java-coding-standards)
9. [Java Bean Naming Conventions](#-java-bean-naming-conventions)
10. [JVM Memory Model](#-jvm-memory-areas)
    
  
---

# 🧠 Java Variable Types

In Java, variables can be classified in **two major divisions**:

---

## 🔹 **Division 1: Based on the Type of Value They Store**

Java variables are first divided based on **what kind of data they represent**:

### 1. **Primitive Variables**

- Used to store **primitive values** like `int`, `boolean`, `char`, etc.
- Directly contain the value (not a reference).

```java
int x = 10;
```

---

### 2. **Reference Variables**

- Used to refer to **objects**.
- Store the memory address (reference) of the object in the heap.

```java
Student s = new Student();
```

🧠 *Note: Arrays and Strings are also objects in Java, so their variables are reference types.*

---

## 🔸 **Division 2: Based on Scope, Lifetime, and Position of Declaration**

Java variables are also divided based on **where and how they are declared**, and **how long they live in memory**:

### 1. **Instance Variables**

- Instance variables hold values that **vary from object to object**.
- **Each object has its own separate copy** of instance variables.
- They are **created when the object is created** and **destroyed when the object is destroyed**.
- Hence, the **lifetime and scope of instance variables match the lifetime of the object**.
- Instance variables are stored in the **heap memory**, as part of the object.
- They must be declared **inside the class but outside any method, constructor, or block**.
- Instance variables **can be accessed directly by instance methods**.
- They **cannot be accessed directly from static methods or static context**.
- To access instance variables from a static context, use **object references**.
- Initialization is **not mandatory**; JVM automatically assigns **default values** to instance variables.

### ✅ Example 1

```java
class Test {
    int i = 10;

    public static void main(String[] args) {
        // System.out.println(i); ❌ Compile Error: non-static variable i cannot be referenced from a static context
        Test t = new Test();
        System.out.println(t.i); // ✅ Output: 10
        t.methodOne();
    }

    public void methodOne() {
        System.out.println(i); // ✅ Output: 10
    }
}
```

### ✅ Example 2 (default values)

```java
class Test {
    boolean b;

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.b); // Output: false
    }
}
```

🔎 Also called **object-level variables** or **attributes**.

---

### 2. **Static Variables**

- If a variable's value **does not vary from object to object**, it **should not** be declared as an instance variable.
- Such variables should be declared at the **class level using the `static` modifier**.
- For **instance variables**, each object gets its **own separate copy**.
- For **static variables**, there is **only one copy shared across all objects** of the class.
- Static variables are **created when the class is loaded** and **destroyed when the class is unloaded**.
- Their scope is tied to the **lifetime of the `.class` file in memory**.
- Static variables are stored in the **method area** of JVM memory.
- They must be declared **inside the class but outside any method, constructor, or block**.
- Static variables can be accessed **directly from both instance methods and static methods**.
- They can be accessed either by **class name (recommended)** or by **object reference**.
- Within the same class, you can access static variables **directly without the class name**.
- Static variables are also called **class-level variables** or **class fields**.

### ✅ Example 1

```java
class Test {
    static int i = 10;

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.i);      // Output: 10
        System.out.println(Test.i);   // Output: 10
        System.out.println(i);        // Output: 10
    }
}
```

### ✅ Example 2 (default values)

```java
class Test {
    static String s;

    public static void main(String[] args) {
        System.out.println(s); // Output: null
    }
}
```

### ✅ Example 3 (shared variable)

```java
class Test {
    int x = 10;
    static int y = 20;

    public static void main(String[] args) {
        Test t1 = new Test();
        t1.x = 888;
        t1.y = 999;

        Test t2 = new Test();
        System.out.println(t2.x + "----" + t2.y); 
        // Output: 10----999
    }
}
```

🔎 Also called **class-level variables** or **fields**.

---

### 📌 Static Variable Lifecycle

When you run `java Test`, here’s what happens:

1. Start JVM
2. JVM creates and starts main thread
3. Main thread locates `Test.class`
4. Class is loaded → **Static variables are created**
5. `main()` is executed
6. Class is unloaded → **Static variables destroyed**
7. Main thread terminates
8. JVM shuts down

---

### 3. **Local Variables**

- Local variables are declared **inside methods, blocks, or constructors** to meet temporary needs.
- They are also known as **automatic variables**, **temporary variables**, or **stack variables**.
- Local variables are stored in the **stack memory**.
- They are **created when the block or method execution starts** and **destroyed once the execution completes**.
- Therefore, the **scope of a local variable is limited to the block or method** where it is declared.

### ❌ Example 1: Scope issue

```java
class Test {
    public static void main(String[] args) {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            i = i + j;
        }
        System.out.println(i + "---" + j); 
       
    }
}
```

Output →

```java
 ❌ Compile Error: cannot find symbol j
```

### ❌ Example 2: Variable not initialized

```java
class Test {
    public static void main(String[] args) {
        int x;
        if (args.length > 0) {
            x = 10;
        }
        System.out.println(x);
    }
}
```

Output : →

```java
❌ Compile Error: variable x might not have been initialized
```

- It is **not recommended** to initialize local variables inside conditional or logical blocks because those blocks may not execute at runtime, leading to uninitialized variables.
- It is **highly recommended** to initialize local variables **at the time of declaration**, at least with default values.

### ✅ Example 3: Proper initialization

```java
class Test {
    public static void main(String[] args) {
        int x;
        if (args.length > 0) {
            x = 10;
        } else {
            x = 20;
        }
        System.out.println(x); // Output: 10 or 20 based on input
    }
}
```

### ❌ Invalid modifiers

```java
class Test {
    public static void main(String[] args) {
        public int x = 10;    // ❌
        private int x = 10;   // ❌
        static int x = 10;    // ❌
        final int x = 10;     // ✅
    }
}
```

🔎 Also called **temporary**, **automatic**, or **stack variables**.

---

## 🧾 Summary Table

| Feature | Instance Variable | Static Variable | Local Variable |
| --- | --- | --- | --- |
| Memory Location | Heap | Method Area | Stack |
| Default Value by JVM | ✅ Yes | ✅ Yes | ❌ No (must init) |
| Lifetime | Object lifespan | Class lifespan | Block lifespan |
| Number of Copies | Per object | One per class | One per thread |
| Accessible in static? | ❌ No (directly) | ✅ Yes | ❌ Only inside block |
| Modifiers Allowed | All (public, etc) | All | Only `final` |
| Thread-Safety | ❌ Not safe | ❌ Not safe | ✅ Thread-safe |

# **Uninitialized Arrays**

When an array reference variable is declared but not initialized, it holds the value `null`. Accessing elements of such an uninitialized array causes a runtime exception.

---

### Instance Level

**Example 1:**

```java
int[] a;
System.out.println(obj.a);    // Output: null
System.out.println(obj.a[0]); // Runtime Exception: NullPointerException
```

**Example 2:**

```java
int[] a = new int[3];
System.out.println(obj.a);    // Output: [I@3e25a5 (hash code)
System.out.println(obj.a[0]); // Output: 0 (default value)
```

---

### Static Level

**Example 1:**

```java
static int[] a;
System.out.println(a);       // Output: null
System.out.println(a[0]);    // Runtime Exception: NullPointerException
```

**Example 2:**

```java
static int[] a = new int[3];
System.out.println(a);       // Output: [I@3e25a5 (hash code)
System.out.println(a[0]);    // Output: 0 (default value)
```

---

### Local Level

**Example 1:**

```java
int[] a;
System.out.println(a);       // Compile Error: variable a might not have been initialized
System.out.println(a[0]);
```

**Example 2:**

```java
int[] a = new int[3];
System.out.println(a);       // Output: [I@3e25a5 (hash code)
System.out.println(a[0]);    // Output: 0 (default value)
```

---

Once an array is created, every element is automatically initialized with default values, regardless of whether it is an instance, static, or local array.

---

### Important Notes:

- Every variable in Java must be either an instance variable, static variable, or local variable.
- Every variable in Java must be either a primitive type or a reference type.

---

# 🔄 Var-Arg Methods (Variable Number of Arguments) — Java 1.5+

---

### 🔹 Introduction

- Prior to Java 1.5, you **could not define** a method to accept a variable number of arguments.
- If the number of arguments differed, you had to **overload**  the method, increasing **code length** and reducing **readability**.
- Java 1.5 introduced **Var-Arg methods**, allowing methods to accept **any number of arguments (including zero)**.

---

### ✅ Declaration Syntax

```java
public static void methodName(int... x) { }
```

- Internally, `x` is treated as a **1D array** (`int[]`).
- This syntax can be used to pass **zero or more `int` values**.

---

### ✅ Basic Example

```java
class Test {
    public static void methodOne(int... x) {
        System.out.println("var-arg method");
    }

    public static void main(String[] args) {
        methodOne();              // ✅ Valid
        methodOne(10);            // ✅ Valid
        methodOne(10, 20, 30);    // ✅ Valid
    }
}
```

**Output:**

```java
var-arg method
var-arg method
var-arg method
```

---

## 📦 Var-Arg Processing Internally

Since the var-arg parameter is **treated like an array**, you can use both:

- Classic `for` loop
- Enhanced `for-each` loop

---

### 🔹 Example 1: Using Index

```java
class Test {
    public static void sum(int... x) {
        int total = 0;
        for (int i = 0; i < x.length; i++) {
            total += x[i];
        }
        System.out.println("The sum: " + total);
    }

    public static void main(String[] args) {
        sum();                        // 0
        sum(10);                      // 10
        sum(10, 20);                  // 30
        sum(10, 20, 30, 40);          // 100
    }
}
```

---

### 🔹 Example 2: Using For-Each

```java
class Test {
    public static void sum(int... x) {
        int total = 0;
        for (int val : x) {
            total += val;
        }
        System.out.println("The sum: " + total);
    }

    public static void main(String[] args) {
       sum(); 
       sum(10);
       sum(10, 20);
       sum(10, 20, 30, 40);
    }
}
```

**Output:**

```java
The sum: 0
The sum: 10
The sum: 30
The sum: 100
```

---

## 🧪 Use Cases & Rules

---

### 🔸 Case 1: Valid and Invalid Declarations

| Declaration | Validity |
| --- | --- |
| `methodOne(int... x)` | ✅ Valid |
| `methodOne(int ...x)` | ✅ Valid |
| `methodOne(int...x)` | ✅ Valid |
| `methodOne(int x...)` | ❌ Invalid |
| `methodOne(int. ..x)` | ❌ Invalid |
| `methodOne(int .x..)` | ❌ Invalid |

---

### 🔸 Case 2: Mixing with Regular Parameters

```java
methodOne(int a, int... b);        // ✅ Valid
methodOne(String s, int... x);     // ✅ Valid
```

---

### 🔸 Case 3: Var-Arg Must Be Last

```java
methodOne(int a, int... b);  // ✅ Valid
methodOne(int... a, int b);  // ❌ Compile Error
```

> ❗ When using both var-args and regular parameters, var-arg must be the last.
>

---

### 🔸 Case 4: Only One Var-Arg Allowed

```java
methodOne(int... a, int... b);  // ❌ Compile Error
```

> ❗ A method can have only one var-arg parameter.
>

---

### 🔸 Case 5: Overloading with Regular Method

```java
class Test {
    public static void methodOne(int i) {
        System.out.println("general method");
    }

    public static void methodOne(int... i) {
        System.out.println("var-arg method");
    }

    public static void main(String[] args) {
        methodOne();           // var-arg method
        methodOne(10);         // general method
        methodOne(10, 20);     // var-arg method
    }
}
```

> 🔍 Var-arg method has lowest priority.
>
>
> If an exact match is found (like `int`), that method is called first.
>

---

### 🔸 Case 6: Passing Arrays Directly

We can provide the corresponding type array as argument.

```java
class Test {
    public static void methodOne(int... i) {
        System.out.println("var-arg method");
    }

    public static void main(String[] args) {
        methodOne(new int[]{10, 20, 30});
    }
}
```

✅ Output: `var-arg method`

---

### 🔸 Case 7: Conflict with `int[]` Overload

```java
class Test {
    public void methodOne(int[] i) { }
    public void methodOne(int... i) { }
}
```

❌ Compile-time Error:

> Cannot declare both methodOne(int[]) and methodOne(int...) in Test.
>

---

## 📐 Single dimensional Array Vs Var-Arg Method :

### Case 1: Array → Var-Arg ✅

```java
void method(int[] a)   →   void method(int... a)  ✅
```

Var-arg can replace a **1D array**.

---

### Case 2: Var-Arg → Array ❌

```java
void method(int... a)   →   void method(int[] a)  ❌
```

Reverse is not always valid — especially with **multi-dimensional** replacements.

---

## 📚 2D Var-Arg Example

```java
class Test {
    public static void methodOne(int[]... x) {
        for (int[] a : x) {
            System.out.println(a[0]);
        }
    }

    public static void main(String[] args) {
        int[] l = {10, 20, 30};
        int[] m = {40, 50};
        methodOne(l, m);
    }
}
```

**Output:**

```java
10
40
```

# Main Method

In Java, whether a class contains a `main()` method and whether it is properly declared are **not compiler responsibilities**—these checks are handled by the **JVM at runtime**.

If the JVM does not find a valid `main()` method, it throws a runtime error:

```java
Exception in thread "main" java.lang.NoSuchMethodError: main
```

### Required Signature

```java
public static void main(String[] args)
```

### Explanation:

- `public` – accessible from anywhere by the JVM
- `static` – no need to create an object to invoke it
- `void` – returns nothing to the JVM
- `String[] args` – used to receive command-line arguments

### Accepted Variations

Even though the standard syntax of the `main()` method is strict, Java allows a few flexible variations:

1. **Modifier Order Doesn't Matter**

   You can switch the order of `public` and `static`:

    ```java
    static public void main(String[] args)
    ```

2. **Flexible Array Declaration**

   The `String[]` can be declared in multiple ways:

    ```java
    String[] args
    String []args
    String args[]
    ```

3. **Custom Identifier Name**

   Instead of `args`, any valid Java identifier can be used:

    ```java
    public static void main(String[] myData)
    ```

4. **Var-args Allowed**

   You can use a var-arg parameter instead of a regular array:

    ```java
    public static void main(String... args)
    ```

5. **Additional Allowed Modifiers**

   The `main()` method can also be marked with the following modifiers:

  - `final`
  - `synchronized`
  - `strictfp`

```java
class Test {
    static final synchronized strictfp public void main(String... ask) {
        System.out.println("valid main method");
    }
}
```

**Output:**

```java
valid main method
```

---

### Validity Check – Which of the following declarations are valid?

| Declaration | Valid? | Notes |
| --- | --- | --- |
| `public static void main(String args)` | ❌ | `args` is not an array |
| `public synchronized final strictfp void main(String[] args)` | ❌ | Missing `static` |
| `public static void Main(String... args)` | ❌ | `Main` must be lowercase |
| `public static int main(String[] args)` | ❌ | Return type must be `void` |
| `public static synchronized final strictfp void main(String... args)` | ✅ | Valid use of modifiers and varargs |
| `public static void main(String... args)` | ✅ | Valid varargs declaration |
| `public void main(String[] args)` | ❌ | Missing `static` |

📝 **Note:** All of the invalid declarations will compile, but result in a **runtime error**, not a **compile-time error**.

---

### Main Method Overloading

Overloading `main()` is allowed, but only the standard `main(String[] args)` will be invoked by the JVM.

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("String[] main");
    }

    public static void main(int[] args) {
        System.out.println("int[] main");
    }
}
```

**Output:**

```
String[] main
```

You must call the overloaded version manually if needed.

---

### Inheritance and the Main Method

Static methods (including `main()`) can be inherited. If a child class does **not** define its own `main()` method, the parent's version will be executed.

### Example 1:

```java
class Parent {
    public static void main(String[] args) {
        System.out.println("Parent main");
    }
}

class Child extends Parent {}
```

**Command:**

```java
java Child
```

**Output:**

```java
Parent main
```

### Example 2:

```java
class Parent {
    public static void main(String[] args) {
        System.out.println("Parent main");
    }
}

class Child extends Parent {
    public static void main(String[] args) {
        System.out.println("Child main");
    }
}
```

**Command:**

```java
java Child
```

**Output:**

```java
Child main
```

🔸 This is **method hiding**, not overriding, because `main()` is static.

---

# 🆕 1.7 Version Enhancements with respect to `main()` Method

---

### ✅ Case 1: Meaningful Error Message If `main()` Is Missing

- **Before Java 1.7 (1.6 and earlier):**
  - If the class does not contain a `main()` method, we get:

      ```java
      Runtime Error: NoSuchMethodError: main
      ```

- **From Java 1.7 onwards:**
  - A more **descriptive error** is shown:

      ```java
      Error: main method not found in class Test, please define the main method as:
      public static void main(String[] args)
      ```


**Example:**

```java
class Test {}
```

**Output in 1.6:**

```java
RE: NoSuchMethodError: main
```

**Output in 1.7:**

```java
Error: main method not found in class Test, please define the main method as:
public static void main(String[] args)
```

---

### ✅ Case 2: Static Block Without `main()` – Not Executed in 1.7

- **In Java 1.6:**
  - Static blocks are executed even if `main()` is not present.
- **In Java 1.7+:**
  - Program checks for `main()` **first**. If not found, it throws error **before executing static blocks**.

**Example:**

```java
class Test {
    static {
        System.out.println("static block");
    }
}
```

**Output in 1.6:**

```java
static block
RE: NoSuchMethodError: main
```

**Output in 1.7:**

```java
Error: main method not found in class Test, please define the main method as:
public static void main(String[] args)
```

---

### ✅ Case 3: `System.exit(0)` Inside Static Block – Still Fails in 1.7

**Example:**

```java
class Test {
    static {
        System.out.println("static block");
        System.exit(0);
    }
}
```

**Output in 1.6:**

```java
static block
```

**Output in 1.7:**

```java
Error: main method not found in class Test, please define the main method as:
public static void main(String[] args)
```

📝 **Note:** Even if `System.exit(0)` is used,  JVM still checks for `main()` first in Java 1.7+.

---

### ✅ Case 4: Static Block + `main()` – Works Normally

**Example:**

```java
class Test {
    static {
        System.out.println("static block");
    }

    public static void main(String[] args) {
        System.out.println("main method");
    }
}
```

**Output in both 1.6 and 1.7:**

```java
static block
main method
```

---

### 🔄 Summary of JVM Behavior

| Version | Execution Flow |
| --- | --- |
| **1.6** | 1️⃣ Execute static block (if present) → 2️⃣ Check for `main()` method |
|  | If `main()` present → ✅ Execute it |
|  | If not → ❌ `NoSuchMethodError: main` |
| **1.7+** | 1️⃣ **Check for `main()` method first** |
|  | If `main()` present → ✅ Execute static block and then `main()` |
|  | If not → ❌ Error (more meaningful) |

---

## 🧠 Command Line Arguments in Java

- The arguments passed via the terminal to a Java program are called **command line arguments**.
- These arguments are available in `main(String[] args)` as an array of `String`.

```java
java Test 10 20 30
```

| args[0] | args[1] | args[2] |
| --- | --- | --- |
| "10" | "20" | "30" |
| args.length = 3 |  |  |

---

### ✅ Example 1: ArrayIndexOutOfBounds Exception

```java
class Test {
    public static void main(String[] args) {
        for (int i = 0; i <= args.length; i++) {
            System.out.println(args[i]);
        }
    }
}
```

**Output:**

```java
ArrayIndexOutOfBoundsException: 3
```

✅ **Fix:**

Replace `i <= args.length` with `i < args.length`.

---

### ✅ Example 2: Overriding Command Line Args

```java
class Test {
    public static void main(String[] args) {
        String[] argh = { "X", "Y", "Z" };
        args = argh;
        for (String s : args) {
            System.out.println(s);
        }
    }
}
```

**Any command:**

```java
java Test A B C
java Test

```

**Output (for all):**

```java
X
Y
Z
```

---

### ✅ Example 3: String Concatenation

```java
class Test {
    public static void main(String[] args) {
        System.out.println(args[0] + args[1]);
    }
}
```

**Command:**

```java
java Test 10 20
```

**Output:**

```java
1020
```

📝 `+` acts as string concatenation in `main()` since all arguments are `String`.

---

### ✅ Example 4: Arguments with Spaces

```java
class Test {
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
```

**Command:**

```bash
java Test "Sai Charan"
```

**Output:**

```java
Sai Charan
```

📝 If argument contains space, enclose it in **double quotes**.

---

## 📏 Java Coding Standards

### 🔷 Class Naming

- Should be **noun**, start with uppercase.
- Use **PascalCase** if multiple words.

**Examples:** `Customer`, `Student`, `StringBuffer`

---

### 🔷 Interface Naming

- Usually **adjectives**, use PascalCase.

**Examples:** `Serializable`, `Runnable`, `Cloneable`

---

### 🔷 Method Naming

- Use **verbs** or **verb-noun** combo.
- Follow **camelCase**.

**Example:** `getSalary`, `printDetails`

---

### 🔷 Variable Naming

- Should be **nouns**, follow **camelCase**.

**Examples:** `length`, `name`, `mobileNumber`

---

### 🔷 Constant Naming

- All **uppercase**, words separated by `_`.

**Examples:** `MAX_VALUE`, `MIN_VALUE`, `PI`

---

## ☕ Java Bean Naming Conventions

### Setter Method

- Public, `void`, prefix with `set`, takes argument.

```java
public void setName(String name) {
    this.name = name;
}
```

---

### Getter Method

- Public, **non-void**, prefix with `get`, no arguments.

```java
public String getName() {
    return name;
}
```

For boolean fields:

```java
public boolean isActive() { ... }
```

---

## 🎧 Listener Method Naming

### Register Listener

- ✅ `addMyActionListener(MyActionListener l)`
- ❌ `registerMyActionListener(...)` – Invalid
- ❌ `addMyActionListener(ActionListener l)` – Invalid

---

### Unregister Listener

- ✅ `removeMyActionListener(MyActionListener l)`
- ❌ `unregisterMyActionListener(...)` – Invalid
- ❌ `deleteMyActionListener(...)` – Invalid

---

## 🧠 JVM Memory Areas

1. **Method Area**
  - Stores class-level data including static variables.
2. **Heap**
  - Stores objects and instance variables.
3. **Stack**
  - Each thread has a runtime stack. Method calls and local variables stored here.
  - Each entry is called a **Stack Frame** or **Activation Record**.
4. **PC Registers**
  - Stores address of the next instruction to be executed by the thread.
