package uml_diagrams.assignment_problems.p3_smart_lab;

public class BrightnessCapability implements Capability {
    public static final String TYPE = "Brightness";
    private int brightness;

    public BrightnessCapability() {
        this.brightness = 100;
    }

    @Override
    public String getCapabilityType() {
        return TYPE;
    }

    public int getBrightness() {
        return brightness;
    }

    public boolean setBrightness(String deviceName, int brightness) {
        if (brightness < 0 || brightness > 100) {
            System.out.println("Rejected: " + deviceName + " brightness must be between 0% and 100%.");
            return false;
        }
        this.brightness = brightness;
        return true;
    }
}
