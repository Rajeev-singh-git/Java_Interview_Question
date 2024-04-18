package ArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.*;

// Write a Java program to iterate through all elements in an array list.
// Retrieve an element (at a specified index) from a given array list.
public class IterateArrayList {

    public static void main(String[]args){

        ArrayList<String> colorList = new ArrayList<>();

        colorList.add("Red");
        colorList.add("Blue");
        colorList.add("Black");

       System.out.println("***********Iterating ArrayList**********");

       for(String color : colorList){
           System.out.println(color);
       }



       colorList.add(0,"Pink");

        System.out.println("*******************Iterating ArrayList after adding**************************");

        for(String color : colorList){
            System.out.println(color);
        }

        System.out.println("*******************Retrieve an element at index 2**************************");
        // retrieve an element (at a specified index) from a given array list.
        System.out.println(colorList.get(2));           // Blue



        System.out.println("******************* update an array element by the given element **************************");

        colorList.set(2,"Brown");
        System.out.println(colorList);                  // [Pink, Red, Brown, Black]



        System.out.println("******************* remove the third element from an array list **************************");
        colorList.remove(3);
        System.out.println(colorList);                  // [Pink, Red, Brown]



        System.out.println("*******************  Search the value Red **************************");
        if(colorList.contains("Red")){
            System.out.println("Found");                        //   Found
        }else{
            System.out.println("Not Found");
        }

        System.out.println("*******************  Sort array list  **************************");
        Collections.sort(colorList);
        System.out.println(colorList);                     //   [Brown, Pink, Red]

        System.out.println("*******************  Copy one array list into another  **************************");



    }


}
