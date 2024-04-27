package Code.Function;

import java.util.function.Function;

public class FunctionChainingDemo2 {

    public static void main(String [] args){

        Function<Integer,Integer> f1 = i->i+i;
        Function<Integer,Integer> f2 = i->i*i*i;

        System.out.println("The result of f1.andThen(f2) : "+f1.andThen(f2).apply(2));  //64
        System.out.println("The result of f1.compose(f2) : "+f1.compose(f2).apply(2));  //16

    }
}
