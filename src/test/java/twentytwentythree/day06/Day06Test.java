package twentytwentythree.day06;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day06Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day06/testOne.txt").getFile());
        Assert.assertEquals(288, Day06.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day06/inputOne.txt").getFile());
        Assert.assertEquals(500346, Day06.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day06/testOne.txt").getFile());
        Assert.assertEquals(71503, Day06.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day06/inputOne.txt").getFile());
        Assert.assertEquals(42515755, Day06.solvePartTwo(file));
    }
}
