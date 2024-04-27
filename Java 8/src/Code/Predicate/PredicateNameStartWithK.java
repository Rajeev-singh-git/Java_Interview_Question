package Code.Predicate;

import java.util.function.Predicate;

public class PredicateNameStartWithK {

    public static void main(String[] args){

        String [] names = {"Ravi","Kajal","Kundan","Abhi","Komal"};

        Predicate<String> startsWithK = s->s.charAt(0)=='K';

        System.out.println("The name starts with are");

        for(String s: names){
            if(startsWithK.test(s)){
                System.out.print(s+", ");  // Kajal, Kundan, Komal,
            }

        }
    }

}
