package twentytwentythree.day10;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day10Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day10/testOne.txt").getFile());
        var day = new Day10();
        Assert.assertEquals(8, day.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day10/inputOne.txt").getFile());
        var day = new Day10();
        Assert.assertEquals(6820, day.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day10/testTwoBasic.txt").getFile());
        var day = new Day10();
        Assert.assertEquals(4, day.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day10/inputOne.txt").getFile());
        var day = new Day10();
        Assert.assertEquals(1082, day.solvePartOne(file));
    }
}
