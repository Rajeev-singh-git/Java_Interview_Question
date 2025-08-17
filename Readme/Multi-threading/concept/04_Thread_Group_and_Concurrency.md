📘🧵 # ThreadGroup and Concurrency Utilities

## Table of Contents

1. [📘 Introduction to Java Multithreading](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Multi-threading/concept/01_Multi_Threading.md)
2. [📘Synchronization](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Multi-threading/concept/02_Synchronization.md)
3. [Question](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Multi-threading/concept/03_Questions_1.md)
4. [Thread_Communication_and_Concurrency](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Multi-threading/concept/04_Thread_Communication_and_Concurrency.md)

---

# 🧵 ThreadGroup in Java

### 🔹 Overview

- `ThreadGroup` represents a **set of threads** and allows performing **common operations** (interrupt, stop, set priority, destroy, list, etc.) on all threads in the group.

- Forms a **hierarchy**: groups can contain **threads + subgroups** → easier organization.

- Located in **`java.lang`** package, direct child of `Object`.

- Every thread belongs to some group.
  
  - `main` thread → belongs to **main group**.
  
  - Every group (directly/indirectly) is a child of **system group** (root).

- **System group** contains system-level threads like `Finalizer`, `ReferenceHandler`, `SignalDispatcher`, `AttachListener`, etc.

