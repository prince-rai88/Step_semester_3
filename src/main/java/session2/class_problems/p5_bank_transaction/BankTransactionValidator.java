package session2.class_problems.p5_bank_transaction;

public class BankTransactionValidator {
    public String normalizeReference(String raw) {
        if (raw == null) return null;
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed.toUpperCase();
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public String validateAndFormat(String reference) {
        if (reference == null || reference.length() != 14) {
            return "Invalid: wrong length";
        }
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must be numeric";
            }
        }

        String bankCode = reference.substring(0, 3);
        String dateStr = reference.substring(3, 9);
        String seqStr = reference.substring(9, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] ");
        sb.append("DATE: ").append(dateStr.substring(0, 2)).append("/")
          .append(dateStr.substring(2, 4)).append("/")
          .append(dateStr.substring(4, 6)).append(" | ");
        sb.append("SEQ: ").append(seqStr);
        
        return sb.toString();
    }
}
