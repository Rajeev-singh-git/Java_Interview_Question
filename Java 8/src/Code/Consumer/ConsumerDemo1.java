package Code.Consumer;

import java.util.function.Consumer;

public class ConsumerDemo1 {

    public static void main(String[] args){
        Consumer<String> c = s -> System.out.println(s);
        c.accept("Hello");                                  // Hello
        c.accept("DurgaSoft");                              // DurgaSoft
    }
}
