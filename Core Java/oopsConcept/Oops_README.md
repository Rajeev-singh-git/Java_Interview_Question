# Oops 


## Abstraction

Abstraction is a process of hiding the internal implementation details and showing only functionality to the user. Another way, it shows only essential things to the user and hides the internal details


Ex : ATM GUI screen only highlights 

Abstraction can be achieved using

1.) Abstract Classes:

An abstract class is a class that cannot be instantiated on its own and may contain abstract methods (methods without a body) that are meant to be implemented by its subclasses.

[Code Example](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Core%20Java/oopsConcept/AbstractionExample.java)

2.) Using Interface:

Interfaces:

An interface in Java is a collection of abstract methods. A class can implement multiple interfaces.

[Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Core%20Java/oopsConcept/AbstractionExampleUsingInterface.java)

## Polymorphism concept

In an interface, the JVM determines the exact method to call based on the object's type at runtime, not the reference type.

```java
Interf I = new Demo();
I.methodOne();  // This calls the methodOne implementation in Demo class

Interf I2 = new Demo2();
I2.methodOne();  // This calls the methodOne implementation in Demo2 class
```

[Complete Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Interf.java)
