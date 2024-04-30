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

![s1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/efcf1a75-0f6f-4f46-a12b-5b9493d78341)


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

![s2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/194a48bd-7e6e-4749-945f-b5c676dee3b9)


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


![s3](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/16ead566-572e-4698-bc2d-cdcdc2de1f24)

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

![s4](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/eec2faa9-3b8c-48e5-a93f-7509e297be17)


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
![s5](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/069a4f77-0a5e-4a7d-99bc-6e125adfa7bb)


# Importance of String Constant Pool (SCP)

1. **Performance and Memory Utilization: By storing unique string literals in the SCP and allowing multiple references to point to the same object, Java optimizes memory usage. This prevents unnecessary duplication of string objects, leading to more efficient memory utilization, especially in scenarios where the same string is used multiple times.**
2. **Immutability and Consistency: Java strings are immutable, meaning their values cannot be changed after they are created. This ensures consistency and predictability in the program's behavior. When a string is shared among multiple references in the SCP, any attempt to modify its content through one reference will result in the creation of a new string object with the modified content. This preserves the integrity of the original string and prevents unintended side effects in other parts of the program.**
3. **Thread Safety: Immutable strings are inherently thread-safe, making them ideal for concurrent programming. Multiple threads can safely access and use shared string objects from the SCP without the risk of concurrent modification, simplifying multi-threaded development and reducing the need for explicit synchronization.**
4. **String Interning: Java provides a mechanism for string interning, allowing developers to explicitly place strings into the SCP. This can be useful for optimizing memory usage and improving performance in scenarios where a large number of string objects are created.**

## Advantage and Dis-advantage of SCP

| Advantage of SCP | Disadvantage of SCP |
| --- | --- |
| Same object can be used multiple times instead of creating new object. | By attempting to change the object's content through one reference, all remaining references to the same object will be affected. Immutability was introduced to prevent unintended modifications. |
| Memory will be saved |  |
| Performance will be improved |  |

## Questions

### Why SCP concept is only applicable for string object but not for StringBuffer?

1. **Immutability:**
  - Strings in Java are immutable, meaning their content cannot be changed after creation. This characteristic makes them ideal candidates for the SCP. Since the content remains fixed, the SCP can store a single copy and allow multiple references to point to it.
  - StringBuffer objects, on the other hand, are mutable. Their content can be modified after creation using methods like `append` or `insert`. This mutability makes it impractical to store them in the SCP because the content could potentially change, requiring updates to all references pointing to the same object.
2. **Performance Optimization:**
  - The SCP is designed to optimize memory usage and performance for frequently used string literals. String objects are often used for storing text and messages, making them prime candidates for this optimization.
  - StringBuffer objects are primarily used for constructing strings that are modified frequently. The overhead of managing multiple references in the SCP wouldn't be beneficial in this scenario.
3. **Thread Safety:**
  - Immutable strings are inherently thread-safe because their content cannot be accidentally modified by multiple threads. This makes them suitable for concurrent programming environments where the SCP can be accessed by multiple threads simultaneously.
  - StringBuffer objects are mutable and require synchronization mechanisms like synchronized methods to ensure thread safety when accessed by multiple threads. This eliminates the advantage of pre-storing them in the SCP.

In summary, the SCP is designed to leverage the immutability of String objects for efficient memory management and performance optimization in common string usage scenarios. StringBuffer objects, with their mutability and focus on string construction, don't benefit from this approach.

### Why String Objects are immutable wheras StringBuffer objects are mutable?

In String because of SCP, same object can be used by multiple reference.By attempting to change the object's content through one reference, all remaining references to the same object will be affected. Immutability was introduced to prevent unintended modifications.

In stringBuffer there is no SOP, so each time new object is created.

### Except String, are any other object immutable in java?

In Java, besides **`String`**, several other objects are immutable. These include:

1. Wrapper classes (e.g., **`Integer`**, **`Double`**, **`Boolean`**, etc.)
2. **`BigInteger`**
3. **`BigDecimal`**
4. **`LocalDate`**, **`LocalTime`**, **`LocalDateTime`**, and other classes from the **`java.time`** package
5. **`Duration`** and **`Period`** from the **`java.time`** package

