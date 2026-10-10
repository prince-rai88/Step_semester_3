package uml_diagrams.assignment_problems.p5_canteen_card;

public class HostellerPlan implements PricingPlan {
    @Override
    public String getPlanName() {
        return "Hosteller";
    }

    @Override
    public double calculateDiscountedPrice(double originalPrice) {
        return originalPrice * 0.90;
    }
}
