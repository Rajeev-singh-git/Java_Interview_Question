# 📘  String – Introduction & Core Concepts

## Table of Contents

1. [String](#-type-casting)
   - [Properties of `String`](#%EF%B8%8F-parent-reference-holding-child-object-upcasting)
   - [Different ways to Create Strings](#dynamic-method-dispatch)
   - [Accessing Child-Specific Methods](#-accessing-child-specific-methods)
   - [Interface Reference Holding Child Object](#-interface-reference-holding-child-object)
   - [Syntax of Type Casting](#syntax-of-type-casting)
2. [Compile-Time Type Compatibility Rules](#-compile-time-type-compatibility-rules)
   - [Actual Object Type and Target Cast Type Must Be Related](#-rule-1-actual-object-type-and-target-cast-type-must-be-related)
   - [Target Cast Type Must Be Same or Subclass of Reference Variable's Type](#-rule-2-target-cast-type-must-be-same-or-subclass-of-reference-variables-type)(#-cohesion)

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

# 🧪 Immutability vs Mutability

#### Case 1: Once a string object is created in Java, its content cannot be modified. Any attempt to modify the string results in the creation of a new string object with the modified content.

```java
// String example (Immutable)
String s1 = new String("Rajeev");
s1.concat(" Singh");
System.out.println(s1); // Output: Rajeev

// StringBuffer example (Mutable)
StringBuffer sb1 = new StringBuffer("Rajeev");
sb1.append(" Singh");
System.out.println(sb1); // Output: Rajeev Singh

```

##### 🔍 What happened here?

- `s1.concat(" Singh")` returns a new object, but we didn’t assign it — so `s1` remains unchanged.

- s1 is still pointing to original i.e.. Rajeev

- `sb1.append(" Singh")` modifies the **original object**, so changes are visible directly.



---

# 🔁 `==` vs `equals()` in Strings

---

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

### 📝==`vs`equals()  Summary

| Comparison Type          | `==` Operator                                  | `equals()` in `String`           | `equals()` in `StringBuffer` |
| ------------------------ | ---------------------------------------------- | -------------------------------- | ---------------------------- |
| Checks                   | Memory address                                 | Content (character-by-character) | Memory address               |
| Overridden?              | ❌ No                                           | ✅ Yes                            | ❌ No                         |
| Output (if content same) | `false` (same content but both objects differ) | ✅ True                           | `false`                      |
| To compare content       | Not suitable                                   | Use directly                     | Use `toString().equals()`    |

---

### ✅ Conclusion

- Use `==` only when you want to check **object identity**.

- Use `equals()` when you care about **actual content**.

- For `StringBuffer`, convert to `String` before comparing values
