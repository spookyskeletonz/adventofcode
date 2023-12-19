package twentytwentythree.day18;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day18Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day18/testOne.txt").getFile());
        Assert.assertEquals(62, Day18.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day18/inputOne.txt").getFile());
        Assert.assertEquals(72821, Day18.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day18/testOne.txt").getFile());
        Assert.assertEquals(952408144115L, Day18.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day18/inputOne.txt").getFile());
        Assert.assertEquals(127844509405501L, Day18.solvePartTwo(file));
    }
}
