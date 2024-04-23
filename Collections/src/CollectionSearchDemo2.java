import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CollectionSearchDemo2 {

    public static void main(String [] args){

        ArrayList <Integer> al = new ArrayList<>();
        al.add(15);
        al.add(0);
        al.add(20);
        al.add(10);
        al.add(5);
        System.out.println(al);   //
        Collections.sort(al, new MyComp());
        System.out.println(al);   //

        System.out.println(Collections.binarySearch(al, 10, new MyComp())); //
        System.out.println(Collections.binarySearch(al,13, new MyComp())); //

    }

}

class MyComp implements Comparator<Integer>{


    @Override
    public int compare(Integer o1, Integer o2) {
        return o2.compareTo(o1);
    }
}