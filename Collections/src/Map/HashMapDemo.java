package Map;

import java.util.*;

public class HashMapDemo {

    public static void main(String[] args){
        HashMap<String, Integer> m = new HashMap<>();

        m.put("Ram",1);
        m.put("Shyam",2);
        m.put("Vishnu",3);
        m.put("Ganesh",4);

        System.out.println(m);                 // {Vishnu=3, Shyam=2, Ganesh=4, Ram=1}

        System.out.println(m.put("Ganesh",44));      // 4

        Set<String> s = m.keySet();
        System.out.println(s);                // [Vishnu, Shyam, Ganesh, Ram]

        Collection<Integer> c = m.values();
        System.out.println(c);               // [3, 2, 44, 1]

        Set<Map.Entry<String, Integer>> s1 = m.entrySet();
        System.out.println(s1);              // [Vishnu=3, Shyam=2, Ganesh=44, Ram=1]


        Iterator<Map.Entry<String, Integer>> itr = s1.iterator();

        while(itr.hasNext())
        {
            Map.Entry m1 = itr.next();
            System.out.println(m1);

            if(m1.getKey().equals("Ganesh"))
                m1.setValue(4);

        }

        System.out.println(m);      //{Vishnu=3, Shyam=2, Ganesh=4, Ram=1}


    }
}
