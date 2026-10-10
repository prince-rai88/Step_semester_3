package uml_diagrams.assignment_problems.p4_elective_enrollment;

public class ElectiveEnrollmentDemo {
    public static void main(String[] args) {
        // 1. Elective 'Cloud Computing' is created with 4 credits and a capacity of 2.
        Elective cloudComputing = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", new RegularPolicy(), 20);
        Student ravi = new Student("Ravi", new HonorsPolicy(), 22);
        Student neha = new Student("Neha", new ExchangePolicy(), 12);
        Student kiran = new Student("Kiran", new RegularPolicy(), 22);

        // 2. Asha (Regular, currently 20 credits) enrolls.
        cloudComputing.enroll(asha);

        // 3. Ravi (Honors, currently 22 credits) enrolls.
        cloudComputing.enroll(ravi);

        // 4. Neha (Exchange, currently 12 credits) enrolls.
        cloudComputing.enroll(neha);

        // 5. Kiran (Regular, currently 22 credits) enrolls.
        cloudComputing.enroll(kiran);

        // 6. Asha drops 'Cloud Computing'.
        cloudComputing.drop(asha);
    }
}
