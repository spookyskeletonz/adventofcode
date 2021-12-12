package twentytwentyone.day12;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day12Test {
  Day12 day = new Day12();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day12/testOne.txt").getFile());
    assertEquals(10, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day12/inputOne.txt").getFile());
    assertEquals(3887, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day12/testOne.txt").getFile());
    assertEquals(36, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day12/inputOne.txt").getFile());
    assertEquals(104834, day.solvePartTwo(file));
  }
}
