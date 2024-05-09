public class InterThreadCommunication {

    public static void main(String [] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();
        synchronized (b) {
            System.out.println("Main Thread calling wait() method"); // Step-1
            b.wait(100);
            System.out.println("Main Thread got notification call"); // Step-4
            System.out.println(b.total);
        }
    }

}
class ThreadB extends Thread{
    int total = 0;

    public void run(){
        synchronized (this){
            System.out.println("Child thread starts calculation"); // Step-2
            for (int i = 0; i <= 100; i++) {
                total = total + i;
            }
            System.out.println("Child thread giving notification call"); // Step-3
            this.notify();
        }
    }
}

/* Output :->
Main Thread calling wait() method
Child thread starts calculation
Child thread giving notification call
Main Thread got notification call
5050
 */