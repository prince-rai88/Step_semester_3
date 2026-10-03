package oops_concepts.class_problems.P1_TicketHierarchyFoundation;

/**
 * Problem 1 – Ticket Hierarchy Foundation & Batch Registration Validator
 *
 * EventTicket is the shared base class for all CineHub fest tickets.
 * Its constructor enforces a minimum-length rule on attendeeId so that
 * every subclass automatically inherits that validation via super(...).
 *
 * registerBatch() attempts to build one EventTicket per array entry,
 * using try/catch to count rejections without pre-validating the strings.
 */
public class EventTicket {

    // ── Fields ────────────────────────────────────────────────────────────────
    private final String attendeeId;
    protected double basePrice;
    private double amountPaid;

    // ── Constructor ───────────────────────────────────────────────────────────

    /**
     * Constructs an EventTicket after validating the attendeeId.
     *
     * @param attendeeId  must be non-null, non-blank, and ≥ 4 characters
     * @param basePrice   positive ticket price
     * @throws IllegalArgumentException if attendeeId fails validation
     */
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

    // ── Accessors ─────────────────────────────────────────────────────────────

    public String getAttendeeId() {
        return attendeeId;
    }

    // ── Business methods ──────────────────────────────────────────────────────

    /**
     * Records a payment against the outstanding balance.
     * Paying more than the balance due is allowed (results in 0 balance, not negative).
     */
    public void pay(double amount) {
        amountPaid += amount;
    }

    /**
     * Returns the amount still owed: basePrice − amountPaid, floored at 0.
     */
    public double getBalanceDue() {
        return Math.max(0.0, basePrice - amountPaid);
    }

    /**
     * Produces a human-readable one-line ticket summary.
     * Subclasses override this to add their own details.
     */
    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + getBalanceDue();
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    /**
     * Attempts to register every ID in the array at the given basePrice.
     * Invalid IDs (those that trigger an IllegalArgumentException during
     * construction) are silently counted as rejections.
     *
     * @param attendeeIds array of candidate attendee IDs (length ≤ 500)
     * @param basePrice   price applied to each attempted registration
     * @return a summary string, e.g. "Registered: 3 | Rejected: 2"
     */
    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected   = 0;

        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice); // validation lives here
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Problem 1: Ticket Hierarchy Foundation ===\n");

        // Test 1 – construction rejected for short ID
        System.out.println("-- Test 1: Short ID (ST1) --");
        try {
            new EventTicket("ST1", 500);
            System.out.println("Construction succeeded (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected: " + e.getMessage());
        }

        // Test 2 – WorkshopTicket pay & balance
        System.out.println("\n-- Test 2: WorkshopTicket pay & balance --");
        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println("Balance due: " + w.getBalanceDue()); // expected 700.0

        // Test 3 – registerBatch
        System.out.println("\n-- Test 3: registerBatch --");
        String[] ids = {"STU1", "ST1", "STU2", " ", "STU3"};
        System.out.println(registerBatch(ids, 500)); // expected "Registered: 3 | Rejected: 2"
    }
}
