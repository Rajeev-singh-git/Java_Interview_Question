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
