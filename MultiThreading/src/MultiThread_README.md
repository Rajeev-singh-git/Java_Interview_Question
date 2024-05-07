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