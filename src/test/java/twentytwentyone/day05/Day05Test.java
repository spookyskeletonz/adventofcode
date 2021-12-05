package twentytwentyone.day05;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day05Test {
  private Day05 day = new Day05();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day05/testOne.txt").getFile());
    assertEquals(5, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day05/inputOne.txt").getFile());
    assertEquals(6007, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day05/testOne.txt").getFile());
    assertEquals(12, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day05/inputOne.txt").getFile());
    assertEquals(19349, day.solvePartTwo(file));
  }
}
