package constructors_and_keywords.assignment_problems.p3_canteen;

public class Canteen implements Comparable<Canteen> {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    @Override
    public int compareTo(Canteen other) {
        int scoreDiff = Integer.compare(other.trustScore, this.trustScore);
        if (scoreDiff != 0) return scoreDiff;
        
        int codeDiff = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeDiff != 0) return codeDiff;
        
        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        for (int i = 0; i < canteens.length; i++) {
            for (int j = i + 1; j < canteens.length; j++) {
                if (canteens[i].compareTo(canteens[j]) > 0) {
                    Canteen temp = canteens[i];
                    canteens[i] = canteens[j];
                    canteens[j] = temp;
                }
            }
        }
        return canteens;
    }
}
