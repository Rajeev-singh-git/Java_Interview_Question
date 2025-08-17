🧵  Thread Communication & Concurrency

## Table of Contents

1. [String – Introduction & Core Concepts](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/01_String_Intro_Immutability.md)
2. [String Memory Model – Heap, SCP & Interning](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/02_String_Memory_Model.md)
3. [String Mutability, Performance & Builders – StringBuffer vs StringBuilder](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/03_String_Constructors_Builders.md)

---

# 🧵 Java Thread Communication & Concurrency

Java provides mechanisms like **`wait()`**, **`notify()`**, and **`notifyAll()`** for inter-thread communication, along with concurrency tools to handle synchronization, deadlocks, and thread management.

---

## 🧵 Inter-Thread Communication (`wait()`, `notify()`, `notifyAll()`)

These methods **`wait()`**, **`notify()`**, and **`notifyAll()`**  (defined in the **`Object`** class) 

allow threads to coordinate actions by waiting and notifying each other.

---

#### 📌 Why in `Object` class and not in `Thread`?

- These methods work on the **lock of an object**, not on a specific thread.

- Any Java object can be used as a monitor lock, so the methods must exist in `Object`.

---

#### ⚙ How They Work

1. **`wait()`**
   
   - Causes the **current thread** to **release the object’s lock** and enter the **waiting state** until notified.
   
   - Must be called **inside a synchronized block/method**.
   
   - Throws `InterruptedException`.

2. **`notify()`**
   
   - Wakes **one** waiting thread (chosen arbitrarily) waiting on the same object.
   
   - Does **not** immediately release the lock — lock is released **after synchronized block/method exits**.

3. **`notifyAll()`**
   
   - Wakes **all** threads waiting on the same object. They will compete for the lock.

---

#### 🛑 Important Rules

- Must call from **within a synchronized context** (else `IllegalMonitorStateException`).

- `wait()` immediately **releases the object's lock**.

- `notify()` / `notifyAll()` **release the lock only after** finishing the synchronized block.

- Only these methods cause **implicit lock release**.

---

#### 🔄 Lock Release Behavior

| Method        | Releases Lock?    |
| ------------- | ----------------- |
| `yield()`     | ❌ No              |
| `join()`      | ❌ No              |
| `sleep()`     | ❌ No              |
| `wait()`      | ✅ Yes (immediate) |
| `notify()`    | ✅ Yes (delayed)   |
| `notifyAll()` | ✅ Yes (delayed)   |

---

##### 🖥 Method Signatures

```java
public final void wait() throws InterruptedException
public final native void wait(long ms) throws InterruptedException
public final void wait(long ms, int ns) throws InterruptedException

public final native void notify()
public final native void notifyAll()
```

