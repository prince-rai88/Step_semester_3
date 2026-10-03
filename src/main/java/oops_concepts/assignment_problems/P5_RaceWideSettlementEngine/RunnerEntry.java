package oops_concepts.assignment_problems.P5_RaceWideSettlementEngine;

/** RunnerEntry – generation 2 in the P5 multilevel chain. */
public class RunnerEntry extends RaceEntry {

    private final String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() { return category; }

    @Override
    public String announce() {
        return "Runner Entry [" + entryCode + "] | Bib: " + getBibNumber()
                + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }
}
