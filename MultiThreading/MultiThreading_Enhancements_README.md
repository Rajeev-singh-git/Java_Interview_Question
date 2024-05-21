# ThreadGroup

1. **Functionality of ThreadGroup**: ThreadGroup represents a set of threads. It allows you to perform common operations on all threads belonging to a particular group, such as stopping all consumer threads or suspending all producer threads.
2. **Hierarchy**: ThreadGroup forms a hierarchical structure where a group can contain other subgroups along with threads. This hierarchical structure allows for organization and management of threads in a logical manner.
3. **Class Location**: The ThreadGroup class is located in the **`java.lang`** package and is a direct child class of the Object class.
4. **Common Operations**: ThreadGroup provides methods to perform common operations on all threads in the group, such as interrupting all threads, destroying the group and its subgroups, enumerating threads and subgroups, and setting the maximum priority of threads in the group.
5. **Main Thread and System Group**: Every thread in Java belongs to some group. The main thread belongs to the main group. Additionally, every thread group in Java is a child group of the system group, either directly or indirectly. Therefore, the system group serves as the root for all thread groups in Java. It contains several system-level threads like **`Finalizer`**, **`ReferenceHandler`**, **`SignalDispatcher`**, etc.
6. Overall, ThreadGroup in Java provides a useful mechanism for organizing and managing threads, allowing for better control and coordination in multithreaded applications.

## Constructor

1. **`ThreadGroup g = new ThreadGroup(String gname);`**
    - This constructor creates a new ThreadGroup with the given name **`gname`**.
    - The parent of this new ThreadGroup is the ThreadGroup of the currently running thread.
    - If the currently running thread doesn't have a ThreadGroup, the parent becomes the System ThreadGroup.

   Example:

    ```java
    
    ThreadGroup myGroup = new ThreadGroup("MyThreadGroup");
    ```

2. **`new ThreadGroup(ThreadGroup pg, String gname);`**
    - This constructor creates a new ThreadGroup with the given name **`gname`**.
    - The parent of this new ThreadGroup is the specified parent ThreadGroup **`pg`**.

   Example:

    ```java
    
    ThreadGroup parentGroup = new ThreadGroup("ParentThreadGroup");
    ThreadGroup myGroup = new ThreadGroup(parentGroup, "MyThreadGroup");
    
    ```


These constructors provide flexibility in creating ThreadGroups and organizing threads into hierarchical structures, which is useful for managing and controlling threads in Java applications.

Note:

- In Java, every thread belongs to some group.
- Every ThreadGroup is either directly or indirectly a child group of the System Group.
- Therefore, the System Group acts as the root for all ThreadGroups in Java.
- The System ThreadGroup represents system-level threads such as ReferenceHandler, SignalDispatcher, Finalizer, AttachListener, etc.

![m1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/b3c3d120-f634-4047-bad0-27c349392101)


Code

```java
package MultiThreading_Enhancements;

public class ThreadGroupDemo {

    public static void main(String [] args){
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());

        ThreadGroup gp = new ThreadGroup("Parent Group");
        System.out.println(gp.getParent().getName());

        ThreadGroup cg = new ThreadGroup(gp,"Child Group");
        System.out.println(cg.getParent().getName());
      }
}
```

Output :→

```java

    main
    system
    main
    Parent Group
```

![m2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/f4921003-e34e-4b14-8789-dc4b5cd009fa)


# Important methods available in the ThreadGroup class

1. **`getName()`**: Returns the name of the ThreadGroup.

    ```java
    String groupName = threadGroup.getName();
    ```

2. **`getMaxPriority()`**: Returns the maximum priority of the ThreadGroup.

    ```java
    int maxPriority = threadGroup.getMaxPriority();
    ```

3. **`setMaxPriority(int priority)`**:
    - Sets the maximum priority of the ThreadGroup to the specified **`priority`**.
    - Threads added to this ThreadGroup afterward will have a maximum priority of **`priority`**.

    ```java
    threadGroup.setMaxPriority(8);
    ```

