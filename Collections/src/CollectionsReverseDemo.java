import java.util.ArrayList;
import java.util.Collections;

public class CollectionsReverseDemo {

    public static void  main(String [] args){

        ArrayList <Integer> al = new ArrayList<>();

        al.add(6);
        al.add(12);
        al.add(15);
        al.add(30);
        al.add(18);
        al.add(21);

        System.out.println("Before Reverse : -> ");
        System.out.println(al);                     // [6, 12, 15, 30, 18, 21]
        Collections.reverse(al);
        System.out.println("After Reverse : -> ");
        System.out.println(al);                     // [21, 18, 30, 15, 12, 6]
    }
}
