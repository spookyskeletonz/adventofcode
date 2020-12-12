package twentytwenty.day11;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day11Test {
  Day11 day = new Day11();

  @Test
  public void testPartOneBasic() throws Exception{
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day11/testOne.txt").getFile());
    assertEquals(37, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day11/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day11/testOne.txt").getFile());
    assertEquals(26, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day11/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
