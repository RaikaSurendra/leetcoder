import java.util.*;

/**
 * LeetCode 3453: Separate Squares I - Approach 3 (Mathematical/Event Processing) ⚡ OPTIMAL
 * 
 * Strategy: Process events (square start/end) in sorted order with efficient area tracking
 * 
 * Key Insight:
 * - Between consecutive critical y-values, the rate of area change is constant
 * - We can track "active width" (sum of widths of squares being split)
 * - Area changes linearly: area += active_width × height_delta
 * - Process events to update active_width efficiently
 * 
 * Algorithm:
 * 1. Create events for each square: (y, START, l) and (y+l, END, l)
 * 2. Sort events by y-coordinate
 * 3. Process events, tracking cumulative area and active width
 * 4. When area crosses target, interpolate to find exact y
 * 
 * Time Complexity: O(n log n)
 * - Create events: O(n)
 * - Sort events: O(n log n)
 * - Process events: O(n)
 * 
 * Space Complexity: O(n) for storing events
 * 
 * Pros: Optimal O(n log n) time, single pass through sorted events
 * Cons: Most complex to implement, requires careful event handling
 */
public class Solution3Mathematical {
    
    /**
     * Event class to track square boundaries
     */
    private static class Event {
        double y;          // Y-coordinate of event
        boolean isStart;   // true = square starts, false = square ends
        int length;        // Side length of the square
        
        Event(double y, boolean isStart, int length) {
            this.y = y;
            this.isStart = isStart;
            this.length = length;
        }
    }
    
    /**
     * Find minimum y-coordinate where area above = area below
     * 
     * @param squares array where squares[i] = [x, y, l]
     * @return y-coordinate of the dividing line
     */
    public double separateSquares(int[][] squares) {
        // Calculate total area and target
        double totalArea = 0;
        for (int[] square : squares) {
            long l = square[2];
            totalArea += l * l;
        }
        double target = totalArea / 2.0;
        
        // Create events for all squares
        List<Event> events = new ArrayList<>();
        for (int[] square : squares) {
            int y = square[1];
            int l = square[2];
            
            // Add START event at bottom of square
            events.add(new Event(y, true, l));
            
            // Add END event at top of square
            events.add(new Event(y + l, false, l));
        }
        
        // Sort events by y-coordinate
        // If same y, process END events before START events
        // (to handle squares that touch exactly)
        Collections.sort(events, (a, b) -> {
            if (a.y != b.y) {
                return Double.compare(a.y, b.y);
            }
            // At same y: process END before START
            if (a.isStart != b.isStart) {
                return a.isStart ? 1 : -1;
            }
            return 0;
        });
        
        // Process events
        double currentY = 0;
        double areaSoFar = 0;
        long activeWidth = 0;  // Sum of lengths of squares being split
        
        for (Event event : events) {
            // Calculate area contribution from currentY to event.y
            if (event.y > currentY && activeWidth > 0) {
                double heightDelta = event.y - currentY;
                areaSoFar += activeWidth * heightDelta;
            }
            
            // Check if we crossed the target
            if (areaSoFar >= target && currentY < event.y) {
                // We crossed target between currentY and event.y
                // Calculate the exact y where area = target
                double deficit = target - (areaSoFar - activeWidth * (event.y - currentY));
                double additionalHeight = deficit / activeWidth;
                return currentY + additionalHeight;
            }
            
            // Update active width based on event type
            if (event.isStart) {
                // Square starts: now being split by any line above this y
                activeWidth += event.length;
            } else {
                // Square ends: no longer being split by lines above this y
                activeWidth -= event.length;
            }
            
            currentY = event.y;
        }
        
        // Should not reach here if input is valid
        return currentY;
    }
    
    /**
     * Alternative implementation with more explicit area tracking
     */
    public double separateSquaresAlt(int[][] squares) {
        // Calculate total area and target
        double totalArea = 0;
        for (int[] square : squares) {
            long l = square[2];
            totalArea += l * l;
        }
        double target = totalArea / 2.0;
        
        // Create and sort events
        List<Event> events = new ArrayList<>();
        for (int[] square : squares) {
            int y = square[1];
            int l = square[2];
            events.add(new Event(y, true, l));
            events.add(new Event(y + l, false, l));
        }
        
        Collections.sort(events, (a, b) -> {
            int cmp = Double.compare(a.y, b.y);
            if (cmp != 0) return cmp;
            return a.isStart ? 1 : -1;
        });
        
        // Track area below each event y
        double prevY = events.get(0).y;
        double areaBelow = 0;
        long activeWidth = 0;
        
        for (Event event : events) {
            // Add area from prevY to event.y
            if (event.y > prevY && activeWidth > 0) {
                double delta = event.y - prevY;
                areaBelow += activeWidth * delta;
            }
            
            // Check if we reached or passed target
            if (areaBelow >= target) {
                // Backtrack to find exact y
                if (activeWidth > 0 && event.y > prevY) {
                    double excess = areaBelow - target;
                    double heightBack = excess / activeWidth;
                    return event.y - heightBack;
                }
                return event.y;
            }
            
            // Update active width
            if (event.isStart) {
                activeWidth += event.length;
            } else {
                activeWidth -= event.length;
            }
            
            prevY = event.y;
        }
        
        return prevY;
    }
    
    // Test method
    public static void main(String[] args) {
        Solution3Mathematical solution = new Solution3Mathematical();
        
        // Example 1: [[0,0,1],[2,2,1]]
        int[][] squares1 = {{0, 0, 1}, {2, 2, 1}};
        double result1 = solution.separateSquares(squares1);
        System.out.printf("Example 1: %.5f (Expected: 1.00000)%n", result1);
        
        // Example 2: [[0,0,2],[1,1,1]]
        int[][] squares2 = {{0, 0, 2}, {1, 1, 1}};
        double result2 = solution.separateSquares(squares2);
        System.out.printf("Example 2: %.5f (Expected: 1.16667)%n", result2);
        
        // Single square
        int[][] squares3 = {{0, 0, 2}};
        double result3 = solution.separateSquares(squares3);
        System.out.printf("Single square: %.5f (Expected: 1.00000)%n", result3);
        
        // Test alternative implementation
        System.out.println("\nTesting alternative implementation:");
        System.out.printf("Example 1 Alt: %.5f%n", solution.separateSquaresAlt(squares1));
        System.out.printf("Example 2 Alt: %.5f%n", solution.separateSquaresAlt(squares2));
    }
}
