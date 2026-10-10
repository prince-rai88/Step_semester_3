package abstraction_and_interface.class_problems.p3_bonus_calculator;

public class BonusSystem {
    public static String getAuditIfApplicable(StaffMember s) {
        if (s instanceof Auditable) {
            return ((Auditable) s).auditRecord();
        }
        return "No audit required";
    }
}
