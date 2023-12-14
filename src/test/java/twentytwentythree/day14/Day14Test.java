package twentytwentythree.day14;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import twentytwentythree.day14.Day14;

import java.io.File;

public class Day14Test {
    private Day14 day;

    @Before
    public void setUp() {
        this.day = new Day14();
    }

    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day14/testOne.txt").getFile());
        Assert.assertEquals(136, day.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day14/inputOne.txt").getFile());
        Assert.assertEquals(106517, day.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day14/testOne.txt").getFile());
        Assert.assertEquals(64, day.solvePartTwo(file, 1000000000));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day14/inputOne.txt").getFile());
        Assert.assertEquals(79723, day.solvePartTwo(file, 1000000000));
    }
}