4. **`getParent()`**: Returns the parent ThreadGroup of the current ThreadGroup.

    ```java
    ThreadGroup parentGroup = threadGroup.getParent();
    ```

5. **`list()`**: Prints information about the ThreadGroup to the console.

    ```java
    threadGroup.list();
    ```

6. **`activeCount()**:` Returns the number of active threads present in the ThreadGroup.

    ```java
    int activeThreads = threadGroup.activeCount();
    ```

7. **`activeGroupCount()`**: Returns the number of active sub-ThreadGroups present in the current ThreadGroup.

    ```java
    int activeSubGroups = threadGroup.activeGroupCount();
    ```

8. **`enumerate(Thread[] threads)`**:
    - Copies all active threads of this ThreadGroup into the provided **`threads`** array.
    - Sub-ThreadGroups' threads are also included.

    ```java
    Thread[] threads = new Thread[threadGroup.activeCount()];
    threadGroup.enumerate(threads);
    ```

9. **`enumerate(ThreadGroup[] groups)`**:
    - Copies all active sub-ThreadGroups into the provided **`groups`** array.

    ```java
    ThreadGroup[] subGroups = new ThreadGroup[threadGroup.activeGroupCount()];
    threadGroup.enumerate(subGroups)
    ```

10. **`isDaemon()`**: Checks if the ThreadGroup is a daemon ThreadGroup.

```java
boolean isDaemon = threadGroup.isDaemon();
```

1. **`setDaemon(boolean daemon)`**: Sets whether the ThreadGroup is a daemon ThreadGroup or not.

```java
threadGroup.setDaemon(true);
```

1. **`interrupt()`**: Interrupts all threads present in the ThreadGroup.

```java
threadGroup.interrupt();
```

1. **`destroy()`**: Destroys the ThreadGroup and its sub-ThreadGroups.

```java
threadGroup.destroy();
```

Example Code:

```java
package MultiThreading_Enhancements;

public class ThreadGroupDemo2 {
    
    public static void main(String[] args){
        ThreadGroup g1 = new ThreadGroup("tg");
        Thread t1 = new Thread(g1,"Thread 1");
        Thread t2 = new Thread(g1,"Thread 2");
        g1.setMaxPriority(3);
        Thread t3 = new Thread(g1,"Thread 3");
        System.out.println(t1.getPriority());  // 5
        System.out.println(t2.getPriority());  // 5
        System.out.println(t3.getPriority());  // 3

    }
}

```

Code : Create ThreadGroups and utilize their methods in Java

```java
class MyThread extends Thread {
    MyThread(ThreadGroup g, String name) {
        super(g, name);
    }
    public void run() {
        System.out.println("Child Thread");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
    }
}

class ThreadGroupDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup pg = new ThreadGroup("Parent Group");
        ThreadGroup cg = new ThreadGroup(pg, "Child Group");
        MyThread t1 = new MyThread(pg, "Child Thread 1");
        MyThread t2 = new MyThread(pg, "Child Thread 2");
        t1.start();
        t2.start();
        System.out.println(pg.activeCount());
        System.out.println(pg.activeGroupCount());
        pg.list();
        Thread.sleep(5000);
        System.out.println(pg.activeCount());
        pg.list();
    }
}

```

Output :

```java
Child Thread
Child Thread
2
1
java.lang.ThreadGroup[name=Parent Group,maxpri=10]
    Thread[#31,Child Thread 1,5,Parent Group]
    Thread[#32,Child Thread 2,5,Parent Group]
    java.lang.ThreadGroup[name=Child Group,maxpri=10]
0
java.lang.ThreadGroup[name=Parent Group,maxpri=10]
    java.lang.ThreadGroup[name=Child Group,maxpri=10]
```

![m3](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/4375dfb4-09a6-49db-8df1-2bd333746797)


Write a Program to Display All Thread Names belongs to System Group

```java
public class ThreadGroupDemo4 {

    public static void main(String[] args) {
        ThreadGroup system = Thread.currentThread().getThreadGroup().getParent();
        Thread[] t = new Thread[system.activeCount()];
        system.enumerate(t);
        for (Thread t1 : t) {
            System.out.println(t1.getName() + "-------" + t1.isDaemon());
        }
    }
}
```

