# 📘 1. String – Introduction & Core Concepts

## Table of Contents

1. [String](#string)
   - [Properties of String](#special-properties-of-string)
   - [Different ways to Create Strings](#different-ways-to-create-strings)
   - [Why is String immutable in Java?](#why-is-string-immutable-in-java)
2. [StringBuffer](#stringbuffer)
   - [String vs StringBuffer](#-string-vs-stringbuffer--key-differences)
   - [Choosing Between String and StringBuffer](#-choosing-between-string-and-stringbuffer)
3. [Immutability vs Mutability](#-immutability-vs-mutability)
4. [== vs equals()](#--vs-equals-in-strings)
   - [equals() in String](#-in-string-equals-is-overridden-to-compare-characters)
   - [equals() in StringBuffer](#-in-stringbuffer-equals-is-not-overridden)
   - [==vsequals()](#vsequals--summary)

---

# String

---

### What is a `String`?

In Java, a `String` is a **sequence of characters** enclosed in double quotes. It is **not a primitive type**, but a **class** from the `java.lang` package — and arguably one of the most important and frequently used.

```java
String message = "Hello, World!";
```

- This creates a `String` object containing the characters `H`, `e`, `l`, `l`, `o`, `,`, etc.

- Internally, every `String` object is backed by a **character array (`char[]`)**.

---

### Special Properties of `String`

| Feature                 | Description                                                                                |
| ----------------------- | ------------------------------------------------------------------------------------------ |
| ✅ Object, not primitive | `String` is a full-fledged class, even though it feels like a basic type.                  |
| ✅ Immutable             | Once created, a string’s content **cannot be changed**.                                    |
| ✅ Backed by `char[]`    | Under the hood, `String` stores characters in a character array.                           |
| ✅ Final class           | The `String` class is declared `final` — it **cannot be subclassed (extended/inherited).** |
| ✅ Reused via SCP        | String literals are stored in the **String Constant Pool** for reuse.                      |

```java
public final class String implements java.io.Serializable,
                                     Comparable<String>,
                                     CharSequence
```

---

### Different ways to Create Strings

**1. Using String Literals**
Literals are stored in the **String Constant Pool (SCP)** — reused if the same content already exists.

```java
String s1 = "Java";
String s2 = "Java"; // Reuses the same object
```

**2. Using `new` Keyword**  

Forces creation of a **new object in the heap**, even if an identical literal exists in SCP.

```java
String s3 = new String("Java");
```

>  Even if `"Java"` already exists in the SCP, `new String()` **bypasses the SCP** and creates a distinct object in the **heap memory**, pointing to a **new memory address**.

---

#### Behind the Scenes: Memory Allocation

| Memory Area                          | Used For                                                  |
| ------------------------------------ | --------------------------------------------------------- |
| 🧠 **SCP (Method Area / Metaspace)** | Stores string literals for reuse and memory optimization. |
| 📦 **Heap Memory**                   | Used when creating strings explicitly with `new` keyword. |

ℹ️ More detailed memory diagrams and behavior will be covered in Next page.

---

### Why is `String` immutable in Java?

| Reason                   | Explanation                                                                                                                     |
| ------------------------ | ------------------------------------------------------------------------------------------------------------------------------- |
| 🔐 **Security**          | Strings are used in sensitive operations (e.g., file paths, network connections). Immutability prevents malicious modification. |
| 🤝 **Thread Safety**     | Multiple threads can safely share the same string without synchronization.                                                      |
| 🧠 **Memory Efficiency** | Enables reuse via **String Constant Pool (SCP)**, reducing duplication and memory overhead.                                     |
| ⚡ **Hashcode Caching**   | Immutable strings can cache their hashcode, boosting performance in collections like `HashMap`.                                 |

---

# StringBuffer

---

A `StringBuffer` is a **mutable sequence of characters**, introduced to improve performance when strings are modified frequently.

```java
StringBuffer sb = new StringBuffer("Rajeev");
sb.append(" Singh");
System.out.println(sb); // Rajeev Singh
```

### Why was StringBuffer introduced??

Modifying a `String` creates **new objects each time**, leading to **memory inefficiency** in scenarios like loops, logging, or dynamic content building.

---

### 🔄 String vs StringBuffer – Key Differences

| Feature                     | `String`                                     | `StringBuffer`                                     |
| --------------------------- | -------------------------------------------- | -------------------------------------------------- |
| Mutability                  | ❌ Immutable                                  | ✅ Mutable                                          |
| Modification Behavior       | Creates a new object                         | Updates the existing object                        |
| Thread Safety               | Thread-safe (due to immutability)            | Thread-safe (via synchronization)                  |
| Performance (single thread) | Slower for frequent changes                  | Faster for frequent changes                        |
| Memory Use                  | More objects → more memory → More GC pressur | Fewer objects → Less object creation → less memory |

---

### 🧭 Choosing Between `String` and `StringBuffer`

✅ Use **`String`** when:

- The value is **constant** or doesn’t change over time.

- Memory sharing via SCP is beneficial.

✅ Use **`StringBuffer`** when:

- The content changes frequently.

- You need **thread safety** during modification.

---

###### 🆕 Additional Consideration: `StringBuilder`

- 🆕 Introduced in **Java 1.5**.

- Same API as `StringBuffer`, but **not synchronized**.
  
  | Feature       | StringBuffer            | StringBuilder           |
  | ------------- | ----------------------- | ----------------------- |
  | Thread Safety | ✅ Yes (synchronized)    | ❌ No (non-synchronized) |
  | Performance   | Slower in single thread | Faster in single thread |
  | Use When      | Multiple threads modify | Single-threaded usage   |

✅ Use `StringBuilder` when **performance is critical**, and **thread safety is not required**.

---

# 🔁 `==` vs `equals()` in Strings

## `==` Operator – Compares Memory Address

The `==` operator checks whether **two reference variables point to the exact same object** in memory.

- For **primitive types** like `int`, it compares **values**.

- For **objects**, memory location (i.e., whether both variables refer to the **same object**).

```java
String s1 = new String("Rajeev");
String s2 = new String("Rajeev");

System.out.println(s1 == s2); // ❌ false – different memory locations
```

---

## `equals()` Method

The `Object` class defines `equals()` to compare references (just like `==`), but:

> 🔄 **Many classes override `equals()` to compare internal content**, like `String`.

---

### 🧵 In `String`: `equals()` is Overridden to Compare Characters

The `String` class overrides `equals()` to check for character-by-character equality.

```java
String s1 = new String("Rajeev");
String s2 = new String("Rajeev");

System.out.println(s1.equals(s2)); // ✅ true – same content
```

---

### 🚫 In `StringBuffer`: `equals()` is NOT Overridden

Since `StringBuffer` does **not override** `equals()`, it behaves like `==`, comparing references only.

```java
StringBuffer sb1 = new StringBuffer("Rajeev");
StringBuffer sb2 = new StringBuffer("Rajeev");

System.out.println(sb1 == sb2);        // ❌ false – different objects
System.out.println(sb1.equals(sb2));   // ❌ false – still reference comparison
```

✅ To compare content of `StringBuffer`, convert to `String`:

```java
System.out.println(sb1.toString().equals(sb2.toString())); // ✅ true
```

---

### 📝== `vs` equals() Summary

| Comparison Type          | `==` Operator                                  | `equals()` in `String`           | `equals()` in `StringBuffer` |
| ------------------------ | ---------------------------------------------- | -------------------------------- | ---------------------------- |
| Checks                   | Memory address                                 | Content (character-by-character) | Memory address               |
| Overridden?              | ❌ No                                           | ✅ Yes                            | ❌ No                         |
| Output (if content same) | `false` (same content but both objects differ) | ✅ True                           | `false`                      |
| To compare content       | Not suitable                                   | Use directly                     | Use `toString().equals()`    |

---

### ✅ Conclusion of `==` and equals()

- Use `equals()` when you care about **actual content**.

- For `StringBuffer`, convert to `String` before comparing values

---

# 🧪 Immutability vs Mutability

---

## 🔐 Immutable (String)

Once a `String` object is created in Java, its **content cannot be modified**.  
Any attempt to modify it results in the **creation of a new object** with the updated content.  
The original object remains unchanged.

```java
String s1 = new String("Rajeev");
s1.concat(" Singh");  // creates a new object, but we don’t assign it
System.out.println(s1); // Output: Rajeev
```

**🔍 What’s Happening?**

- `s1.concat(" Singh")` creates a **new `String` object** (`"Rajeev Singh"`), but we didn’t store the result.

- `s1` still points to the original `"Rajeev"` object.

- Hence, the output is `"Rajeev"` — the original string remains unchanged.

---

## 🔧 **StringBuffer (Mutable)**

Unlike `String`, a `StringBuffer` object **can be modified** after creation.  
All changes happen on the **same object** — no new object is created.

```java
StringBuffer sb1 = new StringBuffer("Rajeev");
sb1.append(" Singh");
System.out.println(sb1); // Output: Rajeev Singh
```

**🔍 What’s Happening?**

- `sb1.append(" Singh")` modifies the **existing `StringBuffer` object** in place.

- Since the object itself is updated, the result reflects the change immediately.

- Output is `"Rajeev Singh"`.

<img src="https://github.com/user-attachments/assets/99b43a18-10a2-4429-b726-98f484cbcb26" width="600" height="690">

---

## 🔄Immutable vs Mutable

| Feature               | `String` (Immutable)           | `StringBuffer` (Mutable)    |
| --------------------- | ------------------------------ | --------------------------- |
| Can content change?   | ❌ No — creates new object      | ✅ Yes — updates same object |
| Object behavior       | Fixed once created             | Can grow/modify dynamically |
| Memory use            | Higher (new object per change) | Lower (same object reused)  |
| Thread-safety         | ✅ Yes                          | ✅ Yes                       |
| Performance (updates) | Slower                         | Faster                      |

---

## 🔒 Important Conclusions About String Immutability

> **Immutability means** once an object is created, its internal state (content) **cannot be changed**. Any modification returns a **new object**, not a modified version of the original.

```java
String s1 = new String("spring");
String s2 = s1.toUpperCase();
String s3 = s1.toLowerCase();

System.out.println(s1 == s2);  // false – new object created
System.out.println(s1 == s3);  // true – content didn't change
```

✅ `s2` points to a **new object**, as `"spring"` was changed to `"SPRING"`.

✅ `s3` **still points to the same object** as `s1` since the lowercase content was unchanged.

---

## 🛠️ Creating Custom Immutable Class

You can build your own immutable class by:

- Declaring it `final`

- Making fields `private final`

- Avoiding setters

- Returning new instances on state changes

```java
final class ImmutableClass {
    private final int i;

    ImmutableClass(int i) {
        this.i = i;
    }

    public ImmutableClass modify(int newI) {
        if (this.i == newI) return this;
        return new ImmutableClass(newI);
    }

    public static void main(String[] args) {
        ImmutableClass i1 = new ImmutableClass(10);
        ImmutableClass i2 = i1.modify(100);
        ImmutableClass i3 = i1.modify(10);

        System.out.println(i1 == i2);  // false
        System.out.println(i1 == i3);  // true
    }
}
```

✅ This pattern ensures **no existing object is changed**, preserving immutability.

---

## 🔐 Final vs Immutability

| Concept          | Purpose                                       | Behavior                                   |
| ---------------- | --------------------------------------------- | ------------------------------------------ |
| `final` keyword  | Prevents reassignment of a reference variable | Doesn't stop object mutation               |
| **Immutability** | Prevents **modifying** object content         | New objects are created when state changes |

###### Example:

```java
final StringBuffer sb = new StringBuffer("Durga");
sb.append("Software");         // ✅ Works — content changed
// sb = new StringBuffer("Ravi"); ❌ Error — can't reassign final reference
```

🧠 **Key Insight**:

- `final` means the **reference can't change**,

- **but** the object itself can be changed unless it's immutable.

---

##### 🎯 When to Use What

✅ Use `final`:

- To protect references from being reassigned (e.g., constants).

- In method parameters or class members where reassignment is undesired.

✅ Use **immutability**:

- For objects that should **never be changed once created**.

- For thread-safe, side-effect-free behavior (e.g., caching, logging, keys in collections).

---

## 🧪 Conceptual Questions

---

### 📌 Code 1 – `concat` and `replace`

```java
public class Test {
    public static void main(String[] args) {
        String ta = "A";
        ta = ta.concat("B");      // AB
        String tb = "C";
        ta = ta.concat(tb);       // ABC
        ta.replace("C", "D");     // Has no effect — result not stored
        ta = ta.concat(tb);       // ABCC
        System.out.println(ta);   // Output: ABCC
    }
}
```

**Output :**

```java
ABCC
```

🧠 `.replace()` didn’t modify the object — a new one was created, but it wasn’t assigned.

![11](https://github.com/user-attachments/assets/2dbd0bcd-0a6b-471a-802a-b9c2aa01c83f)

---

### 📌 Code 2 – `trim` & `isEmpty`

```java
public class Test {
    public static void main(String[] args) {
        String str = " ";
        str.trim();  // Creates a new String, not assigned
        System.out.println(str.equals(""));  
        System.out.println(str.isEmpty());   
    }
}
```

**Output :** 

```java
false
false
```

Even after `.trim()`, original `str` still has one space.

---

### 📌 Code 3 – `indexOf`

```java
public class Test {
    public static void main(String[] args) {
        String str = "Hello World";
        str.trim();  // Has no effect — string has no leading/trailing space
        int i1 = str.indexOf(" ");
        System.out.println(i1);  
    }
}
```

**Output :**

```java
5
```

✔ `indexOf(" ")` gives 5, which is the index of the space.

---

### 📌 Code 4 – Comparing String with case difference

**To ensure the program prints `"Equal"` despite the difference in letter casing between `s1` and `s2`, which of the following options should replace `// Line 1`?**

```java
public class Test {
    public static void main(String[] args) {
        String s1 = "Java";
        String s2 = new String("java");

        // Line 1 – Insert the correct condition here
        {
            System.out.println("Equal");
        } else {
            System.out.println("Not Equal");
        }
    }
}
```

#### 📝 Options:

**A.**  
`String s3 = s2;`  
`if (s1 == s3)`

**B.**  
`if (s1.equalsIgnoreCase(s2))`

**C.**  
`String s3 = s2;`  
`if (s1.equals(s3))`

**D.**  
`if (s1.toLowerCase() == s2.toLowerCase())`

---

✅ **Correct Answer:** **B**

> Because `equalsIgnoreCase()` compares content without considering case, making it the correct way to check if `"Java"` and `"java"` are equal textually, ignoring case.

---

# ✅ Part 1 Recap – Core String Concepts

---

- **`String` is a class**, not a primitive — and it's declared `final`, so it **cannot be extended**.

- Every `String` is internally backed by a **character array (`char[]`)**, and its content is **immutable**.

- When created using **literals** (`String s = "Java";`), strings are stored in the **String Constant Pool (SCP)** and **reused** if identical.

- When created using `new String()`, a **new object is created in the Heap**, even if the same literal exists in the SCP — thus **bypassing the pool**.

- Immutability means: **any modification creates a new object** instead of altering the original.
  
  - This ensures **security**, **thread safety**, and **hashcode stability** (for maps and sets).

- `StringBuffer` was introduced as a **mutable** alternative to String — it's **synchronized**, so it’s **thread-safe**.

- `StringBuilder` (introduced in Java 1.5) is **non-synchronized** and **faster** for **single-threaded use cases**.

- When comparing strings:
  
  - Use `==` for **reference equality** (same memory location)
  
  - Use `equals()` for **content equality**
  
  - `StringBuffer` does not override `equals()`, so content must be compared using `.toString().equals()`

- You learned how to **create a custom immutable class** — with `final` + encapsulation + controlled object creation.

- `final` ≠ immutable: `final` prevents reference reassignment, not object mutation.
  
  - Example: a `final StringBuffer` can still be modified internally.

---

### 🔗 What’s Next in Part 2?

In Part 2, we’ll go **deeper into memory**:

- 🔍 Detailed understanding of **Heap vs String Constant Pool (SCP)**

- 🔄 Internal behavior of **interning**, memory sharing, and object identity

- 🤔 When and why you should (or shouldn’t) use `new String()`

- 📘 More real-world examples and interview scenarios
