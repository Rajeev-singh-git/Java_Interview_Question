package Compare;

import java.util.Comparator;
import java.util.TreeSet;

public class Employee implements Comparable<Object>{

    String name;
    int eid;

    Employee(String name, int eid){
        this.name = name;
        this.eid = eid;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", eid=" + eid +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        int eid1 = this.eid;
        int eid2 = ((Employee)o).eid;

        if(eid1<eid2)
            return -1;
        else if (eid1>eid2)
         return +1;
        else
            return 0;
    }
}

class Comp{

    public static void main(String[] args){

        Employee e1 = new Employee("Rajveer",100);
        Employee e2 = new Employee("Sher Singh",200);
        Employee e3 = new Employee("Abhishek",300);
        Employee e4 = new Employee("Venki",400);
        Employee e5 = new Employee("Brajesh",500);

        TreeSet<Employee> employeeList = new TreeSet<>();

        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
        employeeList.add(e5);

        System.out.println(employeeList);


        TreeSet<Employee> employeeList2 = new TreeSet<>(new MyCom());

        employeeList2.add(e1);
        employeeList2.add(e2);
        employeeList2.add(e3);
        employeeList2.add(e4);
        employeeList2.add(e5);

        System.out.println(employeeList2);
    }

}

class MyCom implements Comparator<Object>{


    @Override
    public int compare(Object o1, Object o2) {
        String s1 = ((Employee)o1).name;
        Employee e2 = (Employee)o2;
        String s2 = e2.name;

        return s1.compareTo(s2);


    }
}