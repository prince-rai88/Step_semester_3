package access_modifiers_encapsulation.class_problems.F2_CrossPackage;

public class AccessRuleEngine {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        String modifier = fieldModifier.toLowerCase().trim();
        String context = accessorContext.toUpperCase().trim();

        switch (modifier) {
            case "private":
                return "SAME_CLASS".equals(context) ? "ALLOWED" : "DENIED";

            case "default":
                return ("SAME_CLASS".equals(context) || "SAME_PACKAGE".equals(context)) ? "ALLOWED" : "DENIED";

            case "protected":
                switch (context) {
                    case "SAME_CLASS":
                    case "SAME_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                        return "ALLOWED";
                    case "DIFFERENT_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                        return "DENIED";
                    default:
                        return "DENIED";
                }

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }

        String[] tokens = accessorContext.trim().split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (!token.isEmpty()) {
                sb.append(Character.toUpperCase(token.charAt(0)));
                if (token.length() > 1) {
                    sb.append(token.substring(1).toLowerCase());
                }
                if (i < tokens.length - 1) {
                    sb.append(" ");
                }
            }
        }

        return sb.toString();
    }

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
        System.out.println("--- Test 1: Protected Cross-Package Subclass Checks ---");
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        System.out.println("\n--- Test 2: describeContext Formatting ---");
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        System.out.println("\n--- Test 3: Other Modifiers in Subclass Contexts ---");
        System.out.println("private in subclass own type: " +
                classifyAccess("private", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println("default in subclass own type: " +
                classifyAccess("default", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println("public in subclass parent type: " +
                classifyAccess("public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
