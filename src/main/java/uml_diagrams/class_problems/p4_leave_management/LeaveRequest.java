package uml_diagrams.class_problems.p4_leave_management;

import java.time.LocalDate;

public class LeaveRequest {
    private final String requestId;
    private final Employee employee;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private LeaveStatus status;

    public LeaveRequest(String requestId, Employee employee, LocalDate startDate, LocalDate endDate) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public String getRequestId() {
        return requestId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void approve() {
        this.status = LeaveStatus.APPROVED;
    }

    public void reject() {
        this.status = LeaveStatus.REJECTED;
    }

    public void revertToPending() {
        if (this.status == LeaveStatus.APPROVED) {
            System.out.println("Cannot change status: Approved request cannot revert to Pending.");
            return;
        }
        if (this.status == LeaveStatus.REJECTED) {
            System.out.println("Cannot change status: Rejected request cannot revert to Pending.");
            return;
        }
        this.status = LeaveStatus.PENDING;
    }
}
