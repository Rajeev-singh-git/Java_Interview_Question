public class NameDemo {

    public static void main(String []args){

        System.out.println(Thread.currentThread().getName());   //main

        MyThread t = new MyThread();
        System.out.println(t.getName());                        // Thread-0

        Thread.currentThread().setName("Rajeev Thread");
        System.out.println(Thread.currentThread().getName());   // Rajeev Thread

    }

}
