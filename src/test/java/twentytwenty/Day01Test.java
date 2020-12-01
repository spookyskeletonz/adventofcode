package twentytwenty;

import org.junit.Test;
import twentytwenty.day01.Day01;

import java.io.File;

import static org.junit.Assert.*;

public class Day01Test {
  private Day01 day = new Day01();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day01/testOne.txt").getFile());
    assertEquals(514579, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day01/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day01/testOne.txt").getFile());
    assertEquals(241861950, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day01/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
