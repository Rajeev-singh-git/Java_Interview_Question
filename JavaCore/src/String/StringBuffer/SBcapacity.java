package String.StringBuffer;

public class SBcapacity {

    public static void main(String []args){

        StringBuffer sb = new StringBuffer();
        System.out.println(sb.capacity());    // 16

        sb.append("ABCDEFGHIJKlMNO");
        System.out.println(sb.capacity());   // 16

        sb.append("PQ");
        System.out.println(sb.capacity());   // 34
    }


}
