package oops_concepts.class_problems.P2_ThreeShapesOfFamilyTree;

/**
 * WorkshopTicket – generation 2 in the multilevel chain.
 * Extends EventTicket directly (single-level descendant).
 */
public class WorkshopTicket extends EventTicket {

    private final String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue();
    }
}
