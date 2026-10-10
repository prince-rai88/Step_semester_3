package uml_diagrams.assignment_problems.p3_smart_lab;

public class PowerCapability implements Capability {
    public static final String TYPE = "Power";
    private boolean on;

    public PowerCapability() {
        this.on = false;
    }

    @Override
    public String getCapabilityType() {
        return TYPE;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
