class MyThread4 extends Thread {
    private Thread mt; // Store the main thread reference

    public void setMainThread(Thread mt) {
        this.mt = mt;
    }

    public void run() {
        try {
            if (mt != null) {
                mt.join(); // Wait for the main thread to finish
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Child Thread");
        }
    }
}

public class JoinDemo2 {
    public static void main(String[] args) throws InterruptedException {
        MyThread4 t = new MyThread4();
        t.setMainThread(Thread.currentThread()); // Pass the main thread reference
        t.start();

        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            System.out.println("Main Thread");
        }
    }
}
