/**
 * LeetCode 3453: Separate Squares I - C Solution (LeetCode Submission Version)
 * 
 * Strategy: Binary Search with Memory and Runtime Optimization
 * Time Complexity: O(n × log(range/ε)) ≈ O(n)
 * Space Complexity: O(1)
 */

#include <stdlib.h>
#include <float.h>

#define EPSILON 1e-6

// Square struct for cache-friendly memory layout
typedef struct {
    int x;
    int y;
    int l;
} Square;

/**
 * Calculate area below horizontal line at height h
 */
static inline double calculate_area_below(
    const Square* squares, 
    const int n, 
    const double h
) {
    double total = 0.0;
    
    for (int i = 0; i < n; i++) {
        const int y = squares[i].y;
        const int l = squares[i].l;
        
        // Early exit for squares entirely above line
        if (h <= y) {
            continue;
        }
        
        // Square entirely below line
        if (h >= y + l) {
            total += (double)l * l;
        } else {
            // Square split by line
            const double height_below = h - y;
            total += l * height_below;
        }
    }
    
    return total;
}

/**
 * Binary search for optimal line position
 */
double separate_squares_optimized(const Square* squares, const int n) {
    // Calculate total area and target
    double total_area = 0.0;
    double y_min = DBL_MAX;
    double y_max = -DBL_MAX;
    
    for (int i = 0; i < n; i++) {
        const int y = squares[i].y;
        const int l = squares[i].l;
        const double l_squared = (double)l * l;
        
        total_area += l_squared;
        
        if (y < y_min) y_min = y;
        const double y_top = y + l;
        if (y_top > y_max) y_max = y_top;
    }
    
    const double target = total_area * 0.5;
    
    // Binary search for line position
    double low = y_min;
    double high = y_max;
    
    while (high - low > EPSILON) {
        const double mid = (low + high) * 0.5;
        const double area_below = calculate_area_below(squares, n, mid);
        
        if (area_below < target) {
            low = mid;
        } else {
            high = mid;
        }
    }
    
    return (low + high) * 0.5;
}

/**
 * LeetCode submission function - matches expected signature
 */
double separateSquares(int** squares, int squaresSize, int* squaresColSize) {
    // Stack allocation for small n, heap for large
    Square* square_structs;
    Square stack_squares[1024];
    
    if (squaresSize <= 1024) {
        square_structs = stack_squares;
    } else {
        square_structs = (Square*)malloc(squaresSize * sizeof(Square));
        if (!square_structs) return -1.0;
    }
    
    // Convert input format
    for (int i = 0; i < squaresSize; i++) {
        square_structs[i].x = squares[i][0];
        square_structs[i].y = squares[i][1];
        square_structs[i].l = squares[i][2];
    }
    
    double result = separate_squares_optimized(square_structs, squaresSize);
    
    if (squaresSize > 1024) {
        free(square_structs);
    }
    
    return result;
}
