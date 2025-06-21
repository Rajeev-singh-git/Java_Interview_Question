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

# 🌀 Type Casting

---

Type casting in Java is the process of converting a reference from one type to another. It is commonly used when dealing with **inheritance** or **interface implementation**.



**Treat an object reference as a different type**, either **upcasting** (child → parent) or **downcasting** (parent → child). But Java enforces strict compile-time and runtime rules to prevent invalid usage.

---  

## ⬆️ Parent Reference Holding Child Object (Upcasting)

You can always use a **parent reference** to hold a **child object**. This is called **upcasting** and is safe at both compile-time and runtime. 

```java
Object o = new String("ashok"); // ✅ Valid  — Upcasting
System.out.println(o.hashCode());   // ✅ Works (Object class method)
System.out.println(o.length());     // ❌ Compile-time Error
```

---  

###### ❌ Why does `o.length()` fail?

- Java uses the **reference type (`Object`)** to resolve method calls at **compile-time**.

- `Object` class does **not declare** `length()`

> When using a **parent reference** to hold a **child object**, you **cannot call child-exclusive methods** directly unless you **explicitly cast** the reference. 

**Summary**

| Phase        | What Happens                                               |
| ------------ | ---------------------------------------------------------- |
| Compile-time | Method is resolved based on the **reference type**.        |
| Runtime      | If allowed, method is dispatched based on **object type**. |

> **You can only call methods available in the reference type at compile time.**  
> The **actual method executed** (if allowed) is chosen from the object the reference points to.

---

### Dynamic method dispatch

**You can only call methods that exist in the reference type at compile time.**  
Once the method is found in the reference type, at **runtime**, the **actual method executed** is chosen based on the **object the reference points to**:

- If the method is **overridden in the child class**, that version is called (child gets preference).

- If the method is **not overridden**, the **parent class version** is executed.

This mechanism is called **dynamic method dispatch** and applies only to **instance methods**, not static methods.

---  

### ✅ Accessing Child-Specific Methods

To access child-exclusive methods (like `length()` in `String`), you must **downcast**:

#### **Option 1: Explicit Type Cast**

```java
Object o = new String("ashok");
System.out.println(((String) o).length()); // ✅ Now works
```

- ✅ **Actual Object Type**: `String`

- 🎯 **Target Cast Type**: `String`

#### **Option 2: Use a Child Reference**

```java
String str = new String("ashok");  
System.out.println(str.length());  // ✅ Works  
```

###### 🔁 Summary

| Access Pattern                                  | Valid? | Why                           |
| ----------------------------------------------- | ------ | ----------------------------- |
| `Object o = new String("ashok");` `o.length();` | ❌      | Reference type is `Object`    |
| `((String) o).length();`                        | ✅      | Type cast restores access     |
| `String str = new String("ashok");`             | ✅      | Child reference directly used |

---  

## 🔌 Interface Reference Holding Child Object

```java
Runnable r = new Thread(); // ✅ Valid — Interface to implementation   
```

Example : 

```java
interface MyInterface {
    void interfaceMethod();
}

class MyClass implements MyInterface {
    @Override
    public void interfaceMethod() {
        System.out.println("Inside interfaceMethod() of MyClass");
    }

    public void childClassMethod() {
        System.out.println("Inside childClassMethod() of MyClass");
    }
}

public class ReferenceAccess {
    public static void main(String[] args) {
        MyInterface ref = new MyClass(); // Upcasting

        ref.interfaceMethod(); // ✅ Valid: interfaceMethod() is declared in MyInterface

        // ref.childClassMethod(); // ❌ Compile-time error!
        // The method childClassMethod() is undefined for the type MyInterface
    }
}
```

Output :

```java
Inside interfaceMethod() of MyClass
```

---  

### Syntax of Type Casting

```java
A  b =(C)  d;  
```

```java
A           b         =      (C)         d;  
↑           ↑                ↑           ↑  
│           │                │           └ object being cast  
│           │                └─ target type to cast into (class/interface)  
│           └──── name of the new reference variable  
└──── reference variable type (class/interface)  
```

| Element | Description                     |
| ------- | ------------------------------- |
| `A`     | Reference type you're declaring |
| `b`     | Variable name                   |
| `(C)`   | 🎯 **Target cast type**         |
| `d`     | ✅ **Actual object being cast**  |

---  

## 📘 Compile-Time Type Compatibility Rules

---

### ✅ Rule 1: **Actual Object Type** and **Target Cast Type** Must Be Related

> ✅ They must be the **same**, or in a **parent-child** relationship.

---

**✅ Valid: Related Types**

```java
Object o = new String("bhaskar");   // ✅ Actual object: String
String s = (String) o;              // 🎯 Target type: String
```

