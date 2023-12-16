package twentytwentythree.day15;

import org.junit.Assert;
import org.junit.Test;
import twentytwentythree.day15.Day15;

import java.io.File;

public class Day15Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day15/testOne.txt").getFile());
        Assert.assertEquals(1320, Day15.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day15/inputOne.txt").getFile());
        Assert.assertEquals(509167, Day15.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day15/testOne.txt").getFile());
        Assert.assertEquals(145, Day15.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day15/inputOne.txt").getFile());
        Assert.assertEquals(259333, Day15.solvePartTwo(file));
    }
}
