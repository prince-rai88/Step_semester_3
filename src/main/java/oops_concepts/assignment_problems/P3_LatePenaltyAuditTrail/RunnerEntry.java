package oops_concepts.assignment_problems.P3_LatePenaltyAuditTrail;

/**
 * RunnerEntry – overrides applyLateFee() to double the penalty before
 * delegating to RaceEntry, which handles both the deduction and recording.
 *
 * @Override guarantees the compiler catches any signature mismatch.
 * No separate recording step is written here — super.applyLateFee() does it all.
 */
public class RunnerEntry extends RaceEntry {

    private final String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() { return category; }

    /**
     * Doubles the incoming penalty before passing it to RaceEntry's
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
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber()
                + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }
}
