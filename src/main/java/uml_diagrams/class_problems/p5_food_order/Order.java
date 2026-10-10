package uml_diagrams.class_problems.p5_food_order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum Status {
        CREATED("Created"),
        PAID("Paid"),
        PENDING_PAYMENT("Pending Payment");

        private final String displayName;

        Status(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private final String orderId;
    private final Customer customer;
    private final List<LineItem> lineItems;
    private Status status;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.lineItems = new ArrayList<>();
        this.status = Status.CREATED;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public Status getStatus() {
        return status;
    }

    public void addItem(FoodItem item, int quantity) {
        lineItems.add(new LineItem(item, quantity));
    }

    public double calculateTotal() {
        double total = 0.0;
        for (LineItem item : lineItems) {
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean placeOrder(IPaymentMethod paymentMethod) {
        if (lineItems.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
            return false;
        }

        double total = calculateTotal();
        boolean paymentSuccess = paymentMethod.processPayment(total);

        if (paymentSuccess) {
            this.status = Status.PAID;
            System.out.println("Order placed successfully. Payment via " + paymentMethod.getMethodName() +
                    " successful. Order status: " + status.getDisplayName() + ".");
            customer.receiveNotification("Order #" + orderId + " placed and paid.");
            return true;
        } else {
            this.status = Status.PENDING_PAYMENT;
            System.out.println("Order placed. Payment via " + paymentMethod.getMethodName() +
                    " failed. Order status: " + status.getDisplayName() + ".");
            customer.receiveNotification("Order #" + orderId + " placed, awaiting payment.");
            return false;
        }
    }
}
