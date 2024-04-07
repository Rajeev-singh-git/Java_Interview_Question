# Serialization

Serialization  refers to the process of converting java object into a format that can be easily stored (e.g., in a file) or transmitted (e.g., over a network). This allows objects to be saved persistently or sent across different applications or systems. The main purpose of serialization is to save the state of an object so that it can be reconstructed later.

By using `FileOutputStream` and `ObjectOutputStream` classes we can achieve
serialization process.

![s1](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/7d8d98f5-85cc-4ed2-b7ca-cb902abb2c89)

# Deserialization

Deserialization is the process of converting a serialized object back into its original form (i.e., a Java object) by reading it from a file or a network stream. This allows you to restore the object's state that was previously serialized.

It is the process of converting an object from file supported form (or) network supported form to java supported form.

By using `FileInputStream` and `ObjectInputStream` classes we can achieve
DeSerialization.

![s2](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/cde14dd3-5620-40e5-a4e1-6a61083ff77a)


Analogy : Big balloon can’t be transported to a long distance, it can burst and occupy large space. But if we take air out of it, it can be transported easily. After reaching the destination again we can fill air and make it big and use it as we want.

Example Code for Serialization and De-Serialization

```java
import java.io.*;

class Dog implements Serializable
{
    static{
		System.out.println("Static block get executed");
    }

	Dog(){
		System.out.println("Object is created");
	}

	int  i =10;
	int  j = 20;

}

class Test
{

	public static void main(String [] args) throws Exception
	{   
         Dog d = new Dog();

		 System.out.println("Serialization Started ...");
		 String fileName = "abcd.ser";
		 FileOutputStream fos = new FileOutputStream(fileName);
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(d);
		 System.out.println("Serialized Object reference is ::" +d);
		 System.out.println("Serialization Ended ...");

         // Wait for user input (for demonstration purposes)
		 System.in.read();
		
		 System.out.println("De-Serialization Started ...");
		 FileInputStream fis = new FileInputStream(fileName);
		 ObjectInputStream ois = new ObjectInputStream(fis);
		 Object obj = ois.readObject();
		 Dog d1 = (Dog)obj;
		 System.out.println("De-Serialized Object reference is ::" +d1);
		 System.out.println("De-Serialization Ended ...");
		

	}
} 

```

Output :→

```java

Static block get executed
Object is created
Serialization Started ...
Serialized Object reference is ::Dog@6e5e91e4
Serialization Ended ...

De-Serialization Started ...
De-Serialized Object reference is ::Dog@6477463f
De-Serialization Ended ...
```

If object is same then why serialized and de-serialized object both have different hashcode?

The difference in hashcodes (**`@6e5e91e4`** vs. **`@6477463f`**) between the serialized (**`d`**) and deserialized (**`d1`**) **`Dog`** objects is a result of the distinct memory addresses and object instances created during the serialization and deserialization processes. The hashcode is a reflection of the specific object instance within the JVM and is not tied to the serialized data or object state.

If object is not the same then why serialized and de-serialized object both have different hashcode?

The **`Dog`** class constructor is not executed again during deserialization (**`ObjectInputStream.readObject()`**), even though a new **`Dog`** object (**`d1`**) is created, because Java's deserialization process initializes the object's fields directly from the serialized data without invoking the constructor. This behavior ensures that the object's state is restored accurately while avoiding potentially unintended side effects that constructors might introduce during normal object instantiation.

### **Reasons for Skipping Constructor**

- **Object Restoration**: The goal of deserialization is to restore the object's state from its serialized form without executing any potentially undesired logic that might be performed in the constructor.
- **Constructor Not Applicable**: Constructors in Java are primarily used for initializing newly created objects. During deserialization, the object is being reconstituted from a saved state, which doesn't require the same initialization steps as a new object creation.

Example Code for Serialization and De-Serialization of 2 classes (**`Dog`** and **`Cat`**) together. This example demonstrates how to serialize instances of both classes to a file (**`abcd.ser`**) and then deserialize them back into new objects.

