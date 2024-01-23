// Parent class (superclass)
class Animal {
    // Public field
    public String species;

    // Public method
    public void eat() {
        System.out.println("Animal is eating");
    }

    // Additional method
    public void leg() {
        System.out.println("Animals generally have 4 legs");
    }
}

// Child class (subclass) inheriting from Animal
class Dog extends Animal {
    // Additional field specific to Dog
    public String breed;

    // Additional method specific to Dog
    public void bark() {
        System.out.println("Dog is barking");
    }

    // Overriding the eat method from the parent class
    @Override
    public void eat() {
        System.out.println("Dog is eating");
    }
}

public class InheritanceExample {
    public static void main(String[] args) {
        // Creating an instance of the child class
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

        // Using a parent class reference to the child class object
        Animal animal = new Dog();

        // Polymorphism: Parent class reference can only access common methods
        animal.eat();  // Dog is eating (Child class method is executed)
        animal.leg();  // Animals generally have 4 legs

        // Compile-time error: Parent class reference cannot call child-specific method
        // animal.bark();
    }
}


/*

Inheritance:

The Dog class inherits from the Animal class.
The Dog class has access to the fields (species), methods (eat, leg), and additional members (breed, bark) defined in the Animal class.
Polymorphism:

Polymorphism is demonstrated when you use a parent class reference (Animal) to refer to a child class object (Dog).
The overridden method eat in the Dog class is invoked through the parent class reference, showcasing dynamic method dispatch during runtime.
 */