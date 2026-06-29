# ⭐ Common Java APIs Using Functional Interfaces

One of the biggest changes introduced in Java 8 was that many JDK APIs started accepting **Functional Interfaces** as parameters.

Instead of writing classes to define behavior, we now pass behavior using **Lambda Expressions** or **Method References**.

Understanding which API expects which Functional Interface is essential for writing modern Java code.

---

# Java API → Functional Interface Mapping

| Java API                         | Functional Interface | Purpose                  |
| -------------------------------- | -------------------- | ------------------------ |
| `Stream.filter()`                | `Predicate<T>`       | Filter elements          |
| `Stream.map()`                   | `Function<T, R>`     | Transform elements       |
| `Stream.forEach()`               | `Consumer<T>`        | Perform an action        |
| `Stream.generate()`              | `Supplier<T>`        | Generate elements        |
| `Stream.reduce()`                | `BinaryOperator<T>`  | Combine elements         |
| `Optional.filter()`              | `Predicate<T>`       | Filter value             |
| `Optional.map()`                 | `Function<T, R>`     | Transform value          |
| `Optional.ifPresent()`           | `Consumer<T>`        | Consume value if present |
| `CompletableFuture.thenApply()`  | `Function<T, R>`     | Transform async result   |
| `CompletableFuture.thenAccept()` | `Consumer<T>`        | Consume async result     |
| `Map.forEach()`                  | `BiConsumer<K, V>`   | Process key-value pairs  |
| `List.sort()`                    | `Comparator<T>`      | Compare elements         |

---

# Common Examples

## Stream.filter()

```java
numbers.stream()
       .filter(n -> n % 2 == 0);
```

✔ Expects **Predicate**

---

## Stream.map()

```java
names.stream()
     .map(String::toUpperCase);
```

✔ Expects **Function**

---

## Stream.forEach()

```java
names.forEach(System.out::println);
```

✔ Expects **Consumer**

---

## Stream.generate()

```java
Stream.generate(Math::random)
      .limit(5)
      .forEach(System.out::println);
```

✔ Expects **Supplier**

---

## Stream.reduce()

```java
numbers.stream()
       .reduce(Integer::sum);
```

✔ Expects **BinaryOperator**

---

## Optional.ifPresent()

```java
optional.ifPresent(System.out::println);
```

✔ Expects **Consumer**

---

## CompletableFuture.thenApply()

```java
future.thenApply(String::length);
```

✔ Expects **Function**

---

## Map.forEach()

```java
marks.forEach(
    (name, score) ->
        System.out.println(name + " : " + score)
);
```

✔ Expects **BiConsumer**

---

# The Big Picture

```
Java API
      │
      ▼
Expects Functional Interface
      │
      ▼
We provide
Lambda Expression
or
Method Reference
      │
      ▼
Java executes our behavior
```

---

# Final Takeaway

> **Modern Java APIs don't accept methods directly—they accept Functional Interface objects. Lambda Expressions and Method References simply provide the implementation of those Functional Interfaces.**

---

## Navigation

⬅️ **[Previous: Passing Behavior as a Parameter](./03.2%20Passing-Behavior-as-a-Parameter.md)** | 🏠 **[Home: Functional Interfaces Handbook](./03.%20📘%20Functional%20Interfaces%20Handbook.md)** | ➡️ **[Next: Predicate](./03.3%20Predicate.md)**
