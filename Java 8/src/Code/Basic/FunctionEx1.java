package Code.Basic;

import java.util.function.Function;

// write a function to find length of the given string
public class FunctionEx1 {

    public static void main(String[]args){
        Function<String,Integer> lengthIs = str -> str.length();
        System.out.println(lengthIs.apply("Durga"));
        System.out.println(lengthIs.apply("Rockstar"));
        System.out.println(lengthIs.apply("Coders"));
    }

}
