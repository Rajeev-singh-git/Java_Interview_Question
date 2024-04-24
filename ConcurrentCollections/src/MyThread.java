import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MyThread extends Thread{

    static ConcurrentHashMap<Integer,String> m = new ConcurrentHashMap<>();

    public void run(){
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){}
        System.out.println("Child Thread updating Map");
        m.put(103, "C");
    }

    public static void main(String [] args) throws InterruptedException{
        m.put(101,"A");
        m.put(102,"B");

    MyThread t = new MyThread();
    t.start();

    Set<Integer> s = m.keySet();
    Iterator<Integer> itr  = s.iterator();
    while(itr.hasNext()){
        Integer I1 = itr.next();
        System.out.println("Main Thread iterating and Current Entry is: " + I1 + "..............." + m.get(I1));
        Thread.sleep(3000);

    }
     System.out.println(m);
    }
}

  /*  Output
  Main Thread iterating and Current Entry is: 101...............A
  Child Thread updating Map
  Main Thread iterating and Current Entry is: 102...............B
  Main Thread iterating and Current Entry is: 103...............C
  {101=A, 102=B, 103=C}
*/