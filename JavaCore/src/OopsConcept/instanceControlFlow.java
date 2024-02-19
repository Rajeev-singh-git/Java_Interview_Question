package OopsConcept;

public class instanceControlFlow {

    int i = 10;

    {
        methodOne();
        System.out.println("First Static Block");
    }

    instanceControlFlow() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {
        instanceControlFlow ic =new instanceControlFlow();
        System.out.println("Main method");

    }

    private void methodOne() {
        System.out.println(j);
    }
    {
        System.out.println("Second instance block");
    }
    int j=20;

}

/*
0
First Static Block
Second instance block
Parent Class Constructor
Main method
 */