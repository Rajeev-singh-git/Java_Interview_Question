package Code.Function;

import java.util.ArrayList;
import java.util.function.Function;

class Student
{
    String name;
    int marks;

    Student(String name, int marks){
        this.name=name;
        this.marks=marks;
    }
}

public class calculateGradeFunction {

    public static void main(String[] args)
    {
        ArrayList<Student> students = new ArrayList<>();
        populate(students);

        Function<Student,String> f = s->{
            int marks = s.marks;

            if(marks>=80){
                return "A {Distinction}";
            } else if (marks>=60) {
                return "B {First Class}";
            }else if(marks>=50){
                return "C {Second Class}";
            }else if(marks>=35) {
                return "D {Third Class}";
            }else{
                return "Fail";
            }
        };

        for(Student s:students){
            System.out.println("Student Name : "+s.name);
            System.out.println("Student Marks : "+s.marks);
            System.out.println("Student Marks : "+f.apply(s));
            System.out.println();
        }

    }

    public static void populate(ArrayList<Student> students){
        students.add(new Student("Rahul",100));
        students.add(new Student("Rohan",65));
        students.add(new Student("Ravi",45));
        students.add(new Student("Suraj",25));
    }
}
