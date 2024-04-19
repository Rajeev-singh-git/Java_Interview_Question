import java.util.LinkedList;
import java.util.ListIterator;

public class ListIteratorDemo {
    public static void main(String [] args){

        LinkedList<String> l = new LinkedList<>();
        l.add("BalaKrishna");
        l.add("Ravi");
        l.add("Shyam");
        l.add("Krishna");

        System.out.println(l);

        ListIterator<String> itr = l.listIterator();
        while (itr.hasNext()){
            String s = itr.next(); //[BalaKrishna, Ravi, Shyam, Krishna]
            if(s.equals("Ravi")){
                itr.remove();
            }
        }
        System.out.println(l); //[BalaKrishna, Shyam, Krishna]

        ListIterator<String> itr2 = l.listIterator();
        while (itr2.hasNext()){
            String s = itr2.next();
            if(s.equals("BalaKrishna")){
                itr2.set("AlmightyKrishna");
            }
        }
        System.out.println(l);  // [[AlmightyKrishna, Shyam, Krishna]

        ListIterator<String> itr3 = l.listIterator();
        while (itr3.hasNext()){
            String s = itr3.next();
            if(s.equals("Shyam")){
                itr3.add("RadhaShyam");
            }
        }
        System.out.println(l);  //[AlmightyKrishna, Shyam, RadhaShyam, Krishna]

    }

}
