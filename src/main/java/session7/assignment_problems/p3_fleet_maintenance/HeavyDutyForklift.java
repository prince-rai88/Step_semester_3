package session7.assignment_problems.p3_fleet_maintenance;

public class HeavyDutyForklift extends Forklift {
    public HeavyDutyForklift(String assetTag) {
        super(assetTag);
    }

    @Override
    public String performMaintenance() {
        return super.performMaintenance() + " | high-pressure hydraulic check complete";
    }
}
