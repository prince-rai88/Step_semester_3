package oops_concepts.class_problems.P2_ThreeShapesOfFamilyTree;

/**
 * PremiumWorkshopTicket – generation 3 (multilevel: EventTicket → WorkshopTicket → PremiumWorkshopTicket).
 * Adds a materials kit fee on top of the workshop base price.
 */
public class PremiumWorkshopTicket extends WorkshopTicket {

    private final double kitFee;

    /**
     * @param attendeeId forwarded all the way up to EventTicket
     * @param basePrice  base ticket price (forwarded to EventTicket)
     * @param track      workshop track (forwarded to WorkshopTicket)
     * @param kitFee     additional materials kit fee
     */
    public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    public double getKitFee() {
        return kitFee;
    }

    @Override
    public String printTicket() {
        return "Premium Workshop Ticket | Track: " + getTrack()
                + " | Kit Fee: " + kitFee
                + " | Balance Due: " + getBalanceDue();
    }
}
