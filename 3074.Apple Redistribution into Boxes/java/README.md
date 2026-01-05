# Java Solution

## Structure
```
java/
├── src/
│   └── Solution.java
├── test/
│   └── SolutionTest.java
├── pom.xml
└── README.md
```

## Build and Test

### Using Maven
```bash
mvn clean test
```

### Compile only
```bash
mvn compile
```

### Without Maven (manual compilation)
```bash
# Compile
javac -d target/classes src/Solution.java

# Compile tests (requires JUnit 5)
javac -cp target/classes:junit-platform-console-standalone.jar -d target/test-classes test/SolutionTest.java

# Run tests
java -jar junit-platform-console-standalone.jar --class-path target/classes:target/test-classes --scan-class-path
```

## Approaches

### Greedy (Sorting)
- Sort capacities in descending order
- Pick boxes from largest to smallest
- Time: O(n + m log m)
- Space: O(1)

### Optimized (Frequency Counting)
- Count boxes by capacity (max 50)
- Pick from capacity 50 down to 1
- Time: O(n + m + 50)
- Space: O(50)
