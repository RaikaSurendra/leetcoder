public class PerformanceBenchmark {
    
    public static void main(String[] args) {
        SolutionOptimized solutionOptimized = new SolutionOptimized();
        SolutionGreedy solutionGreedy = new SolutionGreedy();
        
        int[] apple = {1, 3, 2, 5, 10, 8, 4};
        int[] capacity = {4, 3, 1, 5, 2, 10, 15, 8, 6, 12};
        
        int warmupRuns = 10000;
        for (int i = 0; i < warmupRuns; i++) {
            solutionOptimized.minimumBoxes(apple, capacity);
        }
        
        int runs = 100000;
        long startTime = System.nanoTime();
        for (int i = 0; i < runs; i++) {
            solutionOptimized.minimumBoxes(apple, capacity);
        }
        long endTime = System.nanoTime();
        
        double avgTimeMs = (endTime - startTime) / (double) runs / 1_000_000;
        double avgTimeMicros = (endTime - startTime) / (double) runs / 1_000;
        
        System.out.println("=== OPTIMIZED SOLUTION ===");
        System.out.printf("Average time per call: %.6f ms (%.3f microseconds)%n", avgTimeMs, avgTimeMicros);
        System.out.printf("Total runs: %d%n", runs);
        System.out.printf("Result: %d%n%n", solutionOptimized.minimumBoxes(apple, capacity));
        
        for (int i = 0; i < warmupRuns; i++) {
            solutionGreedy.minimumBoxes(apple, capacity);
        }
        
        startTime = System.nanoTime();
        for (int i = 0; i < runs; i++) {
            solutionGreedy.minimumBoxes(apple, capacity);
        }
        endTime = System.nanoTime();
        
        avgTimeMs = (endTime - startTime) / (double) runs / 1_000_000;
        avgTimeMicros = (endTime - startTime) / (double) runs / 1_000;
        
        System.out.println("=== GREEDY SOLUTION ===");
        System.out.printf("Average time per call: %.6f ms (%.3f microseconds)%n", avgTimeMs, avgTimeMicros);
        System.out.printf("Total runs: %d%n", runs);
        System.out.printf("Result: %d%n", solutionGreedy.minimumBoxes(apple, capacity));
    }
}