Output : →

```java
main-------false
Reference Handler-------true
Finalizer-------true
Signal Dispatcher-------true
Attach Listener-------true
Common-Cleaner-------true
Monitor Ctrl-Break-------true
Notification Thread-------true
```
# Java.util.concurrent.locks.package

## Problems with Traditional  Synchronized Key Word

- **Lack of Flexibility:** With `synchronized`, you can't attempt to acquire a lock without actually blocking the thread. This means you can't check if a lock is available before waiting.
- **Indefinite Waiting:** There's no way to specify a maximum wait time for a thread trying to acquire a lock. This can lead to a thread waiting indefinitely, potentially causing deadlocks.
- **Uncontrolled Lock Release:** When a thread releases a lock acquired with `synchronized`, there's no control over which waiting thread gets it next. This can be unpredictable and make debugging concurrency issues harder.
- **Limited Scope:** You can only use `synchronized` at the method level, which can be too coarse-grained for some scenarios. You might need finer control over specific code blocks within a method.
- **No Waiting Thread Information:** There's no built-in API to list all threads waiting for a particular lock acquired with `synchronized`.



## Lock (Interface)

- Similar to the implicit lock acquired by a thread in synchronized methods or blocks.
- Provides more extensive operations than traditional implicit locks.

### **Important Methods of Lock Interface**:

- **`lock()`**: Locks the lock object, similar to the behavior of the synchronized keyword. Waits if the lock is already acquired by another thread.
- **`tryLock()`**: Attempts to acquire the lock if it's available. Returns true if successful, false otherwise. Does not block the thread.
- **`tryLock(long time, TimeUnit unit)`**: Attempts to acquire the lock, waiting for the specified amount of time if it's unavailable.
- **`lockInterruptibly()`**: Acquires the lock unless the current thread is interrupted. Returns immediately if successful, otherwise waits until the lock is available.
- **`unlock()`**: Releases the lock. The current thread must be the owner of the lock, otherwise, it throws **`IllegalMonitorStateException`**.

## **ReentrantLock Class**:

- Implements the **`Lock`** interface and is a direct subclass of **`Object`**.
- Reentrant: A thread can acquire the same lock multiple times without issues.
- Internally, it maintains a count of lock acquisitions by threads. Each call to **`lock()`** increments this count, and each call to **`unlock()`** decrements it. The lock is released when the count reaches zero.
- `ReentrantLock` can be configured to be fair or non-fair. With fairness enabled, threads waiting for the lock acquire it in the order they requested it, reducing starvation.

Using **`ReentrantLock`** provides more flexibility and control over locking compared to the synchronized keyword, especially with its additional methods like **`tryLock()`** and **`tryLock(long time, TimeUnit unit)`** which offer non-blocking and timed locking capabilities.

### Constructors :

1. `ReentrantLock rl = new ReentrantLock();` - This creates a new `ReentrantLock` instance with the default fairness policy, which is **non-fair**.
2. `ReentrantLock rl = new ReentrantLock(boolean fairness);` - This allows you to specify the fairness behavior during lock creation.
   - `fairness = true` - Creates a fair `ReentrantLock`. Threads waiting for the lock will acquire it in the order they requested it (First-In-First-Out).
   - `fairness = false` (default) - Creates a non-fair `ReentrantLock`. There's no guarantee about which waiting thread acquires the lock once it's available.

**Equivalent Constructors:**

- `ReentrantLock rl = new ReentrantLock();` (default non-fair)
- `ReentrantLock rl = new ReentrantLock(false);` (explicitly non-fair)

These two lines are equivalent because both create a non-fair `ReentrantLock` instance. If you don't specify the fairness parameter, it defaults to non-fair behavior.

**Key Points:**

- Use fair locks when the order of thread acquisition matters and preventing starvation is crucial.
- Non-fair locks can sometimes offer slightly better performance, but fairness guarantees are lost.
- Choose the appropriate fairness policy based on your specific concurrency requirements.

## **Important Methods of ReentrantLock**

