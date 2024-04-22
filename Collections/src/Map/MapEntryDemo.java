package Map;

import java.util.HashMap;
import java.util.Map;


public class MapEntryDemo {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

}

/*  Output :->
Key: one, Value: 1
Key: two, Value: 2
 */
