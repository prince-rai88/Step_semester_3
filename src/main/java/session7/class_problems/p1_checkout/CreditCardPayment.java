package session7.class_problems.p1_checkout;

public class CreditCardPayment extends PaymentMethod {
    private String cardNumberLastFour;

    public CreditCardPayment(String cardNumberLastFour) {
        super();
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount + " to card ending " + cardNumberLastFour + " - Txn " + getTransactionId();
    }
}
