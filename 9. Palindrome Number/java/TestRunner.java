public class TestRunner {
    private static void assertEquals(String label, boolean got, boolean expected) {
        if (got != expected) {
            System.out.println("[FAIL] " + label + ": expected=" + expected + ", got=" + got);
        } else {
            System.out.println("[PASS] " + label + ": " + got);
        }
    }

    public static void main(String[] args) {
        SolutionBruteForce brute = new SolutionBruteForce();
        SolutionOptimal optimal = new SolutionOptimal();

        int[] inputs = {121, -121, 10, 0, 1221, 12321, 123456};
        boolean[] expected = {true, false, false, true, true, true, false};

        System.out.println("=== Testing Brute Force ===");
        for (int i = 0; i < inputs.length; i++) {
            boolean got = brute.isPalindrome(inputs[i]);
            assertEquals("Brute x=" + inputs[i], got, expected[i]);
        }

        System.out.println("\n=== Testing Optimal ===");
        for (int i = 0; i < inputs.length; i++) {
            boolean got = optimal.isPalindrome(inputs[i]);
            assertEquals("Optimal x=" + inputs[i], got, expected[i]);
        }

        System.out.println("\n=== Cross-check Brute vs Optimal ===");
        for (int x : inputs) {
            boolean b = brute.isPalindrome(x);
            boolean o = optimal.isPalindrome(x);
            if (b != o) {
                System.out.println("[MISMATCH] x=" + x + ": brute=" + b + ", optimal=" + o);
            } else {
                System.out.println("[MATCH] x=" + x + ": " + b);
            }
        }
    }
}
