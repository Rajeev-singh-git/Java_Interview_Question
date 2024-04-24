import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo3 {

    public static void main(String[] args){

        ConcurrentHashMap<Integer,String> m = new ConcurrentHashMap<>();
        m.put(101,"Ravi");
        m.replace(101,"Raj","Shiva");
        System.out.println(m);        //{101=Ravi}
        m.replace(101,"Ravi","Shiva");
        System.out.println(m);        //{101=Shiva}
    }
}
