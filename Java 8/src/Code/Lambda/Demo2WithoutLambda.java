package Code.Lambda;

interface intera{
 public void sum(int a, int b);
}

class Demo implements intera{

    @Override
    public void sum(int a, int b) {
        System.out.println("Sum is "+(a+b));  // Sum is 30
    }
}

public class Demo2WithoutLambda {

    public static void main(String[]args){
        intera i = new Demo();
        i.sum(20,10);
    }

}
