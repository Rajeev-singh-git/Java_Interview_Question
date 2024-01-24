package Code.Basic;

import java.util.function.Predicate;

// Write a program by joining predicate into a single predicate , use method and(), or(), negate()
public class PredicateEx4 {

    public static void main(String[]args){
        int [] arr = {2,8,10,15,20,25,30,36,39};
        Predicate<Integer> isEven = num -> num%2==0;
        Predicate<Integer> isGreaterThan10 = num -> num>10;
        System.out.println("The number greater than 10 are");
        m1(isGreaterThan10,arr);
        System.out.println("Even numbers are");
        m1(isEven,arr);
        System.out.println("The number not greater than 10 are");
        m1(isGreaterThan10.negate(),arr);
        System.out.println("Odd numbers are");
        m1(isEven.negate(),arr);
        System.out.println("The number greater than 10 and Even are");
        m1(isEven.and(isGreaterThan10),arr);
        System.out.println("The number greater than 10 or Even are");
        m1(isEven.or(isGreaterThan10),arr);

    }

    public static void m1(Predicate<Integer> p, int[]arr){
        for(int x1:arr){
            if(p.test(x1)) {
                System.out.println(x1);
            }
        }
    }

}
