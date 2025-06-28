# Java Fundamental

## Table of Contents

1. [Identifiers, Keywords and DataTypes](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Fundamental/concept/01_Java_Basics_Identifiers_Keywords_DataTypes.md)
2. [Arrays in Java](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Fundamental/concept/02_Array_Concepts_Internal_Memory.md)
3. [Variables & Execution Fundamentals](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Fundamental/concept/03_Variables_Types_Operators_ControlFlow.md)

--- 

# 💥 Introduction to Exceptions

---

## ❗ What is an Exception?

An **exception** is an **unexpected event** that disrupts the **normal flow** of a program. Think of it like hitting a speed bump while driving — your journey is interrupted until you find a way to recover.

Examples of exceptions include:

- Dividing by zero

- Accessing a file that doesn't exist

- Running out of memory

---

### 🛠️ What is Exception Handling?

**Exception handling** is the mechanism to deal with these unexpected issues without crashing the program.  
Exception handling doesn't mean “fixing” or "repairing" an exception, but rather **providing an alternative path**   to continue the rest of the program normally.

> 💡 Think of it like rerouting traffic after a roadblock — the journey continues, just on a different path.

---

### 💡 Why is Exception Handling Important?

Without exception handling:

- The program might crash abruptly.

- Users see technical errors instead of meaningful messages.

- It makes software feel unreliable and unprofessional.

With proper exception handling:

- The program shows a **user-friendly message**.

- You can log the issue, recover gracefully, or offer alternatives.

---

### 📌 Example Scenario

Let’s say your program tries to read a file from a remote server in London. If the file is not found, you can handle the exception and fall back to a local file.

```java
try {
    // Attempt to read data from the remote London file
    readFrom("london_data.txt");
} catch (FileNotFoundException e) {
    // Fall back to a local file if the remote one is missing
    readFrom("local_backup.txt");
}
```

This ensures the program doesn’t crash and continues functioning smoothly.

---

# 🧠 Java Runtime Stack Mechanism

---

### 🧵 Per Thread Stack Creation

For every thread, the **JVM creates a separate runtime stack** at the time of thread creation. This stack keeps track of method execution flow.

- Each method call by the thread is stored as a **stack frame** (also called an **activation record**).

- When a method completes, its corresponding stack frame is **popped off** the stack.

- Once all method calls are completed and the stack is empty, the **JVM destroys the stack**, and the thread terminates normally.

---

**📘 Example**

```java
class Test {
    public static void main(String[] args) {
        doStuff();
    }

    public static void doStuff() {
        doMoreStuff();
    }

    public static void doMoreStuff() {
        System.out.println("Hello");
    }
}
```

**💡 Output:**

```java
Hello
```

---

# 🧰 Default Exception Handling in Java

When an exception occurs in a method and **no custom handling is provided**, Java uses its **default exception handling mechanism**.

---

### ⚙️ How Default Exception Handling works Internally?

1. The method where the exception occurs **creates an Exception object** containing:
   
   - **Name** of the exception (e.g., `ArithmeticException`)
   
   - **Description** of the error (e.g., `/ by zero`)
   
   - **Location** where it occurred (**stack trace**)

2. The Exception object is **thrown** to the **Java Virtual Machine (JVM)**.

3. The JVM checks whether the **current method has exception handling code** (i.e., `try-catch`).
   
   - If not, it **terminates that method**, removes its stack frame, and **propagates the exception** to the **caller**.

4. This process continues **up the call stack** until:
   
   - The JVM finds appropriate handling code, or
   
   - It reaches the `main()` method.

5. If `main()` also lacks a `try-catch` block:
   
   - The JVM **hands over** the exception to the **default exception handler**.

The default handler **prints the error** in this format :

```java
Exception in thread "main" java.lang.ExceptionName: Description
    at ClassName.methodName(FileName.java:LineNumber)
    ...
```

---

###### 📌 Example

```java
class Test {
    public static void main(String[] args) {
        doStuff();
    }

    public static void doStuff() {
        doMoreStuff();
    }

    public static void doMoreStuff() {
        System.out.println(10 / 0); // ArithmeticException
    }
}
```

###### 💥 Output:

