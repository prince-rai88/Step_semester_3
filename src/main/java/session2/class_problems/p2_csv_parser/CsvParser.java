package session2.class_problems.p2_csv_parser;

public class CsvParser {
    public void parseStudentRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.printf("Name: %s | Roll No: %s | Dept: %s%n", fields[0], fields[1], fields[2]);
        }
    }
}