1. **`void lock();`**
   - Acquires the lock.
2. **`boolean tryLock();`**
   - Attempts to acquire the lock if it is available.
   - Returns true if the lock is acquired, false otherwise.
3. **`boolean tryLock(long time, TimeUnit unit);`**
   - Attempts to acquire the lock, waiting up to the specified wait time if necessary.
   - Returns true if the lock is acquired, false if the wait time elapses before the lock is acquired.
4. **`void lockInterruptibly();`**
   - Acquires the lock unless the current thread is interrupted.
   - If the lock is not available, the current thread waits until the lock is available or it is interrupted.
5. **`void unlock();`**
   - Releases the lock.
   - If the current thread is not the owner of the lock, a **`RuntimeException`** (IllegalMonitorStateException) is thrown.
6. **`int getHoldCount();`**
   - Returns the number of holds on this lock by the current thread.
7. **`boolean isHeldByCurrentThread();`**
   - Returns true if and only if the lock is held by the current thread.
8. **`int getQueueLength();`**
   - Returns the number of threads waiting for the lock.
9. **`Collection<Thread> getQueuedThreads();`**
   - Returns a collection containing thread objects which are waiting to acquire the lock.
10. **`boolean hasQueuedThreads();`**
   - Returns true if any thread is waiting to acquire the lock.
11. **`boolean isLocked();`**
   - Returns true if the lock is held by any thread.
12. **`boolean isFair();`**
   - Returns true if the lock's fairness policy is set to true.
13. **`Thread getOwner();`**
   - Returns the thread that currently owns the lock.

### **Example Usage**

```java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock lock = new ReentrantLock();

    public void performAction() {
        lock.lock();
        try {
            // critical section code
            System.out.println("Lock is held by: " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();

        Runnable task = () -> {
            demo.performAction();
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
```

In this example, **`ReentrantLockDemo`** uses a **`ReentrantLock`** to ensure that the critical section code in **`performAction()`** is executed by one thread at a time. The **`lock.lock()`** call acquires the lock, and **`lock.unlock()`** releases it in the finally block to ensure the lock is always released even if an exception occurs.

Code demonstrates the use of **`ReentrantLock`** in Java to ensure thread-safe execution of the **`wish`** method in the **`Display`** class. Below is a clean and formatted version of the code with comments explaining each section:

```java
import java.util.concurrent.locks.ReentrantLock;

class Display {
    // Create a ReentrantLock with fair lock acquisition policy
    ReentrantLock l = new ReentrantLock(true);

    public void wish(String name) {
        l.lock(); // Acquire the lock
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Good Morning");
                try {
                    Thread.sleep(2000); // Sleep for 2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name);
            }
        } finally {
            l.unlock(); // Release the lock in the finally block to ensure it is always released
        }
    }
}

class MyThread extends Thread {
    Display d;
    String name;

    MyThread(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        d.wish(name);
    }
}

public class ReentrantLockDemo3 {
    public static void main(String[] args) {
        Display d = new Display();
        MyThread t1 = new MyThread(d, "Dhoni");
        MyThread t2 = new MyThread(d, "Yuva Raj");
        MyThread t3 = new MyThread(d, "Virat Kohli");

        // Start the threads
        t1.start();
        t2.start();
        t3.start();
    }
}

```

# Thread Pools

Creating a new thread for each task can lead to performance and memory issues. To address these problems, Java provides the concept of a Thread Pool.

### Overview

- **Thread Pool:** A collection of pre-created threads ready to execute tasks.
- **Introduced in Java 1.5:** The Thread Pool Framework, also known as the Executor Framework, was introduced to efficiently manage thread pools.
- Thread Pool Framework is Also Known as Executor Framework.

### Creating a Thread Pool

To create a thread pool, you can use the `Executors` class. For example:

```java
ExecutorService service = Executors.newFixedThreadPool(3); // Create a thread pool with 3 thread
```

### Submitting a Job to the Thread Pool

To submit a `Runnable` job to the thread pool, use the `submit()` method:

```java
Runnable job = () -> {
    // Job implementation
};
service.submit(job);

```

