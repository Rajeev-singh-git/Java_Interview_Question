package Code.Basic;

import java.util.function.Predicate;

//Check the length of given string is greater than 3 or not
public class PredicateEx2 {

    public static  void main(String[]args){
        Predicate<String> checkLength = (str) -> str.length()>3;
        System.out.println(checkLength.test("Animal"));  // true
        System.out.println(checkLength.test("Ox"));      // false
        System.out.println(checkLength.test("Horse"));   // true
    }
}
