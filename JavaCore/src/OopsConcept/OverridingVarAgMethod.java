package OopsConcept;

public class OverridingVarAgMethod {

    public static void main(String[] args){
        Parents p = new Parents();
        p.methodOne(10);             // Parent Class
        children c = new children();
        c.methodOne(10);              // Child class
        Parents pc = new children();
        pc.methodOne(10);            // Parent Class


        System.out.println("******** When Keeping Var-arg method in both parent and child class **********");


        Parents p1 = new Parents();
        p1.methodTwo(10);             // Parent Class
        children c1 = new children();
        c1.methodTwo(10);             // Child class
        Parents pc1 = new children();
        pc1.methodTwo(10);            // Child  Class


    }


}

class Parents{

    public void methodOne(int... i ){
        System.out.println("Parent Class");
    }
    public void methodTwo(int... i ){
        System.out.println("Parent Class");
    }
}

class children extends Parents{

    public void methodOne(int i ){
        System.out.println("Child class");
    }

    public void methodTwo(int... i ){
        System.out.println("Child  Class");
    }
}

/*
Parent Class
Child class
Parent Class
******** When Keeping Var-arg method in both parent and child class **********
Parent Class
Child  Class
Child  Class
 */