# Difference between String and StringBuffer

- **String:**
  - Immutable: Once a String object is created, its content cannot be changed.
  - Efficient for string literals and constant values.
  - String concatenation with the `+` operator creates a new String object, potentially leading to inefficiency for frequent modifications.
- **StringBuffer:**
  - Mutable: The content of a StringBuffer object can be modified after creation.
  - Useful for building strings dynamically through concatenation or other operations.
  - More memory overhead compared to String due to the ability to change content.

Table summarizing the key differences:

| Feature | String | StringBuffer |
| --- | --- | --- |
| Mutability | Immutable | Mutable |
| When to use | String literals, constant values | Building strings dynamically |
| Concatenation Efficiency | Less efficient (creates new String objects) | More efficient (modifies the same object) |
| Memory Overhead | Lower | Higher |

**Choosing Between String and StringBuffer:**

- Use String for constant values and string literals.
- Use StringBuffer when you need to modify a string frequently through concatenation or other operations.

**Additional Considerations:**

- Java introduced StringBuilder in Java 1.5. It's similar to StringBuffer but not thread-safe, making it faster for single-threaded environments.

## Case 1:

Once a string object is created in Java, its content cannot be modified. Any attempt to modify the string results in the creation of a new string object with the modified content.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/f57917f3-d6e6-45e3-8b91-b569440c7149/Untitled.png)

# == Operator vs equals method

1. **`==` Operator**:
  - The **`==`** operator in Java compares the references of two objects.
  - When used with objects (non-primitive types), **`==`** checks if the two references point to the same memory location, essentially determining whether they are the same object in memory.
  - For primitive types (like **`int`**, **`double`**, etc.), **`==`** compares their values.
2. **`equals()` Method**:
  - The **`equals()`** method is a method of the **`Object`** class in Java and is meant to be overridden by subclasses to provide custom equality checks.
  - By default, the **`equals()`** method provided by the **`Object`** class compares object references, similar to the **`==`** operator.
  - However, many classes override this method to compare the content or state of objects for equality.
  - For example, **`String`** class overrides **`equals()`** to compare the content of strings rather than their references.

## `equals()` method in String

- The **`equals()`** method in the String class is overridden to compare the content of two string objects.
- The **`==`** operator in Java indeed returns **`true`** only when both references point to the exact same object in memory. It compares the memory addresses of the objects, ensuring they are identical. If the two references point to different objects, even if their contents are the same, **`==`** will return **`false` .**

```java
  			String s1 = new String("Rajeev");
        String s2 = new String("Rajeev");

        System.out.println(s1==s2);             //false
        System.out.println(s1.equals(s2));      //true
```

## `equals()` method in StringBuffer

- The **`equals()`** method in the StringBuffer class is not overridden for content comparison.

```java
    		StringBuffer sb1 = new StringBuffer("Rajeev");
        StringBuffer sb2 = new StringBuffer("Rajeev");

        System.out.println(sb1==sb2);             //false
        System.out.println(sb1.equals(sb2));      //false
```

# Heap and String Constant Pool(SCP)

1. **Heap**:
  - The heap is the general-purpose runtime data area where objects are allocated.
  - Objects created using the **`new`** keyword are typically allocated on the heap.
  - It's a large, shared pool of memory that all Java threads have access to.
  - Objects in the heap are managed by the Java Virtual Machine (JVM) and are subject to garbage collection when they are no longer referenced.
2. **String Constant Pool (SCP)**:
  - The SCP is a special area within the heap dedicated to storing String literals.
  - String literals are sequences of characters enclosed in double quotes, like **`"hello"`**.
  - When the compiler encounters a string literal in the source code, it checks if an identical string already exists in the SCP.
  - If an identical string is found, the compiler does not create a new String object; instead, it reuses the existing one.
  - This sharing of String literals conserves memory and enables certain optimizations.
  - String literals in the SCP are immutable, meaning their value cannot be changed after they are created.


**Key Points:**

- The Heap is dynamically allocated, meaning its size can grow or shrink as needed during program execution.
- The SCP is created during program startup and remains fixed in size.
- While String literals in the SCP are immutable, objects created in the Heap using `new` can be mutable (their content can be changed).

