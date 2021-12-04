package twentytwentyone.day01;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day01Test {
  private Day01 day = new Day01();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day01/testOne.txt").getFile());
    assertEquals(7, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day01/inputOne.txt").getFile());
    assertEquals(1451, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day01/testOne.txt").getFile());
    assertEquals(5, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day01/inputOne.txt").getFile());
    assertEquals(1395, day.solvePartTwo(file));
  }
}
