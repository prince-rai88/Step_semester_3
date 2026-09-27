package constructors_and_keywords.assignment_problems.p1_foodorder;

public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean delivered = false;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty() ||
            dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid order");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
    }

    public void markDelivered() {
        if (delivered) {
            System.out.println("Warning: Order already marked as delivered!");
        } else {
            delivered = true;
            System.out.println("Order marked as delivered.");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }
        System.out.printf("Valid: %d | Rejected: %d%n", valid, rejected);
    }
}
