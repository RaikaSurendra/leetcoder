public class SolutionOptimal {
    public boolean isPalindrome(int x) {
        // Early rejections: negatives and numbers ending with 0 (except 0 itself)
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversedHalf = 0;
        // Build the reverse of the last half of digits.
        // Invariant: 'reversedHalf' equals the reverse of digits removed from the right of 'x'.
        // Stop when we've consumed at least half the digits: x <= reversedHalf.
        while (x > reversedHalf) {
            // Append last digit of x to reversedHalf
            reversedHalf = reversedHalf * 10 + x % 10;
            // Drop last digit from x
            x /= 10;
        }
        // Even length: x == reversedHalf
        // Odd length: middle digit sits in reversedHalf; drop it via /10
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
