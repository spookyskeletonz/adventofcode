package twentytwenty.day03;

import org.junit.Test;
import twentytwenty.day02.Day02;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day03Test {
    private Day03 day = new Day03();

    @Test
    public void testPartOneBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwenty/day03/testOne.txt").getFile());
        assertEquals(7, day.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwenty/day03/inputOne.txt").getFile());
        System.out.println(day.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwenty/day03/testOne.txt").getFile());
        assertEquals(336, day.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("twentytwenty/day03/inputOne.txt").getFile());
        System.out.println(day.solvePartTwo(file));
    }
}
