class A {
    public synchronized void foo(B b) {
        System.out.println("Thread1 starts execution of foo() method");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println("Thread1 trying to call b.last()");
        b.last();
    }

    public synchronized void last() {
        System.out.println("inside A, this is last() method");
    }
}

class B {
    public synchronized void bar(A a) {
        System.out.println("Thread2 starts execution of bar() method");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println("Thread2 trying to call a.last()");
        a.last();
    }

    public synchronized void last() {
        System.out.println("inside B, this is last() method");
    }
}

public class Deadlock extends Thread {

   A a = new A();
   B b = new B();

   public void m1()
   {
       this.start();
       a.foo(b);
   }

   public void run()
   {
       b.bar(a);
   }

   public static void main(String [] args){
       Deadlock d = new Deadlock();
       d.m1();
   }
}
