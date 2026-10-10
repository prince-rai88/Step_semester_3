package data_structures.assignment_problems;

import java.util.ArrayList;
import java.util.List;

public class P1_MallFootfall {

    /**
     * Calculates the total footfall for multiple ranges using a prefix sum array.
     * 
     * Naive Approach Time Complexity: O(q * n) where q is the number of queries and n is the size of the array,
     *                                 as we would iterate through the array for each query.
     * 
     * Optimized Time Complexity: O(n + q). O(n) for the one-time preprocessing (building prefix sum),
     *                            and O(1) for each of the q queries.
     * Optimized Space Complexity: O(n) for the prefix sum array.
     * 
     * The preprocessing cost is worth paying when q is large (many queries), as the O(1) query time
     * quickly offsets the initial O(n) setup time.
     * 
     * @param visitors Array of visitor counts per hour
     * @param queries List of start and end indices
     * @return List of total visitors for each query
     */
    public static List<Integer> footfallReport(int[] visitors, int[][] queries) {
        if (visitors == null || visitors.length == 0) return new ArrayList<>();
        
        // Build prefix sum array
        int[] prefixSum = new int[visitors.length];
        prefixSum[0] = visitors[0];
        for (int i = 1; i < visitors.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + visitors[i];
        }
        
        List<Integer> results = new ArrayList<>();
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];
            
            if (start == 0) {
                results.add(prefixSum[end]);
            } else {
                results.add(prefixSum[end] - prefixSum[start - 1]);
            }
        }
        
        return results;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {
            {0, 2},
            {2, 5},
            {4, 6},
            {3, 3}
        };
        System.out.println("Sample 1: " + footfallReport(visitors, queries)); // [22, 31, 27, 9]
    }
}
