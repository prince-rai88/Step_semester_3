package uml_diagrams.class_problems.p4_leave_management;

public class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String id, String name) {
        super(id, name);
    }

    @Override
    public String getEmployeeType() {
        return "Part-time";
    }
}
