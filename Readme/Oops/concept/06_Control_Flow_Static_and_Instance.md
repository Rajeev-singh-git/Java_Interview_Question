# Oops

## Table of Contents

1. [Abstraction](#abstraction)
   - [Abstract Classes:](#-1-abstract-classes)
   - [Interface](#2-interface-)
2. [RIWO state](#riwo-state)
3. [Static control flow parent to child relationship :→](#static-control-flow-parent-to-child-relationship-→)
4. [Static block](#static-block)
5. [Instance Control Flow](#instance-control-flow)
6. [Instance control flow in Parent to Child relationship](#instance-control-flow-in-parent-to-child-relationship)
7. [Type Casting :→](#type-casting-→)
   - [Type Casting Syntax](#type-casting-syntax)
   - [Runtime Checking](#runtime-checking)
8. [Cohesion](#cohesion)

---

# Static Control Flow

---

Static control flow refers to the **order in which static variables and blocks are executed** at the time of **class loading**, before the `main()` method runs.

---

## 🚦 Order of Execution

1. **Identification of static members** (from top to bottom).

2. **Execution of static variable assignments and static blocks** (in order).

3. **Execution of `main()` method** (if present).

---

##### **Static Control Flow Example :**

<img src="https://github.com/user-attachments/assets/161de626-0ade-4b47-9223-d0a8e8ecfe8c" width="550" height="600">
---

## Read Indirectly, Write Only (RIWO)

---

> **RIWO** = **Read Indirect, Write Only**

This is a temporary state a static variable is in **after being identified** but **before being explicitly assigned**.

- ✅ You can **call a method** that reads the variable

- ❌ You **cannot read it directly** in a static block above its declaration

- Default value is used during indirect reads

---

**📊 Access Summary**

| Access Type       | Description                                                      | Valid?    | Output              |
| ----------------- | ---------------------------------------------------------------- | --------- | ------------------- |
| **Direct Read**   | Tries to directly read static variable before its declaration.   | ❌ Illegal | Compile-time Error  |
| **Indirect Read** | Calls a method that accesses the variable before its assignment. | ✅ Allowed | 0                   |
| **Write Access**  | Writes a value to the static variable before its declaration     | ✅ Allowed | Writes successfully |

---

### ❗ Illegal Forward Reference (Direct Read)

---

> 🔴 **Direct access to a static variable is only allowed *after its declaration appears physically in the code*** — even if you're still in the identification phase.

```java
class Test {
    static {
        System.out.println(i); // ❌ Direct read before declaration
    }

    static int i = 10;
}
```

⛔ **Compile-time Error**: `illegal forward reference`

- If you try to **directly read a static variable** *before it’s declared in the file*, the compiler throws an error.

---

### ✅ Indirect Read Is Allowed (RIWO)

---

```java
class Test {
    static {
        methodOne(); // ✅ Indirect read
    }

    public static void methodOne() {
        System.out.println(i); // Reads i
    }

    static int i = 10;
}
```

🟢 Output:

```java
0
```

**Why?**

- During the call to `methodOne()`, `i` is in the **RIWO state**.

- Indirect read is allowed → `i` is not yet assigned explicitly → default value `0` is printed.

---

### ✅ Write Access Before Declaration — Legal

---

```java
class Test {
    static {
        i = 20; // ✅ Write access before declaration
        System.out.println("Written i = 20");
    }

    static int i = 10;
}
```

🧠 **Explanation Notes:**

- During the **static identification phase**, the compiler identifies all static variables — so `i` is already known before any static block runs.

- ✅ You're writing to that identified static variable `i`, and **write access is always allowed**, even if the declaration appears below.

- ❌ This is **not a local variable** — since no `int i` is declared inside the block, the compiler links this to the class-level static `i`.

- ⚠️ Contrast with a local variable:

```java
class Test {
    public static void main(String[] args) {
        int i = 20; // This is a local variable
    }
}
```

- In the local case above, `i` is a **method-local variable**, scoped only within `main()`

---

## 🔁 **Static Control Flow: Parent → Child**

When  we execute a **child class**, the JVM loads and initializes classes in the following strict order:

---

### 🚦Order of Execution When Inheritance Is Involved

---

1. **Identification of static members** (from top to bottom, Parent to Child).

2. **Execution of static variable assignments and static blocks** (from top to bottom, Parent to Child).

3. **Execution of `main()` method** (of the Child class)

---

📝 **Note:*

* Whenever we load the **child class**, the **parent class is automatically loaded**.

* Whenever we load the **child class**, the **parent class is automatically loaded**.

Whenever we load the **child class**, the **parent class is automatically loaded**.


<img src="https://github.com/user-attachments/assets/7bb51189-5516-4484-9787-2789b0b107ff" width="550" height="600">






































## RIWO state

RIWO concept (Read Indirectly Write Out) refers to a specific state that a static variable experiences during initialization. Here's what you need to know:

**When does RIWO occur?**

- RIWO arises when a static variable is declared but **before it is assigned a value**.
- This typically happens within static blocks or before the declaration of the main method in a class.
- During this phase, the variable exists in memory but cannot be directly accessed using its name.

**Why is RIWO important?**

- RIWO ensures initialization safety for static variables.
- Without it, you could potentially read an uninitialized value, leading to unpredictable behavior or errors.
- By restricting direct access, RIWO forces explicit initialization before usage.

**How do you access a variable in RIWO state?**

- Since direct access is not allowed, you need to use an **indirect method call** to initialize the variable.
- This usually involves a method defined within the same class that assigns a value to the variable.

**Example:**

**Java**

```java
class MyClass {
    static int value; // Declared but not yet initialized

  static void initialize() {
        value = 10; // Indirectly assigning a value
    }

    public static void main(String[] args) {
        // RIWO state: accessing `value` directly here would cause an error
        //System.out.println(value);

        initialize(); // Indirectly write to the variable

        // Now, value can be accessed and used normally
        System.out.println(value); // Output: 10
    }
}


```

**Key points to remember:**

- RIWO is a temporary state for static variables during initialization.
- Avoid directly accessing static variables before they are assigned a value.
- Use indirect methods within the class to properly initialize them.
- Understanding RIWO ensures safe and correct use of static variables in Java.

## Static control flow parent to child relationship :→

Whenever we are executing Child class the following sequence of events will be
performed automatically.

1. Identification of static members from Parent to Child.
2. Execution of static variable assignments and static blocks from Parent to
   Child
3. Execution of Child class main() method.
   Note : When ever we are loading child class automatically the parent class will be loaded but when ever we are loading parent class the child class won't be loaded automatically.

## Static block

- **Execution Time**: Static blocks are executed at the time of class loading. This makes them suitable for performing activities that need to be done during class initialization.
- **Multiple Static Blocks**: Within a class, you can have multiple static blocks, and they will be executed in the order they appear, from top to bottom.
- **Example 1**: Loading native libraries is a common activity that needs to be done at the time of class loading. Defining this activity inside a static block ensures it's executed when the class is loaded.
- **Example 2**: JDBC driver classes often contain a static block to register the driver with the **`DriverManager`**. This registration is essential for JDBC functionality and is typically done automatically without the programmer needing to explicitly register the driver.

## Instance Control Flow

Whenever we are executing a java class static control flow will be executed. In the Static control flow whenever we are creating an object the following sequence of events will be performed automatically.

1. Identification of instance members from top to bottom.
2. Execution of instance variable assignments and instance blocks from top to
   bottom.
3. Execution of constructor.
   Note: static control flow is one time activity and it will be executed at the time of class loading.
   But instance control flow is not one time activity for every object creation it will be executed.

## Instance control flow in Parent to Child relationship

Whenever we are creating child class object the following sequence of events will be executed automatically.

1. Identification of instance members from Parent to Child.

2. Execution of instance variable assignments and instance block only in Parent class.

3. Execution of Parent class constructor.

4. Execution of instance variable assignments and instance blocks in Child class.

5. Execution of Child class constructor.
   Note: Object creation is the most costly operation in java and hence if there is no specific requirement never recommended to crate objects.
   
   [Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/instanceControlFlow.java)

We can't access instance variables directly from static area because at the time of execution of static area JVM may not identify those members.

 But from the instance area we can access instance members directly.
 Static members we can access from anywhere directly because these are
identified already at the time of class loading only.

# Type Casting :→

In java, Parent class reference can be used to hold Child class object but by using that reference we can't call Child specific methods.

```java
Object o = new String("ashok"); // Valid
System.out.println(o.hashCode()); // Valid
System.out.println(o.length()); // Compile-time error
```

To resolve this issue, you can either:

1. Cast the **`o`** reference to the **`String`** type before calling the **`length()`** method:
   
   ```java
   System.out.println(((String) o).length());
   ```

2. Declare the **`o`** reference variable as a **`String`** type:
   
   ```java
   String o = new String("ashok");
   System.out.println(o.length());
   ```
   
   ## Type Casting Syntax
   
   ![Screenshot 2024-02-19 181822](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/e3b60918-568d-4f28-8d98-ae1372eb9746)
   
   Rule 1 : The type of "d" and "c" must have some relationship [either Child to Parent (or) Parent to Child (or) same type] otherwise we will get compile time error saying inconvertible types.

![Screenshot 2024-02-19 182020](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/0c9b1fed-f640-4314-88a1-c2f8d4340f82)

   Rule 2: "C" must be either same (or) derived type of "A" otherwise we will get compile time error saying incompatible types.
   Found: C
   Required: A
![Screenshot 2024-02-19 182107](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/f6567961-8393-415d-9ab1-2c5817d41875)

## Runtime Checking

   The underlying object type of "d" must be either same (or) derived type of "C" otherwise we will get runtime exception saying ClassCastException.

  ![Screenshot 2024-02-19 182638](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/cc7637b2-23ad-46bf-8934-2fa1c8bec501)

   Through Type Casting we are not create any new objects for the existing objects we are providing another type of reference variable(mostly Parent type).

    ```java
    package OopsConcept;
    
    public class TypeCasting {
    }
    
    class Parent1{
    
        public void methodOne(){
            System.out.println("Parent Class : A");
        }
    }
    
    class Child1 extends Parent1{
        public void methodOne(){
            System.out.println("Child Class : B");
        }
    
        public void methodTwo(){
            System.out.println("Child Class : C");
        }
    
        public static void main(String[] args){
            Child1 c1 = new Child1();
            c1.methodOne();  //Child Class : B
            c1.methodTwo();  //Child Class : C
            ((Parent1)c1).methodOne(); //Child Class : B
        }
    }
    
    /*
    Child Class : B
    Child Class : C
    Child Class : B
    */
    ```

# Cohesion

   For every component we have to maintain a clear well defined functionality such type of component is said to be follow high cohesion.

![Screenshot 2024-02-19 183928](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/7085dc39-adcb-4335-9714-0665167569eb)

   High cohesion is always good programming practice because it has several advantages.

    1. Without effecting remaining components we can modify any component hence enhancement will become very easy.
    2. It improves maintainability of the application.
    3. It promotes reusability of the application.(where ever validation is required we can reuse the same validate servlet without rewriting )

   Note: It is highly recommended to follow loosely coupling and high cohesion
