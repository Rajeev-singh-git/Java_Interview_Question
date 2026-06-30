# ☕ Java 8 Interview Questions

These questions are designed to comprehensively revise the entire Java 8 handbook.

The questions progress from core concepts to production-level design decisions, closely matching the style of real Java interviews.

---

# 1. Java 8 Fundamentals

### 1. Why was Java 8 considered one of the biggest releases in Java?

---

### 2. What programming paradigm did Java 8 introduce?

---

### 3. Explain the concept of **Passing Behavior as a Parameter**.

---

# 2. Functional Programming

### 4. What is a Functional Interface?

---

### 5. Why can Lambda Expressions target only Functional Interfaces?

---

### 6. How do you choose between:

- Predicate
- Function
- Consumer
- Supplier

---

### 7. When should you prefer a Method Reference over a Lambda Expression?

---

# 3. Interface Enhancements

### 8. Why were Default Methods introduced?

---

### 9. Why are Static Methods useful inside interfaces?

Give a real-world example.

---

# 4. Date & Time API

### 10. Why was the new Date & Time API introduced?

---

### 11. Explain the difference between:

- LocalDate
- LocalTime
- LocalDateTime
- ZonedDateTime

---

# 5. Stream API

### 12. Explain the complete Stream Pipeline.

---

### 13. What is the difference between:

- Collection
- Stream

---

### 14. Explain the difference between:

- Intermediate Operations
- Terminal Operations

---

### 15. Explain the difference between:

- filter()
- map()
- flatMap()

---

### 16. Explain the difference between:

- map()
- peek()

---

### 17. Explain the difference between:

- collect()
- Collectors

---

### 18. Explain the difference between:

- groupingBy()
- partitioningBy()

---

### 19. What is Lazy Evaluation?

Why is it important?

---

# 6. Optional

### 20. Why was Optional introduced?

---

### 21. Explain the difference between:

- of()
- ofNullable()

---

### 22. Explain the difference between:

- orElse()
- orElseGet()

---

### 23. Why is Optional.get() generally discouraged?

---

# 7. Parallel Streams

### 24. Are Parallel Streams always faster?

Why?

---

### 25. When should Parallel Streams be avoided?

---

### 26. Explain how Parallel Streams use the Fork/Join Framework.

---

# 8. Best Practices

### 27. When would you choose:

- Traditional Loop
- Stream
- Parallel Stream

---

### 28. What are the most common Java 8 mistakes developers make in production code?

---

# 9. Scenario-Based Questions

### 29. Design an Employee Analytics Service using Java 8.

Requirements:

- Filter active employees
- Group by department
- Calculate salary statistics
- Handle missing managers safely
- Keep the implementation readable and maintainable

Which Java 8 features would you use, and why?

---

### 30. Imagine you are reviewing a Java 8 codebase.

What would you look for before approving the pull request?