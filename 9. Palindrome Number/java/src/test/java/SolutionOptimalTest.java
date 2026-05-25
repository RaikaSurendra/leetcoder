import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class SolutionOptimalTest {
    private final SolutionOptimal solution = new SolutionOptimal();

    @ParameterizedTest(name = "isPalindrome({0}) => {1}")
    @CsvSource({
            "121, true",
            "-121, false",
            "10, false",
            "0, true",
            "1221, true",
            "12321, true",
            "123456, false",
            "7, true",
            "1001, true",
            "100, false"
    })
    void testIsPalindrome(int input, boolean expected) {
        assertEquals(expected, solution.isPalindrome(input));
    }
}
