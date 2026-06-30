# Intermediate Interview Questions

These questions cover the complete **Intermediate Java 8** module.

Unlike the Stream Interview Questions, these questions combine multiple Java 8 concepts such as **Streams**, **Collectors**, and **Optional**.

---

# Streams

### 1. When would you choose a traditional loop instead of the Stream API?

Discuss readability, debugging, and performance.

---

### 2. Explain the complete Stream Pipeline.

What happens internally from `stream()` to the Terminal Operation?

---

### 3. Why are Intermediate Operations lazy?

How does lazy evaluation improve performance?

---

### 4. What is the difference between:

- `filter()`

- `map()`

- `flatMap()`

Give a practical example for each.

---

### 5. What is the difference between:

- `findFirst()`

- `findAny()`

Which one is more suitable for Parallel Streams?

---

### 6. Why should `peek()` not be used for business logic?

What is its intended purpose?

---

### 7. What is the purpose of the `reduce()` operation?

When would you choose `reduce()` over a Collector?

---

# Collectors

### 8. What is the difference between `collect()` and `Collectors`?

---

### 9. Explain the difference between:

- `groupingBy()`

- `partitioningBy()`

Give a real-world example for each.

---

### 10. What happens if duplicate keys are encountered in `Collectors.toMap()`?

How would you resolve the problem?

---

### 11. Why is `mapping()` useful inside `groupingBy()`?

---

### 12. When would you use `summarizingInt()` instead of performing multiple Stream operations?

---

# Optional

### 13. Why was `Optional` introduced?

What problem does it solve?

---

### 14. What is the difference between:

- `Optional.of()`

- `Optional.ofNullable()`

---

### 15. What is the difference between:

- `orElse()`

- `orElseGet()`

Why is this question frequently asked in interviews?

---

### 16. Why is `Optional.get()` generally discouraged?

What are the preferred alternatives?

---

### 17. Should `Optional` be used:

- As an Entity field?

- As a DTO field?

- As a Method parameter?

Why or why not?

---

# Scenario-Based Questions

### 18. Design an Employee Search API.

The requirements are:

- Search by Department

- Filter by Salary

- Return the highest-paid employee

- Handle missing results safely

Which Java 8 features would you use, and why?

---

### 19. A REST API returns employee data that may be missing.

How would you use `Optional` and the Stream API to process the response safely?

---

### 20. Design a Department Analytics service.

The service should:

- Group employees by department.

- Calculate salary statistics.

- Return the top three highest-paid employees.

- Avoid `NullPointerException`.

Explain your approach using Java 8 features.

---

## Navigation

⬅️ **[Previous: Intermediate Coding Problems](https://chatgpt.com/g/g-p-69836e8421a0819181b313d2d1419f17/c/Intermediate%20Coding%20Problems.md)**

🏠 **[Home: Java 8 Interview Handbook](https://chatgpt.com/g/Java%208%20Interview%20Handbook.md)**

➡️ **[Next: Parallel Streams](https://chatgpt.com/g/Advanced/09.%20Parallel%20Streams.md)**
