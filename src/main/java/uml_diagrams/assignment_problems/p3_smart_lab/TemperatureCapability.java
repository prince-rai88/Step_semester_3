package uml_diagrams.assignment_problems.p3_smart_lab;

public class TemperatureCapability implements Capability {
    public static final String TYPE = "Temperature";
    private int temperature;

    public TemperatureCapability() {
        this.temperature = 22;
    }

    @Override
    public String getCapabilityType() {
        return TYPE;
    }

    public int getTemperature() {
        return temperature;
    }

    public boolean setTemperature(String deviceName, int temperature) {
        if (temperature < 16 || temperature > 30) {
            System.out.println("Rejected: " + deviceName + " temperature must be between 16°C and 30°C.");
            return false;
        }
        this.temperature = temperature;
        return true;
    }
}
