package uml_diagrams.assignment_problems.p2_swiftship;

import java.util.ArrayList;
import java.util.List;

public class Parcel {
    private final String id;
    private final double weightKg;
    private final ShippingType shippingType;
    private final double charge;
    private final List<NotificationChannel> channels;
    private ParcelStatus status;

    public Parcel(String id, double weightKg, ShippingType shippingType) {
        this.id = id;
        this.weightKg = weightKg;
        this.shippingType = shippingType;
        this.charge = shippingType.calculateCost(weightKg);
        this.channels = new ArrayList<>();
        this.status = ParcelStatus.BOOKED;
    }

    public String getId() {
        return id;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public double getCharge() {
        return charge;
    }

    public ParcelStatus getStatus() {
        return status;
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    public void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(id, status);
        }
    }

    public boolean updateStatus(ParcelStatus newStatus) {
        if (!status.canTransitionTo(newStatus)) {
            System.out.println("Invalid transition: " + status + " → " + newStatus + " is not allowed.");
            return false;
        }
        this.status = newStatus;
        notifyChannels();
        return true;
    }

    public boolean cancel() {
        if (status != ParcelStatus.BOOKED) {
            System.out.println("Cancellation failed: " + id + " can be cancelled only while BOOKED.");
            return false;
        }
        this.status = ParcelStatus.CANCELLED;
        notifyChannels();
        return true;
    }
}
