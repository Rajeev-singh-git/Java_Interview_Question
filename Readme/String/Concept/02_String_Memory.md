# Java Fundamental

## Table of Contents

1. [Identifiers, Keywords and DataTypes](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Fundamental/concept/01_Java_Basics_Identifiers_Keywords_DataTypes.md)
2. [Arrays in Java](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Fundamental/concept/02_Array_Concepts_Internal_Memory.md)
3. [Variables & Execution Fundamentals](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Fundamental/concept/03_Variables_Types_Operators_ControlFlow.md)

---

# 🧠 Heap

---

- The **Heap** is the general-purpose **runtime memory** used to store **objects** and **class instances**.

- Objects created using the `new` keyword are typically allocated in the heap.

- It is a **shared memory area** accessible by all threads.

- Managed by the **JVM's Garbage Collector (GC)**, which reclaims memory occupied by unreachable objects.

- The heap is **dynamically allocated**—its size can **grow or shrink** during program execution.

---

# String Constant Pool (SCP)

---

- The **SCP** is a **special area within the heap** reserved for **String literals**.

- It stores **only one copy** of each distinct literal string to optimize memory usage.

- String literals are sequences enclosed in **double quotes**, e.g., `"hello"`.

#### 🔍 How It Works

- When the compiler encounters a literal (e.g., `String s1 = "hello";`):
  
  - It checks if the same value already exists in the SCP.
  
  - If yes, the reference reuses the **existing object**.
  
  - If no, a **new literal object** is created in the SCP.

- SCP objects are **immutable**, ensuring safe reuse across the application.

---

## 🧵 Differences betwen  Heap and SCP

| Feature            | Heap                          | String Constant Pool (SCP)                |
| ------------------ | ----------------------------- | ----------------------------------------- |
| Stores             | All types of objects          | Only String literals and interned Strings |
| Location (Java 7+) | Java Heap                     | Inside the Java Heap                      |
| Memory reuse       | ❌ No                          | ✅ Yes (deduplicated literals)             |
| Created by         | `new` keyword or runtime      | Compiler or `intern()`                    |
| Garbage Collection | ✅ Yes                         | ✅ Yes (but less frequent)                 |
| Mutability         | Mutable and immutable objects | Immutable only                            |

---

### 🔄 Scenario-Based Explanation – Heap vs SCP in Java

---

#### 🟩 **Scenario 1: Basic String Literal (SCP-Only Creation)**

```java
String s1 = "hello";
```

🧠 **Memory Behavior:**

- A single object `"hello"` is created in the **String Constant Pool (SCP)**.

- The reference variable `s1` directly points to the SCP object.

- No heap object is created.

📌 This is the **most memory-efficient** way to create a string.

-![1](https://github.com/user-attachments/assets/47686743-ffeb-4913-9c8d-1796086aeb40)

---

#### 🟦 **Scenario 2: Literal + `new` Constructor (SCP + Heap)**

**Purpose:** Creating a separate heap object even for the same content.

```java
String s1 = "hello";                 // ✅ SCP object created
String s2 = new String("hello");     // ✅ New heap object with same content

System.out.println(s1 == s2);        // ❌ false (different references)
System.out.println(s1.equals(s2));   // ✅ true  (same content)
```

🧠 **Memory Behavior:**

1. **`String s1 = "hello";`**
   
   - Creates the string `"hello"` in the **String Constant Pool (SCP)** if not already present.
   
   - `s1` points to the SCP object.

2. **`String s2 = new String("hello");`**
   
   - A **new `String` object is created in the Heap** with the **same content** as `"hello"`.
   
   - `s2` points to this new heap object, which is **independent** of the SCP object.

![2](https://github.com/user-attachments/assets/4fc5c33a-4201-488b-9233-72c43f1a7f04)

---

#### 🟨 **Scenario 3: Multiple Heap Objects with Same Literal**

```java
String s1 = "hello";
String s2 = new String("hello");
String s3 = new String("hello");

s1 == s2    // ❌ false — SCP vs Heap
s1.equals(s2) // ✅ true — same content

s2 == s3    // ❌ false — two separate heap objects
s2.equals(s3) // ✅ true — same content
```

🧠 **Memory Behavior:**

- `String s1 = "hello";`  
  → Creates the string `"hello"` in the **String Constant Pool (SCP)** (if not already present).  
  → `s1` points to the SCP object.

- `String s2 = new String("hello");`  
  → A **new `String` object is created in the Heap** with the same content as `"hello"`.  
  → `s2` points to this new heap object, which is **independent** of the SCP object.

- `String s3 = new String("hello");`  
  → Another **new `String` object is created in the Heap**, again with the same content.  
  → `s3` points to a **separate heap object**, distinct from both `s1` and `s2`.

![3](https://github.com/user-attachments/assets/07c05d3a-09ef-456c-87ad-72e15a9c1c43)

---

##### 🧩 Key Points on Heap vs String Constant Pool (SCP)

- ✅ **SCP (String Constant Pool)** is preferred for memory efficiency — it avoids duplicate string objects.

- 🚫 Using `new String("...")` **always creates a new object in the Heap**, even if the same string exists in the SCP.

```java
String s = new String("hello");
```

- - Creates:
    
    - One object in **heap** (explicit via `new`).
    
    - One object in **SCP** (implicit, only if not already present).

- ♻️ **SCP reuses objects**: If a literal with the same content already exists, no new object is created — **reference to existing SCP object is returned**.

- 🔁 **Heap can hold duplicate content**: Multiple distinct `String` objects in heap may contain the **same characters**, but occupy **separate memory locations**.

- 🔒 **SCP enforces uniqueness**: Only one object per literal content is stored, and reused wherever needed.

![4](https://github.com/user-attachments/assets/2b77cd0a-a980-40c3-bdd7-b344fdb1080a)

---
