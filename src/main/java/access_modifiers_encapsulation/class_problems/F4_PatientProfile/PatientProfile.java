package access_modifiers_encapsulation.class_problems.F4_PatientProfile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PatientProfile {

    private String patientId;
    private boolean patientIdSet;
    private String name;
    private boolean discharged;
    private String hashedLockerPin; // Write-only: no getter exists

    /**
     * No-argument constructor required by JavaBean-scanning frameworks.
     * Chained to the name-only constructor.
     */
    public PatientProfile() {
        this(null);
    }

    /**
     * Partial constructor for intake when patient ID is not yet assigned.
     * Chained to the primary two-argument constructor.
     *
     * @param name Patient full name
     */
    public PatientProfile(String name) {
        this(null, name);
    }

    /**
     * Primary constructor initializing patient ID and name.
     * Routes through setPatientId to preserve write-once semantics.
     *
     * @param patientId Unique intake ID (or null if not yet available)
     * @param name      Patient full name
     */
    public PatientProfile(String patientId, String name) {
        this.name = name;
        this.discharged = false;
        if (patientId != null) {
            setPatientId(patientId);
        }
    }

    /**
     * Returns the patient ID.
     *
     * @return Patient ID string or null
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Write-once setter for patient ID.
     * Once set (either via constructor or first call to this setter),
     * all subsequent calls are silently ignored.
     *
     * @param id The immutable patient ID to record
     */
    public void setPatientId(String id) {
        if (!patientIdSet && id != null) {
            this.patientId = id;
            this.patientIdSet = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    /**
     * Write-only property: sets the locker PIN using a deterministic one-way hash.
     * Notice: There is intentionally NO matching getter anywhere in this class.
     *
     * @param pin 4–6 digit numeric PIN string
     */
    public void setLockerPin(String pin) {
        if (pin != null && pin.matches("\\d{4,6}")) {
            this.hashedLockerPin = hashPin(pin);
        }
    }

    private String hashPin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(pin.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Fallback deterministic one-way transformation
            return Integer.toHexString(pin.hashCode());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: Name-only constructor leaves patientId null ---");
        PatientProfile p1 = new PatientProfile("Arjun Iyer");
        System.out.println("p1 ID: " + p1.getPatientId()); // null
        System.out.println("p1 Name: " + p1.getName());

        System.out.println("\n--- Test 2: Full constructor initializes patientId ---");
        PatientProfile p2 = new PatientProfile("MT2026-0142", "Arjun Iyer");
        System.out.println("p2 ID: " + p2.getPatientId()); // "MT2026-0142"

        System.out.println("\n--- Test 3: Write-once setPatientId enforcement ---");
        PatientProfile p3 = new PatientProfile();
        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000"); // Silently ignored
        System.out.println("p3 ID after second set attempt: " + p3.getPatientId()); // "MT2026-0142"

        System.out.println("\n--- Test 4: Write-only locker PIN ---");
        p3.setLockerPin("4829");
        System.out.println("Locker PIN set successfully (write-only, no getter exposed)");
    }
}
