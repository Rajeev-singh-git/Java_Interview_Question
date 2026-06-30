# ☕ Java 8 Coding Problems

These problems comprehensively cover the entire Java 8 handbook.

The questions progress from basic concepts to production-level design scenarios and are intended to simulate the style of real Java interviews.

---

# Easy

## 1. Filter Even Numbers

Given:

```java id="cynu9a"
List<Integer> numbers =
        List.of(2, 5, 8, 11, 14);
```

Print only the even numbers.

**Concepts Covered**

* Stream Pipeline
* `filter()`

---

## 2. Convert Names to Uppercase

Convert a list of names into uppercase.

**Constraint**

Use a Method Reference wherever possible.

**Concepts Covered**

* `map()`
* Method Reference

---

## 3. Remove Duplicate Elements

Return a list containing only unique values.

**Concepts Covered**

* `distinct()`
* `collect()`

---

## 4. Find Employee by ID

Implement:

```java id="0rqxnn"
Optional<Employee> findEmployee(int id)
```

Print the employee's name if found; otherwise print:

```text id="g2w2ju"
Employee not found
```

**Constraint**

Do not use explicit `null` checks.

**Concepts Covered**

* Optional
* `orElse()`
* `map()`

---

# Medium

## 5. Group Employees by Department

Return:

```text id="jpjlwm"
IT       → Employees

HR       → Employees

Finance  → Employees
```

**Concepts Covered**

* `groupingBy()`

---

## 6. Department-wise Highest Paid Employee

Return the highest-paid employee from every department.

**Concepts Covered**

* `groupingBy()`
* `maxBy()`
* Optional

---

## 7. Generate Department Statistics

Calculate:

* Employee Count
* Total Salary
* Average Salary
* Highest Salary
* Lowest Salary

for every department.

**Concepts Covered**

* `summarizingInt()`
* `groupingBy()`

---

## 8. Flatten Customer Orders

Each customer contains multiple orders.

Return a list of all purchased product names.

**Constraint**

Do not use nested loops.

**Concepts Covered**

* `flatMap()`

---

## 9. Employee Search API

Implement a search method supporting:

* Department
* Minimum Salary
* Name Sorting
* Top 10 Results

**Concepts Covered**

* `filter()`
* `sorted()`
* `limit()`

---

# Advanced

## 10. Refactor Legacy Code

Convert imperative code into clean Java 8 code.

Your solution should improve readability without making it unnecessarily complex.

**Concepts Covered**

* Streams
* Best Practices

---

## 11. Review a Stream Pipeline

Review a complex Stream pipeline and identify:

* Readability issues
* Performance concerns
* Possible improvements

**Concepts Covered**

* Stream API
* Code Review

---

## 12. Thread Safety Challenge

Review:

```java id="vjlwm9"
List<String> names =
        new ArrayList<>();

employees.parallelStream()
         .forEach(employee ->
                 names.add(employee.getName()));
```

Explain:

* Why it is unsafe.
* How you would fix it.

**Concepts Covered**

* Parallel Streams
* Shared Mutable State

---

## 13. Parallel Stream Decision

A service processes **50 million records**.

Should you use:

* Sequential Stream
* Parallel Stream

Justify your answer.

**Concepts Covered**

* Performance
* Parallel Streams

---

## 14. Production Code Review

Review a Java 8 codebase and identify:

* Misused Streams
* Misused Optional
* Incorrect Collectors
* Performance issues
* Readability problems

Suggest improvements.

**Concepts Covered**

* Best Practices
* Code Review

---

## 15. Employee Analytics Challenge

Design a service that:

* Filters active employees.
* Groups employees by department.
* Calculates salary statistics.
* Finds the highest-paid employee in each department.
* Safely handles missing managers.
* Scales efficiently for millions of records.

Discuss:

* Which Java 8 features you would use.
* Which features you would avoid.
* Why.

**Concepts Covered**

* Stream API
* Collectors
* Optional
* Parallel Streams
* Performance
* Best Practices

---

# What These Problems Test

| Skill                  | Problems       |
| ---------------------- | -------------- |
| Functional Programming | 1–15           |
| Stream API             | 1–15           |
| Collectors             | 5, 6, 7, 15    |
| Optional               | 4, 6, 15       |
| Parallel Streams       | 12, 13, 15     |
| Thread Safety          | 12             |
| Performance            | 11, 13, 14, 15 |
| Code Review            | 11, 14         |
| Design Decisions       | 15             |

---

# Final Advice

Completing these problems requires far more than memorizing Java 8 APIs.

A strong solution should demonstrate:

* Clean and readable code.
* Appropriate use of Java 8 features.
* Good engineering judgment.
* Awareness of performance trade-offs.
* Understanding of production best practices.

---

## Navigation

⬅️ **[Previous: Advanced Interview Questions](../Advanced/Advanced%20Interview%20Questions.md)**

🏠 **[Home: Java 8 Interview Handbook](../Java%208%20Interview%20Handbook.md)**

➡️ **[Next: ⚡ Java 8 Interview Cheat Sheet (10-Minute Revision)](./⚡%20Java%208%20Interview%20Cheat%20Sheet%20%2810-Minute%20Revision%29.md)**
