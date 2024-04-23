import java.util.*;


public class CollectionDemo {

    public static void main(String [] args){

        ArrayList<String> al = new ArrayList<>();
        al.add("Z");
        al.add("B");
        al.add("A");

        System.out.println("Before Sorting : "+al); // [Z, B, A]

        Collections.sort(al);

        System.out.println("After Sorting : "+al);  //[A, B, Z]
    }


}
