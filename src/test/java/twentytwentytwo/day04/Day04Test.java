package twentytwentytwo.day04;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day04Test {
  private final Day04 day = new Day04();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day04/testOne.txt").getFile());
    assertEquals(2, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day04/inputOne.txt").getFile());
    assertEquals(651, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day04/testOne.txt").getFile());
    assertEquals(4, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day04/inputOne.txt").getFile());
    assertEquals(956, day.solvePartTwo(file));
  }
}
