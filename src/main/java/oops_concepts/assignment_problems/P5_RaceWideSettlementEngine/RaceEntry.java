package oops_concepts.assignment_problems.P5_RaceWideSettlementEngine;

/**
 * Problem 5 – Race-Wide Bib Issuance, Discount Codes & Nightly Settlement Engine
 *
 * Features:
 *  • Auto-incrementing static counter → final entryCode (format "BIB-XXXX")
 *  • isValidDiscountCode() — char-by-char check ("M" + 3 digits + 1 uppercase), NO regex
 *  • Overloaded pay(): flat amount, and amount + payment mode (delegates internally)
 *  • settleNight() — instanceof-based RelayTeamEntry separation, null-safe
 */
public class RaceEntry {

    // ── Static counter ────────────────────────────────────────────────────────
    private static int bibCounter = 0; // incremented once per construction

    // ── Fields ────────────────────────────────────────────────────────────────
    public  final String entryCode;    // immutable, assigned once in constructor
    private final String bibNumber;
    protected double entryFee;
    private double   amountPaid;

    // ── Constructor ───────────────────────────────────────────────────────────

    /**
     * Assigns the next available entryCode from the shared static counter.
     * Counter increments once per construction — never settable from outside.
     * A rejected construction (IllegalArgumentException) does NOT increment.
     *
     * @param bibNumber must be non-null, non-blank, and ≥ 4 characters
     * @param entryFee  positive entry fee
     */
    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException(
                    "Invalid bibNumber: must not be blank and must be at least 4 characters long."
            );
        }
        bibCounter++;                               // increment before assigning
        this.entryCode  = "BIB-" + String.format("%04d", bibCounter);
        this.bibNumber  = bibNumber.trim();
        this.entryFee   = entryFee;
        this.amountPaid = 0.0;
    }

    // ── Business methods ──────────────────────────────────────────────────────

    /**
     * Flat-amount payment — the core payment logic lives here.
     * The overloaded version delegates to this one.
     */
    public void pay(double amount) {
        amountPaid += amount;
    }

    /**
     * Payment with a recorded mode. Delegates to pay(double) internally —
     * no duplicated balance logic.
     *
     * @param amount amount to credit
     * @param mode   payment mode label (e.g. "UPI", "Card")
     */
    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount); // delegate — single source of truth for balance update
    }

    public double getBalanceDue() {
        return Math.max(0.0, entryFee - amountPaid);
    }

    public String getBibNumber() { return bibNumber; }

    public String announce() {
        return "Race Entry [" + entryCode + "] | Bib: " + bibNumber
                + " | Balance: " + getBalanceDue();
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    /**
     * Returns the total number of RaceEntry objects successfully constructed
     * (including all subclass instances, since they all call super()).
     * Rejected constructions never increment the counter.
     */
    public static int getBibCounter() {
        return bibCounter;
    }

    /**
     * Validates a discount code against the exact format:
     *   "M" + 3 decimal digits + 1 uppercase letter   (e.g. "M123A")
     *
     * Implemented with charAt() and Character utility methods — NO regex.
     *
     * @param code the candidate discount code
     * @return true if the format matches exactly, false otherwise
     */
    public static boolean isValidDiscountCode(String code) {
        // Fast-fail on wrong length — prevents any out-of-bounds charAt calls
        if (code == null || code.length() != 5) {
            return false;
        }
        // Position 0 must be 'M'
        if (code.charAt(0) != 'M') return false;

        // Positions 1–3 must all be digits
        if (!Character.isDigit(code.charAt(1))) return false;
        if (!Character.isDigit(code.charAt(2))) return false;
        if (!Character.isDigit(code.charAt(3))) return false;

        // Position 4 must be an uppercase letter
        if (!Character.isUpperCase(code.charAt(4))) return false;

        return true;
    }

    /**
     * Processes a night's batch of entries:
     *  • Skips null entries silently (counts as "null skipped").
     *  • Separates RelayTeamEntry from plain individual entries using instanceof.
     *
     * @param entries batch of up to 5,000 entries (may contain nulls)
     * @return summary, e.g. "2 processed | 1 null skipped | 1 relay | 1 individual"
     */
    public static String settleNight(RaceEntry[] entries) {
        int processed   = 0;
        int nullSkipped = 0;
        int relayCount  = 0;
        int individual  = 0;

        for (RaceEntry e : entries) {
            if (e == null) {          // null-safe guard — never throws
                nullSkipped++;
                continue;
            }
            processed++;
            if (e instanceof RelayTeamEntry) {
                relayCount++;
            } else {
                individual++;
            }
        }

        return processed   + " processed | "
             + nullSkipped + " null skipped | "
             + relayCount  + " relay | "
             + individual  + " individual";
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Assignment P5: Race-Wide Settlement Engine ===\n");

        // Test 1 – isValidDiscountCode
        System.out.println("-- Test 1: isValidDiscountCode --");
        System.out.println(isValidDiscountCode("M123A")); // true
        System.out.println(isValidDiscountCode("M12A"));  // false — too short
        System.out.println(isValidDiscountCode("X123A")); // false — wrong leading char
        System.out.println(isValidDiscountCode("M123a")); // false — lowercase final char
        System.out.println(isValidDiscountCode("M12BA")); // false — 'B' not a digit

        // Test 2 – entryCode auto-assignment and getBibCounter
        System.out.println("\n-- Test 2: entryCode & getBibCounter --");
        RaceEntry r1 = new RaceEntry("BIB2001", 80);
        System.out.println("entryCode : " + r1.entryCode);        // BIB-0001
        System.out.println("Counter   : " + getBibCounter());     // 1

        RaceEntry r2 = new RaceEntry("BIB3001", 150);
        System.out.println("entryCode : " + r2.entryCode);        // BIB-0002
        System.out.println("Counter   : " + getBibCounter());     // 2

        // Rejected construction must NOT increment counter
        try { new RaceEntry("B1", 50); } catch (IllegalArgumentException ignored) {}
        System.out.println("Counter after rejection: " + getBibCounter()); // still 2

        // Test 3 – overloaded pay
        System.out.println("\n-- Test 3: overloaded pay --");
        r1.pay(10, "UPI");
        System.out.println("Balance due: " + r1.getBalanceDue()); // 70.0

        // Test 4 – settleNight
        System.out.println("\n-- Test 4: settleNight --");
        EliteRunnerEntry elite = new EliteRunnerEntry("BIB4001", 150, "Elite", 500);
        RelayTeamEntry   relay = new RelayTeamEntry("BIB5001", 300, 4);
        System.out.println("Counter   : " + getBibCounter());     // 4

        RaceEntry[] batch = {elite, null, relay};
        System.out.println(settleNight(batch));
        // expected: "2 processed | 1 null skipped | 1 relay | 1 individual"
    }
}
