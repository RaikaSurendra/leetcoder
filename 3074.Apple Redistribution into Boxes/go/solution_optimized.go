package solution

// MinimumBoxesOptimized - Frequency counting approach (avoids sorting)
//
// Algorithm:
// Exploits the constraint that capacity[i] <= 50. Instead of sorting,
// we count frequency of each capacity value and process from largest to smallest.
//
// Key Optimization:
// - When we need multiple boxes of same capacity, calculate and take them all at once
// - Uses ceiling division to find exact boxes needed: (remaining + c - 1) / c
// - Only iterate through 1-50 range, not the full capacity array
//
// Steps:
// 1. Calculate total apples to pack
// 2. Build frequency array: freq[c] = count of boxes with capacity c
// 3. Track max capacity to avoid checking empty slots
// 4. Iterate from max capacity down to 1:
//   - Calculate how many boxes of this capacity we need
//   - Take min(needed, available)
//   - Update running total
//
// Time Complexity: O(n + m + 50)
//   - O(n) to sum all apples
//   - O(m) to build frequency array
//   - O(50) to iterate through capacities (constant!)
//   - Much faster than O(m log m) sorting
//
// Space Complexity: O(50) = O(1) constant space
//   - Fixed-size frequency array
//   - Zero heap allocations after array creation
//
// Example:
//
//	apple = [1, 3, 2], capacity = [4, 3, 1, 5, 2]
//	total = 6, freq = {1:1, 2:1, 3:1, 4:1, 5:1}, maxCap = 5
//	c=5: remaining=6, needed=ceil(6/5)=2, take=min(2,1)=1 → used=1, current=5
//	c=4: remaining=1, needed=ceil(1/4)=1, take=min(1,1)=1 → used=2, current=9 (done)
func MinimumBoxesOptimized(apple []int, capacity []int) int {
	// Step 1: Calculate total apples to pack
	totalApples := 0
	for _, a := range apple {
		totalApples += a
	}

	// Step 2: Build frequency array and track max capacity
	maxCap := 0
	freq := make([]int, 51) // freq[i] = count of boxes with capacity i
	for _, c := range capacity {
		freq[c]++
		if c > maxCap {
			maxCap = c
		}
	}

	// Step 3: Greedily take boxes from largest to smallest capacity
	used := 0
	current := 0
	for c := maxCap; c >= 1 && current < totalApples; c-- {
		count := freq[c]
		if count > 0 {
			remaining := totalApples - current

			// Calculate exact boxes needed using ceiling division
			// (remaining + c - 1) / c is equivalent to ceil(remaining / c)
			needed := (remaining + c - 1) / c

			// Take minimum of what we need and what's available
			take := needed
			if take > count {
				take = count
			}

			// Update totals (multiply to take multiple boxes at once)
			current += take * c
			used += take
		}
	}

	return used
}
