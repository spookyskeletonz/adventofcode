package twentytwentythree.day08;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day08Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day08/testOne.txt").getFile());
        var day = new Day08();
        Assert.assertEquals(6, day.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day08/inputOne.txt").getFile());
        var day = new Day08();
        Assert.assertEquals(13771, day.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day08/testTwo.txt").getFile());
        var day = new Day08();
        Assert.assertEquals(6, day.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day08/inputOne.txt").getFile());
        var day = new Day08();
        Assert.assertEquals(13129439557681L, day.solvePartTwo(file));
    }
}