```java
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at Test.doMoreStuff(Test.java:10)
    at Test.doStuff(Test.java:7)
    at Test.main(Test.java:4)
```

---

# 🧱 Java Exception Hierarchy

In Java, **`Throwable`** is the **root class** for anything that can be thrown using the `throw` statement. It branches into two major categories:

---

```java
                                Object
                                  │
                         ┌────────┴────────┐
                         │    Throwable    │
                         └────────┬────────┘
                                  │
                ┌─────────────────┴─────────────────┐
                │                                   │
            Exception                             Error
          (Recoverable)                     (Non-Recoverable)
```

### 1. `Exception` – ✅ Recoverable

- **Cause**: Usually caused by **programmer errors or external conditions**.

- **Recoverable**: Can be handled using try-catch blocks.

- **Examples**:
  
  - `FileNotFoundException`
  
  - `IOException`
  
  - `SQLException`

🔁 Example:  
If a file is missing, you can switch to a backup file and continue.

---

### 2. `Error` – ❌ Non-Recoverable

- **Cause**: Typically caused by **system-level failures**, not by program logic.

- **Non-recoverable**: Handling is not practical in most cases.

- **Examples**:
  
  - `OutOfMemoryError`
  
  - `StackOverflowError`
  
  - `InternalError`

🚨 Example:  
If you get an `OutOfMemoryError`, the JVM can’t allocate memory, and the only fix is increasing heap size — something a **system admin** must do.

---

# ✅ Checked vs ❌ Unchecked Exceptions

---

## 🔍 What is a Checked Exception?

- **Checked exceptions** are exceptions that the **compiler checks at compile time**.

- The compiler **forces the programmer** to handle them using  `try-catch` or by declaring `throws`.

- These exceptions are usually **recoverable** — meaning the program can take alternate action and continue.

**Examples:**

- `FileNotFoundException`

- `IOException`

- *(Custom)* `HallTicketMissingException`, `PenNotWorkingException`

---

## ⚠️ What is an Unchecked Exception?

- **Unchecked exceptions** are **not checked by the compiler** for handling.

- They occur at **runtime** and handling them is optional.

- These often result from **programming mistakes** such as accessing a null reference or dividing by zero.

- They typically represent **bugs or logical mistakes**.

**Examples:**

- `ArithmeticException`

- `NullPointerException`

- *(Custom)* `BombBlastException`

---

### 📌 Summary: What’s Considered Unchecked?

**Unchecked exceptions** are:

- All subclasses of **`RuntimeException`**

- All subclasses of **`Error`**

> These are **not checked by the compiler**, meaning the code will **compile even if they are not handled** using `try-catch` or declared using `throws`.

---

🔍 **Why are they unchecked?**

- They usually represent:
  
  - **Programmer mistakes** (e.g., `NullPointerException`)
  
  - **System-level failures** (e.g., `OutOfMemoryError`)

- The compiler **cannot reliably predict** or prevent these.

- Java leaves handling up to **developer discretion**, rather than enforcing it.

> In contrast, checked exceptions signal predictable issues (like I/O problems), so Java enforces handling to ensure graceful recovery.

---

### 📚 Java Exception Hierarchy

```java
                                Object
                                  │
                         ┌────────┴────────┐
                         │    Throwable    │
                         └────────┬────────┘
                                  │
                ┌─────────────────┴─────────────────┐
                │                                   │
             Exception                            Error
           (Recoverable)                   (Non-Recoverable)
                │                                   │
      ┌─────────┴──────────┐             ┌──────────┴───────────┐
      │                    │             │                      │
 Checked Exception     Unchecked     VirtualMachineError   AssertionError
    (Compile-time)     (Runtime)     (e.g. OutOfMemory)         │
     │                     │                                    ...
 ┌───┴─────────┐    ┌─────┬─────────── ┐
 │ IOException │    │ RuntimeException │
 │             │    └─────┬────────────┘
 │ SQLException│          │
 │ FileNotFound│    ┌─────┴────────────┐
 │ EOFException│    │                  │
 │ ...         │    │ NullPointerException
 │             │    │ ArithmeticException
 │             │    │ ArrayIndexOutOfBoundsException
 │             │    │ ClassCastException
 │             │    │ IllegalArgumentException
 │             │    │ NumberFormatException
 │             │    │ ...


```

