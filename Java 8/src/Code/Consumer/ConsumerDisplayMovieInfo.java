package Code.Consumer;

import java.util.ArrayList;
import java.util.function.Consumer;

class Movie
{
    String name;
    String hero;
    String heroine;

    public Movie(String name, String hero, String heroine) {
        this.name = name;
        this.hero = hero;
        this.heroine = heroine;
    }
}

public class ConsumerDisplayMovieInfo {

    public static void main(String[] args){

        ArrayList<Movie> movies = new ArrayList<Movie>();
        populate(movies);

        Consumer<Movie> c = m ->{
            System.out.println("Movie Name : " +m.name);
            System.out.println("Movie Hero : "+m.hero);
            System.out.println("Movie Heroine : "+m.heroine);
            System.out.println();
        };

        for(Movie m :movies){
            c.accept(m);
        }
    }

    public static void populate(ArrayList<Movie> movies){
        movies.add(new Movie("BahuBali","Prabhas","Anushka"));
        movies.add(new Movie("Rayees","Sharukh","Sunny"));
        movies.add(new Movie("Dangal","Ameer","Ritu"));
        movies.add(new Movie("Sultan","Salman","Anushka"));
    }


}
