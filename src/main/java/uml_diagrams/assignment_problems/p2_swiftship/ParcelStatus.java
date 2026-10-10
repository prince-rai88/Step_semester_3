package uml_diagrams.assignment_problems.p2_swiftship;

public enum ParcelStatus {
    BOOKED,
    PICKED_UP,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED;

    public boolean canTransitionTo(ParcelStatus next) {
        switch (this) {
            case BOOKED:
                return next == PICKED_UP;
            case PICKED_UP:
                return next == IN_TRANSIT;
            case IN_TRANSIT:
                return next == OUT_FOR_DELIVERY;
            case OUT_FOR_DELIVERY:
                return next == DELIVERED;
            default:
                return false;
        }
    }
}
