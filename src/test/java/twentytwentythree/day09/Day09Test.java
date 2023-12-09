package twentytwentythree.day09;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day09Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day09/testOne.txt").getFile());
        Assert.assertEquals(114, Day09.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day09/inputOne.txt").getFile());
        Assert.assertEquals(1861775706, Day09.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day09/testOne.txt").getFile());
        Assert.assertEquals(2, Day09.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day09/inputOne.txt").getFile());
        Assert.assertEquals(1082, Day09.solvePartTwo(file));
    }
}
