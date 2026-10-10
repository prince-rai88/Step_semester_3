package session7.class_problems.p1_checkout;

public class PaymentProcessor {
    public static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void test() {
        CreditCardPayment cc = new CreditCardPayment("4471");
        System.out.println(cc.processPayment(250.0));

        CashPayment cash = new CashPayment();
        System.out.println(cash.processPayment(40.0));

        System.out.println(cc.processPayment(250.0, "Birthday gift"));

        // upcasting: a CreditCardPayment reference stored as its parent type
        PaymentMethod ref = cc; 
        printConfirmation(ref, 250.0);
    }
}
