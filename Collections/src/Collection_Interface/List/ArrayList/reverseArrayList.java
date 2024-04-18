package ArrayList;

import java.util.ArrayList;
import java.util.*;

public class reverseArrayList {

    public  static  void  main(String[] args){
        ArrayList<Integer> al = new ArrayList<>();

        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);

        System.out.println("******************** Before  Reverse **********************");
        System.out.println(al);    // [1, 2, 3, 4]

        System.out.println("******************** After  Reverse **********************");

        Collections.reverse(al);
        System.out.println(al);   // [4, 3, 2, 1]

    }

}