## 🧬 Fully Checked vs ⚠️ Partially Checked Exceptions

---

### ✅ Fully Checked Exception Family

A **fully checked exception** is a checked exception where **all of its subclasses are also checked**.

The **compiler enforces handling** throughout the entire inheritance tree.

###### Examples:

- `IOException` → `FileNotFoundException`, `EOFException`, etc.

- `InterruptedException`

---

## ⚠️ Partially Checked Exception

A **partially checked exception** is a checked exception where **some of its subclasses are unchecked**.

Java has **only two partially checked exceptions**:

- `Throwable`
  
  - Subclasses:
    
    - `Error` → Unchecked ❌
    
    - `Exception` → Mixed ✅❌

- `Exception`
  
  - Subclasses:
    
    - `IOException` → Checked ✅
    
    - `RuntimeException` → Unchecked ❌

---

### 🤔 Why Do Partially Checked Exceptions Exist?

- **Runtime exceptions** like `NullPointerException`, `ArithmeticException`, etc., are often **unpredictable**.

- Forcing developers to handle all of them would:
  
  - Add unnecessary boilerplate
  
  - Obscure core business logic

Java’s design allows these to be unchecked to **simplify coding** while still providing the **option** to catch them.

---

# 🛠️ Customized Exception Handling

---

## Customized Exception Handling using `try-catch`

###### 🔍 What Is It?

When we expect a block of code to potentially raise an exception (known as **risky code**), it’s best to **wrap it inside a `try` block**, and write the **handling logic inside a `catch` block**.

This allows the program to **handle errors gracefully** instead of terminating unexpectedly.

---

#### ✅`try-catch` Syntax

```java
try {
    // Risky code
} catch (Exception e) {
    // Handling code
}
```

If an exception occurs in the `try` block, the matching `catch` block handles it.

---

#### ⚠️ Without Try-Catch

```java
public static void main(String[] args) {
    System.out.println("Statement 1");
    System.out.println(10 / 0);     // Risky code
    System.out.println("Statement 3");
}
```

**Output:**

```java
Statement 1
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at Example.main(Example.java:3)
```

🔴 Program terminated **abnormally** — Statement 3 never executes.

---

#### ✅ With Try-Catch

```java
public static void main(String[] args) {
    System.out.println("Statement 1");
    try {
        System.out.println(10 / 0);  // Risky code
    } catch (ArithmeticException e) {
        System.out.println(10 / 2);  // Handling code
    }
    System.out.println("Statement 3");
}
```

**Output:**

```java
Statement 1
5
Statement 3
```

🟢 The program **continues normally** because the exception was handled.

---

#### 🔄 Control Flow in `try-catch`

```java
try {
    statement1;
    statement2;
    statement3;
} catch (X e) {
    statement4;
}
statement5;
```

✅ Case 1: If there is No Exception

- Executes: `statement1 → statement2 → statement3 → statement5`

- ✅ Normal termination

###### ✅ Case 2: If Exception is raised at `statement2` and catch block matches

- `statement1` Executes

- `statement4` Executes (inside catch block).

- `statement5` Executes.

- ✅ Normal termination

###### ❌ Case 3: If Exception is raised at `statement2` and catch block does not match

- Executes: `statement1`  then (exception)

- ❌ Abnormal termination occurs after statement1.

###### ❌ Case 4: If an Exception is raised at `statement4` or `statement5`

- Execution stops at the exception.

- ❌ Abnormal termination of the program always occurs.

---

##### try catch Important Notes

- Once an exception occurs, the remaining statements in the `try` block **are skipped** — even if the exception is caught.

- Keep your `try` block **as short as possible** — only wrap risky code.

- Exceptions can occur in:
  
  - `try` block
  
  - `catch` block
  
  - `finally` block

- If an exception occurs **outside any `try` block**, the program will **terminate abnormally** unless it’s caught at a higher level.

---

## 🛠️ Various Methods to Print Exception Information

Java provides **three main methods** in the `Throwable` class to print or retrieve exception information:

---

### 📄 Method Comparison Table

