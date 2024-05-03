# Introduction

## Exception

**Exceptions: Disruptions in the Program Flow**

- An exception is an unexpected event that disturbs normal flow of the program. It's like hitting a bump in the road while driving. These can be things like trying to divide by zero, opening a non-existent file, or running out of memory.

**Exception Handling: The Art of the Workaround**

- Exception handling is the strategy for dealing with these unexpected bumps. It's not about fixing the flat tire (the exception itself), but rather finding a way to navigate around it and keep the car moving (keep the program running).
- Exception handling doesn't mean repairing an exception. We have to define an alternative way to continue the rest of the program normally.

**Why Exception Handling is Important?**

- Without exception handling, your program might crash abruptly when it encounters an exception. This can be frustrating for users and make your program seem unreliable.
- Exception handling allows you to provide a more graceful exit. You can catch the exception, display a user-friendly message explaining the issue, and maybe even offer an alternative path for the program to continue.

Example, if a program needs to read data from a remote file located in London but encounters a FileNotFoundException, it should handle the exception by providing an alternative approach, such as using a local file, to continue execution.

```java

try {
    // read data from the London file
} catch (FileNotFoundException e) {
    // raed data from a local file
    // and continue the program's execution normally
}

```

## Runtime stack mechanism

For every thread, the JVM creates a separate stack at the time of thread creation. All method calls performed by that thread are stored in that stack. Each entry in the stack is called an "activation record" or "stack frame". After completing every method call, the JVM removes the corresponding entry from the stack. After completing all method calls, the JVM destroys the empty stack and terminates the program normally.

Example:

```java

class Test {
    public static void main(String[] args) {
        doStuff();
    }

    public static void doStuff() {
        doMoreStuff();
    }

    public static void doMoreStuff() {
        System.out.println("Hello");
    }
}

```

Output:

```java
Hello
```

![t1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/5bf85a3f-6977-4043-af98-412eecb99caa)


## Default Exception Handling

1. When an exception occurs within a method, the method creates an Exception object with the following information:
  - Name of the exception.
  - Description of the exception.
  - Location of the exception (StackTrace)
2. The method passes this Exception object to the Java Virtual Machine (JVM).
3. The JVM checks if the method contains any exception handling code. If not, it terminates the method abnormally and removes its entry from the stack.
4. The JVM then looks at the caller method to see if it contains exception handling code. If not, it terminates that method too and continues up the call stack until it finds exception handling code or until it reaches the main method.
5. If the main method also lacks exception handling code, the JVM terminates it as well.
6. Finally, the JVM hands over the responsibility of exception handling to the default exception handler.
7. The default exception handler prints the exception information to the console in a specific format and terminates the program abnormally.

```java
Exception in thread "xxx(main)" Name of exception: description 
Location of exception (stack trace) 
```

Example :

```java
class Test {
    public static void main(String[] args) {
        doStuff();
    }

    public static void doStuff() {
        doMoreStuff();
    }

    public static void doMoreStuff() {
        System.out.println(10 / 0);
    }
}

```

output :

```java
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at Test.doMoreStuff(Test.java:10)
    at Test.doStuff(Test.java:7)
    at Test.main(Test.java:4)
```

# Exception Hierarchy :

![t2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/84724293-fc49-4a99-995b-99a0af0b1c6d)


Throwable acts as the root for the exception hierarchy. The Throwable class contains the following two child classes.

![t3](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/f48a06ae-a9c9-4ffc-b2f1-d168c31ce4e0)


1. Exception:
  - Most cases of exceptions are caused by our program and are recoverable.
  - Example: If a **`FileNotFoundException`** occurs, we can use a local file and continue the rest of the program execution normally.
2. Error:
  - Most cases of errors are not caused by our program; they are due to a lack of system resources and are non-recoverable.
  - Example: If an **`OutOfMemoryError`** occurs, as a programmer, we can't do anything; the program will be terminated abnormally. It's the responsibility of the System Admin or Server Admin to raise/increase heap memory.

## Checked vs Unchecked Exceptions:

- Checked exceptions are those that the compiler checks whether the programmer handles or not.
- These exceptions are typically recoverable and are meant to ensure smooth execution of the program at runtime.
- Examples include:
  1. HallTicketMissingException
  2. PenNotWorkingException
  3. FileNotFoundException

