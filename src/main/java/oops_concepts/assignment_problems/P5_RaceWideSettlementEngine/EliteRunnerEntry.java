package oops_concepts.assignment_problems.P5_RaceWideSettlementEngine;

/**
 * EliteRunnerEntry – generation 3 (RaceEntry → RunnerEntry → EliteRunnerEntry).
 * Gets its own unique entryCode from the shared counter via the super chain.
 */
public class EliteRunnerEntry extends RunnerEntry {

    private final double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee,
                            String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    public double getSponsorBonus() { return sponsorBonus; }

    @Override
    public String announce() {
        return "Elite Runner [" + entryCode + "] | Bib: " + getBibNumber()
                + " | Category: " + getCategory()
                + " | Sponsor Bonus: " + sponsorBonus
                + " | Balance: " + getBalanceDue();
    }
}
