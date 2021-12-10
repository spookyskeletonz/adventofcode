package twentytwentyone.day10;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day10Test {
  private Day10 day = new Day10();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day10/testOne.txt").getFile());
    assertEquals(26397, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day10/inputOne.txt").getFile());
    assertEquals(321237, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day10/testOne.txt").getFile());
    assertEquals(288957, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day10/inputOne.txt").getFile());
    assertEquals(2360030859L, day.solvePartTwo(file));
  }
}