**Unchecked Exceptions:**

- Unchecked exceptions are not checked by the compiler for handling.
- These exceptions can occur at runtime without being explicitly handled by the programmer.
- Examples include:
  1. BombBlastException
  2. ArithmeticException
  3. NullPointerException
- RuntimeException and its child classes, as well as Error and its child classes, are considered unchecked exceptions.

Note:

- RuntimeException and its child classes, Error and its child classes are unchecked exceptions, while all the remaining exceptions are considered as checked exceptions.
- Whether an exception is checked or unchecked, it should occur at runtime only, and there is no chance of occurring any exception at compile time.

## **Fully Checked vs Partially Checked Exceptions :**

**Fully Checked Exceptions: A Consistent Family**

- A fully checked exception is a checked exception where all its subclasses are also checked exceptions. This means the compiler enforces handling for the entire family of exceptions.
- Examples of fully checked exceptions include:
  - `IOException`: Ensures handling for all I/O-related issues (like `FileNotFoundException`).
  - `InterruptedException`: Requires handling for thread interruption scenarios.

**Partially Checked Exceptions: A Mixed Bag**

- A partially checked exception is a checked exception where some of its subclasses are unchecked exceptions. This creates a situation where the compiler might not enforce handling for all potential exceptions within the hierarchy.
- In Java, there are only two partially checked exceptions:
  - `Throwable`: The root of the exception hierarchy, but most of its subclasses (like `RuntimeException`) are unchecked.
  - `Exception`: A broader category, but it includes unchecked exceptions like `NullPointerException`.

**Why Partially Checked Exceptions Exist**

- The design choice for partially checked exceptions likely stems from the nature of certain exceptions. For example, `RuntimeException` and its children often represent unexpected runtime errors that might not be easily anticipated or prevented during development. Enforcing handling for all of them might be overly restrictive.


![t4](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/b9da02d3-c420-4fb7-8022-5588e41624dd)

# Customized Exception Handling by using try-catch :

Handling exceptions is highly recommended in programming. In our program, the code that may raise an exception is referred to as risky code. It's essential to place risky code inside a try block and the corresponding handling code inside a catch block.

Example:

```java
try {
    // Risky code
} catch (Exception e) {
    // Handling code
}
```

This structure ensures that if an exception occurs during the execution of the risky code, it is caught and handled appropriately by the corresponding catch block.

Code Example : Without Try Catch

```java
 public static void main(String [] args){
        System.out.println("Statement 1");
        System.out.println(10/0);
        System.out.println("Statement 3");
    }
```

Output :→

```java
Statement 1
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at ExceptionHandling.WithoutTryCatch1.main(WithoutTryCatch1.java:7)

```

Code Example : With Try Catch

```java
 public static void main(String [] args){
        System.out.println("Statement 1");
        try{
            System.out.println(10/0);
        }catch (ArithmeticException e){
            System.out.println(10/2);
        }
        System.out.println("Statement 3");
    }
```

Output :→

```java
Statement 1
5
Statement 3
```

## Control Flow in try catch

```java
try{
	statement1;
	statement2;
	statement3;
}
catch(X e) {
	statement4;
}
	statement5;
```

1. Case 1: If there is no exception:
  - statement1, statement2, statement3 execute normally.
  - statement5 executes.
  - Normal termination.
2. Case 2: If an exception is raised at statement 2 and the corresponding catch block matches:
  - statement1 executes.
  - statement4 executes (inside catch block).
  - statement5 executes.
  - Normal termination.
3. Case 3: If an exception is raised at statement 2 but the corresponding catch block does not match:
  - statement1 executes.
  - Abnormal termination occurs after statement1.
4. Case 4: If an exception is raised at statement 4 or statement 5:
  - Abnormal termination of the program always occurs.

Notes:

1. If an exception is raised within the try block, subsequent statements within the try block won't be executed, even if the exception is handled. Therefore, it's crucial to only include risky code within the try block and keep its length as short as possible.
2. If any statement raises an exception outside of any try block, it results in abnormal termination of the program.
3. Exceptions can also be raised inside catch and finally blocks, in addition to the try block.

