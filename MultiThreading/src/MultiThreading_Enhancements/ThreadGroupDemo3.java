package MultiThreading_Enhancements;

class MyThread extends Thread{
    MyThread(ThreadGroup g, String name){
        super(g, name);
    }

    public void run() {
        System.out.println("Child Thread");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
    }
}
public class ThreadGroupDemo3 {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup pg = new ThreadGroup("Parent Group");
        ThreadGroup cg = new ThreadGroup(pg, "Child Group");
        MyThread t1 = new MyThread(pg, "Child Thread 1");
        MyThread t2 = new MyThread(pg, "Child Thread 2");
        t1.start();
        t2.start();
        System.out.println(pg.activeCount());
        System.out.println(pg.activeGroupCount());
        pg.list();
        Thread.sleep(5000);
        System.out.println(pg.activeCount());
        pg.list();
    }
}

/* Output : ->
Child Thread
Child Thread
2
1
java.lang.ThreadGroup[name=Parent Group,maxpri=10]
    Thread[#31,Child Thread 1,5,Parent Group]
    Thread[#32,Child Thread 2,5,Parent Group]
    java.lang.ThreadGroup[name=Child Group,maxpri=10]
0
java.lang.ThreadGroup[name=Parent Group,maxpri=10]
    java.lang.ThreadGroup[name=Child Group,maxpri=10]
 */