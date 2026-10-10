package abstraction_and_interface.assignment_problems.p2_data_export;

public class ExportSystem {
    private static int totalExports = 0;

    public static void incrementCounter() {
        totalExports++;
    }

    public static int getTotalExports() {
        return totalExports;
    }

    public static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }
}
