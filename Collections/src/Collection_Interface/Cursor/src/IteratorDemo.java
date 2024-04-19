import java.util.ArrayList;
import java.util.Iterator;

public class IteratorDemo {

    public static void main(String [] args){

        ArrayList<Integer> al = new ArrayList<>();

        for(int i=0;i<=10;i++){
            al.add(i);
        }

        System.out.println(al); //[0,1,2,3,4,5,6,7,8,9,10]

        Iterator<Integer> itr = al.iterator();

        while(itr.hasNext())
        {
            Integer i = itr.next();
            if(i%2==0)
                System.out.println(i);
            else
                itr.remove();

        }

        System.out.println(al); //[0, 2, 4, 6, 8, 10]

    }
}

/*
Output : ->

[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
0
2
4
6
8
10
[0, 2, 4, 6, 8, 10]

 */