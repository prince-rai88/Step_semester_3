package main.java.classes_objects.class_problems.F4_InstanceStatic;

public class SrmStudent {

    // Broken version
    static String name;
    static String regNo;
    static int attendance;

    static void brokenDemo() {
        name = "Ravi";
        regNo = "RA231100301011";
        attendance = 82;

        SrmStudent student1 = new SrmStudent();

        name = "Meera";
        regNo = "RA231100301012";
        attendance = 75;

        SrmStudent student2 = new SrmStudent();

        System.out.println("Broken version:");
        System.out.println(student1.name);
        System.out.println(student2.name);
    }

    // Fixed version
    String studentName;
    String studentRegNo;
    int studentAttendance;

    static String university = "SRM Institute of Science and Technology";
    static int admissionCount = 0;

    SrmStudent(String studentName, int studentAttendance) {
        this.studentName = studentName;
        this.studentAttendance = studentAttendance;

        admissionCount++;
        this.studentRegNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(studentName + " | " + studentRegNo + " | " + university);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }

    public static void main(String[] args) {

        brokenDemo();

        System.out.println("\nFixed version:");

        SrmStudent student1 = new SrmStudent("Ravi", 82);
        SrmStudent student2 = new SrmStudent("Meera", 75);

        student1.printIdCard();
        student2.printIdCard();

        printTotalAdmissions();
    }
}