📘 Java String Deep Dive

## Table of Contents

1. [String – Introduction & Core Concepts](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/01_String_Intro_Immutability.md)
2. [String Memory Model – Heap, SCP & Interning](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/02_String_Memory_Model.md)
3. [String Mutability, Performance & Builders – StringBuffer vs StringBuilder](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/03_String_Constructors_Builders.md)

---

# 🧠 Multi-Tasking in Java

**Multitasking** is the ability of a computer system to execute multiple tasks **apparently simultaneously**. In Java and general computing, this can be achieved in two primary ways:

- **Process-based Multitasking**

- **Thread-based Multitasking**

---

### 🔧 Processes and Threads: The Workhorses of Multitasking

In the world of concurrent programming, two fundamental units of execution reign supreme:

- **Processes**

- **Threads**

While Java heavily emphasizes threads, understanding both concepts is essential.

---

## Processes

A **process** can be thought of as a **self-contained workspace** with its own private set of resources—**most notably, its own memory space**.

- Often represents a **program or application**.

- A single application may be composed of **multiple cooperating processes**.

- Communication between processes requires **Inter-Process Communication (IPC)** mechanisms like:
  
  - Pipes
  
  - Sockets
  
  - Shared files (less common)

> 💡 **In Java:** Most Java Virtual Machines (JVMs) run as **a single process**, but Java apps can launch additional OS-level processes using `ProcessBuilder`.

##### ✅ Ideal For:

- OS-level multitasking (e.g., running independent applications).

##### 🧪 Example:

**Playing music while programming**

- Media Player and IDE run as **separate processes**.

- Each has its **own memory, state, and system resources**.

- You can pause music, switch windows, or close the IDE without affecting the player.

---

## Threads

A **thread** is often called a **lightweight process**. While both processes and threads provide execution environments, threads are **more efficient** and **share memory** with other threads in the same process.

- Each process has **at least one thread** (the main thread).

- Threads **share the same memory and open files**, enabling faster and easier communication.

- Java provides rich built-in support for multithreading:
  
  - `Thread`, `Runnable`
  
  - `ThreadGroup`, `ThreadLocal`
  
  - `Executors`, `Future` (in advanced concurrency)

> 🛠 **Thread lifecycle and synchronization** (including race conditions and `synchronized` blocks) will be explored in later sections.

##### ✅ Ideal For:

- Program-level multitasking (e.g., multiple tasks within the same application).

##### 🧪 Example:

**Code writing and syntax highlighting in an editor**

- One thread handles **text input**.

- Another thread updates **syntax formatting or keyword casing** in real-time.

- Both run within the **same process** (the text editor), improving responsiveness.

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

### 🔁 Context Switching: The Balancing Act

To juggle multiple processes or threads, the **Operating System uses Context Switching**:

- Saves the current task's state (registers, stack pointer, etc.)

- Loads the next task's state

- Introduces **overhead**, but is essential for multitasking

---

### 🧩 Multitasking vs. Parallel Processing

Though they appear similar, they are conceptually different:

| Multitasking                                | Parallel Processing                      |
| ------------------------------------------- | ---------------------------------------- |
| **Simulates** simultaneous execution        | **Truly executes** simultaneously        |
| Uses **single core**, switches rapidly      | Uses **multiple cores** concurrently     |
| E.g., One hand doing multiple tasks quickly | E.g., Multiple hands doing tasks at once |

---

#### 🎯 The Goal: Enhanced Performance and Responsiveness

Whether through **process-based** or **thread-based** multitasking, the aim remains the same:

> ✅ **Reduce response time and improve user experience** by enabling smoother, more responsive interactions.

---

# 🧵 Creating and Starting Threads in Java

---

Java offers two primary ways to define and start a new thread:

1. By extending Thread class. [Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/src/MyThread.java)
2. By implementing Runnable interface. [Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/src/MyRunnable.java)

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

- When multiple threads are waiting to execute, the order in which they will execute first is determined by the "Thread Scheduler," which is part of the JVM.
- The specific algorithm or behavior followed by the Thread Scheduler cannot be predicted precisely, as it is dependent on the JVM vendor.
- Consequently, in multithreading examples, we cannot reliably expect an exact execution order or outcome.

**Factors Influencing Scheduling:** While the exact algorithm is hidden, some common factors likely considered by the scheduler include:

- **Thread Priority:** Threads have priorities (inherited from the thread group they belong to). Higher priority threads generally get better scheduling preference.
- **Waiting State:** Threads waiting for I/O operations (like disk access) might be temporarily suspended, allowing other threads to run.
- **Thread State:** Threads in the `Runnable` state are actively competing for CPU time. Threads in other states (like `Waiting` or `Sleeping`) aren't actively contenders.

---

### 📌 Case 2: `start()` vs `run()` — What's the Difference?

**t.start():**

- Creates a new thread object.
- The new thread starts executing the `run()` method of the object `t`.
- The original thread (the one that called `start()`) continues its execution independently and concurrently with the newly created thread.
- You can only call `start()` on a thread object once. Attempting to call it again throws an `IllegalThreadStateException`.

**t.run():**

- Simply executes the `run()` method of the object `t` on the **current thread**.
- No new thread is created.
- This behaves like a normal method call, and the current thread continues execution after `run()` finishes.
- You can call `run()` on a thread object multiple times.

**Key Points:**

- Use `t.start()` when you want to create a new thread for concurrent execution.
- Use `t.run()` only if you want to execute the `run()` method directly on the current thread, but typically this is not the intended use case for threads.

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

