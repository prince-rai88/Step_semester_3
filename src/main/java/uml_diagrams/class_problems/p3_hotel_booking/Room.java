package uml_diagrams.class_problems.p3_hotel_booking;

public class Room {
    private final String roomNumber;
    private final String name;
    private final RoomCategory category;

    public Room(String roomNumber, String name, RoomCategory category) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.category = category;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getName() {
        return name;
    }

    public RoomCategory getCategory() {
        return category;
    }
}
