# 📘Synchronization

## Table of Contents

1. [String – Introduction & Core Concepts](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/01_String_Intro_Immutability.md)
2. [String Memory Model – Heap, SCP & Interning](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/02_String_Memory_Model.md)
3. [String Mutability, Performance & Builders – StringBuffer vs StringBuilder](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/03_String_Constructors_Builders.md)

---

# 🔒 Synchronization

Synchronization ensures that **only one thread** can access a shared resource (method or block) at a time, preventing **data inconsistency (race conditions)** in multi-threaded environments.

---

### Applicability

- ✅ **Instance methods:** Lock is on the **current object (`this`)**, only one thread per instance can execute synchronized code.

- ✅ **Static methods:** Lock is on the **class object (`ClassName.class`)**, shared across all instances of the class.

- ✅ **Code blocks:** Lock can be placed on a **specific object or class object** explicitly.

- ❌ **Not applicable for classes or variables**

---

### How It Works

- Every object in Java has an **intrinsic lock (monitor)**.

- When a thread **tries to access a synchronized method/block**:
  
  - It must **first acquire the object's lock**.
  
  - If another thread already holds the lock, it is **blocked and waits**.
  
  - Only after the lock is released, it can enter and execute the synchronized code.

- **Non-synchronized methods** can still be accessed by multiple threads in parallel.

---

### 💡 Real-World Analogy (Google Pay)

- **Scenario:** Multiple devices using the same Google Pay account.

- **Read operation (Check balance):**
  
  - Many devices can check balance **simultaneously**, as it's read-only and doesn't risk data corruption.

- **Write operation (Withdraw money):**
  
  - If two devices attempt withdrawal **at the exact same time without synchronization:**
    
    - Both read balance = 100.
    
    - Both deduct 80 → final balance shows 20 ❌ (incorrect).
  
  - **With synchronization:**
    
    - Even if both devices attempt withdrawal **simultaneously**, only **one device (say Device A)** can acquire the lock first and perform the withdrawal.
    
    - **Device B is blocked** until Device A finishes and releases the lock.
    
    - When Device B gets the lock, it executes withdrawal on updated balance (20) → withdrawal rejected ✅.
    
    - **Synchronization makes it impossible for two devices/threads to execute the withdrawal method concurrently.**

---

## Example

### ❌ Without Synchronization

```java
class BankAccount {
    private int balance = 100;

    public void withdraw(String name, int amount) {
        if (balance >= amount) {
            System.out.println(name + " withdrawing " + amount);
            try { Thread.sleep(100); } catch (InterruptedException e) {} // simulate delay
            balance -= amount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance for " + name);
        }
    }
}

public class TestWithoutSync {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Runnable task1 = new Runnable() {
            public void run() { account.withdraw(Thread.currentThread().getName(), 80); }
        };
        Runnable task2 = new Runnable() {
            public void run() { account.withdraw(Thread.currentThread().getName(), 80); }
        };

        Thread t1 = new Thread(task1, "Person A");
        Thread t2 = new Thread(task2, "Person B");
        t1.start(); t2.start();
    }
}
```

##### Possible Output:

```java
Person A withdrawing 80
Person B withdrawing 80
Remaining balance: 20
Remaining balance: 20   // ❌ Incorrect: balance went below zero logically

```

Two threads **read balance=100 before either deducts** → final value wrong.

---

### ✅ With Synchronization

```java
class BankAccountSync {
    private int balance = 100;

    public synchronized void withdraw(String name, int amount) {
        if (balance >= amount) {
            System.out.println(name + " withdrawing " + amount);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            balance -= amount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance for " + name);
        }
    }
}

public class TestWithSync {
    public static void main(String[] args) {
        BankAccountSync account = new BankAccountSync();

        Runnable task = () -> account.withdraw(Thread.currentThread().getName(), 80);

        Thread t1 = new Thread(task, "Person A");
        Thread t2 = new Thread(task, "Person B");
        t1.start(); t2.start();
    }
}

```

##### Correct Output:

```java
Person A withdrawing 80
Remaining balance: 20
Insufficient balance for Person B

```

Here, only **one thread at a time** can run `withdraw()` because the **lock prevents overlapping execution**, fixing the inconsistency.

---

##### Key Takeaways

- Synchronization = **One thread at a time per object lock**.

- Prevents **race conditions** when multiple threads access shared data.

- Use **only where necessary**, as it can reduce concurrency and performance.

---

## ⚙️ Concurrency Control: Synchronization Behavior Explained

In Java multithreading, synchronization works on a **per-object lock basis**. Consider a class with three method:

