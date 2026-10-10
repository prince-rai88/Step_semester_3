package uml_diagrams.class_problems.p3_hotel_booking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    public enum Status {
        CONFIRMED,
        CANCELLED
    }

    private final String reservationId;
    private final Room room;
    private final Customer customer;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final double totalPrice;
    private final LocalDate cancellationDeadline;
    private Status status;

    public Reservation(String reservationId, Room room, Customer customer, LocalDate checkIn, LocalDate checkOut) {
        this.reservationId = reservationId;
        this.room = room;
        this.customer = customer;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        this.totalPrice = nights * room.getCategory().getPricePerNight();
        // Deadline is 1 day before check-in
        this.cancellationDeadline = checkIn.minusDays(1);
        this.status = Status.CONFIRMED;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public boolean overlapsWith(LocalDate start, LocalDate end) {
        if (status == Status.CANCELLED) {
            return false;
        }
        return start.isBefore(checkOut) && end.isAfter(checkIn);
    }

    public boolean cancel(LocalDate requestDate) {
        if (status == Status.CANCELLED) {
            return false;
        }
        if (requestDate.isAfter(cancellationDeadline)) {
            return false;
        }
        status = Status.CANCELLED;
        return true;
    }
}