### Shutting Down the ExecutorService

To properly shut down the `ExecutorService`, use the `shutdown()` method:

```java
service.shutdown();
```

### Example Code :→

```java
package MultiThreading_Enhancements;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Printjob implements Runnable{

    String name;

    Printjob(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(name + "...Job Started by Thread : " +
                Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        }catch (InterruptedException ignored){

        }
        System.out.println(name+ "...Job Completed by Thread :"+
                Thread.currentThread().getName());
    }
}

class ExecutorDemo
{

    public static void main(String[] args)
    {
        Printjob [] jobs = {new Printjob("Durga"),
                            new Printjob("Ravi"),
                            new Printjob("Shiva"),
                            new Printjob("Suresh"),
                            new Printjob("Anil")};

        ExecutorService service = Executors.newFixedThreadPool(3);
        for(Printjob job : jobs)
        {
            service.submit(job);
        }
        service.shutdown();
    }

}
```

Output :→

```java
Ravi...Job Started by Thread : pool-1-thread-2
Durga...Job Started by Thread : pool-1-thread-1
Shiva...Job Started by Thread : pool-1-thread-3
Durga...Job Completed by Thread :pool-1-thread-1
Shiva...Job Completed by Thread :pool-1-thread-3
Ravi...Job Completed by Thread :pool-1-thread-2
Anil...Job Started by Thread : pool-1-thread-3
Suresh...Job Started by Thread : pool-1-thread-1
Suresh...Job Completed by Thread :pool-1-thread-1
Anil...Job Completed by Thread :pool-1-thread-3
```

# Callable and Future:

### Runnable vs. Callable:

- **Runnable**:
   - Defines a task to be executed by a thread.
   - Does not return a result.
   - Does not throw checked exceptions.
   - Method signature: `public void run()`
- **Callable**:
   - Defines a task that can return a result and throw exceptions.
   - Returns a result of type `Object` (or a specific type with generics).
   - Can throw checked exceptions.
   - Method signature: `public T call() throws Exception`

### Future:

- **Future Interface**:
   - Represents the result of an asynchronous computation.
   - Provides methods to:
      - Check if the computation is complete (`isDone()`).
      - Cancel the computation (`cancel(boolean mayInterruptIfRunning)`).
      - Retrieve the result (`get()`), which blocks until the result is available.
      - Check if the task was cancelled (`isCancelled()`).

### Example Code:

Here's an example that demonstrates using `Callable` and `Future` to perform asynchronous tasks and retrieve their results:

```java
javaCopy code
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<Integer> {
    int num;

    MyCallable(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= num; i++) {
            sum += i;
        }
        return sum;
    }
}

public class CallableFutureDemo {
    public static void main(String[] args) {
        // Array of Callable tasks
        MyCallable[] jobs = {
            new MyCallable(10),
            new MyCallable(20),
            new MyCallable(30),
            new MyCallable(40),
            new MyCallable(50),
            new MyCallable(60)
        };

        // Create a thread pool with 3 threads
        ExecutorService service = Executors.newFixedThreadPool(3);

        // Array to hold Future objects
        Future<Integer>[] results = new Future[jobs.length];

        // Submit Callable tasks to ExecutorService
        for (int i = 0; i < jobs.length; i++) {
            results[i] = service.submit(jobs[i]);
        }

        // Retrieve and print results
        for (int i = 0; i < jobs.length; i++) {
            try {
                // Future.get() waits for the computation to complete and retrieves the result
                System.out.println("Sum of first " + jobs[i].num + " numbers is: " + results[i].get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Shutdown the ExecutorService
        service.shutdown();
    }
}

```

### Explanation:

1. **Callable Implementation**:
   - `MyCallable` implements `Callable<Integer>`, allowing it to return an `Integer` result.
   - The `call` method computes the sum of the first `num` integers.
2. **ExecutorService**:
   - A fixed thread pool of 3 threads is created using `Executors.newFixedThreadPool(3)`.
3. **Submitting Tasks**:
   - Instances of `MyCallable` are submitted to the ExecutorService using `submit()`, which returns a `Future<Integer>` for each task.
