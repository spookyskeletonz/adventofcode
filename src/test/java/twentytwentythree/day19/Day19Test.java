package twentytwentythree.day19;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Day19Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day19/testOne.txt").getFile());
        Assert.assertEquals(19114, Day19.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day19/inputOne.txt").getFile());
        Assert.assertEquals(472630, Day19.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day19/testOne.txt").getFile());
        //Assert.assertEquals(952408144115L, Day19.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day19/inputOne.txt").getFile());
        //Assert.assertEquals(127844509405501L, Day19.solvePartTwo(file));
    }
}
