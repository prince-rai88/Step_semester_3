package abstraction_and_interface.class_problems.p5_library_system;

public class DigitalPass implements Renewable {
    private String resourceName;

    public DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return resourceName + " renewed";
    }
}
