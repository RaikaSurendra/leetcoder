const std = @import("std");
const solution_greedy = @import("solution_greedy.zig");
const solution_optimized = @import("solution_optimized.zig");

pub fn main() !void {
    const stdout = std.io.getStdOut().writer();

    // Example 1 - Greedy
    var apple1 = [_]i32{ 1, 3, 2 };
    var capacity1 = [_]i32{ 4, 3, 1, 5, 2 };
    const result1 = solution_greedy.SolutionGreedy.minimumBoxes(&apple1, &capacity1);
    try stdout.print("Example 1 (Greedy): {d}\n", .{result1});

    // Example 1 - Optimized
    const apple1_opt = [_]i32{ 1, 3, 2 };
    const capacity1_opt = [_]i32{ 4, 3, 1, 5, 2 };
    const result1_opt = solution_optimized.SolutionOptimized.minimumBoxes(&apple1_opt, &capacity1_opt);
    try stdout.print("Example 1 (Optimized): {d}\n", .{result1_opt});

    // Example 2 - Greedy
    var apple2 = [_]i32{ 5, 5, 5 };
    var capacity2 = [_]i32{ 2, 4, 2, 7 };
    const result2 = solution_greedy.SolutionGreedy.minimumBoxes(&apple2, &capacity2);
    try stdout.print("Example 2 (Greedy): {d}\n", .{result2});

    // Example 2 - Optimized
    const apple2_opt = [_]i32{ 5, 5, 5 };
    const capacity2_opt = [_]i32{ 2, 4, 2, 7 };
    const result2_opt = solution_optimized.SolutionOptimized.minimumBoxes(&apple2_opt, &capacity2_opt);
    try stdout.print("Example 2 (Optimized): {d}\n", .{result2_opt});
}
