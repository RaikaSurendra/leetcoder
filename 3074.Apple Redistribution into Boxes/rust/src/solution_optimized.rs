/// Optimized Solution using Frequency Counting (No Sorting!)
/// 
/// Problem: Find minimum number of boxes needed to redistribute all apples.
/// Approach: Exploit constraint that capacity ≤ 50 to avoid sorting overhead.
///           Use frequency array to count boxes of each capacity value.
/// 
/// # Why Faster?
/// - Avoids O(m log m) sorting cost
/// - Uses O(50) fixed iteration instead of O(m) comparisons
/// - Takes multiple boxes of same capacity at once (batch processing)
/// - Zero heap allocations during main loop (uses stack-allocated array)
pub struct SolutionOptimized;

impl SolutionOptimized {
    /// Finds minimum boxes needed using frequency counting optimization.
    /// 
    /// # Algorithm
    /// 1. Calculate total apples to pack
    /// 2. Build frequency array: freq[c] = count of boxes with capacity c
    /// 3. Track maximum capacity to avoid iterating unused slots
    /// 4. Iterate from max capacity down to 1:
    ///    - Calculate exact boxes needed using ceiling division
    ///    - Take minimum of (needed, available)
    ///    - Multiply capacity by boxes taken (batch processing)
    /// 
    /// # Key Optimization - Ceiling Division
    /// To find boxes needed: ceil(remaining / capacity)
    /// Using integer math: (remaining + capacity - 1) / capacity
    /// 
    /// Example: remaining=10, capacity=3
    /// - ceil(10/3) = 4
    /// - (10+3-1)/3 = 12/3 = 4 ✓
    /// 
    /// # Key Optimization - Batch Processing
    /// Instead of: take 1 box → check → take 1 box → check...
    /// We calculate: need 3 boxes of capacity 5 → take all 3 at once
    /// This reduces iterations and improves branch prediction
    /// 
    /// # Complexity
    /// - **Time:** O(n + m + 50) ≈ O(n + m)
    ///   - O(n) to sum apple vector
    ///   - O(m) to build frequency array
    ///   - O(50) to iterate capacities 1-50 (constant, not dependent on m!)
    ///   - Much faster than O(m log m) when m > 50
    /// 
    /// - **Space:** O(50) = O(1) constant space
    ///   - Fixed-size frequency vector of 51 elements
    ///   - Only constant extra variables
    /// 
    /// # Example
    /// ```
    /// use solution_optimized::SolutionOptimized;
    /// 
    /// let apple = vec![1, 3, 2];
    /// let capacity = vec![4, 3, 1, 5, 2];
    /// // total_apples = 6
    /// // freq = {1:1, 2:1, 3:1, 4:1, 5:1}, max_cap = 5
    /// // c=5: remaining=6, needed=2, take=1 → used=1, current=5
    /// // c=4: remaining=1, needed=1, take=1 → used=2, current=9 (done!)
    /// assert_eq!(SolutionOptimized::minimum_boxes(apple, capacity), 2);
    /// ```
    /// 
    /// # Arguments
    /// * `apple` - Vector where apple[i] is apples in pack i
    /// * `capacity` - Vector where capacity[i] is max capacity of box i
    /// 
    /// # Returns
    /// Minimum number of boxes needed
    pub fn minimum_boxes(apple: Vec<i32>, capacity: Vec<i32>) -> i32 {
        // Step 1: Calculate total apples to pack
        let total_apples: i32 = apple.iter().sum();
        
        // Step 2: Build frequency array and track max capacity
        let mut max_cap = 0;
        let mut freq = vec![0; 51]; // freq[i] = count of boxes with capacity i
        for &c in &capacity {
            freq[c as usize] += 1;
            if c > max_cap {
                max_cap = c;
            }
        }
        
        // Step 3: Greedily take boxes from largest to smallest capacity
        let mut used = 0;
        let mut current = 0;
        
        for c in (1..=max_cap).rev() {
            let count = freq[c as usize];
            if count > 0 && current < total_apples {
                let remaining = total_apples - current;
                
                // Ceiling division: ceil(remaining / c) = (remaining + c - 1) / c
                let needed = (remaining + c - 1) / c;
                
                // Take minimum of what we need and what's available
                let take = needed.min(count);
                
                // Batch process: take multiple boxes at once
                current += take * c;
                used += take;
            }
        }
        
        used
    }
}
