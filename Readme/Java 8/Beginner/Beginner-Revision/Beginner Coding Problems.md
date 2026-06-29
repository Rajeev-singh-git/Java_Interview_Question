# Beginner Coding Problems

These problems are designed to test your understanding of the Java 8 Beginner topics rather than your knowledge of algorithms.

Focus on writing **clean, readable, and idiomatic Java 8 code**.

---

# Easy

## 1. Create a Runnable Using a Lambda Expression

Create a `Runnable` that prints:

```
Hello Java 8
```

Execute it using a thread.

**Concepts Covered**

* Lambda Expressions
* Functional Interfaces

---

## 2. Filter Even Numbers

Given:

```java
List<Integer> numbers = List.of(2, 5, 8, 11, 14, 17);
```

Print only the even numbers.

**Constraint**

Use a `Predicate`.

**Concepts Covered**

* Predicate
* Passing Behavior as a Parameter

---

## 3. Convert Names to Uppercase

Given:

```java
List<String> names =
        List.of("java", "spring", "docker");
```

Convert every name to uppercase.

**Constraint**

Use a `Function` and a **Method Reference** wherever possible.

**Concepts Covered**

* Function
* Method References

---

## 4. Create a Custom Functional Interface

Create a Functional Interface named `Calculator`.

Implement it using a Lambda Expression to perform addition.

**Concepts Covered**

* Functional Interface
* Lambda Expression

---

# Medium

## 5. Build an Employee Validator

Create an `Employee` class with:

* id
* name
* salary

Write a `Predicate<Employee>` that returns `true` only if:

* Salary is greater than ₹50,000.

**Concepts Covered**

* Predicate
* Real-world validation

---

## 6. Create Employee Objects Using Constructor References

Create a `Supplier<Employee>` that returns a new `Employee`.

Use a **Constructor Reference** instead of a Lambda.

**Concepts Covered**

* Constructor References
* Supplier

---

## 7. Design an Interface Using Default and Static Methods

Create an interface `PaymentProcessor`.

Requirements:

* One abstract method for processing payments.
* One Default Method to print transaction details.
* One Static Method to validate the payment amount.

Implement the interface for UPI and Credit Card payments.

**Concepts Covered**

* Default Methods
* Static Methods
* Interface Design

---

## 8. Calculate Employee Experience

Given an employee's joining date, calculate the number of years of experience.

**Constraint**

Use the Java 8 Date & Time API.

**Concepts Covered**

* LocalDate
* Period

---

# Advanced

## 9. Schedule an International Meeting

Create a program that prints the current time for:

* India
* London
* New York

Use the Java 8 Date & Time API.

**Concepts Covered**

* ZonedDateTime
* Time Zones

---

## 10. Mini Employee Management Challenge

Create an `Employee` class.

Implement the following:

* Validate salary using a `Predicate`.
* Convert employee names to uppercase using a `Function`.
* Print employee details using a `Consumer`.
* Create employees using a Constructor Reference.
* Calculate employee experience using `LocalDate` and `Period`.

**Constraint**

Use Java 8 features wherever applicable.

**Concepts Covered**

* Lambda Expressions
* Functional Interfaces
* Method References
* Constructor References
* Date & Time API
* Interface Design

---

## Navigation

⬅️ **[Previous: Date & Time API](../06.%20Date%20%26%20Time%20API.md)**

🏠 **[Home: Java 8 Interview Handbook](../../../Java%208%20Interview%20Handbook.md)**

➡️ **[Next: Beginner Interview Questions](./Beginner%20Interview%20Questions.md)**