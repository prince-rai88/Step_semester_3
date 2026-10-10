package abstraction_and_interface.assignment_problems.p5_connected_home;

public class SmartHomeSystem {
    public static void connectAll(RemoteControllable[] items, String appId) {
        for (RemoteControllable rc : items) {
            System.out.println(rc.connect(appId));
        }
    }

    public static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            return ((EnergyTrackable) d).getConsumptionWatts();
        }
        return 0.0;
    }
}