```java
import java.io.*;

class Dog implements Serializable
{
    static{
		System.out.println("Static block get executed");
    }

	Dog(){
		System.out.println("Dog Object is created");
	}

	int  i =10;
	int  j = 20;

}

class Cat implements Serializable
{
    static{
		System.out.println("Static block get executed");
    }

	Cat(){
		System.out.println("Cat Object is created");
	}

	int  i =30;
	int  j =40;

}

class Test
{

	public static void main(String [] args) throws Exception
	{   
         Dog d = new Dog();
		 Cat c = new Cat();

		 System.out.println("Serialization Started ...");
		 String fileName = "abcd.ser";
		 FileOutputStream fos = new FileOutputStream(fileName);
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(d);
		 oos.writeObject(c);
		 System.out.println("Serialization Ended ...");

     //Wait for user input (for demonstration purposes)
		 System.in.read();
		
		 System.out.println("De-Serialization Started ...");
		 FileInputStream fis = new FileInputStream(fileName);
		 ObjectInputStream ois = new ObjectInputStream(fis);
		 
		 //Deserialize Dog and Cat objects
		 Dog d1 = (Dog)ois.readObject();
		 Cat c1 = (Cat)ois.readObject();
		 
		 //Display deserialized object data
		 System.out.println("Dog object data is ::"+d1.i+" ---> " +d1.j);
		 System.out.println("Cat object data is ::"+c1.i+" ---> " +c1.j);
		 System.out.println("De-Serialization Ended ...");
		}
} 

```

## Note :→

1. Serialization can only be performed on Serializable objects.
2. An object is considered Serializable if and only if the corresponding class implements the Serializable interface.
3. The Serializable interface is located in the java.io package and does not contain any abstract methods; it is a marker interface. The required capability will be automatically provided by the JVM.
4. We can add any number of objects to a file, and we can read all those objects from the file, but they will come back in the same order in which we wrote them. Therefore, the order is important.
5. If we attempt to serialize a non-serializable object, we will receive a `RuntimeException` with the message "`NotSerializableException`".
6. The order of serialization and deserialization must be maintained. If we serialize the **`Dog`** object first, then we should deserialize the **`Dog`** object first as well, not the **`Cat`** object first followed by the **`Dog`** object; otherwise, we will encounter an **`Exception in thread “main” java.lang.ClassCastException`**.

![s3](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/9f74dea1-c4a6-4eaa-b270-454383632dfd)


# Transient

1. **`Transient`** is a modifier applicable only to variables, not to classes or methods.
2. During serialization, if we want to exclude the value of a specific variable, typically for security reasons, we can declare that variable with the **`transient`** keyword.
3. During serialization, the JVM ignores the original value of a transient variable and instead saves the default value to the file.
4. Therefore, **`transient`** can be understood as meaning "not to serialize."

Code demonstrating the use of the **`transient`** keyword in serialization and deserialization.

```java
import java.io.*;

class Dog implements Serializable
{
	transient int  i = 10;
	int  j = 20;
}

class Test
{

	public static void main(String [] args) throws Exception
	{   
         Dog d = new Dog();
		

		 System.out.println("Serialization Started ...");
		 String fileName = "abcd.ser";
		 FileOutputStream fos = new FileOutputStream(fileName);
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(d);
		 System.out.println("Serialization Ended ...");

         // Wait for user input (for demonstration purposes)
		 System.in.read();
		
		 System.out.println("De-Serialization Started ...");
		 FileInputStream fis = new FileInputStream(fileName);
		 ObjectInputStream ois = new ObjectInputStream(fis);
		 Dog d1 = (Dog)ois.readObject();
		 System.out.println("Dog object data is ::"+d1.i+" ---> " +d1.j);
		 System.out.println("De-Serialization Ended ...");
		

	}
} 

```

Output : →

