package Map;

import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void main(String[] args) {
        WeakHashMap<Temp, String> m = new WeakHashMap<>(); // Use generics for clarity
        Temp t = new Temp();
        m.put(t, "ashok");
        System.out.println(m); // {Temp=ashok}

        t = null; // Remove the strong reference to Temp

        System.out.println(m); // May or may not be empty depending on GC
    }
}


class Temp
{
    public String toString()
    {
        return "Temp";
    }
}