package oops_concepts.assignment_problems.P5_RaceWideSettlementEngine;

/**
 * RelayTeamEntry – hierarchical branch extending RaceEntry directly.
 * Gets its own unique entryCode from the shared counter via super().
 * settleNight() uses instanceof to identify and count this type separately.
 */
public class RelayTeamEntry extends RaceEntry {

    private final int teamSize;

    /**
     * @param bibNumber forwarded to RaceEntry (triggers counter increment)
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
        return "Relay Team [" + entryCode + "] | Bib: " + getBibNumber()
                + " | Team Size: " + teamSize
                + " | Balance: " + getBalanceDue();
    }
}
