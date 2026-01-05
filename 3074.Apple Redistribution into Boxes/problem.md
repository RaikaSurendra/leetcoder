 You are given an array apple of size n and an array capacity of size m.
 
 There are n packs where the ith pack contains apple[i] apples. There are m boxes as well, and the ith box has a capacity of capacity[i] apples.
 
 Return the minimum number of boxes you need to select to redistribute these n packs of apples into boxes.
 
 Note that, apples from the same pack can be distributed into different boxes.
 
 
 
 Example 1:
 
 Input: apple = [1,3,2], capacity = [4,3,1,5,2]
 Output: 2
 Explanation: We will use boxes with capacities 4 and 5.
 It is possible to distribute the apples as the total capacity is greater than or equal to the total number of apples.
 Example 2:
 
 Input: apple = [5,5,5], capacity = [2,4,2,7]
 Output: 4
 Explanation: We will need to use all the boxes.
 
 
 Constraints:
 
 1 <= n == apple.length <= 50
 1 <= m == capacity.length <= 50
 1 <= apple[i], capacity[i] <= 50
 The input is generated such that it's possible to redistribute packs of apples into boxes.

 
 Solution Approach (Greedy)
 
 Intuition:
 Since apples from the same pack can be split across multiple boxes, only the total number of apples matters.
 Let totalApples = sum(apple). We want to pick the minimum number of boxes such that the sum of their capacities is at least totalApples.
 To minimize the number of boxes, always pick the largest capacities first.
 
 Algorithm:
 1. Compute totalApples = sum(apple)
 2. Sort capacity in descending order.
 3. Initialize used = 0, cur = 0
 4. Iterate capacities from largest to smallest:
    - cur += capacity[i]
    - used += 1
    - if cur >= totalApples, return used
 
 Complexity:
 Time: O(n + m log m)
 Space: O(1) extra (ignoring sort space)

 
 Optimized Algorithm (Frequency / Counting)
 
 Observation:
 capacity[i] is at most 50, so we can avoid sorting and instead count how many boxes exist for each capacity value.
 Then we greedily take capacities from 50 down to 1.
 
 Algorithm:
 1. Compute totalApples = sum(apple)
 2. Build freq[0..50], where freq[c] = number of boxes with capacity c
 3. Initialize used = 0, cur = 0
 4. For c from 50 down to 1:
    - while freq[c] > 0 and cur < totalApples:
      - cur += c
      - used += 1
      - freq[c] -= 1
 5. Return used
 
 Complexity:
 Time: O(n + m + 50 + answer)
 Space: O(50)
