package OopsConcept;

class Animal {

    public String species;

    public void eat() {
        System.out.println("Animal is eating");
    }

    public void leg() {
        System.out.println("Animals generally have 4 legs");
    }
}

class Dog extends Animal {

    public String breed;

    // child exclusive behaviour (method)
    public void bark() {
        System.out.println("Dog is barking");
    }

    @Override
    public void eat() {
        System.out.println("Dog is eating");
    }
}

public class InheritanceExample {

    public static void main(String[] args) {

        Dog myDog = new Dog();
        // Accessing inherited field and method from the parent class
        myDog.species = "Canine";
        System.out.println("Species: " + myDog.species);
        myDog.eat();  // Dog is eating

        // Accessing fields and methods specific to the child class
        myDog.breed = "Golden Retriever";
        System.out.println("Breed: " + myDog.breed);
        myDog.bark();  // Dog is barking
        myDog.leg();   // Animals generally have 4 legs

        // Using a parent class reference for a child class object (polymorphism)
        Animal animal = new Dog();

        // Method resolution is based on the actual object type (Dog) at runtime
        // If Dog overrides the method, that version is called; otherwise, Animal's method is used
        animal.eat();  // Output: Dog is eating
        animal.leg();  // Output: Animals generally have 4 legs

        // Compile-time error: Parent class reference cannot call child-exclusive method
        // animal.bark();
    }
}


/*
📌 Method Resolution in Polymorphism
When you call a non-static, non-final, non-private method using a parent class reference, Java checks:

→ If the method is overridden in the child class, the child’s version is executed.
This is called runtime method dispatch or dynamic method dispatch.

If the method is not overridden in the child class, the parent class method is executed.
 */