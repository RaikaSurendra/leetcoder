# LeetCode 3453: Separate Squares I - Go Solutions

## Overview

This folder contains **3 different Go implementations** for solving the Separate Squares I problem, each with different time/space complexity trade-offs.

---

## Project Structure

```
go/
├── go.mod                       # Go module file
├── main.go                      # Main entry point (runs all solutions)
├── solution1_brute_force.go     # Brute force approach
├── solution2_binary_search.go   # Binary search (recommended)
└── solution3_mathematical.go    # Mathematical/event processing (optimal)
```

---

## Solution Files

### 1. solution1_brute_force.go (Event-Based Sweep Line)

**Approach**: Sweep line through all critical y-coordinates

**Complexity**:
- Time: `O(n²)` 
- Space: `O(n)`

**Strategy**:
- Collect all critical y-values (bottom and top of each square)
- For each critical y, calculate total area below
- Use interpolation when area crosses target

**Status**: ❌ Too slow for large inputs (n = 50,000)

**Type**: `Solution1BruteForce struct{}`

---

### 2. solution2_binary_search.go ⭐ **RECOMMENDED**

**Approach**: Binary search on y-coordinate using monotonic property

**Complexity**:
- Time: `O(n log R)` ≈ `O(n)` where R is y-range
- Space: `O(1)`

**Strategy**:
- Leverage monotonic property: as y increases, area below increases
- Binary search for y where area_below = total_area / 2
- ~20 iterations for precision 10⁻⁶

**Status**: ✅ **Best balance of simplicity and performance**

**Type**: `Solution2BinarySearch struct{}`

**Key Features**:
```go
- Simple to understand and implement
- Fast enough for all test cases
- O(1) extra space
- Easy to debug
```

---

### 3. solution3_mathematical.go ⚡ **OPTIMAL**

**Approach**: Event processing with efficient area tracking

**Complexity**:
- Time: `O(n log n)`
- Space: `O(n)`

**Strategy**:
- Create START/END events for each square
- Sort events and process in order
- Track active width and cumulative area
- Single pass through sorted events

**Status**: ⚡ Fastest asymptotic complexity

**Type**: `Solution3Mathematical struct{}`

**Key Features**:
```go
- Optimal O(n log n) time
- Single pass through data
- Two implementation variants: SeparateSquares() and SeparateSquaresAlt()
```

---

## Running the Solutions

### Run All Solutions Together:
```bash
cd go
go run .
```

This will execute `main.go` which runs all 3 solutions with formatted output.

### Run Individual Solutions:
```bash
# Brute Force
go run solution1_brute_force.go main.go

# Binary Search (Recommended)
go run solution2_binary_search.go main.go

# Mathematical (Optimal)
go run solution3_mathematical.go main.go
```

### Build and Run:
```bash
go build -o separate_squares
./separate_squares
```

---

## Performance Comparison

| Solution | Time | Space | Lines | Complexity | Speed Rank |
|----------|------|-------|-------|------------|------------|
| Brute Force | O(n²) | O(n) | ~130 | Simple | 3rd (Slowest) |
| **Binary Search** | **O(n)** | **O(1)** | **~115** | **Medium** | **2nd** ⭐ |
| Mathematical | O(n log n) | O(n) | ~175 | Complex | 1st (Fastest) ⚡ |

---

## Test Results

All solutions produce correct results within required precision (10⁻⁵):

```
Example 1: [[0,0,1],[2,2,1]] → 1.00000 ✓
Example 2: [[0,0,2],[1,1,1]] → 1.16667 ✓
Single square: [[0,0,2]]      → 1.00000 ✓
```

**Output from `go run .`:**

