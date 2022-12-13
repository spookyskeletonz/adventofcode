package twentytwentytwo.day03;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day03Test {
  private final Day03 day = new Day03();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day03/testOne.txt").getFile());
    assertEquals(157, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day03/inputOne.txt").getFile());
    assertEquals(8394, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day03/testOne.txt").getFile());
    assertEquals(70, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day03/inputOne.txt").getFile());
    assertEquals(2413, day.solvePartTwo(file));
  }
}
