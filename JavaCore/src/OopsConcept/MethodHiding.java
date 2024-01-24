package OopsConcept;

class Parent {
    static void greet() {
        System.out.println("Hello from Parent!");
    }
}

class Child extends Parent {
    static void greet() {  // Not overriding, but hiding
        System.out.println("Hello from Child!");
    }
}

public class MethodHiding {
    public static void main(String[] args) {
        Parent.greet(); // Output: Hello from Parent!
        Child.greet();  // Output: Hello from Child!

        Parent p = new Child();
        p.greet();      // Output: Hello from Parent! (Static method called on Parent class)
    }
}

/*Explanation:

The greet() method in Child doesn't override the one in Parent; it merely hides it.
When calling Parent.greet() or Child.greet() directly, the respective static method is called without regard to object types.
Even when calling p.greet() on a Child object through a Parent reference, the Parent class's static method is still called due to static binding.
In Conclusion:

Static methods are inherently bound to their class and cannot be overridden for runtime polymorphism.
If you need to modify behavior for different subclasses, use instance methods (non-static) and overriding, which leverage dynamic binding.
 */