pub struct Solution;

impl Solution {
    /// Greedy approach: Sort capacities in descending order and pick largest first
    /// Time: O(n + m log m)
    /// Space: O(1) extra (ignoring sort space)
    pub fn minimum_boxes(apple: Vec<i32>, capacity: Vec<i32>) -> i32 {
        let total_apples: i32 = apple.iter().sum();
        
        let mut capacity = capacity;
        capacity.sort_unstable_by(|a, b| b.cmp(a)); // Sort descending
        
        let mut used = 0;
        let mut current = 0;
        
        for &c in &capacity {
            current += c;
            used += 1;
            if current >= total_apples {
                return used;
            }
        }
        
        used
    }
    
    /// Optimized approach: Use frequency counting to avoid sorting
    /// Time: O(n + m + 50)
    /// Space: O(50)
    pub fn minimum_boxes_optimized(apple: Vec<i32>, capacity: Vec<i32>) -> i32 {
        let total_apples: i32 = apple.iter().sum();
        
        let mut freq = vec![0; 51];
        for &c in &capacity {
            freq[c as usize] += 1;
        }
        
        let mut used = 0;
        let mut current = 0;
        
        for c in (1..=50).rev() {
            while freq[c] > 0 && current < total_apples {
                current += c as i32;
                used += 1;
                freq[c] -= 1;
            }
            if current >= total_apples {
                break;
            }
        }
        
        used
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let apple = vec![1, 3, 2];
        let capacity = vec![4, 3, 1, 5, 2];
        assert_eq!(Solution::minimum_boxes(apple.clone(), capacity.clone()), 2);
        assert_eq!(Solution::minimum_boxes_optimized(apple, capacity), 2);
    }

    #[test]
    fn test_example_2() {
        let apple = vec![5, 5, 5];
        let capacity = vec![2, 4, 2, 7];
        assert_eq!(Solution::minimum_boxes(apple.clone(), capacity.clone()), 4);
        assert_eq!(Solution::minimum_boxes_optimized(apple, capacity), 4);
    }

    #[test]
    fn test_single_box() {
        let apple = vec![10];
        let capacity = vec![50];
        assert_eq!(Solution::minimum_boxes(apple.clone(), capacity.clone()), 1);
        assert_eq!(Solution::minimum_boxes_optimized(apple, capacity), 1);
    }

    #[test]
    fn test_multiple_small_boxes() {
        let apple = vec![10, 10, 10];
        let capacity = vec![5, 5, 5, 5, 5, 5];
        assert_eq!(Solution::minimum_boxes(apple.clone(), capacity.clone()), 6);
        assert_eq!(Solution::minimum_boxes_optimized(apple, capacity), 6);
    }

    #[test]
    fn test_exact_fit() {
        let apple = vec![10, 20];
        let capacity = vec![15, 15];
        assert_eq!(Solution::minimum_boxes(apple.clone(), capacity.clone()), 2);
        assert_eq!(Solution::minimum_boxes_optimized(apple, capacity), 2);
    }
}
