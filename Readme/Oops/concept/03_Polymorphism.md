# Polymorphism

## Table of Contents

- [Polymorphism](#-polymorphism)  
  - [Types of Polymorphism](#-types-of-polymorphism)  
  - [Overloading](#1-overloading-compile-time-polymorphism)  
    - [Key Concepts of Overloading](#-key-concepts-of-overloading-)  
    - [Overloading Rules](#overloading-resolution-rules-in-java)  
    - [Overloading Rules Cheat Sheet](#-overloading-rules-cheat-sheet)  
  - [Overriding](#2--method-overriding-runtime-polymorphism)  
    - [Rules for Overriding](#rules-for-overriding-)  
    - [Overriding System-Wide Example](#-overriding-with-respect-to-list)  
  - [Overriding vs Overloading](#-overriding-vs-overloading)  
  - [Method Hiding](#-method-hiding)  
    - [Static Methods and Overriding](#%EF%B8%8F-static-methods-and-overriding)  
    - [Method Hiding vs Method Overriding](#-method-hiding-vs-method-overriding)  
  - [Checked vs Unchecked Exceptions](#checked-vs-unchecked-exceptions)  
    - [Overriding Example WRT Exception Handling](#-overriding-examples-valid-vs-invalid)

---

# 🧬 Polymorphism

**Polymorphism** means *"many forms."*  
It enables the same method or interface to exhibit **different behaviors** depending on the context — such as **parameter types** (compile-time) or **object type** (runtime).

> ✅ In Java, polymorphism is **achieved via:**
> 
> - **Method Overloading** (Compile-Time Polymorphism)
> 
> - **Method Overriding** (Runtime Polymorphism)

---

###### 🧠 Real-World Analogy

> 🧑 A person named "Alex" can be a teacher at school, a father at home, and a customer at a store.  
> Same name, different roles. That’s **polymorphism**.

**Polymorphism Analogy**

> A **boy begins love** with the word **“friendship”**,  
> while a **girl ends love** with the same word — **“friendship".**
> 
> The **word is the same**, but the **intention is different**.  
> That, my friend, is the **essence of Polymorphism** —  
> **Same name, different behavior.** 💔➡️❤️

---

### 🔀 Types of Polymorphism

| Type             | Also Known As          | Resolved When?     | How?                               |
| ---------------- | ---------------------- | ------------------ | ---------------------------------- |
| **Compile-Time** | Static / Early Binding | During Compilation | Method Overloading,  Method Hiding |
| **Runtime**      | Dynamic / Late Binding | During Execution   | Method Overriding                  |

```java
                           Polymorphism 
                               / \
                              /   \    
                             /     \
                            /       \
                           /         \
                          /           \
                         /             \
                        /               \
                       /                 \
                      /                   \
 Compile-time/Static/Early binding        Runtime/Dynamic/Late binding 
              / \                                  |
             /   \                                 |
            /     \                                |
           /       \                               |
          /         \                              |  
Method Overloading   Method Hiding          Method Overriding
```

---

## 1.) ⚡Overloading (Compile-time Polymorphism)

---

- **Method overloading** occurs when a class defines **multiple methods with the same name** but with **different parameter lists** (type, number, or order).

- In **method overloading**, the **compiler** determines which method to call based on the **reference type** and **method signature**. The runtime object is irrelevant in this case.

Hence, method overloading is also called:

- **Compile-time Polymorphism**

- **Static Polymorphism**

- **Early Binding**

---

**✅ Overloading Example:**

```java
public class Calculator {
  public int add(int a, int b) {
      return a + b;
  }

  public double add(double a, double b) {
      return a + b;
  }
}
```

- Both methods are named `add` but operate on different data types.

```java
Calculator c = new Calculator();

System.out.println(c.add(2, 3));       //  Output: 5
System.out.println(c.add(2.5, 3.5));   //  Output: 6.0
```

- **Compile-time polymorphism** — Java determines which method to call based on argument types at compile time.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverloadingExample.java)

---

### 🔑 Key Concepts of Overloading :

- The **return type is not** part of the method signature.

- Overloading requires a difference in **number**, **type**, or **order** of parameters.

- Method resolution is based on the **reference type**, not the object type.

- If an exact match isn’t found, Java performs **automatic type promotion**.

**🔄 Type Promotion Chain in Java:**

```java
byte -> short 
             \
              int -> long -> float -> double
            /
         char               
```

*Java automatically promotes smaller types to match a method signature when needed.*

---

### 🧪**Overloading Resolution Rules in Java**

---

#### ⚡ Rule 1: **Automatic Type Promotion**

```java
class Test {
  public void methodOne(int i) { System.out.println("int"); }
  public void methodOne(float f) { System.out.println("float"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne('a');     // char → int → int method
    t.methodOne(10L);     // long → float → float method
    t.methodOne(10.5);    // double → no match → ❌ Compile-Time Error
  }
}
```

> Java promotes smaller types step by step to find a matching method.  
> If none matches even after all promotions → **Compile-Time Error**.

---

#### 🧊 Rule 2: **Match Priority – Exact vs Compatible**

```java
class Test {
  public void methodOne(Object o) { System.out.println("Object"); }
  public void methodOne(String s) { System.out.println("String"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne("Hello");  // ✅ Exact match: String
    t.methodOne(new Object()); // ✅ Exact match: Object
  }
}
```

> - ✅ **Exact match always has the highest priority.**
> 
> - ✅ If no exact match, Java looks for the closest compatible match.
> 
> - ✅ **Child types** are preferred over **parent types**.

---

#### ❗ Rule 3: **Ambiguity with Same Level Types**

```java
class Test {
  public void methodOne(String s) { System.out.println("String"); }
  public void methodOne(StringBuffer sb) { System.out.println("SB"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne(null);   // ❌ Compile-Time Error — ambiguous
  }
}
```

> If both candidates are equally specific, Java throws **ambiguity error**.

```java
                            Object
                             /  \
                            /    \
                           /      \
                          /        \   
                      String    StringBuffer     
```

---

#### 🔄 Rule 4: Same Count, Different Order (Ambiguity)

```java
class Test {
  public void methodOne(int i, float f) { System.out.println("int-float"); }
  public void methodOne(float f, int i) { System.out.println("float-int"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne(10, 10.5f);  // int-float
    t.methodOne(10.5f, 10);  // float-int
    t.methodOne(10, 10);     // ❌ Compile-Time Error — ambiguous
  }
}
```

> Overloading by **reordering argument types** can cause ambiguity when both combinations are valid.

---

#### 🌌 Rule 5: **Var-Args vs Fixed Args**

```java
class Test {
  public void methodOne(int i) { System.out.println("Fixed"); }
  public void methodOne(int... i) { System.out.println("Var-arg"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne();           // Var-arg
    t.methodOne(10);         // Fixed
    t.methodOne(10, 20);     // Var-arg
  }
}
```

> - **Var-arg** methods have the *lowest* priority** during method resolution.
> 
> - If **no other method matches**, **only then** the var-arg method will be chosen.
> 
> - It behaves **almost like the `default` case in a `switch` statement** — used as a *fallback*

---

#### 🧬 Rule 6: **Parent vs Child References**

```java
class Animal {}
class Monkey extends Animal {}

class Test {
  public void methodOne(Animal a) { System.out.println("Animal"); }
  public void methodOne(Monkey m) { System.out.println("Monkey"); }

  public static void main(String[] args) {
    Test t = new Test();
    t.methodOne(new Animal());       // Animal 
    t.methodOne(new Monkey());       // Monkey 

    Animal a = new Monkey();
    t.methodOne(a);                  // Animal (based on ref type)
  }
}
```

> In **overloading**, **reference type** is used for method resolution, not the actual object.

---

### 📊 Overloading Rules Cheat Sheet

| Case | Focus                       | Key Takeaway                                                 |
| ---- | --------------------------- | ------------------------------------------------------------ |
| 1    | Type Promotion              | Smaller types promoted to match method                       |
| 2    | Exact Match Priority        | Exact > Compatible; child type preferred over parent         |
| 3    | Null Ambiguity              | Ambiguity with sibling types like `String` vs `StringBuffer` |
| 4    | Same Count, Different Order | Ambiguity when both overloads match by argument count        |
| 5    | Var-Args Fallback           | Var-arg used only when no fixed match is found               |
| 6    | Ref vs Object Type          | Overload resolution is based on reference type               |

---

## 2. 🔁 Method Overriding (Runtime Polymorphism)

---

##### 🧠 What is Method Overriding ?

- **Method Overriding**  allows a **subclass (child class)** to provide its **own specific implementation** of a method that is already defined in its **superclass (parent class)**.

- The method in the **parent class** that is being redefined is called the **overridden method**.

- The method in the **child class** that redefines the parent’s method is called the **overriding method**.

- The **overriding method** in the child class must have the **same signature** as the method in the parent class.

- It enables **runtime polymorphism**: the **method call is resolved at runtime**, based on the **actual object**, not the reference type.

- To explicitly indicate that a method in the subclass is intended to **override** a method in the superclass, we use the **`@Override`** annotation.  
  This helps the compiler catch mistakes like mismatched method signatures or typos.

---

**📦 Overriding Example:**

```java
class Parent {
    public void property() {
        System.out.println("Cash + Land + Gold");
    }

    public void marry() {
        System.out.println("Arrange marriage");
    }
}

class Child extends Parent {
    @Override
    public void marry() {
        System.out.println("Love marriage");
    }
}

public class Test {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.marry(); // Arrange marriage

        Child c = new Child();
        c.marry(); // Love marriage

        Parent p1 = new Child();
        p1.marry(); // Love marriage
    }
}
```

> In **method overriding**, method resolution is based on the **runtime object**, not the reference type.  
> This is why `p1.marry()` (where `p1` is a parent reference pointing to a child object) invokes the **child’s overridden method**.

However, when using a **parent reference to hold a child object**:

- ✅ You can call **overridden methods** — this is resolved at **runtime** (dynamic method dispatch).

- ✅ You can call **parent-exclusive methods** — resolved at **compile-time** using the **reference type**.

- ❌ You **cannot call child-exclusive methods** — i.e., methods that exist only in the child class and are not declared in the parent class.

- ❌ You **cannot access private methods** of the parent using the parent reference — because private methods are not visible outside their own class and are **not inherited**.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverridingExample.java)

---

### 🧩Rules for Overridng :

---

#### ✅ Rule 1: Signature Must Match Exactly

- The method **name and parameter types** must match exactly in parent and child class.
- If parameter types differ, it’s **not overriding** — it’s overloading.

```java
class A {
    void display(int x) {}        // valid method
}

class B extends A {
    void display(int x) {}        // ✅ valid overriding
    int display(float x) { return 0; } // ❌ overloading, not overriding
}
```

> 🧠 Return type can vary in overriding (see Rule 2), but **parameters must be the same**.

---

#### ✅ Rule 2: Covariant Return Types (Java 1.5+)

- A child class can **narrow the return type** to a subclass of the parent’s return type

```java
class Parent {
    public Object getData() { return null; }
}

class Child extends Parent {
    public String getData() { return null; } // ✅ Valid — String ⊆ Object
}
```

> ❌ Only for reference types — not allowed for primitives.

```java
class A {
    int show() { return 1; }
}

class B extends A {
    long show() { return 1L; } // ❌ Compile-time error — primitives don’t support covariance
}
```

✅ Covariant return types allow more specific results in overridden methods, improving flexibility in OOP design.

---

#### ❌ Rule 3: Private Methods Cannot Be Overridden

- Private methods are **not inherited** — a method with the same name in the child is a **new method**, not an override.

```java
class Parent {
    private void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    private void show() {
        System.out.println("Child");
    }
}

public class Test {
    public static void main(String[] args) {
        Child c = new Child();
        c.show(); // ✅ Works: calls Child's private method → Output: "Child"

        Parent p = new Child();
        p.show(); // ❌ Compile-time error: show() has private access in Parent
    }
}
```

> ✅ `Child.show()` is unrelated to `Parent.show()` — no overriding or hiding.  
> ❌ `Parent.show()` is private and not accessible via reference if object type is child, even if object is Child.
> 
> A private method is **invisible to subclasses**, so overriding is not possible.

---

#### 4. ❌ Final Methods Cannot Be Overridden

If a method in the parent is `final`, **the child class:**

- ❌ **Cannot override** that method (i.e., same name + same parameter list).

**Example 1 : -- >**

```java
class Parent {
    public final void show() {}
}

class Child extends Parent {
    public void show() {} // ❌ Compile-time error
}
```

🛑 Same signature — this is **illegal overriding** of a `final` method.

---

✅ **Child class can overload** that method (same name + different parameters).

```java
class Parent {
    public final void show(int x) {
        System.out.println("Parent: " + x);
    }
}

class Child extends Parent {
    public void show(String msg) { // ✅ different signature → overloading
        System.out.println("Child: " + msg);
    }
}
```

Here:

- `show(int)` is **final** in parent → ❌ cannot be overridden

- `show(String)` is a **new overloaded method** → ✅ allowed

> 🔒 If a method is `final` in the parent, the child **cannot override it** (i.e., same signature),  
> but it **can overload** it by declaring a method with the same name and **different parameters**.

---

#### ✅ Rule 5: A Non-Final Method Can Be Overridden as Final

The child can make the method `final`, preventing **further overriding** in the next subclass.

```java
class Parent {
    public void show() {}
}

class Child extends Parent {
    public final void show() {} // ✅ Legal
}
```

---

#### ✅ Rule 6: Abstract Methods Must Be Overridden

Every concrete (non-abstract) subclass **must override** inherited abstract methods.

```java
abstract class Parent {
    public abstract void show();
}

class Child extends Parent {
    public void show() {} // ✅ Must override
}
```

🧠 Abstract methods provide a **template**, forcing implementation in concrete classes.

---

#### ✅ Rule 7: Concrete Method can be made Abstract in Subclass

You can declare an inherited concrete method as `abstract` in a subclass — but the class itself must also be `abstract`.

```java
class Parent {
    public void show() {}
}

abstract class Child extends Parent {
    public abstract void show(); // ✅ Legal — now abstract
}
```

🧠 Used to **defer implementation** to next-level subclasses.

---

#### ✅ Rule 8: Var-arg Method can only be overriden with Another Var-arg Method

If a parent method is var-arg, the child must also use var-arg to override.

```java
class Parent {
    public void show(int... x) {
        System.out.println("Parent var-arg method");
    }
}
class Child extends Parent {
    public void show(int... x) {
        System.out.println("Child var-arg method");
    }
}
```

🟢 This is valid overriding.

---

##### ⚠️ Not Overriding: Var-arg vs Regular Method

```java
class Child extends Parent {
    public void show(int x) {
        System.out.println("Child regular method");
    }
}
```

❌ This is **overloading**, not overriding — the signatures differ (`int...` vs `int`).

| Parent Method      | Child Method       | Result        |
| ------------------ | ------------------ | ------------- |
| `void m(int... x)` | `void m(int... x)` | ✅ Overriding  |
| `void m(int... x)` | `void m(int x)`    | ❌ Overloading |

---

#### 🔐 Rule 9: Access Modifier Cannot Be More Restrictive

When overriding a method, the access modifier in the child class **cannot be more restrictive** than in the parent class.

The overriding method **cannot reduce visibility**.

| Parent Modifier | Allowed in Child Class           | ✅ / ❌ | Reason                                 |
| --------------- | -------------------------------- | ----- | -------------------------------------- |
| `private`       | ❌ Cannot override                | ❌     | Not inherited; method is class-private |
| *(default)*     | `default`, `protected`, `public` | ✅     | Visible only within the same package   |
| `protected`     | `protected`, `public`            | ✅     | Wider visibility allowed               |
| `public`        | `public` only                    | ✅     | Already the widest access              |

You can **increase visibility** (e.g., `protected` → `public`), but you **cannot decrease** it (e.g., `public` → `protected`).

```java
 // scope of access modifier
 private < default < protected < public
```

🧠 Think of it like **door sizes**: you can open the door wider, but you can’t shrink it.

---

#### ✅ Overriding with respect to list

```java
ArrayList al = new ArrayList();    // Like: Child c = new Child();
List list = new ArrayList();       // Like: Parent p = new Child();
```

| **Aspect**         | `ArrayList al = new ArrayList();`                         | `List l = new ArrayList();`                                        |
| ------------------ | --------------------------------------------------------- | ------------------------------------------------------------------ |
| **Reference Type** | `ArrayList` (Concrete class)                              | `List` (Interface)                                                 |
| **Runtime Object** | `ArrayList`                                               | `ArrayList`                                                        |
| **Flexibility**    | ❌ Tight coupling to `ArrayList`                           | ✅ Flexible: can change to `LinkedList`, `Vector`, etc.             |
| **Access**         | ✅ Can access both `List` and `ArrayList` specific methods | ❌ Can only access methods declared in `List` interface             |
| **Best Use Case**  | When you **know** you'll use only `ArrayList` features    | When you **program to interface**, for flexibility and abstraction |

---

##### 🤔 Why Use: `List l = new ArrayList();`

If we’re creating an `ArrayList`, but limiting ourselves to List methods — **what’s the point?**

##### ✅ The Benefit Comes From: **Polymorphism and Flexibility**

##### ✅ 1. **Easier to Switch Implementations**

You're not locking yourself to `ArrayList`. You can easily switch to `LinkedList`, `Vector`, or any other `List` implementation **without changing the variable type**.

```java
List l = new ArrayList();
// Later...
l = new LinkedList();  // No change needed in rest of the code
```

This is powerful in large codebases or APIs where **you don’t care how the list is implemented**, only that it behaves like a `List`.

---

## ⏬ Overriding vs Overloading

| 🔧 **Property**                          | ⚙️ **Overloading**                                      | 🔄 **Overriding**                                            |
| ---------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------------ |
| 1️⃣ Method Name                          | Must be same                                            | Must be same                                                 |
| 2️⃣ Argument Types                       | Must differ (type, number, or order)                    | Must be exactly same                                         |
| 3️⃣ Method Signature                     | Must be different                                       | Must be same                                                 |
| 4️⃣ Return Type                          | No restriction                                          | Must be same until Java 1.4, <br>Co-variant allowed from 1.5 |
| 5️⃣ `private`, `static`, `final` methods | Can be overloaded                                       | **Cannot** be overridden                                     |
| 6️⃣ Access Modifier                      | No restriction                                          | Can't weaken the access modifier                             |
| 7️⃣ Throws Clause                        | No restriction                                          | Checked exceptions must match or be a subclass               |
| 8️⃣ Method Resolution                    | Done by **compiler** at compile-time based on reference | Done by **JVM** at runtime based on object                   |
| 9️⃣ Also Known As                        | Compile-time / Static / Early Binding                   | Runtime / Dynamic / Late Binding                             |

---

## 🧱 Method Hiding

> In Java, when a **static method** in a subclass has the **same signature** as one in its superclass, it's called **method hiding**, not overriding.

---

### ⚠️ Static Methods and Overriding

---

#### ❌  Can We Override Static Methods?

**No.** Static methods **cannot** be overridden — they are **class-level**, not object-level.

- Instead, if you define a static method with the **same signature**, it’s called **method hiding**.

- Resolution is done at **compile time**, based on the **reference type**.

---

###### 🧪 Method Hiding Example

```java
class Parent {
    public static void methodOne() {
        System.out.println("Parent static");
    }
}
class Child extends Parent {
    public static void methodOne() {
        System.out.println("Child static");
    }
}
public class Test {
    public static void main(String[] args) {
        Parent p = new Child();
        p.methodOne(); // Output: Parent static (⚠️ based on reference)
    }
}
```

> Even though `p` refers to a `Child`, static method is **not overridden** — so **Parent's** version runs.

---

###### 🔍 CASE 1:  Static Method cannot be overriden as Non-static ❌

```java
class Parent {
    public static void methodOne() {
        System.out.println("Parent static method");
    }
}

class Child extends Parent {
    public void methodOne() { // ❌ non-static
        System.out.println("Child instance method");
    }
}
```

🔴 **Compile-time Error:**

```java
methodOne() in Child cannot override methodOne() in Parent; 
overridden method is static
```

---

###### 🔍 CASE 2:  Non-static Method can be overriden as Static ❌

```java
class Parent {
    public void methodOne() {
        System.out.println("Parent instance method");
    }
}

class Child extends Parent {
    public static void methodOne() { // ❌ static
        System.out.println("Child static method");
    }
}
```

🔴 **Compile-time Error:** Same reason — method type mismatch (instance vs static).

---

#### 🔬 Method Hiding vs Method Overriding

| Feature       | Overriding               | Method Hiding             |
| ------------- | ------------------------ | ------------------------- |
| Method Type   | Instance methods         | Static methods            |
| Resolved By   | JVM at runtime           | Compiler at compile time  |
| Polymorphism  | Runtime Polymorphism     | Compile-time polymorphism |
| Required?     | Must be inherited        | Inheritance not mandatory |
| Real Use Case | Enables dynamic behavior | Not polymorphic           |

---

[Code Example 1](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/MethodHiding.java)

[Code Example 2](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/Interf.java)

[Code Example 3](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/JavaCore/src/OopsConcept/OverridingVarAgMethod.java) 

---

#### ✅ **Practice: Validity of Methods in Child Class**

Given:

```java
class Parent {
  public void methodOne(int i) throws IOException;
}
```

Which of the following in Child are valid?

| Method in Child                                  | Result    | Reason                                          |
| ------------------------------------------------ | --------- | ----------------------------------------------- |
| `public void methodOne(int i)`                   | ✅ Valid   | Overrides method and removes exception          |
| `private void methodOne()`                       | ✅ Valid   | Overloaded (different signature)                |
| `public native void methodOne(int i)`            | ✅ Valid   | Overrides with native                           |
| `public static void methodOne(double d)`         | ✅ Valid   | Overloaded (different type)                     |
| `public static void methodOne(int i)`            | ❌ Invalid | Tries to override non-static method with static |
| `public static abstract void methodOne(float f)` | ❌ Invalid | abstract + static not allowed                   |

---

# ✨ **Checked vs Unchecked Exceptions**

---

### ✅ What are Checked Exceptions?

- **Checked exceptions** are those which the compiler checks at **compile-time** to ensure smooth execution at runtime.

- These must be either caught using a `try-catch` block or declared in the method signature using `throws`.

📝 **Examples:**  
`IOException`, `SQLException`, `ClassNotFoundException`

---

### ❌ What are Unchecked Exceptions?

- **Unchecked exceptions** are **not checked at compile time**. These typically indicate programming errors that occur at runtime.

- All exceptions that are subclasses of `RuntimeException` and `Error` are unchecked.

📝 **Examples:**  
`NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`, `StackOverflowError`

---

### 🔁 Exception Rules with respect to overriding

**Rule (✅ for Checked Exceptions):**

If a **child class overrides a method** that declares a checked exception, then:

- The child method can throw:
  
  - The **same checked exception**, or
  
  - A **subclass of it**

- It **cannot** throw:
  
  - A **broader checked exception**
  
  - A **new checked exception** if the parent method doesn’t declare one

**Rule (✅ for Unchecked Exceptions):**

No restrictions apply. You can throw any number of unchecked exceptions regardless of the parent method.

---

**💻 Example Code: Compile-Time Error with Checked Exception**

```java
class Parent {
    public void methodOne() {} // Does not declare any checked exception
}

class Child extends Parent {
    public void methodOne() throws Exception {} // ❌ Compile-time error
}
```

🛑 **Error:**

```java
methodOne() in Child cannot override methodOne() in Parent;
overridden method does not throw java.lang.Exception
```

---

### 🔢 Overriding Examples: Valid vs Invalid

| No. | Parent Method Signature               | Child Method Signature                                                               | ✅ Valid?                                                           | Reason                                                                                                                 |
| --- | ------------------------------------- | ------------------------------------------------------------------------------------ | ------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------------- |
| 1   | `void methodOne() throws Exception`   | `void methodOne()`                                                                   | ✅ Yes                                                              | Child throws nothing (which is okay)                                                                                   |
| 2   | `void methodOne()`                    | `void methodOne() throws Exception`                                                  | ❌ No                                                               | Child throws new checked exception not declared by parent                                                              |
| 3   | `void methodOne() throws Exception`   | `void methodOne() throws Exception`                                                  | ✅ Yes                                                              | Same exception                                                                                                         |
| 4   | `void methodOne() throws IOException` | `void methodOne() throws Exception`                                                  | ❌ No                                                               | Exception is broader than IOException                                                                                  |
| 5   | `void methodOne() throws IOException` | `void methodOne() throws EOFException, FileNotFoundException`                        | ❌ No      ⚠️ Technically valid, but discouraged unless necessary.* | Declares multiple subclasses — though valid in Java, it introduces ambiguity and complicates exception handling logic. |
| 6   | `void methodOne() throws IOException` | `void methodOne() throws EOFException, InterruptedException`                         | ❌ No                                                               | InterruptedException is unrelated                                                                                      |
| 7   | `void methodOne() throws IOException` | `void methodOne() throws EOFException, ArithmeticException`                          | ❌ No                                                               | ArithmeticException (✅ unchecked), but EOFException is okay only                                                       |
| 8   | `void methodOne()`                    | `void methodOne() throws NullPointerException, RuntimeException, ClassCastException` | ✅ Yes                                                              | All are unchecked — no restriction                                                                                     |

---
