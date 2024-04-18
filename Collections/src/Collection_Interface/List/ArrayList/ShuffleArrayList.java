package ArrayList;

import java.util.ArrayList;
import java.util.*;
public class ShuffleArrayList {

    public static void main(String[]args){

        ArrayList<String> colorList = new ArrayList<>();

        colorList.add("Red");
        colorList.add("Blue");
        colorList.add("Black");
        colorList.add("Orange");

        System.out.println("*************** Arraylist before shuffle ******************");

        System.out.println(colorList);    // [Red, Blue, Black, Orange]

        System.out.println("*************** Arraylist after shuffle ******************");

        Collections.shuffle(colorList);
        System.out.println(colorList);    // [Blue, Orange, Red, Black]

    }
}
