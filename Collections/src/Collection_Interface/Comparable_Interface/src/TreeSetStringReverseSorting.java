import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetStringReverseSorting {

    public static void main(String[] args){
        TreeSet<String> alphabet = new TreeSet<>(new MyComp());
        alphabet.add("A");
        alphabet.add("B");
        alphabet.add("C");
        alphabet.add("D");

        System.out.println(alphabet);            // [D, C, B, A]
    }
}

class MyComp implements Comparator<Object>{

    @Override
    public int compare(Object obj1, Object obj2){
        String s1 = obj1.toString();
        String s2 = (String) obj2;

        return -s1.compareTo(s2);
    }
}
