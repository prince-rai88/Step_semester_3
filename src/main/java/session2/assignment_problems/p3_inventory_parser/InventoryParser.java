package session2.assignment_problems.p3_inventory_parser;

public class InventoryParser {
    public void parseInventoryRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.printf("Product: %s | SKU: %s | Qty: %s%n", fields[0], fields[1], fields[2]);
        }
    }
}
