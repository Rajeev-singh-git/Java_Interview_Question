# Advanced Coding Problems

These problems focus on **real-world scenarios** where choosing the right Java 8 feature is more important than simply knowing the API.

In addition to writing code, be prepared to justify your design and performance decisions.

---

# Problem 1. Optimize Report Generation

A report processes **20 million employee records** and performs complex salary calculations.

Should you use:

* Sequential Stream
* Parallel Stream

Justify your choice.

**Concepts Covered**

* Parallel Streams
* Performance
* CPU-bound processing

---

# Problem 2. Process REST API Responses

A REST API returns employee data.

Some employees may not have a manager.

Return the manager's name if available; otherwise return:

```text id="pp8jwo"
Manager Not Assigned
```

**Constraint**

Do not use explicit `null` checks.

**Concepts Covered**

* Optional
* `map()`
* `orElse()`

---

# Problem 3. Detect Performance Bottlenecks

Review the following code.

```java id="kocg5e"
employees.parallelStream()
         .forEach(employee ->
                 employeeService.save(employee));
```

Identify the problems.

Suggest a better implementation.

**Concepts Covered**

* Parallel Streams
* I/O-bound operations
* Thread safety

---

# Problem 4. Refactor Legacy Code

Convert the following imperative code into clean Java 8 code.

```java id="x3h0bn"
List<Employee> result = new ArrayList<>();

for (Employee employee : employees) {

    if (employee.isActive()) {
        result.add(employee);
    }
}
```

**Constraint**

Improve readability without making the solution more complex.

**Concepts Covered**

* Stream API
* Best Practices

---

# Problem 5. Choose the Right Collector

Generate a department report containing:

* Employee Count
* Average Salary
* Highest Salary
* Lowest Salary

Choose the most appropriate Collector.

Explain why.

**Concepts Covered**

* `groupingBy()`
* `summarizingInt()`

---

# Problem 6. Stream or Loop?

Given a list of 10 integers.

Would you choose:

* Stream
* Traditional Loop

Explain your reasoning.

**Concepts Covered**

* Readability
* Performance
* Best Practices

---

# Problem 7. Code Review Challenge

Review the following code.

```java id="grg0kr"
Optional<Employee> employee =
        findEmployee(id);

if (employee.isPresent()) {
    System.out.println(employee.get().getName());
}
```

Refactor it using modern Optional APIs.

**Concepts Covered**

* Optional
* `ifPresent()`
* `map()`
* `orElse()`

---

# Problem 8. Thread Safety Challenge

Find the issue.

```java id="8kwg4z"
List<String> names =
        new ArrayList<>();

employees.parallelStream()
         .forEach(employee ->
                 names.add(employee.getName()));
```

Explain:

* What can go wrong?
* How would you fix it?

**Concepts Covered**

* Parallel Streams
* Shared Mutable State
* Thread Safety

---

# Problem 9. Performance Review

Review the Stream pipeline.

```java id="5zrdqj"
employees.stream()
         .filter(Employee::isActive)
         .map(Employee::getDepartment)
         .distinct()
         .sorted()
         .collect(Collectors.toList());
```

Discuss:

* Readability
* Performance
* Whether any optimization is required

**Concepts Covered**

* Stream Pipeline
* Best Practices

---

# Problem 10. Design Challenge

Design an Employee Analytics Service that:

* Processes millions of employee records.
* Avoids `NullPointerException`.
* Produces department-wise reports.
* Scales efficiently on multi-core processors.
* Maintains readable and maintainable code.

Discuss:

* Which Java 8 features would you use?
* Which would you avoid?
* Why?

**Concepts Covered**

* Streams
* Collectors
* Optional
* Parallel Streams
* Performance
* Best Practices

---

# What These Problems Test

| Skill                | Problems       |
| -------------------- | -------------- |
| Stream Design        | 4, 6, 9, 10    |
| Optional             | 2, 7, 10       |
| Parallel Streams     | 1, 3, 8, 10    |
| Thread Safety        | 3, 8           |
| Performance Analysis | 1, 3, 6, 9, 10 |
| Code Review          | 3, 4, 7, 8, 9  |
| Design Decisions     | 10             |

---

## Navigation

⬅️ **[Previous: Java 8 Best Practices & Performance](../Advanced/10.%20Java%208%20Best%20Practices%20%26%20Performance.md)**

🏠 **[Home: Java 8 Interview Handbook](../../Java%208%20Interview%20Handbook.md)**

➡️ **[Next: Advanced Interview Questions](./Advanced%20Interview%20Questions.md)**
