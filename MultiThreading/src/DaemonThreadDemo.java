class MyThreadC extends Thread{

    public void run()
    {
        for(int i=0;i<10;i++){
            System.out.println("Child Thread");
        }
        try{
            Thread.sleep(2000);
    }catch(InterruptedException ignored){}
    }


}

public class DaemonThreadDemo {
    public static void main(String[] args){
        MyThreadC tc = new MyThreadC();
        tc.setDaemon(true);
        tc.start();
        System.out.println("End of main Thread");
    }
}
