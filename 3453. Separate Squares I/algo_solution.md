# 3453. Separate Squares I - Solution Analysis

## Problem Understanding

### Visual Representation

Given squares with bottom-left corner `(x, y)` and side length `l`:
- **Bottom-left corner**: `(x, y)`
- **Top-right corner**: `(x + l, y + l)`
- **Area of each square**: `l²`

A horizontal line at y-coordinate `h` divides each square into:
- **Area below line**: portion of square where `y ≤ h < y + l`
- **Area above line**: portion of square where `h < y + l`

### Key Observations

1. **Total Area Constraint**: 
   - Total area = sum of all `l²` for each square
   - We need: `area_below = area_above = total_area / 2`

2. **Square-Line Intersection**:
   - If `h < y`: entire square is above the line (contributes 0 below, `l²` above)
   - If `h >= y + l`: entire square is below the line (contributes `l²` below, 0 above)
   - If `y ≤ h < y + l`: square is split
     - Area below = `l × (h - y)`
     - Area above = `l × (y + l - h)`

3. **Overlapping Handling**:
   - Each square contributes independently
   - Overlapping regions count multiple times (NOT geometric union)

4. **Monotonicity**:
   - As `h` increases, area below increases monotonically
   - As `h` increases, area above decreases monotonically
   - This suggests binary search!

---

## Approach 1: Greedy/Brute Force (Event-Based)

### Strategy

Use **sweep line** with all critical y-coordinates where squares start or end.

### Algorithm

1. **Collect Critical Points**:
   - For each square `[x, y, l]`, add `y` (bottom) and `y + l` (top)
   - These are the only y-values where the rate of area change can differ

2. **Calculate Total Area**:
   ```
   total_area = sum(l² for each square)
   target_area = total_area / 2
   ```

3. **Sweep Through Y-coordinates**:
   - Sort all critical y-values
   - For each y-value, calculate area below
   - Find where area below first equals or exceeds target_area

4. **Calculate Area Below a Line at Height `h`**:
   ```python
   def area_below(h):
       total = 0
       for [x, y, l] in squares:
           if h <= y:
               # Square entirely above line
               total += 0
           elif h >= y + l:
               # Square entirely below line
               total += l * l
           else:
               # Square split by line
               height_below = h - y
               total += l * height_below
       return total
   ```

### Implementation Steps

```
1. Calculate total_area = Σ(lᵢ²)
2. target = total_area / 2
3. Collect all critical y-values: {yᵢ, yᵢ + lᵢ} for all squares
4. Sort critical y-values
5. For each critical y in sorted order:
   - Calculate area_below(y)
   - If area_below(y) >= target:
       - Use linear interpolation between this y and previous y
       - Return the exact y where area = target
6. Return result
```

### Complexity Analysis

- **Time Complexity**: `O(n²)`
  - Collecting critical points: `O(n)`
  - Sorting: `O(n log n)`
  - For each critical point (O(n)), calculate area (O(n)): `O(n²)`

- **Space Complexity**: `O(n)`
  - Store critical points

### Pros & Cons

✅ **Pros**:
- Simple to understand and implement
- Guaranteed to find exact answer at critical points

❌ **Cons**:
- `O(n²)` time complexity may be too slow for n = 50,000
- Inefficient recalculation of areas

---

## Approach 2: Optimized Binary Search

### Strategy

Use **binary search** on the y-coordinate, leveraging the monotonic property of area distribution.

### Key Insight

As the line height `h` increases:
- Area below line **increases monotonically**
- Area above line **decreases monotonically**

This monotonic property makes binary search applicable!

### Algorithm

1. **Determine Search Range**:
   ```python
   y_min = min(y for [x, y, l] in squares)
   y_max = max(y + l for [x, y, l] in squares)
   ```

2. **Binary Search**:
   ```python
   low = y_min
   high = y_max
   target = total_area / 2
   epsilon = 1e-6  # Precision requirement
   
   while high - low > epsilon:
       mid = (low + high) / 2
       area_below = calculate_area_below(mid)
       
       if area_below < target:
           low = mid  # Need to move line higher
       else:
           high = mid  # Need to move line lower
   
   return (low + high) / 2
   ```

3. **Optimized Area Calculation**:
   ```python
   def calculate_area_below(h):
       total = 0
       for [x, y, l] in squares:
           if h <= y:
               continue  # 0 contribution
           elif h >= y + l:
               total += l * l
           else:
               height_below = h - y
               total += l * height_below
       return total
   ```

### Implementation Steps

```
1. Calculate total_area = Σ(lᵢ²)
2. target = total_area / 2
3. Find bounds: 
   - low = min(yᵢ)
   - high = max(yᵢ + lᵢ)
4. Binary search:
   - While high - low > 1e-6:
     - mid = (low + high) / 2
     - area_below = calculate_area_below(mid)
     - If area_below < target: low = mid
     - Else: high = mid
5. Return (low + high) / 2
```

### Complexity Analysis

