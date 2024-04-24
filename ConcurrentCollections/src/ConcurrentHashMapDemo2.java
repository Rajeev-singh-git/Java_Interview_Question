import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo2 {

    public static void main(String[] args){

        ConcurrentHashMap<Integer,String> m = new ConcurrentHashMap<>();
        m.put(101,"Ravi");
        m.remove(101,"Raj");
        System.out.println(m);        //{101=Ravi, 102=Raj}
        m.remove(101,"Ravi");
        System.out.println(m);        //{}
    }
}
