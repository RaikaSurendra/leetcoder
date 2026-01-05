# Apple Redistribution into Boxes - C Implementation

## Problem
Find the minimum number of boxes needed to redistribute all apples.

## Solutions

### 1. Greedy Solution (`solution_greedy.c`)
- **Algorithm**: Sort capacities in descending order and greedily pick largest boxes
- **Time Complexity**: O(n + m log m)
- **Space Complexity**: O(1)
- **Approach**: Uses standard library `qsort()` for sorting

### 2. Optimized Solution (`solution_optimized.c`)
- **Algorithm**: Frequency counting with batch processing (no sorting!)
- **Time Complexity**: O(n + m + 50) ≈ O(n + m)
- **Space Complexity**: O(50) = O(1) constant
- **Optimization**: Exploits constraint that capacity ≤ 50

## Building and Testing

### Prerequisites
- GCC or compatible C compiler
- Make

### Build
```bash
make
```

### Run Tests
```bash
make test
```

### Clean Build Artifacts
```bash
make clean
```

### Debug Build
```bash
make debug
```

## File Structure
```
c/
├── src/                           # Source files
│   ├── solution_greedy.c         # Greedy solution implementation
│   └── solution_optimized.c      # Optimized solution implementation
├── include/                       # Header files
│   ├── solution_greedy.h         # Greedy solution header
│   └── solution_optimized.h      # Optimized solution header
├── test/                          # Test files
│   └── test.c                    # Test cases
├── build/                         # Compiled object files (*.o)
├── bin/                           # Executable binaries
├── Makefile                       # Build configuration
└── README.md                      # This file
```

## Key Optimizations in C

1. **Stack Allocation**: Frequency array is stack-allocated (no malloc)
2. **Inline Comparisons**: MIN macro avoids function call overhead
3. **Cache Efficiency**: Small fixed-size arrays improve cache locality
4. **Branch Prediction**: Batch processing reduces branches

## Performance
Expected performance on modern hardware:
- **Greedy**: ~40-50 ns per call
- **Optimized**: ~20-30 ns per call (1.5-2x faster)
- Both well under 1ms requirement

## Example Usage
```c
#include "solution_optimized.h"

int apple[] = {1, 3, 2};
int capacity[] = {4, 3, 1, 5, 2};
int result = minimumBoxesOptimized(apple, 3, capacity, 5);
// result = 2
```
