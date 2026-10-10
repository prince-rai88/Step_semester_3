package uml_diagrams.class_problems.p5_food_order;

public interface IPaymentMethod {
    String getMethodName();
    boolean processPayment(double amount);
}
