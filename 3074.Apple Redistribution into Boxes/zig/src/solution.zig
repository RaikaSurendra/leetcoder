const std = @import("std");

pub const Solution = struct {
    /// Greedy approach: Sort capacities in descending order and pick largest first
    /// Time: O(n + m log m)
    /// Space: O(1) extra (ignoring sort space)
    pub fn minimumBoxes(apple: []const i32, capacity: []i32) i32 {
        var total_apples: i32 = 0;
        for (apple) |a| {
            total_apples += a;
        }

        // Sort in descending order
        std.mem.sort(i32, capacity, {}, comptime std.sort.desc(i32));

        var used: i32 = 0;
        var current: i32 = 0;

        for (capacity) |c| {
            current += c;
            used += 1;
            if (current >= total_apples) {
                return used;
            }
        }

        return used;
    }

    /// Optimized approach: Use frequency counting to avoid sorting
    /// Time: O(n + m + 50)
    /// Space: O(50)
    pub fn minimumBoxesOptimized(apple: []const i32, capacity: []const i32) i32 {
        var total_apples: i32 = 0;
        for (apple) |a| {
            total_apples += a;
        }

        var freq = [_]i32{0} ** 51;
        for (capacity) |c| {
            freq[@intCast(c)] += 1;
        }

        var used: i32 = 0;
        var current: i32 = 0;

        var c: i32 = 50;
        while (c >= 1) : (c -= 1) {
            while (freq[@intCast(c)] > 0 and current < total_apples) {
                current += c;
                used += 1;
                freq[@intCast(c)] -= 1;
            }
            if (current >= total_apples) {
                break;
            }
        }

        return used;
    }
};
