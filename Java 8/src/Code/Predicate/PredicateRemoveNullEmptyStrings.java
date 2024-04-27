package Code.Predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PredicateRemoveNullEmptyStrings {

    public static void main(String [] args){
        String [] names = {"Durga","",null,"Ganesh","Shiva",null,""};
        Predicate<String> p = s->s!=null && s.length()!=0;
        ArrayList<String> al = new ArrayList<>();

        for(String s: names){
            if(p.test(s)){
                al.add(s);
            }
       }

       System.out.println(al);  // [Durga, Ganesh, Shiva]

    }
}
