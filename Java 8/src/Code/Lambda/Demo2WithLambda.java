package Code.Lambda;

interface inter2{

    public void sum(int a,int b);

}
public class Demo2WithLambda {

    public static void main(String[] args){
      inter2 i = (a,b)->{System.out.println("Sum is "+(a+b));};
      i.sum(18,18);  // Sum is 36
    }
}