- ✅ `String` is a subclass of `Object`

- ✅ Valid both at compile-time and runtime

---

**❌ Invalid: Unrelated Types**

```java
String s = new String("bhaskar");   // ✅ Actual object: String
StringBuffer sb = (StringBuffer) s; // 🎯 Target type: StringBuffer ❌
```

- ❌ No parent-child relationship between `String` and `StringBuffer`

- ❌ **Compile-time error**: `inconvertible types`

**Output :**

- The compiler **knows** that `String` and `StringBuffer` are **not related** (neither parent, child, nor interface).

- Therefore, this cast is **statically invalid**, and Java throws:

```java
Test.java:6: inconvertible types  
found: java.lang.String  
required: java.lang.StringBuffer  
```

---

### ✅ Rule 2: Target Cast Type Must Be Same or Subclass of Reference Variable's Type

(or in simpler terms: *you can only cast to something that the actual object can become*)

```java
Object o = new String("bhaskar");  
StringBuffer sb = (StringBuffer) o;  // ❌ Runtime Error: ClassCastException
```

###### 🔍 Why this fails:

- **Reference type**: `Object`

- **Actual object type**: `String`

- **Target cast type**: `StringBuffer`

Now:

- `String` and `StringBuffer` are **unrelated types** — neither is a subclass of the other.

- You're trying to **force a cast** to something the object **can never be**.

- So the code **compiles**, but at **runtime**, it throws a `ClassCastException`.

---

✅ Correct:

```java
Object o = new String("bhaskar");  // Actual object: String
String s = (String) o;             // ✅ Valid: cast target = assignment type
```

---

## 🚨 Runtime Type Compatibility Rule

✅ **Compilation only checks type compatibility of references**.  
❌ But at **runtime**, the actual object type must match the cast target — otherwise, you’ll get a `ClassCastException`.

---

###### 🔥 Example — Compiles, but crashes at runtime

```java
Object o = new String("bhaskar");     // Actual object: String
StringBuffer sb = (StringBuffer) o;   // ❌ Runtime Error: Cannot cast String to StringBuffer
```

- ✅ Compiles, because `o` is of type `Object` and `StringBuffer` is a valid subclass.

- ❌ Crashes at runtime, because the **actual object** is a `String`, not a `StringBuffer`.

**Runtime Error:**

```java
java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.StringBuffer
```

---

## 🧬 Type Casting Hierarchy Tree

```java
              Object
             /      \
          Base1     Base2
         /    \     /    \
       D1     D2   D3    D4
```

- All classes ultimately inherit from `Object`

- `D1`, `D2` are subclasses of `Base1`, and `D3`, `D4` are subclasses of `Base2`

---

### ⚖️ Valid & Invalid Cast Examples

```java
Base1 b = new Derived2();           // ✅ Valid
Object o =  (Base1) b;              // ✅ Valid
Object o1 = (Base2) o;              // ❌ Invalid (ClassCastException)
Object o2 = (Base2) b;              // ❌ Invalid
Base2 b1 = (Base1)(new D1());    // ❌ Invalid
Base2 b2 = (Base2)(new D3());    // ✅ Valid
Base2 b3 = (Base2)(new D1());    // ❌ Invalid
```

---

## 🎯 Clarification: Type Casting ≠ Object Creation

> **Type casting doesn’t create a new object**.  
> It just changes the way you reference an existing object.

```java
String s = new String("Bhaskar");
Object o = (Object) s;              // ✅ Upcasting

System.out.println(s == o);         // ✅ true — same object in memory
```

Is **identical to**:

```java
Object o = new String("Bhaskar");   // ✅ Direct upcasting during creation
```

- Both refer to the **same `String` object**

- Type casting only **changes reference type**, not the object identity

---

## 🧬 Method & Variable Resolution in Inheritance (Examples 1–4)

---

### ✅ Example 1: Parent Reference Limitation

```java
class Parent {
    public void methodOne() {
        System.out.println("A");
    }
}

class Child extends Parent {
    public void methodOne() {
        System.out.println("B");
    }

    public void methodTwo() {
        System.out.println("C");
    }

    public static void main(String[] args) {
        Child c = new Child();
        c.methodOne();          // Output: B
        c.methodTwo();          // Output: C
        ((Parent) c).methodOne(); // Output: B ✅ Allowed due to overriding
        // ((Parent) c).methodTwo(); ❌ Compile-time error — methodTwo() not in Parent
    }
}
```

###### 🔍 Key Insight:

- Casting a child object to a parent reference limits access to only those methods declared in the parent.

- Method resolution for **overridden methods** is based on **runtime object type**.

- Method resolution for **new methods (not in parent)** depends on **reference type**, hence `methodTwo()` is inaccessible via `(Parent)`.

