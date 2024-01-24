package ArrayList;

import java.util.ArrayList;
import java.util.*;

public class swapElementInArrayList {
    public static void main(String[]args){

        ArrayList<String> colorList = new ArrayList<>();

        colorList.add("Red");
        colorList.add("Blue");
        colorList.add("Black");
        colorList.add("Orange");

        System.out.println("*************** Before Swap ******************");

        System.out.println(colorList);    // [Red, Blue, Black, Orange]

        System.out.println("*************** After Swap ******************");

        // Indices to swap
        int index1 = 1;
        int index2 = 3;

        // Swap elements using Collections.swap()
        Collections.swap(colorList, index1, index2);
        System.out.println(colorList);                        // [Red, Orange, Black, Blue]

    }

}
