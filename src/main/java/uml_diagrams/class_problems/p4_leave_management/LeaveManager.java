package uml_diagrams.class_problems.p4_leave_management;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeaveManager {
    private final List<LeaveRequest> requests = new ArrayList<>();
    private int counter = 1;

    public LeaveRequest submitLeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate) {
        LeaveRequest request = new LeaveRequest("LR" + (counter++), employee, startDate, endDate);
        requests.add(request);
        System.out.println("Leave request submitted by " + employee.getName() + " for " + startDate + " to " + endDate + ". Status: " + request.getStatus().getDisplayName() + ".");
        return request;
    }

    public void approveRequest(LeaveRequest request) {
        request.approve();
        System.out.println("Leave request for " + request.getEmployee().getName() + " approved. Status: " + request.getStatus().getDisplayName() + ".");
    }

    public void rejectRequest(LeaveRequest request) {
        request.reject();
        System.out.println("Leave request for " + request.getEmployee().getName() + " rejected. Status: " + request.getStatus().getDisplayName() + ".");
    }

    public void revertRequestToPending(LeaveRequest request) {
        request.revertToPending();
    }

    public static void main(String[] args) {
        LeaveManager manager = new LeaveManager();

        Employee john = new FullTimeEmployee("E1", "John Doe");
        Employee jane = new PartTimeEmployee("E2", "Jane Smith");

        // 1. Employee 'John Doe' (Full-time) submits leave request for 2024-10-10 to 2024-10-12 (Pending).
        LeaveRequest johnRequest = manager.submitLeaveRequest(john, LocalDate.of(2024, 10, 10), LocalDate.of(2024, 10, 12));

        // 2. Manager reviews John Doe's request and approves it.
        manager.approveRequest(johnRequest);

        // 3. Employee 'Jane Smith' (Part-time) submits leave request for 2024-11-01 to 2024-11-05 (Pending).
        manager.submitLeaveRequest(jane, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5));

        // 4. Manager attempts to change John Doe's approved request back to pending.
        manager.revertRequestToPending(johnRequest);
    }
}
