package oops_concepts.assignment_problems.P4_RaceDayAnnouncerBoard;

/**
 * RelayTeamEntry for Problem 4.
 * announceAll() uses instanceof to safely downcast and reach getTeamSize().
 */
public class RelayTeamEntry extends RaceEntry {

    private final int teamSize;

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
