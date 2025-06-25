# 📈 **3. String Mutability, Performance & Builders – StringBuffer vs StringBuilder**

## Table of Contents

1. [Identifiers, Keywords and DataTypes](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Fundamental/concept/01_Java_Basics_Identifiers_Keywords_DataTypes.md)
2. [Arrays in Java](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Fundamental/concept/02_Array_Concepts_Internal_Memory.md)
3. [Variables & Execution Fundamentals](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/Fundamental/concept/03_Variables_Types_Operators_ControlFlow.md)

---

# 🧱 String Constructors

---

| Constructor                    | Description                                                         |
| ------------------------------ | ------------------------------------------------------------------- |
| `new String()`                 | Creates an empty string                                             |
| `new String(String literal)`   | Creates a string from another string literal.                       |
| `new String(StringBuilder sb)` | Creates a string from a `StringBuilder`                             |
| `new String(StringBuffer sb)`  | Creates a string from a `StringBuffer`                              |
| `new String(char[] ch)`        | Creates a string from a character array                             |
| `new String(byte[] b)`         | Creates a string from a byte array using platform’s default charset |

---

# 🔧 Important String Methods

---

###### ✅ `isEmpty()`: Checks if the string is empty

```java
String s = "";
System.out.println(s.isEmpty()); // true

String s = "Rajeev";
System.out.println(s.isEmpty()); // false
```

---

###### ✅ `charAt(int index)`: Gets character at a specific index

```java
String str = "Hello";
char ch = str.charAt(0);
System.out.println(ch); // Output: H
```

---

###### ✅ `concat(String str)`: Appends another string

```java
String str1 = "Hello";
String str2 = " World";
String result = str1.concat(str2);
System.out.println(result); // Output: Hello World
```

---

###### ✅ `equals(Object obj)`: Checks value equality (case-sensitive)

```java
String str1 = "Hello";
String str2 = "Hello";
System.out.println(str1.equals(str2)); // true
```

---

###### ✅ `equalsIgnoreCase(String str)`: Ignores case while comparing

```java
String str1 = "Hello";
String str2 = "hello";
System.out.println(str1.equalsIgnoreCase(str2)); // true
```

---

###### ✅ `length()`: Returns number of characters

```java
String str = "Hello";
System.out.println(str.length()); // Output: 5
```

---

###### ✅ `replace(char oldChar, char newChar)`: Replaces all occurrences

```java
String str = "Hello";
String replaced = str.replace('l', 'p');
System.out.println(replaced); // Output: Heppo
```

---

###### ✅ `substring(int beginIndex)`: From index to end

```java
String str = "Hello World";
String subStr = str.substring(6);
System.out.println(subStr); // Output: World
```

---

###### ✅ `substring(int beginIndex, int endIndex)`: From index to index-1

```java
String str = "Hello World";
String subStr = str.substring(6, 11);
System.out.println(subStr); // Output: World
```

---

###### ✅ `indexOf(char ch)`: First index of given character

```java
String str = "Hello World";
System.out.println(str.indexOf('o')); // Output: 4
```

---

###### ✅ `lastIndexOf(char ch)`: Last index of given character

```java
String str = "Hello World";
System.out.println(str.lastIndexOf('o')); // Output: 7
```

---

###### ✅ `toLowerCase()`: Converts all characters to lowercase

```java
String str = "HELLO";
System.out.println(str.toLowerCase()); // Output: hello
```

---

###### ✅ `toUpperCase()`: Converts all characters to uppercase

```java
String str = "hello";
System.out.println(str.toUpperCase()); // Output: HELLO
```

---

###### ✅ `trim()`: Removes leading and trailing whitespace

```java
String str = "   Hello   ";
System.out.println(str.trim()); // Output: Hello
```

---

# StringBuffer

---

In Java, `StringBuffer` is a mutable class used to create dynamic, changeable character sequences — unlike `String`, which is immutable.

---

### 🧵 When to Use:

Use `StringBuffer` when the **string content changes frequently**, especially inside loops or during dynamic content building.

---

### 🔐 Problem with `String`

- `String` is **immutable** — once created, its content **cannot be modified**.

- Every time you change a `String` (e.g., using `+` or `.concat()`), a **new object is created**.

