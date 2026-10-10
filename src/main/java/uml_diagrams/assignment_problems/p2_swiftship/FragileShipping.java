package uml_diagrams.assignment_problems.p2_swiftship;

public class FragileShipping implements ShippingType {
    private final StandardShipping standardShipping = new StandardShipping();
    private static final double HANDLING_FEE = 50.0;

    @Override
    public String getTypeName() {
        return "Fragile";
    }

    @Override
    public double calculateCost(double weightKg) {
        return standardShipping.calculateCost(weightKg) + HANDLING_FEE;
    }
}