# Various Methods to print Exception Information :

| Method | Description |
| --- | --- |
| printStackTrace() | This method prints exception information in the following format.
Name of the exception: description of exception
Stack trace |
| toString() | This method prints exception information in the following format.
Name of the exception: description of exception.
|
| getMessage() | This method returns only description of the exception.
Description |

Code Example :

```java
 public static void main(String [] args){
        try{
            System.out.println(10/0);
        }catch (ArithmeticException e){
            e.printStackTrace();
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }
```

Output :

```java
java.lang.ArithmeticException: / by zero
	at ExceptionHandling.VariousMethodToPrintException.main(VariousMethodToPrintException.java:7)
java.lang.ArithmeticException: / by zero
/ by zero
```

Note: Default exception handler internally uses `printStackTrace()` method to print exception information to the console.

# Try with multiple catch blocks:

In Java, using multiple catch blocks is possible and recommended, as the way of handling exceptions can vary from exception to exception. It's recommended to have a separate catch block for each type of exception to handle them appropriately.

Here's an example of using multiple catch blocks:

```java
try {
    // Code that may throw exceptions
} catch (FileNotFoundException e) {
    // Handle FileNotFoundException
    // Use local file
} catch (ArithmeticException e) {
    // Handle ArithmeticException
    // Perform arithmetic operations
} catch (SQLException e) {
    // Handle SQLException
    // Don't use Oracle DB, use MySQL DB
} catch (Exception e) {
    // Default handler for other exceptions
    // Handle all other exceptions
}

```

1. **Order of Catch Blocks:**
  - The order of catch blocks is crucial.
  - Catch blocks should be ordered from the most specific (child) exception type to the most general (parent) exception type.
  - If catch blocks are ordered from parent to child, it results in a compile-time error, as it leads to unreachable catch blocks for child exceptions, then we will get Compile time error saying  `**exception xxx has already been caught**`

Example Code:

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println(10/0);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }
}

```

Output : →

```java
Test.java:9:11
java: exception java.lang.ArithmeticException has already been caught
```

Correct Way

```java
    public static void main(String[] args) {
        try {
            System.out.println(10 / 0);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

This will compile successfully.

# Finally Block

1. **Avoiding Cleanup Code in Try and Catch Blocks:**
  - It's not recommended to place cleanup code inside the try block because there's no guarantee that every statement within the try block will execute.
  - Similarly, placing cleanup code inside the catch block is discouraged because if no exception occurs, the catch block won't execute.
2. **Role of Finally Block:**
  - The finally block is used to maintain cleanup code.
  - Its main objective is to ensure that certain code executes regardless of whether an exception is raised, caught, or not raised at all.
3. **Specialty of Finally Block:**
  - The finally block is executed always, irrespective of whether an exception is raised or not, whether it's caught or not caught.
  - This makes it an ideal place to include cleanup code that needs to execute under all circumstances.

```java
 try {
      // Risky code
  } catch (Exception e) {
      // Handling code
  } finally {
      // Cleanup code
  }
```

## Case 1: If there is no exception

Code :

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try block executed");
        } catch (ArithmeticException e) {
            System.out.println("catch block executed");
        } finally {
            System.out.println("finally block executed");
        }
    }
}

```

Output : →

```java
try block executed
Finally block executed

```

## Case-2: If an exception raised but the corresponding catch block matched

Code :

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try block executed");
            System.out.println(10/0); // This line will raise an ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("catch block executed");
        } finally {
            System.out.println("finally block executed");
        }
    }
}

```

Example :

```java
Try block executed
Catch block executed
Finally block executed
```

## Case-3: If an exception raised but the corresponding catch block not matched:

Code :

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try block executed");
            System.out.println(10/0); // This line will raise an ArithmeticException
        } catch (NullPointerException e) {
            System.out.println("catch block executed");
        } finally {
            // This block will be executed even if the corresponding catch block is not matched
            System.out.println("finally block executed");
        }
    }
}

```

Output :

```java
Try block executed
Finally block executed
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at Test.main(Test.java:7)

