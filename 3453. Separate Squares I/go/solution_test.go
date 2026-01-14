package main

import (
	"math/rand"
	"testing"
)

// Test functions for all solutions
func TestExample1_AllSolutions(t *testing.T) {
	squares := [][]int{{0, 0, 1}, {2, 2, 1}}
	expected := 1.00000
	epsilon := 1e-5

	result1 := (&Solution1BruteForce{}).SeparateSquares(squares)
	if abs(result1-expected) > epsilon {
		t.Errorf("Solution1: got %.5f, want %.5f", result1, expected)
	}

	result2 := (&Solution2BinarySearch{}).SeparateSquares(squares)
	if abs(result2-expected) > epsilon {
		t.Errorf("Solution2: got %.5f, want %.5f", result2, expected)
	}

	result3 := (&Solution3Mathematical{}).SeparateSquares(squares)
	if abs(result3-expected) > epsilon {
		t.Errorf("Solution3: got %.5f, want %.5f", result3, expected)
	}
}

func TestExample2_AllSolutions(t *testing.T) {
	squares := [][]int{{0, 0, 2}, {1, 1, 1}}
	expected := 1.16667
	epsilon := 1e-5

	result1 := (&Solution1BruteForce{}).SeparateSquares(squares)
	if abs(result1-expected) > epsilon {
		t.Errorf("Solution1: got %.5f, want %.5f", result1, expected)
	}

	result2 := (&Solution2BinarySearch{}).SeparateSquares(squares)
	if abs(result2-expected) > epsilon {
		t.Errorf("Solution2: got %.5f, want %.5f", result2, expected)
	}

	result3 := (&Solution3Mathematical{}).SeparateSquares(squares)
	if abs(result3-expected) > epsilon {
		t.Errorf("Solution3: got %.5f, want %.5f", result3, expected)
	}
}

func abs(x float64) float64 {
	if x < 0 {
		return -x
	}
	return x
}

// Benchmark data generation
func generateBenchmarkSquares(n int) [][]int {
	rand.Seed(42) // Fixed seed for reproducibility
	squares := make([][]int, n)

	for i := 0; i < n; i++ {
		squares[i] = []int{
			rand.Intn(1001),    // x: 0-1000
			rand.Intn(1001),    // y: 0-1000
			rand.Intn(100) + 1, // l: 1-100
		}
	}

	return squares
}

// ============================================================================
// Benchmarks: Solution 1 - Brute Force
// ============================================================================

func BenchmarkSolution1_10(b *testing.B) {
	squares := generateBenchmarkSquares(10)
	solution := &Solution1BruteForce{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

func BenchmarkSolution1_100(b *testing.B) {
	squares := generateBenchmarkSquares(100)
	solution := &Solution1BruteForce{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

func BenchmarkSolution1_1000(b *testing.B) {
	squares := generateBenchmarkSquares(1000)
	solution := &Solution1BruteForce{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

func BenchmarkSolution1_5000(b *testing.B) {
	squares := generateBenchmarkSquares(5000)
	solution := &Solution1BruteForce{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

// ============================================================================
// Benchmarks: Solution 2 - Binary Search (Recommended)
// ============================================================================

func BenchmarkSolution2_10(b *testing.B) {
	squares := generateBenchmarkSquares(10)
	solution := &Solution2BinarySearch{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

func BenchmarkSolution2_100(b *testing.B) {
	squares := generateBenchmarkSquares(100)
	solution := &Solution2BinarySearch{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

func BenchmarkSolution2_1000(b *testing.B) {
	squares := generateBenchmarkSquares(1000)
	solution := &Solution2BinarySearch{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

func BenchmarkSolution2_5000(b *testing.B) {
	squares := generateBenchmarkSquares(5000)
	solution := &Solution2BinarySearch{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

// ============================================================================
// Benchmarks: Solution 3 - Mathematical (Optimal)
// ============================================================================

func BenchmarkSolution3_10(b *testing.B) {
	squares := generateBenchmarkSquares(10)
	solution := &Solution3Mathematical{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

func BenchmarkSolution3_100(b *testing.B) {
	squares := generateBenchmarkSquares(100)
	solution := &Solution3Mathematical{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

func BenchmarkSolution3_1000(b *testing.B) {
	squares := generateBenchmarkSquares(1000)
	solution := &Solution3Mathematical{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}

func BenchmarkSolution3_5000(b *testing.B) {
	squares := generateBenchmarkSquares(5000)
	solution := &Solution3Mathematical{}
	b.ResetTimer()

	for i := 0; i < b.N; i++ {
		solution.SeparateSquares(squares)
	}
}
