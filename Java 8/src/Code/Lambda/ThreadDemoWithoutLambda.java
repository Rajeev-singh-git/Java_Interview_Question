package Code.Lambda;

class MyRunnable implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<3;i++)
            System.out.println("Child Thread");
    }
}

public class ThreadDemoWithoutLambda {

    public static void main(String[] args) {
        Runnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();

        for(int i=0; i<3;i++){
            System.out.println("Main Thread");
        }

    }

}
