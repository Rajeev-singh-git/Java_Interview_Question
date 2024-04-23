package Map;

import java.util.Hashtable;
import java.util.Map;

public class HashTableDemo {

    public static void main(String [] args){

        Hashtable<Temporary, String> h = new Hashtable<>();

        h.put(new Temporary(5), "A");
        h.put(new Temporary(2), "B");
        h.put(new Temporary(6), "C");
        h.put(new Temporary(15), "D");
        h.put(new Temporary(23), "E");
        h.put(new Temporary(16), "F");

        System.out.println(h); //{16=F, 2=B, 5=A, 6=C, 23=E, 15=D}

    }
}

class Temporary{
    int i;

    Temporary(int i){
        this.i =i;
    }

    @Override
    public int hashCode() {
        return i;
    }

    @Override
    public String toString() {
        return String.valueOf(i);
    }
}

