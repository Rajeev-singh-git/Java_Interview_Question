class MyThread6 extends Thread{
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("I am lazy thread");
        }
        System.out.println("I'm entered into sleeping stage");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("I got interrupted");
        }
    }
}



public class InterruptNonSleepingThread {

    public static void main(String [] args){
        MyThread6 t = new MyThread6();
        t.start();
        t.interrupt(); // Interrupt the child thread
        System.out.println("End of main thread");
    }
}
