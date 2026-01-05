const std = @import("std");
const testing = std.testing;
const solution_greedy = @import("solution_greedy.zig");
const solution_optimized = @import("solution_optimized.zig");

test "example 1" {
    var apple = [_]i32{ 1, 3, 2 };
    var capacity1 = [_]i32{ 4, 3, 1, 5, 2 };
    const capacity2 = [_]i32{ 4, 3, 1, 5, 2 };

    const result1 = solution_greedy.SolutionGreedy.minimumBoxes(&apple, &capacity1);
    try testing.expectEqual(@as(i32, 2), result1);

    const result2 = solution_optimized.SolutionOptimized.minimumBoxes(&apple, &capacity2);
    try testing.expectEqual(@as(i32, 2), result2);
}

test "example 2" {
    var apple = [_]i32{ 5, 5, 5 };
    var capacity1 = [_]i32{ 2, 4, 2, 7 };
    const capacity2 = [_]i32{ 2, 4, 2, 7 };

    const result1 = solution_greedy.SolutionGreedy.minimumBoxes(&apple, &capacity1);
    try testing.expectEqual(@as(i32, 4), result1);

    const result2 = solution_optimized.SolutionOptimized.minimumBoxes(&apple, &capacity2);
    try testing.expectEqual(@as(i32, 4), result2);
}

test "single box" {
    var apple = [_]i32{10};
    var capacity1 = [_]i32{50};
    const capacity2 = [_]i32{50};

    const result1 = solution_greedy.SolutionGreedy.minimumBoxes(&apple, &capacity1);
    try testing.expectEqual(@as(i32, 1), result1);

    const result2 = solution_optimized.SolutionOptimized.minimumBoxes(&apple, &capacity2);
    try testing.expectEqual(@as(i32, 1), result2);
}

test "multiple small boxes" {
    var apple = [_]i32{ 10, 10, 10 };
    var capacity1 = [_]i32{ 5, 5, 5, 5, 5, 5 };
    const capacity2 = [_]i32{ 5, 5, 5, 5, 5, 5 };

    const result1 = solution_greedy.SolutionGreedy.minimumBoxes(&apple, &capacity1);
    try testing.expectEqual(@as(i32, 6), result1);

    const result2 = solution_optimized.SolutionOptimized.minimumBoxes(&apple, &capacity2);
    try testing.expectEqual(@as(i32, 6), result2);
}

test "exact fit" {
    var apple = [_]i32{ 10, 20 };
    var capacity1 = [_]i32{ 15, 15 };
    const capacity2 = [_]i32{ 15, 15 };

    const result1 = solution_greedy.SolutionGreedy.minimumBoxes(&apple, &capacity1);
    try testing.expectEqual(@as(i32, 2), result1);

    const result2 = solution_optimized.SolutionOptimized.minimumBoxes(&apple, &capacity2);
    try testing.expectEqual(@as(i32, 2), result2);
}
