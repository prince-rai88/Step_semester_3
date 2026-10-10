package uml_diagrams.assignment_problems.p2_swiftship;

public class EmailChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}
