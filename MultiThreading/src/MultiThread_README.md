# Multi_Threading

# Multi Tasking

Executing several tasks simultaneously is the concept of multitasking.
There are two types of multitasking's.

1. Process based multitasking.
2. Thread based multitasking.

## Process based multitasking

Executing several tasks simultaneously where each task is a separate independent process such type of multitasking is called process based multitasking.

Example :

- While typing a java program in the editor we can able to listen mp3 audio songs at the same time we can download a file from the net all these tasks are independent of each other and executing simultaneously and hence it is is Process based multitasking.
- This type of multitasking is best suitable at "os level”.

## Thread based multitasking:

Executing several tasks simultaneously where each task is a separate independent part of the same program, is called Thread based multitasking.
And each independent part is called a "Thread".

1.  This type of multitasking is best suitable for "programatic level".
2. When compared with "C++", developing multithreading examples is very easy in java because java provides in built support for multithreading through a rich API (Thread, Runnable, ThreadGroup, ThreadLocal...etc).
3. In multithreading on 10% of the work the programmer is required to do and 90% of the work will be down by java API.
4. The main important application areas of multithreading are:
- To implement multimedia graphics
- To develop animations.
- To develop video games etc.
- To develop web and application server.
1. Whether it is process based or Thread based the main objective of multitasking is to improve performance of the system by reducing response time.

# The ways to define instantiate and start a new Thread

1. By extending Thread class.  [Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/src/MyThread.java)
2. By implementing Runnable interface. [Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/MultiThreading/src/MyThread.java)

## Case 1: Thread Scheduler

- If multiple Threads are waiting to execute then which Thread will execute 1st is  decided by "Thread Scheduler" which is part of JVM.
- Which algorithm or behavior followed by Thread Scheduler we can't expect
  exactly it is the JVM vendor dependent hence in multithreading examples we can't expect exact execution order and exact output

## Case 2: Difference between t.start() and t.run() methods.

- In the case of t.start() a new Thread will be created which is responsible for the execution of run() method.
- But in the case of t.run() no new Thread will be created and run() method will be executed just like a normal method by the main Thread.

## Case 3: importance of Thread class start() method.

For every Thread the required mandatory activities like registering the Thread with
Thread Scheduler will takes care by Thread class start() method and programmer is
responsible just to define the job of the Thread inside run() method.
That is start() method acts as best assistant to the programmer.
Example:

```java
start()
{
1. `Register Thread with Thread Scheduler`
2. `All other mandatory low level activities.`
3. `Invoke or calling run() method.
}
```

We can conclude that without executing Thread class start() method there is no chance of starting a new Thread in java. Due to this start() is considered as heart of multithreading.

## Case 4: If we are not overriding run() method

If we are not overriding run() method then Thread class run() method will be executed
which has empty implementation and hence we won't get any output.
Example:

```java
class MyThread extends Thread 
{}
class ThreadDemo
{
public static void main(String[] args)
{
MyThread t=new MyThread();
t.start();
}
}
```

It is highly recommended to override run() method. Otherwise don't go for
multithreading concept.

## Case 5: Overloading of run() method.

We can overload run() method but Thread class start() method always invokes no argument run() method the other overload run() methods we have to call explicitly then only it will be executed just like normal method.

```java
class MyThread extends Thread
{
public void run()
{
System.out.println("no arg method");
}
public void run(int i)
{
System.out.println("int arg method");
}
}
lass ThreadDemo
{
public static void main(String[] args)
{
MyThread t=new MyThread();
t.start();
}
}
Output:
No arg method
```

## Case 6: overriding of start() method:

If we override start() method then our start() method will be executed just like a normal method call and no new Thread will be started.

```java
class MyThread extends Thread
{
public void start()
{
System.out.println("start method");
}
public void run()
{
System.out.println("run method");
}
}
class ThreadDemo
{
public static void main(String[] args)
{
MyThread t=new MyThread();
t.start();
System.out.println("main method");
}
}
Output:
start method
main method
```

Entire output produced by only main Thread.
Note : It is never recommended to override start() method.

## Life Cycle of the Thread

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/e9af192b-f39f-490b-8e65-0e55de46d363/Untitled.png)

- Once we created a Thread object then the Thread is said to be in new state or born state.
- Once we call start() method then the Thread will be entered into Ready or
  Runnable state.
- If Thread Scheduler allocates CPU then the Thread will be entered into running state.
- Once run() method completes then the Thread will entered into dead state.

## IllegalThreadStateException

After starting a Thread we are not allowed to restart the same Thread once again otherwise we will get runtime exception saying "IllegalThreadStateException”.

```java
MyThread t=new MyThread();
t.start();//valid
;;;;;;;;
t.start();//we will get R.E saying: IllegalThreadStateException
```

# Defining a Thread by implementing Runnable interface

We can define a Thread even by implementing Runnable interface also.
Runnable interface present in java.lang.pkg and contains only one method run().
