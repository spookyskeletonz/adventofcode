package twentytwenty.day09;

import org.junit.Test;

import java.io.File;

public class Day09Test {
  private Day09 day = new Day09();

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day09/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day09/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
