package OopsConcept;


abstract class Shape {

    public abstract double calculateArea();

    public abstract void displayInfo();
}


class Circle extends Shape {
    private final double radius;


    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }


    @Override
    public void displayInfo() {
        System.out.println("Radius of Circle : " + radius);
    }
}


class Square extends Shape {
    private final double side;


    public Square(double side) {
        this.side = side;
    }


    @Override
    public double calculateArea() {
        return Math.pow(side, 2);
    }


    @Override
    public void displayInfo() {
        System.out.println("Side of square : " + side);
    }
}


public class AbstractionExample {

    public static void main(String[] args) {

        Circle circle = new Circle(5.0);
        Square square = new Square(4.0);


        Shape shape1 = circle;
        Shape shape2 = square;

        // Calling methods without knowing the specific implementation
        System.out.println("Area of Circle: " + shape1.calculateArea());    // Area of Circle: 78.53981633974483
        shape1.displayInfo();                                               // Radius of Circle : 5.0

        System.out.println("Area of Square: " + shape2.calculateArea());    // Area of Square: 16.0
        shape2.displayInfo();                                               // Side of square : 4.0
    }
}
