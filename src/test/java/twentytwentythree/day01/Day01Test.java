package twentytwentythree.day01;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day01Test {
    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day01/testOne.txt").getFile());
        Assert.assertEquals(142, Day01.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day01/inputOne.txt").getFile());
        assertEquals(54304, Day01.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day01/testTwo.txt").getFile());
        assertEquals(281, Day01.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day01/inputOne.txt").getFile());
        assertEquals(54418, Day01.solvePartTwo(file));
    }
}
