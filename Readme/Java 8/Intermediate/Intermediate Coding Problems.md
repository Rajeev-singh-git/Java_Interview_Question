# Intermediate Coding Problems

These problems are designed to test your understanding of the complete **Intermediate Java 8** module.

Unlike the Stream Coding Problems, these exercises require you to combine multiple Java 8 features such as the **Stream API**, **Collectors**, and **Optional** to solve realistic business problems.

---

# Problem 1. Find Duplicate Employee Names

Given a list of employees, print only the employee names that appear more than once.

Expected Output

```text
Raj
Amit
```

**Concepts Covered**

- `groupingBy()`

- `counting()`

- `filter()`

---

# Problem 2. Department-wise Highest Paid Employee

Given a list of employees, return the highest-paid employee from each department.

Expected Result

```text
IT       -> Raj
HR       -> Neha
Finance  -> Amit
```

**Concepts Covered**

- `groupingBy()`

- `maxBy()`

- `Optional`

---

# Problem 3. Find Missing Employee

Implement:

```java
Optional<Employee> findEmployee(int id)
```

Print the employee name if found; otherwise print:

```text
Employee not found
```

**Constraint**

Do not use explicit `null` checks.

**Concepts Covered**

- Optional

- `ifPresent()`

- `orElse()`

---

# Problem 4. Product Inventory Summary

Given a list of products, calculate:

- Total Products

- Total Inventory Value

- Average Product Price

- Most Expensive Product

**Concepts Covered**

- `counting()`

- `summingDouble()`

- `averagingDouble()`

- `max()`

---

# Problem 5. Flatten Customer Orders

Each customer contains multiple orders.

Return a list of all purchased product names.

**Constraint**

Do not use nested loops.

**Concepts Covered**

- `flatMap()`

---

# Problem 6. Validate User Email

Implement:

```java
Optional<User> findUser(String email)
```

Return the user's name in uppercase.

If the user does not exist, return:

```text
UNKNOWN USER
```

**Concepts Covered**

- Optional

- `map()`

- `orElse()`

---

# Problem 7. Department Analytics Dashboard

Generate a report containing:

- Number of Employees

- Average Salary

- Highest Salary

- Lowest Salary

for every department.

**Concepts Covered**

- `groupingBy()`

- `summarizingInt()`

---

# Problem 8. Find the First Available Manager

Every employee may or may not have a manager.

Print the manager's name.

If no manager exists, print:

```text
Manager Not Assigned
```

**Constraint**

Avoid explicit null checks.

**Concepts Covered**

- Optional

- `flatMap()`

- `orElse()`

---

# Problem 9. Build a Search API

Implement a search method that can:

- Filter by Department

- Filter by Salary

- Sort by Name

- Return the first 10 matching employees

**Concepts Covered**

- `filter()`

- `sorted()`

- `limit()`

---

# Problem 10. Employee Analytics Challenge

Given a list of employees:

- Remove duplicate employees.

- Filter active employees.

- Group employees by department.

- Find the highest-paid employee in each department.

- Generate salary statistics.

- Return employee names in alphabetical order.

- Safely handle missing managers using `Optional`.

**Constraint**

Write clean, readable Stream pipelines.

**Concepts Covered**

- Stream Pipeline

- Collectors

- Optional

- Method References

- Stream Operations

---

# What These Problems Test

| Concept            | Problems          |
| ------------------ | ----------------- |
| Stream Pipeline    | 1, 4, 5, 7, 9, 10 |
| Collectors         | 1, 2, 4, 7, 10    |
| Optional           | 2, 3, 6, 8, 10    |
| `flatMap()`        | 5, 8              |
| `groupingBy()`     | 1, 2, 7, 10       |
| `summarizingInt()` | 7, 10             |
| `maxBy()`          | 2                 |
| `map()`            | 6                 |
| `orElse()`         | 3, 6, 8           |

---

## Navigation

⬅️ **[Previous: Optional](https://chatgpt.com/g/g-p-69836e8421a0819181b313d2d1419f17/08.%20Optional.md)**

🏠 **[Home: Java 8 Interview Handbook](https://chatgpt.com/g/Java%208%20Interview%20Handbook.md)**

➡️ **[Next: Intermediate Interview Questions](https://chatgpt.com/g/g-p-69836e8421a0819181b313d2d1419f17/c/Intermediate%20Interview%20Questions.md)**
