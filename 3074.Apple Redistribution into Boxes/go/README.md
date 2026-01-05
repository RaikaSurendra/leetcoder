# Go Solution

## Structure
```
go/
├── solution.go
├── solution_test.go
├── go.mod
└── README.md
```

## Run Tests

```bash
cd go
go test -v
```

## Run Benchmarks

```bash
go test -bench=.
```

## Build

```bash
go build
```

## Approaches

### Greedy (Sorting)
- Sort capacities in descending order using `sort.Slice`
- Pick boxes from largest to smallest
- Time: O(n + m log m)
- Space: O(1)

### Optimized (Frequency Counting)
- Count boxes by capacity (max 50)
- Pick from capacity 50 down to 1
- Time: O(n + m + 50)
- Space: O(50)
