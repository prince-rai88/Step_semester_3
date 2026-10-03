package oops_concepts.class_problems.P5_FestWideTicketEngine;

/**
 * Problem 5 – Fest-Wide Ticket Issuance, Promo Codes & Nightly Settlement Engine
 *
 * Features:
 *  • Auto-incrementing static counter → final ticketId (format "TCK-XXXX")
 *  • isValidPromoCode() — char-by-char check, NO regex
 *  • Overloaded pay(): flat amount, and amount + payment mode
 *  • processNightlySettlement() — instanceof-based GroupTicket separation,
 *    null-safe batch processing
 */
public class EventTicket {

    // ── Static counter ────────────────────────────────────────────────────────
    private static int ticketCounter = 1000; // next ticket will be TCK-1001

    // ── Fields ────────────────────────────────────────────────────────────────
    private final int    ticketNumber; // raw integer kept for easy access
    public  final String ticketId;     // formatted "TCK-XXXX"
    protected double basePrice;
    private double   amountPaid;

    // ── Constructor ───────────────────────────────────────────────────────────

    /**
     * Assigns the next available ticketId from the shared counter.
     * The counter increments once per object — never settable from outside.
     *
     * @param basePrice positive ticket price
     */
    public EventTicket(double basePrice) {
        ticketCounter++;                              // increment before assigning
        this.ticketNumber = ticketCounter;
        this.ticketId     = "TCK-" + ticketCounter;  // stored in final field
        this.basePrice    = basePrice;
        this.amountPaid   = 0.0;
    }

    // ── Business methods ──────────────────────────────────────────────────────

    /**
     * Flat-amount payment — the core payment logic lives here.
     *
     * @param amount amount to credit against the balance
     */
    public void pay(double amount) {
        amountPaid += amount;
    }

    /**
     * Payment with a recorded mode — reuses pay(double) internally
     * rather than duplicating the balance logic.
     *
     * @param amount amount to credit
     * @param mode   payment mode label (e.g. "UPI", "Card")
     */
    public void pay(double amount, String mode) {
        System.out.println("Payment mode: " + mode);
        pay(amount); // delegate — no duplicated logic
    }

    public double getBalanceDue() {
        return Math.max(0.0, basePrice - amountPaid);
    }

    public String printTicket() {
        return "Event Ticket [" + ticketId + "] | Balance Due: " + getBalanceDue();
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    /**
     * Returns the total number of EventTicket objects created so far
     * (across all subclasses, since they all call super()).
     */
    public static int getTicketsIssued() {
        return ticketCounter - 1000; // issued = counter - initial offset
    }

    /**
     * Validates a promo code against the exact format: "F" + 3 digits + 1 uppercase letter
     * Example of valid code: "F123A"
     *
     * Implemented using charAt() and Character utility methods — NO regex.
     *
     * @param code the candidate promo code string
     * @return true if the format matches exactly, false otherwise
     */
    public static boolean isValidPromoCode(String code) {
        // Fast-fail on wrong length (avoids any out-of-bounds charAt calls)
        if (code == null || code.length() != 5) {
            return false;
        }

        // Position 0 must be the letter 'F'
        if (code.charAt(0) != 'F') {
            return false;
        }

        // Positions 1–3 must all be digits
        if (!Character.isDigit(code.charAt(1))) return false;
        if (!Character.isDigit(code.charAt(2))) return false;
        if (!Character.isDigit(code.charAt(3))) return false;

        // Position 4 must be an uppercase letter
        if (!Character.isUpperCase(code.charAt(4))) return false;

        return true;
    }

    /**
     * Processes a night's batch of tickets:
     *  • Skips null entries silently (counts them as "null skipped").
     *  • Separates GroupTicket from plain EventTicket using instanceof.
     *
     * @param tickets batch of up to 5,000 entries (may contain nulls)
     * @return summary string, e.g. "2 processed | 1 null skipped | 1 group | 1 individual"
     */
    public static String processNightlySettlement(EventTicket[] tickets) {
        int processed   = 0;
        int nullSkipped = 0;
        int groupCount  = 0;
        int individual  = 0;

        for (EventTicket t : tickets) {
            if (t == null) {          // null-safe guard — never throws
                nullSkipped++;
                continue;
            }

            processed++;

            if (t instanceof GroupTicket) {
                groupCount++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + groupCount  + " group | "
                + individual  + " individual";
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Problem 5: Fest-Wide Ticket Engine ===\n");

        // Test 1 – auto-incrementing ticketId
        System.out.println("-- Test 1: ticketId & getTicketsIssued --");
        EventTicket t1 = new EventTicket(500);
        System.out.println("ticketId : " + t1.ticketId);           // TCK-1001
        System.out.println("Issued   : " + EventTicket.getTicketsIssued()); // 1

        EventTicket t2 = new EventTicket(300);
        System.out.println("ticketId : " + t2.ticketId);           // TCK-1002
        System.out.println("Issued   : " + EventTicket.getTicketsIssued()); // 2

        // Test 2 – isValidPromoCode
        System.out.println("\n-- Test 2: isValidPromoCode --");
        System.out.println(isValidPromoCode("F123A")); // true
        System.out.println(isValidPromoCode("F12A"));  // false — too short
        System.out.println(isValidPromoCode("X123A")); // false — wrong leading char
        System.out.println(isValidPromoCode("F123a")); // false — lowercase final char
        System.out.println(isValidPromoCode("F12BA")); // false — 'B' is not a digit

        // Test 3 – overloaded pay
        System.out.println("\n-- Test 3: overloaded pay --");
        t1.pay(200);
        t1.pay(200, "UPI");
        System.out.println("Balance due: " + t1.getBalanceDue()); // 100.0

        // Test 4 – processNightlySettlement
        System.out.println("\n-- Test 4: processNightlySettlement --");
        EventTicket[] batch = {
                new GroupTicket(2000, 5),
                null,
                new EventTicket(500)
        };
        System.out.println(processNightlySettlement(batch));
        // expected: "2 processed | 1 null skipped | 1 group | 1 individual"
    }
}
