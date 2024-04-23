import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo2 {

    public static void main(String [] args){

        PriorityQueue <String> pq = new PriorityQueue<>(new MyCom());


        pq.offer("Z");
        pq.offer("L");
        pq.offer("A");
        pq.offer("B");

        System.out.println(pq); // [Z, L, A, B]

    }
}

class MyCom implements Comparator<String>
{

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}