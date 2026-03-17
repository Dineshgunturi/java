import java.util.Arrays;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;

        // Step 1: Update the matrix to store consecutive heights of 1s
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        // Step 2: For each row, sort heights and calculate potential area
        for (int i = 0; i < m; i++) {
            int[] currentRow = matrix[i];
            Arrays.sort(currentRow);

            // Step 3: Iterate backwards through sorted heights
            // j is the height, and (n - k) is the width
            for (int k = n - 1; k >= 0; k--) {
                int height = currentRow[k];
                int width = n - k;
                if (height == 0) break; // Optimization: no more 1s in this row
                
                maxArea = Math.max(maxArea, height * width);
            }
        }

        return maxArea;
    }
}
