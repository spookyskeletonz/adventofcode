package twentytwentythree.day03;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day03Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day03/testOne.txt").getFile());
        var day = new Day03();
        Assert.assertEquals(4361, day.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day03/inputOne.txt").getFile());
        var day = new Day03();
        Assert.assertEquals(551094, day.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day03/testOne.txt").getFile());
        var day = new Day03();
        Assert.assertEquals(467835, day.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day03/inputOne.txt").getFile());
        var day = new Day03();
        Assert.assertEquals(80179647, day.solvePartTwo(file));
    }
}
