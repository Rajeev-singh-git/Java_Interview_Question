package ExceptionHandling.throwss;

import java.util.Scanner;

class TooYoungException extends RuntimeException
{
    TooYoungException(String s){
    super(s);
    }
}

class TooOldException extends RuntimeException{
    TooOldException(String s){
        super(s);
    }
}

public class CustomUnCheckedExceptionDemo {

    public static void main(String[]args){
        Scanner scn = new Scanner(System.in);
        int age = scn.nextInt();
        if(age>60){
            throw new TooYoungException("Please wait sometime _ you will get best match");
        }else if(age<18){
            throw new TooOldException("You are late");
        }else{
            System.out.println("You will get match by e-mail");
        }
    }

}
