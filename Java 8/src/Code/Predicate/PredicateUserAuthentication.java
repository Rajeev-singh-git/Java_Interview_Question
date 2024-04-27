package Code.Predicate;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

class User
{
    String username;
    String pwd;

    User(String username, String pwd){
        this.username = username;
        this.pwd = pwd;
    }

}

public class PredicateUserAuthentication {

    public static void main(String[] args){

        Predicate<User> validateUser = u->u.username.equals("Raj") && u.pwd.equals("123");

        Scanner scn = new Scanner(System.in);
        System.out.println("Enter username");
        String username  = scn.next();
        System.out.println("Enter password");
        String password  = scn.next();

        User user = new User(username,password);

        if(validateUser.test(user)){
            System.out.println("User is Valid");
        }else{
            System.out.println("Invalid User");
        }
    }
}


/* Output :->
Enter username
Raj
Enter password
123
User is Valid
 */