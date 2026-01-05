#ifndef SOLUTION_GREEDY_H
#define SOLUTION_GREEDY_H

/**
 * @file solution_greedy.h
 * @brief Greedy Solution using Sorting
 * 
 * Problem: Find minimum number of boxes needed to redistribute all apples.
 * Approach: Since apples can be split across boxes, only total count matters.
 *           Greedily select largest capacity boxes first to minimize count.
 */

/**
 * @brief Finds minimum boxes needed using greedy sorting approach.
 * 
 * Algorithm:
 * 1. Calculate total number of apples to pack
 * 2. Sort capacities in descending order (largest first)
 * 3. Iterate through sorted capacities, accumulating capacity
 * 4. Stop when accumulated capacity >= total apples
 * 
 * Intuition:
 * - Apples can be split, so we only care about total capacity needed
 * - Larger boxes pack more apples per box → fewer boxes needed
 * - Greedy choice: always pick largest available capacity
 * 
 * Time Complexity: O(n + m log m)
 *   - O(n) to sum apple array
 *   - O(m log m) to sort capacity array (qsort)
 *   - O(m) worst case to iterate through capacities
 * 
 * Space Complexity: O(1) extra space
 *   - Sorting is done in-place (ignoring sort's stack)
 *   - Only constant extra variables used
 * 
 * Example:
 *   apple = [1, 3, 2], appleSize = 3
 *   capacity = [4, 3, 1, 5, 2], capacitySize = 5
 *   total_apples = 6
 *   sorted capacity = [5, 4, 3, 2, 1]
 *   Pick 5: current=5, Pick 4: current=9 (≥6, done!)
 *   Return 2 boxes
 * 
 * @param apple Array where apple[i] is apples in pack i
 * @param appleSize Size of apple array
 * @param capacity Array where capacity[i] is max capacity of box i
 * @param capacitySize Size of capacity array
 * @return Minimum number of boxes needed
 */
int minimumBoxes(int* apple, int appleSize, int* capacity, int capacitySize);

#endif // SOLUTION_GREEDY_H
