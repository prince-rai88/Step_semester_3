package constructors_and_keywords.assignment_problems.p4_surge;

public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (delayMinutes == 0) return 0.0;

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
}
