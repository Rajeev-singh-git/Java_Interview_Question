public class JoinDemo {

    public static void main(String [] args){
        Thread t1 = new Thread(()->{
           System.out.println("Thread t1 started");
           try{
               Thread.sleep(2000);
           }catch (InterruptedException e){
               e.printStackTrace();
           }
            System.out.println("Thread t1 completed");
        });

        Thread t2 = new Thread(()->{
            System.out.println("Thread t2 started");
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Thread t2 completed");
        });

        t1.start();
        t2.start();

        try{
            t2.join();   // Main thread (or any other thread) will wait until t2 completes
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Main Thread Completes");

    }

}
