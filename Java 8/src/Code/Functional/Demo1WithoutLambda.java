package Code.Functional;

interface Interf{

    public void methodOne();

    public class Demo implements Interf{

        @Override
        public void methodOne() {
            System.out.println("Method one  execution");
        }
    }

}
public class Demo1WithoutLambda {

    public static void main(String[] args){
        Interf i = new Interf.Demo();
        i.methodOne();     //Method one  execution

    }
}
