package oops_concepts.class_problems.P4_NightlyTicketAnnouncer;

/**
 * Problem 4 – The Nightly Ticket Announcer
 *
 * batchPrint() loops over a mixed EventTicket[] and:
 *  1. Calls printTicket() polymorphically — no instanceof-based branching for printing.
 *  2. Builds the full report with a single StringBuilder.
 *  3. Uses instanceof ONLY to safely downcast to WorkshopTicket when extra track
 *     details are needed — never attempts the cast on a non-WorkshopTicket.
 */
public class EventTicket {

    // ── Fields ────────────────────────────────────────────────────────────────
    private final String attendeeId;
    protected double basePrice;
    private double amountPaid;

    // ── Constructor ───────────────────────────────────────────────────────────

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException(
                    "Invalid attendeeId: must not be blank and must be at least 4 characters long."
            );
        }
        this.attendeeId = attendeeId.trim();
        this.basePrice  = basePrice;
        this.amountPaid = 0.0;
    }

    // ── Business methods ──────────────────────────────────────────────────────

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return Math.max(0.0, basePrice - amountPaid);
    }

    /**
     * Returns a short summary used inside batchPrint's StringBuilder.
     * Subclasses override this with their own detail line.
     */
    public String printTicket() {
        return "Standard | Balance: " + getBalanceDue();
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    /**
     * Assembles a nightly announcement report for all tickets in the array.
     *
     * Rules:
     * • printTicket() is called polymorphically — no instanceof-chain for printing.
     * • A single StringBuilder accumulates all output.
     * • instanceof is used ONLY to safely downcast and append the track detail
     *   for genuine WorkshopTicket entries.
     *
     * @param tickets mixed array (EventTicket and any subclasses); no nulls in P4
     * @return the assembled announcement string
     */
    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();

        for (EventTicket ticket : tickets) {
            sb.append(ticket.printTicket()); // polymorphic — correct type's method runs

            // Only reach for the track when we know it's safe to cast
            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket wt = (WorkshopTicket) ticket; // guarded downcast
                sb.append(" [Track via downcast: ").append(wt.getTrack()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Problem 4: Nightly Ticket Announcer ===\n");

        // Test 1 – mixed batch print
        System.out.println("-- Test 1: batchPrint (mixed tickets) --");
        EventTicket[] tickets = {
                new EventTicket("STU1", 500),
                new WorkshopTicket("STU2", 1200, "AI/ML")
        };
        System.out.println(batchPrint(tickets));
        // expected: "Standard | Balance: 500.0 | Workshop | Track: AI/ML | Balance: 1200.0 [Track via downcast: AI/ML] | "

        // Test 2 – illegal downcast (ClassCastException at runtime)
        System.out.println("\n-- Test 2: invalid downcast (ClassCastException) --");
        try {
            EventTicket plain = new EventTicket("STU3", 500);
            WorkshopTicket bad = (WorkshopTicket) plain; // compiles but fails at runtime
            System.out.println("No exception — unexpected: " + bad);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught as expected: " + e.getMessage());
        }
    }
}
