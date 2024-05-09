
class Display
{
    public synchronized  void  wish(String name){
        for(int i=0;i<3;i++){
            System.out.print("Good morning : ");
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ignored){
            }
            System.out.println(name);
        }
    }
}

class MyThread7 extends Thread{

    Display d;
    String  name;
    MyThread7(Display d, String name){
        this.d = d;
        this.name = name;
    }

    public void run(){
        d.wish(name);
    }
}



public class SynchronizedDemo
{

    public static void main(String[] args){
        Display d1 = new Display();
        MyThread7 t1=new MyThread7(d1,"Rajeev");
        MyThread7 t2 =new MyThread7(d1,"Rajeev Singh");
        t1.start();
        t2.start();
    }
}
