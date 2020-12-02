package twentytwenty.day02;

import org.junit.Test;
import twentytwenty.day01.Day01;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day02Test {
  private Day02 day = new Day02();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day02/testOne.txt").getFile());
    assertEquals(2, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day02/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day02/testOne.txt").getFile());
    assertEquals(1, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day02/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