| Method              | Description                                                                                                                                                                                                                                                                                   |
| ------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `printStackTrace()` | Prints full exception info:                                                             • Name of exception                                                                     • Description                                                                                   • Stack trace |
| `toString()`        | Prints:                                                                                              • Name of exception                                                                       • Description (no stack trace)                                                                 |
| `getMessage()`      | Returns only the **description/message** of the exception (no class name)                                                                                                                                                                                                                     |

✅ Code Example

```java
public class ExceptionPrintDemo {
    public static void main(String[] args) {
        try {
            System.out.println(10 / 0);
        } catch (ArithmeticException e) {
            e.printStackTrace();              // full details
            System.out.println(e);            // toString()
            System.out.println(e.getMessage()); // only message
        }
    }
}
```

🖨️ Output

```java
java.lang.ArithmeticException: / by zero
	at ExceptionPrintDemo.main(ExceptionPrintDemo.java:4)

java.lang.ArithmeticException: / by zero
/ by zero
```

> Note : The **default exception handler** in Java **internally uses `printStackTrace()`** to display the exception on the console when it's uncaught.

---

## 🔀 `try` with Multiple `catch` Blocks

---

###### 🧠 Why Multiple `catch` Blocks?

- Different exceptions require **different handling strategies**.

- Java allows you to use **multiple `catch` blocks**, one for each exception type.

- This improves clarity, modularity, and debuggability.

---

### ✅ General Syntax

```java
try {
    // Risky code
} catch (FileNotFoundException e) {
    // Handle file-related errors
} catch (ArithmeticException e) {
    // Handle division or numeric issues
} catch (SQLException e) {
    // Handle DB access errors
} catch (Exception e) {
    // Default catch-all (must come last)
}
```

---

### ⚠️ Order of Catch Blocks

The **order of catch blocks is crucial**. Catch blocks must be ordered from:

> **Most specific (child class)** → **Most general (parent class)**

If a parent type (like `Exception`) comes **before** a child type (like `ArithmeticException`), the child block becomes **unreachable**, causing a **compile-time error**

---

#### ❌ Incorrect Order (Compile-Time Error)

```java
public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(10 / 0);
        } catch (Exception e) {              // ❗ Parent first
            e.printStackTrace();
        } catch (ArithmeticException e) {    // ❌ Unreachable
            e.printStackTrace();
        }
    }
}
```

Output :

```java
Test.java:9:11
java: exception java.lang.ArithmeticException has already been caught
```

---

#### ✅ Correct Order (Child First → Parent Later)

```java
public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(10 / 0);
        } catch (ArithmeticException e) {    // ✅ Specific first
            e.printStackTrace();
        } catch (Exception e) {              // ✅ General later
            e.printStackTrace();
        }
    }
}
```

✅ **This compiles and runs successfully.**

---

#### 📝 Order of Catch Blocks Key points :

- You **must** maintain order: specific → general.

- The last catch block often uses `Exception` to serve as a **generic fallback**.

- If you break this rule, you'll face:  
  **`Compile-time error: exception <child> has already been caught`**

---

# 🧹 `finally` Block

---

### 🧹 Purpose of the `finally` Block

The `finally` block in Java is designed to contain **cleanup code** that must execute **regardless** of whether an exception occurs or not.

✅ It **always executes**:

- Whether an exception occurs or not

- Whether the exception is caught or not

- Even if the method returns early or exits unexpectedly

---

### ❌ **Why Not Put Cleanup in `try` or `catch`?**

- **Inside `try`:** If an exception is thrown mid-way, the cleanup code after it might never run.

- **Inside `catch`:** If no exception occurs, the `catch` block is skipped, and cleanup won’t run.

🧠 Hence, cleanup logic (like closing files, releasing DB connections, etc.) should be put in the `finally` block.

---

**✅ Syntax**

```java
try {
    // Risky code
} catch (Exception e) {
    // Exception handling
} finally {
    // Cleanup code (always runs)
}
```

---

#### `finally` block Key Property

> **The `finally` block always executes**, no matter:
> 
> - Whether an exception is thrown
> 
> - Whether the exception is caught or uncaught
> 
> - Whether there’s a `return` or `System.exit()` in the try/catch block (with one exception—see below)

---

#### 📦 Use Cases

