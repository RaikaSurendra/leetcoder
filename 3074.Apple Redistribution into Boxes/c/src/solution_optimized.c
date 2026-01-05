#include "../include/solution_optimized.h"

/**
 * @brief Helper macro for minimum of two values
 */
#define MIN(a, b) ((a) < (b) ? (a) : (b))

int minimumBoxesOptimized(int* apple, int appleSize, int* capacity, int capacitySize) {
    // Step 1: Calculate total apples to pack
    int total_apples = 0;
    for (int i = 0; i < appleSize; i++) {
        total_apples += apple[i];
    }
    
    // Step 2: Build frequency array and track max capacity
    int max_cap = 0;
    int freq[51] = {0}; // freq[i] = count of boxes with capacity i
    
    for (int i = 0; i < capacitySize; i++) {
        freq[capacity[i]]++;
        if (capacity[i] > max_cap) {
            max_cap = capacity[i];
        }
    }
    
    // Step 3: Greedily take boxes from largest to smallest capacity
    int used = 0;
    int current = 0;
    
    for (int c = max_cap; c >= 1 && current < total_apples; c--) {
        int count = freq[c];
        if (count > 0) {
            int remaining = total_apples - current;
            
            // Ceiling division: ceil(remaining / c) = (remaining + c - 1) / c
            int needed = (remaining + c - 1) / c;
            
            // Take minimum of what we need and what's available
            int take = MIN(needed, count);
            
            // Batch process: take multiple boxes at once
            current += take * c;
            used += take;
        }
    }
    
    return used;
}
