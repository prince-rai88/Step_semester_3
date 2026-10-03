package oops_concepts.assignment_problems.P1_RaceEntryFoundation;

/**
 * RunnerEntry – single-inheritance specialization of RaceEntry.
 * Adds a race category; shared fields (bibNumber, entryFee) stay in RaceEntry.
 */
public class RunnerEntry extends RaceEntry {

    private final String category;

    /**
     * @param bibNumber forwarded to RaceEntry's validated constructor
     * @param entryFee  forwarded to RaceEntry
     * @param category  race category (e.g. "Open 10K")
     */
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee); // validation happens in RaceEntry
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber()
                + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }
}
