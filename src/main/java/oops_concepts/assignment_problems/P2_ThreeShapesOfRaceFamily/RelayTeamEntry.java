package oops_concepts.assignment_problems.P2_ThreeShapesOfRaceFamily;

/**
 * RelayTeamEntry – hierarchical branch extending RaceEntry directly,
 * completely independent of RunnerEntry and its descendants.
 */
public class RelayTeamEntry extends RaceEntry {

    private final int teamSize;

    /**
     * @param bibNumber forwarded to RaceEntry
     * @param entryFee  forwarded to RaceEntry
     * @param teamSize  number of runners in the relay team (positive integer)
     */
    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() { return teamSize; }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + getBibNumber()
                + " | Team Size: " + teamSize
                + " | Balance: " + getBalanceDue();
    }
}
