package uml_diagrams.assignment_problems.p2_swiftship;

public class SmsChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}