```

## Return vs Finally:

Even though a return statement is present in a try or catch block, the finally block will always be executed first. After the execution of the finally block, then the return statement will be considered. In other words, the finally block dominates the return statement.

Code :

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try block executed");
            return;
        } catch (ArithmeticException e) {
            System.out.println("catch block executed");
        } finally {
            System.out.println("finally block executed");
        }
    }
}

```

Output :

```java
Try block executed
Finally block executed
```

If a return statement is present in both the try, catch, and finally blocks, then the return statement within the finally block will be considered. Here's an example illustrating this behavior:

```java

class Test {
    public static void main(String[] args) {
        System.out.println(m1());
    }

    public static int m1() {
        try {
            System.out.println(10 / 0);
            return 777;
        } catch (ArithmeticException e) {
            return 888;
        } finally {
            return 999;
        }
    }
}
```

Output:

```
999
```

## finally vs System.exit(0);

There's only one situation where the finally block won't be executed, and that's when we use the System.exit(0) method. When System.exit(0) is called, the JVM itself will shut down, and in this case, the finally block won't be executed. Essentially, System.exit(0) dominates over the finally block.

Here's an example illustrating this behavior:

```java

class Test {
    public static void main(String[] args) {
        try {
            System.out.println("try");
            System.exit(0);
        } catch (ArithmeticException e) {
            System.out.println("catch block executed");
        } finally {
            System.out.println("finally block executed");
        }
    }
}
```

Output:

```java
try
```

Note:

1. The argument passed to System.exit() acts as a status code. Instead of zero, any integer value can be used.
2. Zero typically represents normal termination, while a non-zero value indicates abnormal termination.
3. Internally, this status code is used by the JVM. Whether it's zero or non-zero, there's no change in the result, and the effect is the same regarding the program's termination.

## Difference between final, finally, and finalize:

| Concept | Description |
| --- | --- |
| final | - Modifier applicable for classes, methods, and variables.  - If a class is declared as final, child class creation is not possible.                                                                             - If a method is declared as final, overriding of that method is not possible.                                                      - If a variable is declared as final, reassignment is not possible. |
| finally | - finally Block always associated with try-catch to maintain cleanup code.                                                      - Executed always irrespective of whether an exception is raised or not, and whether it's handled or not. |
| finalize | - Method always invoked by the Garbage Collector just before destroying an object to perform cleanup activities. |

Note:

- **`finally`** block is meant for cleanup activities related to the try block, whereas the **`finalize()`** method is meant for cleanup activities related to the object.
- **`finally`** block is recommended over the **`finalize()`** method to maintain cleanup code because we can't expect the exact behavior of the Garbage Collector.

## Control flow in try catch finally:

```java
try {
    Stmt 1;
    Stmt 2;
    Stmt 3;
} catch (Exception e) {
    Stmt 4;
} finally {
    Stmt 5;
}
Stmt 6;

```

- **Case 1:** If there is no exception.
  - Sequence: 1, 2, 3, 5, 6
  - Normal termination.
- **Case 2:** If an exception raised at statement 2 and corresponding catch block matched.
  - Sequence: 1, 4, 5, 6
  - Normal termination.
- **Case 3:** If an exception raised at statement 2 and corresponding catch block is not matched.
  - Sequence: 1, 5
  - Abnormal termination.
- **Case 4:** If an exception raised at statement 4 then it's always abnormal termination but before the finally block will be executed.
  - Sequence: Stmt 4, Stmt 5
  - Abnormal termination.
- **Case 5:** If an exception raised at statement 5 or statement 6 it's always abnormal termination.
  - Sequence: Stmt 5, Stmt 6
  - Abnormal termination.

## Control flow in Nested try-catch-finally

```java
try {
    stmt-1;
    stmt-2;
    stmt-3;
    try {
        stmt-4;
        stmt-5;
        stmt-6;
    } catch (X e) {
        stmt-7;
    } finally {
        stmt-8;
    }
    stmt-9;
} catch (Y e) {
    stmt-10;
} finally {
    stmt-11;
}
stmt-12;

```

- **Case 1:** If there is no exception.
  - Sequence: 1, 2, 3, 4, 5, 6, 8, 9, 11, 12
  - Normal termination.
- **Case 2:** If an exception raised at statement 2 and corresponding catch block matched.
  - Sequence: 1, 10, 11, 12
  - Normal termination.
