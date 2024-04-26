package Code.Functional;

interface interr3{
    public int square(int x);
}

public class Demo3WithLambda {

    public static void main(String[] args){
        interr3 i = x-> x*x;
        System.out.println("Square of 5 : "+i.square(5)); //25
    }
}
