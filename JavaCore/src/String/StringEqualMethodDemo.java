package String;

public class StringEqualMethodDemo {

    public static void main(String[] args){

        String s1 = new String("Rajeev");
        String s2 = new String("Rajeev");

        System.out.println(s1==s2);             //false
        System.out.println(s1.equals(s2));      //true

    }
}
