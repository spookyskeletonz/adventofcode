package twentytwentythree.day07;

import org.junit.Assert;
import org.junit.Test;
import twentytwentythree.day06.Day06;

import java.io.File;

public class Day07Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day07/testOne.txt").getFile());
        Assert.assertEquals(6440, Day07.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day07/inputOne.txt").getFile());
        Assert.assertEquals(250602641, Day07.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day07/testOne.txt").getFile());
        Assert.assertEquals(5905, Day07.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day07/inputOne.txt").getFile());
        Assert.assertEquals(251037509, Day07.solvePartTwo(file));
    }
}
