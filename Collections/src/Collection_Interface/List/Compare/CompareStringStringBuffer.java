package Compare;

import java.util.Comparator;
import java.util.TreeSet;

/* Insert String and StringBuffer Object into the TreeSet where sorting order is increasing length order.
If 2 obj have same length then consider the alphabetical order.
*/

/* For default natural Sorting Order Objects need to be homogenous and  Comparable (String and all wrapper class).
If we are defining our own sorting order by Comparator than "Object need not be homogenous and comparable".

 */

public class CompareStringStringBuffer {

    public static void main (String[]args) {
        TreeSet<Object> treeSet = new TreeSet<>(new CustomComparator());

        treeSet.add("A");
        treeSet.add("ABC");
        treeSet.add(new StringBuffer("BACK"));
        treeSet.add(new StringBuffer("SEE"));
        treeSet.add("ABCD");

        System.out.println(treeSet);
    }
}

class CustomComparator implements Comparator<Object>{


    @Override
    public int compare(Object o1, Object o2) {
        String s1 = o1.toString();
        String s2 = o2.toString();
        int i1 = s1.length();
        int i2 = s2.length();

        if(i1<i2)
            return -1;
        else if(i1>i2)
            return +1;
        else
          return    s1.compareTo(s2);
    }
}
