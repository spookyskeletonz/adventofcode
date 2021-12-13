package twentytwentyone.day13;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day13Test {
  Day13 day = new Day13();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day13/testOne.txt").getFile());
    assertEquals(17, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day13/inputOne.txt").getFile());
    assertEquals(695, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day13/testOne.txt").getFile());
    day.solvePartTwo(file);
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day13/inputOne.txt").getFile());
    day.solvePartTwo(file);
  }
}