```
╔══════════════════════════════════════════════════════════╗
║  LeetCode 3453: Separate Squares I - Go Solutions       ║
╚══════════════════════════════════════════════════════════╝

┌─────────────────────────────────────────────────────────┐
│ Solution 1: Brute Force (Event-Based Sweep Line)       │
│ Time: O(n²), Space: O(n)                                │
└─────────────────────────────────────────────────────────┘
Example 1: 1.00000 ✓
Example 2: 1.16667 ✓

┌─────────────────────────────────────────────────────────┐
│ Solution 2: Binary Search ⭐ RECOMMENDED                │
│ Time: O(n), Space: O(1)                                 │
└─────────────────────────────────────────────────────────┘
Example 1: 1.00000 ✓
Example 2: 1.16667 ✓
Single square: 1.00000 ✓

┌─────────────────────────────────────────────────────────┐
│ Solution 3: Mathematical (Event Processing) ⚡ OPTIMAL  │
│ Time: O(n log n), Space: O(n)                           │
└─────────────────────────────────────────────────────────┘
Example 1: 1.00000 ✓
Example 2: 1.16667 ✓
Single square: 1.00000 ✓
```

---

## Which Solution to Use?

### For LeetCode Submission:
→ **solution2_binary_search.go** ⭐

**Reasons**:
- Fast enough to pass all test cases
- Simple and easy to debug
- Minimal code (~115 lines)
- O(1) space complexity

### For Learning:
→ Study all three in order: Brute Force → Binary Search → Mathematical

### For Production:
→ **solution3_mathematical.go** if performance is critical
→ **solution2_binary_search.go** if readability matters more

---

## Algorithm Explanation

### How Area Calculation Works:

For a square `[x, y, l]` and horizontal line at height `h`:

1. **Case 1**: `h ≤ y` (line below square)
   - Area below line: `0`
   - Area above line: `l²`

2. **Case 2**: `h ≥ y + l` (line above square)
   - Area below line: `l²`
   - Area above line: `0`

3. **Case 3**: `y < h < y + l` (line splits square)
   - Area below line: `l × (h - y)`
   - Area above line: `l × (y + l - h)`

### Why Binary Search Works:

As `h` increases:
- ↗️ Area below increases monotonically
- ↘️ Area above decreases monotonically

This guarantees binary search converges to unique answer!

---

## Go-Specific Features

### Type Definitions:
```go
type Solution1BruteForce struct{}
type Solution2BinarySearch struct{}
type Solution3Mathematical struct{}
```

### Method Signatures:
```go
func (s *SolutionX) SeparateSquares(squares [][]int) float64
```

### Constants:
```go
const epsilon = 1e-6  // Precision for binary search
```

### Event Struct (Solution 3):
```go
type Event struct {
    y       float64
    isStart bool
    length  int
}
```

---

## Common Pitfalls

1. **Integer Overflow**: 
   - Use `float64` for area calculations
   - `l²` can be very large

2. **Floating Point Precision**:
   - Use epsilon = `1e-6` for comparisons
   - Problem accepts answers within `10⁻⁵`

3. **Overlapping Squares**:
   - Count each square's area independently
   - DON'T compute geometric union

4. **Edge Cases**:
   - Single square: answer is middle of square
   - All squares at same y: handle carefully

---

## Additional Resources

- **Problem Description**: `../problem.md`
- **Detailed Algorithm Analysis**: `../algo_solution.md`
- **Java Implementations**: `../java/`
- **Complexity Analysis**: See individual solution files

---

## Dependencies

**Standard Library Only**:
- `fmt` - formatted I/O
- `math` - mathematical functions (Min, Max)
- `sort` - sorting slices

**No external dependencies required!**

---

## Testing

### Manual Testing:
Run `go run .` to see all test cases execute with formatted output.

### Add Your Own Tests:
Edit `main.go` to add custom test cases:

```go
customSquares := [][]int{{x1, y1, l1}, {x2, y2, l2}}
result := solution2.SeparateSquares(customSquares)
fmt.Printf("Custom test: %.5f\n", result)
```

---

## Code Quality

All solutions feature:
- ✅ Comprehensive documentation
- ✅ Clear variable names following Go conventions
- ✅ Detailed comments explaining algorithms
- ✅ Example test cases
- ✅ Edge case handling
- ✅ Idiomatic Go code

**Recommendation**: Start with solution2_binary_search.go for the best learning and submission experience.

---

## Module Information

**Module**: `leetcode3453`
**Go Version**: `1.21+`

To initialize module:
```bash
go mod init leetcode3453
go mod tidy
```
