package twentytwentytwo.day01;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day01Test {
  private final Day01 day = new Day01();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day01/testOne.txt").getFile());
    assertEquals(24000, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day01/inputOne.txt").getFile());
    assertEquals(67027, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day01/testOne.txt").getFile());
    assertEquals(45000, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day01/inputOne.txt").getFile());
    assertEquals(197291, day.solvePartTwo(file));
  }
}
