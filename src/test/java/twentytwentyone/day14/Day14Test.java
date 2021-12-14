package twentytwentyone.day14;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day14Test {
  Day14 day = new Day14();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day14/testOne.txt").getFile());
    assertEquals(1588, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day14/inputOne.txt").getFile());
    assertEquals(2549, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day14/testOne.txt").getFile());
    assertEquals(2188189693529L, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day14/inputOne.txt").getFile());
    assertEquals(2516901104210L, day.solvePartTwo(file));
  }
}
