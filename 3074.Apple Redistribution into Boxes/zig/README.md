# Zig Solution

## Structure
```
zig/
├── build.zig
├── build.zig.zon
├── src/
│   ├── solution.zig
│   ├── main.zig
│   └── test.zig
├── zig-out/         (created after build)
└── README.md
```

## Run Tests

```bash
cd zig
zig build test
```

## Build

```bash
zig build
```

## Build and Run

```bash
zig build run
```

## Build Optimized

```bash
zig build -Doptimize=ReleaseFast
```

## Approaches

### Greedy (Sorting)
- Sort capacities in descending order using `std.mem.sort`
- Pick boxes from largest to smallest
- Time: O(n + m log m)
- Space: O(1)

### Optimized (Frequency Counting)
- Count boxes by capacity (max 50)
- Pick from capacity 50 down to 1
- Time: O(n + m + 50)
- Space: O(50)

## Notes
- Build artifacts go to `zig-out/` directory
- Use `zig build clean` or remove `zig-out/` to clean
- Tests are in separate `test.zig` file
- Main executable demonstrates both examples
