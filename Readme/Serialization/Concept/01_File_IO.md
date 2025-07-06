# Serializaton

## Table of Contents

1. [Java Exception Handling: A Comprehensive Guide ☕](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/ExceptionHandling/concept/01_%20Exception_Core_Concepts.md)
2. [Mastering User-Defined Exceptions](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/ExceptionHandling/concept/02_Custom%20Exceptions.md)
3. [Top Exceptions,Java 7 Enhancements & Beyond](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Readme/ExceptionHandling/concept/03_Advanced_Exception_Topics.md)

---

# 📦 File I/O and Object Persistence in Java

---

In Java programming, dealing with **object storage and retrieval** involves two key concepts:

1. **File Input/Output (I/O)** — related to operations on **files** (usually on Hard Disk).

2. **Persistence** — the practice of **saving object data permanently**, so that it can be reused even after the program (or JVM) has terminated.

---

## 🧠 1. Object Lifecycle in Java (In RAM — Volatile)

In Java, when we create an object like:

```java
Student s1 = new Student(1, "Raj", 21);
```

The object is created and stored in **RAM**, specifically in the **heap memory** section. RAM is **volatile**, meaning all data is lost when the program terminates or JVM shuts down.

**✨ JVM's Role**

- JVM handles **memory allocation** for the object.

- It **manages the heap**, where objects live during execution.

##### ✨ Example Class:

Let's say the `Student` class is defined like this:

```java
class Student {
	private String sname;
	private Integer sid;
	private Integer sage;

	Student(Integer sid, String sname, Integer sage){
		this.sname = sname;
		this.sid = sid;
		this.sage = sage;
	}

	public String toString(){
		return "sid: "+sid+ "  sname: "+sname+ "  sage: "+sage;
	}
}
```

And the main class:

```java
public class Test {
	public static void main(String[] args){
		Student s1 = new Student(1, "Raj", 21);
		System.out.println(s1);
	}
}
```

💻 **Output Displayed:**

```java
sid: 1  sname: Raj  sage: 21
```

This output is only **temporary** — the object resides in **RAM**, which is cleared when the JVM ends.

---

# Persistence

---

## Why Persistence is Needed ?

Once the object is created and output is displayed, **can we retrieve the object back after the program exits?**

> ❌ **No**, because the object lives in volatile memory (RAM), and will be lost after execution ends.

To make sure the object can be **retrieved later**, we must **store it in secondary memory**, such as:

- **Hard Disk** (in file form)

- **Database** (in table form)

---

## Persistence Options in Java

Once we know that data stored in RAM is temporary, the next question becomes: **how can we persist Java objects for long-term use?**

Java provides multiple ways to achieve persistence depending on **where** and **how** you want to store the data.

Let’s explore the most common and powerful options:

---

### ✅ A. File-Based Persistence (Using `java.io`)

This is the simplest and most fundamental method. Java provides `java.io` APIs to **write object data into files** stored on the hard disk.

This is usually done using **serialization**, which converts an object into a byte stream.

#### 📂 **Use Case**:

 When you want to store object data as binary in a file for later use.

```java
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"));
oos.writeObject(s1);
oos.close();
```

To read the object back:

```java
ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"));
Student s2 = (Student) ois.readObject();
```

⚠️ **Note**: The `Student` class must implement `Serializable` for this to work.

---

## ✅ B. Relational Database Persistence (Using JDBC)

In many applications, object data needs to be stored in a **structured format** inside relational databases like MySQL, Oracle, or PostgreSQL.

Java provides the **JDBC API (`java.sql`)** to allow developers to insert, retrieve, update, or delete data in these databases.

#### 📂 **Use Case**:

When you need scalable, queryable, and structured storage for your objects.

```java
Connection con = DriverManager.getConnection(...);
PreparedStatement ps = con.prepareStatement("INSERT INTO student VALUES (?, ?, ?)");
ps.setInt(1, s1.getSid());
ps.setString(2, s1.getSname());
ps.setInt(3, s1.getSage());
ps.executeUpdate();
```

JDBC handles the connection between Java code and the database's **storage engine**, making it possible to persist objects in table form.

