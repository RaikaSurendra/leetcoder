package main

import (
	"sort"
)

/*
LeetCode 3453: Separate Squares I - Approach 1 (Brute Force/Event-Based)

Strategy: Sweep line through all critical y-coordinates where squares start/end.

Algorithm:
1. Collect all critical y-values (bottom and top of each square)
2. Calculate total area and target (total_area / 2)
3. For each critical y-value in sorted order:
   - Calculate area below that y-value
   - If area >= target, interpolate to find exact y

Time Complexity: O(n²)
- Collecting critical points: O(n)
- Sorting: O(n log n)
- For each critical point O(n), calculate area O(n): O(n²)

Space Complexity: O(n) for storing critical points

Pros: Simple to understand and implement
Cons: Too slow for n = 50,000 (may TLE)
*/

type Solution1BruteForce struct{}

// SeparateSquares finds minimum y-coordinate where area above = area below
func (s *Solution1BruteForce) SeparateSquares(squares [][]int) float64 {
	// Calculate total area
	totalArea := 0.0
	for _, square := range squares {
		l := float64(square[2])
		totalArea += l * l
	}

	target := totalArea / 2.0

	// Collect all critical y-coordinates using map to avoid duplicates
	criticalYMap := make(map[float64]bool)
	for _, square := range squares {
		y := float64(square[1])
		l := float64(square[2])
		criticalYMap[y] = true   // Bottom of square
		criticalYMap[y+l] = true // Top of square
	}

	// Convert map to sorted slice
	criticalY := make([]float64, 0, len(criticalYMap))
	for y := range criticalYMap {
		criticalY = append(criticalY, y)
	}
	sort.Float64s(criticalY)

	// Sweep through critical y-values
	prevY := criticalY[0]
	prevArea := s.calculateAreaBelow(squares, prevY)

	// If first critical point already exceeds target
	if prevArea >= target {
		return prevY
	}

	for i := 1; i < len(criticalY); i++ {
		currY := criticalY[i]
		currArea := s.calculateAreaBelow(squares, currY)

		// Found the interval where area crosses target
		if currArea >= target {
			// Binary search for exact y in this interval
			return s.interpolate(squares, prevY, currY, target)
		}

		prevY = currY
		prevArea = currArea
	}

	// Should not reach here if input is valid
	return criticalY[len(criticalY)-1]
}

// calculateAreaBelow calculates total area of squares below a horizontal line at height h
func (s *Solution1BruteForce) calculateAreaBelow(squares [][]int, h float64) float64 {
	total := 0.0

	for _, square := range squares {
		y := float64(square[1])
		l := float64(square[2])

		if h <= y {
			// Square entirely above line: contributes 0
			continue
		} else if h >= y+l {
			// Square entirely below line: contributes full area
			total += l * l
		} else {
			// Square split by line
			heightBelow := h - y
			total += l * heightBelow
		}
	}

	return total
}

// interpolate finds exact y where area equals target using binary search
func (s *Solution1BruteForce) interpolate(squares [][]int, low, high, target float64) float64 {
	epsilon := 1e-7

	for high-low > epsilon {
		mid := (low + high) / 2.0
		midArea := s.calculateAreaBelow(squares, mid)

		if midArea < target {
			low = mid
		} else {
			high = mid
		}
	}

	return (low + high) / 2.0
}
