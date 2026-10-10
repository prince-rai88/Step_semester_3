package uml_diagrams.assignment_problems.p3_smart_lab;

import java.util.LinkedHashMap;
import java.util.Map;

public class Device {
    private final String name;
    private final Map<String, Capability> capabilities = new LinkedHashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getCapabilityType(), capability);
    }

    public boolean hasCapability(String type) {
        return capabilities.containsKey(type);
    }

    @SuppressWarnings("unchecked")
    public <T extends Capability> T getCapability(String type) {
        return (T) capabilities.get(type);
    }
}