![m4](https://private-user-images.githubusercontent.com/87664048/329271268-2c86bf1a-ec30-47d3-a62f-72f47b5e5c30.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTQ3NDgwNjUsIm5iZiI6MTc1NDc0Nzc2NSwicGF0aCI6Ii84NzY2NDA0OC8zMjkyNzEyNjgtMmM4NmJmMWEtZWMzMC00N2QzLWE2MmYtNzJmNDdiNWU1YzMwLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA4MDklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwODA5VDEzNTYwNVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWE1NWY4NWNmNTFkZDk2OWJhZDdlZWNjNjFlNzQzMGFlYjQ2MjkyMmE1Njg3ZTQzMzdlNDVkOGMyYzdiZDZjNGQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.1MQ_sGFU93QZ3PawHpSWmVJ9gmEbs1o2lYSa8QVgQEg)

---

💻 **Example – Main & Child Thread Communication**

```java
public class InterThreadCommunication {

    public static void main(String [] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();

        synchronized (b) {
            System.out.println("Main Thread calling wait() method"); // Step-1
            b.wait();
            System.out.println("Main Thread got notification call"); // Step-4
            System.out.println(b.total);
        }
    }

}
class ThreadB extends Thread{
    int total = 0;

    public void run(){
        synchronized (this){
            System.out.println("Child thread starts calculation"); // Step-2
            for (int i = 0; i <= 100; i++) {
                total = total + i;
            }
            System.out.println("Child thread giving notification call"); // Step-3
            this.notify();
        }
    }
}
```

Output :→

```java
Main Thread calling wait() method
Child thread starts calculation
Child thread giving notification call
Main Thread got notification call
5050
```

---

## 💡**Producer–Consumer Problem**

- **Producer Thread** → Adds items to a shared queue.

- **Consumer Thread** → Removes items from the queue.

- If queue empty → consumer calls `wait()` (releases lock).

- When producer adds → calls `notify()` to wake consumer

```java
class Producer {

    Producer(Thread producerThread) {
        synchronized(q) {
            // Producer adds items to the queue and notifies the consumer thread
            q.notify();
        }
    }
}

class Consumer {

    Consumer(Thread consumerThread) {
        synchronized(q) {
            if (q.isEmpty()) {
                try {
                    // If the queue is empty, the consumer thread waits for notification
                    q.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // Consume item
            }
        }
    }
}
```

---

## **notify() vs notifyAll()**

- **`notify()`** → Wakes **one** thread waiting on the object’s monitor (choice depends on JVM).

- **`notifyAll()`** → Wakes **all** threads waiting on the object’s monitor; they will compete for the lock.

- **Rule:** Call `wait()`, `notify()`, `notifyAll()` **only** when holding the lock of the object on which they are invoked.  
  Example: If calling `S1.wait()`, you must hold **S1**’s lock, not S2’s.

---

# Concurrency Hazards

---

## Dead Lock

Deadlock occurs when **two or more threads are permanently blocked**, each waiting for the other to release a lock. This results in **infinite waiting**.

---

### Key Characteristics of Dead Lock

- No direct resolution at runtime.

- Caused by **circular waiting** for resources.

- Often linked with **`synchronized` blocks/methods**.

---

### Example – Classic Deadlock

```java
class A {
    public synchronized void foo(B b) {
        System.out.println("Thread1 starts foo()");
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        System.out.println("Thread1 trying to call b.last()");
        b.last();
    }
    public synchronized void last() {
        System.out.println("Inside A.last()");
    }
}

class B {
    public synchronized void bar(A a) {
        System.out.println("Thread2 starts bar()");
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        System.out.println("Thread2 trying to call a.last()");
        a.last();
    }
    public synchronized void last() {
        System.out.println("Inside B.last()");
    }
}

public class Deadlock extends Thread {
    A a = new A();
    B b = new B();

    public void m1() {
        this.start();
        a.foo(b); // Holds lock on 'a', waits for 'b'
    }
    public void run() {
        b.bar(a); // Holds lock on 'b', waits for 'a'
    }

    public static void main(String[] args) {
        Deadlock d = new Deadlock();
        d.m1();
    }
}
```

---

**How Deadlock Happens Here:**

1. **Thread 1** (main) → locks `a`, calls `foo()`, tries to access `b.last()`.

2. **Thread 2** (child) → locks `b`, calls `bar()`, tries to access `a.last()`.

3. Each thread waits for the other to release its lock → **deadlock**.

---

#### Deadlock Prevention Strategies

- **Lock ordering:** Always acquire locks in a fixed global order.

- **Try-lock with timeout:** Use `tryLock()` in `ReentrantLock`.

- **Avoid nested locks:** Reduce scenarios where one lock calls another synchronized method.

- **Lock splitting:** Use finer-grained locks.

---

## **Starvation**

- A thread waits indefinitely but **eventually** proceeds.

- Caused by thread scheduling favoring higher-priority threads.

| Aspect        | Deadlock         | Starvation                  |
| ------------- | ---------------- | --------------------------- |
| Definition    | Infinite waiting | Long waiting                |
| Cause         | Circular waiting | Scheduling priority         |
| End Condition | Never ends       | Ends when priorities change |

---

# **Race Condition**

- Multiple threads modify shared data **concurrently**, causing **data inconsistency**.

- **Solution:** Synchronization.

---

# Thread Lifecycle Types

---

## Daemon Threads

A **daemon thread** runs in the background to support **non-daemon threads** (e.g., the main thread, GC)).  
Its main role is **housekeeping tasks** like garbage collection, finalization, and signal dispatching.

---

### Daemon Threads Key Points

- **Definition:** A background thread that provides services to user (non-daemon) threads.

- **Examples:**
  
  - Garbage Collector (GC)
  
  - Signal Dispatcher

- **Priority:** Usually low, but can be set higher if needed.

- **Lifecycle Behavior:**
  
  - When **all non-daemon threads finish**, the JVM **terminates all daemon threads** immediately, regardless of their current execution state.

- **Daemon Status Check:**  
  `boolean isDaemon()` → returns `true` if thread is daemon.

