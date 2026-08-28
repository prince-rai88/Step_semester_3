package main.java.classes_objects.class_problems.F4_InstanceStatic;

class BrokenStudent {

    static String name;
    static String regNo;
    static int attendance;

    BrokenStudent(String name, String regNo, int attendance) {
        BrokenStudent.name = name;
        BrokenStudent.regNo = regNo;
        BrokenStudent.attendance = attendance;
    }

    static void show() {
        System.out.println(name);
    }
}


public class SrmStudent {

    String name;
    String regNo;
    int attendance;

    static String university = "SRM Institute of Science and Technology";
    static int admissionCount = 0;

    SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        admissionCount++;
        this.regNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo + " | " + university);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }


    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenStudent student1 =
                new BrokenStudent("Ravi", "RA231100301011", 82);

        BrokenStudent student2 =
                new BrokenStudent("Meera", "RA231100301012", 75);

        student1.show();
        student2.show();


        System.out.println("\nFixed version:");

        SrmStudent s1 = new SrmStudent("Ravi", 82);
        SrmStudent s2 = new SrmStudent("Meera", 75);

        s1.printIdCard();
        s2.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}