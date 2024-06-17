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


![s1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/47c1f94c-d305-4391-911e-453f8b69526f)


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

14. `**trim()**` : Removes leading and trailing whitespace

```java
String str = "   Hello   ";
String trimmed = str.trim(); // 
System.out.println(trimmed); // Output: Hello
```

# Important Conclusion about String Immutability

Immutability : Once object is created, we cannot change it’s content. If we perform any change with those changes a new object will be created.

```java
 	      String s1 = new String("spring");
        String s2 = s1.toUpperCase();
        String s3 = s1.toLowerCase();

        System.out.println(s1==s2);  //false
        System.out.println(s1==s3);  //true
```

- While assigning value to S2 String, the content changed from lowercase to Uppercase, so internally s2 will point to new content in the heap area.
- While assigning value to s3, there is no change in content of s1 since it’s already in small case so both will refer to same memory address.

![s7](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/ca71c668-3637-4146-929b-f4c8bc58626d)


# Creating Our won immutable class

```java
package String;

final class ImmutableClass {

    private int i;

    ImmutableClass(int i){
        this.i=i;
    }

    public ImmutableClass modify(int i){
        if(this.i==i){
            return this;
        }else{
            return new ImmutableClass(i);
        }
   }

   public static void main(String []args){
        ImmutableClass i1 = new ImmutableClass(10);
        ImmutableClass i2 = i1.modify(100);
        ImmutableClass i3 = i1.modify(10);

        System.out.println(i1==i2);  // false
        System.out.println(i1==i3);  // true
   }

}

```

# Final vs Immutability

- Immutability concerns the object itself, ensuring that its state cannot be changed after creation.
- Final applies to reference variables, preventing them from being reassigned to a new object.
- Code Example:

    ```java
    class Test {
        public static void main(String[] args) {
            final StringBuffer sb = new StringBuffer("Durga");
            sb.append("Software");
            System.out.println(sb);
            //sb = new StringBuffer("ravi") // final variable reference can't be reassigned
        }
    }
    ```

- In this example, **`sb`** is a final reference to a **`StringBuffer`** object. While **`sb`** cannot be reassigned to a new object due to the **`final`** keyword, the content of the **`StringBuffer`** object itself can still be modified.
- Therefore, **`final`** prevents reassignment of the reference variable, but it doesn't inherently ensure immutability of the object it references.
- Immutability, on the other hand, focuses on the object's state, aiming to maintain its integrity throughout its lifetime.
- By combining **`final`** with immutability techniques, such as encapsulation and avoiding mutator methods, a more robust and predictable codebase can be achieved.
- **Use `final` when:**
  - You want to prevent accidental reassignment of a reference variable.
  - The variable holds a constant value.
- **Use immutability when:**
  - You want an object's state to be fixed after creation.
  - This can improve thread safety and reasoning about code behavior.

# Question

Code 1:

```java
public class Test
{
	public static void main(String[] args)
	 {
	  String ta = "A";
	  ta = ta.concat("B");
	  String tb ="C";
	  ta=ta.concat(tb);
	  ta.replace("C","D");
	  ta=ta.concat(tb);
	  System.out.println(ta);
	  }
}
```

What will be the output?

```java
ABCC
```

![s8](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/40206fe8-7e39-4e2b-90ae-fc6cf0fd1fe4)


Code 2:

```java
public class Test
{
	public static void main(String[] args)
	 {
	  String str = " ";
	  str.trim();
	  System.out.println(str.equals(""));
	   System.out.println(str.isEmpty());
	  }
}
```

Output : →

```java
False
False
```

Code 3:

```java
public class Test
{
	public static void main(String[] args)
	 {
	  String str = "Hello World";
	  str.trim();
	  int i1  = s.indexOf(" ");
	  System.out.println(i1);
	 }
}
```

What will be output?

```java
5
```

Code 4:

```java
public class Test
{
	public static void main(String[] args)
	 {
	  String s1 = "Java";
	  String s2 = new Striing("java");
	  //Line 1
	  {
	  System.out.println("Equal");
	  }else
	  {
	  System.out.println("Not Equal");
	  }
	 }
}
```

To print “Equal”, which code fragment should be inserted at line 1?

