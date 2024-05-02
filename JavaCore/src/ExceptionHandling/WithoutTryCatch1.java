package ExceptionHandling;

public class WithoutTryCatch1 {

    public static void main(String [] args){
        System.out.println("Statement 1");
        System.out.println(10/0);
        System.out.println("Statement 3");
    }

}

/*Output : ->

Statement 1
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at ExceptionHandling.WithoutTryCatch1.main(WithoutTryCatch1.java:7)

 */