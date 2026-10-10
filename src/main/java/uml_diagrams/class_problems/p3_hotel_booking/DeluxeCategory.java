package uml_diagrams.class_problems.p3_hotel_booking;

public class DeluxeCategory implements RoomCategory {
    @Override
    public String getCategoryName() {
        return "Deluxe";
    }

    @Override
    public double getPricePerNight() {
        return 200.0;
    }
}
