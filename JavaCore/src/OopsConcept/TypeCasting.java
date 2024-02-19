package OopsConcept;

public class TypeCasting {
}

class Parent1{

    public void methodOne(){
        System.out.println("Parent Class : A");
    }
}

class Child1 extends Parent1{
    public void methodOne(){
        System.out.println("Child Class : B");
    }

    public void methodTwo(){
        System.out.println("Child Class : C");
    }

    public static void main(String[] args){
        Child1 c1 = new Child1();
        c1.methodOne();  //Child Class : B
        c1.methodTwo();  //Child Class : C
        ((Parent1)c1).methodOne(); //Child Class : B
    }
}

/*
Child Class : B
Child Class : C
Child Class : B
*/