const std = @import("std");
const solution = @import("solution.zig");

pub fn main() !void {
    const stdout = std.io.getStdOut().writer();
    
    // Example 1
    var apple1 = [_]i32{ 1, 3, 2 };
    var capacity1 = [_]i32{ 4, 3, 1, 5, 2 };
    const result1 = solution.Solution.minimumBoxes(&apple1, &capacity1);
    try stdout.print("Example 1: {d}\n", .{result1});
    
    // Example 2
    var apple2 = [_]i32{ 5, 5, 5 };
    var capacity2 = [_]i32{ 2, 4, 2, 7 };
    const result2 = solution.Solution.minimumBoxes(&apple2, &capacity2);
    try stdout.print("Example 2: {d}\n", .{result2});
}
