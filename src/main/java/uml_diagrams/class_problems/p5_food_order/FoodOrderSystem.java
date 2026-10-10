package uml_diagrams.class_problems.p5_food_order;

public class FoodOrderSystem {
    public static void main(String[] args) {
        Customer customer = new Customer("C1", "Customer");
        FoodItem pizza = new FoodItem("Pizza", 12.0);
        FoodItem soda = new FoodItem("Soda", 3.0);
        FoodItem burger = new FoodItem("Burger", 8.0);

        // 1. Customer creates an order, adds 'Pizza (Qty 2)' and 'Soda (Qty 1)'.
        Order order1 = new Order("123", customer);
        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);
        System.out.println("Order created. Added Pizza (Qty 2), Soda (Qty 1).");

        // 2. Customer attempts to place the order with an empty cart (should fail).
        Order emptyOrder = new Order("999", customer);
        emptyOrder.placeOrder(new CreditCardPayment());

        // 3. Customer places the order, attempts payment via Credit Card (simulate success).
        order1.placeOrder(new CreditCardPayment());

        // 4. Customer creates another order, adds 'Burger (Qty 1)', attempts payment via Digital Wallet (simulate failure).
        Order order2 = new Order("124", customer);
        order2.addItem(burger, 1);
        order2.placeOrder(new DigitalWalletPayment(false));
    }
}
