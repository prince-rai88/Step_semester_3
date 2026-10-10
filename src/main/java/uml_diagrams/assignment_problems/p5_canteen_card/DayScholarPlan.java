package uml_diagrams.assignment_problems.p5_canteen_card;

public class DayScholarPlan implements PricingPlan {
    @Override
    public String getPlanName() {
        return "Day Scholar";
    }

    @Override
    public double calculateDiscountedPrice(double originalPrice) {
        return originalPrice;
    }
}
