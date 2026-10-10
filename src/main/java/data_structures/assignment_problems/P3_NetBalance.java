package data_structures.assignment_problems;

import java.util.HashMap;
import java.util.Map;

public class P3_NetBalance {

    /**
     * Counts the number of contiguous periods whose net total equals exactly k.
     * 
     * Naive Approach Time Complexity: O(n^2) or O(n^3) if we calculate the sum of every possible 
     *                                 contiguous subarray explicitly.
     * 
     * Optimized Time Complexity: O(n) where n is the number of transactions, as we iterate through
     *                            the array exactly once and perform O(1) hash map operations.
     * Optimized Space Complexity: O(n) in the worst case if every prefix sum is unique, as we store 
     *                             them in a hash map.
     * 
     * @param transactions Array of daily net transactions
     * @param k Target net balance
     * @return Number of contiguous periods with sum equal to k
     */
    public static int countPeriods(int[] transactions, int k) {
        if (transactions == null || transactions.length == 0) return 0;
        
        // Map stores (prefix_sum -> count of occurrences)
        Map<Integer, Integer> prefixSumCounts = new HashMap<>();
        
        // Base case: to handle subarrays that start from index 0
        prefixSumCounts.put(0, 1);
        
        int currentPrefixSum = 0;
        int count = 0;
        
        for (int tx : transactions) {
            currentPrefixSum += tx;
            
            // If currentPrefixSum - k exists in the map, it means there are that many 
            // subarrays ending at the current index which sum to k.
            int requiredPrefix = currentPrefixSum - k;
            if (prefixSumCounts.containsKey(requiredPrefix)) {
                count += prefixSumCounts.get(requiredPrefix);
            }
            
            // Add the current prefix sum to the map for future checks
            prefixSumCounts.put(currentPrefixSum, prefixSumCounts.getOrDefault(currentPrefixSum, 0) + 1);
        }
        
        return count;
    }

    public static void main(String[] args) {
        int[] tx1 = {3, 4, -7, 1, 3, 3, 1, -4};
        System.out.println("Sample 1: " + countPeriods(tx1, 7)); // 4
        
        int[] tx2 = {1, 2, 3};
        System.out.println("Sample 2: " + countPeriods(tx2, 10)); // 0
    }
}
