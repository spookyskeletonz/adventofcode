package twentytwenty.day14;

import org.junit.Test;
import twentytwenty.day14.Day14;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day14Test {
  Day14 day = new Day14();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day14/testOne.txt").getFile());
    assertEquals(165, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day14/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day14/testTwo.txt").getFile());
    assertEquals(208, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day14/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
