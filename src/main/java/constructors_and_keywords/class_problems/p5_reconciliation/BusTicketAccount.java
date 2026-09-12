package constructors_and_keywords.class_problems.p5_reconciliation;

public class BusTicketAccount {
    private String bookingId;
    private double ticketFare;
    
    private static double minimumPenaltyPercent;

    static {
        minimumPenaltyPercent = 1.0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate <= 0) return 0.0;
        double penalty = 0;
        int m = minutesLate;
        if (m > 15) {
            penalty += (m - 15) * 0.02 * ticketFare;
            m = 15;
        }
        if (m > 5) {
            penalty += (m - 5) * 0.01 * ticketFare;
            m = 5;
        }
        if (m > 0) {
            penalty += m * 0.005 * ticketFare;
        }
        double minFloor = ticketFare * (minimumPenaltyPercent / 100.0);
        return Math.max(penalty, minFloor);
    }

    public void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double grandTotalPenalties = 0.0;

        int len = Math.min(accounts.length, Math.min(amounts.length, minutesLateArray.length));

        for (int i = 0; i < len; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
            } else {
                processed++;
                if (accounts[i] instanceof SleeperAccount) {
                    sleeper++;
                } else {
                    regular++;
                }
                grandTotalPenalties += accounts[i].calculatePenalty(minutesLateArray[i]);
            }
        }
        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = %.1f%n",
            processed, nullSkipped, sleeper, regular, grandTotalPenalties);
    }
}
