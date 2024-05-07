class MyThread5 extends Thread{
    public void run(){
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am lazy Thread :" + i);
                Thread.sleep(2000);
            }
            }catch(InterruptedException e){
                System.out.println("I got interrupted");
            }
        }
}





public class InterruptSleepingThread {
    public static void main(String [] args){
        MyThread5 t = new MyThread5();
        t.start();
        t.interrupt(); // Interrupt the child thread
        System.out.println("End of main thread");
    }
}