```java
A.) String s3=s2;
    if(s1==s3);
    
B.) if(s1.equalsIgnoreCase(s2));

C.) String s3 = s2;
    if(s1.equals(s3);
    
D.) if(s1.toLowerCase()==s2.toLowerCase()) 
```

Ans - > B

# StringBuffer

## Why StringBuffer?

- **StringBuffer** is recommended when the content undergoes frequent changes. Unlike **`String`**, where every modification creates a new object due to its immutability, **`StringBuffer`** allows for efficient updates by modifying the existing object.
- With **`String`**, each modification operation results in the creation of a new string object, which can be inefficient and lead to unnecessary memory allocation and garbage collection overhead.
- In contrast, **`StringBuffer`** provides a mutable sequence of characters, allowing for in-place modifications without creating new objects for every change. This improves performance and memory utilization, especially in scenarios where the content is dynamically changing, such as string concatenation within loops or when building strings incrementally.
- Therefore, **`StringBuffer`** is preferred when you need to perform multiple modifications to the content without incurring the overhead of creating new string objects each time.

## StringBuffer Constructor

![s9](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/45f53b76-8158-4d4c-bf7e-ed17f50d003d)


1. Default Capacity: 16

If StringBuffer reaches its full capacity (16 characters) and new characters need to be added, a new object is internally created.The capacity of the new object is calculated as **`(Current Capacity + 1) * 2`**.This resizing process occurs each time the current capacity is exceeded.

Example:

```java
StringBuffer sb = new StringBuffer();
System.out.println(sb.capacity());    // Output: 16

sb.append("ABCDEFGHIJKlMNO");
System.out.println(sb.capacity());   // Output: 16

sb.append("PQ");
System.out.println(sb.capacity());   // Output: 34
```

1. **`StringBuffer sb = new StringBuffer(int initialCapacity);`**
  - Allows declaration of capacity as per requirement.
2. **`StringBuffer sb = new StringBuffer(String s)`**
  - Creates a StringBuffer with equivalent content to the provided string.
  - Capacity of the StringBuffer is set to **`s.length() + 16`**.

   Example:

    ```java
    
    StringBuffer sb = new StringBuffer("Raj");
    System.out.println(sb.capacity()); // Output: 21
    ```


# Important methods of StringBuffer

1. **`public int length()`**
  - Returns the length (number of characters) of the current StringBuffer object.

    ```java
    StringBuffer sb = new StringBuffer("Hello");
    System.out.println("Length: " + sb.length()); // Output: 
    ```

2. **`public int capacity()`**
  - Returns the current capacity (allocated size) of the StringBuffer object.

    ```java
    System.out.println("Capacity: " + sb.capacity()); // Output: 2
    ```

3. **`public char charAt(int index)`**
  - Returns the character at the specified index within the StringBuffer object.

    ```java
    System.out.println("Character at index 1: " + sb.charAt(1)); // Output: e
    ```

4. **`public void setCharAt(int index, char newChar)`**
  - Sets the character at the specified index to the new specified character.

    ```java
    sb.setCharAt(0, 'h');
    System.out.println("After setCharAt: " + sb); // Output: hello
    ```

5. **`public StringBuffer append(String s)`**
  - Appends the specified string to the end of the StringBuffer object.

    ```java
    sb.append(" World");
    System.out.println("After append: " + sb); // Output: hello World
    ```

6. **`public StringBuffer insert(int index, String s)`**
  - Inserts the specified string into the StringBuffer object at the specified index.

    ```java
    sb.insert(6, ", ");
    System.out.println("After insert: " + sb); // Output: hello, Worl
    ```

7. **`public StringBuffer delete(int begin, int end)`**
  - Deletes characters from the StringBuffer object starting from the **`begin`** index (inclusive) to the **`end`** index (exclusive).

    ```java
    sb.delete(6, 8);
    System.out.println("After delete: " + sb); // Output: helloWorld
    ```

8. **`public StringBuffer deleteCharAt(int index)`**
  - Deletes the character at the specified index from the StringBuffer object.

    ```java
    sb.deleteCharAt(5);
    System.out.println("After deleteCharAt: " + sb); // Output: helloorld
    ```

9. **`public StringBuffer reverse()`**
  - Reverses the sequence of characters in the StringBuffer object.

    ```java
    sb.reverse();
    System.out.println("After reverse: " + sb); // Output: dlrowolle
    ```

