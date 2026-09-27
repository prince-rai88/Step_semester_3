package main.java.classes_objects.assignment_problems.F2_ExtendingEmployees;

public class Employee {
    private String empName;
    private int empId;
    private double salary;

    public Employee(String empName, int empId, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empName, int empId, double salary, double teamBonus) {
        super(empName, empId, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empName, int empId, double salary, double stipendCap) {
        super(empName, empId, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

class Main {
    public static void main(String[] args) {

        Employee employee = new Employee("Ravi", 101, 40000);

        ManagerEmployee manager =
                new ManagerEmployee("Karan", 102, 70000, 8000);

        InternEmployee intern =
                new InternEmployee("Meera", 103, 12000, 10000);

        Employee[] employees = {employee, manager, intern};

        for (Employee emp : employees) {

            if (emp instanceof ManagerEmployee) {
                System.out.println("Manager effective pay: Rs "
                        + ((ManagerEmployee) emp).effectiveSalary());

            } else if (emp instanceof InternEmployee) {
                System.out.println("Intern effective pay: Rs "
                        + ((InternEmployee) emp).effectiveSalary());

            } else {
                System.out.println("Plain employee pay: Rs "
                        + emp.getSalary());
            }
        }
    }
}