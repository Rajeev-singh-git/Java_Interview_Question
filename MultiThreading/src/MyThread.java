

/*
Defining a Thread by extending "Thread Class";
 */

public class MyThread extends  Thread
{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("Child Thread");
        }
    }

}

class ThreadDemo{
    public static void main(String[] args)
    {
        MyThread t = new MyThread();
        t.start(); // a new thread will be created which is responsible for the execution of run() method.

        for(int i=0;i<5;i++){
            System.out.println("Main Thread");
        }
    }
}

/*
 t.start(); ->  a new thread will be created which is responsible for the execution of run() method.
 t.run(); ->  no new thread will be created, run() method will be executed just like a normal method by main Thread.
 */

/*
Output is unpredictable

Main Thread
Main Thread
Main Thread
Main Thread
Child Thread
Child Thread
Child Thread
Child Thread
Child Thread
Child Thread
Child Thread
Main Thread
Child Thread
Child Thread
Child Thread

 */