package abstraction_and_interface.assignment_problems.p3_fleet_maintenance;

public abstract class ServiceableVehicle {
    private double mileage;

    public abstract String performMaintenance();

    public double getMileage() {
        return mileage;
    }

    public void addMileage(double km) {
        if (km >= 0) {
            mileage += km;
        } else {
            System.out.println("rejected");
        }
    }
}
