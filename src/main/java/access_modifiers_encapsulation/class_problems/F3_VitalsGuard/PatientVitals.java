package access_modifiers_encapsulation.class_problems.F3_VitalsGuard;

import java.util.Arrays;

public class PatientVitals {

    private static final int MAX_READINGS = 500;
    private final double[] readings;
    private int count;

    /**
     * Constructs PatientVitals and seeds initial history by delegating each reading
     * to recordReading(...) to reuse the validation rule without duplication.
     *
     * @param initialReadings Array of seed readings (may contain invalid entries)
     */
    public PatientVitals(double[] initialReadings) {
        this.readings = new double[MAX_READINGS];
        this.count = 0;

        if (initialReadings != null) {
            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    /**
     * Default constructor initializing an empty vitals history.
     */
    public PatientVitals() {
        this(new double[0]);
    }

    /**
     * Silently rejects physically impossible readings (<= 0°C or > 45°C)
     * without throwing an exception.
     *
     * @param reading Vitals temperature reading in Celsius
     */
    public void recordReading(double reading) {
        if (reading <= 0.0 || reading > 45.0) {
            return; // Silently reject impossible reading
        }

        if (count < MAX_READINGS) {
            readings[count++] = reading;
        }
    }

    /**
     * Calculates the average of all valid recorded readings.
     *
     * @return The average reading, or 0.0 if no readings are recorded
     */
    public double getAverage() {
        if (count == 0) {
            return 0.0;
        }

        double sum = 0.0;
        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }

        return sum / count;
    }

    /**
     * Returns a defensive copy of all recorded readings so external modifications
     * do not corrupt internal state.
     *
     * @return A newly allocated array containing the valid readings
     */
    public double[] getAllReadings() {
        double[] defensiveCopy = new double[count];
        System.arraycopy(readings, 0, defensiveCopy, 0, count);
        return defensiveCopy;
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: Construction-Time Filtering via recordReading ---");
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});
        System.out.println("All readings: " + Arrays.toString(v.getAllReadings())); // [36.5, 37.1]
        System.out.printf("Average: %.2f\n", v.getAverage());

        System.out.println("\n--- Test 2: Defensive Copy Verification ---");
        double[] copy = v.getAllReadings();
        copy[0] = 999;
        System.out.println("Mutated caller's copy[0] to 999");
        System.out.println("Internal v.getAllReadings()[0]: " + v.getAllReadings()[0]); // 36.5
    }
}
