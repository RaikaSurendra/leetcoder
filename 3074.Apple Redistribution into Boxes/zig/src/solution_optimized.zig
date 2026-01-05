const std = @import("std");

/// Optimized Solution using Frequency Counting (No Sorting!)
///
/// Problem: Find minimum number of boxes needed to redistribute all apples.
/// Approach: Exploit constraint that capacity ≤ 50 to avoid sorting overhead.
///           Use frequency array to count boxes of each capacity value.
///
/// Why Faster?
/// - Avoids O(m log m) sorting cost
/// - Uses O(50) fixed iteration instead of O(m) comparisons
/// - Takes multiple boxes of same capacity at once (batch processing)
/// - Zero heap allocations (uses stack-allocated array)
///
/// Algorithm:
/// 1. Calculate total apples to pack
/// 2. Build frequency array: freq[c] = count of boxes with capacity c
/// 3. Track maximum capacity to avoid iterating unused slots
/// 4. Iterate from max capacity down to 1:
///    - Calculate exact boxes needed using ceiling division
///    - Take minimum of (needed, available)
///    - Multiply capacity by boxes taken (batch processing)
///
/// Key Optimization - Ceiling Division:
///   To find boxes needed: ceil(remaining / capacity)
///   Using integer math: (remaining + capacity - 1) / capacity
///   Example: remaining=10, capacity=3
///            ceil(10/3) = 4
///            (10+3-1)/3 = 12/3 = 4 ✓
///
/// Key Optimization - Batch Processing:
///   Instead of: take 1 box → check → take 1 box → check...
///   We calculate: need 3 boxes of capacity 5 → take all 3 at once
///   This reduces iterations and improves branch prediction
///
/// Time Complexity: O(n + m + 50) ≈ O(n + m)
///   - O(n) to sum apple array
///   - O(m) to build frequency array
///   - O(50) to iterate capacities 1-50 (constant, not dependent on m!)
///   - Much faster than O(m log m) when m > 50
///
/// Space Complexity: O(50) = O(1) constant space
///   - Fixed-size frequency array of 51 elements (stack-allocated)
///   - Only constant extra variables
///
/// Example:
///   apple = [1, 3, 2], capacity = [4, 3, 1, 5, 2]
///   total_apples = 6
///   freq = {1:1, 2:1, 3:1, 4:1, 5:1}, max_cap = 5
///   c=5: remaining=6, needed=ceil(6/5)=2, take=min(2,1)=1 → used=1, current=5
///   c=4: remaining=1, needed=ceil(1/4)=1, take=min(1,1)=1 → used=2, current=9 (done!)
///   Return 2 boxes
pub const SolutionOptimized = struct {
    /// Finds minimum boxes needed using frequency counting optimization.
    pub fn minimumBoxes(apple: []const i32, capacity: []const i32) i32 {
        // Step 1: Calculate total apples to pack
        var total_apples: i32 = 0;
        for (apple) |a| {
            total_apples += a;
        }

        // Step 2: Build frequency array and track max capacity
        var max_cap: i32 = 0;
        var freq = [_]i32{0} ** 51; // freq[i] = count of boxes with capacity i
        for (capacity) |c| {
            freq[@intCast(c)] += 1;
            if (c > max_cap) max_cap = c;
        }

        // Step 3: Greedily take boxes from largest to smallest capacity
        var used: i32 = 0;
        var current: i32 = 0;

        var c: i32 = max_cap;
        while (c >= 1 and current < total_apples) : (c -= 1) {
            const count = freq[@intCast(c)];
            if (count > 0) {
                const remaining = total_apples - current;

                // Ceiling division: ceil(remaining / c) = (remaining + c - 1) / c
                const needed = @divTrunc(remaining + c - 1, c);

                // Take minimum of what we need and what's available
                const take = if (needed < count) needed else count;

                // Batch process: take multiple boxes at once
                current += take * c;
                used += take;
            }
        }

        return used;
    }
};
