package twentytwentyone.day16;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day16Test {
  Day16 day = new Day16();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day16/testOne.txt").getFile());
    assertEquals(31, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day16/inputOne.txt").getFile());
    assertEquals(877, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day16/testTwo.txt").getFile());
    assertEquals(1, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day16/inputOne.txt").getFile());
    assertEquals(194435634456L, day.solvePartTwo(file));
  }
}
