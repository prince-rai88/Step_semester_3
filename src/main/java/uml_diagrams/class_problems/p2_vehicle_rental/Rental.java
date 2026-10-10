package uml_diagrams.class_problems.p2_vehicle_rental;

public class Rental {
    private final String rentalId;
    private final Vehicle vehicle;
    private final Customer customer;
    private final int days;
    private final double totalCharge;
    private boolean active;

    public Rental(String rentalId, Vehicle vehicle, Customer customer, int days) {
        this.rentalId = rentalId;
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.totalCharge = vehicle.calculateCharge(days);
        this.active = true;
    }

    public String getRentalId() {
        return rentalId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public boolean isActive() {
        return active;
    }

    public void complete() {
        this.active = false;
        this.vehicle.setAvailable(true);
    }
}
