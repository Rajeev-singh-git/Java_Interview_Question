package MultiThreading_Enhancements;

import java.util.concurrent.locks.ReentrantLock;

class Display {
    // Create a ReentrantLock with fair lock acquisition policy
    ReentrantLock l = new ReentrantLock(true);

    public void wish(String name) {
        l.lock(); // Acquire the lock
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Good Morning");
                try {
                    Thread.sleep(2000); // Sleep for 2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name);
            }
        } finally {
            l.unlock(); // Release the lock in the finally block to ensure it is always released
        }
    }
}

class MyThread9 extends Thread {
    Display d;
    String name;

    MyThread9(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        d.wish(name);
    }
}

public class ReentrantLockDemo3 {
    public static void main(String[] args) {
        Display d = new Display();
        MyThread9 t1 = new MyThread9(d, "Dhoni");
        MyThread9 t2 = new MyThread9(d, "Yuva Raj");
        MyThread9 t3 = new MyThread9(d, "Virat Kohli");

        // Start the threads
        t1.start();
        t2.start();
        t3.start();
    }
}
