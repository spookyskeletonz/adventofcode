package twentytwenty.day07;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day07Test {
  Day07 day = new Day07();

  @Test
  public void testSolvePartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day07/testOne.txt").getFile());
    assertEquals(4, day.solvePartOne(file));
  }

  @Test
  public void testSolvePartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day07/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testSolvePartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day07/testOne.txt").getFile());
    assertEquals(32, day.solvePartTwo(file));
  }

  @Test
  public void testSolvePartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day07/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
