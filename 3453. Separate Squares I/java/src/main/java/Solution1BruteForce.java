import java.util.*;

/**
 * LeetCode 3453: Separate Squares I - Approach 1 (Brute Force/Event-Based)
 * 
 * Strategy: Sweep line through all critical y-coordinates where squares start/end.
 * 
 * Algorithm:
 * 1. Collect all critical y-values (bottom and top of each square)
 * 2. Calculate total area and target (total_area / 2)
 * 3. For each critical y-value in sorted order:
 *    - Calculate area below that y-value
 *    - If area >= target, interpolate to find exact y
 * 
 * Time Complexity: O(n²)
 * - Collecting critical points: O(n)
 * - Sorting: O(n log n)
 * - For each critical point O(n), calculate area O(n): O(n²)
 * 
 * Space Complexity: O(n) for storing critical points
 * 
 * Pros: Simple to understand and implement
 * Cons: Too slow for n = 50,000 (may TLE)
 */
public class Solution1BruteForce {
    
    /**
     * Find minimum y-coordinate where area above = area below
     * 
     * @param squares array where squares[i] = [x, y, l]
     * @return y-coordinate of the dividing line
     */
    public double separateSquares(int[][] squares) {
        int n = squares.length;
        
        // Calculate total area
        double totalArea = 0;
        for (int[] square : squares) {
            long l = square[2];
            totalArea += l * l;
        }
        
        double target = totalArea / 2.0;
        
        // Collect all critical y-coordinates
        Set<Double> criticalYSet = new HashSet<>();
        for (int[] square : squares) {
            int y = square[1];
            int l = square[2];
            criticalYSet.add((double) y);        // Bottom of square
            criticalYSet.add((double) (y + l));  // Top of square
        }
        
        // Sort critical y-values
        List<Double> criticalY = new ArrayList<>(criticalYSet);
        Collections.sort(criticalY);
        
        // Sweep through critical y-values
        double prevY = criticalY.get(0);
        double prevArea = calculateAreaBelow(squares, prevY);
        
        // If first critical point already exceeds target, answer is at first point
        if (prevArea >= target) {
            return prevY;
        }
        
        for (int i = 1; i < criticalY.size(); i++) {
            double currY = criticalY.get(i);
            double currArea = calculateAreaBelow(squares, currY);
            
            // Found the interval where area crosses target
            if (currArea >= target) {
                // Linear interpolation between prevY and currY
                // Area grows linearly within this interval
                return interpolate(squares, prevY, currY, prevArea, currArea, target);
            }
            
            prevY = currY;
            prevArea = currArea;
        }
        
        // Should not reach here if input is valid
        return criticalY.get(criticalY.size() - 1);
    }
    
    /**
     * Calculate total area of squares below a horizontal line at height h
     * 
     * @param squares array of squares
     * @param h height of the horizontal line
     * @return total area below the line
     */
    private double calculateAreaBelow(int[][] squares, double h) {
        double total = 0;
        
        for (int[] square : squares) {
            int x = square[0];
            int y = square[1];
            int l = square[2];
            
            if (h <= y) {
                // Square entirely above line: contributes 0
                continue;
            } else if (h >= y + l) {
                // Square entirely below line: contributes full area
                total += (double) l * l;
            } else {
                // Square split by line
                // Area below = width * height_below
                double heightBelow = h - y;
                total += l * heightBelow;
            }
        }
        
        return total;
    }
    
    /**
     * Interpolate to find exact y where area equals target
     * Between prevY and currY, area grows linearly
     * 
     * @param squares array of squares
     * @param prevY previous critical y
     * @param currY current critical y
     * @param prevArea area at prevY
     * @param currArea area at currY
     * @param target target area
     * @return interpolated y-coordinate
     */
    private double interpolate(int[][] squares, double prevY, double currY, 
                                double prevArea, double currArea, double target) {
        // Between prevY and currY, find the rate of area change
        // We can do binary search in this small interval for precision
        
        double low = prevY;
        double high = currY;
        double epsilon = 1e-7;
        
        while (high - low > epsilon) {
            double mid = (low + high) / 2.0;
            double midArea = calculateAreaBelow(squares, mid);
            
            if (midArea < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        
        return (low + high) / 2.0;
    }
    
    // Test method
    public static void main(String[] args) {
        Solution1BruteForce solution = new Solution1BruteForce();
        
        // Example 1: [[0,0,1],[2,2,1]]
        int[][] squares1 = {{0, 0, 1}, {2, 2, 1}};
        System.out.println("Example 1: " + solution.separateSquares(squares1));
        // Expected: 1.00000
        
        // Example 2: [[0,0,2],[1,1,1]]
        int[][] squares2 = {{0, 0, 2}, {1, 1, 1}};
        System.out.println("Example 2: " + solution.separateSquares(squares2));
        // Expected: 1.16667
    }
}
