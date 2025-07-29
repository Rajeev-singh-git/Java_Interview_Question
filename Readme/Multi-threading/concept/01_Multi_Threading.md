📘 Java Multi-Tasking

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

## Life Cycle of the Thread

[](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/MultiThread_README.md#life-cycle-of-the-thread)

[![m1](https://private-user-images.githubusercontent.com/87664048/328011601-6fae331c-7bc8-410d-ab52-64af75dda128.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM1MzY3MTAsIm5iZiI6MTc1MzUzNjQxMCwicGF0aCI6Ii84NzY2NDA0OC8zMjgwMTE2MDEtNmZhZTMzMWMtN2JjOC00MTBkLWFiNTItNjRhZjc1ZGRhMTI4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI2VDEzMjY1MFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTI2NWIzMjI3ZGQyODAwMDNiZDk2Y2QwMjY1MjA5ZmIyYzg3Mzk1MDQwYzUwYmIzNTdkNmFlZWRkZTc3NDMxNTUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.2FcjjwD02vCAudhvlbvknIj7vV4orwFqZWyusJ0TjE4)](https://private-user-images.githubusercontent.com/87664048/328011601-6fae331c-7bc8-410d-ab52-64af75dda128.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTM1MzY3MTAsIm5iZiI6MTc1MzUzNjQxMCwicGF0aCI6Ii84NzY2NDA0OC8zMjgwMTE2MDEtNmZhZTMzMWMtN2JjOC00MTBkLWFiNTItNjRhZjc1ZGRhMTI4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA3MjYlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNzI2VDEzMjY1MFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTI2NWIzMjI3ZGQyODAwMDNiZDk2Y2QwMjY1MjA5ZmIyYzg3Mzk1MDQwYzUwYmIzNTdkNmFlZWRkZTc3NDMxNTUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.2FcjjwD02vCAudhvlbvknIj7vV4orwFqZWyusJ0TjE4)

1. **New State:** When a thread object is created, it enters the new state or born state.
2. **Runnable State:** Upon calling the **`start()`** method, the thread transitions to the ready or runnable state, indicating it's ready to execute but waiting for the CPU to be allocated by the thread scheduler.
3. **Running State:** If the thread scheduler assigns CPU resources to the thread, it enters the running state, where its **`run()`** method is executed.
4. **Dead State:** Once the **`run()`** method completes execution, the thread transitions to the dead state, indicating the end of its lifecycle.

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

The `Thread` class in Java provides multiple constructors to give developers flexibility when creating threads. These constructors allow you to:

- Specify a `Runnable` task

- Assign a name to the thread

- Place a thread in a specific thread group

- (Rarely) Set stack size manually

---

## ✅ Commonly Used Constructors

#### 1.**`Thread t = new Thread();`**

```java
Thread t = new Thread();
```

- Creates a new thread in the **default thread group** with an **auto-generated name**.

- The `run()` method is empty by default.

- You must override the `run()` method or use a subclass.

---

#### 2. **`Thread t = new Thread(Runnable r);`**

```java
 MyRunnable r = new MyRunnable();
 Thread t = new Thread(r);
 t.start();`
```

- Accepts a `Runnable` object.

- When `start()` is called, `r.run()` is executed in a **new thread**.

---

#### 3. `Thread(String name)`

```java
`Thread t = new Thread("WorkerThread");
```

- Creates a thread with the specified name.

- The `run()` method is still empty (not associated with any task).

---

#### 4. `Thread(Runnable r, String name)` ✅ **Recommended**

```java
MyRunnable r = new MyRunnable(); 
Thread t = new Thread(r, "WorkerThread");`
```

- Combines the ability to **assign a task** and **name the thread**.

- When `start()` is called, it executes `r.run()` in a new thread.

---

## ⚙️ Less Frequently Used Constructors

#### 5. `Thread(ThreadGroup g, String name)`

```java
`ThreadGroup group = new ThreadGroup("MyGroup");
 Thread t = new Thread(group, "NamedThread");
```

- Creates a new thread object with the specified `ThreadGroup` (`g`) and the specified name.

- Thread groups are a way to manage hierarchies of threads, but they are not widely used in modern Java due to potential complexity. Consider using thread pools instead for managing groups of threads

---

#### 6. `Thread(ThreadGroup g, Runnable r)`

```java
Thread t = new Thread(group, new MyRunnable());`
```

- Like constructor 2, but in a custom thread group.

---

#### 7. `Thread(ThreadGroup g, Runnable r, String name)`

```java
Thread t = new Thread(group, new MyRunnable(), "NamedWorker");
```

- Combines group, task, and name.

---

#### 8. `Thread(ThreadGroup g, Runnable r, String name, long stackSize)`

```java
`Thread t = new Thread(group, new MyRunnable(), "CustomStackThread", 1_000_000);
```

- Adds control over stack size.

- ⚠️ **Rarely used** — stack size is **platform-dependent** and generally managed well by the JVM.

---

### 📝 Summary of Constructors

| Constructor Signature                                            | Common? | Description                            |
| ---------------------------------------------------------------- | ------- | -------------------------------------- |
| `Thread()`                                                       | ✅       | Default thread, empty run method       |
| `Thread(Runnable r)`                                             | ✅       | Task-only constructor                  |
| `Thread(String name)`                                            | ☑️      | Named thread, no task                  |
| `Thread(Runnable r, String name)`                                | ✅✅      | Task + name (**Recommended**)          |
| `Thread(ThreadGroup g, String name)`                             | ❌       | Assigns to a group with a name         |
| `Thread(ThreadGroup g, Runnable r)`                              | ❌       | Task + group                           |
| `Thread(ThreadGroup g, Runnable r, String name)`                 | ❌       | Task + group + name                    |
| `Thread(ThreadGroup g, Runnable r, String name, long stackSize)` | ❌❌      | Adds manual stack control (least used) |

---

### 🔑 Key Takeaways

- Use **`Thread(Runnable r, String name)`** when you need a named thread with a task.

- Avoid directly using **ThreadGroups** unless you have a specific legacy or low-level need.

- **Don't manually set stack size** unless absolutely required — JVM manages it efficiently.

- Favor **thread pools** (`Executors`) over manual thread creation for scalable applications.

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