- **Time Complexity**: `O(n log(range/ε))`
  - Binary search iterations: `O(log(range/ε))` where range = max_y - min_y
  - Each iteration calculates area: `O(n)`
  - With ε = 10⁻⁶ and range ≤ 10⁹, we get ~20 iterations
  - Total: `O(20n) = O(n)`

- **Space Complexity**: `O(1)`
  - Only constant extra space

### Pros & Cons

✅ **Pros**:
- Much faster: `O(n log(range))` vs `O(n²)`
- Space efficient: `O(1)` extra space
- Scales well for large inputs (n = 50,000)

❌ **Cons**:
- Requires careful handling of floating-point precision
- Slightly more complex than brute force

---

## Approach 3: Mathematical (Most Optimized)

### Strategy

Use **event processing** with efficient sorting to track area changes.

### Key Insight

The area below the line changes **linearly** between critical y-values. We can:
1. Process squares in order of their y-coordinates
2. Track the "rate of change" of area as we sweep

### Algorithm

1. **Create Events**:
   ```python
   events = []
   for [x, y, l] in squares:
       events.append((y, 'start', l))      # Square starts
       events.append((y + l, 'end', l))    # Square ends
   ```

2. **Process Events in Order**:
   ```python
   Sort events by y-coordinate
   
   current_y = 0
   active_width = 0  # Sum of widths of active squares
   area_so_far = 0
   
   for (y, event_type, l) in sorted_events:
       # Add area contribution from current_y to y
       area_so_far += active_width * (y - current_y)
       
       if event_type == 'start':
           active_width += l
       else:  # 'end'
           active_width -= l
       
       current_y = y
       
       if area_so_far >= target:
           # Interpolate to find exact y
           ...
   ```

3. **Interpolation**:
   When we cross the target area:
   ```python
   deficit = target - area_before_event
   rate = active_width  # Area per unit height
   additional_height = deficit / rate
   result = previous_y + additional_height
   ```

### Complexity Analysis

- **Time Complexity**: `O(n log n)`
  - Create events: `O(n)`
  - Sort events: `O(n log n)`
  - Process events: `O(n)`

- **Space Complexity**: `O(n)`
  - Store events

### Pros & Cons

✅ **Pros**:
- Optimal time complexity: `O(n log n)`
- Single pass through sorted events
- Exact calculation without iteration

❌ **Cons**:
- Most complex to implement
- Requires careful event handling
- Edge cases need attention

---

## Recommendation

### For Competitive Programming / LeetCode:

**Use Approach 2 (Binary Search)** because:
1. ✅ Fast enough: `O(n)` per iteration, ~20 iterations
2. ✅ Simple to code and debug
3. ✅ Handles precision requirements naturally
4. ✅ Easy to understand and explain

### Implementation Priority:

1. **Start with Approach 2** - Binary Search (best balance)
2. **Optimize to Approach 3** if time permits
3. **Avoid Approach 1** - Too slow for n = 50,000

---

## Edge Cases to Consider

1. **Single Square**: 
   - Answer should be `y + l/2` (middle of the square)

2. **All Squares Same Height**:
   - All at same y-coordinate
   - Answer = `y + total_area / (2 * total_width)`

3. **Non-overlapping Squares**:
   - Each square contributes independently
   - Binary search works naturally

4. **Fully Overlapping Squares**:
   - Both squares contribute full areas
   - Count each square separately

5. **Precision**:
   - Use `double` or `float` with sufficient precision
   - Binary search epsilon = `1e-6` or smaller

---

## Complexity Comparison

| Approach | Time | Space | Difficulty | Recommended |
|----------|------|-------|------------|-------------|
| Brute Force (Event) | O(n²) | O(n) | Easy | ❌ Too slow |
| Binary Search | O(n log R) | O(1) | Medium | ✅ **Best** |
| Mathematical (Event) | O(n log n) | O(n) | Hard | ⚡ Optimal |

**R** = range of y-coordinates (≤ 10⁹), but with ε = 10⁻⁶, log R ≈ 20

---

## Implementation Notes

### Binary Search Template:

```python
def separateSquares(squares):
    # Calculate total area
    total_area = sum(l * l for x, y, l in squares)
    target = total_area / 2.0
    
    # Find bounds
    y_min = min(y for x, y, l in squares)
    y_max = max(y + l for x, y, l in squares)
    
    # Binary search
    low, high = y_min, y_max
    epsilon = 1e-6
    
    while high - low > epsilon:
        mid = (low + high) / 2.0
        area_below = calculate_area_below(squares, mid)
        
        if area_below < target:
            low = mid
        else:
            high = mid
    
    return (low + high) / 2.0

def calculate_area_below(squares, h):
    total = 0.0
    for x, y, l in squares:
        if h <= y:
            continue
        elif h >= y + l:
            total += l * l
        else:
            total += l * (h - y)
    return total
```

### Key Implementation Details:

1. Use `float` or `double` for y-coordinates
2. Set epsilon to `1e-6` for binary search precision
3. Handle edge cases: single square, all same height
4. Return final result with proper rounding
