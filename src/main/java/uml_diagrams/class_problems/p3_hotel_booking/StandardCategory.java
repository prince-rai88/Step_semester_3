package uml_diagrams.class_problems.p3_hotel_booking;

public class StandardCategory implements RoomCategory {
    @Override
    public String getCategoryName() {
        return "Standard";
    }

    @Override
    public double getPricePerNight() {
        return 150.0;
    }
}