- **Case 3:** If an exception raised at statement 2 and corresponding catch block is not matched.
  - Sequence: 1, 11
  - Abnormal termination.
- **Case 4:** If an exception raised at statement 5 and corresponding inner catch has matched.
  - Sequence: 1, 2, 3, 4, 7, 8, 9, 11, 12
  - Normal termination.
- **Case 5:** If an exception raised at statement 5 and inner catch has not matched but outer catch block has matched.
  - Sequence: 1, 2, 3, 4, 8, 10, 11, 12
  - Normal termination.
- **Case 6:** If an exception raised at statement 5 and both inner and outer catch blocks are not matched.
  - Sequence: 1, 2, 3, 4, 8, 11
  - Abnormal termination.
- **Case 7:** If an exception raised at statement 7 and the corresponding catch block matched.
  - Sequence: 1, 2, 3, 4, 5, 6, 8, 10, 11, 12
  - Normal termination.
- **Case 8:** If an exception raised at statement 7 and the corresponding catch block not matched.
  - Sequence: 1, 2, 3, 4, 5, 6, 8, 11
  - Abnormal termination.
- **Case 9:** If an exception raised at statement 8 and the corresponding catch block has matched.
  - Sequence: 1, 2, 3, 4, 5, 6, 7, 10, 11, 12
  - Normal termination.
- **Case 10:** If an exception raised at statement 8 and the corresponding catch block not matched.
  - Sequence: 1, 2, 3, 4, 5, 6, 7, 11
  - Abnormal termination.
- **Case 11:** If an exception raised at statement 9 and corresponding catch block matched.
  - Sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12
  - Normal termination.
- **Case 12:** If an exception raised at statement 9 and corresponding catch block not matched.
  - Sequence: 1, 2, 3, 4, 5, 6, 7, 8, 11
  - Abnormal termination.
- **Case 13:** If an exception raised at statement 10 is always abnormal termination, but before that finally block 11 will be executed.
  - Sequence: Stmt 10, Stmt 11
  - Abnormal termination.
- **Case 14:** If an exception raised at statement 11 or 12 is always abnormal termination.
  - Sequence: Stmt 11 or Stmt 12
  - Abnormal termination.

**Note:**

1. If we do not enter the try block, then the finally block won't be executed. Once we enter the try block without executing the finally block, we can't exit it.
2. Nested try-catch blocks are possible, allowing for a try-catch inside another try block.
3. Inner try-catch blocks can handle the most specific exceptions, while generalized exceptions can be handled by outer try-catch blocks.

**Example:**

```java
class Test {
    public static void main(String[] args) {
        try {
            System.out.println(10/0);
        } catch(ArithmeticException e) {
            System.out.println(10/0);
        } finally {
            String s = null;
            System.out.println(s.length());
        }
    }
}

```

**Output:**

```java
RE: NullPointerException
```

Note: Default exception handler can handle only one exception at a time and that is the most recently raised exception.

## Various possible combinations of try catch finally:

1. Whenever we write a try block, it's mandatory to include either a catch or a finally block. A try block without catch or finally is invalid.
2. When writing a catch block, it's mandatory to include a corresponding try block. A catch block without a try is invalid.
3. When writing a finally block, it's mandatory to include a corresponding try block. A finally block without a try is invalid.
4. The order of try, catch, and finally blocks is important in try-catch-finally constructs.
5. Nested try-catch-finally blocks are allowed within try-catch-finally constructs, allowing for nesting of error handling mechanisms.
6. Curly braces **`{}`** are mandatory for enclosing try-catch-finally blocks.

# throw statement

The **`throw`** statement in Java allows for the manual creation and throwing of exceptions. With the **`throw`** keyword, we can explicitly create an exception object and hand it over to the Java Virtual Machine (JVM) for further processing.

```java
public class CustomException extends Exception {
    // Custom exception class
    public CustomException(String message) {
        super(message);
    }
}

public class Test {
    public static void main(String[] args) {
        try {
            // Manually throw an exception
            throw new CustomException("This is a custom exception");
        } catch (CustomException e) {
            // Handle the custom exception
            System.out.println("Custom exception caught: " + e.getMessage());
        }
    }
}
```

