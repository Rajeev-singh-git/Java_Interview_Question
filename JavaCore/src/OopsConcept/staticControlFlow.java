package OopsConcept;

public class staticControlFlow {

    static int i=10;
    static
    {
        methodOne();
        System.out.println("First Static Block");
    }

    public static void main(String[] args){
        methodOne();
        System.out.println("Main method");
    }

    private static void methodOne() {
        System.out.println(j);
    }

    static
    {
        System.out.println("Second Static Block");
    }
    static int j=20;

}

/*
Output :->
        0
        First Static Block
        Second Static Block
        20
        Main method
*/
