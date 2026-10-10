package session7.assignment_problems.p5_connected_home;

public class Refrigerator extends HomeDevice implements EnergyTrackable {
    private double consumptionWatts;

    public Refrigerator(double consumptionWatts) {
        super();
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Refrigerator " + getSerialNumber() + " cooling";
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}
