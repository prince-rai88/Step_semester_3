package oops_concepts.class_problems.P4_NightlyTicketAnnouncer;

/**
 * WorkshopTicket for Problem 4 – adds a track field and a richer printTicket().
 * batchPrint() calls printTicket() polymorphically, then performs a safe downcast
 * to reach getTrack() when it needs the extra detail.
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
        return "Workshop | Track: " + track + " | Balance: " + getBalanceDue();
    }
}
