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
    // Additional field specific to OopsConcept.Dog
    public String breed;

    // Additional method specific to OopsConcept.Dog
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
        myDog.eat();  // OopsConcept.Dog is eating

        // Accessing fields and methods specific to the child class
        myDog.breed = "Golden Retriever";
        System.out.println("Breed: " + myDog.breed);
        myDog.bark();  // OopsConcept.Dog is barking
        myDog.leg();   // Animals generally have 4 legs

        // Using a parent class reference to the child class object
        Animal animal = new Dog();

        // Polymorphism: Parent class reference can only access common methods
        animal.eat();  // OopsConcept.Dog is eating (Child class method is executed)
        animal.leg();  // Animals generally have 4 legs

        // Compile-time error: Parent class reference cannot call child-specific method
        // animal.bark();
    }
}


/*

Inheritance:

The OopsConcept.Dog class inherits from the OopsConcept.Animal class.
The OopsConcept.Dog class has access to the fields (species), methods (eat, leg), and additional members (breed, bark) defined in the OopsConcept.Animal class.
Polymorphism:

Polymorphism is demonstrated when you use a parent class reference (OopsConcept.Animal) to refer to a child class object (OopsConcept.Dog).
The overridden method eat in the OopsConcept.Dog class is invoked through the parent class reference, showcasing dynamic method dispatch during runtime.
 */