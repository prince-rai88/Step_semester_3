package uml_diagrams.class_problems.p5_food_order;

public class DigitalWalletPayment implements IPaymentMethod {
    private final boolean shouldSucceed;

    public DigitalWalletPayment() {
        this(true);
    }

    public DigitalWalletPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public String getMethodName() {
        return "Digital Wallet";
    }

    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed;
    }
}