# String Constructors

1. **`String s = new String();`**: Creates an empty string object.
2. **`String s = new String(String literal);`**: Creates a string object with the content of the specified string literal.
3. **`String s = new String(StringBuilder sb);`**: Creates a string object with the content of the specified **`StringBuilder`** object.
4. **`String s = new String(StringBuffer sb);`**: Creates a string object with the content of the specified **`StringBuffer`** object.
5. **`String s = new String(char[] ch);`**: Creates a string object with the content of the specified character array.
6. **`String s = new String(byte[] b);`**: Creates a string object with the content of the specified byte array, using the platform's default charset.

# Important Methods

Certainly, here are the method names along with example code:

1. **`isEmpty()`:** Checks whether the string is empty or not.

    ```java
    javaCopy code
    String s = "";
    System.out.println(s.isEmpty()); // True
    
    String s = "Rajeev";
    System.out.println(s.isEmpty()); // False
    
    ```

2.  **`charAt(int index)`:** Returns the character at the specified index in the string.

```java
String str = "Hello";
char ch = str.charAt(0); // Retrieves the character at index 0
System.out.println(ch); // Output: H
```

1. **`concat(String str)`:** Concatenates the specified string to the end of this string.

```java
String str1 = "Hello";
String str2 = " World";
String result = str1.concat(str2); // Concatenates two strings
System.out.println(result); // Output: Hello World
```

1.  **`equals(Object obj)`:** Compares this string to the specified object.

```java
String str1 = "Hello";
String str2 = "Hello";
boolean isEqual = str1.equals(str2); // Checks if two strings are equal
System.out.println(isEqual); // Output: true
```

5.  **`equalsIgnoreCase(String anotherString)`:** Compares this **`String`** to another **`String`**, ignoring case considerations.

```java

String str1 = "Hello";
String str2 = "hello";
boolean isEqual = str1.equalsIgnoreCase(str2); // Ignores case while checking equality
System.out.println(isEqual); // Output: true
```

1. **`length()`:** Returns the length of this string.

```java
String str = "Hello";
int len = str.length(); // Gets the length of the string
System.out.println(len); // Output: 5
```

7.  **`replace(char oldChar, char newChar)`:** Returns a new string resulting from replacing all occurrences of **`oldChar`** in this string with **`newChar`**.

```java

String str = "Hello";
String replaced = str.replace('l', 'p'); // Replaces 'l' with 'p'
System.out.println(replaced); // Output: Heppo

```

1. **`substring(int beginIndex)`:** Returns a new string that is a substring of this string, starting from the specified index.

```java
String str = "Hello World";
String subStr = str.substring(6); // Retrieves substring starting from index 6
System.out.println(subStr); // Output: World
```

9 . **`substring(int beginIndex, int endIndex)`:** Returns a new string that is a substring of this string, starting from the specified **`beginIndex`** and ending at **`endIndex-1`**.

```java
String str = "Hello World";
String subStr = str.substring(6, 11); // Retrieves substring from index 6 to 10
System.out.println(subStr); // Output: World

```

1.  **`indexOf(int ch)`:** Returns the index within this string of the first occurrence of the specified character.

```java
String str = "Hello World";
int index = str.indexOf('o'); // Finds the index of 'o'
System.out.println(index); // Output: 4
```

1.  **`lastIndexOf(int ch)`:** Returns the index within this string of the last occurrence of the specified character.

```java
String str = "Hello World";
int lastIndex = str.lastIndexOf('o'); // Finds the last index of 'o'
System.out.println(lastIndex); // Output: 7
```

12.  **`toLowerCase()`:** Converts all of the characters in this **`String`** to lower case.

```java
String str = "HELLO";
String lowerCase = str.toLowerCase(); // Converts string to lowercase
System.out.println(lowerCase); // Output: hello
```

13.  **`toUpperCase()`:** Converts all of the characters in this **`String`** to upper case.

```java
String str = "hello";
String upperCase = str.toUpperCase(); // Converts string to uppercase
System.out.println(upperCase); // Output: HELLO
```