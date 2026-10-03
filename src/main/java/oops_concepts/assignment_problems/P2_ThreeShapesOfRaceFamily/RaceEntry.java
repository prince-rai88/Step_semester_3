package oops_concepts.assignment_problems.P2_ThreeShapesOfRaceFamily;

/**
 * Problem 2 – Three Shapes of One Race Family
 *
 * Inheritance hierarchy:
 *   RaceEntry ←── RunnerEntry ←── EliteRunnerEntry   (multilevel, 3 deep)
 *   RaceEntry ←── RelayTeamEntry                     (hierarchical, independent)
 *
 * classifyGeneration() uses instanceof; getTotalBalanceDue() uses polymorphism only.
 */
public class RaceEntry {

    private final String bibNumber;
    protected double entryFee;
    private double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException(
                    "Invalid bibNumber: must not be blank and must be at least 4 characters long."
            );
        }
        this.bibNumber  = bibNumber.trim();
        this.entryFee   = entryFee;
        this.amountPaid = 0.0;
    }

    public String getBibNumber() { return bibNumber; }

    public void pay(double amount) { amountPaid += amount; }

    public double getBalanceDue() {
        return Math.max(0.0, entryFee - amountPaid);
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    /**
     * Classifies a race entry by its depth in the inheritance tree.
     * Uses instanceof exclusively — no manual type field consulted.
     */
    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else if (entry instanceof RunnerEntry) {
            return "Single-level descendant (2 generations deep)";
        } else {
            return "Root entry (base RaceEntry)";
        }
    }

    /**
     * Sums outstanding balances across a mixed array using polymorphism alone.
     * No per-element type check is performed inside the loop.
     *
     * @param entries any mix of RaceEntry subclasses (length ≤ 1,000)
     * @return total balance due
     */
    public static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0.0;
        for (RaceEntry e : entries) {
            total += e.getBalanceDue(); // polymorphic — correct subclass version runs
        }
        return total;
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Assignment P2: Three Shapes of One Race Family ===\n");

        RunnerEntry      runner   = new RunnerEntry("BIB2001", 80,  "Open 10K");
        EliteRunnerEntry elite    = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry   relay    = new RelayTeamEntry("BIB4001", 300, 4);

        // Test 1 – announce()
        System.out.println("-- Test 1: announce() --");
        System.out.println(runner.announce());
        System.out.println(elite.announce());
        System.out.println(relay.announce());

        // Test 2 – classifyGeneration
        System.out.println("\n-- Test 2: classifyGeneration --");
        System.out.println(classifyGeneration(elite));  // Multilevel descendant
        System.out.println(classifyGeneration(relay));  // Hierarchical sibling

        // Test 3 – getTotalBalanceDue
        System.out.println("\n-- Test 3: getTotalBalanceDue --");
        RaceEntry[] all = {runner, elite, relay};
        System.out.println("Total: " + getTotalBalanceDue(all)); // 530.0
    }
}
