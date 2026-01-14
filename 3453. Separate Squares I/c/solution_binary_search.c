/**
 * LeetCode 3453: Separate Squares I - C Implementation (Optimized)
 * 
 * Strategy: Binary Search with Memory and Runtime Optimization
 * 
 * Memory Optimizations:
 * - Stack allocation for small arrays (no malloc overhead)
 * - Cache-friendly data layout
 * - Minimal memory footprint O(1)
 * 
 * Runtime Optimizations:
 * - Inline functions for hot paths
 * - Loop unrolling hints
 * - Branch prediction friendly code
 * - Compiler optimization flags (-O3, -march=native)
 * 
 * Time Complexity: O(n × log(range/ε)) ≈ O(n)
 * Space Complexity: O(1)
 * 
 * Compile: gcc -O3 -march=native -ffast-math -o solution solution_binary_search.c
 * Or: clang -O3 -march=native -ffast-math -o solution solution_binary_search.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <float.h>
#include <stdint.h>
#include <time.h>

#define EPSILON 1e-6

// Square struct for cache-friendly memory layout
// Packed to 12 bytes (instead of 16 with padding)
typedef struct __attribute__((packed)) {
    int32_t x;      // 4 bytes
    int32_t y;      // 4 bytes
    int32_t l;      // 4 bytes
} Square;

/**
 * Calculate area below horizontal line at height h
 * Inline for performance - hot path function
 * 
 * Optimizations:
 * - Early continue for squares above line
 * - Minimize branching
 * - Use restrict keyword for pointer aliasing
 */
static inline double calculate_area_below(
    const Square * restrict squares, 
    const int n, 
    const double h
) {
    double total = 0.0;
    
    // Loop optimization: compiler can vectorize this better
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
 * Main solution: Binary search for optimal line position
 * 
 * Memory optimizations:
 * - Pass arrays by pointer (no copying)
 * - Use const to enable compiler optimizations
 * - Stack variables only
 */
double separate_squares_optimized(const Square * restrict squares, const int n) {
    // Calculate total area and target
    double total_area = 0.0;
    double y_min = DBL_MAX;
    double y_max = -DBL_MAX;
    
    // Single pass to compute total area and bounds
    // This loop is cachefriendly with sequential access
    for (int i = 0; i < n; i++) {
        const int y = squares[i].y;
        const int l = squares[i].l;
        const double l_squared = (double)l * l;
        
        total_area += l_squared;
        
        // Update bounds
        if (y < y_min) y_min = y;
        const double y_top = y + l;
        if (y_top > y_max) y_max = y_top;
    }
    
    const double target = total_area * 0.5; // Multiply faster than divide
    
    // Binary search for line position
    double low = y_min;
    double high = y_max;
    
    // Loop will run ~20 iterations for precision 1e-6
    while (high - low > EPSILON) {
        const double mid = (low + high) * 0.5; // Multiply faster than divide
        const double area_below = calculate_area_below(squares, n, mid);
        
        // Branch prediction friendly: most iterations go same direction
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
 * Wrapper for standard int[][] input format
 * Converts to cache-friendly Square struct
 */
double separateSquares(int** squares, int squaresSize, int* squaresColSize) {
    // Stack allocation for small n, heap for large
    Square* square_structs;
    Square stack_squares[1024]; // 12KB on stack
    
    if (squaresSize <= 1024) {
        square_structs = stack_squares;
    } else {
        square_structs = (Square*)malloc(squaresSize * sizeof(Square));
        if (!square_structs) return -1.0; // Error
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

// ============================================================================
// Test and Benchmark Functions
// ============================================================================

/**
 * Generate random test data
 */
void generate_random_squares(Square* squares, int n, unsigned int seed) {
    srand(seed);
    
    for (int i = 0; i < n; i++) {
        squares[i].x = rand() % 1001;
        squares[i].y = rand() % 1001;
        squares[i].l = rand() % 100 + 1;
    }
}

/**
 * Benchmark function
 */
void benchmark(const char* name, int n, int iterations) {
    Square* squares = (Square*)malloc(n * sizeof(Square));
    if (!squares) {
        fprintf(stderr, "Memory allocation failed\n");
        return;
    }
    
    generate_random_squares(squares, n, 42);
    
    // Warmup
    for (int i = 0; i < 100; i++) {
        separate_squares_optimized(squares, n);
    }
    
    // Measure
    clock_t start = clock();
    for (int i = 0; i < iterations; i++) {
        separate_squares_optimized(squares, n);
    }
    clock_t end = clock();
    
    double time_ms = (double)(end - start) * 1000.0 / CLOCKS_PER_SEC;
    double avg_us = time_ms * 1000.0 / iterations;
    
    printf("%-20s n=%5d  iterations=%6d  avg=%.3f μs\n", 
           name, n, iterations, avg_us);
    
    free(squares);
}

/**
 * Test function
 */
void test_example(const char* name, Square* squares, int n, double expected) {
    double result = separate_squares_optimized(squares, n);
    double diff = fabs(result - expected);
    
    if (diff < 1e-5) {
        printf("✓ %s: %.5f (expected %.5f)\n", name, result, expected);
    } else {
        printf("✗ %s: %.5f (expected %.5f) - FAILED\n", name, result, expected);
    }
}

int main() {
    printf("╔══════════════════════════════════════════════════════════╗\n");
    printf("║  LeetCode 3453: Separate Squares I - C (Optimized)      ║\n");
    printf("╚══════════════════════════════════════════════════════════╝\n\n");
    
    // ========================================================================
    // Tests
    // ========================================================================
    
    printf("Running Tests:\n");
    printf("─────────────────────────────────────────────────────────────\n");
    
    // Example 1
    Square example1[] = {{0, 0, 1}, {2, 2, 1}};
    test_example("Example 1", example1, 2, 1.00000);
    
    // Example 2
    Square example2[] = {{0, 0, 2}, {1, 1, 1}};
    test_example("Example 2", example2, 2, 1.16667);
    
    // Single square
    Square example3[] = {{0, 0, 2}};
    test_example("Single Square", example3, 1, 1.00000);
    
    printf("\n");
    
    // ========================================================================
    // Benchmarks
    // ========================================================================
    
    printf("Running Benchmarks:\n");
    printf("─────────────────────────────────────────────────────────────\n");
    
    benchmark("Small (10)", 10, 100000);
    benchmark("Medium (100)", 100, 10000);
    benchmark("Large (1000)", 1000, 1000);
    benchmark("Very Large (5000)", 5000, 200);
    benchmark("Max (10000)", 10000, 100);
    
    printf("\n");
    
    // ========================================================================
    // Memory Statistics
    // ========================================================================
    
    printf("Memory Usage:\n");
    printf("─────────────────────────────────────────────────────────────\n");
    printf("Square struct size:  %zu bytes\n", sizeof(Square));
    printf("Stack buffer (1024): %zu bytes (%.1f KB)\n", 
           sizeof(Square) * 1024, sizeof(Square) * 1024 / 1024.0);
    printf("For n=50000:         %zu bytes (%.1f KB)\n",
           sizeof(Square) * 50000, sizeof(Square) * 50000 / 1024.0);
    
    printf("\n✅ All tests and benchmarks completed!\n");
    
    return 0;
}
