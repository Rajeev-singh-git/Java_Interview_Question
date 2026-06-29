# Beginner Interview Questions

These questions cover the most important Java 8 Beginner concepts frequently discussed in technical interviews.

If you can confidently answer these without referring to your notes, you have a strong foundation in Java 8.

---

# Lambda Expressions

### 1. Why were Lambda Expressions introduced?

Explain the problems they solved compared to Anonymous Inner Classes.

---

### 2. Can a Lambda Expression exist without a Functional Interface?

Why or why not?

---

### 3. What is the difference between a Lambda Expression and an Anonymous Inner Class?

Discuss syntax, `this` reference, readability, and use cases.

---

# Functional Interfaces

### 4. What is a Functional Interface?

Why is it also called a SAM (Single Abstract Method) Interface?

---

### 5. Why are Functional Interfaces required for Lambda Expressions?

Explain the relationship between a Lambda Expression and a Functional Interface.

---

### 6. What is the purpose of the `@FunctionalInterface` annotation?

Is it mandatory?

---

### 7. Explain the difference between the following Functional Interfaces.

* Predicate
* Function
* Consumer
* Supplier

When would you choose each one?

---

### 8. What does "Passing Behavior as a Parameter" mean in Java 8?

Can Java really pass methods as parameters?

---

### 9. Which Functional Interface is expected by the following APIs?

* `filter()`
* `map()`
* `forEach()`
* `generate()`
* `reduce()`

---

# Method & Constructor References

### 10. What is a Method Reference?

When should it be preferred over a Lambda Expression?

---

### 11. Can every Lambda Expression be converted into a Method Reference?

Explain with an example.

---

# Default & Static Methods

### 12. Why were Default Methods introduced?

What problem did they solve?

---

### 13. What is the difference between a Default Method and a Static Method?

---

### 14. What happens when two interfaces define the same Default Method?

How do you resolve the conflict?

---

### 15. Why would you define a Static Method inside an interface instead of a utility class?

Give a real-world example.

---

# Date & Time API

### 16. Why was the Java 8 Date & Time API introduced?

What problems existed with `Date`, `Calendar`, and `SimpleDateFormat`?

---

### 17. When would you use each of the following?

* LocalDate
* LocalTime
* LocalDateTime
* ZonedDateTime
* Instant

---

### 18. What is the difference between `Period` and `Duration`?

Give a real-world example for each.

---

### 19. Why is `DateTimeFormatter` preferred over `SimpleDateFormat`?

---

# Scenario-Based Question

### 20. Design a simple Employee Management application using Java 8.

Explain where and why you would use:

* Lambda Expressions
* Functional Interfaces
* Method References
* Default Methods
* Static Methods
* Java 8 Date & Time API

---

## Navigation

⬅️ **[Previous: Beginner Coding Problems](./Beginner%20Coding%20Problems.md)**

🏠 **[Home: Java 8 Interview Handbook](../../../Java%208%20Interview%20Handbook.md)**

➡️ **[Next: Stream API Handbook](../../Intermediate/07.%20🌊%20Stream%20API/07.%20🌊%20Stream%20API%20Handbook.md)**