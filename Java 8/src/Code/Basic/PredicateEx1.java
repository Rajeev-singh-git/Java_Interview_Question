package Code.Basic;

import java.util.function.Predicate;

// Programme to check whether the given integer is greater than 10 or not
public class PredicateEx1 {

    public static void main(String[]args){
        Predicate<Integer> isGreaterThan10 = num -> (num>10);
        System.out.println(isGreaterThan10.test(15));  // true
        System.out.println(isGreaterThan10.test(1));   // false
     //   System.out.println(isGreaterThan10.test(a));    //CE
    }


}
