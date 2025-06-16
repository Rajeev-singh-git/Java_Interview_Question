# Abstraction Interview Questions

## Table of Contents

1. [What exactly is abstraction in Java, and how is it different from encapsulation](#-1-what-exactly-is-abstraction-in-java-and-how-is-it-different-from-encapsulation)
2. [How does Java achieve abstraction? Why does Java need both abstract classes and interfaces?](#2-how-does-java-achieve-abstraction-why-does-java-need-both-abstract-classes-and-interfaces)
3. [Suppose I create an abstract class with only concrete methods and no abstract method. Is that valid? Why would I do that?](#-3-suppose-i-create-an-abstract-class-with-only-concrete-methods-and-no-abstract-method-is-that-valid-why-would-i-do-that)
4. [Can a constructor be present in an abstract class or an interface? If yes, how are they used or behave?](#-4-can-a-constructor-be-present-in-an-abstract-class-or-an-interface-if-yes-how-are-they-used-or-behave)

---

# 🎯 **Questions on Class Relationships**

---

# 🔗 1. Association vs Dependency

---

## Q1: Imagine two classes: `Order` and `Customer`. A method in `Order` accepts a `Customer` object as a parameter but never stores it. Is this Association or Dependency? Why?

> 🎯 *Follow-up:* How does the duration of interaction influence your answer?

---

✅ **Answer: This is a *Dependency*, not an Association.**

###### 💡 Why?

- In this case, `Order` **uses** `Customer` **temporarily**, typically to perform some operation during the method call (e.g., `processOrder(Customer c)`), but does **not retain a reference** to it.

- There is **no long-term relationship** — once the method finishes, the `Order` object no longer interacts with or remembers the `Customer` object.

📌 **Dependency** means:

> "Class A uses Class B temporarily for some operation but does not own or manage its lifecycle."

📌 **Association** means:

> "Class A maintains a reference to Class B as part of its structure or behavior — indicating a longer-term relationship."

---

#### **🎯 Follow-up: How does the duration of interaction influence your answer?**

⏳ **Duration is key to distinguishing Dependency vs Association:**

| Aspect                  | Dependency                           | Association                                    |
| ----------------------- | ------------------------------------ | ---------------------------------------------- |
| 🔄 **Lifespan**         | Short-lived / one-time use           | Long-lived / ongoing relationship              |
| 🧠 **Memory Retention** | No retention (method-level use only) | Reference stored as a field or property        |
| 📦 **Example**          | Method parameter used briefly        | Field like `Customer customer;` inside `Order` |

👉 So, **if `Customer` is just passed and used in a method**, it’s a **dependency** — *temporary collaboration*.  
But **if `Order` keeps a `Customer` as a member field**, it's an **association** — *ongoing relationship*.

---

## **Q2: If `Order` class has a method `sendEmail(Customer c)` vs having a field `Customer customer;` — how does the relationship differ? How would you classify each?**

---

###### 🧪 Scenario 1: `sendEmail(Customer c)`

```java
class Order {
    void sendEmail(Customer c) {
        // use c temporarily
    }
}
```

###### ✅ Classification: **Dependency**

- `Order` is **temporarily using** a `Customer` object passed as a parameter.

- It does **not maintain ownership** or a long-term reference to the `Customer`.

- Interaction is **short-lived**, scoped to the method execution.

📌 This means:

> `Order` *depends* on `Customer` to perform a task but is **not related** to it beyond that call.

---

##### 🧪 Scenario 2: `Customer customer;` field

```java
class Order {
    Customer customer; // persistent reference
}
```

###### ✅ Classification: **Association**

- `Order` **has a reference** to a `Customer` object.

- This is a **structural relationship** — `Order` maintains a long-term link with `Customer`.

- It represents a **"has-a"** relationship: *"An order has a customer."*

📌 This implies:

> `Order` and `Customer` are **associated** — not just temporarily connected, but logically tied together in the system's structure.

---

## Q3: Can a class be in a dependency relationship with another class and still not break if that class is removed? Under what conditions?

---

✅ **Yes**, a class can depend on another **without breaking** if the other class is removed — but **only under certain conditions**.

---

###### 🧠 Let's break it down:

> **Dependency** = *"uses another class temporarily (e.g., as a method parameter or local variable)."*

If the *depended-on class* is removed, the **depending class won't compile** **unless** you’ve insulated it through **abstraction** or **loose coupling**.

---

##### ✅ Conditions Where Removal Won’t Break the Class

###### 1. **Interface-based Dependency (Programming to Abstraction)**

```java
interface NotificationService {
    void send();
}

class Order {
    void notify(NotificationService ns) {
        ns.send();  // uses interface, not the actual class
    }
}
```

- `Order` **depends on the interface**, not the concrete implementation.

- If a specific implementing class (e.g., `EmailNotification`) is removed, `Order` **still works**, as long as the interface remains.

> 🔑 **Key:** Dependency is on an **abstraction**, not a specific class.

---

###### 2. **Dependency Injected at Runtime via Reflection / Config**

- Some frameworks (e.g., Spring, ServiceLoader) inject dependencies dynamically.

- `Order` might not **directly reference** the dependent class at all — just a class name in config or a dynamic load.

```java
Class<?> clazz = Class.forName(config.get("notifierClass"));
Object obj = clazz.getDeclaredConstructor().newInstance();
```

---

###### 3. Optional or Defensive Code Paths

```java
void sendNotification(Object o) {
    if (o instanceof EmailNotification) {
        ((EmailNotification) o).send();
    }
}
```

- The code may **check for class presence** before using it.

- If the class is removed, compilation may break unless:
  
  - You use reflection, or
  
  - Use **dynamic loading with type erasure** (e.g., weak plugin systems).

---

##### ❌ When It **Will** Break

- If `Order` directly uses a concrete class:

```java
void process(EmailNotification e) {
    e.send();  // Breaks if EmailNotification is deleted
}
```

→ Removal causes **compilation failure**.

---

###### 🧠 Summary Table

| Condition                        | Will it break if the class is removed? |
| -------------------------------- | -------------------------------------- |
| Depends on concrete class        | ❌ Yes, will break                      |
| Depends on interface             | ✅ No, unless the interface is removed  |
| Uses reflection/dynamic loading  | ✅ No at compile-time, maybe at runtime |
| Optional check with `instanceof` | ✅ May avoid breakage, but risky        |

---

###### 🎯 Final Thought:

> **A class can depend on another and still survive its removal — *only if the dependency is abstracted, optional, or dynamically resolved*.**  
> Otherwise, the dependent class becomes tightly coupled and breaks with it

---

# 🧱 2. Aggregation vs Composition

---

## Q4: Suppose a `Team` class holds a list of `Player` objects. If a `Player` can belong to multiple teams and exist even if a team is deleted — is this Composition? Why or why not?

---

###### ❌ **No, this is *not* Composition — it’s Aggregation.**

---

###### 🧠 Here's Why:

- **Composition** implies **strong ownership**:
  
  - The **child (Player)** *cannot* exist independently of the **parent (Team)**.
  
  - If the `Team` is deleted, its `Player`s are also deleted (or have no reason to exist).

- In your case:
  
  - A `Player` can belong to **multiple** `Team`s ➤ clearly **shared ownership**.
  
  - A `Player` exists **even if** one or all teams are deleted ➤ no lifecycle dependency.

✅ This is a textbook example of **Aggregation** (a "has-a" relationship with shared references).

---

###### ⚖️ Comparison:

| Relationship    | Ownership          | Lifecycle Dependency           | Sharing Allowed?                 |
| --------------- | ------------------ | ------------------------------ | -------------------------------- |
| **Aggregation** | Weak (shared)      | ❌ No                           | ✅ Yes (Player in multiple Teams) |
| **Composition** | Strong (exclusive) | ✅ Yes (child dies with parent) | ❌ No                             |

---

###### 🎯 **Follow-up: How would you convert this from Aggregation to Composition using code?**

To convert it to **Composition**, you'd make:

- The `Team` class **fully responsible** for creating and managing its `Player`s.

- `Player` cannot exist without being inside a `Team`.

---

###### 🧱 Aggregation Code (Shared Players)

```java
class Player {
    String name;
}

class Team {
    List<Player> players = new ArrayList<>();

    void addPlayer(Player p) {
        players.add(p); // shared reference
    }
}

```

---

###### 🧱 Composition Code (Players *owned* by Team)

```java
class Player {
    String name;

    Player(String name) {
        this.name = name;
    }
}

class Team {
    private List<Player> players = new ArrayList<>();

    void addPlayer(String name) {
        players.add(new Player(name)); // Players created and owned by Team
    }
}
```

###### ✅ **What changed?**

- The `Team` creates its own `Player`s ➤ not shared.

- No other team can add the same `Player`.

- If the `Team` object is deleted, **all its `Player`s become unreachable** (get garbage collected).

---

## Q5. How would you **enforce composition behavior** at the code level in Java (i.e., make sure the contained object cannot exist outside the container)?

> 🎯 *Deeper probe:* Can this be **violated via reflection**?

---

#### ✅ Enforcing Composition in Code

To enforce *true composition* — where the contained object (child) **cannot exist independently** of the container (parent) — we apply the following rules:

```java
class Player {
    private String name;

    // ❌ Private constructor prevents outside instantiation
    private Player(String name) {
        this.name = name;
    }

    // ✅ Allow Team to access via static nested class
    static Player create(String name) {
        return new Player(name);
    }
}
```

But even better...

---

#### 🧱 2. **Nest the Child Class Inside the Parent**

```java
class Team {
    private List<Player> players = new ArrayList<>();

    public void addPlayer(String name) {
        players.add(new Player(name)); // can only be created by Team
    }

    // 👇 Nested class; inaccessible outside
    private class Player {
        private String name;

        private Player(String name) {
            this.name = name;
        }
    }
}
```

- `Player` is `private` and **cannot be seen or instantiated from outside**.

- The only way to create a `Player` is *through* the `Team`.

🛡️ **This models Composition perfectly**: `Player` only exists *inside* `Team`.

---

### ⚠️ 🎯 Deeper Probe: **Can this be violated via reflection?**

✅ **Yes**, but only *partially and dangerously.

---

#### 💣 What Reflection Can Do:

1. **Access private constructors**

```java
Constructor<Player> ctor = Player.class.getDeclaredConstructor(String.class);
ctor.setAccessible(true); // breaks encapsulation
Player p = ctor.newInstance("Hacker");

```

**2. Bypass access modifiers** to instantiate or manipulate private/nested classes.

---

##### 🔒 Can You Prevent That?

You **can’t fully prevent reflection** in standard Java — but you can **mitigate it**:

---

##### ✅ Defense Mechanisms:

1. **Use Security Manager (deprecated in recent Java)**
   
   - Could restrict `setAccessible(true)` — but no longer reliable in Java 17+.

2. **Fail-fast in Constructor**  
   Add a check to prevent reflection-based instantiation:

```java
private Player(String name) {
    if (!calledFromTeam()) {
        throw new IllegalStateException("Player must be created by Team");
    }
    this.name = name;
}

private boolean calledFromTeam() {
    StackTraceElement[] stack = Thread.currentThread().getStackTrace();
    return Arrays.stream(stack).anyMatch(e -> e.getClassName().equals("Team"));
}
```

> 🚨 Not bulletproof, but deters casual abuse.

---

###### 🧠 Summary:

| Action                                    | Composition Enforced? | Reflection-Proof? |
| ----------------------------------------- | --------------------- | ----------------- |
| Private/nested constructors               | ✅ Yes                 | ❌ No              |
| Nested child class inside parent          | ✅ Yes                 | ❌ No              |
| Constructor with caller validation        | ✅ Partial             | ❌ No              |
| JVM-level sandboxing / custom classloader | ✅ Advanced            | ✅ Mostly          |

---

###### 💡 Final Thought:

> **Composition can be strongly suggested in code, but not absolutely enforced at runtime — unless you control the environment (e.g., sealed module, custom JVM, or bytecode verification).**

In most cases, **nesting the child class + private constructor** is sufficient to model and enforce *composition semantics* in Java.

---

## Q7. You’re designing a `Person` and `Heart` class. The heart’s lifecycle depends on the person. Which relationship fits? Now, if you replace `Heart` with `Passport`, does the relationship change? Explain both.

---

###### 🫀 Scenario 1: `Person` and `Heart`

###### ✅ **Relationship Type: Composition**

---

###### 💡 Why?

- The **heart cannot exist independently** of the person.

- If the `Person` object is destroyed (dies), the `Heart` object should logically and programmatically **cease to exist**.

- The **ownership is exclusive** — a heart belongs to exactly one person.

🧠 Think of this as:

> The **Heart is a part of the Person**. Without the person, the heart has no meaning in the system.

###### 🧱 Code (Composition)

```java
class Person {
    private Heart heart;

    public Person() {
        this.heart = new Heart(); // created and owned by Person
    }

    private class Heart {
        // only accessible within Person
    }
}
```

---

##### 🛂 Scenario 2: `Person` and `Passport`

###### ✅ **Relationship Type: Aggregation**

---

###### 💡 Why?

- A `Passport` is a document **owned by** the person but can:
  
  - Be **passed around** (e.g., to an embassy),
  
  - Be **revoked**, **renewed**, or **replaced** independently,
  
  - Exist **temporarily even if the person object is deleted** (e.g., for records).

- It's **not tightly coupled in lifecycle** — you can model it as a separate entity with a looser bond.

🧠 Think of this as:

> The **Passport is associated with the Person**, but not a physical part of them. It can be transferred, invalidated, or even stored independently.

###### 🧱 Code (Aggregation)

```java
class Passport {
    private String passportNumber;
    // can exist independently
}

class Person {
    private Passport passport;

    public void assignPassport(Passport p) {
        this.passport = p; // externally created, shared if needed
    }
}
```

---

###### 🔍 Summary Table

| Scenario            | Relationship | Lifecycle Dependency | Sharing Allowed?   | Real-world Analogy                 |
| ------------------- | ------------ | -------------------- | ------------------ | ---------------------------------- |
| `Person`–`Heart`    | Composition  | ✅ Yes                | ❌ No               | Heart dies with person             |
| `Person`–`Passport` | Aggregation  | ❌ No                 | ✅ Yes (contextual) | Passport may outlive person object |

---

###### 🧠 Final Insight:

> **Composition** = *"part-of" with strong lifecycle dependency*.  
> **Aggregation** = *"has-a" with looser coupling and independent existence*.

So yes — replacing `Heart` with `Passport` **changes the relationship** from **Composition ➝ Aggregation** because the nature of **ownership, exclusivity, and lifecycle coupling** changes.

---

# 🧬 3. Inheritance

---

## Q8: Inheritance allows code reuse. So does composition. Why do modern design principles (like "Favor composition over inheritance") warn against inheritance? Be specific.

---

##### ✅ **Short Answer:**

Because **inheritance tightly couples classes** and **exposes internal implementation details**, making code **fragile, rigid, and hard to evolve safely** — especially across larger systems or over time.

---

###### ⚠️ **Problems with Inheritance (Why It’s Risky in Modern Design)**

| 🚨 **Problem**                   | 🧠 **What It Means**                                        | ❗ **Why It's Bad**                                                                      |
| -------------------------------- | ----------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| 🔗 **Tight Coupling**            | Child is bound to parent’s structure and logic              | Changes in parent can **unexpectedly break** child behavior                             |
| 🚫 **Inherits Too Much**         | You inherit **everything**, even unwanted stuff             | You **can’t selectively reuse**, leading to bloated or misused classes                  |
| 🔎 **Violates Encapsulation**    | Child can access parent’s `protected` fields/methods        | Parent’s **internal logic leaks out**, making it harder to maintain or refactor         |
| 🔁 **Breaks Substitution (LSP)** | Overriding can **violate the parent’s behavioral contract** | Leads to subtle bugs and **runtime failures** (Liskov Substitution Principle violation) |
| 🔄 **Rigid Hierarchies**         | Deep inheritance trees  become hard to manage               | Difficult to **restructure or extend** without breaking downstream subclasses           |

---

#### 🎯 Deeper: Example Where Inheritance Hurts Maintainability

###### ❌ **Bad Design: Inheritance Gone Wrong**

---

```java
class Bird {
    void fly() { System.out.println("Flying..."); }
}

class Penguin extends Bird {
    // Can't fly, but inherits fly() anyway
}
```

###### 🔥 Problem:

- `Penguin` gets a `fly()` method it **shouldn't** have.

- You now need to **override and disable** the inherited behavior — a hacky patch to a flawed design.

```java
class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}
```

👉 Now any method that **expects a Bird and calls `fly()`** will **crash** at runtime if passed a Penguin.

###### 🔁 Fix via Composition:

```java
interface FlyBehavior {
    void fly();
}

class FlyingBird {
    FlyBehavior flyBehavior = () -> System.out.println("Flying...");
}

class Penguin {
    // No fly behavior
}
```

- Now, only birds that *should* fly will have the behavior — **no accidental reuse**.

---

###### ✅ **Composition Benefits:**

| Aspect        | Inheritance                | Composition                              |
| ------------- | -------------------------- | ---------------------------------------- |
| Coupling      | Tight (compile-time bound) | Loose (runtime wiring)                   |
| Flexibility   | Rigid hierarchy            | Plug and play behavior                   |
| Encapsulation | Leaky (protected access)   | Preserved (uses interfaces or delegates) |
| Reuse Control | Inherit all                | Selectively compose                      |
| Change Impact | High ripple risk           | Localized impact                         |

---

###### 💡 Final Insight:

> **Inheritance is for "is-a" relationships with stable, trusted contracts.**  
> **Composition is for flexibility, safety, and behavior reuse without inheritance baggage.**

Hence, modern design principles **favor composition** — it’s **safer, more maintainable**, and **easier to test, replace, and evolve**.

---

### Q9. What happens when a subclass inherits a method and **silently overrides it with different behavior**? Is there a way to prevent this?

> 🎯 *Discuss*: How does `final`, `@Override`, or sealed classes (Java 17+) help here?

---

###### 🔥 Problem: **Silent Method Override**

When a **subclass overrides a method from its superclass** but:

- Does **not use `@Override`**, and

- **Changes the behavior** without maintaining the parent’s contract...

👉 It can lead to **unexpected behavior, bugs, and broken substitution** — especially when the subclass is **used polymorphically**.

---

###### 🚨 Example of Silent Override Gone Wrong

```java
class Printer {
    void print() {
        System.out.println("Printing in black and white");
    }
}

class ColorPrinter extends Printer {
    void print() { // 👈 silently overrides
        System.out.println("Printing in color");
    }
}
```

If someone uses a `Printer` reference expecting B&W printing:

```java
Printer p = new ColorPrinter();
p.print(); // Outputs: "Printing in color" 😵
```

🔍 The original expectation (B&W) is **violated** — no warning from the compiler.

---

###### 🛡️ Prevention & Control Mechanisms

| ✅ **Tool**            | 🔧 **Purpose**                                                                   | 🛡️ **How It Helps**                                                                             |
| --------------------- | -------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| `@Override`           | **Annotation** that tells the compiler “I’m overriding a method.”                | - If method signature mismatch ➜ **Compile-time error** <br> - Prevents silent override mistakes |
| `final` method        | **Prevents method from being overridden**                                        | - Ensures subclass **cannot change** core behavior                                               |
| `sealed` classes      | (Java 17+) **Restricts which classes can extend a class**                        | - Helps maintain **control over hierarchy** and overrides                                        |
| **Proper visibility** | Use `private` or `package-private` for methods that **should not be overridden** | - Reduces surface area for override risks                                                        |

---

###### ✅ Example with `@Override` + `final`

```java
class Printer {
    final void print() {
        System.out.println("Printing in black and white");
    }
}

class ColorPrinter extends Printer {
    // ❌ Compile-time error: Cannot override final method
    void print() {
        System.out.println("Color print");
    }
}
```

###### ✅ Example with `@Override` — Safe override detection

```java
class Printer {
    void print() {
        System.out.println("Print B&W");
    }
}

class ColorPrinter extends Printer {
    @Override
    void print() {
        System.out.println("Print in color");
    }
}
```

Now if the method signature mismatches:

```java
// @Override
void print(String mode) { // ❌ Compile-time error — no such method in parent
    ...
}
```

---

###### 🧱 Java 17+: `sealed`, `non-sealed`, `final` combo

```java
public sealed class Printer permits ColorPrinter, LaserPrinter {}

public final class ColorPrinter extends Printer {
    // final class — cannot be extended further
}

public non-sealed class LaserPrinter extends Printer {
    // may allow further subclasses, but within controlled hierarchy
}
```

✅ Ensures you **limit who can override and how deep** the hierarchy can grow.

---

###### 🧠 Summary

| Concern                              | Solution                                              |
| ------------------------------------ | ----------------------------------------------------- |
| Prevent silent override              | Use `@Override`                                       |
| Prevent any override                 | Mark method as `final`                                |
| Prevent override outside hierarchy   | Use `sealed` classes (Java 17+)                       |
| Avoid exposing methods unnecessarily | Use restrictive access (`private`, `package-private`) |

---

###### 🎯 Final Insight:

> **Inheritance is powerful but dangerous** when misused. Silent overrides are among the most insidious bugs in OOP.  
> ✅ Use `@Override` as a **must-have** safeguard, and apply `final` or `sealed` for **intentional control and stability**.

---

## Q10. Suppose class `Dog` extends `Animal`, and someone calls `Dog d = new Animal();` — Why does this cause a compile-time error? What principle does this violate?

> 🎯 *Follow-up:* What if the cast is `Animal a = new Dog();` — how does the behavior differ?

---

Because Java **doesn't allow a child class reference to hold a parent class object** — it’s **unsafe** since not all `Animal`s are `Dog`s.

###### 🚫 Violated Principle:

**Liskov Substitution Principle** — You can safely treat a child as a parent, but **not vice versa**.

---

###### 🎯 **What about `Animal a = new Dog();`?**

This is **valid**. It's **upcasting** — safe because a `Dog` **is-a** `Animal`.

✅ **"Java allows a parent class reference to hold a child class object (upcasting)."**

---

###### 🔁 Summary:

| Code                    | Result               | Why                                   |
| ----------------------- | -------------------- | ------------------------------------- |
| `Dog d = new Animal();` | ❌ Compile-time error | Not all Animals are Dogs              |
| `Animal a = new Dog();` | ✅ Valid              | Every Dog is an Animal (polymorphism) |

---

## Q11: Can removing inheritance and using interfaces + composition improve extensibility?

---

✅ **Yes — especially in real-world scenarios like designing a payment system or GUI components.**

---

##### 🔍 **Scenario: Payment Processing System**

###### ❌ Inheritance-based design:

```java
class PaymentProcessor {
    void process() { ... }
}

class CreditCardProcessor extends PaymentProcessor { ... }
class PayPalProcessor extends PaymentProcessor { ... }
```

- Adding a new type like **CryptoProcessor** may require modifying or duplicating base logic.

- Tight coupling → changes ripple across subclasses.

- Hard to combine behaviors (e.g., logging, fraud detection).

---

✅ Interface + Composition design:

```java
interface PaymentMethod {
    void pay(double amount);
}

class CreditCard implements PaymentMethod { ... }
class PayPal implements PaymentMethod { ... }

class PaymentService {
    private PaymentMethod method;
    PaymentService(PaymentMethod method) {
        this.method = method;
    }
    void executePayment(double amount) {
        method.pay(amount);
    }
}
```

###### 🔁 **Why It's More Extensible:**

| Inheritance                        | Interface + Composition             |
| ---------------------------------- | ----------------------------------- |
| Rigid hierarchy                    | Flexible assembly of behavior       |
| Code reuse via subclassing (tight) | Code reuse via delegation (loose)   |
| Hard to scale or combine behaviors | Easy to inject/compose new features |

---

###### 🧠 Final Insight:

> Replacing inheritance with interfaces and composition often leads to **loose coupling**, **easier testing**, and **plug-and-play extensibility** — key for scalable design.

---

## Q12. If class `B` extends `A`, and both declare a field `int x`, what happens when you access `x` via `B`? Is field overriding a thing in Java?

> 🎯 *Deeper:* Why is **method overriding** allowed but **field overriding** discouraged?

---

###### ❌ **No, field overriding is not a thing in Java.**

Java allows **method overriding**, but **not field overriding**.  
When both superclass and subclass declare a field with the same name:

```java
class A {
    int x = 10;
}

class B extends A {
    int x = 20;
}
```

---

###### 🧪 Behavior:

```java
B b = new B();
System.out.println(b.x);         // 🔹 Prints 20 (B's x)
System.out.println(((A)b).x);    // 🔹 Prints 10 (A's x)
```

- Fields are **resolved at compile-time based on the reference type**, not at runtime.

- This is called **field hiding**, not overriding.

---

##### 🎯 **Why does Java discourage "field overriding"?**

| 📌 **Reason**                            | 🔍 **Explanation**                                                                 |
| ---------------------------------------- | ---------------------------------------------------------------------------------- |
| 🔁 Fields don’t support dynamic dispatch | Unlike methods, fields are **not polymorphic** — you can't override field behavior |
| 🧱 Breaks OOP principles                 | Makes code confusing and **breaks substitutability**                               |
| 😵 Surprising behavior                   | Accessing the same object via parent vs child reference gives **different values** |

---

###### 🧠 Final Insight:

> Java **allows method overriding** to enable polymorphism, but **disallows field overriding** to avoid confusion and maintain type safety.  
> Fields are **resolved statically**, methods **dynamically** — that’s why fields can be hidden but not overridden.

---

# ⚙️ 4. Real-Life Design Insight

---

## Q13. You’re building a system with the following classes:

- `Customer`

- `Order`

- `Invoice`

- `PaymentGateway`

Draw the relationships (Association / Aggregation / Composition / Dependency / Inheritance) you would use between these. Justify each.

---

###### 🏗️ **Classes:**

- `Customer`

- `Order`

- `Invoice`

- `PaymentGateway`

---

###### 🔁 **Class Relationships Diagram**

*(Textual, with arrows and labels)*

```java
Customer ──────▶ Order            : Aggregation
Order ─────────▶ Invoice          : Composition
Order ─────────▶ PaymentGateway   : Dependency
```

---

###### 🔍 **Detailed Explanation**

| 🔧 **From**              | 🔁 **To**        | 🔗 **Relationship** | 💡 **Why?**                                                                                        |
| ------------------------ | ---------------- | ------------------- | -------------------------------------------------------------------------------------------------- |
| `Customer`               | `Order`          | **Aggregation**     | A Customer *has* Orders, but Orders can exist independently (e.g., archived Orders).               |
| `Order`                  | `Invoice`        | **Composition**     | An Invoice is *part of* the Order — it cannot exist without its parent Order. Lifecycles are tied. |
| `Order`                  | `PaymentGateway` | **Dependency**      | Order *uses* PaymentGateway temporarily to process payment — no long-term ownership.               |
| `Invoice`, `Order`, etc. | None             | —                   | These are **concrete classes**. No inheritance assumed unless common abstraction needed.           |

> Use **composition** when lifecycles are tightly bound (e.g., Invoice inside Order),  
> **aggregation** when objects are loosely coupled but related (e.g., Customer and Orders),  
> and **dependency** for temporary use or method-level interaction (e.g., PaymentGateway)
