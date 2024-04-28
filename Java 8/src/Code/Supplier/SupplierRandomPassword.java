package Code.Supplier;

import java.util.function.Supplier;

public class SupplierRandomPassword {
    public static void main(String[] args) {
        Supplier<String> s = () -> {
            String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ#$@";
            Supplier<Integer> d = () -> (int) (Math.random() * 10);
            Supplier<Character> c = () -> symbols.charAt((int) (Math.random() * symbols.length()));
            StringBuilder pwdBuilder = new StringBuilder();
            for (int i = 1; i <= 8; i++) {
                if (i % 2 == 0) {
                    pwdBuilder.append(d.get());
                } else {
                    pwdBuilder.append(c.get());
                }
            }
            return pwdBuilder.toString();
        };

        // Generating and printing a random password
        System.out.println("Random Password: " + s.get());
        System.out.println("Random Password: " + s.get());
        System.out.println("Random Password: " + s.get());
    }
}
