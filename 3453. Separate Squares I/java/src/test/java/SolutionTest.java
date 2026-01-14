import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for all 3 solutions
 */
public class SolutionTest {
    
    private static final double EPSILON = 1e-5;
    
    private final Solution1BruteForce solution1 = new Solution1BruteForce();
    private final Solution2BinarySearch solution2 = new Solution2BinarySearch();
    private final Solution3Mathematical solution3 = new Solution3Mathematical();
    
    @Test
    public void testExample1_AllSolutions() {
        int[][] squares = {{0, 0, 1}, {2, 2, 1}};
        double expected = 1.00000;
        
        assertEquals(expected, solution1.separateSquares(squares), EPSILON, "Solution 1 failed");
        assertEquals(expected, solution2.separateSquares(squares), EPSILON, "Solution 2 failed");
        assertEquals(expected, solution3.separateSquares(squares), EPSILON, "Solution 3 failed");
    }
    
    @Test
    public void testExample2_AllSolutions() {
        int[][] squares = {{0, 0, 2}, {1, 1, 1}};
        double expected = 1.16667;
        
        assertEquals(expected, solution1.separateSquares(squares), EPSILON, "Solution 1 failed");
        assertEquals(expected, solution2.separateSquares(squares), EPSILON, "Solution 2 failed");
        assertEquals(expected, solution3.separateSquares(squares), EPSILON, "Solution 3 failed");
    }
    
    @Test
    public void testSingleSquare_AllSolutions() {
        int[][] squares = {{0, 0, 2}};
        double expected = 1.00000;
        
        assertEquals(expected, solution1.separateSquares(squares), EPSILON, "Solution 1 failed");
        assertEquals(expected, solution2.separateSquares(squares), EPSILON, "Solution 2 failed");
        assertEquals(expected, solution3.separateSquares(squares), EPSILON, "Solution 3 failed");
    }
    
    @Test
    public void testLargeSquare_AllSolutions() {
        int[][] squares = {{0, 0, 100}};
        double expected = 50.00000;
        
        assertEquals(expected, solution1.separateSquares(squares), EPSILON, "Solution 1 failed");
        assertEquals(expected, solution2.separateSquares(squares), EPSILON, "Solution 2 failed");
        assertEquals(expected, solution3.separateSquares(squares), EPSILON, "Solution 3 failed");
    }
    
    @Test
    public void testMultipleNonOverlapping_AllSolutions() {
        int[][] squares = {{0, 0, 1}, {0, 2, 1}, {0, 4, 1}};
        double expected = 2.50000; // Total area = 3, target = 1.5, line at y=2.5
        
        assertEquals(expected, solution1.separateSquares(squares), EPSILON, "Solution 1 failed");
        assertEquals(expected, solution2.separateSquares(squares), EPSILON, "Solution 2 failed");
        assertEquals(expected, solution3.separateSquares(squares), EPSILON, "Solution 3 failed");
    }
}
