package twentytwenty.day10;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day10Test {
  private Day10 day = new Day10();

  @Test
  public void testPartOneBasic() throws Exception{
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day10/testOne.txt").getFile());
    assertEquals(220, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day10/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day10/testOne.txt").getFile());
    assertEquals(19208, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day10/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
