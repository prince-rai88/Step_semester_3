package oops_concepts.class_problems.P2_ThreeShapesOfFamilyTree;

/**
 * HackathonTicket – hierarchical branch extending EventTicket directly,
 * completely independent of WorkshopTicket and its descendants.
 */
public class HackathonTicket extends EventTicket {

    private final String teamName;

    /**
     * @param attendeeId forwarded to EventTicket
     * @param basePrice  forwarded to EventTicket
     * @param teamName   name of the competing team
     */
    public HackathonTicket(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public String printTicket() {
        return "Hackathon Ticket | Team: " + teamName + " | Balance Due: " + getBalanceDue();
    }
}
