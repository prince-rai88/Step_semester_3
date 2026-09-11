package access_modifiers_encapsulation.class_problems.F1_FieldVisibility;

public class PatientRecord {

    // Sensitive internal identifier: private
    private String patientId;

    // Ward information accessible to subclasses/package: protected
    protected String wardCode;

    // Clinical score accessible to subclasses/package: protected
    protected double vitalsScore;

    // Facility name is general public information: public
    public String facilityName;

    /**
     * Parameterized constructor serving as a validation gate.
     * Rejects invalid patientId (null, blank, whitespace-only, or length < 4).
     *
     * Note: No no-argument constructor is provided.
     */
    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        if (patientId == null || patientId.trim().length() < 4) {
            throw new IllegalArgumentException(
                    "Invalid patientId: must not be blank and must be at least 4 characters long."
            );
        }

        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public double getVitalsScore() {
        return vitalsScore;
    }

    public void setVitalsScore(double vitalsScore) {
        this.vitalsScore = vitalsScore;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: Valid PatientRecord ---");
        try {
            PatientRecord valid = new PatientRecord("MT94", "W3", 98.2, "MediTrack Central");
            System.out.println("Constructed successfully: ID = " + valid.getPatientId());
        } catch (IllegalArgumentException e) {
            System.out.println("Unexpected failure: " + e.getMessage());
        }

        System.out.println("\n--- Test 2: Invalid PatientRecord (Too short) ---");
        try {
            new PatientRecord("MT9", "W3", 98.2, "MediTrack Central");
            System.out.println("Construction succeeded (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected as expected: " + e.getMessage());
        }

        System.out.println("\n--- Test 3: Invalid PatientRecord (Whitespace-only) ---");
        try {
            new PatientRecord("    ", "W3", 98.2, "MediTrack Central");
            System.out.println("Construction succeeded (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected as expected: " + e.getMessage());
        }
    }
}
