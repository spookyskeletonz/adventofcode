package twentytwentythree.day04;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day04Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day04/testOne.txt").getFile());
        Assert.assertEquals(13, Day04.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day04/inputOne.txt").getFile());
        Assert.assertEquals(19855, Day04.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day04/testOne.txt").getFile());
        Assert.assertEquals(30, Day04.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day04/inputOne.txt").getFile());
        Assert.assertEquals(10378710, Day04.solvePartTwo(file));
    }
}
