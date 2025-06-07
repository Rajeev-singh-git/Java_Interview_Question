package OopsConcept;


interface Printable {
    void print();
}


class Printer implements Printable {

    @Override
    public void print() {
        System.out.println("Printing...");
    }
}


public class AbstractionExampleUsingInterface {
    public static void main(String[] args) {

        Printer printer = new Printer();
        printer.print(); // Printing...
    }
}
