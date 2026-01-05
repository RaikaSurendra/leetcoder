import java.util.Arrays;

/**
 * Greedy Solution using Sorting
 * 
 * Problem: Find minimum number of boxes needed to redistribute all apples.
 * Approach: Since apples can be split across boxes, only total count matters.
 *           Greedily select largest capacity boxes first to minimize count.
 */
public class SolutionGreedy {
    
    /**
     * Finds minimum boxes needed using greedy sorting approach.
     * 
     * Algorithm:
     * 1. Calculate total number of apples to pack
     * 2. Sort capacities in ascending order (Java's Arrays.sort)
     * 3. Iterate from largest capacity (end of sorted array) backwards
     * 4. Keep adding boxes until all apples are packed
     * 
     * Intuition:
     * - Apples can be split, so we only care about total capacity needed
     * - Larger boxes pack more apples per box → fewer boxes needed
     * - Greedy choice: always pick largest available capacity
     * 
     * Time Complexity: O(n + m log m)
     *   - O(n) to sum apple array
     *   - O(m log m) to sort capacity array
     *   - O(m) worst case to iterate through capacities
     * 
     * Space Complexity: O(1) extra space
     *   - Sorting is done in-place (ignoring sort's internal stack)
     *   - Only constant extra variables used
     * 
     * Example:
     *   apple = [1, 3, 2], capacity = [4, 3, 1, 5, 2]
     *   totalApples = 6
     *   sorted capacity = [1, 2, 3, 4, 5]
     *   Start from end: 5 → current=5, 4 → current=9 (≥6, done!)
     *   Return 2 boxes
     * 
     * @param apple array where apple[i] is apples in pack i
     * @param capacity array where capacity[i] is max capacity of box i
     * @return minimum number of boxes needed
     */
    public int minimumBoxes(int[] apple, int[] capacity) {
        // Step 1: Calculate total apples to pack
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }
        
        // Step 2: Sort capacities (ascending order)
        Arrays.sort(capacity);
        
        // Step 3: Greedily pick boxes from largest to smallest
        int used = 0;
        int current = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            current += capacity[i];
            used++;
            if (current >= totalApples) {
                return used;
            }
        }
        
        return used; // All boxes used (shouldn't reach here per problem constraints)
    }
}
