package uml_diagrams.assignment_problems.p4_elective_enrollment;

public class Student {
    private final String name;
    private final StudentPolicy policy;
    private int currentCredits;

    public Student(String name, StudentPolicy policy, int currentCredits) {
        this.name = name;
        this.policy = policy;
        this.currentCredits = currentCredits;
    }

    public String getName() {
        return name;
    }

    public StudentPolicy getPolicy() {
        return policy;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getCreditLimit() {
        return policy.getCreditLimit();
    }

    public boolean canTakeCredits(int credits) {
        return (currentCredits + credits) <= getCreditLimit();
    }

    public void addCredits(int credits) {
        this.currentCredits += credits;
    }

    public void removeCredits(int credits) {
        this.currentCredits -= credits;
    }

    public String getCreditsRatio() {
        return currentCredits + "/" + getCreditLimit();
    }
}
