import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for Optimized Maximum Matrix Sum solution
 * Verifies correctness and compares with standard solution
 */
public class SolutionOptimizedTest {
    
    private final SolutionOptimized solution = new SolutionOptimized();
    
    @Test
    public void testExample1() {
        int[][] matrix = {{1, -1}, {-1, 1}};
        long expected = 4L;
        long actual = solution.maxMatrixSum(matrix);
        assertEquals(expected, actual, "Example 1: [[1,-1],[-1,1]] should return 4");
    }
    
    @Test
    public void testExample2() {
        int[][] matrix = {{1, 2, 3}, {-1, -2, -3}, {1, 2, 3}};
        long expected = 16L;
        long actual = solution.maxMatrixSum(matrix);
        assertEquals(expected, actual, "Example 2: [[1,2,3],[-1,-2,-3],[1,2,3]] should return 16");
    }
    
    @Test
    public void testAllPositive() {
        int[][] matrix = {{1, 2}, {3, 4}};
        long expected = 10L;
        long actual = solution.maxMatrixSum(matrix);
        assertEquals(expected, actual, "All positive: should return sum of all elements");
    }
    
    @Test
    public void testAllNegativeEven() {
        int[][] matrix = {{-1, -2}, {-3, -4}};
        long expected = 10L;
        long actual = solution.maxMatrixSum(matrix);
        assertEquals(expected, actual, "All negative (even count): should make all positive");
    }
    
    @Test
    public void testOddNegativeCount() {
        int[][] matrix = {{-1, 2}, {3, 4}};
        long expected = 8L;
        long actual = solution.maxMatrixSum(matrix);
        assertEquals(expected, actual, "Odd negative count: smallest abs should remain negative");
    }
    
    @Test
    public void testWithZero() {
        int[][] matrix = {{0, -1}, {2, 3}};
        long expected = 6L;
        long actual = solution.maxMatrixSum(matrix);
        assertEquals(expected, actual, "Matrix with zero: zero has smallest abs value");
    }
    
    @Test
    public void testSingleNegative() {
        int[][] matrix = {{-5, 10}, {15, 20}};
        long expected = 40L;
        long actual = solution.maxMatrixSum(matrix);
        assertEquals(expected, actual, "Single negative: should remain negative");
    }
    
    @Test
    public void testLargeValues() {
        int[][] matrix = {{-100000, 100000}, {100000, -100000}};
        long expected = 400000L;
        long actual = solution.maxMatrixSum(matrix);
        assertEquals(expected, actual, "Large values: should handle max constraint");
    }
}
