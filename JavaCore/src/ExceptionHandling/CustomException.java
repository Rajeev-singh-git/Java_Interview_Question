package ExceptionHandling;

public class CustomException extends Exception{

    public CustomException(String message) {
        super(message);
    }
}

class Test
{

   public static void main(String[] args)
   {
       try {
           // Manually throw an exception
           throw new CustomException("This is a custom exception");
       } catch (CustomException e) {
           // Handle the custom exception
           System.out.println("Custom exception caught: " + e.getMessage());
       }
   }
}

/* Output :->
Custom exception caught: This is a custom exception
 */