- This leads to:
  
  - ❌ **Memory overhead**
  
  - ❌ **More garbage collection**
  
  - ❌ **Performance hit** in scenarios involving many changes

---

### ✅ How `StringBuffer` Solves It

- `StringBuffer` is **mutable** — changes are done **in-place** on the same object.

- No need to create a new object for every update.

- This makes it **faster and memory-efficient** when building or modifying strings dynamically.

---

### 🧪 Example Comparison

```java
// String (Inefficient for repeated changes)
String s = "Hello";
s = s + " World";   // Creates a new object each time

// StringBuffer (Efficient)
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World"); // Modifies the same object
```

---

### ✅ **Choose `StringBuffer`** when:

- You're performing **repeated string operations**

- Performance and memory efficiency matter

- You need **thread-safe** mutable strings

---

## 🔧 `StringBuffer` Constructors & Capacity

---

### 🧱 Default Constructor

```java
StringBuffer sb = new StringBuffer();
```

- ✅ Creates an empty buffer with **default capacity of 16 characters**.

- You can start appending characters immediately.

---

### 📈 Auto-Expansion Rule

When content exceeds current capacity:

```java
New Capacity = (Current Capacity + 1) * 2
```

➡️ A new internal array is created, and data is copied.

---

### 🧪 Example: Capacity Behavior

```java
StringBuffer sb = new StringBuffer();
System.out.println(sb.capacity());  // Output: 16

sb.append("ABCDEFGHIJKLMNO");       // 15 chars
System.out.println(sb.capacity());  // Output: 16 (still fits)

sb.append("PQ");                    // total = 17 chars
System.out.println(sb.capacity());  // Output: 34
```

---

### 🏗️ Constructor with Initial Capacity

```java
StringBuffer sb = new StringBuffer(int initialCapacity);
```

✅ Allows setting custom capacity from the start.

---

### 🧵 Constructor with String

```java
StringBuffer sb = new StringBuffer("Raj");
System.out.println(sb.capacity());  // Output: 19
```

---

### 📝 `StringBuffer` Constructors & Capacity Summary

| Constructor                      | Description                                                          |
| -------------------------------- | -------------------------------------------------------------------- |
| `new StringBuffer()`             | Creates empty buffer, capacity = 16                                  |
| `new StringBuffer(int capacity)` | Creates empty buffer with custom capacity                            |
| `new StringBuffer(String str)`   | Creates buffer with content of `str`, capacity = `str.length() + 16` |

---

## 🔧 `StringBuffer` Important Methods

#### ✅ **`public int length()`**

Returns the number of characters currently in the buffer.

```java
StringBuffer sb = new StringBuffer("Hello");
System.out.println("Length: " + sb.length()); // Output: 5
```

---

✅ **`public int capacity()`**  
Returns the total allocated size (capacity), not just content length.

```java
StringBuffer sb = new StringBuffer("Hello");
System.out.println("Capacity: " + sb.capacity()); // Output: 21
```

---

✅ **`public char charAt(int index)`**  
Returns the character at the given index.

```java
StringBuffer sb = new StringBuffer("Hello");
System.out.println("Character at index 1: " + sb.charAt(1)); // Output: e
```

---

✅ **`public void setCharAt(int index, char ch)`**  
Replaces the character at the specified index.

```java
StringBuffer sb = new StringBuffer("Hello");
sb.setCharAt(0, 'h');
System.out.println("After setCharAt: " + sb); // Output: hello
```

---

✅ **`public StringBuffer append(String str)`**  
Adds content to the end of the buffer.

```java
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World");
System.out.println("After append: " + sb); // Output: hello World
```

---

✅ **`public StringBuffer insert(int offset, String str)`**  
Inserts content at the specified index.

```java
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World");
sb.insert(6, ",");
System.out.println("After insert: " + sb); // Output: hello, World
```

---

✅ **`public StringBuffer delete(int start, int end)`**  
Deletes characters from `start` (inclusive) to `end` (exclusive).

```java
sb.delete(6, 8);
System.out.println("After delete: " + sb); // Output: helloorld
```

---

✅ **`public StringBuffer deleteCharAt(int index)`**  
Removes the character at the specified index.

