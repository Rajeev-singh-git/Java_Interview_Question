# 📘 Introduction to Java Multithreading

## Table of Contents

1. [Multi-Tasking in Java](#-multi-tasking-in-java)


---

# 🧠 Multi-Tasking in Java

Multitasking enables a computer to execute multiple tasks **apparently simultaneously**, improving performance and responsiveness.  
In Java, multitasking is primarily achieved through:

- **Process-based multitasking** (OS-level)

- **Thread-based multitasking** (program-level)

---

### 🔧 Processes vs Threads: The Workhorses of Multitasking

---

## Processes

A **process** is an independent execution unit with its **own memory space and system resources**.

- Typically represents a running application.

- A single application may be composed of **multiple cooperating processes**.

- Communication between processes requires **Inter-Process Communication (IPC)** mechanisms (pipes, sockets, shared files).

> 💡 JVM itself runs as a process; Java can create new OS processes using `ProcessBuilder`.

##### ✅ Ideal For:

- OS-level multitasking (e.g., running independent applications).

##### 🧪 Example:

**Playing music while programming**

- Media Player and IDE run as **separate processes**.

- Each has its **own memory, state, and system resources**.

---

## Threads

A **thread** is a **lightweight process** that shares memory with other threads in the same process.

- Every Java program starts with **one main thread**.

- Multiple threads allow tasks to run **concurrently** inside one process.

- Supported by classes/interfaces like `Thread`, `Runnable`, `Executors`, etc.

✅ **Example:** A text editor handling **typing** and **syntax highlighting** in separate threads.

---

### 🔄 Process vs Thread: Quick Comparison

| Feature        | Process                           | Thread                             |
| -------------- | --------------------------------- | ---------------------------------- |
| Memory         | Separate memory space             | Shared memory space                |
| Overhead       | High (context switching + memory) | Low (lighter context switching)    |
| Communication  | Complex (IPC mechanisms)          | Easier (shared memory)             |
| Failure Impact | Isolated                          | May affect the whole process       |
| Example        | Music player & IDE                | Typing + Auto-formatting in editor |

---

### 🔁 Context Switching:

To juggle multiple processes or threads, the **Operating System uses Context Switching**:

- Saves the current task's state (registers, stack pointer, etc.)

- Loads the next task's state

⚠️ Adds some **overhead**, but allows multitasking on even a **single-core CPU**.

---

### 🧩 Multitasking vs. Parallel Processing

Though they appear similar, they are conceptually different:

| Multitasking (Simulated)                    | Parallel Processing (True)               |
| ------------------------------------------- | ---------------------------------------- |
| Uses **single core**, switches rapidly      | Uses **multiple cores** concurrently     |
| Improves responsiveness                     | Improves throughput                      |
| E.g., One hand doing multiple tasks quickly | E.g., Multiple hands doing tasks at once |

---

# 🧵 Creating  Threads in Java

---

Java offers two primary ways to define and start a new thread:

1. Extending the `Thread` class [Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/src/MyThread.java)
2. Implementing Runnable interface. [Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/src/MyRunnable.java)

---

## ✅ 1. By **Extending the `Thread` Class**

```java
public class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Child Thread");
        }
    }
}

class ThreadDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start(); // Creates a new thread and invokes run() in that thread

        for (int i = 0; i < 5; i++) {
            System.out.println("Main Thread");
        }
    }
}

```

---

### 📌 Case 1: Thread Scheduler (Unpredictable Execution Order)

- The **Thread Scheduler** decides which thread runs first when multiple threads are waiting.

- Its exact algorithm is **JVM and OS dependent** → **execution order cannot be predicted**.

- **Factors affecting scheduling:**
  
  - **Priority:** Higher priority threads usually get preference.
  
  - **Waiting State:** Threads waiting for I/O may be skipped temporarily.
  
  - **Thread State:** Only threads in `Runnable` state compete for CPU time.

---

### 📌 Case 2: `start()` vs `run()` — What's the Difference?

**`t.start()`**

- Starts a **new thread** and invokes the `run()` method **concurrently**.

- The calling thread continues independently.

- Can be called **only once** per thread object — else throws `IllegalThreadStateException`.

**`t.run()`**

- Executes the `run()` method on the **current thread** — no new thread is created.

- Behaves like a normal method call.

- Can be called **multiple times**.

---

##### 🧠 Summary

| Feature         | `t.start()`                                   | `t.run()`                                      |
| --------------- | --------------------------------------------- | ---------------------------------------------- |
| Thread creation | ✅ Starts a new thread                         | ❌ Executes on current thread                   |
| Method invoked  | `start()` ➝ JVM ➝ calls `run()` in new thread | Direct call to `run()` as a regular method     |
| Concurrency     | ✅ Executes concurrently with main thread      | ❌ Executes sequentially within current thread  |
| Reusability     | ❌ Can only be called once (else: exception)   | ✅ Can be called multiple times like any method |

> 🔥 Use `t.start()` to get true multithreading.  
> Avoid calling `run()` directly unless you're doing something intentional (like testing or chaining).

---

### 📌 Case 3: Why `start()` is Critical in Multithreading

`start()` does three key things before executing `run()`:

1. **Registers** the thread with the thread scheduler.

2. **Initializes** low-level system resources.

3. **Triggers** `run()` in a new execution path.

Without it, you’re just calling a method on the main thread.

---

## 📌 Case 4:  When `run()` Is Not Overridden

```java
class MyThread extends Thread {}

class ThreadDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
```

- **No `run()` method in `MyThread` → Inherits default `Thread.run()`**

- The default method does **nothing**, so:
  
  - ✅ Thread is created and started
  
  - ❌ No meaningful work is done

- Always **override `run()`** for actual thread functionality.

---

### 📌 Case 5: Overloading the **`run()`** method.

In Java, it's possible to overload the **`run()`** method in a subclass of **`Thread`**. However, the **`start()`** method of the **`Thread`** class always invokes the no-argument **`run()`** method. Other overloaded versions of **`run()`** must be explicitly called like normal methods to execute.

```java
public void run() {...}
public void run(int i) {...}
```

- `start()` only calls the **default `run()` method with no arguments**

- Overloaded versions must be invoked manually.

> ⚠️ **Overloading is allowed**, but **not useful** unless you're calling it directly.

---

### 📌 Case 6: Overriding  `start()`  (⚠️ Dangerous)

```java
class MyThread extends Thread {   
 public void start() { System.out.println("start method"); }    
 public void run() { System.out.println("run method"); }
}

class ThreadDemo { 
   public static void main(String[] args) {    
    MyThread t = new MyThread();  
    t.start();     
    System.out.println("main method");  
  }
}
```

Output:

```java
start method
main method
```

- Runs like a normal method → **no new thread is created**.

- JVM’s internal thread creation is skipped.

- Avoid overriding `start()` unless you explicitly call `super.start()`.

---

### 📌 Case 7: `IllegalThreadStateException`

After initiating a thread with the **`start()`** method, attempting to restart the same thread will result in a runtime exception known as "IllegalThreadStateException."

```java
MyThread t = new MyThread();
t.start(); // Valid
// Other code
t.start(); // Results in Runtime Exception: IllegalThreadStateException
```

- Once a thread has started, you **cannot restart it**.

- Doing so results in:
  
  > `java.lang.IllegalThreadStateException`

---

# 🔄 Thread Lifecycle

[](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/MultiThread_README.md#life-cycle-of-the-thread)

[![m1](https://private-user-images.githubusercontent.com/87664048/328011601-6fae331c-7bc8-410d-ab52-64af75dda128.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM1MzY3MTAsIm5iZiI6MTc1MzUzNjQxMCwicGF0aCI6Ii84NzY2NDA0OC8zMjgwMTE2MDEtNmZhZTMzMWMtN2JjOC00MTBkLWFiNTItNjRhZjc1ZGRhMTI4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI2VDEzMjY1MFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTI2NWIzMjI3ZGQyODAwMDNiZDk2Y2QwMjY1MjA5ZmIyYzg3Mzk1MDQwYzUwYmIzNTdkNmFlZWRkZTc3NDMxNTUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.2FcjjwD02vCAudhvlbvknIj7vV4orwFqZWyusJ0TjE4)](https://private-user-images.githubusercontent.com/87664048/328011601-6fae331c-7bc8-410d-ab52-64af75dda128.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM1MzY3MTAsIm5iZiI6MTc1MzUzNjQxMCwicGF0aCI6Ii84NzY2NDA0OC8zMjgwMTE2MDEtNmZhZTMzMWMtN2JjOC00MTBkLWFiNTItNjRhZjc1ZGRhMTI4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI2VDEzMjY1MFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTI2NWIzMjI3ZGQyODAwMDNiZDk2Y2QwMjY1MjA5ZmIyYzg3Mzk1MDQwYzUwYmIzNTdkNmFlZWRkZTc3NDMxNTUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.2FcjjwD02vCAudhvlbvknIj7vV4orwFqZWyusJ0TjE4)

A thread can be in one of these states:

```java
New → Runnable → Running → Waiting/Sleeping → Dead
```

- **New:** Object created but not started.

- **Runnable:** Ready to run, waiting for CPU.

- **Running:** Actively executing.

- **Waiting/Sleeping:** Temporarily paused.

- **Dead:** Execution finished.

📌 **Only one transition to running is possible; a thread cannot be restarted after finishing.**

---

## ⚠️ Common Pitfalls

1. **Overriding `start()` method**
   
   - Runs like a normal method, **no new thread** created.
   
   - Always call `super.start()` if overriding for special needs.

2. **Overloading `run()` method**
   
   - `start()` only calls the **no-argument run()**. Other versions must be called manually.
   
   - Avoid unless necessary.

3. **Calling `start()` twice**
   
   - Throws **IllegalThreadStateException**.
   
   - Create a **new Thread object** to restart.

---

## 🧵 Defining a Thread by Implementing the `Runnable` Interface

In Java, threads can also be created by implementing the `Runnable` interface, which is part of the `java.lang` package. This interface contains a single method:

[![m2](https://private-user-images.githubusercontent.com/87664048/328011615-50b564bd-8372-4391-a2f3-ea805b0951c5.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM2Mjg1MjYsIm5iZiI6MTc1MzYyODIyNiwicGF0aCI6Ii84NzY2NDA0OC8zMjgwMTE2MTUtNTBiNTY0YmQtODM3Mi00MzkxLWEyZjMtZWE4MDViMDk1MWM1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI3VDE0NTcwNlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTljMDQzZjg3ZDYxYWJjZDNjZjdjZTI2OTIwZDE1ZTgxNjQ3NDczNjAwY2Y0NjM3NGVmMzk0Yjc3YTEyZTcwMzQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.533swdFuLRUC5-xwRZwgWhbJ5bGPbbtG0r3CmfxssHg)](https://private-user-images.githubusercontent.com/87664048/328011615-50b564bd-8372-4391-a2f3-ea805b0951c5.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM2Mjg1MjYsIm5iZiI6MTc1MzYyODIyNiwicGF0aCI6Ii84NzY2NDA0OC8zMjgwMTE2MTUtNTBiNTY0YmQtODM3Mi00MzkxLWEyZjMtZWE4MDViMDk1MWM1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI3VDE0NTcwNlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTljMDQzZjg3ZDYxYWJjZDNjZjdjZTI2OTIwZDE1ZTgxNjQ3NDczNjAwY2Y0NjM3NGVmMzk0Yjc3YTEyZTcwMzQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.533swdFuLRUC5-xwRZwgWhbJ5bGPbbtG0r3CmfxssHg)

```java
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Child Thread");
        }
    }
}

class ThreadDemo2
{
    public static void main(String[]args){
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();

        for(int i=0;i<10;i++){
            System.out.println("Main Thread");
        }
    }

}
```

```java
MyRunnable r = new MyRunnable();
Thread t1 = new Thread()
Thread t2 = new Thread(r);
```

---

| Case       | Code          | Behavior                                                                               |
| ---------- | ------------- | -------------------------------------------------------------------------------------- |
| **Case 1** | `t1.start();` | ✅ Creates a new thread, runs `Thread` class's empty `run()` method (no output).        |
| **Case 2** | `t1.run();`   | ❌ No new thread. `run()` called like a normal method on main thread.                   |
| **Case 3** | `t2.start();` | ✅ Creates a new thread, executes `MyRunnable.run()` on that new thread.                |
| **Case 4** | `t2.run();`   | ❌ No new thread. `MyRunnable.run()` called like a normal method on main thread.        |
| **Case 5** | `r.start();`  | ❌ Compile-time error. `MyRunnable` has no `start()` method.                            |
| **Case 6** | `r.run();`    | ❌ No new thread. `MyRunnable.run()` executed like a regular method on the main thread. |

---

### ✅ Best Practice: Use `Runnable` Over `Thread`

| Aspect                    | Extending `Thread`               | Implementing `Runnable`              |
| ------------------------- | -------------------------------- | ------------------------------------ |
| Inheritance limitation    | Cannot extend any other class    | Can extend another class freely      |
| Design flexibility        | Tightly coupled (Thread = logic) | Loosely coupled (Thread ≠ logic)     |
| Reusability & testability | Less reusable                    | More reusable and testable           |
| Recommended?              | ❌ Not recommended                | ✅ Preferred and recommended approach |

---

**Conclusion:**

> Always prefer implementing `Runnable` over extending `Thread` when defining a thread. It promotes better design, allows flexibility with inheritance, and encourages separation of concern between the task and the thread itself.

---

# 🧵 Thread Class Constructors

| Constructor                         | Usage                                        |
| ----------------------------------- | -------------------------------------------- |
| `Thread()`                          | Empty thread (no task)                       |
| `Thread(Runnable r)`                | Runs `r.run()` in new thread                 |
| `Thread(String name)`               | Named thread, no task                        |
| `Thread(Runnable r, String name)` ✅ | Named thread with task (preferred)           |
| `Thread(ThreadGroup g, …)`          | Puts thread in a group (rarely used)         |
| `Thread(..., long stackSize)`       | Custom stack size (rare, platform-dependent) |

---

# 🧵 Getting and Setting the Name of a Thread

Every thread in Java has a **name**, which may be:

- Automatically assigned by the JVM (e.g., `"Thread-0"`, `"Thread-1"`, etc.)

- Explicitly assigned by the programmer

### 🔧 Methods from `Thread` class

```java
public final String getName(); // Returns the thread's name public
final void setName(String name); // Sets a new name for the thread
```

##### ✅ Example

```java
class MyThread extends Thread {}

class ThreadDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()); // main

        MyThread t = new MyThread();
        System.out.println(t.getName()); // Thread-0

        Thread.currentThread().setName("Bhaskar Thread");
        System.out.println(Thread.currentThread().getName()); // Bhaskar Thread
    }
}
```

> 🧠 **Note:** Use `Thread.currentThread()` to get a reference to the **currently executing thread**.

---

# 🎚️ Thread Priorities in Java

In Java, each thread has an associated **priority**, represented as an integer between **1 (lowest)** and **10 (highest)**. Thread priorities are **hints** to the JVM thread scheduler, influencing the order of thread execution—but not guaranteeing it.

---

## 🔢 Priority Basics

- Every thread has a priority.

- The default priority of the **main thread** is **5**.

- A new thread inherits the priority of its **parent thread** by default.

```java
Thread.MIN_PRIORITY  = 1;  // Lowest priority
Thread.NORM_PRIORITY = 5;  // Default priority
Thread.MAX_PRIORITY  = 10; // Highest priority
```

---

### 🔧 Setting and Getting Thread Priority

```java
public final int getPriority();              // Retrieves current thread's priority
public final void setPriority(int priority); // Sets priority (must be in 1–10 range)


```

> ❗ Setting an invalid priority (e.g., 0 or 11) throws `IllegalArgumentException`.

---

## Default Priority

The default priority for the main Thread is 5. However, for all other Threads, the default priority is inherited from parent to child. This means that the child Thread will have the same priority as its parent Thread by default.

---

### ✅ Example 1: Inheriting Priority from Parent

```java
class MyThread extends Thread {}

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getPriority()); // 5 (default)

        Thread.currentThread().setPriority(9); // Change main thread priority

        MyThread t = new MyThread(); // Inherits priority from main
        System.out.println(t.getPriority()); // 9
    }
}
```

---

### ✅ Example 2: Priority Affects Scheduling Order

```java
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("child thread");
        }
    }
}

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();

        // t.setPriority(10); // Uncomment to give child thread higher priority

        t.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main thread");
        }
    }
}
```

- If `setPriority(10)` is **commented out**, both threads have priority **5** → execution order is **unpredictable**.

- If **child thread** has priority **10** and **main thread** has priority **5**, the JVM is **likely** to execute the child thread **first**.

---

#### ⚠️ Important Considerations

| Aspect                                             | Detail                                                                       |
| -------------------------------------------------- | ---------------------------------------------------------------------------- |
| ✅ Priority Range                                   | 1 to 10 only                                                                 |
| ❗ Invalid Priority                                 | Throws `IllegalArgumentException`                                            |
| ❗ Constants like `LOW_PRIORITY` or `HIGH_PRIORITY` | ❌ Not available — use `MIN_PRIORITY`, `MAX_PRIORITY`                         |
| 🔁 Inheritance Rule                                | Child thread inherits priority from parent thread                            |
| 🤔 Scheduler Behavior                              | JVM **may** prefer higher-priority threads, but this is **not guaranteed**   |
| 🛑 Overuse Consequence                             | Overusing high priorities can lead to **starvation** of low-priority threads |

---

##### 📝 Key Takeaways

- **Use priorities sparingly**—they **influence** but **do not control** execution order.

- Threads with the **same priority** may run in any order.

- Avoid writing logic that depends strictly on thread priorities.

- For better control over concurrency, use **thread pools** or **concurrency utilities** like `Executors`.

---

# ⏸️ Methods to Prevent or Pause Thread Execution

Java provides several methods to **control** or **pause** a thread's execution. These are especially useful in managing concurrency and CPU resource allocation.

### 🧩 Methods Covered:

- `yield()`

- `join()`

- `sleep()`

- `interrupt()`

---

## 1️⃣ `yield()` Method

##### ✅ What It Does:

- `yield()` **pauses the currently executing thread**, hinting to the scheduler that **other runnable threads of the same priority** can be given a chance to execute.

- If a higher or equal priority thread is waiting, the scheduler may switch execution to it.

##### 🔧 Signature:

```java
public static native void yield();
```

### 🧠 Clarifications:

- It is **only a suggestion** to the thread scheduler, not a guarantee.

- If no other thread is waiting or the scheduler ignores the hint, the **current thread continues immediately**.

- Platform-dependent behavior; not reliable for predictable execution order.

- **Rarely used in production code**, mainly for testing or avoiding CPU starvation in specific loops.

![m3](https://private-user-images.githubusercontent.com/87664048/328074972-f410cf59-a47f-470c-9ddd-b5970bbf5a75.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM2Mjg1MjYsIm5iZiI6MTc1MzYyODIyNiwicGF0aCI6Ii84NzY2NDA0OC8zMjgwNzQ5NzItZjQxMGNmNTktYTQ3Zi00NzBjLTlkZGQtYjU5NzBiYmY1YTc1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI3VDE0NTcwNlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWM4YjMzMzA0MWIyMmI5ZDA5NDQ0NTg0ZjBkOWY0ZTBjM2VlMDQ4ODc2OGRmM2QyMWVlMTM5YzQ4NWEzYjhhMWQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.mU34w_abFWnasXNldEdr2gMbRLFGfWJSRLFgRu1v9DA)

#### ✅ Example:

```java
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            Thread.yield();
            System.out.println("Child Thread");
        }
    }
}

public class ThreadYieldDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("Main Thread");
        }
    }
}
```

### Expected Output (Non-deterministic):

```java
Main Thread
Child Thread
Main Thread
Child Thread
Main Thread
Main Thread
Child Thread
Child Thread
Child Thread
Main Thread
```

> ℹ️ **Clarification:** The order of execution is **not guaranteed**. If the main thread is already running and has no competition, `yield()` may do nothing.

---

## 2️⃣ 🔧 `join()` Method

The **`join()`** method is used to **pause the execution of the current thread** until the thread on which `join()` was called **finishes its execution**. This ensures that one thread **completes before another thread resumes**, providing a way to maintain execution order in a multithreaded environment.

---

#### ✅ join Definition

```java
public final void join() throws InterruptedException
```

- **Purpose:** Makes the **calling thread wait** for the target thread to finish.

- **Throws:** `InterruptedException` if the current thread is interrupted while waiting.

---

#### ✅  Overloaded Versions

1. `join()`  
   → Waits indefinitely until the target thread completes.

2. `join(long millis)`  
   → Waits for the target thread to finish or the specified time to pass (whichever happens first).

3. `join(long millis, int nanos)`  
   → Waits for the target thread to finish or the specified time + nanoseconds to pass.

---

### ✅ Key Rules & Behavior

1. **Calling `join()` does not start a thread**; you must call `start()` first.

2. **`join()` is called on another thread object**, making the **current thread wait** until the other finishes.

3. If the target thread:
   
   - Already finished → `join()` returns immediately.
   
   - Never started → `join()` returns immediately (thread is not alive).

4. **Does not change thread priority:**
   
   - It only **pauses the calling thread**, allowing the target thread to finish first.
   
   - Appears like the target thread got "higher priority," but it's just **less competition for CPU**.

5. If multiple threads call `join()` on the same target thread, **all will wait** until that thread completes.

6. If the current thread itself calls `join()` on itself, it will **enter a deadlock** (never resume).

7. If the main thread calls **`join()`** on a child thread object and the child thread calls **`join()`** on the main thread object, then both threads will wait for each other indefinitely, causing the program to hang. This situation resembles a deadlock.

---

 ![m4](https://private-user-images.githubusercontent.com/87664048/328074987-43bb4211-aa2a-4c9b-9484-b6f9182cafdd.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM2Mjg1MjYsIm5iZiI6MTc1MzYyODIyNiwicGF0aCI6Ii84NzY2NDA0OC8zMjgwNzQ5ODctNDNiYjQyMTEtYWEyYS00YzliLTk0ODQtYjZmOTE4MmNhZmRkLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI3VDE0NTcwNlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTcxYmU5M2Q1NDZiYmQ2YzUyNGNlYjRiMTlhNmYxM2I5MGFlNTg3OTA4MWJiZjg1OWRjM2Y5MzUzN2E1MGRiYjkmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.X94RN8vNJVSiTZJBBJ9-8G-Kzaa5qYr7SGkdfZNHpOc)

---

### ✅ Example

```java
class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try {
                Thread.sleep(500); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t1.join();  // Main thread waits for t1 to complete

        t2.start();
        t2.join();  // Main thread waits for t2 to complete

        System.out.println("Main thread ends after t1 and t2 complete");
    }
}

```

##### ✅ Expected Output

```java
Thread-0 - Count: 1
Thread-0 - Count: 2
Thread-0 - Count: 3
Thread-0 - Count: 4
Thread-0 - Count: 5
Thread-1 - Count: 1
Thread-1 - Count: 2
Thread-1 - Count: 3
Thread-1 - Count: 4
Thread-1 - Count: 5
Main thread ends after t1 and t2 complete
```

---

#### Usage Scenarios

- When one thread’s task **must finish before another thread continues**.

- Useful in cases where **results from one thread are required** for the next step in execution.

- Common in **main thread coordination** with worker threads.

---

#### Summary:

- `join()` = **Thread synchronization** → **Current thread waits** for another thread to finish.

- Does **not alter priority** but gives the target thread more chance to run by removing competition.

---

## 3️⃣🔧 `sleep()` Method

The **`sleep()`** method is used to **pause the execution of the current thread temporarily** for a specified time. During this pause, the thread **remains in a TIMED_WAITING state** and **does not lose its lock** if it already holds one. After the sleep duration ends, the thread becomes **ready for execution** again (runnable state).

---

#### ✅  Definition

```java
public static void sleep(long millis) throws InterruptedException
public static void sleep(long millis, int nanos) throws InterruptedException

```

- **Purpose:** Temporarily pauses the **current thread** for the specified time.

- **Throws:** `InterruptedException` if another thread interrupts the sleeping thread.

---

#### ✅ 2. Key Points & Rules

1. **Static Method:** `sleep()` always affects the **currently executing thread**, regardless of which thread object you call it on.

2. **Time Units:**
   
   - `millis` → milliseconds
   
   - `nanos` → additional nanoseconds (0–999999).

3. **Does not release locks:**
   
   - If a thread holds a monitor lock, it will **not release it** during `sleep()`.

4. **Can be interrupted:**
   
   - If another thread calls `interrupt()` on a sleeping thread, it wakes up and throws an `InterruptedException`.

5. **Does not guarantee exact timing:**
   
   - Actual sleep time depends on **OS scheduling**, may be longer than requested.

6. **Does not change thread state permanently:**
   
   - After sleep duration, thread becomes **runnable** again and competes for CPU time.

7. **Does not affect other threads:**
   
   - Only the **thread that calls sleep()** is paused; other threads continue running.

---

![m5](https://private-user-images.githubusercontent.com/87664048/328075002-4d521ecf-d99e-4c97-80c6-3677b61666d4.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM2Mjg1MjYsIm5iZiI6MTc1MzYyODIyNiwicGF0aCI6Ii84NzY2NDA0OC8zMjgwNzUwMDItNGQ1MjFlY2YtZDk5ZS00Yzk3LTgwYzYtMzY3N2I2MTY2NmQ0LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI3VDE0NTcwNlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTAwNjEwMmNjODM4ZjQzMWFkNzlmZGFmNjQzY2NmOTQwNjRiYTFiNzkyYWUyZjI1NjE4NDU4NmFiYWI3MzQ3NjYmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.5ECf3a_Je77sqLmsifd02ZzrQ7e3_bmLPhibKYWYVFI)

---

### Sleep Example

```java
class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + " - Count: " + i);
            try {
                Thread.sleep(1000); // pause for 1 second
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }
    }
}

public class SleepExample {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t2.start();
    }
}

```

---

#### ✅ Expected Output (interleaved, order not guaranteed)

```java
Thread-0 - Count: 1
Thread-1 - Count: 1
Thread-0 - Count: 2
Thread-1 - Count: 2
Thread-0 - Count: 3
Thread-1 - Count: 3
```

*(Order may vary because both threads run concurrently.)*

---

#### ✅  Key Clarifications

1. **Sleep ≠ Stop:** The thread only pauses temporarily; it doesn’t stop or terminate.

2. **Sleep ≠ Yield:**
   
   - `sleep()` → **pauses for a specific time**.
   
   - `yield()` → **suggests CPU to switch to other threads but may not pause**.

3. **Sleep does not release lock:**
   
   - If a synchronized thread calls `sleep()`, no other thread can enter that locked block until it wakes up.

4. **Sleep does not depend on priority:**
   
   - After waking up, the thread competes for CPU like other threads.

---

#### ✅ Usage Scenarios

- **Delaying execution** of a thread for a fixed duration.

- **Simulating time-consuming tasks** (e.g., file downloads, animations).

- **Pausing between retries** in polling mechanisms or scheduled tasks.

---

#### Summary:

- `sleep()` → **Pauses the current thread** for a specific duration.

- **Does not release locks**, can be **interrupted**, and **does not guarantee exact timing**.

- Useful for **timed delays**, not for synchronization.

---

## 4️⃣ `interrupt()` Method

#### ✅ What It Does:

- Sends a **signal to a thread** indicating that it should stop what it’s doing and handle the interruption.

- If the thread is **sleeping or waiting**, it throws an `InterruptedException` immediately.

- If it’s running normally, it just **sets an interrupt flag**, which must be checked manually.

- Calling `interrupt()` on a thread that never sleeps or checks its status has **no effect**.

---

#### 🔧 Signature:

```java
public void interrupt();
```

---

### ✅ Example 1 – Interrupting a sleeping thread:

```java
class MyThread extends Thread {
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am lazy Thread: " + i);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            System.out.println("I got interrupted");
        }
    }
}

public class ThreadInterruptDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
        t.interrupt();
        System.out.println("End of main thread");
    }
}
```

---

#### ✅ Two possible valid outputs:

##### Case A (Most common):

```java
I am lazy Thread: 0
End of main thread
I got interrupted
```

##### Case B (Alternate scheduling):

```java
I am lazy Thread: 0
I got interrupted
End of main thread
```

---

### **Execution Flow Explained:**

1. **`t.start()`** – Child thread starts and begins executing `run()`.  
   It prints: `I am lazy Thread: 0`.

2. **`Thread.sleep(2000)`** – Child thread goes to sleep for 2 seconds.

3. **`t.interrupt()`** – Main thread interrupts child while it’s sleeping.  
   → This **immediately causes `InterruptedException`**, waking the child thread.

4. **Context switching is unpredictable:**
   
   - Sometimes the **main thread** may print `"End of main thread"` before the child prints `"I got interrupted"`.
   
   - Sometimes `"I got interrupted"` appears before the main thread finishes.
   
   - This depends on CPU scheduling after the interrupt is triggered.

---

### ✅ Example 2 – Interrupting non-sleeping thread (delayed effect):

```java
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("I am lazy thread");
        }
        try {
            System.out.println("Entering sleep...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("I got interrupted");
        }
    }
}

public class ThreadInterruptDemo1 {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
        t.interrupt();
        System.out.println("End of main thread");
    }
}
```

#### Expected Output:

```java
I am lazy thread
I am lazy thread
I am lazy thread
I am lazy thread
I am lazy thread
End of main thread
Entering sleep...
I got interrupted
```

---

# Comparison Table: yield() vs join() vs sleep()

| Feature                            | `yield()`                                                               | `join()`                                                          | `sleep()`                                         |
| ---------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------- | ------------------------------------------------- |
| **Purpose**                        | Hint scheduler to let other threads of equal or higher priority execute | Make the **calling thread wait** until the target thread finishes | Pause the **current thread** for a specified time |
| **Static?**                        | ✅ Yes                                                                   | ❌ No                                                              | ✅ Yes                                             |
| **Final?**                         | ❌ No                                                                    | ✅ Yes                                                             | ❌ No                                              |
| **Overloaded?**                    | ❌ No                                                                    | ✅ Yes (`join()`, `join(long)`, `join(long,int)`)                  | ✅ Yes (`sleep(long)`, `sleep(long,int)`)          |
| **Throws `InterruptedException`?** | ❌ No                                                                    | ✅ Yes                                                             | ✅ Yes                                             |
| **Native Method?**                 | ✅ Yes                                                                   | ❌ No                                                              | ✅ Yes                                             |
| **Changes Thread State?**          | RUNNING → RUNNABLE (suggested)                                          | Calling thread: RUNNING → WAITING                                 | RUNNING → TIMED_WAITING                           |
| **Guarantee of effect?**           | ❌ No (scheduler may ignore)                                             | ✅ Yes (calling thread always waits)                               | ✅ Yes (thread sleeps for specified time)          |
| **Releases lock?**                 | ❌ No                                                                    | ❌ No                                                              | ❌ No                                              |

---

### ✅ Key Takeaways:

- **`yield()`** → Suggests scheduler to switch, **no guarantees**, rarely used.

- **`join()`** → Ensures one thread **waits** for another, useful for execution ordering.

- **`sleep()`** → **Delays thread execution** for a given time.

- **`interrupt()`** → Signals a thread to **stop sleeping/waiting or exit early** (cooperative).

- Use **structured concurrency tools** (Executors, Locks) in production instead of relying solely on these low-level controls.


