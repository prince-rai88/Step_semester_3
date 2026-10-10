package uml_diagrams.assignment_problems.p4_elective_enrollment;

public class HonorsPolicy implements StudentPolicy {
    @Override
    public String getTypeName() {
        return "Honors";
    }

    @Override
    public int getCreditLimit() {
        return 28;
    }
}
