package Code.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class streamSorting {

    public static void main(String [] args){
        ArrayList<String> al = new ArrayList<>();
        al.add("rvk");
        al.add("rkv");
        al.add("abc");
        al.add("say");
        System.out.println(al);             //[rvk, rkv, abc, say]
        List<String> l2 = al.stream().map(s->s.toUpperCase()).collect(Collectors.toList());
        System.out.println(l2);             // [RVK, RKV, ABC, SAY]

        //sorting
        List<String> l3 = l2.stream().sorted().collect(Collectors.toList());
        System.out.println(l3);             // [ABC, RKV, RVK, SAY]

        // customized sorting
        List<String> l4 = l3.stream().sorted((s1,s2)->-s1.compareTo(s2)).collect(Collectors.toList());
        System.out.println(l4);             // [SAY, RVK, RKV, ABC]

    }
}