```java
Serialization Started ...
Serialization Ended ...

De-Serialization Started ...
Dog object data is ::0 ---> 20
De-Serialization Ended ...
```

## Static vs Transient

A static variable is not part of an object's state; therefore, it does not participate in serialization. Declaring a static variable as transient serves no purpose.

```java
import java.io.*;

class Dog implements Serializable
{

	static transient int  i = 10;
	int  j = 20;

}

class Test
{

	public static void main(String [] args) throws Exception
	{   
         Dog d = new Dog();
		

		 System.out.println("Serialization Started ...");
		 String fileName = "abcd.ser";
		 FileOutputStream fos = new FileOutputStream(fileName);
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(d);
		 System.out.println("Serialization Ended ...");

         // Wait for user input (for demonstration purposes)
		 System.in.read();
		
		 System.out.println("De-Serialization Started ...");
		 FileInputStream fis = new FileInputStream(fileName);
		 ObjectInputStream ois = new ObjectInputStream(fis);
		 Dog d1 = (Dog)ois.readObject();
		 System.out.println("Dog object data is ::"+d1.i+" ---> " +d1.j);
		 System.out.println("De-Serialization Ended ...");
		

	}
} 

```

Output :→

```java
Serialization Started ...
Serialization Ended ...

De-Serialization Started ...
Dog object data is ::10 ---> 20
De-Serialization Ended ...
```

## Final vs Transient

Final variables participate in serialization directly by their values. Therefore, declaring a final variable as transient serves no purpose.

```java
import java.io.*;

class Dog implements Serializable
{

	static transient int  i = 10;
	final transient int  j = 20;

}

class Test
{

	public static void main(String [] args) throws Exception
	{   
         Dog d = new Dog();
		

		 System.out.println("Serialization Started ...");
		 String fileName = "abcd.ser";
		 FileOutputStream fos = new FileOutputStream(fileName);
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(d);
		 System.out.println("Serialization Ended ...");

     // Wait for user input (for demonstration purposes)
		 System.in.read();
		
		 System.out.println("De-Serialization Started ...");
		 FileInputStream fis = new FileInputStream(fileName);
		 ObjectInputStream ois = new ObjectInputStream(fis);
		 Dog d1 = (Dog)ois.readObject();
		 System.out.println("Dog object data is ::"+d1.i+" ---> " +d1.j);
		 System.out.println("De-Serialization Ended ...");
		

	}
} 

```

Output →

```java
Serialization Started ...
Serialization Ended ...

De-Serialization Started ...
Dog object data is ::10 ---> 20
De-Serialization Ended ...
```

### Note :→

If we don’t know order of object , how can we deserialize the object?

![s4](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/4e89b92a-3669-40bb-93e0-28b8c3a7d81e)


```java
Object o = oos.readObject();

if(o instanceof Dog)
  // perform Dog related operation
  
if(o instanceof Cat)
  // perform Cat related operation
  
if(o instanceof Rat)
  // perform Rat related operation
```

# Object graph in Serialization

- Whenever we serialize an object, the set of all objects reachable from that object will be automatically serialized. This group of objects is known as the object graph in serialization.


![s5](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/93f3210e-1216-4c67-af86-f5e2ee34ff8b)


- In the object graph, every object should implement Serializable; otherwise, we will encounter a runtime exception, specifically a “`NotSerializableException`”.

```java
import java.io.*;

class Dog implements Serializable{
 Cat c=new Cat();
}
class Cat{
 Rat r=new Rat();
}
class Rat {
 int i=10;
}

class Test
{

	public static void main(String [] args) throws Exception
	{   
         Dog d = new Dog();
		

		 System.out.println("Serialization Started ...");
		 String fileName = "abcd.ser";
		 FileOutputStream fos = new FileOutputStream(fileName);
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(d);
		 System.out.println("Serialization Ended ...");

         // Wait for user input (for demonstration purposes)
		 System.in.read();
		
		 System.out.println("De-Serialization Started ...");
		 FileInputStream fis = new FileInputStream(fileName);
		 ObjectInputStream ois = new ObjectInputStream(fis);
		 Dog d1 = (Dog)ois.readObject();
		 System.out.println(d1.c.r.i);
		 System.out.println("De-Serialization Ended ...");
		

	}
} 

```

