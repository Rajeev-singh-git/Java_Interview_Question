# Multi Tasking

Multitasking is the ability of a computer system to execute several tasks apparently simultaneously. There are two primary ways to achieve multitasking:

1. Process-based multitasking.
2. Thread-based multitasking.

**Processes and Threads: The Workhorses of Multitasking**

In the world of concurrent programming, two fundamental units of execution reign supreme: processes and threads. While Java focuses heavily on threads, understanding processes is also valuable.

## Processes

- **Processes:** Imagine a process as a self-contained workspace with its own private set of resources, most notably its own memory space. Processes are often synonymous with programs or applications. However, a single application might actually be a bunch of cooperating processes working together. Operating systems typically provide Inter-Process Communication (IPC) mechanisms like pipes and sockets to facilitate communication between processes, even on different systems. Notably, most Java virtual machines run as a single process, but a Java application can create additional processes using `ProcessBuilder`. Process-based multitasking is ideal for the operating system level, as it allows truly independent programs to run concurrently.

**Example: Playing Music and Programming Simultaneously**

A classic example of process-based multitasking is playing music in a media player while writing code in an IDE (Integrated Development Environment). These are separate applications, each acting as an independent process with its own resources. You can pause the music, minimize the player, and continue coding without affecting the music player process.

## Threads

- **Threads:** Threads are often referred to as lightweight processes. While both provide an execution environment, creating a thread consumes fewer resources compared to a process. Threads reside within a process, meaning every process has at least one thread. Unlike processes, threads share the process's resources like memory and open files. This enables efficient communication but can also introduce potential complications. Java heavily leverages multithreading. Every Java application starts with at least one thread (the main thread) and can create additional ones using its rich threading API (Thread, Runnable, ThreadGroup, ThreadLocal, etc.). Thread-based multitasking excels at the programmatic level, allowing different parts of the same program to run concurrently. Compared to C++, Java offers much simpler multithreading development due to its built-in support.

**Example: Writing Code and Changing Case Simultaneously**

A common thread-based multitasking example can be found in text editors. While you're writing code in one thread (the main thread), another thread within the same editor process might be responsible for features like automatically changing the case of keywords as you type (e.g., automatically capitalizing keywords like "if", "else", or "while"). Both functionalities (writing and case-changing) happen within the same application (single process) but use separate threads to achieve a more responsive and efficient user experience.

**Context Switching: The Balancing Act**

To effectively juggle multiple processes or threads, the operating system employs a technique called context switching. When switching between tasks, the OS saves the state of the current task (registers, memory pointers, etc.) and restores the state of the task it's switching to. While context switching adds some overhead, it's essential for multitasking to function.

**Multitasking vs. Parallel Processing: Not Quite the Same**

It's important to distinguish between multitasking and parallel processing. While multitasking creates the illusion of simultaneous execution by rapidly switching between tasks, parallel processing involves true simultaneous execution on multiple cores. Imagine having multiple hands to truly work on things at the same time, as opposed to rapidly switching between a single pair of hands.

**The Goal: Enhanced Performance and Responsiveness**

Regardless of the method (process-based or thread-based), the core objective of multitasking remains the same: to improve system performance by reducing response time. By allowing seemingly concurrent execution of multiple tasks, users experience a smoother and more responsive computing experience.

# The ways to define instantiate and start a new Thread

1. By extending Thread class.
   [Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/src/MyThread.java)
2. By implementing Runnable interface.
    [Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/src/MyRunnable.java)

## Defining a Thread by extending thread class.

```java

public class MyThread extends  Thread
{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("Child Thread");
        }
    }

}

class ThreadDemo{
    public static void main(String[] args)
    {
        MyThread t = new MyThread();
        t.start(); // a new thread will be created which is responsible for the execution of run() method.

        for(int i=0;i<5;i++){
            System.out.println("Main Thread");
        }
    }
}

```

### Case 1: Thread Scheduler

- When multiple threads are waiting to execute, the order in which they will execute first is determined by the "Thread Scheduler," which is part of the JVM.
- The specific algorithm or behavior followed by the Thread Scheduler cannot be predicted precisely, as it is dependent on the JVM vendor.
- Consequently, in multithreading examples, we cannot reliably expect an exact execution order or outcome.

**Factors Influencing Scheduling:** While the exact algorithm is hidden, some common factors likely considered by the scheduler include:

- **Thread Priority:** Threads have priorities (inherited from the thread group they belong to). Higher priority threads generally get better scheduling preference.
- **Waiting State:** Threads waiting for I/O operations (like disk access) might be temporarily suspended, allowing other threads to run.
- **Thread State:** Threads in the `Runnable` state are actively competing for CPU time. Threads in other states (like `Waiting` or `Sleeping`) aren't actively contenders.

### Case 2: Difference between t.start() and t.run() methods.

**t.start():**

- Creates a new thread object.
- The new thread starts executing the `run()` method of the object `t`.
- The original thread (the one that called `start()`) continues its execution independently and concurrently with the newly created thread.
- You can only call `start()` on a thread object once. Attempting to call it again throws an `IllegalThreadStateException`.

**t.run():**

- Simply executes the `run()` method of the object `t` on the **current thread**.
- No new thread is created.
- This behaves like a normal method call, and the current thread continues execution after `run()` finishes.
- You can call `run()` on a thread object multiple times.

**Key Points:**

- Use `t.start()` when you want to create a new thread for concurrent execution.
- Use `t.run()` only if you want to execute the `run()` method directly on the current thread, but typically this is not the intended use case for threads.

### Case 3: importance of Thread class start() method.

The **`start()`** method of the **`Thread`** class handles essential tasks required for thread execution, making it a vital component of multithreading in Java.

Here's what the **`start()`** method accomplishes:

