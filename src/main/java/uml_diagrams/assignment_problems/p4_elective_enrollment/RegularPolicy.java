package uml_diagrams.assignment_problems.p4_elective_enrollment;

public class RegularPolicy implements StudentPolicy {
    @Override
    public String getTypeName() {
        return "Regular";
    }

    @Override
    public int getCreditLimit() {
        return 24;
    }
}
