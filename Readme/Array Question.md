## 📚 Java  Array – Internal Memory Behavior (Interview Q&A)

### **💬 Q 1 : What happens internally when you initialize `a[0] = new int[3];`? Is there a default value for its elements? If yes, what is it?"**

---

When you execute `a[0] = new int[3];`, Java allocates memory on the heap for a new one-dimensional `int` array of size 3.

**Internally:**

- A contiguous block of memory is created for 3 integers.
- The reference `a[0]` now holds the memory address of that array (i.e., it’s no longer `null`).

**Default Values:**

Yes, Java automatically assigns **default values** to all elements in the new array.

Since it's an `int[]`, the default value for each element is:

```
0
```

So after initialization:

```java
System.out.println(Arrays.toString(a[0]));  // Output: [0, 0, 0]
```

---

If you now try:

```java
System.out.println(a[0][0]); // Output: 0
```

It works perfectly, because:

- `a[0]` is no longer `null`
- `a[0][0]` is an actual `int` initialized to `0` by default

---

### **💬 Q 2 : W**hen we declare a 2D array like `int[][] a = new int[2][];`, what is the default value of `a[0]`, and why?

- When you declare:

    ```java
    int[][] a = new int[2][];
    ```

  Java creates a 1D array of 2 **references** to `int[]`, but doesn't yet allocate memory for the inner arrays.

  Without initializing the inner array, its elements don’t exist in memory — only the reference is created and set to `null`

- So initially:

    ```java
    a[0] = null
    a[1] = null
    ```

- Each reference slot is set to `null` by default — it **doesn't point to any memory location** in the heap yet.
- When you later do:

    ```java
    a[0] = new int[3];
    ```

    - Now memory is allocated in the heap for `int[3]`
    - And `a[0]` now contains the **heap address** of that array instead of `null`

👉 **Default values apply only when memory is actually allocated.**

So yes — even if the inner arrays are of type `int[]` (which defaults to `0`),

**no memory is allocated for their elements until the inner arrays themselves are initialized.**

➡️ **Until then, only the reference is created — and it is set to `null`.**

---

### 💬 **Q3: What happens internally when you initialize `a[0] = new int[3];`? Is there a default value for its elements?**

✅ **Answer:**

- Memory is allocated in the heap for a new `int[3]`.
- The reference `a[0]` now points to this memory block.
- Since it's a primitive `int` array, all 3 elements are initialized to default value `0`.

```java
System.out.println(Arrays.toString(a[0])); // Output: [0, 0, 0]
```

---

### 💬 **Q4: Before initializing `a[0]`, what is its default value and why?**

✅ **Answer:**

`a[0]` is `null`.

`int[][]` is an array of references to `int[]`, and in Java, **object references default to `null`** unless explicitly initialized.

---

### 💬 **Q5: Why does `System.out.println(a[0][0])` throw a `NullPointerException` before initializing `a[0]`?**

✅ **Answer:**

Because `a[0]` is `null`, there is **no array object** to access `a[0][0]`.

Trying to access an index inside a `null` reference causes a `NullPointerException`.

---

### 💬 **Q6: If `a[0]` is of type `int[]`, and `int` has a default of 0, then why isn’t `a[0]` also 0 by default?**

✅ **Answer:**

Because `a[0]` is not an `int`, it’s a **reference to an `int[]`**, and **references default to `null`**, not to the type’s default value.

---

### 💬 **Q7: Can we initialize different rows of a 2D array with different sizes in Java?**

✅ **Answer:**

Yes — Java allows **jagged arrays**, since it uses an **array-of-arrays** implementation.

```java
int[][] a = new int[2][];
a[0] = new int[3];
a[1] = new int[1];
```

Each row can be a different size, improving memory efficiency.

---

### 💬 **Q8: What's the difference between `int[][] a = new int[2][3];` and `int[][] a = new int[2][];`?**

| Declaration | Behavior |
| --- | --- |
| `new int[2][3]` | Both outer and inner arrays are created and fully initialized. |
| `new int[2][]` | Only the outer array is created; inner arrays are `null`. |

---

### 💬 **Q9: What does `System.out.println(a)` print after `int[][] a = new int[2][];`?**

✅ **Answer:**

It prints something like:

```java
[[I@3e25a5
```

Explanation:

- `[[I` = 2D array of `int`
- `@3e25a5` = hashcode in hex
- This is Java’s default `toString()` behavior for arrays.

---

### 💬 **Q10: Why isn’t the output something like `ClassName@hashcode`?**

✅ **Answer:**

Arrays in Java use internal type notation:

- `[I` = 1D `int[]`
- `[[I` = 2D `int[][]`
- `[Ljava.lang.String;` = `String[]`

Since they’re **not custom classes**, the internal name is printed instead of a `ClassName`.

---

---

---

## Set 2

---

### 1.) How many objects are created in total during the execution of this code snippet?

### How many objects become eligible for garbage collection by the end of this code snippet's execution?

```java
int[][] a = new int[3][2];
a[0] = new int[3];
a[1] = new int[4];
a = new int[4][3];
```

✅ **Answer:**

🔍 Step-by-Step Analysis:

Step 1: `int[][] a = new int[3][2];`

- Creates:
    - **1 outer array** (size 3)
    - **3 inner arrays**, each of size 2 (Java automatically initializes each row)

✅ Total so far: **4 objects**

- `a`: → [int[2], int[2], int[2]]

---

Step 2: `a[0] = new int[3];`

- Creates a new `int[3]` object
- Replaces the original `a[0]` (which was `int[2]`)

♻️ Original `a[0]` becomes **unreferenced**

✅ Created: +1

♻️ GC: +1

---

Step 3: `a[1] = new int[4];`

- Creates a new `int[4]` object
- Replaces the original `a[1]` (which was `int[2]`)

♻️ Original `a[1]` becomes **unreferenced**

✅ Created: +1

♻️ GC: +1

---

Step 4: `a = new int[4][3];`

- Creates:
    - **1 new outer array** (size 4)
    - **4 new inner arrays** (each `int[3]`)
- The previous outer array (of size 3) and **all** its inner arrays (current and replaced) are now unreferenced.

Here's the breakdown:

- The **outer array** from step 1 is now unreferenced → GC ✅
- `a[0] = new int[3]` → now unreachable → GC ✅
- `a[1] = new int[4]` → now unreachable → GC ✅
- `a[2]` (original `int[2]` from step 1) → still there → GC ✅

✅ Created: +5

♻️ GC: +4 (outer array + 3 inner arrays: [2 replaced + 1 untouched])

---

🔄 Final Totals:

| Description | Count |
| --- | --- |
| **Total objects created** | 4 (step 1) + 1 + 1 + 5 (step 4) = **11** |
| **Eligible for GC** | 1 (`a[0]` old) + 1 (`a[1]` old) + 1 (`a[2]`) + 1 (outer) + 2 (replaced arrays) = **6** |

---

✅ Correct Final Answer:

- **Total objects created:** `11`
- **Objects eligible for GC:** `6`

### Q.) What will be output?

```java
class Test {
    public static void main(String[] args) {
        String[] argh = {"A", "B"};
        args = argh;
        System.out.println(args.length); // 2

        for (int i = 0; i <= args.length; i++) {
            System.out.println(args[i]);
        }
    }
}
```

✅ **Answer:**

```java
2
A
B
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 2

```

### Q.) What will be output?

```java
class Test {
    public static void main(String[] args) {
        String[] argh = {"A", "B"};
        args = argh;
        System.out.println(args.length);//2
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}
```

✅ **Answer:**
```java
2
A
B
```