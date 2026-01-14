# LeetCode 3453: Separate Squares I - Java Solutions

## Overview

This folder contains **3 different implementations** for solving the Separate Squares I problem, each with different time/space complexity trade-offs.

---

## Solution Files

### 1. Solution1BruteForce.java (Event-Based Sweep Line)

**Approach**: Sweep line through all critical y-coordinates

**Complexity**:
- Time: `O(n²)` 
- Space: `O(n)`

**Strategy**:
- Collect all critical y-values (bottom and top of each square)
- For each critical y, calculate total area below
- Use interpolation when area crosses target

**Status**: ❌ Too slow for large inputs (n = 50,000)

**When to use**: Small datasets, educational purposes

---

### 2. Solution2BinarySearch.java ⭐ **RECOMMENDED**

**Approach**: Binary search on y-coordinate using monotonic property

**Complexity**:
- Time: `O(n log R)` ≈ `O(n)` where R is y-range
- Space: `O(1)`

**Strategy**:
- Leverage monotonic property: as y increases, area below increases
- Binary search for y where area_below = total_area / 2
- ~20 iterations for precision 10⁻⁶

**Status**: ✅ **Best balance of simplicity and performance**

**When to use**: Production code, competitive programming, interviews

**Key Features**:
```java
- Simple to understand and implement
- Fast enough for all test cases
- O(1) extra space
- Easy to debug
```

---

### 3. Solution3Mathematical.java ⚡ **OPTIMAL**

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

**When to use**: Performance-critical applications, large datasets

**Key Features**:
```java
- Optimal O(n log n) time
- Single pass through data
- More complex to implement
- Two implementation variants provided
```

---

## Compilation and Execution

### Compile All Solutions:
```bash
javac Solution1BruteForce.java
javac Solution2BinarySearch.java
javac Solution3Mathematical.java
```

### Run Individual Solutions:
```bash
# Brute Force
java Solution1BruteForce

# Binary Search (Recommended)
java Solution2BinarySearch

# Mathematical (Optimal)
java Solution3Mathematical
```

---

## Performance Comparison

| Solution | Time | Space | Lines | Complexity | Speed Rank |
|----------|------|-------|-------|------------|------------|
| Brute Force | O(n²) | O(n) | ~150 | Simple | 3rd (Slowest) |
| **Binary Search** | **O(n)** | **O(1)** | **~100** | **Medium** | **2nd** ⭐ |
| Mathematical | O(n log n) | O(n) | ~200 | Complex | 1st (Fastest) ⚡ |

---

## Test Results

All solutions handle the LeetCode examples correctly:

**Example 1**: `squares = [[0,0,1],[2,2,1]]`
- Expected: `1.00000`
- All solutions: ✅ Pass

**Example 2**: `squares = [[0,0,2],[1,1,1]]`
- Expected: `1.16667`
- All solutions: ✅ Pass

**Edge Case (Single Square)**: `squares = [[0,0,2]]`
- Expected: `1.00000`
- All solutions: ✅ Pass

---

## Which Solution to Use?

### For LeetCode Submission:
→ **Solution2BinarySearch.java** ⭐

**Reasons**:
- Fast enough to pass all test cases
- Simple and easy to debug
- Minimal code (~100 lines)
- O(1) space complexity

### For Learning:
→ Study all three in order: Brute Force → Binary Search → Mathematical

### For Production:
→ **Solution3Mathematical.java** if performance is critical
→ **Solution2BinarySearch.java** if readability matters more

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

## Common Pitfalls

1. **Integer Overflow**: 
   - Use `long` for intermediate calculations
   - `l²` can be up to `10¹⁸`

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
- **Complexity Analysis**: See individual solution files

---

## Author Notes

All three solutions are production-ready with:
- ✅ Comprehensive documentation
- ✅ Example test cases in main()
- ✅ Clear variable names
- ✅ Detailed comments
- ✅ Edge case handling

**Recommendation**: Start with Solution2BinarySearch.java for the best learning and submission experience.
