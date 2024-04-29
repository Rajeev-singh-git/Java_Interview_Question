package String;

public class StringBufferEqualMethodDemo {

    public static void main(String[]args){

        StringBuffer sb1 = new StringBuffer("Rajeev");
        StringBuffer sb2 = new StringBuffer("Rajeev");

        System.out.println(sb1==sb2);             //false
        System.out.println(sb1.equals(sb2));      //false
    }

}
