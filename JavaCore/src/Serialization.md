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


![FileInput](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/1129d47f-08f6-4a22-8bf6-074e311088b3)

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

### Approach 1 : →

Read one character from the file, again go to the file and read, repeat the process

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

### Approach 2 : →

For larger files, the this program may have better performance as it reads the entire content into memory in one go, whereas the first (approach 1) program reads characters one by one which might result in more frequent disk I/O operations.

Read one character in a file, store in an array, continue the reading…

```java
import java.io.*;

class Test
{

	public static void main(String [] args) throws Exception
	{   
		File f =  new File("Rajeev.txt");
		FileReader f1 = new FileReader(f);
		
		char [] ch = new char[(int)f.length()];
		
		//read one character and store it inside char[]
		f1.read(ch);

		for(char data:ch)
			System.out.print(data);

  f1.close();
	}
} 
```

Analogy :→ Take one popcorn from the kitchen eat again go to the kitchen take another and eat or better take all popcorns in a container and then sit and eat.

## Why FileWriter and FileReader is not recommended?

1. While writing data by FileWriter compulsory we should insert line separator (\n) manually which is bigger headache to the programmer.
2. While reading data by FileReader we have to read character by character instead of line by line which is not convenient to the programmer.
3. Assume we need to search for a 10 digit mobile no present in a file called “mobile.txt”.

   → Since we can read only character just to search one mobile no 10 searching and to search 10,000  mobile no need to read 1 cr times, so performance is very low.

4. To overcome these limitations we should go for BufferedWriter and BufferedReader concept.


# BufferedWriter Overview

BufferedWriter is a class in Java used for efficiently writing text data to a file. It improves performance by buffering data internally before writing it to the underlying file system, making it particularly useful for frequent write operations.

## Introduction

BufferedWriter cannot directly open or communicate with a file; instead, it requires a Writer object like FileWriter as its base. This buffering mechanism significantly enhances write performance, especially for small frequent writes.

- BufferedWriter improves performance over FileWriter for small frequent writes.

