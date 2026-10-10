package uml_diagrams.assignment_problems.p4_elective_enrollment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Elective {
    private final String name;
    private final int credits;
    private final int capacity;
    private final List<Student> enrolledStudents;
    private final Queue<Student> waitlist;

    public Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
        this.waitlist = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean enroll(Student student) {
        if (enrolledStudents.contains(student) || waitlist.contains(student)) {
            System.out.println("Enrollment failed: " + student.getName() + " is already enrolled or waitlisted.");
            return false;
        }

        if (!student.canTakeCredits(credits)) {
            int attemptedTotal = student.getCurrentCredits() + credits;
            System.out.println("Enrollment failed: " + student.getName() + " would exceed the " +
                    student.getPolicy().getTypeName() + " credit limit (" + attemptedTotal + "/" + student.getCreditLimit() + ").");
            return false;
        }

        if (enrolledStudents.size() >= capacity) {
            waitlist.offer(student);
            System.out.println(name + " is full. " + student.getName() + " added to waitlist (position " + waitlist.size() + ").");
            return false;
        }

        enrolledStudents.add(student);
        student.addCredits(credits);
        System.out.println(student.getName() + " enrolled in " + name + " (credits: " + student.getCreditsRatio() + ").");
        return true;
    }

    public boolean drop(Student student) {
        if (!enrolledStudents.remove(student)) {
            System.out.println("Drop failed: " + student.getName() + " is not enrolled in " + name + ".");
            return false;
        }

        student.removeCredits(credits);
        System.out.println(student.getName() + " dropped " + name + " (credits: " + student.getCreditsRatio() + ").");

        promoteFromWaitlist();
        return true;
    }

    private void promoteFromWaitlist() {
        while (!waitlist.isEmpty() && enrolledStudents.size() < capacity) {
            Student candidate = waitlist.poll();
            if (candidate.canTakeCredits(credits)) {
                enrolledStudents.add(candidate);
                candidate.addCredits(credits);
                System.out.println(candidate.getName() + " promoted from waitlist and enrolled in " + name + " (credits: " + candidate.getCreditsRatio() + ").");
                break;
            } else {
                System.out.println("Promotion skipped for " + candidate.getName() + ": credit limit exceeded.");
            }
        }
    }
}
