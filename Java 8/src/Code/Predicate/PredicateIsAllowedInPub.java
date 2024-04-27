package Code.Predicate;

import java.util.function.Predicate;

class SoftwareEngineer
{
    String  name;
    int age;

    boolean isHavingGf;

    public SoftwareEngineer(String name, int age, boolean isHavingGf) {
        this.name = name;
        this.age = age;
        this.isHavingGf = isHavingGf;
    }


    @Override
    public String toString() {
        return name;
    }


}
public class PredicateIsAllowedInPub {

    public static void main(String [] args){

        Predicate<SoftwareEngineer> checkIfAllowedInPub = se -> se.age>=18 && se.isHavingGf;

        SoftwareEngineer [] list = {new SoftwareEngineer("Ravi",36,false),
                new SoftwareEngineer("Rahul",21,true),
                new SoftwareEngineer("Rohan",16,true),
                new SoftwareEngineer("Rajveer",28,true)
       };

        System.out.println("The allowed members in Pub are");

        for (SoftwareEngineer se:list){
            if(checkIfAllowedInPub.test(se))
                System.out.println(se);
        }
    }
}


/*  Output :->
The allowed members in Pub are
Rahul
Rajveer
 */