Output :→

```java
Custom exception caught: This is a custom exception
```

![t5](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/29f76ab4-e651-4119-8cec-99a4a3faa0c0)


The result of the following two programs is exactly the same:

```java
// Program 1
class Test {
    public static void main(String[] args) {
        System.out.println(10/0);
    }
}
```

```java

// Program 2
class Test {
    public static void main(String[] args) {
        throw new ArithmeticException("/ by zero");
    }
}
```

In both cases, an **`ArithmeticException`** is thrown due to attempting to divide by zero. In the first program, the creation of the **`ArithmeticException`** object and handing it over to the JVM is performed automatically by the **`main()`** method when the exception occurs. In the second program, we explicitly create the **`ArithmeticException`** object and manually hand it over to the JVM using the **`throw`** statement. Despite the difference in how the exception is thrown, the end result is the same - an **`ArithmeticException`** is thrown.

Case 1 : If **`e`** refers to null, then we will get a **`NullPointerException`** when trying to throw it.

```java

class Test3 {
    static ArithmeticException e = new ArithmeticException();
    public static void main(String[] args) {
        throw e;
    }
}
```

Output:

```java
Runtime exception: Exception in thread "main"
java.lang.ArithmeticException

```

```java
class Test3 {
    static ArithmeticException e;
    public static void main(String[] args) {
        throw e;
    }
}
```

Output:

```java

Exception in thread "main" java.lang.NullPointerException
    at Test3.main(Test3.java:5)
```

In the first case, even though **`e`** is initialized with a new **`ArithmeticException()`** instance, since it's declared as static, it's initialized to null first, hence the **`NullPointerException`** is not thrown. However, in the second case, where **`e`** is not initialized, it's initialized to null by default, and hence throwing it leads to a **`NullPointerException`**.

**Case 2:**

After a **`throw`** statement, we can't have any statements directly following it, otherwise, we will get a compile-time error indicating unreachable statement.

```java
class Test3 {
    public static void main(String[] args) {
        System.out.println(10/0);
        System.out.println("hello");
    }
 }
```

Output:

```java

Runtime error: Exception in thread "main" java.lang.ArithmeticException: / by zero
    at Test3.main(Test3.java:4)
```

```java
class Test3 {
    public static void main(String[] args) {
        throw new ArithmeticException("/ by zero");
        System.out.println("hello");
    }
}
```

Output:

```java
Compile-time error.
Test3.java:5: unreachable statement
System.out.println("hello");
```

In the first case, despite the division by zero error, the "hello" statement gets executed. In the second case, however, the "hello" statement is marked as unreachable because it follows the **`throw`** statement, which will unconditionally throw an exception.

**Case 3:**

We can use the **`throw`** keyword only for **`Throwable`** types; otherwise, we will get a compile-time error indicating incompatible types.

```java
class Test3 {
    public static void main(String[] args) {
        throw new Test3();
    }
}
```

Output:

```java

Compile-time error.
Test3.java:4: incompatible types
found : Test3
required: java.lang.Throwable
throw new Test3();
```

```java
class Test3 extends RuntimeException {
    public static void main(String[] args) {
        throw new Test3();
    }
}

```

Output:

```java

Runtime error: Exception in thread "main" Test3
    at Test3.main(Test3.java:4)

```

In the first example, we're attempting to throw an instance of **`Test3`**, which is not a subclass of **`Throwable`**, resulting in a compile-time error. However, in the second example, **`Test3`** extends **`RuntimeException`**, which is a subclass of **`Throwable`**, allowing it to be thrown without a compile-time error.

# Throws statement:

In our program, if there is any chance of a checked exception being raised, then we must handle it either by using a try-catch block or by declaring it with the throws keyword; otherwise, the code won't comile.

**Example:**

```java
import java.io.*;

class Test3 {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter("abc.txt");
         out.println("hello");
      }

```

Output:

```java

Unreported exception java.io.FileNotFoundException; must be caught or declared to be thrown.
```

```java
class Test3 {
    public static void main(String[] args) 
     {
        Thread.sleep(5000);
    }
}

```

Output:

```java

Unreported exception java.lang.InterruptedException; must be caught or declared to be thrown.
```

We can handle this compile-time error by using the following two ways.

