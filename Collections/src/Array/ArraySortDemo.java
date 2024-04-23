package Array;

import java.util.Arrays;
import java.util.Comparator;

public class ArraySortDemo {

    public static void main(String[]args){

        int [] a = {10,5,20,11,6};

        System.out.println("Primitive Array before Sorting");

        for(int a1:a){
            System.out.println(a1);
        }

        Arrays.sort(a);

        System.out.println("Primitive Array after Sorting");

        for(int a1:a){
            System.out.println(a1);
        }

        String [] s = {"A", "Z", "B"};

        System.out.println("Object Array before Sorting");

        for(String s1:s){
            System.out.println(s1);
        }

        Arrays.sort(s);

        System.out.println("Object Array after Sorting");

        for(String s1:s){
            System.out.println(s1);
        }

        Arrays.sort(s, new MyCom());

        System.out.println("Object Array after Sorting by Comparator");

        for(String s1:s){
            System.out.println(s1);
        }

    }

}

class MyCom implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}

/* Output :->
Primitive Array before Sorting
10
5
20
11
6
Primitive Array after Sorting
5
6
10
11
20
Object Array before Sorting
A
Z
B
Object Array after Sorting
A
B
Z
Object Array after Sorting by Comparator
Z
B
A

*/