package access_modifiers_encapsulation.class_problems.F5_DischargeSummary;

public class CriticalCareDischargeSummary extends DischargeSummary {

    private final int icuDays;

    /**
     * Constructs an immutable CriticalCareDischargeSummary with ICU stay duration.
     *
     * @param patientId       Unique patient identifier
     * @param medicationCodes Array of valid medication codes
     * @param icuDays         Number of days spent in the intensive care unit
     */
    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        if (icuDays < 0) {
            throw new IllegalArgumentException("ICU days cannot be negative.");
        }
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }

    /**
     * Wither method preserving subclass type and ICU-specific state.
     *
     * @param index   Index of medication code to replace
     * @param newCode New medication code
     * @return New CriticalCareDischargeSummary instance
     */
    @Override
    public CriticalCareDischargeSummary withCorrectedMedication(int index, String newCode) {
        String[] updatedCodes = getMedicationCodes();
        if (index < 0 || index >= updatedCodes.length) {
            throw new IndexOutOfBoundsException("Invalid medication index: " + index);
        }
        updatedCodes[index] = newCode;
        return new CriticalCareDischargeSummary(getPatientId(), updatedCodes, this.icuDays);
    }
}