**Example:**
By using try-catch:

```java
class Test3 {
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Handle the InterruptedException
        }
    }
}

```

Output:

```java

Compile and running successfully
```

We can use the throws keyword to delegate the responsibility of exception handling to the caller method. Then, the caller method is responsible for handling that exception.

**Example:**

```java
class Test3 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
    }
```

Output:

```java
Compile and running successfully
```

Note :

- Hence the main objective of "throws" keyword is to delegate the responsibility of exception handling to the caller method.
- "throws" keyword required only checked exceptions. Usage of throws for
  unchecked exception there is no use.
- "throws" keyword required only to convince complier. Usage of throws keyword doesn't prevent abnormal termination of the program.

Hence recommended to use try-catch over throws keyword.

Example:

```java
class Test
{
public static void main(String[] args)throws InterruptedException{
doStuff();
}
public static void doStuff()throws InterruptedException{
doMoreStuff();
}
public static void doMoreStuff()throws InterruptedException{
Thread.sleep(5000);
}
}
```

Output :→

```java
Compile and running successfully.
```

In the above program if we are removing at least one throws keyword then the program won't compile.

**Case 1:** We can use the **`throws`** keyword only for **`Throwable`** types; otherwise, we will get a compile-time error indicating incompatible types.

```java
class Test3 {
    public static void main(String[] args) throws Test3 {
       }
    }
```

Output:

```java

Compile-time error:
Test3.java:2: incompatible types
found: Test3
required: java.lang.Throwable
    throws Test3;
           ^
```

Example :

```java
class Test3 extends RuntimeException {
    public static void main(String[] args) throws Test3 {
    }
}
```

Output:

```java

Compile and running successfully.

```

**Case 1: Compile-Time Error**

```java
class Test3 {
  public static void main(String[] args) {
    throw new Exception(); // Unreported exception: Exception
  }
}
```

**Error Explanation:**

The **`main`** method doesn't handle the **`Exception`** that's thrown using **`throw new Exception()`**.
In Java, unchecked exceptions like **`Exception`** (and its subclasses) must be either:

- Caught using a try-catch block within the **`main`** method.
- Declared using **`throws Exception`** in the **`main`** method signature.
  Since you're not doing either in this case, the compiler throws an error because unchecked exceptions can potentially cause unexpected program termination if not handled.

**Case 2: Runtime Error**

```java

class Test3 {
  public static void main(String[] args) {
    throw new Error(); // Throwing an Error
  }
}
```

**Error Explanation:**

You're throwing a new **`Error`** object using **`throw new Error()`**.
**`Error`** is a subclass of **`Throwable`** but not **`Exception`**. It's a separate category of exceptions typically used for serious issues that should not be handled by the application (like **`OutOfMemoryError`**).
Unlike unchecked exceptions, **`Errors`** don't need to be declared using **`throws`** because they are considered unrecoverable.

**Output:**

```php
phpCopy code
Exception in thread "main" java.lang.Error
at Test3.main(Test3.java:3)

```

The program throws a **`java.lang.Error`** at runtime.
While the error message mentions "Exception," it's actually an **`Error`** being thrown.
This is because **`Error`** is still a subclass of **`Throwable`**, which is the root of all exceptions in Java.

**Key Points:**

- Unchecked exceptions (like **`Exception`**) must be caught or declared using **`throws`**.
- **`Error`** is a separate category of exceptions and doesn't need to be declared using **`throws`**.
- Throwing **`Error`** is generally not recommended as it indicates a severe problem that the application might not recover from.

**In summary:**

- Understand the difference between checked and unchecked exceptions.
- Use try-catch blocks or throws declarations to handle unchecked exceptions appropriately.
- Avoid throwing **`Error`** objects unless it represents a truly unrecoverable situation.

Case 4:  Our program, if within the try block there's no possibility of an exception occurring, then we shouldn't include a catch block for that exception. Otherwise, we'll encounter a compile-time error stating "exception XXX is never thrown in the body of the corresponding try statement." However, this rule applies exclusively to fully checked exceptions.

Ex 1: No Possibility of Exception

```java
public class NoExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 + 20; // No chance of an exception here
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught: " + e.getMessage());
        }
    }
}
```

