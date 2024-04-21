import java.util.Comparator;
import java.util.TreeSet;

public class StringBufferTreeSetExample {

    public static void main(String [] args){

        TreeSet<StringBuffer> treeSet = new TreeSet<>(new StringBufferComparator());

        // Add StringBuffer objects to the TreeSet
        treeSet.add(new StringBuffer("Apple"));
        treeSet.add(new StringBuffer("Orange"));
        treeSet.add(new StringBuffer("Banana"));
        treeSet.add(new StringBuffer("Mango"));
        treeSet.add(new StringBuffer("Pineapple"));

        // Print elements of the TreeSet (automatically sorted)
        for (StringBuffer sb : treeSet) {
            System.out.println(sb);
        }

    }
}


// Custom Comparator for StringBuffer based on alphabetical order of their string representation
class StringBufferComparator implements Comparator<StringBuffer>{


    @Override
    public int compare(StringBuffer sb1, StringBuffer sb2) {
        return sb1.toString().compareTo(sb2.toString());
    }
}