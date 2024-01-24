# Java Interview Questions

## Polymorphism concept

In an interface, the JVM determines the exact method to call based on the object's type at runtime, not the reference type.

```java
OopsConcept.Interf I = new OopsConcept.Demo();
I.methodOne();  // This calls the methodOne implementation in OopsConcept.Demo class

OopsConcept.Interf I2 = new OopsConcept.Demo2();
I2.methodOne();  // This calls the methodOne implementation in OopsConcept.Demo2 class
```

[Complete Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Interf.java)
