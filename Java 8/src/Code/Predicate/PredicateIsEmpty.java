package Code.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

//Check whether the given collection is empty or not
public class PredicateIsEmpty {

    public static  void main(String[]args){
        Predicate<Collection<?>> checkEmpty = Collection::isEmpty;
        List<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        System.out.println(checkEmpty.test(list));  //false
        System.out.println(checkEmpty.test(list1));  //true

    }
}
