public class Solution {
    
    /**
     * Brute Force Approach: Convert integer to string and check if it's a palindrome.
     * 
     * Time Complexity: O(n), where n is the number of digits in x. String conversion and 
     *                  character comparison both take linear time with respect to the number of digits.
     * Space Complexity: O(n), since we need to store the string representation of the integer.
     * 
     * @param x The integer to check
     * @return true if x is a palindrome, false otherwise
     */
    public boolean isPalindromeBruteForce(int x) {
        // Negative numbers are not palindromes (e.g., -121 reads 121- from right to left)
        if (x < 0) {
            return false;
        }
        
        String str = Integer.toString(x);
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }

    /**
     * Optimal Approach: Reverse only half of the integer mathematically.
     * 
     * Time Complexity: O(log10(x)). We divided the input by 10 for every iteration, so the time 
     *                  complexity is the number of digits in the integer.
     * Space Complexity: O(1). We only used a constant amount of extra space.
     * 
     * @param x The integer to check
     * @return true if x is a palindrome, false otherwise
     */
    public boolean isPalindromeOptimal(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        // We only reverse half of the number to prevent integer overflow and to save time
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palindrome (it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] testCases = {121, -121, 10, 0, 1221, 12321, 123456};

        System.out.println("--- Testing Brute Force Approach ---");
        for (int testCase : testCases) {
            System.out.println("Input: " + testCase + " -> Output: " + solution.isPalindromeBruteForce(testCase));
        }

        System.out.println("\n--- Testing Optimal Approach ---");
        for (int testCase : testCases) {
            System.out.println("Input: " + testCase + " -> Output: " + solution.isPalindromeOptimal(testCase));
        }
    }
}
