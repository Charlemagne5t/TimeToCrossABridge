import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        int n = 1;
        int k = 1;
        int[][] time = {
                {1, 1, 2, 1},
                {1, 1, 3, 1},
                {1, 1, 4, 1},
        };
        int expected = 6;
        int actual = new Solution().findCrossingTime(n, k, time);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        int n = 3;
        int k = 2;
        int[][] time = {
                {1, 9, 1, 8},
                {10, 10, 10, 10},
        };
        int expected = 50;
        int actual = new Solution().findCrossingTime(n, k, time);
        Assert.assertEquals(expected, actual);
    }
}
