package abstraction_and_interface.class_problems.p1_checkout;

public abstract class PaymentMethod {
    private static int counter = 1000;
    private final String transactionId;

    public PaymentMethod() {
        this.transactionId = "TXN-" + (++counter);
    }

    public abstract String processPayment(double amount);

    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }

    public String getTransactionId() {
        return transactionId;
    }
}
