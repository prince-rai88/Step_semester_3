package oops_concepts.assignment_problems.P4_RaceDayAnnouncerBoard;

/** RunnerEntry for Problem 4 — adds category and a richer announce(). */
public class RunnerEntry extends RaceEntry {

    private final String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() { return category; }

    /**
     * Late-registration penalty used in the smoke test — delegates to
     * base class to keep logic in one place (no override required in P4,
     * but base class support is needed so main() can adjust balance).
     */
    protected void applyLateFee(double amount) {
        entryFee += amount;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber()
                + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }
}
