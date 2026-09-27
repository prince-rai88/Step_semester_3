package access_modifiers_encapsulation.assignment_problems.p5_immutable_loan_receipt;

import java.util.Arrays;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        if (bookIds == null) throw new IllegalArgumentException("construction rejected");
        if (bookIds.length > 20) throw new IllegalArgumentException("construction rejected");
        for (String id : bookIds) {
            if (id == null || !id.matches("BK-\\d{3}")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] newIds = Arrays.copyOf(bookIds, bookIds.length);
        newIds[index] = newId;
        if (this instanceof ReferenceOnlyLoanReceipt) {
             return new ReferenceOnlyLoanReceipt(this.memberId, newIds, ((ReferenceOnlyLoanReceipt)this).getRoomNumber());
        }
        return new LoanReceipt(this.memberId, newIds);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int refOnly = 0;
        int regular = 0;

        if (receipts == null) return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        if (receipts.length > 5000) throw new IllegalArgumentException("batch too large");

        for (LoanReceipt r : receipts) {
            if (r == null) {
                nullSkipped++;
            } else {
                processed++;
                if (r instanceof ReferenceOnlyLoanReceipt) {
                    refOnly++;
                } else {
                    regular++;
                }
            }
        }
        return String.format("%d processed | %d null skipped | %d reference-only | %d regular", processed, nullSkipped, refOnly, regular);
    }
}