![BufferReader](https://github.com/Rajeev-singh-git/Java_Interview_Question/assets/87664048/98d07d95-3c4f-4db7-8cb9-c8f4235b0b6a)


## Constructor

```java
BufferedWriter bw = new BufferedWriter(Writer w);
BufferedWriter bw = new BufferedWriter(Writer w,int buffersize);
```

Which of the following declarations are valid?

```java
//Invalid: BufferedWriter cannot directly open files
BufferedWriter bw = new BufferedWriter("abc.txt"); 

//Valid: Creates BufferedWriter with default buffer size using a FileWriter
BufferedWriter bw = new BufferedWriter(new File("abc.txt")); 

//Valid: Allows specifying buffer size
BufferedWriter bw = new BufferedWriter(new FileWriter("abc.txt"));

//Valid: But creates unnecessary nesting of BufferedWriter
BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter("abc.txt")));
```

## Methods

```java
write(int): Writes a single character (represented by its ASCII value) to the file.
write(char[] ch): Writes an array of characters to the file.
write(String s): Writes a String to the file.
newLine(): Inserts a new line character (often \n) into the file.
flush(): Forces all buffered data to be written to the file immediately.
close(): Closes the BufferedWriter and the underlying Writer object (like FileWriter), releasing resources.
```

## **BufferedWriter vs FileWriter:**

When compared to FileWriter, which of the following capabilities are  available as a method in BufferedWriter?

1. Writing data to the file.
2. Closing the Writer
3. Flush the Writer
4. Inserting newline character.

Ans ⇒ 4. Inserting new line characater

- Both FileWriter and BufferedWriter can write data to a file.
- BufferedWriter provides additional methods like `newLine()` and improved performance for frequent writes due to buffering.

## **Example: Writing Data to a Text File**

The following Java program demonstrates how to use BufferedWriter to write data to a text file named "April3.txt":

```java
import java.io.*;

class Test {
    public static void main(String [] args) throws Exception {   
        // Create a FileWriter object to write to the file "April3.txt".
        // The second argument 'true' specifies that the FileWriter should append data to the file if it already exists.
        FileWriter fw = new FileWriter("April3.txt", true);

        // Create a BufferedWriter object which wraps the FileWriter object for efficient writing of text to the file.
        BufferedWriter bw = new BufferedWriter(fw);
         
        // Write an integer value 105 to the file. This will write the character corresponding to the ASCII value 105, which is 'i'.
        bw.write(105);

        // Write the string "Rajeev" to the file.
        bw.write("Rajeev");

        // Write a newline character to the file.
        bw.newLine();

        // Write an array of characters {'J','A','V','A'} to the file.
        char[] ch = {'J','A','V','A'};
        bw.write(ch);

        // Write the string "unicorn" to the file.
        bw.write("unicorn");

        // Flush the BufferedWriter to ensure that any buffered data is written to the file immediately.
        bw.flush();

        // Close the BufferedWriter. This will automatically close the underlying FileWriter as well.
        bw.close();
    }
}

```

### Output : →

```java
new file "April3.text" will be created with content

iRajeev
JAVA
unicorn

```

## **Example: Reading Data from a Text File**

We can read data from a text file using BufferedReader. Here's how:

```java
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class Test {

    public static void main(String[] args) throws IOException {   
        // Create a FileReader object to read from the file "April3.txt".
        FileReader fr = new FileReader("April3.txt");

        // Create a BufferedReader object which wraps the FileReader object for efficient reading of text from the file.
        BufferedReader br = new BufferedReader(fr);
         
        // Read the first line of the file.
        String line = br.readLine();
		
        // Loop until the end of file (line is null).
		while(line != null) {
            // Print the current line.
			System.out.println(line);
            // Read the next line from the file.
			line = br.readLine();
		}

        // Close the BufferedReader. This will automatically close the underlying FileReader as well.
		br.close();
	}
} 

```

## Limitation :

- Only one character at a time can be written at a time.
- While string data can be written, BufferedWriter does not support writing boolean, integer, or other data types directly. Attempting to write integer values will result in the corresponding ASCII character being printed instead.
- To Overcome the limitation of writing any type, of data we need to use “PrintWriter”.

### More Limitations :→

1. **Buffer Size Limitation**: BufferedWriter relies on buffering to improve performance. However, if the buffer size is not managed properly, it can lead to issues such as memory overhead or inefficient write operations.
2. **Not Suitable for Large Files**: BufferedWriter may not be suitable for handling large files, as buffering all the data in memory before writing it to the file can consume a significant amount of memory.
3. **Concurrency Issues**: BufferedWriter is not thread-safe, meaning it may cause issues in concurrent or multi-threaded environments if not used properly with appropriate synchronization mechanisms.
4. **File System Limitations**: BufferedWriter's performance may vary depending on the underlying file system's capabilities and limitations. For example, it may encounter issues with file size limits or file system permissions.
5. **Platform Dependence**: BufferedWriter's behavior may differ across different platforms (e.g., Windows, Linux, macOS) due to differences in file system implementations and Java Virtual Machine (JVM) behavior.

# PrintWriter

**`PrintWriter`** is a class in Java that provides enhanced capabilities for writing text data to files or other output destinations. Unlike **`FileWriter`** and **`BufferedWriter`**, **`PrintWriter`** allows you to write data of different types (such as integers, doubles, booleans, and strings) directly to the output.

## **Constructors :**

1. `PrintWriter(String name)`: Creates a `PrintWriter` associated with a new file specified by the `name` string.
2. `PrintWriter(File f)`: Similar to the first one, but takes a `File` object instead of a string.
3. `PrintWriter(Writer w)`: Creates a `PrintWriter` that writes to an existing `Writer` object. This allows flexibility in output destinations.

## **Methods :**

1. Writes a single character represented by the Unicode value **`ch`** to the output.
2. **`write(char[] ch)`**
   - Writes an array of characters **`ch`** to the output.
3. **`write(String s)`**
   - Writes a string **`s`** to the output.
4. **`flush()`**
   - Flushes the stream, forcing any buffered output to be written out.
5. **`close()`**
   - Closes the **`PrintWriter`**, flushing any buffered output and releasing associated resources.
6. **`print(char ch)`**
   - Prints a single character **`ch`** to the output.
7. **`print(int i)`**
   - Prints an integer **`i`** to the output.
8. **`print(double d)`**
   - Prints a double **`d`** to the output.
9. **`print(boolean b)`**
   - Prints a boolean **`b`** to the output.
10. **`print(String s)`**
   - Prints a string **`s`** to the output.
11. **`println(char ch)`**
   - Prints a single character **`ch`** to the output followed by a newline.
12. **`println(int i)`**
   - Prints an integer **`i`** to the output followed by a newline.
13. **`println(double d)`**
   - Prints a double **`d`** to the output followed by a newline.
14. **`println(boolean b)`**
   - Prints a boolean **`b`** to the output followed by a newline.
15. **`println(String s)`**
   - Prints a string **`s`** to the output followed by a newline.

### **Additional Notes**

- **`PrintWriter`** is often used for writing formatted text to files or network connections.
- It provides convenient methods (**`print`** and **`println`**) for writing different data types without explicitly converting them to strings.
- Remember to flush or close the **`PrintWriter`** after writing to ensure that all data is properly written and resources are released.

### *Example: Writing Data to a Text File using PrintWriter**

```java
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;

public class Test {
    public static void main(String[] args) throws IOException {
        // Main method that demonstrates using PrintWriter to write data to a file

        // Step 1: Create a FileWriter to write to "April6.txt"
        FileWriter fw = new FileWriter("April6.txt");

        // Step 2: Create a PrintWriter to write to the FileWriter
        PrintWriter out = new PrintWriter(fw);

        // Writing Unicode character corresponding to the value 100
        out.write(100);  // Writes the Unicode character 'd' (Unicode value 100)
        out.write('\n'); // Writes a newline character
        
        // Using println to write different types of data
        out.println(100); // Writes the integer 100
        out.println(true); // Writes the boolean value true
        out.println('c');  // Writes the character 'c'
        out.println("DenisRitchie"); // Writes the string "DenisRitchie"

        // Step 3: Flush the PrintWriter to ensure all buffered data is written to the file
        out.flush();

        // Step 4: Close the PrintWriter and underlying FileWriter to release resources
        out.close();
    }
}


```

### Note :→

What is the difference between write(100) and print(100)?
=> In the case of write(100) the corresponding character "d" will be added to the File
=> In the case of print(100) "100" value will be added directly to the File.
Note 1:

1. The most enhanced Reader to read character data from the File is BufferedReader.
2. The most enhanced Writer to write character data to the File is PrintWriter.

Note 2:

1.  In general we can use Readers and Writers to handle character data. Where          as we can use InputStreams and OutputStreams to handle  binary data(like images, audio files, video files etc).

2. We can use OutputStream to write binary data to the File and we can use
   InputStream to read binary data from the File.

                       Character Data => Reader and Writer
    
                    Binary Data => InputStream and OutputStream
