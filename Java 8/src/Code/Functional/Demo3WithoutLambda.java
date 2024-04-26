package Code.Functional;

interface inter3{
    public int square(int x);
}

class Demo3 implements inter3{

    @Override
    public int square(int x) {
        return x*x;
    }
}
public class Demo3WithoutLambda {
    public static void main(String[] args){
        inter3 i = new Demo3();
        System.out.println("Square  of 6 is : "+i.square(6)); //36
    }

}
