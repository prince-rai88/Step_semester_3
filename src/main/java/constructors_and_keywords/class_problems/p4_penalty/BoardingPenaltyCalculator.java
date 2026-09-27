package constructors_and_keywords.class_problems.p4_penalty;

public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (minutesLate == 0) return 0.0;

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
}
