package oops_concepts.class_problems.P1_TicketHierarchyFoundation;

/**
 * WorkshopTicket – single-inheritance specialization of EventTicket.
 *
 * Adds a workshop track field; all shared fields (attendeeId, basePrice)
 * are owned by EventTicket and forwarded here via super(...).
 */
public class WorkshopTicket extends EventTicket {

    // ── Fields ────────────────────────────────────────────────────────────────
    private final String track;

    // ── Constructor ───────────────────────────────────────────────────────────

    /**
     * @param attendeeId  forwarded to EventTicket's validated constructor
     * @param basePrice   forwarded to EventTicket
     * @param track       workshop track name (e.g. "AI/ML")
     */
    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice); // validation happens in EventTicket
        this.track = track;
    }

    // ── Accessors ─────────────────────────────────────────────────────────────

    public String getTrack() {
        return track;
    }

    // ── Overrides ─────────────────────────────────────────────────────────────

    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue();
    }
}
