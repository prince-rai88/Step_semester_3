package uml_diagrams.assignment_problems.p5_canteen_card;

public class Transaction {
    private final String description;
    private final double amount; // positive for top-up/refund, negative for purchase
    private final boolean isPurchase;
    private boolean refunded;

    public Transaction(String description, double amount, boolean isPurchase) {
        this.description = description;
        this.amount = amount;
        this.isPurchase = isPurchase;
        this.refunded = false;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isPurchase() {
        return isPurchase;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }
}
