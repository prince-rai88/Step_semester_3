package classes_objects.class_problems.F1_AttendanceSystem;

import java.util.*;
public class SRMStudent {
    String name,regNo;
    double attendance;
    SRMStudent(String name,String regNo,double attendance){
        this.name=name;
        this.regNo=regNo;
        this.attendance=attendance;
    }
    void addAttendanceUpdate(double newAttendance){
        this.attendance=newAttendance;
    }
    boolean isEligible(){
        return attendance >= 75;
    }
    static double classAverage(SRMStudent[] students){
        double sum=0;

        for(SRMStudent student:students){
            sum+=student.attendance;
        }
        return sum/students.length;
    }
    public static void main(String[] args) {
        SRMStudent[] students = {
                new SRMStudent("Ravi", "101", 82),
                new SRMStudent("Anitha", "102", 68),
                new SRMStudent("Karthik", "103", 91),
                new SRMStudent("Meera", "104", 74),
                new SRMStudent("Suresh", "105", 60)
        };

        for (SRMStudent student : students)
            System.out.println(student.name + " - " + student.attendance + "% - " +
                    (student.isEligible() ? "Eligible" : "Detained"));

        System.out.println("Class average: " + SRMStudent.classAverage(students) + "%");
    }
}
