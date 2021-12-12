package twentytwentyone.day11;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day11Test {
  Day11 day = new Day11();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day11/testOne.txt").getFile());
    assertEquals(1656, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day11/inputOne.txt").getFile());
    assertEquals(1675, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day11/testOne.txt").getFile());
    assertEquals(195, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day11/inputOne.txt").getFile());
    assertEquals(515, day.solvePartTwo(file));
  }
}
