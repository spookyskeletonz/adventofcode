package twentytwentythree.day02;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class Day02Test {
    @Test
    public void testPartOneBasic() throws FileNotFoundException {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day02/testOne.txt").getFile());
        assertEquals(8, Day02.solvePartOne(file));
    }

    @Test
    public void testPartOneInput() throws FileNotFoundException {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day02/inputOne.txt").getFile());
        assertEquals(2447, Day02.solvePartOne(file));
    }

    @Test
    public void testPartTwoBasic() throws FileNotFoundException {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day02/testOne.txt").getFile());
        assertEquals(2286, Day02.solvePartTwo(file));
    }

    @Test
    public void testPartTwoInput() throws FileNotFoundException {
        File file = new File(getClass().getClassLoader().getResource("twentytwentythree/day02/inputOne.txt").getFile());
        assertEquals(56322, Day02.solvePartTwo(file));
    }
}
