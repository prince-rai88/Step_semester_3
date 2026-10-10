package data_structures.assignment_problems;

import java.util.ArrayList;
import java.util.List;

public class P5_SpiralAudit {

    /**
     * Returns the list of bin IDs in the order a robot visits them in a clockwise spiral.
     * 
     * Time Complexity: O(m * n) where m is the number of rows and n is the number of columns,
     *                  as each bin in the grid is visited exactly once.
     * Space Complexity: O(1) additional space excluding the output list, as we only maintain
     *                   four boundary pointers (top, bottom, left, right).
     * 
     * @param grid 2D array of bin IDs
     * @return List of bin IDs in spiral order
     */
    public static List<Integer> auditRoute(int[][] grid) {
        List<Integer> result = new ArrayList<>();
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return result;
        }

        int top = 0;
        int bottom = grid.length - 1;
        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse Top Row
            for (int i = left; i <= right; i++) {
                result.add(grid[top][i]);
            }
            top++;

            // Traverse Right Column
            for (int i = top; i <= bottom; i++) {
                result.add(grid[i][right]);
            }
            right--;

            // Check if bounds crossed before traversing Bottom Row
            if (top <= bottom) {
                // Traverse Bottom Row
                for (int i = right; i >= left; i--) {
                    result.add(grid[bottom][i]);
                }
                bottom--;
            }

            // Check if bounds crossed before traversing Left Column
            if (left <= right) {
                // Traverse Left Column
                for (int i = bottom; i >= top; i--) {
                    result.add(grid[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        System.out.println("Sample 1: " + auditRoute(grid));
        // Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }
}
