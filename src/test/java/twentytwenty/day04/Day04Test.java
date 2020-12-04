package twentytwenty.day04;

import org.junit.Test;
import twentytwenty.day03.Day03;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day04Test {
  private Day04 day = new Day04();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day04/testOne.txt").getFile());
    assertEquals(2, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day04/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day04/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
