import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {

    private static CopyOnWriteArrayList<Integer> numberList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // Adding initial elements to the list
        numberList.add(1);
        numberList.add(2);
        numberList.add(3);

        // Create a child Thread to modify the list
        Thread childThread = new Thread(()->{
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            // Add a new element to the list
            numberList.add(4);
            System.out.println("Child Thread added element to list");
        });

        //start the child Thread
        childThread.start();

        // Main thread iterating over the list
        System.out.println("Main Thread iterating over the list:");

        for(Integer number : numberList){
            System.out.println("Current element: " +number);
        }

        // Introduce a delay during iteration to simulate processing time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Join the child thread to ensure it completes execution
        childThread.join();

        // After iteration, print the final state of the list
        System.out.println("Final state of the list: " + numberList);
  }

}

/*
Main Thread iterating over the list:
Current element: 1
Current element: 2
Current element: 3
Child Thread added element to list
Final state of the list: [1, 2, 3, 4]
 */