/**
 * LeetCode 3453: Separate Squares I - Approach 2 (Binary Search) ⭐ RECOMMENDED
 * 
 * Strategy: Use binary search on y-coordinate, leveraging monotonic property
 * 
 * Key Insight:
 * - As line height h increases, area below increases monotonically
 * - As line height h increases, area above decreases monotonically
 * - This monotonic property enables binary search!
 * 
 * Algorithm:
 * 1. Calculate total area and target (total_area / 2)
 * 2. Find y-range: [min(y), max(y+l)]
 * 3. Binary search for h where area_below(h) = target
 * 4. Each iteration: O(n) to calculate area
 * 5. ~20 iterations for precision 10^-6
 * 
 * Time Complexity: O(n log(range/ε)) ≈ O(n)
 * - Binary search: O(log(10^9 / 10^-6)) ≈ O(20) iterations
 * - Each iteration: O(n) to calculate area
 * - Total: O(20n) = O(n)
 * 
 * Space Complexity: O(1) - only constant extra space
 * 
 * Pros: Fast, simple, scales well for n = 50,000
 * Cons: Requires careful floating-point precision handling
 */
public class Solution2BinarySearch {
    
    private static final double EPSILON = 1e-6; // Precision requirement
    
    /**
     * Find minimum y-coordinate where area above = area below
     * 
     * @param squares array where squares[i] = [x, y, l]
     * @return y-coordinate of the dividing line
     */
    public double separateSquares(int[][] squares) {
        // Calculate total area
        double totalArea = 0;
        for (int[] square : squares) {
            long l = square[2];
            totalArea += l * l;
        }
        
        double target = totalArea / 2.0;
        
        // Find search bounds
        // low = minimum y-coordinate (bottom of lowest square)
        // high = maximum y-coordinate (top of highest square)
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;
        
        for (int[] square : squares) {
            int y = square[1];
            int l = square[2];
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }
        
        // Binary search for the line position
        // Invariant: area_below(low) < target <= area_below(high)
        while (high - low > EPSILON) {
            double mid = (low + high) / 2.0;
            double areaBelow = calculateAreaBelow(squares, mid);
            
            if (areaBelow < target) {
                // Need to move line higher to increase area below
                low = mid;
            } else {
                // Area below >= target, can potentially move lower
                high = mid;
            }
        }
        
        // Return the midpoint of converged interval
        return (low + high) / 2.0;
    }
    
    /**
     * Calculate total area of squares below a horizontal line at height h
     * 
     * For each square [x, y, l]:
     * - If h <= y: square entirely above line → area = 0
     * - If h >= y + l: square entirely below line → area = l²
     * - If y < h < y + l: square split → area = l × (h - y)
     * 
     * @param squares array of squares
     * @param h height of the horizontal line
     * @return total area below the line
     */
    private double calculateAreaBelow(int[][] squares, double h) {
        double total = 0;
        
        for (int[] square : squares) {
            int y = square[1];
            int l = square[2];
            
            if (h <= y) {
                // Square entirely above line
                // Contribution: 0
                continue;
            } else if (h >= y + l) {
                // Square entirely below line
                // Contribution: full area = l²
                total += (double) l * l;
            } else {
                // Square split by line (y < h < y + l)
                // Area below = width × height_below
                // height_below = h - y
                double heightBelow = h - y;
                total += l * heightBelow;
            }
        }
        
        return total;
    }
    
    // Test method
    public static void main(String[] args) {
        Solution2BinarySearch solution = new Solution2BinarySearch();
        
        // Example 1: [[0,0,1],[2,2,1]]
        // Two 1×1 squares, total area = 2
        // Target = 1, line should be at y = 1
        int[][] squares1 = {{0, 0, 1}, {2, 2, 1}};
        double result1 = solution.separateSquares(squares1);
        System.out.printf("Example 1: %.5f (Expected: 1.00000)%n", result1);
        
        // Example 2: [[0,0,2],[1,1,1]]
        // One 2×2 square (area 4) and one 1×1 square (area 1)
        // Total area = 5, target = 2.5
        // Expected: 1.16667
        int[][] squares2 = {{0, 0, 2}, {1, 1, 1}};
        double result2 = solution.separateSquares(squares2);
        System.out.printf("Example 2: %.5f (Expected: 1.16667)%n", result2);
        
        // Edge case: Single square
        // Square [0,0,2] has area 4, target 2
        // Line at y = 1 splits it equally
        int[][] squares3 = {{0, 0, 2}};
        double result3 = solution.separateSquares(squares3);
        System.out.printf("Single square: %.5f (Expected: 1.00000)%n", result3);
    }
}
