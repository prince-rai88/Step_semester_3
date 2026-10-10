package uml_diagrams.class_problems.p5_food_order;

public class LineItem {
    private final FoodItem foodItem;
    private final int quantity;

    public LineItem(FoodItem foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return foodItem.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return foodItem.getName() + " (Qty " + quantity + ")";
    }
}
