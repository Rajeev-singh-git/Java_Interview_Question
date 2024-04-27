package Code.Function;

import java.util.function.Function;

public class RemoveSpaceFunction {

    public static void main(String[]args){
        String s = "Somewhere On The Earth";

        Function<String,String> f = s1 -> s1.replaceAll(" ","");
        System.out.println(f.apply(s));   //SomewhereOnTheEarth
    }
}
