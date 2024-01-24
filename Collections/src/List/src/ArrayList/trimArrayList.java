package ArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class trimArrayList {

    public static void main(String[]args){

        ArrayList<String> colorList = new ArrayList<>();


        colorList.add("Red");
        colorList.add("Blue");
        colorList.add("Black");
        colorList.add("Orange");

        System.out.println("*************** Before Trimming ******************");

        System.out.println(colorList.size());    // 4

        System.out.println("*************** After Trimming ******************");



        colorList.trimToSize();


        System.out.println(colorList.size());   // 4

    }

}
