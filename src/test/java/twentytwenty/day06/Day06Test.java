package twentytwenty.day06;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day06Test {
  Day06 day = new Day06();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day06/testOne.txt").getFile());
    assertEquals(11, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day06/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day06/testOne.txt").getFile());
    assertEquals(6, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day06/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
