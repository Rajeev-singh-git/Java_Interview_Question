# Advanced Interview Questions

These questions focus on **design decisions, performance, scalability, and production best practices**.

The goal is not to recall Java 8 APIs, but to demonstrate when and why you would use them.

---

# Streams & Performance

### 1. When would you choose a traditional loop instead of the Stream API?

Discuss the trade-offs in terms of readability, debugging, and performance.

---

### 2. Are Streams always more readable than loops?

Can you think of situations where a loop is the better choice?

---

### 3. A Stream pipeline contains ten chained operations.

Would you keep it as a Stream or refactor it?

How would you decide?

---

### 4. How would you optimize a Stream pipeline that processes millions of records?

What factors would you consider before making changes?

---

# Parallel Streams

### 5. Are Parallel Streams always faster than Sequential Streams?

Explain why this is a common misconception.

---

### 6. When should Parallel Streams be avoided?

Give practical examples.

---

### 7. Why are database operations and REST API calls generally poor candidates for Parallel Streams?

---

### 8. What problems can occur when modifying shared mutable state inside a Parallel Stream?

How would you avoid them?

---

### 9. What is the difference between:

* `forEach()`
* `forEachOrdered()`

When would you use each?

---

# Optional

### 10. Should every method return an `Optional`?

Why or why not?

---

### 11. Why is `Optional.get()` considered a bad practice?

What are the preferred alternatives?

---

### 12. Should `Optional` be used:

* As an Entity field?
* As a DTO field?
* As a Method parameter?

Explain your reasoning.

---

# Design & Best Practices

### 13. How do you decide whether to use:

* Stream
* Parallel Stream
* Traditional Loop

for a given problem?

---

### 14. What are the most common Java 8 mistakes you have seen in production code?

How would you avoid them?

---

### 15. Which Java 8 feature has had the biggest impact on code quality?

Support your answer with examples.

---

# Scenario-Based Questions

### 16. You need to process 50 million records on an 8-core machine.

Would you use Parallel Streams?

What additional information would you need before making the decision?

---

### 17. During a code review, you find the following:

```java
employees.parallelStream()
         .forEach(employee ->
                 employeeRepository.save(employee));
```

What issues do you see?

How would you improve the implementation?

---

### 18. You inherit a project where every method returns `Optional`.

Would you keep this design?

Why or why not?

---

### 19. A Stream pipeline is difficult to read because it chains many operations together.

How would you improve the code while preserving functionality?

---

### 20. Imagine you're designing a new backend service from scratch.

Which Java 8 features would you actively encourage your team to use?

Which features would you ask them to use cautiously?

Explain your reasoning.

---

# Final Interview Advice

Senior Java interviews rarely focus on **remembering APIs**.

Instead, interviewers want to understand:

* Can you choose the right tool for the problem?
* Can you explain performance trade-offs?
* Can you write maintainable code?
* Can you avoid common production pitfalls?
* Can you justify your design decisions?

Demonstrating good engineering judgment is often more valuable than knowing every Java 8 API.

---

## Navigation

⬅️ **[Previous: Advanced Coding Problems](./Advanced%20Coding%20Problems.md)**

🏠 **[Home: Java 8 Interview Handbook](../../Java%208%20Interview%20Handbook.md)**

➡️ **[Next: Java 8 Coding Problems (Comprehensive)](../../🏆%20Java%208%20Master%20Revision/Java%208%20Coding%20Problems.md)**
