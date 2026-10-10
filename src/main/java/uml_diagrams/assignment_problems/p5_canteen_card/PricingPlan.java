package uml_diagrams.assignment_problems.p5_canteen_card;

public interface PricingPlan {
    String getPlanName();
    double calculateDiscountedPrice(double originalPrice);
}
