package Vector;

import java.util.*;

public class StackDemo {
    public static void main(String[] args) {

        Stack<String> s = new Stack<>();

        // Push elements onto the stack
        s.push("A");
        s.push("B");
        s.push("C");
        System.out.println(s); // Output: [A, B, C]

        // Pop the top element from the stack
        System.out.println(s.pop()); // Output: C
        System.out.println(s); // Output: [A, B]

        // Peek at the top element of the stack
        System.out.println(s.peek()); // Output: B

        // Search for elements in the stack
        System.out.println(s.search("A")); // Output: 2 (position of "A" in the stack)
        System.out.println(s.search("Z")); // Output: -1 (element not found)

        // Check if the stack is empty
        System.out.println(s.empty()); // Output: false (stack is not empty)
    }


}
