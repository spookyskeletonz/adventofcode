package twentytwentythree.day11;

import org.junit.Assert;
import org.junit.Test;
import twentytwentythree.day11.Day11;

import java.io.File;

public class Day11Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day11/testOne.txt").getFile());
        var day = new Day11();
        Assert.assertEquals(374, day.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day11/inputOne.txt").getFile());
        var day = new Day11();
        Assert.assertEquals(10033566, day.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day11/testOne.txt").getFile());
        var day = new Day11();
        Assert.assertEquals(8410, day.solvePartTwoBasic(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day11/inputOne.txt").getFile());
        var day = new Day11();
        Assert.assertEquals(560822911938L, day.solvePartTwo(file));
    }
}
