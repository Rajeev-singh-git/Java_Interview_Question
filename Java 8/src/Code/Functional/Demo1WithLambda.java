package Code.Functional;

interface Inter{
    public void methodOne();

}
public class Demo1WithLambda {

    public static void main(String [] args){

        Inter i = ()->{System.out.println("Method One Execution"); };
        i.methodOne();  // Method One Execution
  }

}