1. **Registration with Thread Scheduler:** The **`start()`** method registers the thread with the thread scheduler, allowing it to be managed by the JVM's threading system.
2. **Initialization:** It handles all other mandatory low-level activities necessary for thread initialization.
3. **Invocation of `run()` method:** Finally, it invokes or calls the **`run()`** method of the thread, where the actual job or task of the thread is defined by the programmer.

This sequence of actions performed by the **`start()`** method ensures the proper initiation and execution of a new thread in Java. Without executing the **`start()`** method, a thread cannot begin its execution. Therefore, the **`start()`** method is often regarded as the heart of multithreading in Java.

```java
start() {
    1. Register Thread with Thread Scheduler
    2. All other mandatory low-level activities
    3. Invoke or call run() method
}
```

### **Case 4: Behavior When `run()` Method is Not Overridden**

If the **`run()`** method is not overridden in a subclass of **`Thread`**, the default **`run()`** method provided by the **`Thread`** class will be executed. This default implementation of **`run()`** has no functionality, resulting in no observable output.

Here's an example:

```java
class MyThread extends Thread {}

class ThreadDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
```

In this scenario, since the **`run()`** method is not overridden in the **`MyThread`** class, the default **`run()`** method of the **`Thread`** class will be executed. As this default implementation does nothing, no output will be produced.

It's important to note that while it's technically possible to create threads without overriding the **`run()`** method, it is highly recommended to override **`run()`** with the desired functionality. Without a custom **`run()`** method, there is no real benefit to utilizing multithreading, as the threads would not perform any meaningful tasks.

### Case 5: Overloading the **`run()`** method.

In Java, it's possible to overload the **`run()`** method in a subclass of **`Thread`**. However, the **`start()`** method of the **`Thread`** class always invokes the no-argument **`run()`** method. Other overloaded versions of **`run()`** must be explicitly called like normal methods to execute.

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("no arg method");
    }

    public void run(int i) {
        System.out.println("int arg method");
    }
}

class ThreadDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
```

Output:

```java
no arg method
```

In this example, even though there's an overloaded **`run(int i)`** method, it won't be invoked by **`start()`**. Only the no-argument **`run()`** method will be automatically invoked. To execute the overloaded **`run(int i)`** method, it must be called explicitly like any other method.

### Case 6: overriding the **`start()`** method:

If we override the **`start()`** method in a subclass of **`Thread`**, our overridden **`start()`** method will be executed like a normal method call, and no new thread will be started.

```java
class MyThread extends Thread {
    public void start() {
        System.out.println("start method");
    }

