package access_modifiers_encapsulation.class_problems.F1_FieldVisibility;

public class AccessRuleEngine {

    /**
     * Classifies a single access attempt based on Java's core visibility rules
     * across basic contexts: SAME_CLASS, SAME_PACKAGE, and DIFFERENT_PACKAGE.
     *
     * @param fieldModifier   One of: "private", "default", "protected", "public"
     * @param accessorContext One of: "SAME_CLASS", "SAME_PACKAGE", "DIFFERENT_PACKAGE"
     * @return "ALLOWED" or "DENIED"
     */
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier.toLowerCase()) {
            case "private":
                return "SAME_CLASS".equalsIgnoreCase(accessorContext) ? "ALLOWED" : "DENIED";

            case "default":
            case "protected":
                // Across the 3 basic contexts, protected behaves identically to default
                return ("SAME_CLASS".equalsIgnoreCase(accessorContext) ||
                        "SAME_PACKAGE".equalsIgnoreCase(accessorContext)) ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    /**
     * Summarizes a batch of access attempts.
     *
     * @param attempts 2D array where each row contains {fieldModifier, accessorContext}
     * @return Summary string in format "Allowed: X | Denied: Y"
     */
    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String result = classifyAccess(attempt[0], attempt[1]);
                    if ("ALLOWED".equals(result)) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: Single Access Checks ---");
        System.out.println(classifyAccess("private", "SAME_CLASS")); // ALLOWED
        System.out.println(classifyAccess("default", "DIFFERENT_PACKAGE")); // DENIED

        System.out.println("\n--- Test 2: Batch Summary ---");
        String[][] attempts = {
                {"protected", "SAME_PACKAGE"},
                {"protected", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeBatch(attempts)); // Allowed: 2 | Denied: 1
    }
}
