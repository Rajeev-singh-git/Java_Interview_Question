package Code.Basic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class streamMinMax {

    public static void main(String [] args){
        ArrayList<Integer> l1 = new ArrayList<Integer>();
        l1.add(0);l1.add(10);l1.add(15);l1.add(5);l1.add(25);l1.add(20);
        System.out.println(l1);        // [0, 10, 15, 5, 25, 20]
        List<Integer> l2 = l1.stream().map(i->i+10).collect(Collectors.toList());
        System.out.println(l2);         // [10, 20, 25, 15, 35, 30]
        long count = l1.stream().filter(i->i%2==0).count();
        System.out.println(count);      // 3
        List<Integer>l3 = l1.stream().sorted().collect(Collectors.toList());
        System.out.println(l3);         // [0, 5, 10, 15, 20, 25]
        Comparator<Integer> comp = (i1,i2)->i1.compareTo(i2);
        List<Integer>l4 = l1.stream().sorted(comp).collect(Collectors.toList());
        System.out.println(l4);        // [0, 5, 10, 15, 20, 25]
        Integer min = l1.stream().min(comp).get();
        System.out.println(min);       // 0
        Integer max = l1.stream().max(comp).get();
        System.out.println(max);       // 25
        l3.stream().forEach(i->System.out.print(i +" "));   // 0 5 10 15 20 25
        System.out.println();
        l3.stream().forEach(System.out::println);
        /*
         0
         5
         10
         15
         20
         25
         */
    }

}
