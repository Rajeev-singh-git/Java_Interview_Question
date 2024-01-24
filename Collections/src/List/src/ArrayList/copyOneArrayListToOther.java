package ArrayList;

import java.util.ArrayList;
import java.util.*;

public class copyOneArrayListToOther {

    public static void main(String[]args){

        ArrayList<String> arrayList1 = new ArrayList<>();

        arrayList1.add("1");
        arrayList1.add("2");
        arrayList1.add("3");


        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("A");
        arrayList2.add("B");
        arrayList2.add("C");


        System.out.println("*************** Arraylist before copy ******************");
        System.out.println(arrayList1);     // [1, 2, 3]
        System.out.println(arrayList2);     // [A, B, C]


        System.out.println("*************** Arraylist after copy ******************");

        Collections.copy(arrayList1,arrayList2);

        System.out.println(arrayList1);      // [A, B, C]

        System.out.println("*************** Add two arraylist ******************");

        arrayList1.addAll(arrayList2);

        System.out.println(arrayList1);     // [A, B, C, A, B, C]

    }
}