Common cleanup actions in `finally`:

- Closing file or database connections

- Releasing resources (sockets, threads, streams)

- Rolling back transactions (if needed)

---

## 🔁 `finally` Common  Execution Scenarios (Case-wise)

---

### ✅ **Case 1: No Exception Raised**

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try block executed");
        } catch (ArithmeticException e) {
            System.out.println("catch block executed");
        } finally {
            System.out.println("finally block executed");
        }
    }
}
```

🟢 Output:

```java
try block executed
finally block executed
```

🧠 **Note:** Catch block skipped since no exception is thrown.

---

#### ⚠️ **Case 2: Exception Raised & Catch Block Matches**

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try block executed");
            System.out.println(10 / 0);  // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("catch block executed");
        } finally {
            System.out.println("finally block executed");
        }
    }
}
```

**🟡 Output:**

```java
try block executed
catch block executed
finally block executed
```

🧠 **Note:** All blocks execute normally.

---

#### ❌ **Case 3: Exception Raised but Catch Block Doesn't Match**

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try block executed");
            System.out.println(10 / 0);  // ArithmeticException
        } catch (NullPointerException e) {
            System.out.println("catch block executed");
        } finally {
            System.out.println("finally block executed");
        }
    }
}
```

**🔴 Output:**

```java
try block executed
finally block executed
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at Test.main(Test.java:7)
```

🧠 **Note:** Catch block doesn't match → skipped.  
`finally` block still executes before abnormal termination.

---

## 🔁 `return` vs `finally`:

###### Key Rule

> Even if a `return` statement exists in the `try` or `catch` block, the **`finally` block always executes before** the method returns.

✅ This ensures that **cleanup logic runs** no matter how the method exits.

---

### ✅ Example 1: `return` in `try`, `finally` still executes

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try block executed");
            return;
        } catch (ArithmeticException e) {
            System.out.println("catch block executed");
        } finally {
            System.out.println("finally block executed");
        }
    }
}
```

**🟢 Output:**

```java
try block executed
finally block executed
```

 🧠 **Even if a `return` is hit inside `try`, the `finally` block **executes first** before control leaves the method.

---

### ✅ Example 2: `return` in try, catch, and finally → `finally` dominates

```java
class Test {
    public static void main(String[] args) {
        System.out.println(m1());
    }

    public static int m1() {
        try {
            System.out.println(10 / 0);
            return 777;
        } catch (ArithmeticException e) {
            return 888;
        } finally {
            return 999;
        }
    }
}
```

**🟡 Output:**

```java
999
```

🧠 Even though the catch block returns `888`, the `finally` block **overrides** it with `999`.

> ✅ **If `finally` has a return statement, it always wins.**

---

#### ❌ `System.exit(0)` vs `finally`

