package oops_concepts.assignment_problems.P3_LatePenaltyAuditTrail;

import java.util.Arrays;

/**
 * Problem 3 – The Late-Withdrawal Penalty Override & Audit Trail
 *
 * RaceEntry owns the core late-fee logic and a private audit array.
 * applyLateFee() records every penalty applied and adds it to the balance.
 * getLateFeeHistory() always returns a DEFENSIVE COPY — tampering with
 * the returned array never reaches the internal record.
 */
public class RaceEntry {

    private final String bibNumber;
    protected double entryFee;
    private double amountPaid;

    /** Audit trail – max 10 late fees per entry as per constraints. */
    private double[] lateFeeHistory;
    private int      lateFeeCount;

    private static final int MAX_LATE_FEES = 10;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException(
                    "Invalid bibNumber: must not be blank and must be at least 4 characters long."
            );
        }
        this.bibNumber      = bibNumber.trim();
        this.entryFee       = entryFee;
        this.amountPaid     = 0.0;
        this.lateFeeHistory = new double[MAX_LATE_FEES];
        this.lateFeeCount   = 0;
    }

    public String getBibNumber() { return bibNumber; }

    public void pay(double amount) { amountPaid += amount; }

    public double getBalanceDue() {
        return Math.max(0.0, entryFee - amountPaid);
    }

    /**
     * Applies a late-registration penalty to the outstanding balance
     * and records the exact amount applied in the private audit array.
     *
     * RunnerEntry overrides this to double the amount before delegating
     * back here via super.applyLateFee(amount * 2).
     *
     * @param amount positive penalty amount
     */
    protected void applyLateFee(double amount) {
        entryFee += amount;                            // increases balance due
        if (lateFeeCount < MAX_LATE_FEES) {
            lateFeeHistory[lateFeeCount++] = amount;   // record in audit trail
        }
    }

    /**
     * Returns a defensive copy of the audit trail so callers cannot
     * modify the entry's internal history.
     */
    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount); // defensive copy
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Assignment P3: Late-Penalty Override & Audit Trail ===\n");

        // Test 1 – RunnerEntry doubles the penalty
        System.out.println("-- Test 1: RunnerEntry doubled late fee --");
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        System.out.println("Balance after 30 paid: " + r.getBalanceDue()); // 50.0
        r.applyLateFee(20);
        System.out.println("Balance after late fee of 20: " + r.getBalanceDue()); // 90.0

        // Test 2 – defensive copy
        System.out.println("\n-- Test 2: defensive copy --");
        double[] history = r.getLateFeeHistory();
        System.out.println("History: " + Arrays.toString(history)); // [40.0]

        history[0] = 999; // tamper attempt
        System.out.println("After tamper, internal history: "
                + Arrays.toString(r.getLateFeeHistory())); // still [40.0]

        // Test 3 – base RaceEntry records penalty without doubling
        System.out.println("\n-- Test 3: base RaceEntry late fee (no doubling) --");
        RaceEntry e = new RaceEntry("BIB9001", 100);
        e.applyLateFee(25);
        System.out.println("History: " + Arrays.toString(e.getLateFeeHistory())); // [25.0]
        System.out.println("Balance: " + e.getBalanceDue()); // 125.0
    }
}
