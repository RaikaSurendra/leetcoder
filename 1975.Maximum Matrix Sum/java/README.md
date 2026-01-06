# Maximum Matrix Sum - Java Implementation

## Problem
LeetCode 1975: Maximize the sum of an n×n matrix by flipping adjacent elements' signs any number of times.

## Solution

### Algorithm
**Key Insight**: The parity (even/odd count) of negative numbers determines the solution.

- **Even negatives**: Can make ALL elements positive → sum of absolute values
- **Odd negatives**: Must keep ONE negative → minimize by keeping smallest absolute value negative

### Implementation
Single optimized solution with O(n²) time and O(1) space complexity.

**Steps**:
1. Calculate sum of all absolute values
2. Count negative numbers
3. Track minimum absolute value
4. If odd negatives: `totalSum - 2 × minAbs`
5. If even negatives: `totalSum`

### Complexity
- **Time**: O(n²) - single pass through n×n matrix
- **Space**: O(1) - only 3 variables (totalSum, negCount, minAbs)

## Building and Testing

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Compile
```bash
mvn compile
```

### Run Tests
```bash
mvn test
```

### Run Tests with Output
```bash
mvn test -q
```

### Clean Build Artifacts
```bash
mvn clean
```

## File Structure
```
java/
├── src/
│   └── Solution.java          # Optimized solution
├── test/
│   └── SolutionTest.java      # JUnit test cases
├── pom.xml                     # Maven configuration
└── README.md                   # This file
```

## Test Cases
- ✓ Example 1: Basic 2×2 matrix
- ✓ Example 2: 3×3 matrix with mixed signs
- ✓ All positive numbers
- ✓ All negative (even count)
- ✓ Odd negative count
- ✓ Matrix with zero
- ✓ Single negative
- ✓ Large values (edge of constraints)

## Performance
Expected performance on modern hardware:
- **Small matrices** (n ≤ 10): < 1 microsecond
- **Medium matrices** (n ≤ 100): < 100 microseconds
- **Large matrices** (n = 250): < 10 milliseconds

## Example Usage
```java
Solution solution = new Solution();
int[][] matrix = {{1, -1}, {-1, 1}};
long result = solution.maxMatrixSum(matrix);
// result = 4
```