```java
sb.deleteCharAt(5);
System.out.println("After deleteCharAt: " + sb); // Output: helloorld
```

---

✅ **`public StringBuffer reverse()`**  
Reverses the content of the buffer.

```java
sb.reverse();
System.out.println("After reverse: " + sb); // Output: dlrowolleh
```

---

✅ **`public void setLength(int newLength)`**  
Truncates or pads the buffer to the new length.

```java
sb.setLength(5);
System.out.println("After setLength: " + sb); // Output: dlrow
```

---

✅ **`public void ensureCapacity(int minCapacity)`**  
Expands the capacity if it's less than `minCapacity`.

```java
sb.ensureCapacity(50);
System.out.println("Capacity after ensureCapacity: " + sb.capacity()); // Output: 50
```

---

✅ **`public void trimToSize()`**  
Reduces the capacity to match the current length.

```java
sb.trimToSize();
System.out.println("Capacity after trimToSize: " + sb.capacity()); // Output: 5
```

---

# 🚀 StringBuilder

---

### 🧠 **Why `StringBuilder`?**

Java introduced `StringBuilder` in Java 1.5 as a non-synchronized alternative to `StringBuffer`. While `StringBuffer` ensures thread safety, it adds synchronization overhead that slows down performance in single-threaded scenarios. `StringBuilder` eliminates that, making it ideal when performance is critical and thread safety is not needed.

---

### 🔍 **StringBuffer vs. StringBuilder**

| Feature                | `StringBuffer`                             | `StringBuilder`                                |
| ---------------------- | ------------------------------------------ | ---------------------------------------------- |
| 🔒 **Synchronization** | All methods are synchronized (thread-safe) | Methods are not synchronized (not thread-safe) |
| 🐢 **Performance**     | Slower due to synchronization overhead     | Faster due to no thread contention             |
| 📅 **Introduced In**   | Java 1.0                                   | Java 1.5                                       |
| 🧵 **Thread Safety**   | Safe in multithreaded environments         | Unsafe in multithreaded environments           |
| ⚙️ **Use Case**        | When thread safety is important            | When thread safety is not needed               |
| 💡 **Purpose**         | Reliability in concurrent access           | Performance in single-threaded tasks           |

---

#### **Shared Functionality**

Constructors and methods from `StringBuffer` also apply to `StringBuilder` — except that `StringBuilder` methods are **not synchronized**.

---

# ⚖️ **Choosing Between String, StringBuffer, and StringBuilder**

| Feature              | `String`                                | `StringBuffer`                                                  | `StringBuilder`                                                         |
| -------------------- | --------------------------------------- | --------------------------------------------------------------- | ----------------------------------------------------------------------- |
| 🧾 **When to Use**   | When content is fixed or rarely changes | When content changes frequently **and** thread safety is needed | When content changes frequently **and** thread safety is **not** needed |
| 🔒 **Thread Safety** | Immutable, so inherently thread-safe    | Synchronized methods = thread-safe                              | No synchronization = not thread-safe                                    |
| 🚀 **Performance**   | Medium                                  | Lower due to locking                                            | Higher for single-threaded scenarios                                    |
| 🧠 **Notes**         | Used for constants, keys, etc.          | Suitable for logging across threads                             | Ideal for loops, string building                                        |
| 🔄 Mutable?          | ❌ No                                    | ✅ Yes                                                           | ✅ Yes                                                                   |

---

### 🔁 **Method Chaining in `StringBuilder`**

In method chaining, multiple method calls are executed from **left to right**, returning the same object after each call.

---

```java
public class MethodChaining {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        sb.append("durga")
          .append("solutions")
          .reverse()
          .insert(2, "xyz")
          .delete(3, 7);

        System.out.println(sb);  // Output: snxtulosagrud
    }
}
```

**Tip:** Most `StringBuilder` methods return the same instance — enabling chaining.

🧠 **Note:** This technique boosts readability and is widely used in fluent APIs.

```java
new StringBuilder("Hi")
  .append(" Rajeev")
  .insert(0, "Hello,")
  .delete(5, 7)
  .reverse();
```

💡 Chaining improves readability and avoids boilerplate.
