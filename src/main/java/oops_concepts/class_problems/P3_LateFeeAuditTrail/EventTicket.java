package oops_concepts.class_problems.P3_LateFeeAuditTrail;

import java.util.Arrays;

/**
 * Problem 3 – The Late-Registration Penalty Override & Audit Trail
 *
 * EventTicket owns the core late-fee logic and the private audit array.
 * Every call to applyLateFee() records the applied penalty and adds it to
 * the balance due. getLateFeeHistory() returns a DEFENSIVE COPY so no
 * outside code can tamper with the real history.
 */
public class EventTicket {

    // ── Fields ────────────────────────────────────────────────────────────────
    private final String attendeeId;
    protected double basePrice;
    private double amountPaid;

    /** Audit trail – max 10 late fees per ticket as per constraints. */
    private double[] lateFeeHistory;
    private int      lateFeeCount;

    private static final int MAX_LATE_FEES = 10;

    // ── Constructor ───────────────────────────────────────────────────────────

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException(
                    "Invalid attendeeId: must not be blank and must be at least 4 characters long."
            );
        }
        this.attendeeId     = attendeeId.trim();
        this.basePrice      = basePrice;
        this.amountPaid     = 0.0;
        this.lateFeeHistory = new double[MAX_LATE_FEES];
        this.lateFeeCount   = 0;
    }

    // ── Business methods ──────────────────────────────────────────────────────

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return Math.max(0.0, basePrice - amountPaid);
    }

    /**
     * Applies a late-fee penalty to this ticket's outstanding balance and
     * records the exact amount applied in the private audit array.
     *
     * WorkshopTicket overrides this to double the amount before delegating
     * back here via super.applyLateFee(amount * 2).
     *
     * @param amount positive penalty amount
     */
    protected void applyLateFee(double amount) {
        basePrice += amount;                            // increases balance due
        if (lateFeeCount < MAX_LATE_FEES) {
            lateFeeHistory[lateFeeCount++] = amount;   // record in audit trail
        }
    }

    /**
     * Returns a defensive copy of the late-fee audit trail so callers
     * cannot modify the ticket's internal history.
     *
     * @return copy of the fees recorded so far
     */
    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount); // defensive copy
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + getBalanceDue();
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Problem 3: Late-Registration Penalty Override & Audit Trail ===\n");

        // Test 1 – WorkshopTicket doubles the penalty
        System.out.println("-- Test 1: doubled late fee (workshop) --");
        WorkshopTicket w = new WorkshopTicket("STU1", 1200, "AI/ML");
        w.pay(1200);
        System.out.println("Balance after full pay: " + w.getBalanceDue()); // 0.0
        w.applyLateFee(100);
        System.out.println("Balance after late fee of 100: " + w.getBalanceDue()); // 200.0

        // Test 2 – defensive copy
        System.out.println("\n-- Test 2: defensive copy --");
        double[] history = w.getLateFeeHistory();
        System.out.println("History: " + Arrays.toString(history)); // [200.0]

        history[0] = 999; // tamper attempt
        System.out.println("After tamper, internal history: "
                + Arrays.toString(w.getLateFeeHistory())); // still [200.0]

        // Test 3 – base EventTicket records its own penalty without doubling
        System.out.println("\n-- Test 3: base EventTicket late fee (no doubling) --");
        EventTicket e = new EventTicket("STU2", 500);
        e.applyLateFee(50);
        System.out.println("History: " + Arrays.toString(e.getLateFeeHistory())); // [50.0]
        System.out.println("Balance: " + e.getBalanceDue()); // 550.0
    }
}
