package twentytwentythree.day13;

import org.junit.Assert;
import org.junit.Test;
import twentytwentythree.day13.Day13;

import java.io.File;

public class Day13Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day13/testOne.txt").getFile());
        Assert.assertEquals(405, Day13.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day13/inputOne.txt").getFile());
        Assert.assertEquals(35691, Day13.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day13/testOne.txt").getFile());
        Assert.assertEquals(400, Day13.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day13/inputOne.txt").getFile());
        Assert.assertEquals(39037, Day13.solvePartTwo(file));
    }
}
