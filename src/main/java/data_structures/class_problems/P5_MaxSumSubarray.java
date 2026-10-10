package data_structures.class_problems;

public class P5_MaxSumSubarray {

    /**
     * Finds the maximum sum of any contiguous subarray of size k.
     * 
     * Naive Approach: Calculate sum of every possible k-sized window from scratch.
     * Naive Time Complexity: O(n * k) where n is the length of sales array.
     * 
     * Optimized Approach: Sliding window. Instead of recalculating, we subtract the element leaving 
     * the window and add the element entering the window.
     * Optimized Time Complexity: O(n) as each element is processed at most twice.
     * Optimized Space Complexity: O(1) as we only use a few integer variables.
     * 
     * @param sales Array of daily sales
     * @param k Size of the window
     * @return The maximum sum
     */
    public static int maxSumSubarray(int[] sales, int k) {
        if (sales == null || sales.length == 0 || k <= 0 || k > sales.length) {
            return 0; // Or throw IllegalArgumentException
        }

        int maxSum = 0;
        int currentWindowSum = 0;

        // Calculate sum of the first window
        for (int i = 0; i < k; i++) {
            currentWindowSum += sales[i];
        }
        maxSum = currentWindowSum;

        // Slide the window
        for (int i = k; i < sales.length; i++) {
            currentWindowSum += sales[i] - sales[i - k];
            if (currentWindowSum > maxSum) {
                maxSum = currentWindowSum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("Sample 1: " + maxSumSubarray(new int[]{2, 1, 5, 1, 3, 2}, 3)); // 9
    }
}
