package twentytwentyone.day07;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day07Test {
  Day07 day = new Day07();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day07/testOne.txt").getFile());
    assertEquals(37, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day07/inputOne.txt").getFile());
    assertEquals(340052, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day07/testOne.txt").getFile());
    assertEquals(168, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day07/inputOne.txt").getFile());
    assertEquals(92948968, day.solvePartTwo(file));
  }
}
