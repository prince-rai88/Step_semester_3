package uml_diagrams.assignment_problems.p2_swiftship;

import java.util.Locale;

public class SwiftShipDemo {
    public static void main(String[] args) {
        // 1. Customer books Express parcel 'P101' weighing 2 kg and subscribes to SMS and Email updates.
        Parcel p101 = new Parcel("P101", 2.0, new ExpressShipping());
        System.out.printf(Locale.US, "Parcel %s booked (%s, %.0f kg). Charge: ₹%.2f.%n",
                p101.getId(), p101.getShippingType().getTypeName(), p101.getWeightKg(), p101.getCharge());

        p101.subscribe(new SmsChannel());
        p101.subscribe(new EmailChannel());
        p101.notifyChannels();

        // 2. Courier marks 'P101' as picked up.
        p101.updateStatus(ParcelStatus.PICKED_UP);

        // 3. Customer attempts to cancel 'P101'.
        p101.cancel();

        // 4. Courier marks 'P101' as in transit.
        p101.updateStatus(ParcelStatus.IN_TRANSIT);

        // 5. Courier attempts to mark 'P101' as delivered.
        p101.updateStatus(ParcelStatus.DELIVERED);
    }
}
