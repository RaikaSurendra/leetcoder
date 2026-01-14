# 🚀 LeetCoder

A comprehensive repository documenting algorithmic problem-solving journey with multiple solution approaches, performance benchmarking, and cross-language implementations.

## 📚 Overview

This repository contains solutions to competitive programming problems with a focus on:
- **Multiple algorithmic approaches** (Brute Force → Optimized → Mathematical)
- **Cross-language implementations** (Java, C, Go, Rust)
- **Performance analysis** with benchmarking and complexity analysis
- **Production-ready code** with comprehensive testing

## 🎯 Problem-Solving Philosophy

### Multi-Approach Strategy
Each problem is solved using different approaches to understand trade-offs:
1. **Brute Force** - Straightforward solution to establish correctness
2. **Optimized** - Improved algorithms (binary search, greedy, dynamic programming)
3. **Mathematical/Optimal** - Best theoretical complexity with advanced techniques

### Cross-Language Implementation
Solutions implemented in multiple languages to explore:
- Language-specific optimizations
- Memory management paradigms
- Performance characteristics across ecosystems

## 🧠 Computer Science Topics Covered

### Algorithms & Data Structures
- **Search Algorithms**: Binary Search, Linear Search
- **Sorting Algorithms**: QuickSort, Merge Sort
- **Greedy Algorithms**: Optimization problems with local choices
- **Dynamic Programming**: Memoization and tabulation approaches
- **Computational Geometry**: Sweep line algorithms, coordinate geometry
- **Event Processing**: Time-based event handling

### Complexity Analysis
- Time Complexity: O(1), O(n), O(n log n), O(n²)
- Space Complexity: In-place vs auxiliary space trade-offs
- Amortized Analysis: Average case performance

### System Design & Engineering
- **Memory Optimization**: Stack vs heap allocation, cache-friendly data structures
- **Performance Benchmarking**: JMH (Java), clock-based timing (C), testing frameworks
- **Code Organization**: Modular design, separation of concerns
- **Testing**: Unit tests, integration tests, edge case coverage

### Language-Specific Techniques

#### Java
- Object-oriented design patterns
- Stream API and functional programming
- JUnit 5 testing framework
- JMH microbenchmarking
- Maven build automation

#### C
- Manual memory management
- Pointer optimization and restrict keyword
- Compiler optimizations (-O3, -march=native)
- Stack vs heap allocation strategies
- Cache-friendly struct packing

#### Go
- Concurrency patterns (goroutines, channels)
- Interface-based design
- Standard library testing
- Slice and map optimizations

#### Rust
- Memory safety without garbage collection
- Ownership and borrowing
- Zero-cost abstractions
- Pattern matching

## 📊 Problems Solved

| Problem | Difficulty | Topics | Languages | Approaches |
|---------|-----------|--------|-----------|------------|
| [3453. Separate Squares I](./3453.%20Separate%20Squares%20I/) | Medium | Binary Search, Geometry, Sweep Line | Java, C, Go | 3 approaches |
| [3074. Apple Redistribution into Boxes](./3074.Apple%20Redistribution%20into%20Boxes/) | Easy | Greedy, Sorting | Java, C, Go, Rust | Multiple |
| [1975. Maximum Matrix Sum](./1975.Maximum%20Matrix%20Sum/) | Medium | Greedy, Matrix | Java | Optimized |

## 📁 Repository Structure

```
leetcoder/
├── [Problem Number]. [Problem Name]/
│   ├── problem.md                 # Problem statement
│   ├── algo_solution.md          # Algorithm analysis & approaches
│   ├── c/                        # C implementation
│   │   ├── solution*.c
│   │   ├── Makefile
│   │   └── README.md
│   ├── go/                       # Go implementation
│   │   ├── *.go
│   │   ├── go.mod
│   │   └── README.md
│   ├── java/                     # Java implementation
│   │   ├── src/main/java/        # Solution classes
│   │   ├── src/test/java/        # Tests & benchmarks
│   │   ├── pom.xml
│   │   └── README.md
│   └── rust/                     # Rust implementation (if applicable)
│       ├── src/
│       ├── Cargo.toml
│       └── README.md
```

## 🔧 Building & Running

### Java
```bash
cd "Problem Name"/java
mvn clean test                    # Run tests
mvn clean package                 # Build with benchmarks
java -jar target/benchmarks.jar   # Run JMH benchmarks
```

### C
```bash
cd "Problem Name"/c
make                              # Compile
./solution                        # Run tests & benchmarks
make clean                        # Clean build artifacts
```

### Go
```bash
cd "Problem Name"/go
go test -v                        # Run tests
go test -bench=.                  # Run benchmarks
```

### Rust
```bash
cd "Problem Name"/rust
cargo test                        # Run tests
cargo bench                       # Run benchmarks
```

## 📈 Performance Benchmarking

Each solution includes performance benchmarks with:
- Multiple input sizes (small, medium, large, edge cases)
- Warmup iterations to account for JIT compilation
- Statistical analysis (mean, standard deviation)
- Comparison across different approaches

## 🎓 Learning Resources

Each problem directory contains:
- **problem.md**: Original problem statement with constraints
- **algo_solution.md**: Detailed algorithmic analysis with:
  - Problem understanding and examples
  - Multiple solution approaches
  - Complexity analysis (time & space)
  - Trade-offs and optimization techniques
  - Visual diagrams and step-by-step walkthroughs

## 🛠️ Technologies & Tools

- **Languages**: Java 11+, C (C11), Go 1.21+, Rust 1.70+
- **Build Tools**: Maven, Make, Go modules, Cargo
- **Testing**: JUnit 5, Go testing, Rust test framework
- **Benchmarking**: JMH (Java Microbenchmark Harness), Native timing
- **Version Control**: Git with semantic branching (nplc/*)

## 🚦 Branch Nomenclature

- `nplc/[problem-number]` - New problem solutions
- Example: `nplc/3453` for problem 3453

## 📝 Contributing Philosophy

Focus on:
- **Correctness**: All solutions pass test cases
- **Clarity**: Well-documented code with comments
- **Completeness**: Multiple approaches with trade-off analysis
- **Performance**: Benchmarked and optimized solutions

## 📧 Contact

For questions, discussions, or collaborations, please open an issue or reach out via GitHub.

---

**Note**: This is a personal learning repository documenting problem-solving techniques, algorithmic thinking, and software engineering best practices across multiple programming paradigms.
