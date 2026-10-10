package uml_diagrams.class_problems.p4_leave_management;

public class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String id, String name) {
        super(id, name);
    }

    @Override
    public String getEmployeeType() {
        return "Full-time";
    }
}
