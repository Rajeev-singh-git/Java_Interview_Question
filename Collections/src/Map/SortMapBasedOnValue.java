package Map;

import java.util.*;

public class SortMapBasedOnValue {

    public static void main(String [] args){

        Map<Integer,String> empMap = new HashMap<>();

        empMap.put(101,"Ravi");
        empMap.put(110,"Avi");
        empMap.put(111,"Savi");
        empMap.put(183,"Sabhi");
        empMap.put(171,"Manvi");
        empMap.put(186,"Ravi");

        System.out.println("Original Map");
        empMap.forEach((k, v) -> System.out.println(k+ "\t" +v));

        System.out.println("Map Sorted based on Value");

        Set<Map.Entry<Integer,String>> entrySet = empMap.entrySet();

        List<Map.Entry<Integer, String>> list = new ArrayList<>(entrySet);

        Collections.sort(list,(o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        list.forEach(s->{
            System.out.println(s.getKey()+"\t"+s.getValue());
        });


    }
}
