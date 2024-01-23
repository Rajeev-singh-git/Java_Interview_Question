import java.util.ArrayList;
import java.util.List;

class State{
    public String state;
    public String capital;

    State(String state, String capital){
        this.state = state;
        this.capital = capital;
    }

}

class India{

    private final List<State> State;

    India(List<State> state){
        this.State = state;
    }

    public List<State> getStateList(){
        return State;
    }


}

public class Composition {

    public static void main(String[] args){

       State s1 = new State("Bihar", "Patna");
       State s2 = new State("Karnataka","Bangalore");
       State s3 = new State("Odisha","Bhubaneswar");

       List<State> states = new ArrayList<>();

       states.add(s1);
       states.add(s2);
       states.add(s3);

       India india = new India(states);

       List<State> state = india.getStateList();

       for(State st : state){
           System.out.println("State : "+ st.state + ", Capital :"+ st.capital );

       }

    }

}

/*Composition Relationship:

The India class has a "HAS-A" relationship with the State class, indicating a composition relationship.
The India class contains a list of State objects, forming a "whole-part" relationship.
Lifecycle Dependency:

The lifecycle of the State objects is tied to the lifecycle of the India object. If there is no India, the State objects would not exist in the context of this design.
Container Object:

India is the container object, managing a collection of State objects.

 */