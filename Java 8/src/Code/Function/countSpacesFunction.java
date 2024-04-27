package Code.Function;

import java.util.function.Function;

public class countSpacesFunction {

    public static void main(String[]args){
        String s = "Somewhere On The Earth";

        Function<String,Integer> f = s1 -> s1.length()-s1.replaceAll(" ","").length();
        System.out.println(f.apply(s));  //3
    }
}
