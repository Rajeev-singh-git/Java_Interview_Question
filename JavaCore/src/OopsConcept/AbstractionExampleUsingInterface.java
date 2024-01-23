package OopsConcept;

// Interface defining abstraction
interface Printable {
    // Abstract method (to be implemented by classes that implement this interface)
    void print();
}

// Concrete class implementing the interface
class Printer implements Printable {
    @Override
    public void print() {
        // Implementation of the abstract method defined in the interface
        System.out.println("Printing...");
    }
}

// Main class demonstrating abstraction using the interface
public class AbstractionExampleUsingInterface {
    public static void main(String[] args) {
        // Creating an object of the concrete class that implements the interface
        Printer printer = new Printer();

        // Using abstraction to call the print method without knowing the specific implementation
        printer.print(); // Printing...
    }
}