- **m1** → synchronized

- **m2** → synchronized

- **m3** → non-synchronized

---

##### Two threads accessing the **same synchronized method** on the same object

- If thread `t1` is executing  `m1` on object `x`, and thread `t2` tries to execute `m1` on the **same object**:
  
  - thread `t2` must **wait**, because the **lock on `x` is held by thread `t1`**.
  
  - Once `t1` releases the lock, `t2` can proceed.

---

##### Two threads accessing **different synchronized methods** on the same object

- If one thread is in `m1` and another calls `m2` on the same object:
  
  - Only **one thread** can execute any synchronized method at a time on that object.
  
  - This is because the **lock is on the object**, not on individual methods.

---

##### One thread accessing a **non-synchronized method** while another runs a synchronized one

- If thread `t3` calls `m3` on object `x` while thread `t1` is executing a synchronized method:
  
  - `t3` **does not attempt to acquire the object's lock**, because `m3` is not synchronized.
  
  - It can **execute immediately**, even while a lock is held for `m1` or `m2`.
  
  - Multiple threads can freely execute non-synchronized methods at the same time.

---

#### Important behavior: **Lock acquisition happens only for synchronized code**

- Calling a method on an object **does not automatically acquire the lock**.

- The JVM **only acquires the object's lock** when:
  
  - The method (or code block) is declared as **synchronized**.

- For non-synchronized methods:
  
  - **No lock is requested or checked**, so threads can run concurrently.
  
  - If they **modify shared data**, this can cause **race conditions**.

---

##### ✅ Key Rules

- **One lock per object:** Only one thread can execute any synchronized method on the same object at a time.

- **Non-synchronized methods:** Do not require or acquire a lock → multiple threads can execute them concurrently.

- **Race condition risk:** Modifying shared data in non-synchronized methods can cause inconsistent results.

---

The decision to acquire an object's lock is based on the method being invoked. If the method is declared `synchronized`, the thread must first acquire the object's lock before execution. If the method is not synchronized, no lock is requested, and the thread executes the method immediately even if lock is with another thread.

---

## 🔒 Object Lock Behavior: Same vs Different Objects

In Java, synchronization works **per object instance**, meaning **each object has its own lock**.  
The following example demonstrates how thread behavior changes depending on whether they share the same object or use separate objects.

---

```java
class Display {
    public synchronized void wish(String name) {
        for (int i = 0; i < 3; i++) {
            System.out.print("Good morning : ");
            try {
                Thread.sleep(1000); // simulate delay
            } catch (InterruptedException ignored) {}
            System.out.println(name);
        }
    }
}

class MyThread7 extends Thread {
    Display d;
    String name;

    MyThread7(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        d.wish(name);
    }
}
```

---

### 1️⃣ Case 1 – **Shared Object**

```java
public class SynchronizedDemo {
    public static void main(String[] args) {
        Display d1 = new Display();
        MyThread7 t1 = new MyThread7(d1, "Rajeev");
        MyThread7 t2 = new MyThread7(d1, "Rajeev Singh");
        t1.start();
        t2.start();
    }
}
```

#### ✅ Behavior:

- Both threads call the **same synchronized method** on the **same object** (`d1`).

- They compete for the **same object lock** → only one thread executes at a time.

- Output is **sequential and clean**:

```java
Good morning : Rajeev
Good morning : Rajeev
Good morning : Rajeev
Good morning : Rajeev Singh
Good morning : Rajeev Singh
Good morning : Rajeev Singh
```

---

### 2️⃣ Case 2 – **Different Objects**

```java
public class SynchronizedDemo {
    public static void main(String[] args) {
        Display d1 = new Display();
        Display d2 = new Display();
        MyThread7 t1 = new MyThread7(d1, "Dhoni");
        MyThread7 t2 = new MyThread7(d2, "Yuvaraj");
        t1.start();
        t2.start();
    }
}
```

#### ✅ Behavior:

- Each thread uses a **different `Display` object** (`d1`, `d2`).

- Each object has its **own lock**, so both threads execute simultaneously.

- Output may interleave:

```java
Good morning : Dhoni
Good morning : Yuvaraj
Good morning : Dhoni
Good morning : Yuvaraj
Good morning : Dhoni
Good morning : Yuvaraj
```

---

#### 📌 Key Takeaways

- **One lock per object:** Synchronization applies to the object instance, not the method.

- **Shared object:** Threads must wait for the same lock → execution becomes sequential.

- **Separate objects:** Threads acquire different locks → methods can run in parallel.

---


