# 🚨 Part 3: Top Exceptions,Java 7 Enhancements & Beyond

## Table of Contents

1.  [🚨 Top 10 Most Common Exceptions](#-top-10-most-common-exceptions)
    - [✅ 1. `ArrayIndexOutOfBoundsException` (Unchecked)](#-1-arrayindexoutofboundsexception-unchecked)
    - [✅ 2. `NullPointerException` (Unchecked)](#-2-nullpointerexception-unchecked)
    - [✅ 3. `StackOverflowError` (Unchecked)](#-3-stackoverflowerror-unchecked)
    - [✅ 4. `NoClassDefFoundError` (Unchecked)](#-4-noclassdeffounderror-unchecked)
    - [✅ 5. `ClassCastException` (Unchecked)](#-5-classcastexception-unchecked)
    - [✅ 6. `ExceptionInInitializerError` (Unchecked)](#-6-exceptionininitializererror-unchecked)
    - [✅ 7. `IllegalArgumentException` (Unchecked)](#-7-illegalargumentexception-unchecked)
    - [✅ 8. `NumberFormatException` (Unchecked)](#-8-numberformatexception-unchecked)
    - [✅ 9. `IllegalStateException` (Unchecked)](#-9-illegalstateexception-unchecked)
    - [✅ 10. `AssertionError` (Unchecked)](#-10-assertionerror-unchecked)
2.  [📊 Categorization: JVM vs Programmatic Exceptions](#-categorization-jvm-vs-programmatic-exceptions)
3.  [🔧 Java 1.7 Exception Handling Enhancements](#-java-17-exception-handling-enhancements)
    - [✅ 1. Try-With-Resources](#-1-try-with-resources)
        - [📜 Pre-Java 7 Resource Handling (Manual Resource Handling)](#-pre-java-7-resource-handling-manual-resource-handling)
        - [⚠️ Problems:](#-problems)
    - [✅ Try-With-Resources (Java 7+)](#-try-with-resources-java-7)
        - [📌Key Features:](#-key-features)
        - [1. Multiple Resources Supported:](#-1-multiple-resources-supported)
        - [✔️ Resources Must Implement `AutoCloseable`:](#-resources-must-implement-autocloseable)
        - [✔️ Implicitly Final Variables](#-implicitly-final-variables)
        - [✔️ `finally` Becomes Optional](#-finally-becomes-optional)
    - [2️⃣ Multi-Catch Block](#-2-multi-catch-block)
        - [🧱 Before Java 7](#-before-java-7)
        - [✅ After Java 7: Multi-Catch](#-after-java-7-multi-catch)
        - [✅ Benefits](#-benefits)
        - [⚠️ Rules](#-rules)
    - [3️⃣ Rethrowing an Exception](#-3-rethrowing-an-exception)
        - [✅ Use Cases](#-use-cases)
4.  [✅ Summary](#-summary)
5.  [✅ Java 8+: Exception Handling in `CompletableFuture`](#-java-8-exception-handling-in-completablefuture)
6.  [✅ Java 14+: Helpful `NullPointerException` Messages](#-java-14-helpful-nullpointerexception-messages)


---

# 🚨 Top 10 Most Common Exceptions

Exceptions are categorized as either **checked** or **unchecked**, and they are further raised **automatically by the JVM** or **manually by the developer/API**. Below are the 10 most frequently encountered exceptions/errors.

---

## ✅ 1. `ArrayIndexOutOfBoundsException` (Unchecked)

Thrown when trying to access an array with an invalid index.

```java
class Test {
    public static void main(String[] args) {
        int[] x = new int[10];
        System.out.println(x[0]);    // ✅ Valid
        System.out.println(x[100]);  // ❌ AIOOBE
        System.out.println(x[-100]); // ❌ AIOOBE
    }
}
```

---

## ✅ 2. `NullPointerException` (Unchecked)

Thrown when accessing methods or fields on a null reference.

```java
class Test {
    public static void main(String[] args) {
        String s = null;
        System.out.println(s.length()); // ❌ NPE
    }
}
```

---

## ✅ 3. `StackOverflowError` (Unchecked)

Thrown when recursive method calls exceed the call stack size.

```java
class Test {
    public static void methodOne() { methodTwo(); }
    public static void methodTwo() { methodOne(); }

    public static void main(String[] args) {
        methodOne(); // ❌ StackOverflowError
    }
}
```

---

## ✅ 4. `NoClassDefFoundError` (Unchecked)

Occurs when the `.class` file was present during compilation but missing during runtime.

```java
# Compile:
javac Test.java

# Delete Test.class

# Run:
java Test
# ❌ NoClassDefFoundError
```

---

### ✅ 5. `ClassCastException` (Unchecked)

Thrown when an invalid type cast is attempted at runtime.

```java
// Invalid cast example
class Test {
    public static void main(String[] args) {
        Object o = new Object();
        String s = (String) o; // ❌ CCE
    }
}
```

---

### ✅ 6. `ExceptionInInitializerError` (Unchecked)

Occurs when an exception is thrown during static initialization.

```java
class Test {
    static int i = 10 / 0; // ❌ Exception during static init
}
```

OR

```java
class Test {
    static {
        String s = null;
        System.out.println(s.length()); // ❌ NPE inside static block
    }
}
```

---

### ✅ 7. `IllegalArgumentException` (Unchecked)

Thrown when a method receives an argument it cannot accept.

```java
class Test {
    public static void main(String[] args) {
        Thread t = new Thread();
        t.setPriority(10);   // ✅ Valid
        t.setPriority(100);  // ❌ IllegalArgumentException
    }
}
```

---

### ✅ 8. `NumberFormatException` (Unchecked)

Thrown when a string cannot be parsed as a number.

```java
class Test {
    public static void main(String[] args) {
        int a = Integer.parseInt("10");  // ✅ Valid
        int b = Integer.parseInt("ten"); // ❌ NFE
    }
}
```

---

### ✅ 9. `IllegalStateException` (Unchecked)

Thrown when a method is called at an inappropriate time.

```java
HttpSession session = req.getSession();
System.out.println(session.getId());
session.invalidate();
System.out.println(session.getId()); // ❌ IllegalStateException
```

---

### ✅ 10. `AssertionError` (Unchecked)

Thrown when an `assert` statement fails.

```java
class Test {
    public static void main(String[] args) {
        assert false : "Assertion failed"; // ❌ AssertionError
    }
}
```

---

## 📊 Categorization: JVM vs Programmatic Exceptions

| 🔹 JVM Exceptions                         | 🔸 Programmatic Exceptions       |
| ----------------------------------------- | -------------------------------- |
| `ArrayIndexOutOfBoundsException` (AIOOBE) | `IllegalArgumentException` (IAE) |
| `NullPointerException` (NPE)              | `NumberFormatException` (NFE)    |
| `StackOverflowError`                      | `IllegalStateException` (ISE)    |
| `NoClassDefFoundError`                    | `AssertionError` (AE)            |
| `ClassCastException` (CCE)                |                                  |
| `ExceptionInInitializerError`             |                                  |

---

# 🔧 Java 1.7 Exception Handling Enhancements

Java 1.7 introduced two powerful enhancements to simplify and modernize exception handling:

1. ✅ **Try-With-Resources**

2. ✅ **Multi-Catch Blocks**

---

### ✅ 1. Try-With-Resources

---

#### 📜 Pre-Java 7 Resource Handling (Manual Resource Handling)

Before Java 7, resources like `BufferedReader`, `FileInputStream`, or JDBC connections had to be manually closed in a `finally` block:

```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("abc.txt"));
    // Use the resource
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (br != null)
        br.close(); // Risk of forgetting this
}
```

#### ⚠️ Problems:

- Manual null checks and closing logic = verbose & error-prone

- Easy to forget cleanup → **resource leaks**

- Harder to read and maintain

---

### ✅ Try-With-Resources (Java 7+)

Introduced in Java 7 to simplify resource handling.  
Resources declared in the `try()` parentheses are **automatically closed** at the end of the block.

```java
try (BufferedReader br = new BufferedReader(new FileReader("abc.txt"))) {
    // Use the resource
} catch (IOException e) {
    e.printStackTrace();
}
```

#### 💡 Benefits:

- ✅ Automatic resource closing (even on exceptions)

- ✅ No need for null checks

- ✅ Prevents resource leaks

- ✅ Reduces the chances of forgetting `.close()`

- ✅ Cleaner, more readable code

---

#### **📌Key Features:**

---

#### 1. **Multiple Resources Supported:**

```java
try (
    BufferedReader br = new BufferedReader(new FileReader("abc.txt"));
    PrintWriter writer = new PrintWriter("output.txt")
) {
    // Use both resources
}
```

#### **✔️ Resources Must Implement `AutoCloseable`:**

- `BufferedReader`, `PrintWriter`, `Scanner`, `FileReader`, `Connection` all implement `AutoCloseable`.

#### ✔️ Implicitly Final Variables

The resource variables are treated as **implicitly final**. You cannot reassign them inside the `try`.

```java
try (BufferedReader br = new BufferedReader(new FileReader("abc.txt"))) {
    // br = new BufferedReader(...); // ❌ Compile-time error
}
```

#### ✔️ `finally` Becomes Optional

You no longer need a `finally` block just to close resources. Use it only for extra logic like logging.

---

## 2️⃣ Multi-Catch Block

---

### 🧱 Before Java 7

You had to catch each exception type in a separate block:

```java
try {
    // risky code
} catch (ArithmeticException e) {
    e.printStackTrace();
} catch (NullPointerException e) {
    e.printStackTrace();
}
```

### ✅ After Java 7: Multi-Catch

Use `|` (pipe) to catch multiple exceptions in a single block.

```java
try {
    // risky code
} catch (ArithmeticException | NullPointerException e) {
    e.printStackTrace();
}
```

#### ✅ Benefits

- Cleaner and shorter code

- Reduces duplication

- Groups related exceptions together

#### ⚠️ Rules

- ❌ Exceptions in the same block must **not have a parent-child relationship**.

```java
catch (IOException | FileNotFoundException e) { } // ❌ Compile-time error
```

✅ Use most specific exceptions first if writing separate catch blocks.

---

### 3️⃣ Rethrowing an Exception

You can catch an exception and **rethrow** it, optionally wrapping it inside another exception.

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println(10 / 0);
        } catch (ArithmeticException e) {
            throw new RuntimeException("Calculation failed", e);
        }
    }
}
```

### ✅ Use Cases

- Wrapping lower-level exceptions in a custom or meaningful exception

- Logging before rethrowing

- Escalating to higher-level handlers

---

## ✅ Summary

| Feature                        | Java 7+ Enhancement             |
| ------------------------------ | ------------------------------- |
| ✅ Auto resource management     | `try-with-resources`            |
| ✅ Catching multiple exceptions | `multi-catch block`             |
| ✅ Cleaner exception flow       | Rethrowing + Optional `finally` |
| ✅ Safer & readable code        | Less boilerplate                |

---

# ✅ Java 8+: Exception Handling in `CompletableFuture`

---

Modern Java handles async operations using `CompletableFuture`. Exceptions in such cases are not caught by traditional `try-catch` blocks. Instead, use `.exceptionally()`, `.handle()`, or `.whenComplete()`.

```java
CompletableFuture.supplyAsync(() -> 10 / 0)
    .exceptionally(ex -> {
        System.out.println("Handled: " + ex);
        return 0;
    });
```

> 📌 This is essential for handling errors in multithreaded or async code flows.

---

# ✅ Java 14+: Helpful `NullPointerException` Messages

---

Java 14 introduced more informative NPE messages when the JVM flag is enabled:

```java
-XX:+ShowCodeDetailsInExceptionMessages
```

These messages are **on by default** from Java 15 onward.

```java
String[] names = null;
System.out.println(names[0]);
```

Output in Java 15+:

```java
Exception in thread "main" java.lang.NullPointerException: 
Cannot load from object array because "names" is null
```

> 🔍 This drastically improves debugging by pointing out exactly *which* reference was `null`.
