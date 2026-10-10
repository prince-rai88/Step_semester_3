package uml_diagrams.class_problems.p2_vehicle_rental;

public class StandardCar extends Vehicle {
    private static final double DAILY_RATE = 50.0;

    public StandardCar(String id, String name) {
        super(id, name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * DAILY_RATE;
    }
}
