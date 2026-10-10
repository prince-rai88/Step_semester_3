package uml_diagrams.class_problems.p2_vehicle_rental;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RentalService {
    private final Map<String, Rental> activeRentals = new HashMap<>();
    private int rentalCounter = 1;

    public Rental rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println("Rental failed: " + vehicle.getName() + " is currently rented.");
            return null;
        }

        vehicle.setAvailable(false);
        String rentalId = "R" + (rentalCounter++);
        Rental rental = new Rental(rentalId, vehicle, customer, days);
        activeRentals.put(vehicle.getId(), rental);

        System.out.printf(Locale.US, "%s rented for %d days. Total charge: $%.2f%n",
                vehicle.getName(), days, rental.getTotalCharge());
        return rental;
    }

    public void returnVehicle(Vehicle vehicle) {
        Rental rental = activeRentals.remove(vehicle.getId());
        if (rental != null) {
            rental.complete();
            System.out.println(vehicle.getName() + " returned. Now available.");
        } else {
            System.out.println("No active rental found for " + vehicle.getName() + ".");
        }
    }

    public static void main(String[] args) {
        RentalService service = new RentalService();

        Vehicle luxuryCarA = new LuxuryCar("V1", "Luxury Car A");
        Vehicle standardCarB = new StandardCar("V2", "Standard Car B");
        Customer customer = new Customer("C1", "Customer");

        // 1. Customer rents 'Luxury Car A' for 3 days.
        service.rentVehicle(luxuryCarA, customer, 3);

        // 2. Customer rents 'Standard Car B' for 5 days.
        service.rentVehicle(standardCarB, customer, 5);

        // 3. Customer returns 'Luxury Car A'.
        service.returnVehicle(luxuryCarA);
    }
}
