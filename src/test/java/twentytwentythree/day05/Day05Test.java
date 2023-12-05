package twentytwentythree.day05;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day05Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day05/testOne.txt").getFile());
        Assert.assertEquals(35, Day05.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day05/inputOne.txt").getFile());
        Assert.assertEquals(579439039, Day05.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day05/testOne.txt").getFile());
        Assert.assertEquals(46, Day05.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day05/inputOne.txt").getFile());
        Assert.assertEquals(7873084, Day05.solvePartTwo(file));
    }
}
