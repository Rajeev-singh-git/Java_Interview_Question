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
