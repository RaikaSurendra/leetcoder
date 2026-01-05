#include <stdio.h>
#include <assert.h>
#include <string.h>
#include "../include/solution_greedy.h"
#include "../include/solution_optimized.h"

/**
 * @brief Test helper to run a single test case
 */
void run_test(const char* test_name, 
              int* apple, int appleSize,
              int* capacity_greedy, int* capacity_optimized, int capacitySize,
              int expected) {
    int result_greedy = minimumBoxes(apple, appleSize, capacity_greedy, capacitySize);
    int result_optimized = minimumBoxesOptimized(apple, appleSize, capacity_optimized, capacitySize);
    
    printf("Test %s:\n", test_name);
    printf("  Greedy result: %d, Optimized result: %d, Expected: %d\n", 
           result_greedy, result_optimized, expected);
    
    assert(result_greedy == expected);
    assert(result_optimized == expected);
    
    printf("  ✓ PASSED\n\n");
}

int main() {
    printf("Running Apple Redistribution Tests...\n\n");
    
    // Test 1: Example 1
    {
        int apple[] = {1, 3, 2};
        int capacity_greedy[] = {4, 3, 1, 5, 2};
        int capacity_optimized[] = {4, 3, 1, 5, 2};
        run_test("Example 1", apple, 3, capacity_greedy, capacity_optimized, 5, 2);
    }
    
    // Test 2: Example 2
    {
        int apple[] = {5, 5, 5};
        int capacity_greedy[] = {2, 4, 2, 7};
        int capacity_optimized[] = {2, 4, 2, 7};
        run_test("Example 2", apple, 3, capacity_greedy, capacity_optimized, 4, 4);
    }
    
    // Test 3: Single box
    {
        int apple[] = {10};
        int capacity_greedy[] = {50};
        int capacity_optimized[] = {50};
        run_test("Single Box", apple, 1, capacity_greedy, capacity_optimized, 1, 1);
    }
    
    // Test 4: Multiple small boxes
    {
        int apple[] = {10, 10, 10};
        int capacity_greedy[] = {5, 5, 5, 5, 5, 5};
        int capacity_optimized[] = {5, 5, 5, 5, 5, 5};
        run_test("Multiple Small Boxes", apple, 3, capacity_greedy, capacity_optimized, 6, 6);
    }
    
    // Test 5: Exact fit
    {
        int apple[] = {10, 20};
        int capacity_greedy[] = {15, 15};
        int capacity_optimized[] = {15, 15};
        run_test("Exact Fit", apple, 2, capacity_greedy, capacity_optimized, 2, 2);
    }
    
    printf("All tests passed! ✓\n");
    return 0;
}
