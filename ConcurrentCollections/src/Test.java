import java.util.ArrayList;
import java.util.Iterator;

public class Test {

    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();
        al.add("A");
        al.add("B");
        al.add("C");
        Iterator itr = al.iterator();
        while (itr.hasNext()) {
            String s = (String) itr.next();
            System.out.println(s);
     //       al.add("D");   ConcurrentModificationException
        }

    }
}
