package session7.assignment_problems.p3_fleet_maintenance;

public class FleetSystem {
    public static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable) {
            return ((Insurable) v).getInsuranceInfo();
        }
        return "No insurance record exists";
    }
}
