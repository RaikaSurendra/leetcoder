import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {
    
    @Test
    public void testExample1() {
        Solution solution = new Solution();
        int[] apple = {1, 3, 2};
        int[] capacity = {4, 3, 1, 5, 2};
        assertEquals(2, solution.minimumBoxes(apple, capacity));
        assertEquals(2, solution.minimumBoxesOptimized(apple, capacity));
    }
    
    @Test
    public void testExample2() {
        Solution solution = new Solution();
        int[] apple = {5, 5, 5};
        int[] capacity = {2, 4, 2, 7};
        assertEquals(4, solution.minimumBoxesOptimized(apple, capacity));
        assertEquals(4, solution.minimumBoxes(apple, capacity));
    }
    
    @Test
    public void testSingleBox() {
        Solution solution = new Solution();
        int[] apple = {10};
        int[] capacity = {50};
        assertEquals(1, solution.minimumBoxes(apple, capacity));
        assertEquals(1, solution.minimumBoxesOptimized(apple, capacity));
    }
    
    @Test
    public void testMultipleSmallBoxes() {
        Solution solution = new Solution();
        int[] apple = {10, 10, 10};
        int[] capacity = {5, 5, 5, 5, 5, 5};
        assertEquals(6, solution.minimumBoxes(apple, capacity));
        assertEquals(6, solution.minimumBoxesOptimized(apple, capacity));
    }
    
    @Test
    public void testExactFit() {
        Solution solution = new Solution();
        int[] apple = {10, 20};
        int[] capacity = {15, 15};
        assertEquals(2, solution.minimumBoxes(apple, capacity));
        assertEquals(2, solution.minimumBoxesOptimized(apple, capacity));
    }
}