- **Set Daemon:**  
  `void setDaemon(boolean b)` → must be called **before** `start()`.  
  After `start()`, calling it throws `IllegalThreadStateException`.

- However, the daemon nature can only be changed before starting the Thread. Attempting to change it after starting the Thread results in an IllegalThreadStateException.

- **Main Thread:** Always non-daemon, and its daemon status **cannot be changed**.

- **Inheritance Rule:** Child threads inherit daemon status from their parent.

---

### Example 1 – Checking Daemon Status

```java
class MyThread extends Thread {}

public class DaemonThreadDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().isDaemon()); // false (main thread)
        
        MyThread t = new MyThread();
        System.out.println(t.isDaemon()); // false (inherits from main)

        t.start();
        t.setDaemon(true); // ❌ Throws IllegalThreadStateException
    }
}
```

---

### Example 2 – Automatic Termination of Daemon Threads

```java
class MyThreadC extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Child Thread");
        }
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }
}

public class DaemonThreadDemo2 {
    public static void main(String[] args) {
        MyThreadC tc = new MyThreadC();
        tc.setDaemon(true);
        tc.start();
        System.out.println("End of main Thread");
    }
}
```

**Output behavior:**

- The child thread starts printing.

- As soon as `main` ends, JVM terminates the daemon thread—possibly before it finishes printing or sleeping.**Output behavior:**

---

### Interview Tip

- Think of daemon threads as **"helper threads"**.

- If **no user threads remain**, JVM doesn't wait for daemon threads to finish.**Output behavior:**

---

## Thread Models in Java

### 1. Green Thread Model

- Threads managed entirely by the JVM without OS support.

- **Support:** Only a few OS like **Sun Solaris**.

- **Status:** **Deprecated** – not recommended.

- **Reason:** Limited performance, poor utilization of multi-core CPUs.

### 2. Native OS Thread Model

-  Threads managed by JVM **with OS assistance**.

- **Support:** Windows, Linux, and most modern OS.

- **Preferred** over Green Thread Model.

---

# Forceful Thread Termination (Deprecated)

**Deprecated Methods:**

1. `stop()` – Terminates a thread immediately.
   
   - Unsafe: Can leave application in **inconsistent state**.

```java
public final void stop();
```

2. `suspend()` & `resume()` – Temporarily pauses/resumes a thread.
- Unsafe: Can cause **deadlocks**.

```java
public final void suspend();
public final void resume();
```

**Best Practice:**  
Design threads to **terminate gracefully** using flags or interrupt signals.

---

# ThreadGroup

**Purpose:**  
Group related threads to manage them together (e.g., stop, interrupt).

**Creation:**

```java
ThreadGroup g = new ThreadGroup("Printing Threads");
Thread t1 = new Thread(g, "Header Printing");
Thread t2 = new Thread(g, "Footer Printing");
Thread t3 = new Thread(g, "Body Printing");
```

**Stop All Threads in Group:**

```java
g.stop(); // Deprecated
```

---

## ThreadLocal (Java 1.2+)

**Definition:**  
Provides **thread-local variables** – each thread gets its own independent value.

**Use Cases:**

- Database connections per thread

- Thread-specific counters

- Thread-specific context (similar to servlet scopes: page, request, session, application)

**Example:**

```java
ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);

counter.set(counter.get() + 1); // Each thread updates its own copy
System.out.println(counter.get());

```

---

# Life Cycle of Thread

![m5](https://private-user-images.githubusercontent.com/87664048/329271309-5dc02580-479d-4b7a-ac41-514d675cd475.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTQ3NDgwNjUsIm5iZiI6MTc1NDc0Nzc2NSwicGF0aCI6Ii84NzY2NDA0OC8zMjkyNzEzMDktNWRjMDI1ODAtNDc5ZC00YjdhLWFjNDEtNTE0ZDY3NWNkNDc1LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA4MDklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwODA5VDEzNTYwNVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPThkYjBhNTA4ZGJmY2RhYTAyM2E1Zjc3MTc0NzkwZDFkMThhZGFjMmI5MmMwYjI4NDNiZDhlM2QwY2NlYjJiZjkmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.-BbzxIuo6ZGPcCW7dYfkVrbC8pAqbkk2ekf4ZFB_rtk)

[All Codes](https://github.com/Rajeev-singh-git/Java_Interview_Question/tree/main/MultiThreading/src)

---


