# LeetCode 9: Palindrome Number - Java Solutions

## Overview
This folder provides two Java implementations for checking if an integer is a palindrome.

- SolutionBruteForce.java — String-based two-pointer (simple baseline)
- SolutionOptimal.java — Reverse-half numeric method (follow-up compliant)

---

## Files
- `SolutionBruteForce.java`
  - Approach: Convert to string and compare with two pointers.
  - Complexity: Time O(n), Space O(n)
  - Includes `main` with sample tests.

- `SolutionOptimal.java`
  - Approach: Reverse only half of the integer to avoid overflow and extra space.
  - Complexity: Time O(log10 x), Space O(1)
  - Includes `main` with sample tests.

- `../algo_solution.md`
  - In-depth notes: approaches, edge cases, pitfalls, and pseudocode.

---

## Compile & Run
From this directory:

```bash
# Brute Force
javac SolutionBruteForce.java
java SolutionBruteForce

# Optimal (Recommended)
javac SolutionOptimal.java
java SolutionOptimal
```

---

## Testing (JUnit 5)

Maven project with parameterized tests is included.

```bash
mvn -q -DskipTests=false test
```

File structure:

```
java/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── SolutionBruteForce.java
│   │       └── SolutionOptimal.java
│   └── test/
│       └── java/
│           ├── SolutionBruteForceTest.java
│           └── SolutionOptimalTest.java
└── (standalone .java files also exist for direct javac/java runs)
```

Notes:
- Tests use JUnit 5 parameterized `@CsvSource` to scale input cases easily.
- Standalone files remain for quick manual runs without Maven.

---

## Developer Notes
- Negative numbers are never palindromes due to the leading '-'.
- Numbers ending with 0 (except 0 itself) cannot be palindromes.
- Reverse-half method avoids integer overflow and uses O(1) extra space.

---

## Example Outputs
For test cases: 121, -121, 10, 0, 1221, 12321, 123456
- Brute Force and Optimal both print expected true/false results for the above inputs.

---

## Recommendation
Use `SolutionOptimal` for submissions/interviews (satisfies follow-up). Keep `SolutionBruteForce` as an easy-to-understand baseline.
