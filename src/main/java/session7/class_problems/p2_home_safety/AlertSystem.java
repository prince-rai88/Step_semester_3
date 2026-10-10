package session7.class_problems.p2_home_safety;

public class AlertSystem {
    public static void broadcastAll(Alertable[] devices, String message) {
        for (Alertable d : devices) {
            System.out.println(d.sendAlert(message));
        }
    }

    public static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor) {
            return ((MotionSensor) a).getZoneName();
        }
        return "Not a motion sensor";
    }
}
