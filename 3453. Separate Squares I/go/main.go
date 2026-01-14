package main

import "fmt"

// Main entry point to run all solution examples
func main() {
	fmt.Println("╔══════════════════════════════════════════════════════════╗")
	fmt.Println("║  LeetCode 3453: Separate Squares I - Go Solutions       ║")
	fmt.Println("╚══════════════════════════════════════════════════════════╝")
	fmt.Println()

	// Run Solution 1: Brute Force
	fmt.Println("┌─────────────────────────────────────────────────────────┐")
	fmt.Println("│ Solution 1: Brute Force (Event-Based Sweep Line)       │")
	fmt.Println("│ Time: O(n²), Space: O(n)                                │")
	fmt.Println("└─────────────────────────────────────────────────────────┘")
	solution1 := &Solution1BruteForce{}

	squares1 := [][]int{{0, 0, 1}, {2, 2, 1}}
	result1_1 := solution1.SeparateSquares(squares1)
	fmt.Printf("Example 1: %.5f (Expected: 1.00000)\n", result1_1)

	squares2 := [][]int{{0, 0, 2}, {1, 1, 1}}
	result1_2 := solution1.SeparateSquares(squares2)
	fmt.Printf("Example 2: %.5f (Expected: 1.16667)\n", result1_2)
	fmt.Println()

	// Run Solution 2: Binary Search (Recommended)
	fmt.Println("┌─────────────────────────────────────────────────────────┐")
	fmt.Println("│ Solution 2: Binary Search ⭐ RECOMMENDED                │")
	fmt.Println("│ Time: O(n), Space: O(1)                                 │")
	fmt.Println("└─────────────────────────────────────────────────────────┘")
	solution2 := &Solution2BinarySearch{}

	result2_1 := solution2.SeparateSquares(squares1)
	fmt.Printf("Example 1: %.5f (Expected: 1.00000)\n", result2_1)

	result2_2 := solution2.SeparateSquares(squares2)
	fmt.Printf("Example 2: %.5f (Expected: 1.16667)\n", result2_2)

	squares3 := [][]int{{0, 0, 2}}
	result2_3 := solution2.SeparateSquares(squares3)
	fmt.Printf("Single square: %.5f (Expected: 1.00000)\n", result2_3)
	fmt.Println()

	// Run Solution 3: Mathematical (Optimal)
	fmt.Println("┌─────────────────────────────────────────────────────────┐")
	fmt.Println("│ Solution 3: Mathematical (Event Processing) ⚡ OPTIMAL  │")
	fmt.Println("│ Time: O(n log n), Space: O(n)                           │")
	fmt.Println("└─────────────────────────────────────────────────────────┘")
	solution3 := &Solution3Mathematical{}

	result3_1 := solution3.SeparateSquares(squares1)
	fmt.Printf("Example 1: %.5f (Expected: 1.00000)\n", result3_1)

	result3_2 := solution3.SeparateSquares(squares2)
	fmt.Printf("Example 2: %.5f (Expected: 1.16667)\n", result3_2)

	result3_3 := solution3.SeparateSquares(squares3)
	fmt.Printf("Single square: %.5f (Expected: 1.00000)\n", result3_3)

	fmt.Println()
	fmt.Println("Testing alternative implementation:")
	fmt.Printf("Example 1 Alt: %.5f\n", solution3.SeparateSquaresAlt(squares1))
	fmt.Printf("Example 2 Alt: %.5f\n", solution3.SeparateSquaresAlt(squares2))

	fmt.Println()
	fmt.Println("✅ All solutions tested successfully!")
}