    public void run() {
        System.out.println("run method");
    }
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

In this example, the entire output is produced by the main thread, indicating that only the main thread is executed. It's important to note that it's never recommended to override the **`start()`** method.

### Life Cycle of the Thread

![m1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/6fae331c-7bc8-410d-ab52-64af75dda128)

1. **New State:** When a thread object is created, it enters the new state or born state.
2. **Runnable State:** Upon calling the **`start()`** method, the thread transitions to the ready or runnable state, indicating it's ready to execute but waiting for the CPU to be allocated by the thread scheduler.
3. **Running State:** If the thread scheduler assigns CPU resources to the thread, it enters the running state, where its **`run()`** method is executed.
4. **Dead State:** Once the **`run()`** method completes execution, the thread transitions to the dead state, indicating the end of its lifecycle.

### IllegalThreadStateException

After initiating a thread with the **`start()`** method, attempting to restart the same thread will result in a runtime exception known as "IllegalThreadStateException."

```java
MyThread t = new MyThread();
t.start(); // Valid
// Other code
t.start(); // Results in Runtime Exception: IllegalThreadStateException
```

## Defining a Thread by implementing Runnable interface

Threads can also be defined by implementing the **`Runnable`** interface. This interface, found in the **`java.lang`** package, contains only one method: **`run()`**.

![m2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/50b564bd-8372-4391-a2f3-ea805b0951c5)


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

### Case 1: `t1.start();`

When **`t1.start();`** is called, a new thread is created which is responsible for executing the **`run()`** method of the **`Thread`** class.

### Case 2: `t1.run();`

No new Thread will be created but Thread class run() method will be executed just like a normal method call.

### Case 3: `t2.start():`

New Thread will be created which is responsible for the execution of MyRunnable run() method.

### Case 4: `t2.run();`

No new Thread will be created and MyRunnable run() method will be executed just like a normal method call.

### Case 5: `r.start();`

We will get compile time error saying start()method is not available in MyRunnable class

### Case 6: `r.run();`

No new Thread will be created and MyRunnable class run() method will be executed just like a normal method call.


## **Best Approach to Define a Thread:**

When defining a Thread in Java, it's important to consider the most effective approach. Among the two ways of defining a Thread, implementing the **`Runnable`** interface is always recommended over extending the **`Thread`** class directly.

In the first approach, where our class extends the **`Thread`** class, we are limited in terms of inheritance. There is no flexibility to extend any other class, potentially missing out on the benefits of inheritance.

However, in the second approach, by implementing the **`Runnable`** interface, we can extend other classes as needed. This provides greater flexibility and is considered the more versatile and recommended method for defining a Thread.


# Thread Class Constructor

**Constructors for the `Thread` Class:**

1. **`Thread t = new Thread();`**
    - Creates a new `Thread` object with the default thread group (usually the main thread's group) and an automatically generated name.
    - The `run()` method of this thread object is empty by default. You need to override it to define the thread's task.

2. **`Thread t = new Thread(Runnable r);`**
    - Creates a new `Thread` object with the default thread group and an automatically generated name.
    - Takes a `Runnable` object (`r`) as an argument.
    - When you call `start()` on this thread, the `run()` method of the provided `Runnable` object (`r`) is executed.

    ```java
            MyRunnable r = new MyRunnable();
            Thread t = new Thread(r);
            t.start();
    ```

3. **`Thread t = new Thread(String name);`**
    - Creates a new `Thread` object with the default thread group and the specified name.
    - The `run()` method is still empty by default.

**Commonly Used Constructors (Corrected):**

These constructors are the most commonly used in practice:

1. **`Thread t = new Thread(Runnable r, String name);`**
    - Combines the functionalities of constructors 2 and 3.
    - Creates a new thread with the default thread group, the specified name, and the provided `Runnable` object (`r`).
    - When you call `start()` on this thread, the `run()` method of the `Runnable` object (`r`) is executed.

**Less Frequently Used Constructors:**

The following constructors are less commonly used but provide more advanced control:

1. **`Thread t = new Thread(ThreadGroup g, String name);`**
    - Creates a new thread object with the specified `ThreadGroup` (`g`) and the specified name.
    - Thread groups are a way to manage hierarchies of threads, but they are not widely used in modern Java due to potential complexity. Consider using thread pools instead for managing groups of threads.
2. **`Thread t = new Thread(ThreadGroup g, Runnable r);`**
    - Similar to constructor 2, but creates the thread within the specified `ThreadGroup` (`g`).
3. **`Thread t = new Thread(ThreadGroup g, Runnable r, String name);`**
    - Combines functionalities of constructors 4 and 6. Creates a thread with the specified `ThreadGroup` (`g`), name, and `Runnable` object (`r`).
4. **`Thread t = new Thread(ThreadGroup g, Runnable r, String name, long stackSize);`**
    - Least commonly used constructor. Creates a thread with the specified `ThreadGroup` (`g`), name, `Runnable` object (`r`), and stack size.
    - Setting the stack size is platform-dependent and not recommended in most cases. The JVM usually manages stack sizes effectively.

**Key Points:**

- For most cases, use constructor 4 (or constructor 2 if you don't need a specific name) to create threads with a `Runnable` object.
- Thread groups are not commonly used in modern Java development. If you need to manage groups of threads, consider using thread pools.
- Setting the stack size is rarely necessary. The JVM typically manages stack sizes appropriately.

# Getting and setting the name of a Thread:

Every Thread in Java has a name, which may be provided explicitly by the programmer or automatically generated by the JVM. The Thread class defines the following methods to get and set the name of a Thread:

1. **public final String getName()**: Returns the name of the thread.
2. **public final void setName(String name)**: Sets the name of the thread.

Example:

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

Note: We can obtain the reference to the currently executing Thread object using the **`Thread.currentThread()`** method.

# Thread Priorities

- Each thread in Java has a priority, which is an integer value between 1 (lowest) and 10 (highest).
- The priority influences, but doesn't guarantee, the thread scheduling decisions made by the JVM. Higher priority threads are generally given preference for CPU time by the thread scheduler.

**Default and Explicit Priorities:**

- By default, a newly created thread inherits its priority from the thread that created it (usually the main thread, which has a normal priority of 5).
- You can explicitly set the priority of a thread using the `setPriority(int newPriority)` method.

**Standard Priority Constants:**

- The `Thread` class defines constants for commonly used priorities:
    - `MIN_PRIORITY`: 1 (lowest)
    - `NORM_PRIORITY`: 5 (normal)
    - `MAX_PRIORITY`: 10 (highest)

**Important Considerations:**

- There are no constants for `LOW_PRIORITY` or `HIGH_PRIORITY`. You can use the defined constants or values within the valid range (1-10).
- Priority is a hint, not a guarantee: The Thread scheduler uses these priorities while allocating CPU time. The Thread with the highest priority will be given the first chance for execution. If two Threads have the same priority, the exact execution order cannot be guaranteed, as it depends on the behavior of the Thread scheduler, which is vendor-dependent.
- Setting priorities too aggressively can lead to starvation for lower priority threads. Use them judiciously to manage thread execution order when necessary.

**Getting and Setting Priorities:**

- The `getPriority()` method retrieves the current priority of a thread.
- The `setPriority(int newPriority)` method allows you to change the priority of a thread (within the valid range of 1-10). Setting an invalid priority throws an `IllegalArgumentException`.

**Key Takeaways:**

- Thread priorities provide a mechanism to influence thread scheduling, but they should be used with caution.
- Understand that priorities are hints, not absolute guarantees.
- Prioritize threads only when managing execution order is crucial for your application's functionality.

## Default Priority

The default priority for the main Thread is 5. However, for all other Threads, the default priority is inherited from parent to child. This means that the child Thread will have the same priority as its parent Thread by default.

Example 1:

```java
class MyThread extends Thread {}

class ThreadPriorityDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getPriority()); // 5
        Thread.currentThread().setPriority(9);
        MyThread t = new MyThread();
        System.out.println(t.getPriority()); // 9
    }
}

```

Example 2:

```java
class MyThread extends Thread {
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("child thread");
        }
    }
}

class ThreadPriorityDemo {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        // t.setPriority(10); // Uncomment this line to set priority to 10
        t.start();
        for(int i = 0; i < 10; i++) {
            System.out.println("main thread");
        }
    }
}
```

- If we comment out line 1 (**`//t.setPriority(10);`**), both the main and child Threads will have the same priority, and the exact execution order cannot be determined.
- If line 1 is not commented out, and the priority of the child Thread is set to 10 while the main Thread retains its default priority of 5, then the child Thread will be given priority for execution. Once the child Thread completes its execution, the main Thread will get its chance to execute.

# **Methods to Prevent Thread Execution:**

You can prevent or pause a thread's execution using the following methods:

1. **`yield()`**
2. **`join()`**
3. **`sleep()`**

## **`yield()` Method:**

1. **Behavior**:
    - If all waiting threads have lower priority or if there are no waiting threads, the same thread will continue its execution.
    - If multiple waiting threads with the same priority are available, it's not guaranteed which one will get the chance for execution next.
2. **Thread Scheduling**:
    - When a thread is yielded, the thread scheduler determines when it will resume execution.
        1. **Functionality**:
            - The **`yield()`** method causes the current executing thread to pause temporarily, allowing other threads of the same priority to execute.
            - It gives other waiting threads of the same priority a chance to run.
    - The thread's chance for execution again depends on the mercy of the thread scheduler.
3. **Method Signature**:
    - **`public static native void yield()`**

  ![m3](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/f410cf59-a47f-470c-9ddd-b5970bbf5a75)


   Example Thread Yield Demo Code:

    ```java
    class MyThread3 extends Thread
    {
        public void run(){
            for (int i=0;i<5;i++){
                Thread.yield();
                System.out.println("Child Thread");
            }
        }
    
    }
    
    public class ThreadYieldDemo {
    
        public static void main(String[] args){
            MyThread3 t = new MyThread3();
            t.start();
            for (int i=0;i<5;i++){
                System.out.println("Main Thread");
            }
        }
    }
    
    ```


## **`join()`** Method:

- When a thread wants to wait until the completion of another thread, it can use the **`join()`** method.
- Example: If a thread **`t1`** wants to wait until the completion of another thread **`t2`**, **`t1`** can call **`t2.join()`**.

### **Behavior:**

- When **`t1`** executes **`t2.join()`**, **`t1`** will enter a waiting state until **`t2`** completes its execution.
- Once **`t2`** finishes executing, **`t1`** will resume its execution.

![m4](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/43bb4211-aa2a-4c9b-9484-b6f9182cafdd)


The **`join()`** method throws a checked exception **`InterruptedException`**, which indicates that the thread calling **`join()`** was interrupted while waiting for the target thread to finish.

Example Code :

```java
public class ThreadJoinDemo {

    public static void main(String [] args){
        Thread t1 = new Thread(()->{
           System.out.println("Thread t1 started");
           try{
               Thread.sleep(2000);
           }catch (InterruptedException e){
               e.printStackTrace();
           }
            System.out.println("Thread t1 completed");
        });

        Thread t2 = new Thread(()->{
            System.out.println("Thread t2 started");
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Thread t2 completed");
        });

        t1.start();
        t2.start();

        try{
            t2.join();   // Main thread (or any other thread) will wait until t2 completes
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Main Thread Completes");

    }

}

```

Output : →

```java
Thread t1 started
Thread t2 started
Thread t1 completed
Thread t2 completed
Main Thread Completes
```

- Two threads **`t1`** and **`t2`** are created using lambda expressions representing their respective tasks.
- Both threads are started using the **`start()`** method.
- The main thread then calls **`t2.join()`**, causing it to wait until **`t2`** completes its execution.
- Once **`t2`** completes, the main thread prints "Main thread completes" to indicate its completion.

Code : Make the child thread wait for the main thread to finish

```java
class MyThread4 extends Thread {
    private Thread mt; // Store the main thread reference

    public void setMainThread(Thread mt) {
        this.mt = mt;
    }

    public void run() {
        try {
            if (mt != null) {
                mt.join(); // Wait for the main thread to finish
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Child Thread");
        }
    }
}

public class ThreadJoinDemo2 {
    public static void main(String[] args) throws InterruptedException {
        MyThread4 t = new MyThread4();
        t.setMainThread(Thread.currentThread()); // Pass the main thread reference
        t.start();

        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            System.out.println("Main Thread");
        }
    }
}

```

Output:→

```java
Main Thread
Main Thread
Main Thread
Main Thread
Main Thread
Child Thread
Child Thread
Child Thread
Child Thread
Child Thread
```

Note :

If the main thread calls **`join()`** on a child thread object and the child thread calls **`join()`** on the main thread object, then both threads will wait for each other indefinitely, causing the program to hang. This situation resembles a deadlock. Similarly, if a Thread class **`join()`** method is called on the same thread itself, the program will hang

```java
class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread childThread = new Thread(() -> {
            try {
                mainThread.join(); // Child thread waits for main thread to complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        childThread.start();

        try {
            childThread.join(); // Main thread waits for child thread to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

```

## **`sleep()` Method:**

The **`sleep()`** method in Java is used to pause the execution of the current thread for a specified amount of time. It has two overloaded versions, one that takes milliseconds (**`sleep(long ms)`**) and another that takes milliseconds and nanoseconds (**`sleep(long ms, int ns)`**).

1. **`public static native void sleep(long ms) throws InterruptedException`**
2. **`public static void sleep(long ms, int ns) throws InterruptedException`**

Every invocation of the **`sleep()`** method throws an **`InterruptedException`**, which is a checked exception. Therefore, it is mandatory to handle this exception using either a try-catch block or by declaring it with the **`throws`** keyword; otherwise, a compile-time error will occur.

![m5](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/4d521ecf-d99e-4c97-80c6-3677b61666d4)


Example Code :→

```java
public class SleepDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("R");
        Thread.sleep(1000);
        System.out.println("A");
        Thread.sleep(2000);
        System.out.println("J");
        Thread.sleep(3000);
    }
}

```

Output : →

```java
R
A
J
```
## Interrupting a Thread :

1. **Interrupting a Thread**: A thread can be interrupted  by calling the **`interrupt()`** method on its instance.
2. **Effect on Sleeping or Waiting Threads**: If the target thread is in a sleeping or waiting state, the interrupt will take effect immediately, causing an **`InterruptedException`** to be thrown if the sleeping or waiting thread is properly handling interruptions.
3. **Waiting for Thread State Change**: If the target thread is not in a sleeping or waiting state, the interrupt call will wait until the thread enters such a state. Once the target thread enters a sleeping or waiting state, the interrupt will take effect immediately.
4. **No Impact If Never in Sleeping or Waiting State**: If the target thread never enters a sleeping or waiting state during its lifetime, the interrupt call will have no effect and will be essentially wasted.

**Example 1: Interrupting a Sleeping Thread**

```java
class MyThread extends Thread {
  public void run() {
    try {
      for (int i = 0; i < 5; i++) {
        System.out.println("I am lazy Thread :" + i);
        Thread.sleep(2000);
      }
    } catch (InterruptedException e) {
      System.out.println("I got interrupted");
    }
  }
}

class ThreadInterruptDemo {
  public static void main(String[] args) {
    MyThread t = new MyThread();
    t.start();
    t.interrupt(); // Interrupt the child thread
    System.out.println("End of main thread");
  }
}
```

In this example, the `interrupt()` call in the `main` thread will likely interrupt the `MyThread` during its first `sleep()`, causing it to exit the loop and print "I got interrupted."

**Example 2: Interrupting a Non-Sleeping Thread (Delayed Effect)**

```java
class MyThread extends Thread {
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println("I am lazy thread");
    }
    System.out.println("I'm entered into sleeping stage");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      System.out.println("I got interrupted");
    }
  }
}

class ThreadInterruptDemo1 {
  public static void main(String[] args) {
    MyThread t = new MyThread();
    t.start();
    t.interrupt(); // Interrupt the child thread
    System.out.println("End of main thread");
  }
}
```

Output :→

```java
I am lazy thread
I am lazy thread
I am lazy thread
I am lazy thread
I am lazy thread
I'm entered into sleeping stage
I got interrupted
```

Here, the `interrupt()` call in the `main` thread might not have an immediate effect because `MyThread` is not initially sleeping. However, once it enters the `sleep()` state, the interrupt flag will be checked, causing the `InterruptedException` and the "I got interrupted" message.

**Key Points:**

- `interrupt()` sets an interrupt flag, not a guaranteed stop.
- Sleeping/waiting threads are more susceptible to interruption.
- Interrupted threads need to check the interrupt flag themselves.
- Interrupting is a cooperative mechanism, requiring the target thread to handle it.

## Comparison of the **`yield()`**, **`join()`**, and **`sleep()`** methods in Java:

| Property | yield() | join() | sleep() |
| --- | --- | --- | --- |
| Purpose | To pause current executing thread to allow other threads of the same priority to execute. | To make a thread wait until another thread completes its execution. | To make a thread pause its execution for a specific amount of time. |
| Is it static? | Yes | No | Yes |
| Is it final? | No | Yes | No |
| Is it overloaded? | No | Yes | Yes |
| Throws InterruptedException? | No | Yes | Yes |
| Is it a native method? | Yes | No | sleep(long ms) is native, sleep(long ms, int ns) is non-native. |

# Synchronization

1. **Applicability of synchronized keyword**:
    - It is applicable for methods and blocks but not for classes and variables.
2. **Execution restriction**:
    - When a method or block is declared as synchronized, only one thread at a time is allowed to execute that method or block on the given object.
3. **Advantage of synchronized keyword**:
    - The main advantage of the synchronized keyword is resolving data inconsistency problems, particularly in multi-threaded environments.
4. **Disadvantage of synchronized keyword**:
    - However, the main disadvantage is that it increases the waiting time of threads and can affect the performance of the system.
5. **Recommendation on usage**:
    - Hence, if there is no specific requirement for synchronization, it's not recommended to use the synchronized keyword to avoid unnecessary performance overhead.
6. **Implementation**:
    - Internally, synchronization is implemented using the concept of locks.
7. **Lock concept**:
    - Every object in Java has a unique lock. Synchronization comes into play when the synchronized keyword is used, and it operates based on these locks.
8. **Acquiring and releasing lock**:
    - When a thread wants to execute any synchronized method on a given object, it first needs to acquire the lock of that object. Once acquired, the thread can execute synchronized methods on that object. Upon completion, the lock is automatically released.
9. **Concurrency behavior**:
    - While a thread is executing any synchronized method on an object, other threads are not allowed to execute any synchronized method on the same object simultaneously. However, they are allowed to execute non-synchronized methods simultaneously. This behavior is based on the object's lock rather than the method itself.

Finally, it's important to note that the acquisition and release of locks are managed by the JVM, and programmers are not responsible for these activities. This ensures consistency and reliability in multi-threaded programming.



## Concurrency Control : Synchronization Behavior Explained

In Java multithreading, if a class has two synchronized methods, let's say **`m1`** and **`m2`**, and one non-synchronized method **`m3`**, and multiple threads are accessing these methods concurrently:

- If **`thread t1`** is executing **`m1`** on object **`x`**, and **`thread t2`** attempts to execute **`m1`** on the same object **`x`**, it will have to wait because the lock on object **`x`** is held by **`thread t1`**. Therefore, **`thread t2`** will be in a waiting state until it can acquire the lock on object **`x`**.
- Meanwhile, if **`thread t3`** attempts to execute **`m3`** on object **`x`**, it will be able to do so immediately because **`m3`** is a non-synchronized method. Non-synchronized methods can be executed by multiple threads simultaneously without waiting for a lock.

![m1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/9f1110ed-c2bf-4733-ad0f-45d50346010c)


- Non-synchronized methods don't acquire a separate lock.
- They can access the object's state concurrently with a synchronized method on the same object, but only if they don't modify shared data.
- Modifying shared data within a non-synchronized method can lead to issues due to the lack of synchronization with other threads.

## Synchronized vs. Non-Synchronized Areas

![m2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/7f7a1aef-447d-4ea7-8bf4-f43a96411de2)


1. **Synchronized Area**: This is a portion of the code where access to shared resources or critical sections is synchronized using locks. Only one thread can access this area at a time, ensuring that updates to shared state are atomic and consistent.
2. **Non-Synchronized Area**: This part of the code doesn't require synchronization. Multiple threads can access non-synchronized methods or sections concurrently without any risk of data corruption because these methods don't modify shared state.
3. **Recommended Approach for Update Operations**: Operations that modify the state of an object, such as add, delete, remove, or replace, should be performed within synchronized blocks or methods. This ensures that only one thread at a time can modify the object's state, preventing data corruption and maintaining consistency.
4. **Recommended Approach for Read Operations**: Read operations that don't modify the object's state can be safely performed using non-synchronized methods. Since these operations don't change the shared state, there's no risk of data corruption even if multiple threads read the object concurrently.

```java
class Counter {
  private int value = 0;

  public synchronized void increment() { // Synchronized for updates
    value++;
  }

  public int getValue() { // Non-synchronized if read doesn't affect other threads
    return value;
  }
}

```

Example 2 : Synchronized Demo

```java

class Display
{
    public synchronized  void  wish(String name){
        for(int i=0;i<3;i++){
            System.out.print("Good morning : ");
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ignored){
            }
            System.out.println(name);
        }
    }
}

class MyThread7 extends Thread{

    Display d;
    String  name;
    MyThread7(Display d, String name){
        this.d = d;
        this.name = name;
    }

    public void run(){
        d.wish(name);
    }
}

public class SynchronizedDemo
{

    public static void main(String[] args){
        Display d1 = new Display();
        MyThread7 t1=new MyThread7(d1,"Rajeev");
        MyThread7 t2 =new MyThread7(d1,"Rajeev Singh");
        t1.start();
        t2.start();
    }
}

```

Output : →

```java
Good morning : Rajeev
Good morning : Rajeev
Good morning : Rajeev
Good morning : Rajeev Singh
Good morning : Rajeev Singh
Good morning : Rajeev Singh
```

If the **`wish()`** method is not declared as **`synchronized`**, both threads (**`t1`** and **`t2`**) will be able to execute it simultaneously. This can lead to interleaved or irregular output because the two threads might be printing their messages concurrently, causing overlapping output lines.

However, when the **`wish()`** method is declared as **`synchronized`**, only one thread can execute it at a time. This ensures that the output is regular and predictable because each thread will wait for its turn to execute the method. In this case, you'll get a sequence of messages printed for each thread, without any overlap or irregularity in the output.

### Case 1:

```java
Display d1=new Display();
Display d2=new Display();
MyThread t1=new MyThread(d1,"dhoni");
MyThread t2=new MyThread(d2,"yuvaraj");
t1.start();
t2.start();
```

![m3](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/5e793e5d-d76d-442c-b6b6-de197cdd42c6)


1. When you have multiple threads operating on multiple objects (like in your case with **`d1`** and **`d2`**), even if the method is synchronized, each thread will acquire the lock for its respective object. Since the threads are operating on different objects, they won't block each other, and the synchronization won't have any impact on the execution order. This can result in irregular output because there's no coordination between the threads regarding access to shared resources.
2. On the other hand, when multiple threads are operating on the same Java object, synchronization becomes crucial. If you have multiple threads operating on the same object and the methods they call are synchronized, then only one thread will be able to execute those methods at a time, ensuring proper coordination and avoiding data corruption or inconsistent behavior due to concurrent access.

## Class level lock :

1. Every class in Java has a unique class-level lock associated with it. This lock is used when a thread wants to execute a static synchronized method of that class.
2. Once a thread acquires the class-level lock of a class, it is allowed to execute any static synchronized method of that class.
3. While a thread is executing any static synchronized method of a class, other threads are not allowed to execute any other static synchronized methods of that class simultaneously. This ensures that only one thread can execute static synchronized methods of the class at a time.
4. However, other threads are still allowed to execute normal synchronized methods, normal static methods, and normal instance methods of the class simultaneously. These methods use instance-level locks and do not conflict with the class-level lock.
5. It's important to note that the class-level lock and object-level lock (used with synchronized instance methods and blocks) are different and independent. There's no relationship between these two locks, and acquiring one does not affect the other. Each object has its own monitor lock, which is separate from the class-level lock associated with its class.

## Synchronized block:

1. **Using Synchronized Blocks**: When only a few lines of code within a method require synchronization, it's often better to use synchronized blocks instead of synchronizing the entire method. This allows for finer-grained control over which sections of code are synchronized, reducing contention and improving performance.
2. **Advantages of Synchronized Blocks: R**educe waiting time for threads and improve system performance. This is because synchronized blocks limit the scope of synchronization to only the critical sections of code, allowing other threads to execute non-synchronized parts concurrently.

**Examples**:

a. **Synchronized on Current Object (`this`)**: To synchronize on the current object, you can use **`synchronized(this) { ... }`**. This ensures that only one thread at a time can execute the synchronized block while holding the lock on the current object.

b. **Synchronized on a Specific Object (`b`)**: To synchronize on a specific object **`b`**, you can use **`synchronized(b) { ... }`**. This ensures that only one thread that holds the lock on object **`b`** can execute the synchronized block at a time.

c. **Synchronized on Class Level Lock (`Display.class`)**: To synchronize on the class level, you can use **`synchronized(Display.class) { ... }`**. This ensures that only one thread that holds the lock on the class **`Display`** can execute the synchronized block at a time.

**Note**: When using synchronized blocks, the argument passed to **`synchronized`** must be an object reference or a class object (obtained using **`.class`**). Primitive values cannot be used because synchronization is applicable only to objects and classes, not primitives.

## Questions

1. Explain about the **`synchronized`** keyword and its advantages and disadvantages.
2. What is an object lock, and when is a Thread required to obtain it?
3. Define class-level lock and specify when a Thread is required to acquire it.
4. What distinguishes an object lock from a class-level lock?
5. While a Thread is executing a synchronized method on a given object, are other Threads allowed to execute other synchronized methods simultaneously on the same object?
6. Define a synchronized block and explain its declaration.
7. Enumerate the advantage of a synchronized block over a synchronized method.
8. Can a Thread hold more than one lock simultaneously?
9. What is synchronized statement?

# Inter Thread communication (wait(),notify(), notifyAll())

1. **Inter-Thread Communication**: Threads can communicate with each other by using **`wait()`**, **`notify()`**, and **`notifyAll()`** methods provided by the **`Object`** class.
2. **Usage of `wait()` Method**:
   - A thread that requires an update on an object calls the **`wait()`** method on that object.
   - This causes the thread to enter a waiting state until it receives a notification from another thread.
3. **Usage of `notify()` and `notifyAll()` Methods**:
   - The thread responsible for performing the update on the object calls the **`notify()`** or **`notifyAll()`** method to notify waiting threads.
   - This wakes up one or all waiting threads, allowing them to proceed and obtain the updates.
4. **Location of Methods**:
   - **`wait()`**, **`notify()`**, and **`notifyAll()`** methods are available in the **`Object`** class, not the **`Thread`** class, because they operate on common objects.
   - Thread can call these method on any java object, if we require method on any java object, compulsory that method should be available in object class.
   - These methods must be called from a synchronized context because the thread calling them must own the lock of the object.
   - Calling these methods from unsynchronized contexts will result in **`IllegalMonitorStateException`**.
5. **Behavior of Threads**:
   - When a thread calls **`wait()`** on any object, it immediately releases the lock of the object and enters a waiting state.
   - When a thread calls **`notify()`** or **`notifyAll()`**, it releases the lock of the object, allowing waiting threads to continue execution. However, the lock release may not happen immediately.
6. **Lock Release**:
   - The **`wait()`**, **`notify()`**, and **`notifyAll()`** methods are the only methods where the lock release will occur. Other methods or statements do not release the lock implicitly.

| Method | Releases Lock? |
| --- | --- |
| yield() | No |
| join() | No |
| sleep() | No |
| wait() | Yes |
| notify() | Yes |
| notifyAll() | Yes |

When a Thread invokes methods like wait(), notify(), or notifyAll() on an object, it releases the lock of that particular object while keeping other locks it holds intact.

```java
1. public final void wait()throws InterruptedException
2. public final native void wait(long ms)throws InterruptedException
3. public final void wait(long ms,int ns)throws InterruptedException
4. public final native void notify()
5. public final void notifyAll()
```

Additionally, every wait method throws the InterruptedException checked exception, so it's necessary to handle it properly using try-catch or the throws keyword when using wait().

![m4](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/2c86bf1a-ec30-47d3-a62f-72f47b5e5c30)


Example Code :→

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

## Producer Consumer problem :

The producer thread adds items to the queue, and the consumer thread removes them. If the queue is empty, the consumer thread waits using the **`wait()`** method on the queue object until it's notified. When the producer adds items, it notifies the consumer by calling **`notify()`** on the queue.

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

## Notify vs NotifyAll()

- We use the notify() method to signal one thread waiting for a resource. If multiple threads are waiting, only one thread will be awakened, while the others remain in a waiting state. The selection of which thread gets notified depends on the JVM's scheduling mechanism.
- Alternatively, we use the notifyAll() method to wake up all waiting threads. Each waiting thread will be notified and will subsequently execute one by one, as they acquire the required lock.

Note: When invoking wait(), notify(), or notifyAll() methods, we must obtain the lock of the corresponding object on which these methods are called, not the locks of other objects.

For instance, if we call wait() on object S1, we must acquire the lock of the S1 object, not the lock of object S2

# Dead Lock

- Deadlock occurs when two or more threads are indefinitely waiting for each other, resulting in a situation of infinite waiting.
- While there are no direct resolution techniques for deadlocks, various prevention or avoidance strategies can be implemented.
- The synchronized keyword often leads to deadlock situations. Therefore, extra caution is necessary when using synchronized blocks or methods.

```java
class A {
    public synchronized void foo(B b) {
        System.out.println("Thread1 starts execution of foo() method");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println("Thread1 trying to call b.last()");
        b.last();
    }

    public synchronized void last() {
        System.out.println("inside A, this is last() method");
    }
}

class B {
    public synchronized void bar(A a) {
        System.out.println("Thread2 starts execution of bar() method");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println("Thread2 trying to call a.last()");
        a.last();
    }

    public synchronized void last() {
        System.out.println("inside B, this is last() method");
    }
}

public class Deadlock extends Thread {

   A a = new A();
   B b = new B();

   public void m1()
   {
       this.start();
       a.foo(b);
   }

   public void run()
   {
       b.bar(a);
   }

   public static void main(String [] args){
       Deadlock d = new Deadlock();
       d.m1();
   }
}

```

This Java code exhibits a classic example of a deadlock scenario. Let's understand how the deadlock occurs:

1. The **`Deadlock`** class creates instances of classes **`A`** and **`B`**.
2. It starts a new thread (**`this.start()`**) within the **`m1()`** method, which implicitly calls the **`run()`** method.
3. In the **`run()`** method, the **`bar()`** method of class **`B`** is called with object **`a`**.
4. Meanwhile, in the **`m1()`** method, the **`foo()`** method of class **`A`** is called with object **`b`**.

Now, if **`foo()`** and **`bar()`** methods are called simultaneously by different threads with objects **`a`** and **`b`**, respectively, they can potentially lead to a deadlock:

- Thread 1 (from the **`run()`** method) holds the lock on object **`b`** and tries to call **`a.last()`**.
- Thread 2 (from the **`m1()`** method) holds the lock on object **`a`** and tries to call **`b.last()`**.

Both threads are waiting for each other to release the lock on the objects they need, resulting in a deadlock.

To avoid deadlock, you should ensure that threads acquire locks on resources in the same order. In this case, you could modify the code to ensure that either **`foo()`** or **`bar()`** is called first, or avoid calling **`last()`** method from within **`foo()`** and **`bar()`** altogether.

### Deadlock vs Starvation:

- A long waiting of a Thread which never ends is called deadlock.
- A long waiting of a Thread which ends at a certain point is called starvation.
- Starvation occurs when a low priority Thread has to wait until completing all high priority Threads.
- Starvation is characterized by a long waiting of a Thread, which eventually ends at a certain point.

# Daemon Threads:

Daemon Threads are those executing in the background. Their main objective is to provide support for non-daemon Threads, like the main Thread. Usually, Daemon Threads have low priority, but they can run with high priority based on specific requirements.

Examples of Daemon Threads include the Garbage Collector and the Signal Dispatcher. For instance, when a program runs with low memory, the JVM executes the Garbage Collector to free memory, allowing the main Thread to continue its execution.

- We can check whether a Thread is a daemon or not using the isDaemon() method of the Thread class: **`public final boolean isDaemon();`**
- The daemon nature of a Thread can be changed using the setDaemon() method: **`public final void setDaemon(boolean b);`**
- However, the daemon nature can only be changed before starting the Thread. Attempting to change it after starting the Thread results in an IllegalThreadStateException.
- By default, the main Thread is always non-daemon, and its daemon nature cannot be changed because it's already started at the beginning.
- The daemon nature of Threads is inherited from parent to child. If the parent is a daemon, then the child is also a daemon, and if the parent is non-daemon, then the child is also non-daemon.
- When the last non-daemon Thread terminates, all daemon Threads are automatically terminated.

```java
class MyThread extends Thread {
}

class DaemonThreadDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().isDaemon());
        MyThread t = new MyThread();
        System.out.println(t.isDaemon()); // Output: false
        t.start();
        t.setDaemon(true); // Throws IllegalThreadStateException
        System.out.println(t.isDaemon());
    }
}

```

```java
class MyThreadC extends Thread{

    public void run()
    {
        for(int i=0;i<10;i++){
            System.out.println("Child Thread");
        }
        try{
            Thread.sleep(2000);
    }catch(InterruptedException ignored){}
    }

}

public class DaemonThreadDemo {
    public static void main(String[] args){
        MyThreadC tc = new MyThreadC();
        tc.setDaemon(true);
        tc.start();
        System.out.println("End of main Thread");
    }
}

```

In this example, a thread of type MyThreadC is created and set as a daemon thread using **`setDaemon(true)`**. It starts execution, printing "Child Thread" ten times and then sleeps for two seconds. Meanwhile, the main thread prints "End of main Thread". As soon as the main thread terminates, the daemon thread also terminates, regardless of its current state.

# GreenThread:

Java's multithreading concept can be implemented using two main methods:

1. GreenThread Model
2. Native OS Model

GreenThread Model:

Threads managed entirely by the JVM without relying on support from the underlying operating system are referred to as Green Threads.

Very few operating system like Sun Solaries provide support for Green Thread Model.

Green Thread model is deprecated and not recommended to use.

Native OS Model:

- Threads managed by the JVM with the assistance of the underlying operating system are known as Native OS Model.
- Windows-based operating systems support the Native OS Model.
- Only a few operating systems like Sun Solaris provide support for the GreenThread Model.
- However, the GreenThread Model is deprecated and not recommended for us.

## How to kill Thread in the middle of the line?

To stop a thread in the middle of its execution, the deprecated **`stop()`** method could be called. However, it is not recommended due to its unsafe nature and potential for leaving the application in an inconsistent state.

```java
public final void stop();
```

## Suspend and Resume methods :

Alternatively, the **`suspend()`** and **`resume()`** methods could be used to temporarily pause and resume a thread's execution. However, both of these methods are deprecated and not recommended for use due to their potential to cause deadlock situations.

```java
public final void suspend();
public final void resume();
```

It's generally preferred to design threads in a way that they naturally terminate or signal each other to stop gracefully, rather than forcefully terminating them. This helps in maintaining application stability and avoiding potential issues caused by abrupt thread termination.

# Race Condition:

A race condition occurs when multiple threads execute simultaneously and interfere with each other, leading to data inconsistency problems.

One way to resolve race conditions is by using the **`synchronized`** keyword to ensure that only one thread can access critical sections of code at a time, thereby preventing concurrent access and potential conflicts.

# ThreadGroup:

ThreadGroup is a way to group threads together based on their functionality, allowing for common operations to be performed on all threads within the group.

We can create a ThreadGroup using the constructor:

```java
ThreadGroup g = new ThreadGroup(String gName);
```

Threads can be attached to a ThreadGroup using the constructor of the Thread class:

```java
Thread t = new Thread(ThreadGroup g, String name);
```

For example:

```java
ThreadGroup g = new ThreadGroup("Printing Threads");
MyThread t1 = new MyThread(g, "Header Printing");
MyThread t2 = new MyThread(g, "Footer Printing");
MyThread t3 = new MyThread(g, "Body Printing");
```

To stop all threads in a ThreadGroup, you can call:

```java
g.stop();
```

## ThreadLocal (1.2 v):

ThreadLocal provides a way to define thread-local resources, which are specific to each thread. Examples include database connections, counter variables, etc.

It can also be used to define thread scopes similar to servlet scopes (page, request, session, application), ensuring that each thread has its own independent context.

# Life Cycle of Thread

![m5](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/5dc02580-479d-4b7a-ac41-514d675cd475)

[All Codes](https://github.com/Rajeev-singh-git/Java_Interview_Question/tree/main/MultiThreading/src)

# Questions

1. What is a Thread?
2. Which Thread by default runs in every java program?
   Ans: By default main Thread runs in every java program.
3. What is the default priority of the Thread?
4. How can you change the priority number of the Thread?
5. Which method is executed by any Thread?
   Ans: A Thread executes only public void run() method.
6. How can you stop a Thread which is running?
7. Explain the two types of multitasking?
8. What is the difference between a process and a Thread?
9. What is Thread scheduler?
10. Explain the synchronization of Threads?
11. What is the difference between synchronized block and synchronized keyword?
12. What is Thread deadlock? How can you resolve deadlock situation?
13. Which methods are used in Thread communication?
14. What is the difference between notify() and notifyAll() methods?
15. What is the difference between sleep() and wait() methods?
16. Explain the life cycle of a Thread?
17. What is daemon Thread
