const std = @import("std");

/// Greedy Solution using Sorting
///
/// Problem: Find minimum number of boxes needed to redistribute all apples.
/// Approach: Since apples can be split across boxes, only total count matters.
///           Greedily select largest capacity boxes first to minimize count.
///
/// Algorithm:
/// 1. Calculate total number of apples to pack
/// 2. Sort capacities in descending order (largest first)
/// 3. Iterate through sorted capacities, accumulating capacity
/// 4. Stop when accumulated capacity >= total apples
///
/// Intuition:
/// - Apples can be split, so we only care about total capacity needed
/// - Larger boxes pack more apples per box → fewer boxes needed
/// - Greedy choice: always pick largest available capacity
///
/// Time Complexity: O(n + m log m)
///   - O(n) to sum apple array
///   - O(m log m) to sort capacity array
///   - O(m) worst case to iterate through capacities
///
/// Space Complexity: O(1) extra space
///   - Sorting is done in-place (ignoring sort's stack)
///   - Only constant extra variables used
///
/// Example:
///   apple = [1, 3, 2], capacity = [4, 3, 1, 5, 2]
///   total_apples = 6
///   sorted capacity = [5, 4, 3, 2, 1]
///   Pick 5: current=5, Pick 4: current=9 (≥6, done!)
///   Return 2 boxes
pub const SolutionGreedy = struct {
    /// Finds minimum boxes needed using greedy sorting approach.
    pub fn minimumBoxes(apple: []const i32, capacity: []i32) i32 {
        // Step 1: Calculate total apples to pack
        var total_apples: i32 = 0;
        for (apple) |a| {
            total_apples += a;
        }

        // Step 2: Sort capacities in descending order (largest first)
        std.mem.sort(i32, capacity, {}, comptime std.sort.desc(i32));

        // Step 3: Greedily pick boxes until all apples are packed
        var used: i32 = 0;
        var current: i32 = 0;

        for (capacity) |c| {
            current += c;
            used += 1;
            if (current >= total_apples) {
                return used;
            }
        }

        return used; // All boxes used (shouldn't reach per problem constraints)
    }
};
