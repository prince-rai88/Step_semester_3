package constructors.assignment_problems;

public class Canteen {
    String canteenCode, canteenName;
    int trustScore;

    Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    int compareTo(Canteen other) {

        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);

        if (codeCompare != 0) {
            return codeCompare;
        }

        return this.canteenName.length() - other.canteenName.length();
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {

        Canteen[] result = canteens.clone();

        for (int i = 0; i < result.length - 1; i++) {

            int best = i;

            for (int j = i + 1; j < result.length; j++) {
                if (result[j].compareTo(result[best]) < 0) {
                    best = j;
                }
            }

            Canteen temp = result[i];
            result[i] = result[best];
            result[best] = temp;
        }

        return result;
    }

    public static void main(String[] args) {

        Canteen[] canteens = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);

        for (Canteen canteen : ranked) {
            System.out.println(canteen.canteenCode);
        }
    }
}