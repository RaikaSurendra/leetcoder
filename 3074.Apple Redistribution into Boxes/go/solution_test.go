package solution

import "testing"

func TestMinimumBoxesExample1(t *testing.T) {
	apple := []int{1, 3, 2}
	capacity := []int{4, 3, 1, 5, 2}
	expected := 2

	result := MinimumBoxes(apple, capacity)
	if result != expected {
		t.Errorf("MinimumBoxes() = %d; want %d", result, expected)
	}

	resultOpt := MinimumBoxesOptimized([]int{1, 3, 2}, []int{4, 3, 1, 5, 2})
	if resultOpt != expected {
		t.Errorf("MinimumBoxesOptimized() = %d; want %d", resultOpt, expected)
	}
}

func TestMinimumBoxesExample2(t *testing.T) {
	apple := []int{5, 5, 5}
	capacity := []int{2, 4, 2, 7}
	expected := 4

	result := MinimumBoxes(apple, capacity)
	if result != expected {
		t.Errorf("MinimumBoxes() = %d; want %d", result, expected)
	}

	resultOpt := MinimumBoxesOptimized([]int{5, 5, 5}, []int{2, 4, 2, 7})
	if resultOpt != expected {
		t.Errorf("MinimumBoxesOptimized() = %d; want %d", resultOpt, expected)
	}
}

func TestMinimumBoxesSingleBox(t *testing.T) {
	apple := []int{10}
	capacity := []int{50}
	expected := 1

	result := MinimumBoxes(apple, capacity)
	if result != expected {
		t.Errorf("MinimumBoxes() = %d; want %d", result, expected)
	}

	resultOpt := MinimumBoxesOptimized([]int{10}, []int{50})
	if resultOpt != expected {
		t.Errorf("MinimumBoxesOptimized() = %d; want %d", resultOpt, expected)
	}
}

func TestMinimumBoxesMultipleSmallBoxes(t *testing.T) {
	apple := []int{10, 10, 10}
	capacity := []int{5, 5, 5, 5, 5, 5}
	expected := 6

	result := MinimumBoxes(apple, capacity)
	if result != expected {
		t.Errorf("MinimumBoxes() = %d; want %d", result, expected)
	}

	resultOpt := MinimumBoxesOptimized([]int{10, 10, 10}, []int{5, 5, 5, 5, 5, 5})
	if resultOpt != expected {
		t.Errorf("MinimumBoxesOptimized() = %d; want %d", resultOpt, expected)
	}
}

func TestMinimumBoxesExactFit(t *testing.T) {
	apple := []int{10, 20}
	capacity := []int{15, 15}
	expected := 2

	result := MinimumBoxes(apple, capacity)
	if result != expected {
		t.Errorf("MinimumBoxes() = %d; want %d", result, expected)
	}

	resultOpt := MinimumBoxesOptimized([]int{10, 20}, []int{15, 15})
	if resultOpt != expected {
		t.Errorf("MinimumBoxesOptimized() = %d; want %d", resultOpt, expected)
	}
}

func BenchmarkMinimumBoxes(b *testing.B) {
	apple := []int{1, 3, 2}
	capacity := []int{4, 3, 1, 5, 2}
	for i := 0; i < b.N; i++ {
		MinimumBoxes(apple, capacity)
	}
}

func BenchmarkMinimumBoxesOptimized(b *testing.B) {
	apple := []int{1, 3, 2}
	capacity := []int{4, 3, 1, 5, 2}
	for i := 0; i < b.N; i++ {
		MinimumBoxesOptimized(apple, capacity)
	}
}
