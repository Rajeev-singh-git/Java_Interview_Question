//
public class ComparableInterfaceEx1 {

    public static void main(String[] args){

        System.out.println("A".compareTo("Z"));     // -25
        System.out.println("Z".compareTo("A"));     // 15
        System.out.println("Z".compareTo("Z"));     // 0
        System.out.println("Z".compareTo("z"));     // -32

    }
}

