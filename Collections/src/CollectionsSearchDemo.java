import java.util.ArrayList;
import java.util.Collections;

public class CollectionsSearchDemo {

    public static void main(String [] args){

        ArrayList <String> al = new ArrayList<>();
        al.add("Z");
        al.add("A");
        al.add("M");
        al.add("K");
        al.add("a");
        System.out.println(al);   // [Z, A, M, K, A]
        Collections.sort(al);
        System.out.println(al);   // [A, A, K, M, Z]

        System.out.println(Collections.binarySearch(al,"Z")); // 4
        System.out.println(Collections.binarySearch(al,"J")); //-2

    }

}
