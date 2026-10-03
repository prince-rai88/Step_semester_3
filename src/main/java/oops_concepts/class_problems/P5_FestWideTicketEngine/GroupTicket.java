package oops_concepts.class_problems.P5_FestWideTicketEngine;

/**
 * GroupTicket – extends EventTicket for group registrations.
 *
 * Calls super(basePrice) so it gets its own unique ticketId from the
 * shared static counter, just like any other EventTicket.
 * The groupSize is stored locally for settlement reporting.
 */
public class GroupTicket extends EventTicket {

    private final int groupSize;

    /**
     * @param basePrice  total price for the group booking
     * @param groupSize  number of attendees in the group (positive integer)
     */
    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);                     // ticketId assigned by parent counter
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }

    @Override
    public String printTicket() {
        return "Group Ticket [" + ticketId + "] | Group Size: " + groupSize
                + " | Balance Due: " + getBalanceDue();
    }
}
