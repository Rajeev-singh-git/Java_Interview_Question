package String;

public class StringConstantPoolConcept {

    public static void main(String[] args){

        String s1 = "Rajeev";
        String s2 = "Rajeev";
        String s3 = new String("Rajeev");
        String s4 = new String("Rajeev");

        System.out.println(s1==s2);             //true
        System.out.println(s3==s4);             //false

    }
}
