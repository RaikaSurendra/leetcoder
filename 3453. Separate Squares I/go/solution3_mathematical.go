package main

import (
	"sort"
)

/*
LeetCode 3453: Separate Squares I - Approach 3 (Mathematical/Event Processing) ⚡ OPTIMAL

Strategy: Process events (square start/end) in sorted order with efficient area tracking

Key Insight:
- Between consecutive critical y-values, the rate of area change is constant
- We can track "active width" (sum of widths of squares being split)
- Area changes linearly: area += active_width × height_delta
- Process events to update active_width efficiently

Algorithm:
1. Create events for each square: (y, START, l) and (y+l, END, l)
2. Sort events by y-coordinate
3. Process events, tracking cumulative area and active width
4. When area crosses target, interpolate to find exact y

Time Complexity: O(n log n)
- Create events: O(n)
- Sort events: O(n log n)
- Process events: O(n)

Space Complexity: O(n) for storing events

Pros: Optimal O(n log n) time, single pass through sorted events
Cons: Most complex to implement, requires careful event handling
*/

// Event represents a square boundary
type Event struct {
	y       float64
	isStart bool
	length  int
}

type Solution3Mathematical struct{}

// SeparateSquares finds minimum y-coordinate where area above = area below
func (s *Solution3Mathematical) SeparateSquares(squares [][]int) float64 {
	// Calculate total area and target
	totalArea := 0.0
	for _, square := range squares {
		l := float64(square[2])
		totalArea += l * l
	}
	target := totalArea / 2.0

	// Create events for all squares
	events := make([]Event, 0, len(squares)*2)
	for _, square := range squares {
		y := float64(square[1])
		l := square[2]

		// Add START event at bottom of square
		events = append(events, Event{y: y, isStart: true, length: l})

		// Add END event at top of square
		events = append(events, Event{y: y + float64(l), isStart: false, length: l})
	}

	// Sort events by y-coordinate
	// If same y, process END events before START events
	sort.Slice(events, func(i, j int) bool {
		if events[i].y != events[j].y {
			return events[i].y < events[j].y
		}
		// At same y: process END (false) before START (true)
		return !events[i].isStart && events[j].isStart
	})

	// Process events
	currentY := 0.0
	areaSoFar := 0.0
	activeWidth := 0.0 // Sum of lengths of squares being split

	for _, event := range events {
		// Calculate area contribution from currentY to event.y
		if event.y > currentY && activeWidth > 0 {
			heightDelta := event.y - currentY
			areaSoFar += activeWidth * heightDelta
		}

		// Check if we crossed the target
		if areaSoFar >= target && currentY < event.y {
			// We crossed target between currentY and event.y
			// Calculate the exact y where area = target
			deficit := target - (areaSoFar - activeWidth*(event.y-currentY))
			additionalHeight := deficit / activeWidth
			return currentY + additionalHeight
		}

		// Update active width based on event type
		if event.isStart {
			// Square starts: now being split by any line above this y
			activeWidth += float64(event.length)
		} else {
			// Square ends: no longer being split by lines above this y
			activeWidth -= float64(event.length)
		}

		currentY = event.y
	}

	// Should not reach here if input is valid
	return currentY
}

// SeparateSquaresAlt is an alternative implementation with more explicit area tracking
func (s *Solution3Mathematical) SeparateSquaresAlt(squares [][]int) float64 {
	// Calculate total area and target
	totalArea := 0.0
	for _, square := range squares {
		l := float64(square[2])
		totalArea += l * l
	}
	target := totalArea / 2.0

	// Create and sort events
	events := make([]Event, 0, len(squares)*2)
	for _, square := range squares {
		y := float64(square[1])
		l := square[2]
		events = append(events, Event{y: y, isStart: true, length: l})
		events = append(events, Event{y: y + float64(l), isStart: false, length: l})
	}

	sort.Slice(events, func(i, j int) bool {
		if events[i].y != events[j].y {
			return events[i].y < events[j].y
		}
		return !events[i].isStart && events[j].isStart
	})

	// Track area below each event y
	prevY := events[0].y
	areaBelow := 0.0
	activeWidth := 0.0

	for _, event := range events {
		// Add area from prevY to event.y
		if event.y > prevY && activeWidth > 0 {
			delta := event.y - prevY
			areaBelow += activeWidth * delta
		}

		// Check if we reached or passed target
		if areaBelow >= target {
			// Backtrack to find exact y
			if activeWidth > 0 && event.y > prevY {
				excess := areaBelow - target
				heightBack := excess / activeWidth
				return event.y - heightBack
			}
			return event.y
		}

		// Update active width
		if event.isStart {
			activeWidth += float64(event.length)
		} else {
			activeWidth -= float64(event.length)
		}

		prevY = event.y
	}

	return prevY
}
