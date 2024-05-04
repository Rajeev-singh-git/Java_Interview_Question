package ExceptionHandling;

public class CustomCheckedException extends Exception{

    public CustomCheckedException(String message) {
        super(message);
    }
}

class Test
{

   public static void main(String[] args)
   {
       try {
           // Manually throw an exception
           throw new CustomCheckedException("This is a custom exception");
       } catch (CustomCheckedException e) {
           // Handle the custom exception
           System.out.println("Custom exception caught: " + e.getMessage());
       }
   }
}

/* Output :->
Custom exception caught: This is a custom exception
 */