package abstraction_and_interface.class_problems.p2_home_safety;

public class SmokeDetector implements Alertable {
    private String deviceId;

    public SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String sendAlert(String message) {
        return "[" + deviceId + "] " + message;
    }
}
