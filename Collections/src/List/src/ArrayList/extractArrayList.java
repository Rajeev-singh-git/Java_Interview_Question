package ArrayList;

import java.util.ArrayList;
import java.util.List;

public class extractArrayList {

    public static void main(String[]args){

        ArrayList<String> colorList = new ArrayList<>();

        colorList.add("Red");
        colorList.add("Blue");
        colorList.add("Black");
        colorList.add("Brown");
        colorList.add("Silver");
        colorList.add("Orange");

        List<String>  sub_list = colorList.subList(0,3);

        System.out.println("******************** Sub Array List **********************");
        System.out.println(sub_list);   //[Red, Blue, Black]



    }

}
