package session1.assignment_problems.p4_inventory_balancer;

public class InventoryBalancer {
    public void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA == null || sectionB == null || sectionA.length != sectionB.length) return;
        
        int totalA = 0;
        int totalB = 0;
        int highest = -1;
        String highestSection = "";
        int highestIndex = -1;
        
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                highestSection = "Section A";
                highestIndex = i + 1;
            }
        }
        
        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
            if (sectionB[i] > highest) {
                highest = sectionB[i];
                highestSection = "Section B";
                highestIndex = i + 1;
            }
        }
        
        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";
        
        System.out.printf("Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)%n", 
            totalA, totalB, status, highest, highestSection, highestIndex);
    }
}