4. **Retrieving Results**:
   - The `get()` method of each `Future` object is called to retrieve the result of the computation. This method blocks until the result is available.
5. **Graceful Shutdown**:
   - `service.shutdown()` is called to gracefully shut down the ExecutorService, ensuring all submitted tasks complete before the program terminates.

This approach allows tasks to execute asynchronously, with the ability to retrieve results and handle exceptions properly.

# Questions : →

1. What Is Multi-Tasking?
2. What Is Multi-Threading and Explain Its Application Areas?
3. What Are the Advantages of Multi-Threading?
4. When Compared with C++, What Is the Advantage of Java with Respect to Multi-Threading?
5. In How Many Ways Can We Define a Thread?
6. Between Extending Thread and Implementing Runnable, Which Approach Is Recommended?
7. What Is the Difference Between `t.start()` and `t.run()`?
8. Explain the Thread Scheduler.
9. If We Do Not Override `run()`, What Will Happen?
10. Is Overloading of `run()` Possible?
11. Is It Possible to Override `start()` and What Will Happen?
12. Explain the Life Cycle of a Thread.
13. What Is the Importance of the Thread Class `start()` Method?
14. After Starting a Thread, If We Try to Restart the Same Thread Once Again, What Will Happen?
15. Explain the Thread Class Constructors.
16. How to Get and Set the Name of a Thread?
17. Who Uses Thread Priorities?
18. What Is the Default Priority for the Main Thread?
19. Once We Create a New Thread, What Is Its Priority?
20. How to Get the Priority from a Thread and Set the Priority for a Thread?
21. If We Try to Set the Priority of a Thread as 100, What Will Happen?
22. If Two Threads Have Different Priorities, Which Thread Will Get the Chance First for Execution?
23. If Two Threads Have the Same Priority, Which Thread Will Get the Chance First for Execution?
24. How Can We Prevent a Thread from Execution?
25. What Is `yield()` and Explain Its Purpose?
26. Is `join()` Overloaded?
27. What Is the Purpose of `sleep()`?
28. What Is the `synchronized` Keyword? Explain Its Advantages and Disadvantages.
29. What Is Object Lock and When Is It Required?
30. What Is a Class Level Lock and When Is It Required?
31. While a Thread Is Executing Any Synchronized Method on a Given Object, Is It Possible for Another Thread to Execute the Remaining Synchronized Methods on the Same Object Simultaneously?
32. What Is the Difference Between a Synchronized Method and a Static Synchronized Method?
33. What Are the Advantages of a Synchronized Block Over a Synchronized Method?
34. What Is a Synchronized Statement?
35. How Do Two Threads Communicate with Each Other?
36. In Which Class Are `wait()`, `notify()`, and `notifyAll()` Available?
37. Why Are `wait()`, `notify()`, and `notifyAll()` Methods Defined in the Object Class Instead of the Thread Class?
38. Without Having the Lock, Is It Possible to Call `wait()`?
39. If a Waiting Thread Gets a Notification, Which State Will It Enter Into?
40. In Which Methods Can a Thread Release a Lock?
41. Explain `wait()`, `notify()`, and `notifyAll()`.
42. What Is the Difference Between `notify()` and `notifyAll()`?
43. Once a Thread Gives a Notification, Which Waiting Thread Will Get a Chance?
44. How Can a Thread Interrupt Another Thread?
45. What Is Deadlock? Is It Possible to Resolve a Deadlock Situation?
46. Which Keyword Can Cause a Deadlock Situation?
47. How Can We Stop a Thread Explicitly?
48. Explain `suspend()` and `resume()`.
49. What Is Starvation and Explain the Difference Between Deadlock and Starvation?
50. What Is a Race Condition?
51. What Is a Daemon Thread? Give an Example and the Purpose of a Daemon Thread.
52. How Can We Check the Daemon Nature of a Thread? Is It Possible to Change the Daemon Nature of a Thread? Is the Main Thread a Daemon or Non-Daemon?
53. Explain ThreadGroup.
54. What Is ThreadLocal?