This is the **only time `finally` is not executed** — when the **JVM shuts down** explicitly.

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try");
            System.exit(0);
        } catch (ArithmeticException e) {
            System.out.println("catch block executed");
        } finally {
            System.out.println("finally block executed");
        }
    }
}
```

**🔴 Output:**

```java
try
```

🧠 `System.exit(0)` terminates the JVM immediately — no more code runs, not even the `finally` block.

---

#### ℹ️ Note on Exit Codes:

- `System.exit(0)` → Normal termination

- `System.exit(non-zero)` → Abnormal termination

In both cases, `finally` **won’t run**, because the **JVM itself stops**.

---

### 🔀 `final` vs `finally` vs `finalize`

| Keyword      | Type     | Description                                                                                                                                                                                                                                                                                                                                             |
| ------------ | -------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `final`      | Modifier | A modifier used to restrict further modification.             - Used with **classes**, **methods**, and **variables**.                 - Prevents **inheritance**, **overriding**, and **reassignment**, respectively.                                                                                                                                  |
| `finally`    | Block    | A block used with try-catch to define cleanup code.    ✅ Always executes regardless of exception occurrence, handling, or return.                                      🧹 Ideal for closing resources, like files, DB connections, etc                                                                                                                  |
| `finalize()` | Method   | A method called by the Garbage Collector before destroying an object.                                                         ✅ Used to perform last-minute cleanup related to the object.                                                                                  ⚠️ Not guaranteed to execute promptly or reliably. Deprecated since Java 9. |

---

###### 🧠 `final` vs `finally` vs `finalize` Important Note

- The `finally` block is **preferred over `finalize()`** for cleanup because:
  
  - ✅ `finally` executes predictably
  
  - ❌ `finalize()` is **non-deterministic** and may not run at all
  
  - As of Java 9, `finalize()` is **deprecated**

---

## 🔄 Control Flow in `try-catch-finally`

```java
try {
    stmt-1;
    stmt-2;
    stmt-3;
} catch (Exception e) {
    stmt-4;
} finally {
    stmt-5;
}
stmt-6;
```

#### Case Flow Summary

| Case | Description                                | Flow              | Termination |
| ---- | ------------------------------------------ | ----------------- | ----------- |
| 1    | ✅ No exception                             | 1 → 2 → 3 → 5 → 6 | Normal      |
| 2    | ✅ Exception at `stmt-2` and **caught**     | 1 → 4 → 5 → 6     | Normal      |
| 3    | ❌ Exception at `stmt-2` and **not caught** | 1 → 5             | Abnormal    |
| 4    | ❌ Exception at `stmt-4`                    | 4 → 5             | Abnormal    |
| 5    | ❌ Exception at `stmt-5` or `stmt-6`        | 5 → 6             | Abnormal    |

---

## 🔄 Control Flow in **Nested try-catch-finally**

```java
try {
    stmt-1;
    stmt-2;
    stmt-3;
    try {
        stmt-4;
        stmt-5;
        stmt-6;
    } catch (X e) {
        stmt-7;
    } finally {
        stmt-8;
    }
    stmt-9;
} catch (Y e) {
    stmt-10;
} finally {
    stmt-11;
}
stmt-12;
```

**🧠 Case Summary**

| Case No. | Condition                                                                                                                                          | Execution Flow                                                                                                    | Termination |
| -------- | -------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------- |
| 1        | If there is no exception                                                                                                                           | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-5 → stmt-6 → stmt-8 → stmt-9 → stmt-11 → stmt-12`                       | ✅ Normal    |
| 2        | If an exception is raised at statement 2 and the corresponding outer catch block matches                                                           | `stmt-1 → stmt-10 → stmt-11 → stmt-12`                                                                            | ✅ Normal    |
| 3        | If an exception is raised at statement 2 and the corresponding outer catch block does not match                                                    | `stmt-1 → stmt-11`                                                                                                | ❌ Abnormal  |
| 4        | If an exception is raised at statement 5 and the corresponding inner catch block matches                                                           | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-7 → stmt-8 → stmt-9 → stmt-11 → stmt-12`                                | ✅ Normal    |
| 5        | If an exception is raised at statement 5, inner catch block does not match, but outer catch matches                                                | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-8 → stmt-10 → stmt-11 → stmt-12`                                        | ✅ Normal    |
| 6        | If an exception is raised at statement 5 and neither inner nor outer catch blocks match                                                            | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-8 → stmt-11`                                                            | ❌ Abnormal  |
| 7        | If an exception is raised at statement 5, handled by inner catch block, but statement 7 throws another exception and outer catch matches it        | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-7(Exception!) → stmt-10 → stmt-11 → stmt-12`                            | ✅ Normal    |
| 8        | If an exception is raised at statement 5, handled by inner catch block, but statement 7 throws another exception and outer catch does not match it | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-7(Exception!) → stmt-11`                                                | ❌ Abnormal  |
| 9        | If an exception is raised at statement 8 (inner finally block) and the outer catch block matches it                                                | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-5 → stmt-6 → stmt-8(Exception!) → stmt-10 → stmt-11 → stmt-12`          | ✅ Normal    |
| 10       | If an exception is raised at statement 8 (inner finally block) and the outer catch block does not match                                            | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-5 → stmt-6 → stmt-8(Exception!) → stmt-11`                              | ❌ Abnormal  |
| 11       | If an exception is raised at statement 9 and the outer catch block matches it                                                                      | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-5 → stmt-6 → stmt-8 → stmt-9(Exception!) → stmt-10 → stmt-11 → stmt-12` | ✅ Normal    |
| 12       | If an exception is raised at statement 9 and the outer catch block does not match                                                                  | `stmt-1 → stmt-2 → stmt-3 → stmt-4 → stmt-5 → stmt-6 → stmt-8 → stmt-9(Exception!) → stmt-11`                     | ❌ Abnormal  |
| 13       | If an exception is raised at statement 10, finally block still executes                                                                            | `stmt-10(Exception!) → stmt-11`                                                                                   | ❌ Abnormal  |
| 14       | If an exception is raised at statement 11 or statement 12                                                                                          | `stmt-11(Exception!)` or `stmt-12(Exception!)`                                                                    | ❌ Abnormal  |

