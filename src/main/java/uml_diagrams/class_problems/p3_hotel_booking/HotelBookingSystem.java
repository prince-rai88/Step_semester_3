package uml_diagrams.class_problems.p3_hotel_booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HotelBookingSystem {
    private final List<Reservation> reservations = new ArrayList<>();
    private int counter = 1;

    public Reservation bookRoom(Room room, Customer customer, LocalDate checkIn, LocalDate checkOut) {
        // Check availability
        for (Reservation res : reservations) {
            if (res.getRoom().getRoomNumber().equals(room.getRoomNumber()) && res.overlapsWith(checkIn, checkOut)) {
                System.out.println("Booking failed: " + room.getName() + " is not available for " + checkIn + " to " + checkOut + ".");
                return null;
            }
        }

        Reservation reservation = new Reservation("RES" + (counter++), room, customer, checkIn, checkOut);
        reservations.add(reservation);
        System.out.printf(Locale.US, "%s booked from %s to %s. Total price: $%.2f (example value).%n",
                room.getName(), checkIn, checkOut, reservation.getTotalPrice());
        return reservation;
    }

    public void cancelReservation(Reservation reservation, LocalDate cancellationDate) {
        if (reservation != null && reservation.cancel(cancellationDate)) {
            System.out.println("Reservation for " + reservation.getRoom().getName() + " cancelled successfully.");
        } else {
            System.out.println("Cancellation failed: past cancellation deadline or already cancelled.");
        }
    }

    public static void main(String[] args) {
        HotelBookingSystem system = new HotelBookingSystem();

        Room deluxe101 = new Room("101", "Deluxe Room 101", new DeluxeCategory());
        Room standard205 = new Room("205", "Standard Room 205", new StandardCategory());
        Customer customer = new Customer("C1", "Customer");

        // 1. Customer requests to book 'Deluxe Room 101' from 2024-12-01 to 2024-12-05.
        Reservation res1 = system.bookRoom(deluxe101, customer, LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5));

        // 2. Customer requests to book 'Standard Room 205' from 2024-12-03 to 2024-12-07.
        system.bookRoom(standard205, customer, LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));

        // 3. Customer attempts to book 'Deluxe Room 101' from 2024-12-03 to 2024-12-07 (overlapping).
        system.bookRoom(deluxe101, customer, LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));

        // 4. Customer cancels reservation for 'Deluxe Room 101' (before cancellation point).
        system.cancelReservation(res1, LocalDate.of(2024, 11, 20));
    }
}
