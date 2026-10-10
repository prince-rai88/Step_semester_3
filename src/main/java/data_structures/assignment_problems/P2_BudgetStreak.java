package data_structures.assignment_problems;

public class P2_BudgetStreak {
    
    public static class Result {
        public int length;
        public int startIndex;
        
        public Result(int length, int startIndex) {
            this.length = length;
            this.startIndex = startIndex;
        }
        
        @Override
        public String toString() {
            return "(" + length + ", " + startIndex + ")";
        }
    }

    /**
     * Finds the longest contiguous run of days whose total cost is <= budget.
     * 
     * Naive Approach Time Complexity: O(n^2) or O(n^3) if we check the sum of every possible run from scratch.
     * 
     * Optimized Time Complexity: O(n) where n is the number of days. The left pointer and right pointer
     *                            each traverse the array at most once (sliding window).
     * Space Complexity: O(1) as we only keep track of a few state variables (pointers and sums).
     * 
     * The fact that costs are non-negative is essential because it guarantees that adding a day will never 
     * decrease the total cost, and removing a day from the left will never increase the total cost, enabling 
     * the sliding window logic to work deterministically.
     * 
     * @param costs Array of daily costs
     * @param budget The maximum total budget allowed
     * @return Result object containing the length and start index
     */
    public static Result longestStreak(int[] costs, int budget) {
        int maxLength = 0;
        int bestStart = -1;
        
        int currentSum = 0;
        int left = 0;
        
        for (int right = 0; right < costs.length; right++) {
            currentSum += costs[right];
            
            // Shrink window if it exceeds budget
            while (currentSum > budget && left <= right) {
                currentSum -= costs[left];
                left++;
            }
            
            int currentLength = right - left + 1;
            // Update max if this is strictly longer. 
            // This ensures we keep the first occurrence of the max length.
            if (currentLength > maxLength) {
                maxLength = currentLength;
                bestStart = left;
            }
        }
        
        if (maxLength == 0) {
            return new Result(0, -1);
        }
        
        return new Result(maxLength, bestStart);
    }

    public static void main(String[] args) {
        int[] costs1 = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        System.out.println("Sample 1: " + longestStreak(costs1, 8)); // (4, 4)
        
        int[] costs2 = {9, 10};
        System.out.println("Sample 2: " + longestStreak(costs2, 8)); // (0, -1)
    }
}
