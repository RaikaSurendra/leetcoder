import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {
    
    private static SolutionGreedy solutionGreedy;
    private static SolutionOptimized solutionOptimized;
    
    @BeforeAll
    public static void setUp() {
        solutionGreedy = new SolutionGreedy();
        solutionOptimized = new SolutionOptimized();
    }
    
    @Test
    public void testExample1() {
        int[] apple = {1, 3, 2};
        int[] capacity = {4, 3, 1, 5, 2};
        assertEquals(2, solutionGreedy.minimumBoxes(apple, capacity));
        assertEquals(2, solutionOptimized.minimumBoxes(apple, capacity));
    }
    
    @Test
    public void testExample2() {
        int[] apple = {5, 5, 5};
        int[] capacity = {2, 4, 2, 7};
        assertEquals(4, solutionOptimized.minimumBoxes(apple, capacity));
        assertEquals(4, solutionGreedy.minimumBoxes(apple, capacity));
    }
    
    @Test
    public void testSingleBox() {
        int[] apple = {10};
        int[] capacity = {50};
        assertEquals(1, solutionGreedy.minimumBoxes(apple, capacity));
        assertEquals(1, solutionOptimized.minimumBoxes(apple, capacity));
    }
    
    @Test
    public void testMultipleSmallBoxes() {
        int[] apple = {10, 10, 10};
        int[] capacity = {5, 5, 5, 5, 5, 5};
        assertEquals(6, solutionGreedy.minimumBoxes(apple, capacity));
        assertEquals(6, solutionOptimized.minimumBoxes(apple, capacity));
    }
    
    @Test
    public void testExactFit() {
        int[] apple = {10, 20};
        int[] capacity = {15, 15};
        assertEquals(2, solutionGreedy.minimumBoxes(apple, capacity));
        assertEquals(2, solutionOptimized.minimumBoxes(apple, capacity));
    }
}
