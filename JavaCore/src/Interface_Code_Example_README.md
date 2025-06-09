# 🧱 Interface– Conceptual Code Examples

## Table of Contents

1. [Abstraction](#abstraction)
   - [Abstract Classes:](#-1-abstract-classes)
   - [Interface](#2-interface-)
2. [Encapsulation](#-encapsulation)
   - [Tightly Encapsulated Class](#-tightly-encapsulated-class-in-java)

---

# 🧱 Conceptual Code Examples

These examples illustrate what interfaces are, how they evolved, and what common mistakes to avoid — especially relevant in interviews after Java 8+.

---

### ✅ 1. Core Concept: What is an Interface?

```java
interface Greeter {
    void sayHello(); // public + abstract by default
}

class EnglishGreeter implements Greeter {
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}

public class Main {
    public static void main(String[] args) {
        EnglishGreeter greeter = new EnglishGreeter();
        greeter.sayHello(); // Output: Hello!
    }
}

```

Output ->

```java
Hello!
```

**✅ Concepts Highlighted**

- ✅ **Interface**: Defines a contract (method signature only).

- ✅ **Implicit modifiers**: Methods are implicitly `public abstract`.

- ✅ **Implementation**: A class uses `implements` and must define all methods.

---

### 🧠 2. Behavior Deep Dive: Default, Static, and Private Methods

```java
interface Calculator {
    // Default method (inherited with body)
    default int add(int a, int b) {
        return a + b;
    }

    // Static method (not inherited)
    static int multiply(int a, int b) {
        return a * b;
    }

    // Private helper (used only internally)
    private int square(int x) {
        return x * x;
    }

    // Abstract method
    int subtract(int a, int b);
}

class BasicCalculator implements Calculator {
    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    // Inherits add()
}

public class Main {
    public static void main(String[] args) {
        BasicCalculator calc = new BasicCalculator();

        System.out.println(calc.add(5, 3));       // Output: 8
        System.out.println(calc.subtract(5, 3));  // Output: 2

        // Static method — called on interface, not instance
        System.out.println(Calculator.multiply(4, 2)); // Output: 8
    }
}
```

Output :-->

```java
8
2
8
```

**🔍 Concepts Highlighted**

- ✅ **Default methods**: Have body; subclass may override.

- ✅ **Static methods**: Belong to the interface; not inherited.

- ✅ **Private methods**: Used internally inside default/static methods (Java 9+).

- ✅ **Interface evolution**: Interfaces can provide reusable logic without breaking implementations.

---

### 🚧 3. Interview Pitfall: Conflicting Default Methods

```java
interface A {
    default void greet() {
        System.out.println("Hello from A");
    }
}

interface B {
    default void greet() {
        System.out.println("Hello from B");
    }
}

// ❌ Compilation error unless override is provided
class ConflictClass implements A, B {
    @Override
    public void greet() {
        // Resolving conflict manually
        A.super.greet(); // or B.super.greet();
    }
}

public class Main {
    public static void main(String[] args) {
        ConflictClass obj = new ConflictClass();
        obj.greet(); // Output: Hello from A
    }
}
```

#### 🧠 Concepts Highlighted

- ⚠️ **Diamond problem**: Multiple interfaces define same default method.

- 🚫 **Ambiguity**: Java forces the class to resolve which default to use.

- ✅ **Resolution syntax**: Use `InterfaceName.super.method()` to resolve.


