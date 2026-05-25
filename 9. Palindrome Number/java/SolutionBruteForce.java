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

    public static void main(String[] args) {
        SolutionBruteForce solution = new SolutionBruteForce();
        int[] tests = {121, -121, 10, 0, 1221, 12321, 123456};
        for (int t : tests) {
            System.out.println("Input: " + t + " -> " + solution.isPalindrome(t));
        }
    }
}
