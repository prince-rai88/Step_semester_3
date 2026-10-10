package uml_diagrams.class_problems.p3_hotel_booking;

public class SuiteCategory implements RoomCategory {
    @Override
    public String getCategoryName() {
        return "Suite";
    }

    @Override
    public double getPricePerNight() {
        return 300.0;
    }
}