---

## ✅ C. ORM Frameworks (Hibernate, JPA, Spring Data JPA)

For large-scale or enterprise applications, using raw JDBC can become verbose and error-prone. Instead, Java offers **Object-Relational Mapping (ORM)** frameworks that simplify persistence by **mapping Java classes to database tables**.

Frameworks like:

- **Hibernate**

- **JPA (Java Persistence API)**

- **Spring Data JPA**

#### 📂 **Use Case**:

 When you want clean, declarative database integration without writing SQL queries manually.

```java
@Entity
public class Student {
    @Id
    private int sid;
    private String sname;
    private int sage;
}
```

These frameworks automatically generate queries to persist and retrieve objects.

---

## ✅ D. JSON or XML File Storage

Sometimes, storing data as **human-readable files** (instead of binary) is preferred — especially for APIs, configuration files, or lightweight data transfer.

Java supports converting objects into:

- **JSON** using libraries like `Jackson`, `Gson`

- **XML** using `JAXB`, `XStream`

📂 **Use Case**: When you need portable, readable formats for storing or transferring object data.

```java
ObjectMapper mapper = new ObjectMapper();
mapper.writeValue(new File("student.json"), s1);
```

These formats are ideal for web APIs and cross-language communication.

---

## ✅ E. NoSQL Database Storage (MongoDB, Redis, etc.)

Java objects can also be stored in **NoSQL databases**, which use document or key-value formats instead of traditional relational tables.

Examples:

- **MongoDB**: Stores objects as BSON documents.

- **Redis**: Can store serialized objects or JSON strings.

- **Cassandra**: Wide-column format, useful for time-series or high-write workloads.

#### 📂 **Use Case**:

When your data model is flexible or schema-less, or you need fast reads/writes at scale.

Frameworks like **Spring Data MongoDB** or **Morphia** help with easy integration.

---

## ✅ F. Byte Arrays / In-Memory Streams

Objects can also be persisted temporarily in memory as **byte arrays**, which is useful for:

- Caching

- Transferring over a network

- Writing to compressed formats or custom binary protocols

```java
ByteArrayOutputStream bos = new ByteArrayOutputStream();
ObjectOutputStream oos = new ObjectOutputStream(bos);
oos.writeObject(s1);
byte[] objectBytes = bos.toByteArray();
```

#### 📂 **Use Case**:

 When you don’t want to write to disk but need to persist or transmit the object temporarily.

---

## ✅ G. In-Memory Caching with Optional Disk Persistence

Frameworks like:

- **Ehcache**

- **Hazelcast**

- **Infinispan**

…allow Java objects to be **cached in memory** for performance, with optional persistence to disk.

#### 📂 **Use Case**:

When you want fast access during runtime but still need backup storage in case of failure.

---

## ✅ H. Custom Binary or Flat File Formats

Some applications (e.g., embedded systems, games, or device-level programs) may use **hand-crafted binary formats** or write flat text files manually for persistence.

#### 📂 **Use Case**:

When space efficiency, format control, or platform portability is critical.

---

### 🔁 Persistence Table

| Persistence Method       | Storage Location     | API/Frameworks             | Format           |
| ------------------------ | -------------------- | -------------------------- | ---------------- |
| File I/O (Serialization) | HDD (File)           | `java.io`                  | Binary           |
| JDBC                     | RDBMS (Tables)       | `java.sql`, JDBC           | Structured rows  |
| ORM (Hibernate/JPA)      | RDBMS                | Hibernate, Spring Data JPA | Auto-managed     |
| JSON/XML Files           | HDD (Text File)      | Jackson, Gson, JAXB        | Human-readable   |
| NoSQL DBs                | Key-Value / Document | MongoDB, Redis, Cassandra  | JSON/Binary      |
| Byte Arrays              | RAM / Network        | `java.io` streams          | Binary           |
| In-Memory Caching        | RAM + Optional Disk  | Ehcache, Hazelcast         | In-memory/Backup |
| Custom Binary            | HDD                  | Manual Logic               | Optimized Binary |

---


