package data_structures.class_problems;

public class P2_WarehouseGrid {
    public static class Summary {
        public int total;
        public int[] maxCoordinate;
        
        public Summary(int total, int[] maxCoordinate) {
            this.total = total;
            this.maxCoordinate = maxCoordinate;
        }
        
        @Override
        public String toString() {
            return "total = " + total + ", maxCoordinate = (" + maxCoordinate[0] + ", " + maxCoordinate[1] + ")";
        }
    }

    /**
     * Scans a warehouse grid to find the total items and the bin with the most items.
     * 
     * Time Complexity: O(m * n) where m is the number of rows and n is the number of columns,
     *                  as we must inspect every bin exactly once.
     * Space Complexity: O(1) additional space (excluding the input grid and return object).
     * 
     * @param grid 2D array of bin counts
     * @return Summary object containing total and max coordinates
     */
    public static Summary warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new Summary(0, new int[]{-1, -1});
        }
        
        int total = 0;
        int maxItems = -1;
        int[] maxCoordinate = new int[]{-1, -1};
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];
                // Keep the first max encountered by using strictly greater >
                if (grid[i][j] > maxItems) {
                    maxItems = grid[i][j];
                    maxCoordinate[0] = i;
                    maxCoordinate[1] = j;
                }
            }
        }
        
        return new Summary(total, maxCoordinate);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };
        System.out.println("Sample 1: " + warehouseSummary(grid));
    }
}
