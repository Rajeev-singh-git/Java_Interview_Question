# **Part 2: Mastering User-Defined Exceptions**


## Table of Contents

1.  [🧾 `throw` Statement](#-throw-statement)
    - [🤔 JVM vs Developer Thrown Exceptions](#-jvm-vs-developer-thrown-exceptions)
        - [🔁 Program 1 – Thrown by JVM (Automatically)](#-program-1-%E2%80%93-thrown-by-jvm-automatically)
        - [🔁 Program 2 – Thrown by Developer (Manually)](#-program-2-%E2%80%93-thrown-by-developer-manually)
    - [🎯 `throw` Statement: Special Cases in Java](#-throw-statement-special-cases-in-java)
        - [🧪 Case 1: Throwing a `null` Reference](#-case-1-throwing-a-null-reference)
        - [🧪 Case 2: Unreachable Code After `throw`](#-case-2-unreachable-code-after-throw)
        - [🧪 Case 3: `throw` Only Works With `Throwable` Types](#-case-3-throw-only-works-with-throwable-types)
2.  [🏷️ `throws` Keyword](#-throws-keyword)
    - [✅ Two Ways to Resolve the Error](#-two-ways-to-resolve-the-error)
        - [1.🔒 Using `try-catch` (Recommended)](#1%E2%97%A6-using-try-catch-recommended)
        - [2. 📨 Using `throws` to Delegate](#2%E2%97%A6-using-throws-to-delegate)
    - [🎯 Purpose and Behavior of `throws`](#-purpose-and-behavior-of-throws)
    - [🪜 Example: Chaining `throws` Across Methods](#-example-chaining-throws-across-methods)
    - [❌ `throws` Common Mistakes and Clarifications](#-throws-common-mistakes-and-clarifications)
        - [✅ Why `throws` **does not** guarantee crash prevention ?](#-why-throws-does-not-guarantee-crash-prevention-%3F)
3.  [🧩 `throws` Keyword – Edge Cases & Deep Dive](#-throws-keyword-%E2%80%93-edge-cases-%26-deep-dive)
    - [✅ Case 1: Only Throwable Types Can Be Thrown or Declared with `throws`](#-case-1-only-throwable-types-can-be-thrown-or-declared-with-throws)
    - [✅ Case 2: `throw new Exception()` Without Handling](#-case-2-throw-new-exception-without-handling)
    - [✅ Case 3: Throwing an `Error`](#-case-3-throwing-an-error)
    - [✅ Case 4: Catch Block for Never-Thrown Exception (Checked Only)](#-case-4-catch-block-for-never-thrown-exception-checked-only)
    - [`throws` `Keyword` Key Takeaways](#-throws-keyword-key-takeaways)
4.  [⚖️ `throw` vs `throws`](#-throw-vs-throws)
    - [`throws` Keyword](#-throws-keyword)
    - [`throw` Keyword](#-throw-keyword)
    - [📌`throw` vs `throws` Summary Table](#-throw-vs-throws-summary-table)
5.  [☂️ Exception Handling Keywords Summary](#-exception-handling-keywords-summary)
6.  [🧠 Common Compile-Time Errors in Exception Handling](#-common-compile-time-errors-in-exception-handling)
7.  [⚙️ Customized Exceptions (User-Defined Exceptions)](#-customized-exceptions-user-defined-exceptions)
    - [What Are Customized Exceptions?](#-what-are-customized-exceptions%3F)
    - [🧪 Example: Match-Making Age Validator](#-example-match-making-age-validator)
    - [🧰 Checked vs Unchecked Custom Exceptions](#-checked-vs-unchecked-custom-exceptions)
        - [✅ Example: Creating a Custom Checked Exception](#-example-creating-a-custom-checked-exception)
        - [✅ Example: Creating a Custom Unchecked Exception](#-example-creating-a-custom-unchecked-exception)
    - [🔍 Best Practices](#-best-practices)
    - [🧠 Bonus Tip](#-bonus-tip)

---

# 🧾 `throw` Statement

The `throw` statement in Java is used to **manually create and throw an exception**. It allows you to instantiate an exception object and explicitly pass it to the **Java Virtual Machine (JVM)** for handling.

---

**Syntax**

```java
throw new ExceptionType("error message");
```

**Key Rules about `throw`**

| Rule                      | Description                                                                         |
| ------------------------- | ----------------------------------------------------------------------------------- |
| ✅ Syntax                  | Must use `throw new ExceptionType("message");`                                      |
| ✅ Only one object allowed | You can throw only **one exception object** at a time                               |
| ✅ Must be throwable       | Object must be a subclass of `Throwable` (like `Exception` or `Error`)              |
| ⚠️ Checked exception rule | If you throw a **checked exception**, it must be handled or declared using `throws` |
| ⚠️ Location               | `throw` is used **inside a method or block**, never directly in a class body        |

--- 

```java
public class CustomException extends Exception {
    // Custom exception class
    public CustomException(String message) {
        super(message);
    }
}

public class Test {
    public static void main(String[] args) {
        try {
            // Manually throw an exception
            throw new CustomException("This is a custom exception");
        } catch (CustomException e) {
            // Handle the custom exception
            System.out.println("Custom exception caught: " + e.getMessage());
        }
    }
}
```

Output :

```java
Custom exception caught: This is a custom exception
```

---

### 🤔 JVM vs Developer Thrown Exceptions

Both JVM and developers can throw exceptions. The difference is **who triggers it**.

---

#### 🔁 Program 1 – Thrown by JVM (Automatically)

```java
public class Test {
    public static void main(String[] args) {
        System.out.println(10 / 0); // JVM automatically throws ArithmeticException
    }
}
```

#### 🔁 Program 2 – Thrown by Developer (Manually)

```java
public class Test {
    public static void main(String[] args) {
        throw new ArithmeticException("/ by zero"); // Manually thrown by developer
    }
}
```

#### 🔍 Observation:

- In **Program 1**, the JVM detects a runtime error and throws `ArithmeticException`.

- In **Program 2**, the developer explicitly throws the same exception.

- ✅ **Result is the same** — an exception is thrown and the program terminates.

---

## 🎯 `throw` Statement: Special Cases in Java

---

### 🧪 Case 1: Throwing a `null` Reference

---

If you try to `throw` a reference that is `null`, the JVM will throw a **`NullPointerException`** at runtime.

#### ✅ Valid Scenario (non-null)

```java
class Test {
    static ArithmeticException e = new ArithmeticException();

    public static void main(String[] args) {
        throw e;  // Throws ArithmeticException
    }
}
```

🟢 **Output:**

```java
Exception in thread "main" java.lang.ArithmeticException
```

#### ❌ Invalid Scenario (null reference)

```java
class Test {
    static ArithmeticException e; // default value is null

    public static void main(String[] args) {
        throw e;  // Throws NullPointerException
    }
}
```

🔴 **Output:**

```java
Exception in thread "main" java.lang.NullPointerException
    at Test.main(Test.java:5)
```

**Key Insight**

- Even though the reference type is valid (`ArithmeticException`), if the **actual object is `null`**, trying to throw it will result in a **`NullPointerException`**.

- Java doesn’t check at **compile-time** if the object is null — this is a **runtime error**.

---

### 🧪 Case 2: Unreachable Code After `throw`

Any **statement after a `throw` is considered unreachable**, and the compiler throws an error.

#### ❌ Compile-Time Error Example

```java
class Test {
    public static void main(String[] args) {
        throw new ArithmeticException("/ by zero");
        System.out.println("hello");  // ❌ Unreachable
    }
}
```

🔴 **Output:**

```java
Test.java:5: error: unreachable statement
        System.out.println("hello");
```

#### ✅ Runtime Error Example (No compile-time error)

```java
class Test {
    public static void main(String[] args) {
        System.out.println(10 / 0);  // Division by zero
        System.out.println("hello"); // Not unreachable at compile-time
    }
}
```

🔴 **Output:**

```java
Exception in thread "main" java.lang.ArithmeticException: / by zero
```

###### 🧠  Key Insight

- The compiler only flags **guaranteed** unreachable statements — `throw` is guaranteed.

- `10 / 0` is **not evaluated at compile-time**, so compiler **doesn’t know** it will fail — hence no error.

---

### 🧪 Case 3: `throw` Only Works With `Throwable` Types

The object passed to `throw` **must be a subclass of `Throwable`**. Throwing anything else causes a compile-time error.

#### ❌ Compile-Time Error: Not Throwable

```java
class Test {
    public static void main(String[] args) {
        throw new Test();  // ❌ Not a Throwable
    }
}
```

🔴 **Output:**

```java
Test.java:4: error: incompatible types: Test cannot be converted to Throwable
        throw new Test();
```

#### ✅ Valid: Custom Exception Extending Throwable

```java
class Test extends RuntimeException {
    public static void main(String[] args) {
        throw new Test();  // ✅ RuntimeException subclass
    }
}
```

🔴 **Output:**

```java
Exception in thread "main" Test
    at Test.main(Test.java:4)
```

##### 🧠 Key Insight

- You **can’t throw just any object**.

- Only classes that extend `Throwable` (like `Exception`, `Error`, or `RuntimeException`) are valid.

---

# 🏷️ `throws` Keyword

---

### 📌 What is the `throws` Statement?

If a method **may throw a checked exception**, the compiler enforces that we must either:

- Handle it with a `try-catch` block, or

- **Declare it** using the `throws` keyword.

> If neither is done, the code will **not compile**.

---

**🛠️ Syntax**

```java
returnType methodName() throws ExceptionType1, ExceptionType2 {
    // method body
}
```

---

#### 📕 Example Without Handling

```java
import java.io.*;

class Test {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter("abc.txt"); // FileNotFoundException
        out.println("hello");
    }
}
```

**🔴 Output:**

```java
Unreported exception java.io.FileNotFoundException;
must be caught or declared to be thrown
```

---

#### 🧪 Another Example

```java
class Test {
    public static void main(String[] args) {
        Thread.sleep(5000);  // InterruptedException
    }
}
```

**🔴 Output:**

```java
Unreported exception java.lang.InterruptedException;
must be caught or declared to be thrown
```

---

### ✅ Two Ways to Resolve the Error

---

#### 1.🔒 Using `try-catch` (Recommended)

```java
class Test {
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Handle the exception
            System.out.println("Interrupted!");
        }
    }
}
```

✅ **Output:**

```java
Program compiles and runs successfully
```

---

#### 2. 📨 Using `throws` to Delegate

```java
class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000); // Delegate handling
    }
}
```

✅ **Output:**

```java
Program compiles and runs successfully
```

We can use the throws keyword to delegate the responsibility of exception handling to the caller method. Then, the caller method is responsible for handling that exception.

---

## 🎯 Purpose and Behavior of `throws`

| Concept            | Description                                                                      |
| ------------------ | -------------------------------------------------------------------------------- |
| ✅ Main Purpose     | Delegate the responsibility of exception handling to the **caller method**       |
| ✅ Required For     | Only **checked exceptions**                                                      |
| ❌ Not Required For | Unchecked exceptions (e.g., `NullPointerException`, `ArithmeticException`)       |
| ⚠️ Usefulness      | It only **convinces the compiler**; it does **not prevent abnormal termination** |
| 💡 Recommendation  | Prefer `try-catch` over `throws` to handle exceptions **gracefully**             |

---

### 🪜 Example: Chaining `throws` Across Methods

```java
class Test {
    public static void main(String[] args) throws InterruptedException {
        doStuff();
    }

    public static void doStuff() throws InterruptedException {
        doMoreStuff();
    }

    public static void doMoreStuff() throws InterruptedException {
        Thread.sleep(5000);
    }
}
```

✅ **Output:**

```java
Program compiles and runs successfully
```

---

#### 🧠 Key Insight

- Each method in the call chain must declare the same checked exception using `throws`, unless it handles it with `try-catch`.

- If **even one method** omits the `throws` declaration or a handler, the program will not compile.

---

### ❌ `throws` Common Mistakes and Clarifications

---

#### ⚠️ Using `throws` with Unchecked Exception

```java
public class Test {
    public static void main(String[] args) throws ArithmeticException {
        System.out.println(10 / 0);
    }
}
```

✅ This will **compile**  
🔴 But the program still **crashes at runtime**

> `throws` has **no effect on unchecked exceptions** — it only exists to **satisfy the compiler** for **checked** ones.

---

**Summary Table**

| Keyword     | Purpose                | Applicable To       | Prevents Crash?                   | Required by Compiler?          |
| ----------- | ---------------------- | ------------------- | --------------------------------- | ------------------------------ |
| `try-catch` | Handles the exception  | Checked & Unchecked | ✅ Yes (if properly handled)       | ✅ Yes (for checked exceptions) |
| `throws`    | Declares the exception | Checked only        | ❌ No (just shifts responsibility) | ✅ Yes (for checked exceptions) |

---

#### ✅ Why `throws` **does not** guarantee crash prevention ?

When you use `throws`, you're simply **declaring** that a method **might throw** a checked exception. This:

- **Shifts the responsibility** of handling the exception to the **calling method**.

- If **none** of the calling methods **handle it with try-catch**, and it eventually reaches `main()` **without being caught**, the JVM will handle it by:
  
  - Printing a stack trace.
  
  - And **terminating the program abnormally** (crash-like behavior).

---

**🧪 Example:**

```java
class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000); // Not handled, just declared
    }
}
```

✔️ This **compiles** because the compiler sees the `throws`.

❌ But if an `InterruptedException` occurs during runtime and it's not caught, the **program will terminate abnormally**.

---

**✅ Contrast with try-catch:**

```java
public class Test {
    public static void main(String[] args) {
        try {
            Thread.sleep(5000); // Handled
        } catch (InterruptedException e) {
            System.out.println("Handled safely.");
        }
    }
}
```

✔️ **Handled properly** → **No crash**.

---

#### 🔁 Conclusion:

> Declaring with `throws` is just telling the compiler: “I acknowledge this method might throw something—but **I won’t handle it here**.”

Unless **someone else does** (eventually), the JVM will, and it won't be graceful.

---

## 🧩 `throws` Keyword – Edge Cases & Deep Dive

---

### ✅ Case 1: Only Throwable Types Can Be Thrown or Declared with `throws`

#### ❌ Invalid — Not Throwable

```java
class Test3 {
    public static void main(String[] args) throws Test3 {
    }
}
```

🔴 **Compile-Time Error:**

```java
incompatible types: Test3 cannot be converted to Throwable
```

🧠 You can **only use `throws` with subclasses of `Throwable`** (`Exception`, `Error`, or their subclasses). Here, `Test3` is not a Throwable, so the compiler rejects it.

---

**✅ Valid Example — Extending Throwable**

```java
class Test3 extends RuntimeException {
    public static void main(String[] args) throws Test3 {
    }
}
```

✔️ **Compiles and runs successfully**  

Because `Test3` is a subclass of `RuntimeException`, which is a subclass of `Throwable`.

---

### ✅ Case 2: `throw new Exception()` Without Handling

```java
class Test3 {
    public static void main(String[] args) {
        throw new Exception(); // ❌ Unhandled checked exception
    }
}
```

🔴 **Compile-Time Error:**

```java
unreported exception java.lang.Exception;
must be caught or declared to be thrown
```

> `Exception` is a checked exception, and Java forces you to either handle it with `try-catch` or declare it using `throws`.

#### ✅ Fix with `throws`

```java
class Test3 {
    public static void main(String[] args) throws Exception {
        throw new Exception(); // ✅ Now it's valid
    }
}
```

---

### ✅ Case 3: Throwing an `Error`

```java
class Test3 {
    public static void main(String[] args) {
        throw new Error("Something went terribly wrong");
    }
}
```

🟠 **Runtime Error:**

```java
Exception in thread "main" java.lang.Error: Something went terribly wrong
```

> `Error` is a subclass of `Throwable` but **not meant to be handled** — typically used by JVM to signal **serious problems** like `OutOfMemoryError`.

🧠 **Note:** You *can* throw an `Error`, but it’s discouraged unless absolutely necessary.

---

### ✅ Case 4: Catch Block for Never-Thrown Exception (Checked Only)

#### ❌ Invalid — No Exception Expected

```java
public class NoExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 + 20; // No exception here
        } catch (IOException e) {
            System.out.println("Caught IOException");
        }
    }
}
```

🔴 **Compile-Time Error:**

```java
exception java.io.IOException is never thrown in body of corresponding try statement
```

> The compiler knows `IOException` won't be thrown, so the `catch` block is **redundant** for checked exceptions.

---

#### ✅ Valid — Matching Exception Present

```java
public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // Triggers ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
```

✅ **Output:**

```java
Caught: / by zero
```

> `ArithmeticException` is an **unchecked exception**, so the compiler doesn’t enforce presence or absence of a `catch` block — but including it is valid and useful.

---

### throws `Keyword` Key Takeaways

| Case                                                             | Rule                                                       |
| ---------------------------------------------------------------- | ---------------------------------------------------------- |
| ✅ `throws` requires subclasses of `Throwable`                    | You cannot declare non-Throwable types                     |
| ✅ Checked exceptions must be handled                             | Either with `try-catch` or `throws`                        |
| ❌ `throws` not needed for `Error`                                | Errors are serious issues, not for regular flow            |
| ❌ Don't catch exceptions that aren't possible                    | Especially for **checked exceptions** — compiler will warn |
| ✅ For `RuntimeException`, use catch only if recovery is possible | Otherwise, let it propagate                                |

---

## ⚖️ `throw` vs `throws`

### `throws` Keyword

- Used in **method declarations**.

- Indicates that the method **might throw** certain types of **checked exceptions**.

- Does **not actually throw** an exception—it just delegates the responsibility to the caller.

- Multiple exception types can be declared, separated by commas.

- Mainly used to **satisfy the compiler** for checked exceptions.

**Syntax:**

```java
returnType methodName(parameters) throws ExceptionType1, ExceptionType2 {
    // method body
}
```

---

### `throw` Keyword

- Used to **explicitly throw** an exception from a method or block.

- Used to create and throw a **specific exception object**.

- Can throw any **subclass of `Throwable`** (i.e., `Exception`, `Error`, or their subclasses).

- Often used when **custom conditions** are met in code.

**Syntax:**

```java
throw new ExceptionType("Custom error message");
```

---

### 📌`throw` vs `throws`  Summary Table

| Feature           | `throw`                        | `throws`                                    |
| ----------------- | ------------------------------ | ------------------------------------------- |
| Purpose           | Actually throws an exception   | Declares possible exceptions                |
| Position          | Inside method/block            | In method signature                         |
| Used For          | Throwing a specific exception  | Delegating exception handling               |
| Can Be Used With  | Single instance of `Throwable` | One or more checked exceptions              |
| Causes Exception? | ✅ Yes                          | ❌ No (just declares)                        |
| Example           | `throw new IOException();`     | `public void readFile() throws IOException` |

---

#### 🧠 In Summary

- **`throw`** → actually throws an exception instance during execution.

- **`throws`** → declares potential exceptions a method might throw, so that the **caller must handle them**.

---

# ☂️ Exception Handling Keywords Summary

| Keyword   | Purpose                                                                                             |
| --------- | --------------------------------------------------------------------------------------------------- |
| `try`     | Encloses risky code that might throw an exception.                                                  |
| `catch`   | Handles specific exceptions thrown in the corresponding `try` block.                                |
| `finally` | Executes **cleanup code** regardless of whether an exception was thrown or not.                     |
| `throw`   | Manually creates and throws an exception object.                                                    |
| `throws`  | Declares exceptions in a method signature, delegating handling responsibility to the caller method. |

---

# 🧠 Common Compile-Time Errors in Exception Handling

| Error Message                                                          | Cause                                                                                        |
| ---------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| `exception XXX has already been caught`                                | A parent exception is caught before its child, making the child catch block unreachable.     |
| `unreported exception XXX; must be caught or declared to be thrown`    | A checked exception is thrown but not caught or declared using `throws`.                     |
| `exception XXX is never thrown in body of corresponding try statement` | The `catch` block is written for an exception that cannot possibly occur in the `try` block. |
| `try without catch or finally`                                         | A `try` block must be followed by at least one `catch` or `finally`.                         |
| `catch without try`                                                    | A `catch` block is used without a corresponding `try` block.                                 |
| `finally without try`                                                  | A `finally` block is used without an accompanying `try`.                                     |
| `incompatible types: found Test, required java.lang.Throwable`         | `throws` or `throw` is used with a type that does not extend `Throwable`.                    |
| `unreachable statement`                                                | A statement after `throw`, `return`, or infinite loop is unreachable.                        |

---

# ⚙️ Customized Exceptions (User-Defined Exceptions)

We can define our own exceptions to better represent **domain-specific or business logic errors**. These are called **customized or user-defined exceptions**.

---

### What Are Customized Exceptions?

Sometimes, built-in exceptions are not sufficient to express **application-specific error conditions**. In such cases, we can create our **own exception classes**, known as **customized (or user-defined) exceptions**.

Built-in exceptions (like `NullPointerException`, `ArithmeticException`, etc.) cover generic errors. But in real-world applications, we often need **application-specific errors** such as:

- `InsufficientFundsException`

- `TooYoungException`

- `TooOldException`

---

### 🧪 Example: Match-Making Age Validator

```java
import java.util.Scanner;

// ✅ Custom unchecked exception
class TooYoungException extends RuntimeException {
    TooYoungException(String message) {
        super(message);
    }
}

// ✅ Custom unchecked exception
class TooOldException extends RuntimeException {
    TooOldException(String message) {
        super(message);
    }
}

public class CustomExceptionDemo {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter your age: ");
        int age = scn.nextInt();

        if (age > 60) {
            throw new TooYoungException("Please wait some time – you'll get the best match!");
        } else if (age < 18) {
            throw new TooOldException("You are too late for this process.");
        } else {
            System.out.println("You will receive your match details via email.");
        }
    }
}
```

### 🧰 Checked vs Unchecked Custom Exceptions

| Type            | Extends            | Must be handled or declared? | Use case                                                                                      |
| --------------- | ------------------ | ---------------------------- | --------------------------------------------------------------------------------------------- |
| ✅ **Unchecked** | `RuntimeException` | ❌ No                         | - For programming errors or invalid input.                   - When the caller can’t recover. |
| ✅ **Checked**   | `Exception`        | ✅ Yes                        | - For recoverable errors.    - When the exception is part of the method’s contract.           |

---

#### ✅ Example: Creating a Custom Checked Exception

```java
// Checked exception
class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}
```

> Must be declared using `throws` or handled using `try-catch`.

---

#### ✅ Example: Creating a Custom Unchecked Exception

```java
// Unchecked exception
class InvalidOperationException extends RuntimeException {
    InvalidOperationException(String message) {
        super(message);
    }
}
```

> Can be thrown without explicit handling.

---

### 🔍 Best Practices

- ✅ Prefer **unchecked exceptions** (`RuntimeException`) for programmer errors or unexpected logic issues.

- ✅ Use **checked exceptions** (`Exception`) for conditions that the **caller is expected to handle**.

- ❗ Avoid using `Error` or creating custom `Error` subclasses — they represent **JVM-level failures** (e.g., `OutOfMemoryError`).

---

#### 🧠 Bonus Tip

You can even catch **any `Throwable`** — including `Errors` — but it’s usually discouraged unless you're writing **very low-level error handling** like logging or final cleanup.

```java
try {
    // some risky code
} catch (Throwable t) {
    // handles Exception or Error
}
```


