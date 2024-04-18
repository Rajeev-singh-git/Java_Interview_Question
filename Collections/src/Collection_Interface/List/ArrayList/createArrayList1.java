package ArrayList;

import java.util.ArrayList;

//Write a Java program to create an array list, add some colors (strings) and print out the collection.
public class createArrayList1 {

    public static void main(String[]args){

        ArrayList<String> colorList = new ArrayList<>();

        colorList.add("Red");
        colorList.add("Blue");
        colorList.add("Black");

        System.out.println(colorList);    // [Red, Blue, Black]

    }

}
