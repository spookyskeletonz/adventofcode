package twentytwentyone.day03;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day03Test {
  private Day03 day = new Day03();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day03/testOne.txt").getFile());
    assertEquals(198, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day03/inputOne.txt").getFile());
    assertEquals(693486, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day03/testOne.txt").getFile());
    assertEquals(230, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day03/inputOne.txt").getFile());
    assertEquals(3379326, day.solvePartTwo(file));
  }
}
