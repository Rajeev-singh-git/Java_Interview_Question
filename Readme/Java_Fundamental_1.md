# Introduction

This README covers all the fundamental concepts of the Java programming language. It includes essential topics such as syntax, data types, variables, operators, control flow statements, loops, and other core basics required to build a strong foundation in Java.

# 🆔 Identifier

An **identifier** is the name used to identify elements in a Java program such as:

- Class names
- Method names
- Variable names
- Label names

### 🔹 Example

```java
class Test {
    public static void main(String[] args) {
        int x = 10;
    }
}
```

This example contains 5 identifiers: `Test`, `main`, `args`, `int`, and `x`.

---

## ✅ Rules for Defining Identifiers in Java

### 1. Allowed Characters:

- Letters: `a–z`, `A–Z`
- Digits: `0–9`
- Special characters: `_` (underscore), `$` (dollar sign)

> ❌ Any other character results in a compile-time error
>

```java
int total_number = 10;   // ✅ Valid
int Total# = 20;         // ❌ Invalid (contains '#'
```

---

### 2. Cannot Start with a Digit

```java
int ABC123 = 10;  // ✅ Valid
int 123ABC = 20;  // ❌ Invalid
```

---

### 3. Java is Case-Sensitive

Identifiers with different cases are treated as distinct.

```java
int number = 10;
int Number = 20;
int NUMBER = 30;
int NuMbEr = 40;
```

---

### 4. No Length Limit

There is **no restriction** on the length of an identifier, but it is recommended to keep names concise (preferably under 15 characters).

---

### 5. Reserved Keywords Not Allowed

Identifiers **cannot be Java reserved words**.

---

### 6. Predefined Class/Interface Names Can Be Used

Technically, you **can** use names like `String`, `Runnable`, etc., as identifiers, but it’s **not recommended**.

```java
class Test {
    public static void main(String[] args) {
        int String = 10;
        System.out.println(String);  // Output: 10
    }
}
```

```java
class Test {
    public static void main(String[] args) {
        int Runnable = 10;
        System.out.println(Runnable);  // Output: 10
    }
}
```

---

## ❓ Practice: Identify Valid Identifiers

> Which of the following are valid Java identifiers?
>
- `totalMarks`
- `_value`
- `$temp`
- `2ndItem`
- `float`
- `main()`
- `employee#id`
- `MAX_VALUE`

# Reserved Words

In Java, some identifier are **reserved** to convey specific functionality or meaning. These are known as **reserved words** and cannot be used as names for variables, methods, classes, or other identifiers.

---

## Overview

Java has a total of **53 reserved words**, divided into:

- **Keywords**: 50
- **Reserved Literals**: 3

