# Serializaton

## Table of Contents

1. [Java Exception Handling: A Comprehensive Guide ☕](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/ExceptionHandling/concept/01_%20Exception_Core_Concepts.md)
2. [Mastering User-Defined Exceptions](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/ExceptionHandling/concept/02_Custom%20Exceptions.md)
3. [Top Exceptions,Java 7 Enhancements & Beyond](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/ExceptionHandling/concept/03_Advanced_Exception_Topics.md)

---

# 📦 Serialization and Deserialization

---

## Serialization (v1.1)

**Serialization** is the process of converting a **Java object into a byte stream**, allowing it to be easily **saved to a file**, **transmitted over a network**, or **stored in a database** for later reconstruction.

This is achieved in Java using the `FileOutputStream` and `ObjectOutputStream` classes.

---

### 🔧 How to Perform Serialization?

1. Create a `FileOutputStream` to the destination file.

2. Wrap it in an `ObjectOutputStream`.

3. Call `writeObject(object)`.

```java
FileOutputStream fos = new FileOutputStream("data.ser");
ObjectOutputStream oos = new ObjectOutputStream(fos);
oos.writeObject(obj);
```

✅ Make sure the object’s class implements `Serializable`.

---

**🎈 Analogy**

Think of a large balloon being deflated (air pumped out) to fit inside a box for transport — compact and easy to store or send.

---

## Deserialization

**Deserialization** is the process of converting a **byte stream** back into a **Java object**, restoring its original state. This allows objects to be reconstructed after being read from a file, received over a network, or retrieved from a database.

This is achieved in Java using the `FileInputStream` and `ObjectInputStream` classes.

---

### 🔧 How to Perform Deserialization?

Follow these 3 simple steps:

1. **Create a stream from the file** using `FileInputStream`.

2. **Wrap it** with `ObjectInputStream`.

3. **Call** `readObject()` and **cast** it to the correct type.

```java
FileInputStream fis = new FileInputStream("data.ser");
ObjectInputStream ois = new ObjectInputStream(fis);
MyClass obj = (MyClass) ois.readObject();
```

✅ The file must contain a serialized object, and the class must be available on the classpath.

---

###### 🎈 Analogy:

Like re-inflating the balloon at the destination — bringing the original object back to life.

---

#### ✅ Example 1: Serializing a Single Object

```java
import java.io.*;

class Dog implements Serializable {
    int i = 10;
    int j = 20;
}

class SerializableDemo {
    public static void main(String[] args) throws Exception {
        Dog d1 = new Dog();
        System.out.println("Serialization started");

        FileOutputStream fos = new FileOutputStream("dogdata.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(d1);
        System.out.println("Serialization ended");

        System.out.println("Deserialization started");
        FileInputStream fis = new FileInputStream("dogdata.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Dog d2 = (Dog) ois.readObject();
        System.out.println("Deserialization ended");

        System.out.println(d2.i + "................" + d2.j);
    }
}
```

###### 🧾 Output:

```java
Serialization started  
Serialization ended  
Deserialization started  
Deserialization ended  
10................20
```

---

#### ✅ Example 2: Serializing Multiple Objects

```java
import java.io.*;

class Dog implements Serializable {
    int i = 10;
    int j = 20;
}

class Cat implements Serializable {
    int i = 30;
    int j = 40;
}

class SerializableDemo {
    public static void main(String[] args) throws Exception {
        Dog d1 = new Dog();
        Cat c1 = new Cat();

        System.out.println("Serialization started");
        FileOutputStream fos = new FileOutputStream("animals.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(d1);
        oos.writeObject(c1);
        System.out.println("Serialization ended");

        System.out.println("Deserialization started");
        FileInputStream fis = new FileInputStream("animals.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Dog d2 = (Dog) ois.readObject();
        Cat c2 = (Cat) ois.readObject();
        System.out.println("Deserialization ended");

        System.out.println(d2.i + "................" + d2.j);
        System.out.println(c2.i + "................" + c2.j);
    }
}
```

**🧾 Output:**

```java
Serialization started  
Serialization ended  
Deserialization started  
Deserialization ended  
10................20  
30................40
```

---

## 📌 Serialization and Deserialization  Important Notes

- ✅ Only **serializable** objects can be serialized.

- ✅ A class must implement the `Serializable` interface to be eligible.

- ⚙️ `Serializable` is a **marker interface** (no methods). JVM provides the implementation.

- 🔄 **Order matters**: Objects must be **read in the same order** they were written.

- ❌ Serializing a non-serializable object throws a `NotSerializableException` at runtime.

---


