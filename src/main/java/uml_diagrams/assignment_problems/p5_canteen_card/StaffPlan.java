package uml_diagrams.assignment_problems.p5_canteen_card;

public class StaffPlan implements PricingPlan {
    @Override
    public String getPlanName() {
        return "Staff";
    }

    @Override
    public double calculateDiscountedPrice(double originalPrice) {
        return originalPrice * 0.80;
    }
}
