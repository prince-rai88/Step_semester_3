package uml_diagrams.class_problems.p2_vehicle_rental;

public class LuxuryCar extends Vehicle {
    private static final double DAILY_RATE = 100.0;

    public LuxuryCar(String id, String name) {
        super(id, name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * DAILY_RATE;
    }
}
