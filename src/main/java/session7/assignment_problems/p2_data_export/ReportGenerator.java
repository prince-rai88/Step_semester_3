package session7.assignment_problems.p2_data_export;

public class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportSystem.incrementCounter();
        return "Exported report: " + reportName;
    }
}
