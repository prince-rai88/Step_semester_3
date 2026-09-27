package constructors.assignment_problems;

public class FoodOrder {
    String studentName, dishName;
    boolean delivered = false;

    FoodOrder(String studentName, String dishName) {

        if (studentName == null || dishName == null) {
            throw new IllegalArgumentException("Invalid Argument");
        }

        if (studentName.trim().length() == 0 ||
                dishName.trim().length() == 0) {
            throw new IllegalArgumentException("Invalid Argument");
        }

        this.studentName = studentName;
        this.dishName = dishName;
    }

    void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order delivered successfully.");
        } else {
            System.out.println("Order was already delivered.");
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }
    public static void main(String[] args) {

        String[][] rawOrders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "},
                {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);

        FoodOrder order = new FoodOrder("Arun", "Masala Dosa");

        order.markDelivered();
        order.markDelivered();
    }
}