import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args){

        ConcurrentHashMap<Integer,String> m = new ConcurrentHashMap<>();
        m.put(101,"Ravi");
        m.put(102,"Raj");
        System.out.println(m);        //{101=Ravi, 102=Raj}
        m.putIfAbsent(101,"Siva");
        System.out.println(m);        //101=Ravi, 102=Raj}
    }
}
