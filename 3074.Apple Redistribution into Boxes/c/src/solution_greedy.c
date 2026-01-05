#include "../include/solution_greedy.h"
#include <stdlib.h>

/**
 * @brief Comparison function for qsort - sorts in descending order
 */
static int compare_desc(const void* a, const void* b) {
    return (*(int*)b - *(int*)a);
}

int minimumBoxes(int* apple, int appleSize, int* capacity, int capacitySize) {
    // Step 1: Calculate total apples to pack
    int total_apples = 0;
    for (int i = 0; i < appleSize; i++) {
        total_apples += apple[i];
    }
    
    // Step 2: Sort capacities in descending order (largest first)
    qsort(capacity, capacitySize, sizeof(int), compare_desc);
    
    // Step 3: Greedily pick boxes until all apples are packed
    int used = 0;
    int current = 0;
    
    for (int i = 0; i < capacitySize; i++) {
        current += capacity[i];
        used++;
        if (current >= total_apples) {
            return used;
        }
    }
    
    return used; // All boxes used (shouldn't reach per problem constraints)
}
