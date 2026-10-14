package dsa_sorting.class_problems;

import java.util.ArrayList;
import java.util.List;

public class P4_ProductPriceFinder {

    public static class RangeResult {
        public final int lowerBoundIndex;
        public final int upperBoundIndex; // index of first element > upper
        public final int count;
        public final List<Integer> matchingPrices;

        public RangeResult(int lowerBoundIndex, int upperBoundIndex, List<Integer> matchingPrices) {
            this.lowerBoundIndex = lowerBoundIndex;
            this.upperBoundIndex = upperBoundIndex;
            this.matchingPrices = matchingPrices;
            this.count = matchingPrices.size();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Lower bound index ").append(lowerBoundIndex).append("\n");
            sb.append("Upper bound index ").append(upperBoundIndex).append("\n");
            sb.append("Count ").append(count).append(" (products with prices ");
            for (int i = 0; i < matchingPrices.size(); i++) {
                sb.append(matchingPrices.get(i));
                if (i < matchingPrices.size() - 1) sb.append(", ");
            }
            sb.append(")");
            return sb.toString();
        }
    }

    /**
     * Finds first index with price >= low.
     */
    public static int lowerBound(int[] prices, int low) {
        int left = 0, right = prices.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prices[mid] >= low) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * Finds first index with price > high.
     */
    public static int upperBound(int[] prices, int high) {
        int left = 0, right = prices.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prices[mid] > high) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static RangeResult findPricesInRange(int[] prices, int low, int high) {
        int lb = lowerBound(prices, low);
        int ub = upperBound(prices, high);

        List<Integer> matched = new ArrayList<>();
        for (int i = lb; i < ub; i++) {
            matched.add(prices[i]);
        }

        return new RangeResult(lb, ub, matched);
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        RangeResult result = findPricesInRange(prices, 150, 300);
        System.out.println(result);
    }
}
