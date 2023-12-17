package twentytwentythree.day17;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day17Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day17/testOne.txt").getFile());
        Assert.assertEquals(102, Day17.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day17/inputOne.txt").getFile());
        Assert.assertEquals(1110, Day17.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day17/testOne.txt").getFile());
        Assert.assertEquals(94, Day17.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day17/inputOne.txt").getFile());
        Assert.assertEquals(1294, Day17.solvePartTwo(file));
    }
}
