package session7.assignment_problems.p2_data_export;

public class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportSystem.incrementCounter();
        return "Exported profile: " + username;
    }
}
