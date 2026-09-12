package constructors_and_keywords.class_problems.p2_faresplitter;

public class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0 || passengerCount > 60) {
            throw new IllegalArgumentException("Invalid split parameters");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1); 
    }

    public FareSplitter(String tripId) {
        this(tripId, 0, 1);
    }

    public double[] fareBreakdown() {
        double[] shares = new double[passengerCount];
        if (passengerCount == 0) return shares;

        long totalPaise = Math.round(totalFare * 100);
        long baseShare = totalPaise / passengerCount;
        long remainder = totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            shares[i] = baseShare / 100.0;
        }

        for (int i = 0; i < remainder; i++) {
            shares[passengerCount - 1 - i] += 0.01;
            shares[passengerCount - 1 - i] = Math.round(shares[passengerCount - 1 - i] * 100.0) / 100.0;
        }
        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}
