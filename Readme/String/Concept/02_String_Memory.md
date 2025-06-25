# 📦2. String Memory Model – Heap, SCP & Interning

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

The **String Constant Pool** is a special memory region within the heap where **Java stores unique string literals**. When a string literal appears in code, the JVM places it in the SCP to avoid creating duplicate objects, thus saving memory and improving performance.

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

### 📘 **Examples**

---

#### 📘 **Example 1: Heap vs SCP Behavior with String Literals and `new` Keyword**

```java
String s1 = new String("Rajeev");
String s2 = new String("Rajeev");
String s3 = "Rajeev";
String s4 = "Rajeev";

System.out.println(s1 == s2); // ❌ false — two separate heap objects
System.out.println(s3 == s4); // ✅ true  — both point to the same SCP object
```

###### 🧠 Memory Behavior:

![1](https://github.com/user-attachments/assets/acc81fe9-04b2-46f0-9f97-0ee39a9c24b4)

- `s1` → Creates a **new object in the Heap**  
  Also triggers creation of `"Rajeev"` in the **String Constant Pool (SCP)** (if not already present).

- `s2` → Creates another **new object in the Heap**  
  SCP is **reused** — `"Rajeev"` already exists, so no new SCP object is created.

- `s3`, `s4` → **No new objects** are created  
  Both directly point to the existing `"Rajeev"` in the **SCP**

---

###### 🧮 Total Objects Created: **3**

- 🟡 **2 in Heap** → via `new String("Rajeev")`

- 🔵 **1 in SCP** → `"Rajeev"` (shared by `s3` and `s4`)

---

#### ✅ Example 2: `concat()` and String Immutability

```java
String s1 = new String("Spring");
s1.concat("Fall");
String s2 = s1.concat("Winter");
s2.concat("Summer");

System.out.println(s1); // Spring
System.out.println(s2); // SpringWinter
```

###### 🧠 Memory Behavior : Total Objects Created: 8

![2](https://github.com/user-attachments/assets/4404889d-b7ec-479c-b649-f65df491e82a)

- `String s1 = new String("Spring");`  
  🔹 Creates **two objects**:
  
  - `"Spring"` in the **SCP** (since it's a string literal)
  
  - A **new object in the Heap** with content `"Spring"` → referenced by `s1`

- `s1.concat("Fall");`  
  🔹 `"Fall"` (a string literal) is stored in the **SCP**  
  
  🔹 It doesn't modify original string reference by s1.
  🔹 A **new String object in the Heap** is created→ `"SpringFall"`  
  ❌ Not assigned to any reference → becomes **unreferenced**

- `String s2 = s1.concat("Winter");`  
  🔹 `"Winter"` (a string literal) is stored in the **SCP**  
  🔹 A **new Heap object** is created → `"SpringWinter"` → assigned to `s2`

- `s2.concat("Summer");`  
  🔹 `"Summer"` (a string literal) is stored in the **SCP**  
  🔹 A **new Heap object** is created → `"SpringWinterSummer"`  
  ❌ Not assigned → becomes **unreferenced**

###### 🔑 Key Takeaways:

- All **string literals** (`"Spring"`, `"Fall"`, `"Winter"`, `"Summer"`) are stored in the **SCP** — one copy per unique literal.

- Every call to `.concat()` creates a **new Heap object**, not a modified version of the original string.

- If the result of `.concat()` is **not assigned**, the object becomes **unreachable** and eligible for **garbage collection**.

---

#### ✅ Example 3: Compile-Time vs Runtime Concatenation

```java
String s1 = new String("You cannot change me");
String s2 = new String("You cannot change me");
System.out.println(s1 == s2); 

String s3 = "You cannot change me";
System.out.println(s1 == s3); 

String s4 = "You cannot change me";
System.out.println(s3 == s4); 

String s5 = "You cannot " + "change me";
System.out.println(s4 == s5); 

String s6 = "You cannot ";
String s7 = s6 + "change me";
System.out.println(s4 == s7);

final String s8 = "You cannot ";
System.out.println(s6 == s8); 

String s9 = s8 + "change me";
System.out.println(s4 == s9); 
```

**Output : -->**

```java
false  
false  
true  
true  
false  
true  
true
```

#### Memory Behavior:

![3](https://github.com/user-attachments/assets/78bce15f-7254-4f3d-b1fd-52297a923847)

#### 🔸 `String s1 = new String("You cannot change me");`

Two objects created:

- 1 in **SCP** for the literal `"You cannot change me"`

- 1 in **Heap** due to `new`

✅ **Created**: 1 SCP object + 1 Heap object

---

#### 🔸 `String s2 = new String("You cannot change me");`

- `"You cannot change me"` already exists in SCP → reused

- `new` creates another object in **Heap**

✅ **Created**: 1 Heap object

---

#### 🔸 `String s3 = "You cannot change me";`

- Reuses existing SCP literal

✅ **Created**: None

---

#### 🔸 `String s4 = "You cannot change me";`

- Same literal reused from SCP

✅ **Created**: None

---

#### 🔸 `String s5 = "You cannot " + "change me";`

- Both are **literals** → compiler performs **constant folding**

- Folded result: `"You cannot change me"` → reused from SCP

- `"You cannot "` and `"change me"` are **not** added to SCP here (unless used separately)

✅ **Created**: None

---

#### 🔸 `String s6 = "You cannot ";`

- Literal directly used → added to SCP

✅ **Created**: 1 SCP object

---

#### 🔸 `String s7 = s6 + "change me";`

- `"change me"` is a literal → added to SCP

- `s6` is a variable → results in **runtime concatenation** (even if 1 variable is there then operation will be performed at runtime).

- New object with content `"You cannot change me"` is created in **Heap**

✅ **Created**: 1 SCP object + 1 Heap object

---

#### 🔸 `final String s8 = "You cannot ";`

- Being `final`, it is a **compile-time constant** (final variable is replaced at compile time only as final variable are contant and can't be changed).

- Reuses existing SCP literal

✅ **Created**: None

---

#### 🔸 `String s9 = s8 + "change me";`

- `s8` is `final` → compiler performs **compile-time concatenation**

- Result is `"You cannot change me"` → already exists in SCP

✅ **Created**: None

---

###### 📦 Object Creation Summary

| Location | Objects Created                                                                           |
| -------- | ----------------------------------------------------------------------------------------- |
| **SCP**  | `"You cannot change me"` (L1), `"You cannot "` (L6), `"change me"` (L7) → ✅ **3 objects** |
| **Heap** | `s1`, `s2`, runtime concat result (`s7`) → ✅ **3 objects**                                |

---

### 🚀 Importance of the String Constant Pool (SCP)

Java provides a **String Constant Pool (SCP)** to optimize memory and performance for string handling. Here's why it's important:

---

###### 1. Memory Efficiency & Performance

- The JVM stores only **one copy** of each string literal in the SCP.

- Multiple variables referring to the same literal will **reuse the same memory**.

✅ This saves memory and reduces garbage collection overhead.

---

###### 2. Immutability Enables Safe Sharing

- Strings in SCP are **immutable** — they can’t be changed after creation.

- Even if multiple references point to the same literal, **no one can modify it**.

✅ Ensures safe sharing without side effects.

---

###### 3. Thread Safety

- Since strings are immutable, they are **inherently thread-safe**.

- Multiple threads can access the same SCP string safely **without synchronization**.

---

###### 4. String Interning

- Java allows manual placement of a string into the SCP using `.intern()`:

```java
String s1 = new String("Java");
String s2 = s1.intern(); // s2 now points to SCP
```

✅ Useful in large-scale applications (like compilers or parsers) with many duplicate strings.

---

### ⚖️ Advantages vs Disadvantages of SCP

| ✅ Advantages                       | ⚠️ Disadvantages (Why Immutability is Needed)                          |
| ---------------------------------- | ---------------------------------------------------------------------- |
| Saves memory via reuse             | If strings were mutable, changes via one reference would affect others |
| Improves performance               | Immutability prevents accidental or malicious updates                  |
| Enables string sharing across code | Can’t store mutable objects like `StringBuffer`                        |
| Simplifies thread-safe programming | SCP is effective **only for immutable** objects                        |

---

### Why SCP is Only for `String`, Not `StringBuffer`  ❓

###### 🔐 1. Immutability

- `String` is immutable → safe for reuse.

- `StringBuffer` is mutable → unsafe to share across multiple references.

If `StringBuffer` were in SCP, one thread’s modification would affect all others. ⚠️

---

###### 🚀 2. Use Case Difference

- `String` is used for stable values like keys, messages, config.

- `StringBuffer` is used for **dynamic, changeable** content.

SCP is built for stability and reuse, **not for modification**.

---

###### 🧵 3. Thread Safety by Design

- `String`: No sync needed due to immutability.

- `StringBuffer`: Uses synchronized methods — SCP would be inefficient.

---

### Why `String` is Immutable but `StringBuffer` is Not ?

###### 🔒 Why `String` is Immutable:

1. **Shared References (SCP Safety)**:  
   Multiple variables can point to the same String literal.  
   ➤ If one could modify it, it would affect **all others**.  
   ➤ Immutability **prevents unintended side effects**.

2. **Security**:  
   Used in sensitive APIs (e.g., file paths, class loaders).  
   ➤ Prevents external code from modifying validated data.

3. **Thread-Safety**:  
   No sync needed — safe to use across threads.

4. **HashCode Caching**:  
   Once computed, safely reused (used in HashMap, Set).

---

###### ✏️ Why `StringBuffer` is Mutable:

1. Built for **frequent string modifications**.

2. Uses **internal char array** that can be changed.

3. **Synchronized**, so safe for multithreading.

4. More **memory and performance-efficient** for dynamic strings.

---

| Feature      | `String` (Immutable) | `StringBuffer` (Mutable) |
| ------------ | -------------------- | ------------------------ |
| Can change?  | ❌ No                 | ✅ Yes                    |
| Thread-safe? | ✅ Yes                | ✅ Yes (via sync)         |
| Performance  | ❌ Slower (new obj)   | ✅ Faster for changes     |
| Use case     | Constants, keys      | Dynamic string ops       |

---

### 🔎 Are There Other Immutable Classes in Java?

Yes — Java provides many immutable classes:

- 🔢 **Wrapper classes**: `Integer`, `Double`, `Character`, etc.

- 💰 **BigInteger**, **BigDecimal**

- 🕒 **java.time** API: `LocalDate`, `LocalTime`, `Duration`, `Period`

Immutability ensures **thread safety, caching, and predictability**, which is why many core Java APIs are designed this way.

---

## intern()

---

### 🧠 What is `intern()` in Java?

The `intern()` method is a special method of the `String` class that tells the JVM:

> “Please move or link this string to the **String Constant Pool (SCP)**, if not already present.”

---

###### 🔧 Syntax:

```java
String interned = str.intern();
```

---

###### 🧪 Example:

```java
String s1 = new String("hello");
String s2 = "hello";

System.out.println(s1 == s2);           // ❌ false — s1 is from heap, s2 is from SCP

String s3 = s1.intern();

System.out.println(s3 == s2);           // ✅ true — both now refer to SCP object
```

---

### 🔍 How it Works Internally:

- When `.intern()` is called, JVM checks if the string content **already exists in SCP**:
  
  - ✅ If yes, returns reference to existing SCP object.
  
  - ❌ If not, adds it to SCP and returns the reference.

---

### 🔁 Use Case: When and Why to Use `intern()`

###### Memory Optimization (Especially in Large Apps)

In apps with many repeated strings (e.g., XML/JSON parsers, database keys, compilers):

- Instead of storing many identical strings in heap,

- Interned strings ensure **only one instance is stored in SCP**.

###### Reference Equality Checks

Sometimes you care whether two strings **point to the exact same object**, not just whether their content is equal:

```java
if (s1.intern() == s2.intern()) {
    // Safe reference equality check via SCP
}
```

---

##### ⚠️ Points to Remember

| Concept             | Explanation                                                                           |
| ------------------- | ------------------------------------------------------------------------------------- |
| Immutable required  | Only works with `String` (not `StringBuilder` or `StringBuffer`) due to immutability. |
| Performance gain    | Saves memory by eliminating duplicate strings.                                        |
| Not auto-interned   | `new String()` does NOT go to SCP unless `.intern()` is explicitly called.            |
| SCP size is limited | Excessive interning can fill up Metaspace (before Java 8, PermGen).                   |

---

##### Use `intern()` when:

- You're working with **many duplicate strings**

- You need **reference equality**

- You want to **optimize memory usage** deliberately

---

# Summary --- Key Takeaways

- `Heap` holds all regular objects created with `new`, including Strings.

- `SCP` stores unique string literals and interned strings for memory efficiency.

- `==` compares reference, `.equals()` compares content.

- Avoid `new String(...)` unless necessary.

- Use `.intern()` when optimizing memory in apps with many repeated strings.

- Compile-time string concatenation results in SCP reuse, while runtime concatenation creates new heap objects.

- Only immutable objects can live safely in SCP — hence no `StringBuffer` or `StringBuilder`.
