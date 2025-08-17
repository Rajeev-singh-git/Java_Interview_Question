📘 Multithreading Interview Questions

## Table of Contents

1. [String – Introduction & Core Concepts](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/01_String_Intro_Immutability.md)
2. [String Memory Model – Heap, SCP & Interning](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/02_String_Memory_Model.md)
3. [String Mutability, Performance & Builders – StringBuffer vs StringBuilder](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/String/Concept/03_String_Constructors_Builders.md)

---

### 🔒 Java Synchronization

1. **What is the `synchronized` keyword in Java? What are its advantages and disadvantages?**

2. **What is an object-level lock in Java, and when does a thread need to acquire it?**

3. **What is a class-level lock, and under what circumstances must a thread acquire it?**

4. **How does an object-level lock differ from a class-level lock?**

5. **If a thread is executing a synchronized method on an object, can other threads execute other synchronized methods on the same object concurrently?**

6. **What is a synchronized block in Java, and how is it declared?**

7. **What are the advantages of using a synchronized block over a synchronized method?**

8. **Can a single thread hold multiple locks at the same time in Java?**

9. **What is a synchronized statement, and how is it used in Java?**

---

# Part 2

---

**1.** What is a thread?

**2.* Which thread runs by default in every Java program?  

**3.** What is the default priority of a thread?

**4.** How can you change the priority of a thread?

*5.* Which method is executed by any thread?  

**6.** How can you stop a running thread?

**7.** Explain the two types of multitasking.

**8.** What is the difference between a process and a thread?

**9.** What is a thread scheduler?

**10.** Explain thread synchronization.

**11.** What is the difference between a synchronized block and the synchronized keyword?

**12.** What is thread deadlock? How can you resolve a deadlock situation?

**13.** Which methods are used for thread communication?

**14.** What is the difference between **notify()** and **notifyAll()** methods?

**15.** What is the difference between **sleep()** and **wait()** methods?

**16.** Explain the life cycle of a thread.

**17.** What is a daemon thread?



---

## Multithreading Questions

1. What is **Multitasking**?

2. What is **Multithreading** and what are its application areas?

3. What are the **advantages of Multithreading**?

4. Compared with C++, what advantage does **Java provide for Multithreading**?

5. In how many ways can we **create a Thread** in Java?

6. Between **extending Thread** and **implementing Runnable**, which approach is recommended and why?

7. What is the difference between `t.start()` and `t.run()`?

8. Explain the **Thread Scheduler**.

9. What happens if we **do not override the run() method**?

10. Is **overloading** of `run()` possible?

11. Is it possible to **override start()**? What will happen if we do?

12. Explain the **life cycle of a Thread**.

13. What is the importance of the **start() method** in the Thread class?

14. What happens if we try to **restart the same thread** once it has already started?

15. Explain the **Thread class constructors**.

16. How can we **get and set the name** of a thread?

17. Who uses **Thread priorities**?

18. What is the **default priority** of the main thread?

19. Once a new thread is created, what is its **default priority**?

20. How can we **get and set the priority** of a thread?

21. What happens if we try to **set a thread’s priority to 100**?

22. If two threads have **different priorities**, which one will execute first?

23. If two threads have the **same priority**, which one will execute first?

24. How can we **prevent a thread from executing**?

25. What is `yield()` and what is its purpose?

26. Is `join()` overloaded? Explain.

27. What is the purpose of `sleep()`?

28. What is the `synchronized` keyword? Explain its **advantages and disadvantages**.

29. What is an **Object lock** and when is it required?

30. What is a **Class-level lock** and when is it required?

31. While a thread is executing a **synchronized method** on an object, is it possible for another thread to execute another synchronized method on the **same object** simultaneously?

32. What is the difference between a **synchronized method** and a **static synchronized method**?

33. What are the advantages of a **synchronized block** over a synchronized method?

34. What is a **synchronized statement**?

35. How do **two threads communicate** with each other?

36. In which class are `wait()`, `notify()`, and `notifyAll()` defined?

37. Why are `wait()`, `notify()`, and `notifyAll()` methods defined in the **Object class** instead of the Thread class?

38. Without holding the **lock**, is it possible to call `wait()`?

39. When a waiting thread gets a **notification**, which state does it enter into?

40. In which methods can a thread **release the lock**?

41. Explain `wait()`, `notify()`, and `notifyAll()`.

42. What is the difference between `notify()` and `notifyAll()`?

43. Once a thread gives a **notification**, which waiting thread will be chosen for execution?

44. How can one thread **interrupt** another thread?

45. What is **Deadlock**? Is it possible to resolve a deadlock situation?

46. Which keyword can potentially **cause deadlock**?

47. How can we **stop a thread explicitly**?

48. Explain the purpose of **suspend()** and **resume()**.

49. What is **Starvation**? How is it different from Deadlock?

50. What is a **Race Condition**?

51. What is a **Daemon Thread**? Give an example and explain its purpose.

52. How can we check whether a thread is a **Daemon**? Is it possible to change a thread’s daemon nature? Is the **main thread** a daemon or non-daemon?

53. Explain **ThreadGroup**.

54. What is **ThreadLocal**?
