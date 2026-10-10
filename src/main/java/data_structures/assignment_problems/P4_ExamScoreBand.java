package data_structures.assignment_problems;

public class P4_ExamScoreBand {

    /**
     * Counts the number of students who scored within the [low, high] band inclusive.
     * 
     * Naive Approach Time Complexity: O(n) where n is the number of scores (Linear scan).
     * Naive Space Complexity: O(1).
     * 
     * Optimized Time Complexity: O(log n) as we perform two binary searches on the sorted array.
     * Optimized Space Complexity: O(1) as we only use a few pointer variables.
     * 
     * Duplicates at the edges affect the search because standard binary search just finds *any* 
     * occurrence. We must specifically find the *first* occurrence of a score >= low, and the 
     * *first* occurrence of a score > high to correctly calculate the count as (rightIndex - leftIndex).
     * 
     * @param scores Array of student scores, sorted in ascending order
     * @param low The minimum score in the band
     * @param high The maximum score in the band
     * @return Number of scores within the band
     */
    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null || scores.length == 0 || low > high) {
            return 0;
        }

        // Find the index of the first element >= low
        int startIndex = findFirstGreaterOrEqual(scores, low);
        
        // Find the index of the first element > high
        int endIndex = findFirstGreater(scores, high);
        
        return endIndex - startIndex;
    }

    private static int findFirstGreaterOrEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int findFirstGreater(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println("Sample 1: " + countInBand(scores, 42, 58)); // 6
        System.out.println("Sample 2: " + countInBand(scores, 90, 100)); // 0
    }
}
