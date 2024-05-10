package MultiThreading_Enhancements;

public class ThreadGroupDemo {

    public static void main(String [] args){
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());

        ThreadGroup gp = new ThreadGroup("Parent Group");
        System.out.println(gp.getParent().getName());

        ThreadGroup cg = new ThreadGroup(gp,"Child Group");
        System.out.println(cg.getParent().getName());

    }

    /* Output : ->
    main
    system
    main
    Parent Group
     */


}
