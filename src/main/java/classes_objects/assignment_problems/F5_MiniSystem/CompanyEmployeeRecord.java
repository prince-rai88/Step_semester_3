package main.java.classes_objects.assignment_problems.F5_MiniSystem;

class Employee {
    private String empName;
    private int empId;
    private double salary;

    Employee(String empName, int empId, double salary) {
        this.empName = empName;
        this.empId = empId;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(String empName, int empId, double salary, double teamBonus) {
        super(empName, empId, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(String empName, int empId, double salary, double stipendCap) {
        super(empName, empId, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity)
                return slot;
        }
        return null;
    }

    static ParkingSlot safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
            return slot;
        }

        return null;
    }
}

public class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    void fullProfile() {

        double pay = employee.getSalary();

        if (employee instanceof ManagerEmployee)
            pay = ((ManagerEmployee) employee).effectiveSalary();

        else if (employee instanceof InternEmployee)
            pay = ((InternEmployee) employee).effectiveSalary();

        if (slot == null)
            System.out.println(name + " | Pay: Rs " + pay
                    + " | Slot: no parking assigned");
        else
            System.out.println(name + " | Pay: Rs " + pay
                    + " | Slot: " + slot.slotNo);
    }

    public static void main(String[] args) {

        ParkingSlot[] slots = {
                new ParkingSlot("A1", 1, 0),
                new ParkingSlot("A2", 1, 0)
        };

        Employee e1 =
                new ManagerEmployee("Divya", 101, 70000, 8000);

        Employee e2 =
                new Employee("Karan", 102, 40000);

        Employee e3 =
                new InternEmployee("Meera", 103, 12000, 10000);

        CompanyEmployeeRecord r1 =
                new CompanyEmployeeRecord("Divya", "101", e1, null);

        CompanyEmployeeRecord r2 =
                new CompanyEmployeeRecord("Karan", "102", e2, null);

        CompanyEmployeeRecord r3 =
                new CompanyEmployeeRecord("Meera", "103", e3, null);

        r1.slot = ParkingSlot.safeAllot(slots, "TN01AB1234");
        r2.slot = ParkingSlot.safeAllot(slots, "TN02CD5678");

        r1.fullProfile();
        r2.fullProfile();
        r3.fullProfile();

        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}