Output :→

```java
Serialization Started ...
Exception in thread "main" java.io.NotSerializableException: Cat
        at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1198)
        at java.base/java.io.ObjectOutputStream.defaultWriteFields(ObjectOutputStream.java:1583)
        at java.base/java.io.ObjectOutputStream.writeSerialData(ObjectOutputStream.java:1540)
        at java.base/java.io.ObjectOutputStream.writeOrdinaryObject(ObjectOutputStream.java:1449)
        at java.base/java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1192)
        at java.base/java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:358)
        at Test.main(Test.java:25)
```

Corrected Code

```java
import java.io.*;

class Dog implements Serializable{
 Cat c=new Cat();
}
class Cat implements Serializable{
 Rat r=new Rat();
}
class Rat implements Serializable{
 int i=10;
}

class Test
{

	public static void main(String [] args) throws Exception
	{   
         Dog d = new Dog();
		

		 System.out.println("Serialization Started ...");
		 String fileName = "abcd.ser";
		 FileOutputStream fos = new FileOutputStream(fileName);
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(d);
		 System.out.println("Serialization Ended ...");

         // Wait for user input (for demonstration purposes)
		 System.in.read();
		
		 System.out.println("De-Serialization Started ...");
		 FileInputStream fis = new FileInputStream(fileName);
		 ObjectInputStream ois = new ObjectInputStream(fis);
		 Dog d1 = (Dog)ois.readObject();
		 System.out.println(d1.c.r.i);
		 System.out.println("De-Serialization Ended ...");
		

	}
} 

```

Output :→

```java
Serialization Started ...
Serialization Ended ...

De-Serialization Started ...
10
De-Serialization Ended ...

```

# CustomSerialization

When using transient in serialization, loss of data may happen.


![s6](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/8b08774d-9c7d-47f9-a60b-2b2603320678)


Serialization Example with Transient Keyword

```java
import java.io.*;

class Account implements Serializable{
   String username = "Rajeev";
   transient String password = "Singh";
}

class Test
{

	public static void main(String [] args) throws Exception
	{   
         
		 Account account = new Account();

		 System.out.println("Serialization Started ...");
		 String fileName = "abcd.ser";
		 FileOutputStream fos = new FileOutputStream(fileName);
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(account);
		 System.out.println("Serialization Ended ...");

         // Wait for user input (for demonstration purposes)
		 System.in.read();
		
		 System.out.println("De-Serialization Started ...");
		 FileInputStream fis = new FileInputStream(fileName);
		 ObjectInputStream ois = new ObjectInputStream(fis);
		 Account acc = (Account)ois.readObject();
		 System.out.println("Username is :: "+acc.username);
		 System.out.println("Password is :: "+acc.password);
		 System.out.println("De-Serialization Ended ...");
		

	}
} 

```

Output :→

```java
Serialization Started ...
Serialization Ended ...

De-Serialization Started ...
Username is :: Rajeev
Password is :: null
De-Serialization Ended ...
```

In the example above, before serialization, the **`Account`** object can provide both a proper username and password. However, after deserialization, the **`Account`** object can only provide the username and not the password. This behavior is a result of declaring the password field as transient.

Using default serialization with transient fields introduces the risk of information loss due to the transient keyword.

### **Customized Serialization for Information Recovery**

To recover from this information loss, consider implementing customized serialization. By customizing the serialization process, you can ensure that essential data, such as passwords, is properly handled and not lost during object serialization and deserialization.

![s7](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/16eef1b3-b9c6-4dc7-baad-f10c5ddf13a2)