![1](https://github.com/user-attachments/assets/d901b0b7-f430-473a-8574-3ec3fc47242c)


```java
Reserved Words (53)
├── Keywords (50)
│   ├── Used Keywords (48)
│   └── Unused Keywords (2) → `goto`, `const`
└── Reserved Literals (3) → `true`, `false`, `null`
```

---

## Reserved Keywords by Category

### Data Types (8)

```java
byte, short, int, long, float, double, char, boolean
```

### Flow Control (11)

```java
if, else, switch, case, default, for, do, while, break, continue, return
```

### Modifiers (11)

```java
public, private, protected, static, final, abstract,
synchronized, native, strictfp, transient, volatile
```

### Exception Handling (6)

```java
try, catch, finally, throw, throws, assert // (introduced in Java 1.4)

```

### Class-Related (6)

```java
class, interface, package, import, extends, implements
```

### Object-Related (4)

```java
new, instanceof, super, this
```

### Method Return Type (1)

```java
void
```

### Enum (1)

```java
enum // (introduced in Java 1.5)
```

---

## Reserved Literals (3)

These literals are reserved and cannot be used as identifiers:

```java
true    // boolean literal
false   // boolean literal
null    // default value for object references
```

---

## Unused (Discouraged) Keywords (2)

```java
goto    // Reserved for future use; causes complications in older languages
const   // Use 'final' instead
```

If used in a program, Unused keywords will result in a **compile-time error**.

---

## Special Notes

1. All reserved words are **lowercase only**.
2. Java supports only `new`, not `delete`. Object destruction is handled by the **Garbage Collector**.
3. Be cautious of common confusions:
  - `instanceof`, not `instanceOf`
  - `strictfp`, not `strictFp`
  - `synchronized`, not `synchronize`
  - `implements`, not `implement`
  - `import`, not `imports`
  - `int`, not `Int`
  - `finalize` is **not** a keyword — it’s a method in the `Object` class.

---

## Practice: Valid or Invalid?

### Which of the following are **not** valid Java reserved words?

| List | Reason | Valid? |
| --- | --- | --- |
| `final, finally, finalize` | `finalize` is a method, not a keyword | ❌ |
| `throw, throws, thrown` | `thrown` is not a keyword | ❌ |
| `break, continue, return, exit` | `exit` is not a keyword | ❌ |
| `goto, constant` | `constant` is not a keyword | ❌ |
| `byte, short, Integer, long` | `Integer` is a class, not a keyword | ❌ |
| `extends, implements, imports` | `imports` is not a keyword | ❌ |
| `finalize, synchronized` | `finalize` is not a keyword | ❌ |
| `instanceof, sizeOf` | `sizeOf` is not a keyword | ❌ |
| `new, delete` | `delete` is not a keyword | ❌ |
| **None of the above** | ✅ |  |

---

### Which of the following are valid Java **keywords**?

| Keyword | Valid? |
| --- | --- |
| `public` | ✅ |
| `static` | ✅ |
| `void` | ✅ |
| `main` | ❌ (method name, not a keyword) |
| `String` | ❌ (class name, not a keyword) |
| `args` | ❌ (parameter name, not a keyword) |

---

# Data Types

Every variable and expression in Java has a type, and all assignments are strictly checked for type compatibility at compile time. This makes Java a **strongly typed programming language**.

---

## Is Java a Pure Object-Oriented Language?

No. Java is **not** a pure object-oriented programming language because:

- It lacks some OOP features like **multiple inheritance** and **operator overloading**.
- It supports **primitive data types**, which are not objects.

---

## Classification of Primitive Data Types

Java provides 8 primitive data types, grouped into:

![2](https://github.com/user-attachments/assets/b6587e9b-af65-43fb-8f46-3b7714154346)


> All primitive types except char and boolean are signed, meaning they can represent both positive and negative values.
>

---

## Integral Data Types

### `byte`

- **Size**: 1 byte (8 bits)
- **Range**: -128 to 127 (`2⁷` to `2⁷-1`)
- **Use Case**: Efficient for file and network stream handling
- **Notes**:
  - Most significant bit is the **sign bit** (`0 = +ve`, `1 = -ve`)
  - Negative numbers use **2's complement** representation

```java
byte b = 10;           // valid
byte b2 = 130;         // Compile-time error: possible loss of precision
byte b3 = 10.5;        // Compile-time error: incompatible types
byte b4 = true;        // Compile-time error: incompatible types
byte b5 = "ashok";     // Compile-time error: incompatible types
```

---

### `short`

- **Size**: 2 bytes
- **Range**: -32,768 to 32,767 (`2¹⁵` to `2¹⁵-1`)
- **Use Case**: Historically for 16-bit processors like Intel 8086 (now outdated)

```java
short s = 130;         // valid
short s2 = 32768;      // Compile-time error
short s3 = true;       // Compile-time error
```

---

### `int`

- **Size**: 4 bytes
- **Range**: -2,147,483,648 to 2,147,483,647 (`2³¹` to `2³¹-1`)
- **Use Case**: Most commonly used integral type

```java
int i = 130;           // valid
int i2 = 10.5;         // Compile-time error
int i3 = true;         // Compile-time error
```

---

### `long`

- **Size**: 8 bytes
- **Range**: `2⁶³` to `2⁶³-1`
- **Use Case**: Useful when `int` is not sufficient, e.g., file sizes

```java
File f = new File("data.txt");
long size = f.length();  // `length()` returns long, no of character in file
```

---

## Floating Point Data Types

| Data Type | Size | Precision | Range | Use Case |
| --- | --- | --- | --- | --- |
| `float` | 4 bytes | 5–6 decimal places | ±3.4e38 | When moderate precision is enough |
| `double` | 8 bytes | 14–15 decimal places | ±1.7e308 | For high-precision calculations |
- `float` uses **single precision**
- `double` uses **double precision**

---

## `boolean` Data Type

- **Size**: JVM dependent
- **Allowed Values**: `true`, `false`
- **Range**: Not applicable

```java
boolean b1 = true;       // valid
boolean b2 = True;       // Compile-time error: cannot find symbol
boolean b3 = "True";     // Compile-time error
boolean b4 = 0;          // Compile-time error
```

---

## `char` Data Type

- **Size**: 2 bytes
- **Range**: 0 to 65,535
- **Notes**:
  - Based on **Unicode**, unlike older languages (C/C++) which use **ASCII**
  - Supports **international characters**

```java
char ch1 = 97;         // valid (Unicode for 'a')
char ch2 = 65536;      // Compile-time error: possible loss of precision
```

---

## Summary Table: Primitive Data Types

| Data Type | Size | Range | Wrapper Class | Default Value |
| --- | --- | --- | --- | --- |
| `byte` | 1 byte | -128 to 127 | `Byte` | `0` |
| `short` | 2 bytes | -32,768 to 32,767 | `Short` | `0` |
| `int` | 4 bytes | -2,147,483,648 to 2,147,483,647 | `Integer` | `0` |
| `long` | 8 bytes | -2⁶³ to 2⁶³-1 | `Long` | `0L` |
| `float` | 4 bytes | ±3.4e38 | `Float` | `0.0f` |
| `double` | 8 bytes | ±1.7e308 | `Double` | `0.0d` |
| `boolean` | N/A | `true`, `false` | `Boolean` | `false` |
| `char` | 2 bytes | 0 to 65,535 | `Character` | `'\u0000'`  |

---

> 🔹 The default value for object references is null.
>

---

# Literals

## What is a Literal?

Any constant value that can be assigned to a variable is called a **literal**.

**Example:**

```java
int x = 10; // 10 = literal, int = data type, x = identifier
```

---

## Integral Literals

For integral data types (`byte`, `short`, `int`, and `long`), we can specify literal values in the following formats:

### 1. Decimal Literals

- Allowed digits: `0` to `9`

```java
int x = 10;
```

### 2. Octal Literals

- Allowed digits: `0` to `7`
- Must be prefixed with `0`

```java
int x = 010; // 8 in decimal
```

### 3. Hexadecimal Literals

- Allowed digits: `0-9`, `A-F` (case insensitive)
- Prefix: `0x` or `0X`

```java
int x = 0x10; // 16 in decimal
```

### Validity Check

```java
int x = 0777;   // ✅ valid (octal)
int x = 0786;   // ❌ Compile Error: 8 is invalid in octal
int x = 0xFACE; // ✅ valid
int x = 0xbeef; // ✅ valid
int x = 0xBeer; // ❌ Compile Error
int x = 0xabb2cd; // ✅ valid
```

### Output Example

```java
int x = 10;
int y = 010;
int z = 0x10;
System.out.println(x + "----" + y + "----" + z); // 10----8----16
```

### Default Type & Long Literals

- By default, every integral literal is of type `int`.
- To specify `long`, suffix with `L` or `l`:

```java
long l1 = 10L;
long l2 = 10;   // still valid
int x = 10l;    // ❌ Compile Error: possible loss of precision
```

### Byte and Short Literals

> We cannot explicitly declare byte/short literals, but the compiler treats integral literals as byte/short if within range.
>

```java
byte b = 127;     // ✅ valid
byte b = 130;     // ❌ Compile Error
short s = 32767;  // ✅ valid
short s = 32768;  // ❌ Compile Error
```

---

## Floating Point Literals

By default, floating point literals are of type `double`. Use `f` or `F` suffix to specify as `float`.

### Examples:

```java
float f = 123.456;   // ❌ Compile Error
float f = 123.456f;  // ✅ valid
double d = 123.456;  // ✅ valid
double d = 123.456D; // ✅ valid
```

### Format Restrictions

> Floating point literals can only be specified in decimal form (not octal or hex).
>

```java
double d = 0123.456;   // ✅ valid (treated as decimal)
double d = 0x123.456;  // ❌ Compile Error
```

### Validity Check

```java
float f = 123.456;     // ❌
float f = 123.456D;    // ❌
double d = 0x123.456;  // ❌
double d = 0xFace;     // ✅
double d = 0xBeef;     // ✅
```

### Assigning Integral to Floating Point

> Integral literals (decimal, octal, hex) can be assigned to floating point types.
>

```java
double d = 0xBeef; // 48879.0
```

> But not vice-versa:
>

```java
int x = 10.0; // ❌ Compile Error
```

### Scientific Notation (Exponential Form)

```java
double d = 10e2;   // 10 * 10^2 = 1000.0
float f = 10e2F;   // ✅ valid
float f = 10e2;    // ❌ Compile Error
```

---

## Boolean Literals

Only two values are allowed: `true` and `false` (case-sensitive)

```java
boolean b1 = true;      // ✅ valid
boolean b2 = 0;         // ❌ Compile Error
boolean b3 = True;      // ❌ Compile Error
boolean b4 = "true";    // ❌ Compile Error
```

---

## Char Literals

### 1. Single Character in Single Quotes

```java
char ch = 'a';   // ✅ valid
char ch = a;     // ❌ Compile Error
char ch = "a";   // ❌ Compile Error
char ch = 'ab';  // ❌ Compile Error
```

### 2. As Integral Literal (Unicode Code Point)

> We can specify a char literal as an integral value that represents the Unicode of the character.
>

```java
char ch1 = 97;        // 'a'
char ch2 = 0xFace;    // valid (non-printable character)
char ch3 = 65536;     // ❌ Compile Error
```

### 3. Unicode Representation

> Format: \uXXXX (4-digit hex)
>

```java
char ch1 = '\ubeef';  // ✅ valid
char ch2 = '\u0061';  // 'a'
char ch3 = \u0062;     // ❌ Compile Error
char ch4 = '\iface';   // ❌ illegal escape character
```

### 4. Escape Characters

Every escape character is a valid `char` literal:

```java
char ch1 = '\n';  // ✅ newline character
char ch2 = '\l';  // ❌ illegal escape character
```

| Escape | Description |
| --- | --- |
| `\n` | New line |
| `\t` | Horizontal tab |
| `\r` | Carriage return |
| `\f` | Form feed |
| `\b` | Backspace |
| `\'` | Single quote |
| `\"` | Double quote |
| `\\` | Backslash |

### Validity Check

```java
char ch = a;        // ❌
char ch = 'ab';     // ❌
char ch = 65536;    // ❌
char ch = \uface;   // ❌
char ch = '/n';     // ❌
```

---

## String Literals

Any sequence of characters enclosed in double quotes.

```java
String s = "Ashok"; // ✅ valid
```

---

## Enhancements in Java 1.7+

### 1. Binary Literals

> Allowed digits: 0 and 1. Prefix: 0b or 0B
>

```java
int x = 0b111;  // 7
```

### 2. Underscore in Numeric Literals

> Improves readability. Underscores are removed at compile time.
>

```java
double d1 = 123456.789;          // ✅
double d2 = 1_23_456.7_8_9;      // ✅
double d3 = 123_456.7_8_9;       // ✅
```

### Invalid Usages

> Must be between digits only.
>

```java
double d = _123_456.789;   // ❌ invalid

float f = 10L;             // valid, auto converted
System.out.println(f);     // 10.0
```

---

## Summary Table

| Type | Default | Explicit Suffix | Notes |
| --- | --- | --- | --- |
| int | int | none |  |
| long | int | `L` or `l` |  |
| float | double | `F` or `f` | must specify suffix |
| double | double | `D` or `d` | suffix optional |
| char | char | none | can use integer or Unicode |
| boolean | boolean | none | only `true`/`false` |
| String | String | none | double quotes required |

![3](https://github.com/user-attachments/assets/36710667-c6e7-417b-840c-426d91526a3f)