The **`start()`** method of the **`Thread`** class handles essential tasks required for thread execution, making it a vital component of multithreading in Java.

Here's what the **`start()`** method accomplishes:

1. **Registration with Thread Scheduler:** The **`start()`** method registers the thread with the thread scheduler, allowing it to be managed by the JVM's threading system.
2. **Initialization:** It handles all other mandatory low-level activities necessary for thread initialization.
3. **Invocation of `run()` method:** Finally, it invokes or calls the **`run()`** method of the thread, where the actual job or task of the thread is defined by the programmer.

This sequence of actions performed by the **`start()`** method ensures the proper initiation and execution of a new thread in Java. Without executing the **`start()`** method, a thread cannot begin its execution. Therefore, the **`start()`** method is often regarded as the heart of multithreading in Java.

```java
start() {
    1. Register thread with the Thread Scheduler
    2. Handle low-level system activities
    3. Trigger run() method (in a new thread)
}
```

Without `start()`, you're **not starting a new thread**, just executing a method normally. This is why `start()` is often called the **"heart of multithreading in Java."**

---

## 📌 Case 4: Behavior When `run()` Is Not Overridden

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
class MyThread extends Thread {    public void run() {        System.out.println("no arg method");    }    public void run(int i) {        System.out.println("int arg method");    }}

class ThreadDemo {    public static void main(String[] args) {        MyThread t = new MyThread();        t.start();    }}
```

Output:

```java
no arg method
```

- Output: `no-arg method`

- `start()` only calls the **default `run()` method with no arguments**

- To invoke `run(int i)`, you must call it manually like `t.run(5)`

> ⚠️ **Overloading is allowed**, but **not useful** unless you're calling it directly.

---

### 📌 Case 6: Overriding the `start()` Method (⚠️ Dangerous)

If we override the **`start()`** method in a subclass of **`Thread`**, our overridden **`start()`** method will be executed like a normal method call, and no new thread will be started.

```java
class MyThread extends Thread {    public void start() {        System.out.println("start method");    }    public void run() {        System.out.println("run method");    }}

class ThreadDemo {    public static void main(String[] args) {        MyThread t = new MyThread();        t.start();        System.out.println("main method");    }}
```

Output:

```java
start method
main method
```

- **No new thread is created.**

- The overridden `start()` acts like a **normal method**, so:
  
  - The **main thread executes it**
  
  - `run()` is **never invoked**

> ❌ It’s a bad practice to override `start()` unless you really know what you're doing.

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

### Life Cycle of the Thread

[](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/MultiThread_README.md#life-cycle-of-the-thread)

[![m1](https://private-user-images.githubusercontent.com/87664048/328011601-6fae331c-7bc8-410d-ab52-64af75dda128.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM1MzY3MTAsIm5iZiI6MTc1MzUzNjQxMCwicGF0aCI6Ii84NzY2NDA0OC8zMjgwMTE2MDEtNmZhZTMzMWMtN2JjOC00MTBkLWFiNTItNjRhZjc1ZGRhMTI4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI2VDEzMjY1MFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTI2NWIzMjI3ZGQyODAwMDNiZDk2Y2QwMjY1MjA5ZmIyYzg3Mzk1MDQwYzUwYmIzNTdkNmFlZWRkZTc3NDMxNTUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.2FcjjwD02vCAudhvlbvknIj7vV4orwFqZWyusJ0TjE4)](https://private-user-images.githubusercontent.com/87664048/328011601-6fae331c-7bc8-410d-ab52-64af75dda128.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM1MzY3MTAsIm5iZiI6MTc1MzUzNjQxMCwicGF0aCI6Ii84NzY2NDA0OC8zMjgwMTE2MDEtNmZhZTMzMWMtN2JjOC00MTBkLWFiNTItNjRhZjc1ZGRhMTI4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI2VDEzMjY1MFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTI2NWIzMjI3ZGQyODAwMDNiZDk2Y2QwMjY1MjA5ZmIyYzg3Mzk1MDQwYzUwYmIzNTdkNmFlZWRkZTc3NDMxNTUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.2FcjjwD02vCAudhvlbvknIj7vV4orwFqZWyusJ0TjE4)

1. **New State:** When a thread object is created, it enters the new state or born state.
2. **Runnable State:** Upon calling the **`start()`** method, the thread transitions to the ready or runnable state, indicating it's ready to execute but waiting for the CPU to be allocated by the thread scheduler.
3. **Running State:** If the thread scheduler assigns CPU resources to the thread, it enters the running state, where its **`run()`** method is executed.
4. **Dead State:** Once the **`run()`** method completes execution, the thread transitions to the dead state, indicating the end of its lifecycle.

![](https://private-user-images.githubusercontent.com/87664048/328011601-6fae331c-7bc8-410d-ab52-64af75dda128.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM2Mjg1MjYsIm5iZiI6MTc1MzYyODIyNiwicGF0aCI6Ii84NzY2NDA0OC8zMjgwMTE2MDEtNmZhZTMzMWMtN2JjOC00MTBkLWFiNTItNjRhZjc1ZGRhMTI4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI3VDE0NTcwNlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWFiMjI3MjE0ZDRmYzBjM2JiMWM4NDY3MmE1MGMyMTQ5NGRmZTc1Yjk1MWYyZWYyNTQ3NjE4YmRiNjE5NmFhNTImWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.w6R_9b6uEkjIef9O_-GjuWfQDSDyn8EmQf6EKnNrxAE)


