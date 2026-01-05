pub mod solution_greedy;
pub mod solution_optimized;

pub use solution_greedy::SolutionGreedy;
pub use solution_optimized::SolutionOptimized;

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let apple = vec![1, 3, 2];
        let capacity = vec![4, 3, 1, 5, 2];
        assert_eq!(SolutionGreedy::minimum_boxes(apple.clone(), capacity.clone()), 2);
        assert_eq!(SolutionOptimized::minimum_boxes(apple, capacity), 2);
    }

    #[test]
    fn test_example_2() {
        let apple = vec![5, 5, 5];
        let capacity = vec![2, 4, 2, 7];
        assert_eq!(SolutionGreedy::minimum_boxes(apple.clone(), capacity.clone()), 4);
        assert_eq!(SolutionOptimized::minimum_boxes(apple, capacity), 4);
    }

    #[test]
    fn test_single_box() {
        let apple = vec![10];
        let capacity = vec![50];
        assert_eq!(SolutionGreedy::minimum_boxes(apple.clone(), capacity.clone()), 1);
        assert_eq!(SolutionOptimized::minimum_boxes(apple, capacity), 1);
    }

    #[test]
    fn test_multiple_small_boxes() {
        let apple = vec![10, 10, 10];
        let capacity = vec![5, 5, 5, 5, 5, 5];
        assert_eq!(SolutionGreedy::minimum_boxes(apple.clone(), capacity.clone()), 6);
        assert_eq!(SolutionOptimized::minimum_boxes(apple, capacity), 6);
    }

    #[test]
    fn test_exact_fit() {
        let apple = vec![10, 20];
        let capacity = vec![15, 15];
        assert_eq!(SolutionGreedy::minimum_boxes(apple.clone(), capacity.clone()), 2);
        assert_eq!(SolutionOptimized::minimum_boxes(apple, capacity), 2);
    }
}
