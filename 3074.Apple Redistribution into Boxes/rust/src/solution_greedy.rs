/// Greedy Solution using Sorting
/// 
/// Problem: Find minimum number of boxes needed to redistribute all apples.
/// Approach: Since apples can be split across boxes, only total count matters.
///           Greedily select largest capacity boxes first to minimize count.
pub struct SolutionGreedy;

impl SolutionGreedy {
    /// Finds minimum boxes needed using greedy sorting approach.
    /// 
    /// # Algorithm
    /// 1. Calculate total number of apples to pack
    /// 2. Sort capacities in descending order (largest first)
    /// 3. Iterate through sorted capacities, accumulating capacity
    /// 4. Stop when accumulated capacity >= total apples
    /// 
    /// # Intuition
    /// - Apples can be split, so we only care about total capacity needed
    /// - Larger boxes pack more apples per box → fewer boxes needed
    /// - Greedy choice: always pick largest available capacity
    /// 
    /// # Complexity
    /// - **Time:** O(n + m log m)
    ///   - O(n) to sum apple vector
    ///   - O(m log m) to sort capacity vector (unstable sort)
    ///   - O(m) worst case to iterate through capacities
    /// 
    /// - **Space:** O(1) extra space
    ///   - Sorting is done in-place (ignoring sort's stack)
    ///   - Only constant extra variables used
    /// 
    /// # Example
    /// ```
    /// use solution_greedy::SolutionGreedy;
    /// 
    /// let apple = vec![1, 3, 2];
    /// let capacity = vec![4, 3, 1, 5, 2];
    /// // total_apples = 6
    /// // sorted capacity = [5, 4, 3, 2, 1]
    /// // Pick 5: current=5, Pick 4: current=9 (≥6, done!)
    /// assert_eq!(SolutionGreedy::minimum_boxes(apple, capacity), 2);
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
        
        // Step 2: Sort capacities in descending order (largest first)
        let mut capacity = capacity;
        capacity.sort_unstable_by(|a, b| b.cmp(a));
        
        // Step 3: Greedily pick boxes until all apples are packed
        let mut used = 0;
        let mut current = 0;
        
        for &c in &capacity {
            current += c;
            used += 1;
            if current >= total_apples {
                return used;
            }
        }
        
        used // All boxes used (shouldn't reach per problem constraints)
    }
}
