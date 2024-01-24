package OopsConcept;

class Vehicle {
    void start() {
        System.out.println("Vehicle starting...");
    }

    void stop() {
        System.out.println("Vehicle stopping...");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car starting...");
    }

    void drive() {
        System.out.println("Car is driving...");
    }
}

class Motorcycle extends Vehicle {
    @Override
    void start() {
        System.out.println("Motorcycle starting...");
    }

    void ride() {
        System.out.println("Motorcycle is riding...");
    }
}

public class OverridingExample {
    public static void main(String[] args) {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Motorcycle();

        vehicle1.start(); // (Car starting...) Calls the overridden start() method in Car class
        vehicle1.stop();  // (Vehicle stopping...)

        System.out.println();

        vehicle2.start(); // (Motorcycle starting...) Calls the overridden start() method in Motorcycle class
        vehicle2.stop();  // (Vehicle stopping...)

        // Uncommenting the lines below would result in a compilation error,
        // as drive() and ride() are specific to Car and Motorcycle, respectively,
        // and the type of the reference is Vehicle.
        // vehicle1.drive(); // Compilation error
        // vehicle2.ride(); // Compilation error
    }
}