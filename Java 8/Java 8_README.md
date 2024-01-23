# Java Interview Questions

## Polymorphism concept

In an interface, the JVM determines the exact method to call based on the object's type at runtime, not the reference type.

```java
Interf I = new Demo();
I.methodOne();  // This calls the methodOne implementation in Demo class

Interf I2 = new Demo2();
I2.methodOne();  // This calls the methodOne implementation in Demo2 class
```

[Complete Code](https://github.com/Rajeev-singh-git/Java_Interview_Question/blob/main/Java%208/src/Interf.java)
