package OopsConcept;

// Abstract class representing a OopsConcept.Shape
abstract class Shape {
    // Abstract method to calculate the area
    public abstract double calculateArea();

    // Abstract method to display information about the shape
    public abstract void displayInfo();
}

// Concrete class representing a OopsConcept.Circle
class Circle extends Shape {
    private double radius;

    // Constructor
    public Circle(double radius) {
        this.radius = radius;
    }

    // Implementation of abstract method to calculate the area
    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    // Implementation of abstract method to display information
    @Override
    public void displayInfo() {
        System.out.println("OopsConcept.Circle - Radius: " + radius);
    }
}

// Concrete class representing a OopsConcept.Square
class Square extends Shape {
    private double side;

    // Constructor
    public Square(double side) {
        this.side = side;
    }

    // Implementation of abstract method to calculate the area
    @Override
    public double calculateArea() {
        return Math.pow(side, 2);
    }

    // Implementation of abstract method to display information
    @Override
    public void displayInfo() {
        System.out.println("OopsConcept.Square - Side: " + side);
    }
}

// Main class to demonstrate abstraction
public class AbstractionExample {
    public static void main(String[] args) {
        // Creating objects of concrete classes
        Circle circle = new Circle(5.0);
        Square square = new Square(4.0);

        // Using abstraction to treat objects uniformly
        Shape shape1 = circle;
        Shape shape2 = square;

        // Calling methods without knowing the specific implementation
        System.out.println("Area of OopsConcept.Circle: " + shape1.calculateArea());    // Area of OopsConcept.Circle: 78.53981633974483
        shape1.displayInfo();                                               // OopsConcept.Circle - Radius: 5.0

        System.out.println("Area of OopsConcept.Square: " + shape2.calculateArea());    // Area of OopsConcept.Square: 16.0
        shape2.displayInfo();                                               // OopsConcept.Square - Side: 4.0
    }
}