10. **`public void setLength(int newLength)`**
  - Sets the length of the StringBuffer object to the specified new length.

    ```java
    sb.setLength(5);
    System.out.println("After setLength: " + sb); // Output: dlrow
    ```

11. **`public void ensureCapacity(int minimumCapacity)`**
  - Ensures that the capacity of the StringBuffer object is at least equal to the specified minimum capacity.

    ```java
    sb.ensureCapacity(50);
    System.out.println("Capacity after ensureCapacity: " + sb.capacity()); // Output: 50
    ```

12. **`public void trimToSize()`**
  - Reduces the capacity of the StringBuffer object to its current length.

    ```java
    sb.trimToSize();
    System.out.println("Capacity after trimToSize: " + sb.capacity()); // Output: 5
    ```


# Need  of StringBuilder and difference with StringBuffer

| StringBuffer | StringBuilder |
| --- | --- |
| Each method present in StringBuffer is synchronized, allowing only one thread to operate on the StringBuffer object at a time, making it thread-safe. | No method present in StringBuilder is synchronized, allowing multiple threads to operate on the StringBuilder object simultaneously, but making it not thread-safe. |
| Threads are required to wait to operate on StringBuffer objects, resulting in relatively slower performance due to thread contention. | Threads are not required to wait to operate on StringBuilder objects, leading to relatively higher performance as there is no thread contention. |
| Introduced in Java 1.0 | Introduced in Java 1.5 |
| StringBuffer uses synchronized methods, which are denoted with the 'buffer' keyword. | StringBuilder uses unsynchronized methods, denoted with the 'builder' keyword. |
| StringBuffer is ideal for scenarios where thread safety is a concern, at the expense of performance. | StringBuilder is preferred in situations where thread safety is not critical, and performance is a priority. |

Java introduced StringBuilder as an alternative to StringBuffer, removing synchronization where it was not needed. This enhancement improved performance, especially in multi-threaded environments where StringBuffer's thread-safe behavior could lead to performance bottlenecks.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/31e9e415-573f-4b24-af21-dff408689727/Untitled.jpeg)

**Shared Functionality:**

- Constructors and methods you learned for `StringBuffer` are mostly applicable to `StringBuilder` as well, with the exception of synchronization.

**Choosing Between StringBuffer and StringBuilder:**

- Use `StringBuffer` when thread safety is essential for concurrent access to the string object in a multithreaded environment.
- Use `StringBuilder` when performance is a priority and thread safety is not a concern. In most single-threaded scenarios, `StringBuilder` is the preferred choice.

Remember that even though `StringBuilder` is faster, it's crucial to use `StringBuffer` if multiple threads need to access and modify the string data concurrently without corrupting the object's state.

# When to choose among String, StringBuilder and StringBuffer

| String | StringBuffer | StringBuilder |
| --- | --- | --- |
| When to Use | When to Use | When to Use |
| Use when the content is fixed or rarely changes. Strings are immutable, meaning their content cannot be altered after creation. | Use when the content changes frequently, and thread safety is a concern. StringBuffer provides synchronized methods, ensuring thread safety in multi-threaded environments. | Use when the content changes frequently, and thread safety is not a concern. StringBuilder offers better performance than StringBuffer as it is not synchronized, allowing multiple threads to operate simultaneously without the overhead of synchronization. |
| Thread Safety | Thread Safety | Thread Safety |
| String objects are inherently thread-safe because they are immutable. Once a string object is created, its content cannot be changed. | StringBuffer is thread-safe because it provides synchronized methods, ensuring that only one thread can operate on the StringBuffer object at a time. | StringBuilder is not thread-safe by default, as it does not provide synchronization. Multiple threads can operate on a StringBuilder object simultaneously, making it suitable for single-threaded scenarios or situations where thread safety is not required. |
| Additional Information |  |  |
| All immutable objects, including String, are thread-safe by default. |  |  |

# Method Chaining

In method chaining, all methods are executed from left to right.

```java
public class MethodChaining
{
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();

        sb.append("durga").append("solutions").reverse().insert(2,"xyz").delete(3,7);

        System.out.println(sb);  // snxtulosagrud
    }
}

```

[Example Codes](https://github.com/Rajeev-singh-git/Java_Interview_Question/tree/main/JavaCore/src/String)