In this example, the try block contains a simple arithmetic operation where no exception can occur. However, a catch block for **`ArithmeticException`** is included, leading to a compile-time error.

Case 2: Exception Handling Included

```java
public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // This will raise an ArithmeticException
            System.out.println("Result: " + result); // This line won't be executed
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught: " + e.getMessage());
        }
    }
}
```

In this example, the try block contains an arithmetic operation where an **`ArithmeticException`** can occur. Hence, a catch block for **`ArithmeticException`** is included, and no compile-time error will be encountered.

# Throw vs Throws keyword

The **`throws`** and **`throw`** statements are both used in Java for exception handling, but they serve different purposes:

**throws Statement:**

- The **`throws`** keyword is used in method declarations to indicate that the method might throw certain types of exceptions.
- It specifies the exceptions that a method can throw, but it doesn't actually throw an exception itself.
- It's used to declare the exceptions that might occur within the method, allowing the caller of the method to handle those exceptions or propagate them further.
- Multiple exceptions can be listed in a **`throws`** clause, separated by commas.
- Syntax:

    ```java
    returnType methodName(parameters) throws ExceptionType1, ExceptionType2, ...
    ```


**throw Statement:**

- The **`throw`** keyword is used to manually throw an exception within a method or a block of code.
- It's used to create and throw a new exception object explicitly.
- It's typically used when a specific condition occurs in the code that warrants raising an exception to indicate an exceptional situation.
- The **`throw`** statement can be used to throw any subclass of **`Throwable`**, including instances of **`Exception`** or **`Error`**, or their subclasses.
- Syntax:

    ```java
    throw throwableInstance;
    ```


In summary, **`throws`** is used in method declarations to declare the types of exceptions that a method can throw, while **`throw`** is used within a method or block to throw a specific exception when certain conditions are met.

# Exception handling keywords summary:

1. **try**: Used to enclose risky code that may throw exceptions.
2. **catch**: Used to handle exceptions that occur within the corresponding try block.
3. **finally**: Used to execute cleanup code that should run regardless of whether an exception is thrown or not.
4. **throw**: Used to manually create and throw an exception object.
5. **throws**: Used in method signatures to indicate that the method may throw certain types of exceptions, delegating the responsibility of exception handling to the caller method.


# Various possible compile time errors in exception handling:

1. Exception XXX has already been caught.
2. Unreported exception XXX must be caught or declared to be thrown.
3. Exception XXX is never thrown in body of corresponding try statement.
4. Try without catch or finally.
5. Catch without try.
6. Finally without try.
7. Incompatible types.

```java
found:Test
requried:java.lang.Throwable;
```

8. Unreachable statement

# Customized Exceptions (User defined Exceptions)

Sometimes we can create our own exception to meet our programming requirements.
Such type of exceptions are called customized exceptions (user defined exceptions).
Example:

1. InSufficientFundsException
2. TooYoungException
3. TooOldException

```java

import java.util.Scanner;

class TooYoungException extends RuntimeException
{
    TooYoungException(String s){
    super(s);
    }
}

class TooOldException extends RuntimeException{
    TooOldException(String s){
        super(s);
    }
}

public class CustomExceptionDemo {

    public static void main(String[]args){
        Scanner scn = new Scanner(System.in);
        int age = scn.nextInt();
        if(age>60){
            throw new TooYoungException("Please wait sometime _ you will get best match");
        }else if(age<18){
            throw new TooOldException("You are late");
        }else{
            System.out.println("You will get match by e-mail");
        }
    }

}

```

In java, It is highly recommended to maintain our customized exceptions as unchecked by extending RuntimeException.

If we will extend exception then checked exception will take place

We can catch any Throwable type including Errors also.

- **Customized Unchecked Exceptions**: If the exception you are creating represents an error that the caller can't reasonably be expected to recover from, or if it represents a programming error, then making it unchecked might be appropriate. Extending RuntimeException for such exceptions allows them to be thrown without being declared in the method signature.
- **Customized Checked Exceptions**: If the exception represents a condition from which the caller can recover, or if the exception is part of the method's contract, then it might be more appropriate to make it a checked exception by extending Exception. This forces callers to handle the exception explicitly.