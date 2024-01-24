package OopsConcept;

public interface Interf {

    public void methodOne();
}

class Demo implements Interf{

    @Override
    public void methodOne() {
        System.out.println("Method one executed");             // Method one executed
    }
}

class Demo2 implements Interf{

    @Override
    public void methodOne() {
        System.out.println("Method one executed from OopsConcept.Demo 2 class");    // Method one executed from OopsConcept.Demo 2 class
    }
}

class Test{

    public static void main(String[]args){
        Interf I = new Demo();
        I.methodOne();

        Interf I2 = new Demo2();
        I2.methodOne();

    }
}
