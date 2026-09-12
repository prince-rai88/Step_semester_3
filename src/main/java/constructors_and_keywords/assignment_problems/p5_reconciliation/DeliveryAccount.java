package constructors_and_keywords.assignment_problems.p5_reconciliation;

public class DeliveryAccount {
    private String studentId;
    private double orderValue;
    
    private static double minimumSurgePercent;

    static {
        minimumSurgePercent = 1.0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes <= 0) return 0.0;
        double fee = 0;
        int m = delayMinutes;
        if (m > 15) {
            fee += (m - 15) * 0.02 * orderValue;
            m = 15;
        }
        if (m > 5) {
            fee += (m - 5) * 0.01 * orderValue;
            m = 5;
        }
        if (m > 0) {
            fee += m * 0.005 * orderValue;
        }
        double minFloor = orderValue * (minimumSurgePercent / 100.0);
        return Math.max(fee, minFloor);
    }

    public void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        // processing logic
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotalSurge = 0.0;

        int len = Math.min(accounts.length, Math.min(amounts.length, delayMinutesArray.length));

        for (int i = 0; i < len; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
            } else {
                processed++;
                if (accounts[i] instanceof PremiumAccount) {
                    premium++;
                } else {
                    regular++;
                }
                grandTotalSurge += accounts[i].calculateSurgeFee(delayMinutesArray[i]);
            }
        }
        System.out.printf("%d processed | %d null skipped | %d premium | %d regular | grand total surge fees = %.1f%n",
            processed, nullSkipped, premium, regular, grandTotalSurge);
    }
}
