package oops_concepts.assignment_problems.P2_ThreeShapesOfRaceFamily;

/** RunnerEntry – generation 2 (RaceEntry → RunnerEntry). */
public class RunnerEntry extends RaceEntry {

    private final String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() { return category; }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber()
                + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }
}
