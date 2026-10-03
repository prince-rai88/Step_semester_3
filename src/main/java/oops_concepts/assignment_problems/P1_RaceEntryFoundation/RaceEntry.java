package oops_concepts.assignment_problems.P1_RaceEntryFoundation;

/**
 * Problem 1 – Race Entry Foundation & Batch Bib Validator
 *
 * RaceEntry is the validated base for all marathon entries.
 * Its constructor enforces a minimum-length rule on bibNumber.
 * registerBatch() attempts to build one RaceEntry per array entry,
 * using try/catch to count rejections — never pre-validating itself.
 */
public class RaceEntry {

    // ── Fields ────────────────────────────────────────────────────────────────
    private final String bibNumber;
    protected double entryFee;
    private double amountPaid;

    // ── Constructor ───────────────────────────────────────────────────────────

    /**
     * @param bibNumber must be non-null, non-blank, and ≥ 4 characters
     * @param entryFee  positive entry fee
     * @throws IllegalArgumentException if bibNumber fails validation
     */
    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException(
                    "Invalid bibNumber: must not be blank and must be at least 4 characters long."
            );
        }
        this.bibNumber  = bibNumber.trim();
        this.entryFee   = entryFee;
        this.amountPaid = 0.0;
    }

    // ── Accessors ─────────────────────────────────────────────────────────────

    public String getBibNumber() {
        return bibNumber;
    }

    // ── Business methods ──────────────────────────────────────────────────────

    /** Credits a payment against the outstanding balance. */
    public void pay(double amount) {
        amountPaid += amount;
    }

    /** Returns the amount still owed, floored at 0. */
    public double getBalanceDue() {
        return Math.max(0.0, entryFee - amountPaid);
    }

    /** Human-readable one-line summary. Subclasses override for richer output. */
    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    /**
     * Attempts to register every bib in the array at the given entryFee.
     * Invalid bibs (those that trigger an IllegalArgumentException during
     * construction) are silently counted as rejections.
     *
     * @param bibNumbers array of candidate bib numbers (length ≤ 500)
     * @param entryFee   fee applied to each attempted registration
     * @return summary string, e.g. "Registered: 2 | Rejected: 1"
     */
    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected   = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee); // validation lives only here
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Assignment P1: Race Entry Foundation ===\n");

        // Test 1 – constructor rejects short bib
        System.out.println("-- Test 1: Short bib (B1) --");
        try {
            new RaceEntry("B1", 50);
            System.out.println("Construction succeeded (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // Test 2 – RunnerEntry pay & balance
        System.out.println("\n-- Test 2: RunnerEntry pay & balance --");
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        System.out.println("Balance due: " + r.getBalanceDue()); // expected 50.0

        // Test 3 – registerBatch
        System.out.println("\n-- Test 3: registerBatch --");
        String[] bibs = {"BIB1", "B1", "BIB2"};
        System.out.println(registerBatch(bibs, 80)); // expected "Registered: 2 | Rejected: 1"
    }
}
