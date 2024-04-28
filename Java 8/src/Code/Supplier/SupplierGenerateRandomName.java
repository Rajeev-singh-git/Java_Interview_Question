package Code.Supplier;

import java.util.function.Supplier;

public class SupplierGenerateRandomName {

    public static void main(String[] args){
        Supplier<String> s =()->{
          String[] sr = {"Ravi","Rahul","Sunny","Honey"};
          int x = (int)(Math.random()*4);
          return sr[x];
       };

        System.out.println(s.get());
        System.out.println(s.get());
        System.out.println(s.get());
    }

}
