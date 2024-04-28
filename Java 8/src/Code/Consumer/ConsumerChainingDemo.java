package Code.Consumer;

import java.util.function.Consumer;

class Movies
{
    String name;
    String result;


    public Movies(String name, String result) {
        this.name =  name;
        this.result = result;
    }
}

public class ConsumerChainingDemo {

    public static void main(String []args){

        Consumer<Movies> c1 = m-> System.out.println("Movie : "+m.name+ " is ready to release ");
        Consumer<Movies> c2 = m-> System.out.println("Movie : "+m.name+ " is just released and is "+m.result);
        Consumer<Movies> c3 = m-> System.out.println("Movie : "+m.name+ " is stored in the database");

        Consumer<Movies> chainedC = c1.andThen(c2).andThen(c3);

        Movies m1 = new Movies("Bahubali","Hit");
        chainedC.accept(m1);
        Movies m2 = new Movies("Spider","Flop");
        chainedC.accept(m2);

    }
}
