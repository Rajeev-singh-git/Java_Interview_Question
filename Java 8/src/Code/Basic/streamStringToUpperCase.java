package Code.Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class streamStringToUpperCase {

    public static void main(String [] args){
        ArrayList<String> al = new ArrayList<>();
        al.add("rvk");al.add("rkv");al.add("abc");al.add("say");
        System.out.println(al);             // [rvk, rkv, abc, say]
        List<String> l2 = al.stream().map(s->s.toUpperCase()).collect(Collectors.toList());
        System.out.println(l2);             // [RVK, RKV, ABC, SAY]

    }
}
