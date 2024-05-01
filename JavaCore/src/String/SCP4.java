package String;

public class SCP4 {

    public static void main(String[] args){

        String s1 = new String("spring");
        String s2 = s1.toUpperCase();
        String s3 = s1.toLowerCase();


        System.out.println(s1==s2);  //false
        System.out.println(s1==s3);  //true

    }
}
