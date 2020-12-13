package twentytwenty.day12;

import org.junit.Test;
import twentytwenty.day12.Day12;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day12Test {
  Day12 day = new Day12();

  @Test
  public void testPartOneBasic() throws Exception{
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day12/testOne.txt").getFile());
    assertEquals(25, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day12/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day12/testOne.txt").getFile());
    assertEquals(286, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day12/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
