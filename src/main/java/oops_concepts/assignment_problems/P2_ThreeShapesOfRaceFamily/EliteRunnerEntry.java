package oops_concepts.assignment_problems.P2_ThreeShapesOfRaceFamily;

/**
 * EliteRunnerEntry – generation 3 (multilevel: RaceEntry → RunnerEntry → EliteRunnerEntry).
 * Adds a sponsor bonus on top of the standard runner entry.
 */
public class EliteRunnerEntry extends RunnerEntry {

    private final double sponsorBonus;

    /**
     * @param bibNumber    forwarded all the way to RaceEntry
     * @param entryFee     forwarded to RaceEntry
     * @param category     forwarded to RunnerEntry
     * @param sponsorBonus additional sponsor bonus amount
     */
    public EliteRunnerEntry(String bibNumber, double entryFee,
                            String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    public double getSponsorBonus() { return sponsorBonus; }

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + getBibNumber()
                + " | Category: " + getCategory()
                + " | Sponsor Bonus: " + sponsorBonus
                + " | Balance: " + getBalanceDue();
    }
}
