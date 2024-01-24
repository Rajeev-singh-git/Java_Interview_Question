package OopsConcept;

class Calculator {
    int add(int a, int b) {
        System.out.println("Adding two integers");
        return a + b;
    }

    double add(double a, double b) {
        System.out.println("Adding two doubles");
        return a + b;
    }

    String add(String a, String b) {
        System.out.println("Concatenating two strings");
        return a + b;
    }
}

public class OverloadingExample {
    public static void main(String[] args) {
        Calculator calculator1 = new Calculator();
        Calculator calculator2 = new Calculator();

        // Call based on reference type (Calculator)
        int resultInt = calculator1.add(5, 10);
        System.out.println("Result (int): " + resultInt);            /* Adding two integers
                                                                        Result (int): 15  */

        double resultDouble = calculator2.add(3.5, 7.2);
        System.out.println("Result (double): " + resultDouble);        /* Adding two doubles
                                                                        Result (double): 10.7 */

    }
}