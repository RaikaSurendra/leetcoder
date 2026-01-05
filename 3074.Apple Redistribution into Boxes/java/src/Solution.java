import java.util.Arrays;

public class Solution {
    
    /**
     * Greedy approach: Sort capacities in descending order and pick largest first
     * Time: O(n + m log m)
     * Space: O(1) extra (ignoring sort space)
     */
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }
        
        Arrays.sort(capacity);
        
        int used = 0;
        int current = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            current += capacity[i];
            used++;
            if (current >= totalApples) {
                return used;
            }
        }
        
        return used;
    }
    
    /**
     * Optimized approach: Use frequency counting to avoid sorting
     * Time: O(n + m + 50)
     * Space: O(50)
     */
    public int minimumBoxesOptimized(int[] apple, int[] capacity) {
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }
        
        int[] freq = new int[51];
        for (int c : capacity) {
            freq[c]++;
        }
        
        int used = 0;
        int current = 0;
        for (int c = 50; c >= 1; c--) {
            while (freq[c] > 0 && current < totalApples) {
                current += c;
                used++;
                freq[c]--;
            }
            if (current >= totalApples) {
                break;
            }
        }
        
        return used;
    }
}
