package twentytwentyone.day15;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day15Test {
  Day15 day = new Day15();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day15/testOne.txt").getFile());
    assertEquals(40, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day15/inputOne.txt").getFile());
    assertEquals(429, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day15/testOne.txt").getFile());
    assertEquals(315, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day15/inputOne.txt").getFile());
    assertEquals(2844, day.solvePartTwo(file));
  }
}
