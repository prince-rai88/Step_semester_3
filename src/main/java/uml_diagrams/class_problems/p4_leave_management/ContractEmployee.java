package uml_diagrams.class_problems.p4_leave_management;

public class ContractEmployee extends Employee {
    public ContractEmployee(String id, String name) {
        super(id, name);
    }

    @Override
    public String getEmployeeType() {
        return "Contract";
    }
}
