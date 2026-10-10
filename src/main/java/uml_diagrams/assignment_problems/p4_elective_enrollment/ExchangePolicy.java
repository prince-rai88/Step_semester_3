package uml_diagrams.assignment_problems.p4_elective_enrollment;

public class ExchangePolicy implements StudentPolicy {
    @Override
    public String getTypeName() {
        return "Exchange";
    }

    @Override
    public int getCreditLimit() {
        return 20;
    }
}
