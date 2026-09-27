package session1.assignment_problems.p1_seat_duplication;

public class SeatChecker {
    public void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null) return;
        boolean found = false;
        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No Duplicate Seats Found");
        }
    }
}
