package uml_diagrams.class_problems.p5_food_order;

public class CreditCardPayment implements IPaymentMethod {
    @Override
    public String getMethodName() {
        return "Credit Card";
    }

    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}
