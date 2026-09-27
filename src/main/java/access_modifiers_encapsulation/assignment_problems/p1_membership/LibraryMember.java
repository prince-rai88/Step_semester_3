package access_modifiers_encapsulation.assignment_problems.p1_membership;

public class LibraryMember {
    private String membershipId;
    String branchCode; 
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().isEmpty() || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if ("public".equals(fieldModifier)) return "ALLOWED";
        if ("private".equals(fieldModifier)) {
            return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
        }
        if ("default".equals(fieldModifier)) {
            return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
        }
        if ("protected".equals(fieldModifier)) {
            return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
        }
        return "DENIED";
    }

    public static String summarizeByModifier(String[][] attempts) {
        int[] priv = new int[2]; 
        int[] def = new int[2];
        int[] prot = new int[2];
        int[] pub = new int[2];

        for (String[] attempt : attempts) {
            String mod = attempt[0];
            String ctx = attempt[1];
            String result = classifyAccess(mod, ctx);
            boolean allowed = "ALLOWED".equals(result);
            switch (mod) {
                case "private": if (allowed) priv[0]++; else priv[1]++; break;
                case "default": if (allowed) def[0]++; else def[1]++; break;
                case "protected": if (allowed) prot[0]++; else prot[1]++; break;
                case "public": if (allowed) pub[0]++; else pub[1]++; break;
            }
        }
        return String.format("private: %d allowed / %d denied | default: %d allowed / %d denied | protected: %d allowed / %d denied | public: %d allowed / %d denied",
                priv[0], priv[1], def[0], def[1], prot[0], prot[1], pub[0], pub[1]);
    }
}
