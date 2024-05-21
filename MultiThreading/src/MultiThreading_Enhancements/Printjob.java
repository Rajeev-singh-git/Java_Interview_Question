package MultiThreading_Enhancements;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Printjob implements Runnable{

    String name;

    Printjob(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(name + "...Job Started by Thread : " +
                Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        }catch (InterruptedException ignored){

        }
        System.out.println(name+ "...Job Completed by Thread :"+
                Thread.currentThread().getName());
    }
}

class ExecutorDemo
{

    public static void main(String[] args)
    {
        Printjob [] jobs = {new Printjob("Durga"),
                            new Printjob("Ravi"),
                            new Printjob("Shiva"),
                            new Printjob("Suresh"),
                            new Printjob("Anil")};

        ExecutorService service = Executors.newFixedThreadPool(3);
        for(Printjob job : jobs)
        {
            service.submit(job);
        }
        service.shutdown();
    }

}