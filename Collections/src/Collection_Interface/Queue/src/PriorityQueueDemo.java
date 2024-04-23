import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String [] args){

        PriorityQueue<Integer> numbers = new PriorityQueue<>();

        for(int i=0;i<10;i++){
            numbers.offer(i);
        }

        System.out.println(numbers); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        System.out.println(numbers.poll());  // 0
        System.out.println(numbers);  // [1, 3, 2, 7, 4, 5, 6, 9, 8]

    }


}
