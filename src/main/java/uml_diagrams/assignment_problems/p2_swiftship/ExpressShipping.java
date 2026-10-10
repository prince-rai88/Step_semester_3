package uml_diagrams.assignment_problems.p2_swiftship;

public class ExpressShipping implements ShippingType {
    private static final double BASE_RATE = 80.0;
    private static final double PER_KG_RATE = 15.0;

    @Override
    public String getTypeName() {
        return "Express";
    }

    @Override
    public double calculateCost(double weightKg) {
        return BASE_RATE + (PER_KG_RATE * weightKg);
    }
}
