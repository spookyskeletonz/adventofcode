package twentytwentythree.day16;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import twentytwentythree.day16.Day16;

import java.io.File;

public class Day16Test {

    private Day16 day;

    @Before
    public void setUp() {
        this.day = new Day16();
    }

    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day16/testOne.txt").getFile());
        Assert.assertEquals(46, day.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day16/inputOne.txt").getFile());
        Assert.assertEquals(7870, day.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day16/testOne.txt").getFile());
        Assert.assertEquals(51, day.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day16/inputOne.txt").getFile());
        Assert.assertEquals(8143, day.solvePartTwo(file));
    }
}
