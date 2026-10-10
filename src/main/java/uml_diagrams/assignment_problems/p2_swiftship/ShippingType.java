package uml_diagrams.assignment_problems.p2_swiftship;

public interface ShippingType {
    String getTypeName();
    double calculateCost(double weightKg);
}