## Scenario Based Explanation

**Scenario 1: String Literal ("hello")**

- When you create a String literal like `String s1 = "hello";`, **only one object** is created.
- This object resides in the **String Constant Pool (SCP)**, a special memory area within the Heap.
- The reference variable `s1` points directly to the SCP object containing the characters "h", "e", "l", "l", and "o".
- There's no separate object created in the general Heap area for this scenario.

**Scenario 2: new String("hello")**

- When you use `String s2 = new String("hello");`, things get a bit more nuanced:
  - The JVM first checks the SCP to see if "hello" already exists.
  - Since it does (from scenario 1), **no new object is created in the SCP**.
  - However, the `new` keyword forces the creation of a **new String object in the Heap**. This object is separate from the SCP object.
  - The reference variable `s2` points to this new Heap object.
- This might seem redundant, but using `new String` can have specific use cases, like creating modifiable String content (which is not possible with SCP Strings).

**Scenario 3: Reusing Existing String Literal ("hello")**

- If you later create **`String s3 = new String("hello");`**, here's what happens:
  - The JVM again checks the SCP and finds "hello" already exists.
  - **No new object is created in the SCP**.
  - However, since **`new String("hello")`** is used, a new **`String`** object is created on the heap.
  - The reference variable **`s3`** points to this new **`String`** object in the heap, not to the existing SCP object.
- This behavior optimizes memory usage by reusing String objects in the SCP when possible, while still allowing for explicit heap allocation when **`new`** is used.

**Key Points:**

- String literals in the SCP are generally preferred for memory efficiency.
- The `new String` keyword can create a separate Heap object even if the String literal exists in the SCP.
- The SCP helps reduce memory usage by reusing String objects for identical String literals.
- Heap: Multiple objects with the same content can exist.
- SCP: Only one object with the same content will be stored, and the same object will be reused when the content matches.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/bb0ed545-767b-449a-a503-27b8c00f84ff/Untitled.png)

### Example 1 :

```java
	      String s1 = new String("Rajeev");
        String s2 = new String("Rajeev");
	      String s3 = "Rajeev";
        String s4 = "Rajeev";
        

        System.out.println(s1==s2);             //false
        System.out.println(s3==s4);             //true
```

In total, there are three objects created:

- Two objects on the heap (**`s3`** and **`s4`**).
- One object in the SCP (shared by **`s1`** and **`s2`**).

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/44f90b36-e9e8-48b7-883a-5c06235aa43f/Untitled.png)

## Example 2 :

```java
        String s1 = new String("Spring");
        s1.concat("Fall");
        String s2 = s1.concat("Winter");
        s2.concat("Summer");

        System.out.println(s1);   //Spring
        System.out.println(s2);   //SpringWinter
```

- Total 8 object will be created, but s1 will point to "Spring" in heap and
  "S2" will point to "SpringWinter" in heap.
- **`String s2 = s1.concat("Winter")`**; and **`s2.concat("Summer");`**
  This creates a new String object "SpringWinter" on the heap by concatenating the contents of s1 ("Spring") and "Winter".
- new object is not assigned to any variable and is eligible for garbage collection. No change is made to s1.
- `concat()`  creates a new String object and doesn't modify the original String.
- However, like before, this new object is not assigned to any variable and is eligible for garbage collection. No change is made to s2.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/48bcc85b-4490-4841-b82a-c285225ba60b/Untitled.png)

## Example 3

```java
	      String s1 = new String("You cannot change me");
        String s2 = new String("You cannot change me");
        System.out.println(s1==s2);             // false
        String s3 = "You cannot change me";
        System.out.println(s1==s3);             // false
        String s4 = "You cannot change me";
        System.out.println(s3==s4);             // true
        String s5 = "You cannot " +  "change me";
        System.out.println(s4==s5);             // true
        String s6 = "You cannot ";
        String s7 = s6 +  "change me";
        System.out.println(s4==s7);             // false
        final String s8 = "You cannot ";
        System.out.println(s6==s8);             // true
        String s9 = s8 +"change me";
        System.out.println(s4==s9);             //true
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/79d6d6f1-e21a-4afa-a684-23f9f53fb333/Untitled.png)