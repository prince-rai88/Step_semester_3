package oops_concepts.class_problems.P2_ThreeShapesOfFamilyTree;

/**
 * Problem 2 – Three Shapes of One Family Tree
 *
 * Inheritance hierarchy:
 *   EventTicket  ←── WorkshopTicket  ←── PremiumWorkshopTicket   (multilevel)
 *   EventTicket  ←── HackathonTicket                              (hierarchical)
 *
 * classifyGeneration() uses instanceof to describe where a ticket sits in the tree.
 * getTotalBalanceDue() sums balances polymorphically with no type-checking per entry.
 */
public class EventTicket {

    // ── Fields ────────────────────────────────────────────────────────────────
    private final String attendeeId;
    protected double basePrice;
    private double amountPaid;

    // ── Constructor ───────────────────────────────────────────────────────────

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException(
                    "Invalid attendeeId: must not be blank and must be at least 4 characters long."
            );
        }
        this.attendeeId = attendeeId.trim();
        this.basePrice  = basePrice;
        this.amountPaid = 0.0;
    }

    // ── Business methods ──────────────────────────────────────────────────────

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return Math.max(0.0, basePrice - amountPaid);
    }

    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + getBalanceDue();
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    /**
     * Classifies a ticket by its depth in the inheritance tree using instanceof.
     * No "type" field is consulted — only runtime type information is used.
     *
     * @param ticket any EventTicket or descendant
     * @return a human-readable classification string
     */
    public static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        } else if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        } else if (ticket instanceof WorkshopTicket) {
            return "Single-level descendant (2 generations deep)";
        } else {
            return "Root ticket (base EventTicket)";
        }
    }

    /**
     * Sums the outstanding balance across every ticket in the array using
     * polymorphism alone — no per-entry type check is performed.
     *
     * @param tickets array of any mix of EventTicket subclasses (length ≤ 1,000)
     * @return total balance due
     */
    public static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0.0;
        for (EventTicket t : tickets) {
            total += t.getBalanceDue(); // polymorphic dispatch — no instanceof needed
        }
        return total;
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Problem 2: Three Shapes of One Family Tree ===\n");

        EventTicket          standard  = new EventTicket("STU1", 500);
        WorkshopTicket       workshop  = new WorkshopTicket("STU2", 1200, "AI/ML");
        PremiumWorkshopTicket premium  = new PremiumWorkshopTicket("STU3", 2000, "Cloud Native", 300);
        HackathonTicket      hackathon = new HackathonTicket("STU4", 800, "Byte Force");

        // Test 1 – printTicket
        System.out.println("-- Test 1: printTicket --");
        System.out.println(standard.printTicket());
        System.out.println(workshop.printTicket());
        System.out.println(premium.printTicket());
        System.out.println(hackathon.printTicket());

        // Test 2 – classifyGeneration
        System.out.println("\n-- Test 2: classifyGeneration --");
        System.out.println(classifyGeneration(premium));    // Multilevel descendant
        System.out.println(classifyGeneration(hackathon));  // Hierarchical sibling

        // Test 3 – getTotalBalanceDue
        System.out.println("\n-- Test 3: getTotalBalanceDue --");
        EventTicket[] all = {standard, workshop, premium, hackathon};
        System.out.println("Total balance due: " + getTotalBalanceDue(all)); // 4500.0
    }
}
