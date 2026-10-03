package oops_concepts.assignment_problems.P4_RaceDayAnnouncerBoard;

/**
 * Problem 4 – The Race-Day Announcer Board
 *
 * announceAll() loops over a mixed RaceEntry[] and:
 *  1. Calls announce() polymorphically — no instanceof-based if-else for printing.
 *  2. Builds the full report with a single StringBuilder across all iterations.
 *  3. Uses instanceof ONLY to safely downcast to RelayTeamEntry when team-size
 *     detail is needed — never attempted on a non-RelayTeamEntry.
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

    /**
     * Returns a one-line description. Subclasses override with richer output.
     */
    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    /**
     * Assembles the race-day announcement board for all entries.
     *
     * Rules:
     * • announce() is called polymorphically — no instanceof chain for printing.
     * • A single StringBuilder accumulates all output across every iteration.
     * • instanceof is used ONLY to safely downcast and append the team-size
     *   detail for genuine RelayTeamEntry entries.
     *
     * @param entries mixed array (RaceEntry and any subclasses); no nulls in P4
     * @return the assembled announcement string
     */
    public static String announceAll(RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();

        for (RaceEntry entry : entries) {
            sb.append(entry.announce()); // polymorphic dispatch — correct type's method runs

            // Only reach for the team size when we know it's safe to cast
            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry; // guarded downcast
                sb.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    // ── Main (smoke tests) ────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Assignment P4: Race-Day Announcer Board ===\n");

        // Test 1 – mixed announceAll
        System.out.println("-- Test 1: announceAll (mixed entries) --");
        RunnerEntry    runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        RelayTeamEntry relay  = new RelayTeamEntry("BIB4001", 300, 4);
        runner.applyLateFee(40); // push balance up to match problem example (90.0 shown)

        RaceEntry[] fleet = {runner, relay};
        System.out.println(announceAll(fleet));
        // Runner Entry | Bib: BIB2001 | ... | Balance: 120.0 |
        // Relay Team | Bib: BIB4001 | ... | Balance: 300.0 [Team size via downcast: 4] |

        // Test 2 – illegal downcast (ClassCastException at runtime)
        System.out.println("\n-- Test 2: invalid downcast (ClassCastException) --");
        try {
            RaceEntry plain = new RaceEntry("BIB5001", 50);
            RelayTeamEntry bad = (RelayTeamEntry) plain; // compiles, fails at runtime
            System.out.println("No exception — unexpected: " + bad);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught as expected: " + e.getMessage());
        }
    }
}