![m1](https://private-user-images.githubusercontent.com/87664048/329486920-b3c3d120-f634-4047-bad0-27c349392101.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTU0NDUzODQsIm5iZiI6MTc1NTQ0NTA4NCwicGF0aCI6Ii84NzY2NDA0OC8zMjk0ODY5MjAtYjNjM2QxMjAtZjYzNC00MDQ3LWJhZDAtMjdjMzQ5MzkyMTAxLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA4MTclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwODE3VDE1MzgwNFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWU1MjZkOWRlMzlkMGEyNjIxMGQ4MmE5MDJjYjM0MjQxMzg3NWRiZmUyMjcxZWYyYzk3YjdjMzVkZWM5MGE0ZDUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.pKxkLSWYSUwu4sajjl-jFW6dXeetDieb87ZtivEuTTo)

---

## Constructors

```java
ThreadGroup g = new ThreadGroup(String gname);
```

- Creates a new `ThreadGroup` with given name.

- Parent = group of currently running thread (or `System` group if none).

```java
ThreadGroup g = new ThreadGroup(ThreadGroup parent, String gname);
```

- Creates new group with specified **parent group**.

✅ Example:

```java
ThreadGroup parent = new ThreadGroup("ParentGroup");
ThreadGroup child  = new ThreadGroup(parent, "ChildGroup");
```

---

### Example 1: Hierarchy Demo

```java
public class ThreadGroupDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getThreadGroup().getName()); // main
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName()); // system

        ThreadGroup gp = new ThreadGroup("Parent Group");
        System.out.println(gp.getParent().getName()); // main

        ThreadGroup cg = new ThreadGroup(gp, "Child Group");
        System.out.println(cg.getParent().getName()); // Parent Group
    }
}
```

###### Output

```java
main
system
main
Parent Group
```

![m2](https://private-user-images.githubusercontent.com/87664048/329486952-f4921003-e34e-4b14-8789-dc4b5cd009fa.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTU0NDUzODQsIm5iZiI6MTc1NTQ0NTA4NCwicGF0aCI6Ii84NzY2NDA0OC8zMjk0ODY5NTItZjQ5MjEwMDMtZTM0ZS00YjE0LTg3ODktZGM0YjVjZDAwOWZhLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA4MTclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwODE3VDE1MzgwNFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTZjNzY5ZTZkOGQxNjViNzkyMGFmOTFjZWE5NjNjYzJhMTUyM2VkNDM2YWFjNzU0M2ViNzE3OTI0ZDU5NGNmOTEmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.BGIuVbYJtVEvziNsgJpgx18uCck6ZxvYTGBHa6iyPl4)

---

## Common Methods of ThreadGroup

| Method                     | Description                                |
| -------------------------- | ------------------------------------------ |
| `getName()`                | Returns group name                         |
| `getParent()`              | Returns parent group                       |
| `getMaxPriority()`         | Returns group’s max thread priority        |
| `setMaxPriority(int p)`    | Sets max priority (new threads only)       |
| `list()`                   | Prints group details (threads + subgroups) |
| `activeCount()`            | No. of active threads                      |
| `activeGroupCount()`       | No. of active subgroups                    |
| `enumerate(Thread[])`      | Copies active threads into array           |
| `enumerate(ThreadGroup[])` | Copies active subgroups                    |
| `isDaemon()`               | Checks if group is daemon                  |
| `setDaemon(boolean)`       | Marks group as daemon                      |
| `interrupt()`              | Interrupts all threads in group            |
| `destroy()`                | Destroys group + subgroups                 |

---

### Example 2: Priority Behavior

```java
public class ThreadGroupDemo2 {
    public static void main(String[] args) {
        ThreadGroup g1 = new ThreadGroup("tg");
        Thread t1 = new Thread(g1, "Thread 1");
        Thread t2 = new Thread(g1, "Thread 2");

        g1.setMaxPriority(3); // affects future threads
        Thread t3 = new Thread(g1, "Thread 3");

        System.out.println(t1.getPriority()); // 5
        System.out.println(t2.getPriority()); // 5
        System.out.println(t3.getPriority()); // 3
    }
}
```

---

### Example 3: Active Count & List

```java
class MyThread extends Thread {
    MyThread(ThreadGroup g, String name) { super(g, name); }
    public void run() {
        System.out.println("Child Thread: " + getName());
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }
}

public class ThreadGroupDemo3 {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup pg = new ThreadGroup("Parent Group");
        ThreadGroup cg = new ThreadGroup(pg, "Child Group");

        MyThread t1 = new MyThread(pg, "Child Thread 1");
        MyThread t2 = new MyThread(pg, "Child Thread 2");
        t1.start(); t2.start();

        System.out.println(pg.activeCount());       // 2
        System.out.println(pg.activeGroupCount());  // 1
        pg.list();

        Thread.sleep(5000);
        System.out.println(pg.activeCount());       // 0
        pg.list();
    }
}
```

###### Output (abridged)

```java
Child Thread: Child Thread 1
Child Thread: Child Thread 2
2
1
java.lang.ThreadGroup[name=Parent Group,maxpri=10]
    Thread[Child Thread 1,5,Parent Group]
    Thread[Child Thread 2,5,Parent Group]
    java.lang.ThreadGroup[name=Child Group,maxpri=10]
0
java.lang.ThreadGroup[name=Parent Group,maxpri=10]
    java.lang.ThreadGroup[name=Child Group,maxpri=10]
```

![m3](https://private-user-images.githubusercontent.com/87664048/329487033-4375dfb4-09a6-49db-8df1-2bd333746797.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTU0NDUzODQsIm5iZiI6MTc1NTQ0NTA4NCwicGF0aCI6Ii84NzY2NDA0OC8zMjk0ODcwMzMtNDM3NWRmYjQtMDlhNi00OWRiLThkZjEtMmJkMzMzNzQ2Nzk3LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA4MTclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwODE3VDE1MzgwNFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTJmNTUwNDAwNTk0ZTAyZDY4MjM1YTMyNGM4YTA3ODg0MzU3MDZjYTk1YjgwMzc2OTgwNDZhOTc0ZjRiNWMxZjAmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.gfArgiApBHXOuPvhxmE1Ggubr0Vye5zD_O29Z490nNU)

---

### Example 4: Display All System Threads

```java
public class ThreadGroupDemo4 {
    public static void main(String[] args) {
        ThreadGroup system = Thread.currentThread().getThreadGroup().getParent();
        Thread[] threads = new Thread[system.activeCount()];
        system.enumerate(threads);

        for (Thread t : threads) {
            System.out.println(t.getName() + " ---- " + t.isDaemon());
        }
    }
}
```

###### Sample Output

```java
main ---- false
Reference Handler ---- true
Finalizer ---- true
Signal Dispatcher ---- true
Attach Listener ---- true
Common-Cleaner ---- true
```

---

✅ **Summary**

- `ThreadGroup` organizes threads hierarchically.

- Useful for **batch operations** (interrupt all, destroy, etc.).

- Each thread belongs to some group → ultimately child of `system`.

- Methods like `activeCount()`, `list()`, `setMaxPriority()` are key in managing groups.

---

# `java.util.concurrent.locks` Package

## 🔴 Problems with `synchronized` keyword

1. **Lack of Flexibility** – Cannot attempt a lock without blocking. No way to check availability before waiting.

2. **Indefinite Waiting** – No timeout support. Threads may wait forever → risk of **deadlock**.

3. **Uncontrolled Lock Release** – No control over which waiting thread acquires the lock next → **unpredictable debugging**.

4. **Limited Scope** – Works only at **method/block level**, lacks fine-grained control.

5. **No Thread Info** – Cannot query which or how many threads are waiting for a lock.

---

## 🔑 Lock Interface

- Provides explicit locking (like synchronized blocks) but with **more control**.

- Important methods:
  
  - `void lock()` → Blocks until lock acquired.
  
  - `boolean tryLock()` → Acquires if available, else returns `false` (non-blocking).
  
  - `boolean tryLock(long time, TimeUnit unit)` → Waits up to given time.
  
  - `void lockInterruptibly()` → Waits but responds to interrupts.
  
  - `void unlock()` → Releases the lock (must be owner).

---

## 🌀 ReentrantLock

- **Implements `Lock`**.

- **Reentrant**: A thread can acquire the same lock multiple times (maintains **hold count**).

- Can be **fair** or **non-fair**:
  
  - **Fair (`true`)** → Threads acquire lock in FIFO order.
  
  - **Non-fair (`false`, default)** → No ordering guarantee, better performance.

### Constructors

```java
ReentrantLock rl = new ReentrantLock();       // non-fair (default)
ReentrantLock rl = new ReentrantLock(true);   // fair lock
```

### Common Methods

- `void lock()` → acquires lock.

- `boolean tryLock()` / `tryLock(timeout, unit)` → attempt lock without blocking.

- `void lockInterruptibly()` → acquires unless interrupted.

- `void unlock()` → releases lock.

- `int getHoldCount()` → times lock held by current thread.

- `boolean isHeldByCurrentThread()` → true if current thread owns lock.

- `boolean isLocked()` → true if held by any thread.

- `boolean isFair()` → fairness policy.

- `int getQueueLength()` / `Collection<Thread> getQueuedThreads()` → info about waiting threads.

- `Thread getOwner()` → thread currently holding the lock.

---

### Example 1 – Simple ReentrantLock Usage

```java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock lock = new ReentrantLock();

    public void performAction() {
        lock.lock();
        try {
            System.out.println("Lock held by: " + Thread.currentThread().getName());
        } finally {
            lock.unlock(); // always release lock
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();

        Runnable task = () -> demo.performAction();

        new Thread(task, "Thread-1").start();
        new Thread(task, "Thread-2").start();
    }
}
```

✅ Ensures only one thread executes critical section at a time.

---

### Example 2 – Fair ReentrantLock

```java
import java.util.concurrent.locks.ReentrantLock;

class Display {
    // Fair lock (FIFO order)
    private final ReentrantLock lock = new ReentrantLock(true);

    public void wish(String name) {
        lock.lock();
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("Good Morning: " + name);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MyThread extends Thread {
    Display d; String name;
    MyThread(Display d, String name) { this.d = d; this.name = name; }
    public void run() { d.wish(name); }
}

public class ReentrantLockDemo3 {
    public static void main(String[] args) {
        Display d = new Display();
        new MyThread(d, "Dhoni").start();
        new MyThread(d, "Yuvraj").start();
        new MyThread(d, "Virat").start();
    }
}
```

✅ Ensures **fairness** – threads acquire lock in the order they requested it.

---

### 📌 Key Takeaways

- Use `ReentrantLock` when you need **more control** than `synchronized` offers.

- Prefer **fair locks** when starvation prevention is critical.

- Always release locks in a **finally block** to avoid deadlock.

- Avoid overuse: `synchronized` is simpler and sufficient in many cases.

---

# 🧵 Thread Pools, Callable & Future

### 🔹 Why Thread Pools?

Creating a new thread for each task is expensive (performance + memory overhead).  
To solve this, Java introduced **Thread Pool Framework** (a.k.a. **Executor Framework**) in **Java 1.5**.

- **Thread Pool** → A collection of pre-created threads that can be reused for multiple tasks.

- **ExecutorService** → Interface to manage thread pools.

- **Executors** → Utility class to create thread pools.

---

### ✅ Creating a Thread Pool

```java
ExecutorService service = Executors.newFixedThreadPool(3); // Pool of 3 threads
```

---

### ✅ Submitting a Job to the Thread Pool

```java
Runnable job = () -> {
    System.out.println("Task executed by: " + Thread.currentThread().getName());
};
service.submit(job);
```

---

### ✅ Shutting Down ExecutorService

```java
service.shutdown(); // Graceful shutdown (waits for tasks to finish)
```

---

### 📌 Example: Runnable with Thread Pool

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PrintJob implements Runnable {
    String name;

    PrintJob(String name) { this.name = name; }

    public void run() {
        System.out.println(name + " started by " + Thread.currentThread().getName());
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        System.out.println(name + " completed by " + Thread.currentThread().getName());
    }
}

public class ExecutorDemo {
    public static void main(String[] args) {
        PrintJob[] jobs = {
            new PrintJob("A"), new PrintJob("B"),
            new PrintJob("C"), new PrintJob("D"),
            new PrintJob("E")
        };

        ExecutorService service = Executors.newFixedThreadPool(3);
        for (PrintJob job : jobs) {
            service.submit(job);
        }
        service.shutdown();
    }
}

```

---

## 🧵 Callable and Future

---

### Runnable vs Callable

| Feature      | Runnable                  | Callable                     |
| ------------ | ------------------------- | ---------------------------- |
| Return Value | No                        | Yes (`T call()`)             |
| Exceptions   | Cannot throw checked ones | Can throw checked exceptions |
| Method       | `public void run()`       | `public T call() throws Ex`  |

---

### ✅ Future Interface

Represents result of async computation.

- `isDone()` → check if completed.

- `cancel()` → cancel the task.

- `isCancelled()` → check cancellation.

- `get()` → blocks until result is ready.

---

### 📌 Example: Callable + Future

```java
import java.util.concurrent.*;

class MyCallable implements Callable<Integer> {
    int num;
    MyCallable(int num) { this.num = num; }

    public Integer call() {
        int sum = 0;
        for (int i = 1; i <= num; i++) sum += i;
        return sum;
    }
}

public class CallableFutureDemo {
    public static void main(String[] args) throws Exception {
        MyCallable[] jobs = {
            new MyCallable(10), new MyCallable(20),
            new MyCallable(30), new MyCallable(40),
            new MyCallable(50), new MyCallable(60)
        };

        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Integer>[] results = new Future[jobs.length];

        for (int i = 0; i < jobs.length; i++) {
            results[i] = service.submit(jobs[i]);
        }

        for (int i = 0; i < jobs.length; i++) {
            System.out.println("Sum of first " + jobs[i].num +
                               " numbers: " + results[i].get());
        }

        service.shutdown();
    }
}
```

---

## 📖 Explanation

- **Callable Implementation** → `MyCallable implements Callable<Integer>`, returning sum of numbers.

- **ExecutorService** → 3-thread pool (`Executors.newFixedThreadPool(3)`).

- **Submitting Tasks** → `submit()` returns a `Future<Integer>` for each task.

- **Retrieving Results** → `get()` blocks until result is available.

- **Graceful Shutdown** → `shutdown()` waits for all tasks to finish.

👉 This allows **async execution** with **results + exception handling**.