---

#### 🔁 Example 2: Instance Method Overriding

```java
class A {
    public void methodOne() {
        System.out.println("A");
    }
}
class B extends A {
    public void methodOne() {
        System.out.println("B");
    }
}
class C extends B {
    public void methodOne() {
        System.out.println("C");
    }
}
```

```java
C c = new C();
c.methodOne();                      // Output: C
((B) c).methodOne();                // Output: C
((A)((B) c)).methodOne();           // Output: C
```

###### 🔍 Key Insight:

- Instance method overriding is **runtime polymorphism**.

- Method resolution is always based on **actual object** (i.e., `new C()`), regardless of reference type.

- This is **true overriding**.

---

#### ⚠️ Example 3: Static Method Hiding (NOT Overriding)

```java
class A {
    public static void methodOne() {
        System.out.println("A");
    }
}
class B extends A {
    public static void methodOne() {
        System.out.println("B");
    }
}
class C extends B {
    public static void methodOne() {
        System.out.println("C");
    }
}
```

```java
C c = new C();
c.methodOne();                      // Output: C
((B) c).methodOne();                // Output: B
((A)((B) c)).methodOne();           // Output: A
```

---

#### 📦 Example 4: Variable Hiding

```java
class A {
    int x = 777;
}
class B extends A {
    int x = 888;
}
class C extends B {
    int x = 999;
}
```

```java
C c = new C();
System.out.println(c.x);                    // Output: 999
System.out.println(((B) c).x);              // Output: 888
System.out.println(((A)((B) c)).x);         // Output: 777
```

###### 🔍 Key Insight:

- **Instance variables are also resolved using reference type**, not runtime object type.

- Even though the actual object is `new C()`, variable `x` is accessed based on **reference type**.

---

###### 🧠 Summary Table

| Concept         | Resolution Based On | Overridden/Hidden | Reference Type Effect |
| --------------- | ------------------- | ----------------- | --------------------- |
| Instance Method | Runtime object      | ✅ Overridden      | ❌ No effect           |
| Static Method   | Reference type      | ❌ Hidden          | ✅ Affects outcome     |
| Variables       | Reference type      | ❌ Hidden          | ✅ Affects outcome     |

---

# 🔗 Coupling

> **Coupling** is the degree of dependency between components.  
> If components heavily depend on each other, they are said to be **tightly coupled**.

---

## 🧪 Example of Tightly Coupled Code:

```java
class A {
    static int i = B.j;
}

class B extends A {
    static int j = C.methodOne();
}

class C extends B {
    public static int methodOne() {
        return D.k;
    }
}

class D extends C {
    static int k = 10;

    public static void main(String[] args) {
        D d = new D();
    }
}
```

###### ⚠️ Observation:

- Class `A` depends on `B`, which depends on `C`, which depends on `D`.

- All components are tightly coupled.

---

#### ❌ Disadvantages of Tight Coupling:

1. **Low Modifiability:**  
   Can't change one component without affecting others — enhancement becomes hard.

2. **Poor Maintainability:**  
   Code becomes harder to maintain as complexity grows.

3. **No Reusability:**  
   Tightly bound logic can't be reused independently.

---

##### ✅ Best Practice:

> It’s always recommended to maintain **loose coupling** between components — minimize dependency and interaction.

---

# 🧲 Cohesion

---

###### 🔍 Definition:

> **Cohesion** is the degree to which elements of a component belong together.  
> A highly cohesive component performs a **single, well-defined task**.

---

#### 📊 High Cohesion vs Low Cohesion

##### ✅ High Cohesion:

- Every class/component has a **single responsibility**.

- Improves reusability, readability, and testability.

##### ❌ Low Cohesion:

- Component handles multiple unrelated responsibilities.

- Makes the code hard to understand, modify, or reuse.

---

### 💡 Visual Comparison:

###### 🚫 Low Cohesion:

```java
// One servlet handling everything
class TotalServlet {
    void handle() {
        // login, validation, inbox, compose, forward...
    }
}
```

###### ✅ High Cohesion:

```java
login.jsp       --> login only  
validateServlet --> validation  
error.jsp       --> error display  
inbox.jsp       --> inbox display  
compose.jsp     --> compose mail  
```

---

###### ✅ Advantages of High Cohesion:

1. **Easy Enhancement:**  
   You can modify or extend components independently.

2. **Better Maintainability:**  
   Small, focused components are easier to manage and test.

3. **Improved Reusability:**  
   You can reuse validation or UI modules across the app.

---

##### 🧠 Final Note:

> ✅ Follow **Loose Coupling** and **High Cohesion** to build maintainable, extensible, and testable Java applications.
