package uml_diagrams.assignment_problems.p2_swiftship;

public class StandardShipping implements ShippingType {
    private static final double BASE_RATE = 40.0;
    private static final double PER_KG_RATE = 10.0;

    @Override
    public String getTypeName() {
        return "Standard";
    }

    @Override
    public double calculateCost(double weightKg) {
        return BASE_RATE + (PER_KG_RATE * weightKg);
    }
}
