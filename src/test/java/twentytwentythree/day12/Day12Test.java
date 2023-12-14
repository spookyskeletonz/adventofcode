package twentytwentythree.day12;

import org.junit.Assert;
import org.junit.Test;
import twentytwentythree.day12.Day12;

import java.io.File;

public class Day12Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day12/testOne.txt").getFile());
        var day = new Day12();
        Assert.assertEquals(21, day.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day12/inputOne.txt").getFile());
        var day = new Day12();
        Assert.assertEquals(7622, day.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day12/testOne.txt").getFile());
        var day = new Day12();
        Assert.assertEquals(525152, day.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day12/inputOne.txt").getFile());
        var day = new Day12();
        Assert.assertEquals(4964259839627L, day.solvePartTwo(file));
    }
}
