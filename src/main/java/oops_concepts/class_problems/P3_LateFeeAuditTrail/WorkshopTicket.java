package oops_concepts.class_problems.P3_LateFeeAuditTrail;

/**
 * WorkshopTicket – overrides applyLateFee() to double the penalty before
 * delegating to the parent, which handles both the deduction and recording.
 *
 * The @Override annotation guarantees the compiler will catch any signature mismatch.
 * No separate recording step is needed here — super.applyLateFee() does it all.
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

    /**
     * Doubles the incoming penalty before passing it to EventTicket's
     * applyLateFee(), which records the actual (doubled) amount in the
     * private audit trail.
     *
     * @param amount original (undoubled) penalty amount
     */
    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2); // parent handles deduction + recording
    }

    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue();
    }
}
