package abstraction_and_interface.assignment_problems.p5_connected_home;

public abstract class HomeDevice {
    private static int counter = 1000;
    private final String serialNumber;

    public HomeDevice() {
        this.serialNumber = "HD-" + (++counter);
    }

    public abstract String activate();

    public String getSerialNumber() {
        return serialNumber;
    }
}
