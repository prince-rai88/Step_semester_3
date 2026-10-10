package data_structures.class_problems;

public class P1_PairSum {
    /**
     * Finds a pair of distinct elements in a sorted array that sum to a target.
     * 
     * Time Complexity: O(n) where n is the number of elements in the array.
     * Space Complexity: O(1) as we only use two pointer variables.
     * 
     * A naive approach ignoring the sorted property would take O(n^2) time to check all pairs.
     * Using the sorted property allows us to shrink the search space optimally.
     * 
     * @param nums Sorted array of integers
     * @param target The target sum
     * @return A string representing the pair or "Not Found"
     */
    public static String pairSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return "(" + nums[left] + ", " + nums[right] + ")";
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return "Not Found";
    }

    public static void main(String[] args) {
        System.out.println("Sample 1: " + pairSumSorted(new int[]{-4, -1, 0, 3, 5, 9}, 4));
        System.out.println("Sample 2: " + pairSumSorted(new int[]{1, 2, 3}, 100));
    }
}
