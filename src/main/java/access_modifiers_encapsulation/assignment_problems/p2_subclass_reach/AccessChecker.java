package access_modifiers_encapsulation.assignment_problems.p2_subclass_reach;

public class AccessChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("public".equals(fieldModifier)) return "ALLOWED";
        if ("private".equals(fieldModifier)) {
            return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
        }
        if ("default".equals(fieldModifier)) {
            return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
        }
        if ("protected".equals(fieldModifier)) {
            if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext) || "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                return "ALLOWED";
            }
            return "DENIED";
        }
        return "DENIED";
    }

    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.isEmpty()) return "";
        String[] parts = accessorContext.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].toLowerCase();
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
            if (i < parts.length - 1) sb.append(" ");
        }
        return sb.toString();
    }
}
