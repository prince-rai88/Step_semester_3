package uml_diagrams.class_problems.p2_vehicle_rental;

public class SUV extends Vehicle {
    private static final double DAILY_RATE = 80.0;

    public SUV(String id, String name) {
        super(id, name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * DAILY_RATE;
    }
}
