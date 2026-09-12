package constructors_and_keywords.class_problems.p1_busticket;

import java.util.HashSet;
import java.util.Set;

public class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn = false;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty() ||
            destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid booking");
        }
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
    }

    public void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
        }
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;
        Set<String> seen = new HashSet<>();

        for (String[] booking : rawBookings) {
            try {
                BusTicket ticket = new BusTicket(booking[0], booking[1]);
                String key = ticket.passengerName.toLowerCase() + "|" + ticket.destination.toLowerCase();
                if (seen.contains(key)) {
                    duplicates++;
                } else {
                    seen.add(key);
                    valid++;
                }
            } catch (Exception e) {
                rejected++;
            }
        }
        System.out.printf("Valid: %d | Rejected: %d | Duplicates skipped: %d%n", valid, rejected, duplicates);
    }
}
