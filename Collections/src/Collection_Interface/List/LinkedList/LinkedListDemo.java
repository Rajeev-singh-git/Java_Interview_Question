package LinkedList;

import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<Object> l = new LinkedList<>();

        // Adding elements to LinkedList
        l.add("ashok");
        l.add(30);
        l.add(null);
        l.add("ashok");
        System.out.println(l); // [ashok, 30, null, ashok]

        // Modifying elements in LinkedList
        l.set(0, "software");
        System.out.println(l); // [software, 30, null, ashok]

        l.set(0, "venky");
        System.out.println(l); // [venky, 30, null, ashok]

        // Removing elements from LinkedList
        l.removeLast();
        System.out.println(l); // [venky, 30, null]

        // Adding element at the beginning of LinkedList
        l.addFirst("vvv");
        System.out.println(l); // [vvv, venky, 30, null]
    }
}