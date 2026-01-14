import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * JMH Benchmark for LeetCode 3453: Separate Squares I
 * 
 * Compares performance of all 3 solution approaches:
 * 1. Brute Force (O(n²))
 * 2. Binary Search (O(n)) - Recommended
 * 3. Mathematical (O(n log n)) - Optimal
 * 
 * Run with: mvn clean package && java -jar target/benchmarks.jar
 * 
 * Or programmatically: java -cp target/benchmarks.jar SolutionBenchmark
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class SolutionBenchmark {
    
    @Param({"10", "100", "1000", "5000"})
    private int n;
    
    private int[][] smallSquares;
    private int[][] mediumSquares;
    private int[][] largeSquares;
    private int[][] maxSquares;
    
    private Solution1BruteForce solution1;
    private Solution2BinarySearch solution2;
    private Solution3Mathematical solution3;
    
    @Setup(Level.Trial)
    public void setup() {
        solution1 = new Solution1BruteForce();
        solution2 = new Solution2BinarySearch();
        solution3 = new Solution3Mathematical();
        
        // Generate test data of various sizes
        smallSquares = generateRandomSquares(10);
        mediumSquares = generateRandomSquares(100);
        largeSquares = generateRandomSquares(1000);
        maxSquares = generateRandomSquares(n);
    }
    
    /**
     * Generate random test squares
     * Coordinates: [0, 1000], Length: [1, 100]
     */
    private int[][] generateRandomSquares(int count) {
        Random rand = new Random(42); // Fixed seed for reproducibility
        int[][] squares = new int[count][3];
        
        for (int i = 0; i < count; i++) {
            squares[i][0] = rand.nextInt(1001);  // x: 0-1000
            squares[i][1] = rand.nextInt(1001);  // y: 0-1000
            squares[i][2] = rand.nextInt(100) + 1; // l: 1-100
        }
        
        return squares;
    }
    
    // ========================================================================
    // Benchmarks: Solution 1 - Brute Force
    // ========================================================================
    
    @Benchmark
    public double solution1_small() {
        return solution1.separateSquares(smallSquares);
    }
    
    @Benchmark
    public double solution1_medium() {
        return solution1.separateSquares(mediumSquares);
    }
    
    @Benchmark
    public double solution1_large() {
        return solution1.separateSquares(largeSquares);
    }
    
    @Benchmark
    public double solution1_parameterized() {
        return solution1.separateSquares(maxSquares);
    }
    
    // ========================================================================
    // Benchmarks: Solution 2 - Binary Search (Recommended)
    // ========================================================================
    
    @Benchmark
    public double solution2_small() {
        return solution2.separateSquares(smallSquares);
    }
    
    @Benchmark
    public double solution2_medium() {
        return solution2.separateSquares(mediumSquares);
    }
    
    @Benchmark
    public double solution2_large() {
        return solution2.separateSquares(largeSquares);
    }
    
    @Benchmark
    public double solution2_parameterized() {
        return solution2.separateSquares(maxSquares);
    }
    
    // ========================================================================
    // Benchmarks: Solution 3 - Mathematical (Optimal)
    // ========================================================================
    
    @Benchmark
    public double solution3_small() {
        return solution3.separateSquares(smallSquares);
    }
    
    @Benchmark
    public double solution3_medium() {
        return solution3.separateSquares(mediumSquares);
    }
    
    @Benchmark
    public double solution3_large() {
        return solution3.separateSquares(largeSquares);
    }
    
    @Benchmark
    public double solution3_parameterized() {
        return solution3.separateSquares(maxSquares);
    }
    
    // ========================================================================
    // Main Method - Run Benchmarks Programmatically
    // ========================================================================
    
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SolutionBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(3)
                .measurementIterations(5)
                .build();
        
        new Runner(opt).run();
    }
}
