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
