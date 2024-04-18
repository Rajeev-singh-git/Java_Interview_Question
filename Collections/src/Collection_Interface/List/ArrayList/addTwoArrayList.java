package ArrayList;

import java.util.ArrayList;

public class addTwoArrayList {

    public static void main(String[]args){
        ArrayList<String> arrayList1 = new ArrayList<>();

        arrayList1.add("1");
        arrayList1.add("2");
        arrayList1.add("3");


        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("A");
        arrayList2.add("B");
        arrayList2.add("C");

        arrayList1.addAll(arrayList2);

        System.out.println(arrayList1);   // [1, 2, 3, A, B, C]
    }
}
