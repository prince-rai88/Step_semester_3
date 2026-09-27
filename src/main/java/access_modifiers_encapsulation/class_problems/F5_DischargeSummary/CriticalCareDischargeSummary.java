package access_modifiers_encapsulation.class_problems.F5_DischargeSummary;

public class CriticalCareDischargeSummary extends DischargeSummary {

    private final int icuDays;

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
