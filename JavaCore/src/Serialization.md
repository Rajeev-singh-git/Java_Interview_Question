# Serialization

# File Input Output

In Java programming, the concept of file input/output (I/O) involves reading from and writing to files stored on secondary memory devices like hard disks. Persistence, on the other hand, refers to the mechanism of storing data permanently, often accomplished by saving objects to files.

```java
class  Student
{
	private String sname;
	private Integer sid;
	private Integer sage;

	Student(Integer sid, String sname, Integer sage){
		this.sname = sname;
		this.sid = sid;
		this.sage = sage;
	}

	public String toString(){
		return "sid: "+sid+ "  sname: "+sname+ "  sage" +sage;
	}
}

class Test
{

	public static void main(String [] args){
		Student s1 = new Student(1, "Raj", 21);
        System.out.println(s1);
	}
}

```

Once the object output is displayed, can we retrieve the object back?

No, because objects reside in RAM, which is volatile memory. Once the Java Virtual Machine (JVM) shuts down, objects in memory are lost. To retain data beyond program execution, we need to store it in secondary memory (e.g., hard disk or database).

If we want to get data for future uses, we have to keep it in Secondary memory unit (Hard Disk) or Data base.

Data in Hard disk is stored in file which is smallest storage unit and in DB in table form.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/c3d21907-7e8a-4e9c-b8a1-1c113798fdb2/Untitled.png)

# What is Persistency?

Persistence involves storing data permanently on a file or database. In Java, persistence can be achieved using APIs provided by the **`java.io`** package.

## Code snippet to create and check the existence of a file:

```java
import java.io.*;

class Test
{

	public static void main(String [] args) throws Exception{

		String fileName = "abc.txt";

		/*
		Checks whether the file called "abc.txt" exists or not 
		if it is available then it would go and point to that file
		otherwise it will represent a java file object, not physical file.
		*/
		File f = new File(fileName);
		System.out.println(f.exists()); // false

       //It will create a physical file for the java file object, if it does not already exist.
		f.createNewFile();

		System.out.println(f.exists()); // True
		
	}
}

/*
Output : ->

When we run code first time

false
true

When we run code Second time

true
true

*/
```

## Code demonstrating how to create a directory using Java I/O classes.

```java
import java.io.*;

/*
The java.io API classes are built using the standards of Unix OS.
In Linux/Unix OS, there is no difference between a file and a directory.
*/

class Test {

    public static void main(String[] args) throws IOException {

        String directoryName = "IPLteams"; // If extension not specified, JVM will treat it as a directory. For JVM, there is no difference between a directory and a filename.

        File directory = new File(directoryName);
        System.out.println(directory.exists()); // Output: false

        directory.mkdir(); // Create the directory

        System.out.println(directory.exists()); // Output: true
    }
}

/*
Output : ->

When we run code first time

false
true

When we run code Second time

true
true

*/

```

## Program for creation of a directory and a file within that directory using the **`File`** class in Java.

```java
import java.io.*;

class Test
{

	public static void main(String [] args) throws Exception{

		File f = new File("MyFile");
		f.mkdir();
		System.out.println("Is f pointing to a directory :: "+f.isDirectory());

       
     	File f1 = new File(f, "abc.txt");
		f1.createNewFile();
		System.out.println("Is f1 pointing to a file :: "+f1.isFile()); 

	}

}

/*
Is f pointing to a directory :: true
Is f1 pointing to a file :: true
*/
```

Program to Create a Directory and File at Desired Location

```java
import java.io.*;

class Test
{

	public static void main(String [] args) throws Exception{

		String location = "C:\\javaSkills";
	
		File f = new File(location);
		f.mkdir();

       
     	File f1 = new File(f, "java.txt");
		f1.createNewFile();
	
	}

}
```

## Program to Count Files of Different Types in a Directory

```java
import java.io.*;

class Test
{

	public static void main(String [] args)
	{
		int dirCount = 0;
		int fileCount = 0;
		int pdfFileCount = 0;
		int txtFileCount = 0;
		int docFileCount = 0;
		int jpgFileCount = 0;

		String location = "D:\\Details";
		File f = new File(location);

	    String [] names = f.list();

	   for(String name: names){
            
		 File file = new File(f, name);

             if (file.isDirectory()) {
                 dirCount++;
               }
             if (file.isFile()) {

				 if(name.endsWith(".jpg"))
				     jpgFileCount++;
				 if(name.endsWith(".txt"))
				     txtFileCount++;
				 if(name.endsWith(".docx"))
				     docFileCount++;
				  if(name.endsWith(".pdf"))
				     pdfFileCount++;
				 

                  fileCount++;
              }
           System.out.println(name);
       }

       System.out.println("Total no of files is :: " +fileCount);
	   System.out.println("Total no of jpg File is :: " +jpgFileCount);
	   System.out.println("Total no of txt File is :: " +txtFileCount);
	   System.out.println("Total no of doc File is :: " +docFileCount);
	   System.out.println("Total no of pdf File is :: " +pdfFileCount);
	}
}
```

On a existing file/directory we performed following operation

- isFile()
- isDirectory()
- length()
- exists()
- mkdir()
- craeteNewFile()
- list()

If we want to perform read operation on File, we need to use “`FileReader`”.

If we want to perform read operation on File, we need to use “`FileWriter`”.

## Program to write data to a file using FileWriter

```java
import java.io.*;

class Test
{

	public static void main(String [] args) throws Exception
	{
		File f1 = new File("abc.txt");
		FileWriter f2 = new FileWriter(f1);

		//performing write operation on a file
		
		f2.write(97);
		f2.write("\n");
		f2.write("Hello Sachin how are you ??");
		f2.write("\n");
		char [] ch = {'P','W','S','K','I','L','L','S'};
		f2.write(ch);

		// closing the resource
		f2.close();

		System.out.println("open abc.txt to see the output");

	}
} 
```

Main problem with fileWriter is we have to insert line separator manually, which is difficult to the programmer. (”\n”)

## Program to read data from a file named "Rajeev.txt" using the **`FileReader`** class

```java
import java.io.*;

class Test
{

	public static void main(String [] args) throws Exception
	{
		FileReader f1 = new FileReader("Rajeev.txt");
		int i = f1.read();

		while(i!=-1){
			System.out.print(i+"===>");
			System.out.println((char)i);
			i=f1.read();
		}

	}
} 
```