package access_modifiers_encapsulation.class_problems.F5_DischargeSummary;

import java.util.Arrays;
import java.util.regex.Pattern;

public class DischargeSummary {

    // Shared one-time compiled pattern for medication code validation
    private static final Pattern MEDICATION_CODE_PATTERN;

    static {
        // One-time static setup for validation rule: "MED-" followed by exactly one uppercase letter
        MEDICATION_CODE_PATTERN = Pattern.compile("^MED-[A-Z]$");
    }

    private final String patientId;
    private final String[] medicationCodes;

    /**
     * Constructs an immutable DischargeSummary.
     * Rejects construction if patientId is invalid, or if any medication code
     * fails the "MED-[A-Z]" format.
     * Defensively copies input array to prevent caller mutation.
     *
     * @param patientId       Unique patient ID
     * @param medicationCodes Array of medication codes
     */
    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty.");
        }
        if (medicationCodes == null) {
            throw new IllegalArgumentException("Medication codes array cannot be null.");
        }

        for (String code : medicationCodes) {
            if (code == null || !MEDICATION_CODE_PATTERN.matcher(code).matches()) {
                throw new IllegalArgumentException(
                        "Invalid medication code format: \"" + code + "\". Must match MED-[A-Z]."
                );
            }
        }

        this.patientId = patientId;
        // Defensive copy on the way in
        this.medicationCodes = medicationCodes.clone();
    }

    public String getPatientId() {
        return patientId;
    }

    /**
     * Returns a defensive copy of medication codes so caller cannot mutate internal state.
     *
     * @return Cloned array of medication codes
     */
    public String[] getMedicationCodes() {
        // Defensive copy on the way out
        return medicationCodes.clone();
    }

    /**
     * Wither method: returns a brand-new DischargeSummary instance with the corrected medication code,
     * maintaining complete immutability.
     *
     * @param index   Position in the medication codes array
     * @param newCode New medication code matching MED-[A-Z]
     * @return New DischargeSummary instance
     */
    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IndexOutOfBoundsException("Invalid medication index: " + index);
        }

        String[] updatedCodes = this.medicationCodes.clone();
        updatedCodes[index] = newCode;
        return new DischargeSummary(this.patientId, updatedCodes);
    }

    /**
     * Nightly batch processor that reconciles discharges without crashing on null entries.
     * Uses instanceof to differentiate CriticalCareDischargeSummary from routine summaries.
     *
     * @param summaries Batch array of discharge summaries (may contain nulls)
     * @return Formatted batch reconciliation string
     */
    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0;
        int nullSkipped = 0;
        int criticalCareCount = 0;
        int routineCount = 0;

        if (summaries != null) {
            for (DischargeSummary summary : summaries) {
                if (summary == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (summary instanceof CriticalCareDischargeSummary) {
                        criticalCareCount++;
                    } else {
                        routineCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + criticalCareCount + " critical-care | " + routineCount + " routine";
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: Construction Format Validation ---");
        try {
            new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
            System.out.println("Construction succeeded (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected as expected: " + e.getMessage());
        }

        System.out.println("\n--- Test 2: Defensive Copying Immutability ---");
        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println("Mutated returned array[0] to TAMPERED");
        System.out.println("d.getMedicationCodes()[0]: " + d.getMedicationCodes()[0]); // MED-A

        System.out.println("\n--- Test 3: Wither Pattern ---");
        DischargeSummary corrected = d.withCorrectedMedication(0, "MED-C");
        System.out.println("Original codes: " + Arrays.toString(d.getMedicationCodes())); // [MED-A, MED-B]
        System.out.println("Corrected codes: " + Arrays.toString(corrected.getMedicationCodes())); // [MED-C, MED-B]

        System.out.println("\n--- Test 4: Nightly Batch Processing ---");
        DischargeSummary[] batch = {
                new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
                null,
                new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(batch));
        // "2 processed | 1 null skipped | 1 critical-care | 1 routine"
    }
}