**Note:**

- If we do not enter the `try` block, then the `finally` block won't be executed. Once we enter the `try` block, we cannot exit it without executing the `finally` block.

- Nested `try-catch` blocks are possible, allowing a `try-catch` inside another `try` block.

- Inner `try-catch` blocks can handle the most specific exceptions, while more generalized exceptions can be handled by outer `try-catch` blocks.

---

#### 🧪 Example (Multi-exception Clash)

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println(10/0); // ArithmeticException
        } catch(ArithmeticException e) {
            System.out.println(10/0); // New exception
        } finally {
            String s = null;
            System.out.println(s.length()); // NullPointerException
        }
    }
}
```

**Output:**

```java
NullPointerException
```

`NullPointerException` (handled by JVM)  
👉 Because only the **last exception raised** is handled by the default exception handler.

---

Summary :

| ✅ Rule                                                   | 📌 Description                         |
| -------------------------------------------------------- | -------------------------------------- |
| ✔️ `try` must be followed by either `catch` or `finally` | A lone `try` block is illegal.         |
| ✔️ `catch` and `finally` must have a corresponding `try` | Cannot exist independently.            |
| ✔️ Block order matters                                   | Always `try → catch → finally`.        |
| ✔️ Nested `try-catch-finally` is allowed                 | Enables granular error handling.       |
| ✔️ Curly braces `{}` are mandatory                       | Even if only one statement is present. |

---

## 🧩 Various Valid & Invalid Combinations of `try-catch-finally` in Java

---

#### ✅ Rule 1: A `try` block **must** be followed by **at least one** of the following:

- A `catch` block

- A `finally` block

- Or both

```java
// ✅ Valid: try with catch
try {
    // risky code
} catch (Exception e) {
    // handle exception
}

// ✅ Valid: try with finally
try {
    // risky code
} finally {
    // cleanup code
}

// ✅ Valid: try with catch and finally
try {
    // risky code
} catch (Exception e) {
    // handle exception
} finally {
    // cleanup code
}
```

---

#### ✅ Rule 2: A `catch` block **cannot exist alone**

- A `catch` block **must be associated** with a `try` block.

```java
// ❌ Invalid
catch (Exception e) {
    // handle exception
}
// Compile-time error: ‘catch’ without ‘try’
```

---

#### ✅ Rule 3: A `finally` block **cannot exist alone**

- A `finally` block **must be associated** with a `try` block.

```java
// ❌ Invalid
finally {
    // cleanup code
}
// Compile-time error: ‘finally’ without ‘try’
```

---

#### 🧱 Rule 4: **Block Order Matters**

- The order of blocks **must** be:  
  `try` → (optional multiple `catch`) → (optional `finally`)

```java
// ❌ Invalid order
catch (Exception e) { }
try { }
// Compile-time error: ‘catch’ without ‘try’
```

---

#### 🧩 Rule 5: **Nested try-catch-finally is Allowed**

- You can nest a `try-catch-finally` block **inside another**.

- Inner `try-catch` handles specific/local exceptions.

- Outer `try-catch` handles broader/global exceptions.

```java
try {
    // outer try
    try {
        // inner try
    } catch (Exception e) {
        // inner catch
    } finally {
        // inner finally
    }
} catch (Exception e) {
    // outer catch
} finally {
    // outer finally
}
```

---

#### 🧷 Rule 6: **Curly Braces `{}` Are Mandatory**

- You **must** use `{}` to define the body of each `try`, `catch`, and `finally` block.

```java
// ❌ Invalid (missing braces)
try
    System.out.println("No braces");
// Compile-time error
```
