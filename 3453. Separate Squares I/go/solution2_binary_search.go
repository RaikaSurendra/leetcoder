package main

import (
	"math"
)

/*
LeetCode 3453: Separate Squares I - Approach 2 (Binary Search) ⭐ RECOMMENDED

Strategy: Use binary search on y-coordinate, leveraging monotonic property

Key Insight:
- As line height h increases, area below increases monotonically
- As line height h increases, area above decreases monotonically
- This monotonic property enables binary search!

Algorithm:
1. Calculate total area and target (total_area / 2)
2. Find y-range: [min(y), max(y+l)]
3. Binary search for h where area_below(h) = target
4. Each iteration: O(n) to calculate area
5. ~20 iterations for precision 10^-6

Time Complexity: O(n log(range/ε)) ≈ O(n)
- Binary search: O(log(10^9 / 10^-6)) ≈ O(20) iterations
- Each iteration: O(n) to calculate area
- Total: O(20n) = O(n)

Space Complexity: O(1) - only constant extra space

Pros: Fast, simple, scales well for n = 50,000
Cons: Requires careful floating-point precision handling
*/

const epsilon = 1e-6 // Precision requirement

type Solution2BinarySearch struct{}

// SeparateSquares finds minimum y-coordinate where area above = area below
func (s *Solution2BinarySearch) SeparateSquares(squares [][]int) float64 {
	// Calculate total area
	totalArea := 0.0
	for _, square := range squares {
		l := float64(square[2])
		totalArea += l * l
	}

	target := totalArea / 2.0

	// Find search bounds
	// low = minimum y-coordinate (bottom of lowest square)
	// high = maximum y-coordinate (top of highest square)
	low := math.MaxFloat64
	high := -math.MaxFloat64

	for _, square := range squares {
		y := float64(square[1])
		l := float64(square[2])
		low = math.Min(low, y)
		high = math.Max(high, y+l)
	}

	// Binary search for the line position
	// Invariant: area_below(low) < target <= area_below(high)
	for high-low > epsilon {
		mid := (low + high) / 2.0
		areaBelow := s.calculateAreaBelow(squares, mid)

		if areaBelow < target {
			// Need to move line higher to increase area below
			low = mid
		} else {
			// Area below >= target, can potentially move lower
			high = mid
		}
	}

	// Return the midpoint of converged interval
	return (low + high) / 2.0
}

// calculateAreaBelow calculates total area of squares below a horizontal line at height h
//
// For each square [x, y, l]:
// - If h <= y: square entirely above line → area = 0
// - If h >= y + l: square entirely below line → area = l²
// - If y < h < y + l: square split → area = l × (h - y)
func (s *Solution2BinarySearch) calculateAreaBelow(squares [][]int, h float64) float64 {
	total := 0.0

	for _, square := range squares {
		y := float64(square[1])
		l := float64(square[2])

		if h <= y {
			// Square entirely above line
			// Contribution: 0
			continue
		} else if h >= y+l {
			// Square entirely below line
			// Contribution: full area = l²
			total += l * l
		} else {
			// Square split by line (y < h < y + l)
			// Area below = width × height_below
			// height_below = h - y
			heightBelow := h - y
			total += l * heightBelow
		}
	}

	return total
}
