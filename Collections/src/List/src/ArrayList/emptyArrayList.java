package ArrayList;

import java.util.ArrayList;
public class emptyArrayList {


        public static void main(String[] args) {
            ArrayList<String> c1= new ArrayList<String>();
            c1.add("Red");
            c1.add("Green");
            c1.add("Black");
            c1.add("White");
            c1.add("Pink");
            System.out.println("Original array list: " + c1);                // Original array list: [Red, Green, Black, White, Pink]
            System.out.println(c1.isEmpty());
            c1.removeAll(c1);                                                // false
            System.out.println("Array list after remove all elements "+c1);  // Array list after remove all elements []
            System.out.println(c1.isEmpty());                                // true
        }

}
