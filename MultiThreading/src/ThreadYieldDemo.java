class MyThread3 extends Thread
{
    public void run(){
        for (int i=0;i<5;i++){
            Thread.yield();
            System.out.println("Child Thread");
        }
    }

}

public class ThreadYieldDemo {

    public static void main(String[] args){
        MyThread3 t = new MyThread3();
        t.start();
        for (int i=0;i<5;i++){
            System.out.println("Main Thread");
        }
    }
}
