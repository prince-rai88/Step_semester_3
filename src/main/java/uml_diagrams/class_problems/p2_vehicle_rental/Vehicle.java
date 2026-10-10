package uml_diagrams.class_problems.p2_vehicle_rental;

public abstract class Vehicle {
    private final String id;
    private final String name;
    private boolean available;

    public Vehicle(String id, String name) {
        this.id = id;
        this.name = name;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}
