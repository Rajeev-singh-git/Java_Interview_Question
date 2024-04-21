import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDescendingOrder {

    public static void  main(String[]args){

        TreeSet<Integer> t = new TreeSet<>(new MyComparator());
        t.add(10);
        t.add(6);
        t.add(20);
        t.add(19);
        t.add(39);

        System.out.println(t);   // [39, 20, 19, 10, 6]

    }

}

class MyComparator implements Comparator<Object>{

    @Override
    public int compare(Object o1, Object o2) {
        Integer i1 = (Integer) o1;
        Integer i2 = (Integer) o2;

        // Implementation 1
//        if(i1<i2)
//            return +1;
//        else if(i1>i2)
//            return -1;
//        else
//            return 0;

        // Alternative implementation
  //      return -i1.compareTo(i2);     [39, 20, 19, 10, 6]
  //      return i1.compareTo(i2);      [6, 10, 19, 20, 39]
           return i2.compareTo(i1);    // [39, 20, 19, 10, 6]


    }
}

/*
- At line "1" if we are not passing Comparator object then JVM will always  calls compareTo()
  method which is meant for default natural sorting order(ascending order)
  hence in this case the output is [0, 5, 10, 15, 20].
- At line "1" if we are passing Comparator object then JVM calls compare() method of MyComparator
  class which is meant for customized sorting order(descending order) hence in this case the
  output is [20, 15, 10, 5, 0].
 */