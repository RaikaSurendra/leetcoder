public class SolutionBruteForce {
    public boolean isPalindrome(int x) {
        // Two-pointer over string representation; negatives are not palindromes due to '-'
        if (x < 0) return false;
        String s = Integer.toString(x);
        int i = 0, j = s.length() - 1;
        // Compare characters from both ends, moving inward
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
