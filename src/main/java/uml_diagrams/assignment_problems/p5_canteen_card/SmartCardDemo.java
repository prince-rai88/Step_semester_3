package uml_diagrams.assignment_problems.p5_canteen_card;

public class SmartCardDemo {
    public static void main(String[] args) {
        // 1. Card 'C-2045' (Hosteller plan) is topped up with ₹500.
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());
        card.topUp(500.0);

        // 2. Holder buys 'Veg Thali' priced at ₹120.
        card.purchase("Veg Thali", 120.0);

        // 3. Holder buys 'Cold Coffee' priced at ₹60.
        card.purchase("Cold Coffee", 60.0);

        // 4. Holder attempts to buy items worth ₹400.
        card.purchase("Items worth 400", 400.0);

        // 5. Canteen refunds the 'Veg Thali' purchase.
        card.refund("Veg Thali");

        // 6. Canteen attempts to refund the 'Veg Thali' purchase again.
        card.refund("Veg Thali");

        // 7. Holder requests a mini-statement.
        card.printMiniStatement();
    }
}
