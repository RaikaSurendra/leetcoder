/**
 * Maximum Matrix Sum - Optimized Solution
 * 
 * Problem: Maximize the sum of an n×n matrix by flipping adjacent elements' signs.
 * 
 * Key Insight:
 * - Count negative numbers in the matrix
 * - If even count: Can make all positive → return sum of absolute values
 * - If odd count: Must keep one negative → keep smallest absolute value negative
 * 
 * Algorithm:
 * 1. Calculate sum of all absolute values
 * 2. Count negative numbers
 * 3. Track minimum absolute value
 * 4. If odd negatives: subtract 2×minAbs (correct for the one that must stay negative)
 * 
 * Time Complexity: O(n²) - single pass through matrix
 * Space Complexity: O(1) - only constant extra variables
 */
public class Solution {
    
    /**
     * Finds the maximum sum of matrix elements after optimal flip operations.
     * 
     * @param matrix n×n integer matrix
     * @return maximum possible sum after flipping operations
     */
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        long totalSum = 0;
        int negCount = 0;
        int minAbs = Integer.MAX_VALUE;
        
        // Single pass: calculate sum of absolute values, count negatives, find min
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                int absValue = Math.abs(value);
                
                totalSum += absValue;
                
                if (value < 0) {
                    negCount++;
                }
                
                minAbs = Math.min(minAbs, absValue);
            }
        }
        
        // If odd number of negatives, one must remain negative
        // Choose the smallest absolute value to minimize loss
        if (negCount % 2 == 1) {
            totalSum -= 2L * minAbs;
        }
        
        return totalSum;
    }
}
