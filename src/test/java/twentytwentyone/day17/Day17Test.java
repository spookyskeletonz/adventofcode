package twentytwentyone.day17;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day17Test {
  Day17 day = new Day17();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day17/testOne.txt").getFile());
    assertEquals(45, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day17/inputOne.txt").getFile());
    assertEquals(5565, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day17/testOne.txt").getFile());
    assertEquals(112, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day17/inputOne.txt").getFile());
    assertEquals(2118, day.solvePartTwo(file));
  }
}
