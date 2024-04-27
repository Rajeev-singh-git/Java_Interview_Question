package Code.Lambda;

public class ThreadDemoWithLambda {

    public static void main(String[]args){
        Runnable r = ()->{
            for(int i=0;i<3;i++){
                System.out.println("Child Thread");
            }
        };

        Thread t = new Thread(r);
        t.start();
        for(int i=0;i<3;i++){
            System.out.println("Main Thread");
        }
    }
}
