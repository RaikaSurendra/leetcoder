package solution

import "sort"

// MinimumBoxes - Greedy approach with sorting
// Time: O(n + m log m)
// Space: O(1) extra
func MinimumBoxes(apple []int, capacity []int) int {
	totalApples := 0
	for _, a := range apple {
		totalApples += a
	}

	// Sort in descending order
	sort.Slice(capacity, func(i, j int) bool {
		return capacity[i] > capacity[j]
	})

	used := 0
	current := 0
	for _, c := range capacity {
		current += c
		used++
		if current >= totalApples {
			return used
		}
	}

	return used
}

// MinimumBoxesOptimized - Frequency counting approach
// Time: O(n + m + 50)
// Space: O(50)
func MinimumBoxesOptimized(apple []int, capacity []int) int {
	totalApples := 0
	for _, a := range apple {
		totalApples += a
	}

	freq := make([]int, 51)
	for _, c := range capacity {
		freq[c]++
	}

	used := 0
	current := 0
	for c := 50; c >= 1; c-- {
		for freq[c] > 0 && current < totalApples {
			current += c
			used++
			freq[c]--
		}
		if current >= totalApples {
			break
		}
	}

	return used
}
