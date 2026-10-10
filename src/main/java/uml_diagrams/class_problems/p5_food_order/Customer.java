package uml_diagrams.class_problems.p5_food_order;

public class Customer {
    private final String id;
    private final String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void receiveNotification(String message) {
        System.out.println("Notification: " + message);
    }
}
