package solution

import "sort"

// MinimumBoxes - Greedy approach with sorting
//
// Algorithm:
// Since apples can be split across boxes, we only care about total apple count.
// To minimize boxes used, always pick the largest capacity boxes first (greedy).
//
// Steps:
// 1. Calculate total apples needed to pack
// 2. Sort capacities in descending order (largest first)
// 3. Greedily pick boxes from largest to smallest until all apples are packed
//
// Time Complexity: O(n + m log m)
//   - O(n) to sum all apples
//   - O(m log m) to sort capacities
//   - O(m) to iterate through capacities
//
// Space Complexity: O(1) extra space
//   - Sorting is done in-place (ignoring sort's stack space)
//
// Example:
//
//	apple = [1, 3, 2], capacity = [4, 3, 1, 5, 2]
//	total = 6
//	sorted capacity = [5, 4, 3, 2, 1]
//	Pick 5: total = 1, Pick 4: total = -3 (done) → return 2
func MinimumBoxes(apple []int, capacity []int) int {
	// Step 1: Calculate total apples to pack
	total := 0
	for _, num := range apple {
		total += num
	}

	// Step 2: Sort capacities in descending order (largest first)
	sort.Slice(capacity, func(i, j int) bool {
		return capacity[i] > capacity[j]
	})

	// Step 3: Greedily pick boxes until all apples are packed
	for i, num := range capacity {
		total -= num
		if total <= 0 {
			return i + 1 // Return 1-indexed count
		}
	}

	return len(capacity)
}
