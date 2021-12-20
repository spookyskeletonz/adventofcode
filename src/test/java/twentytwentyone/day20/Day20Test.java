package twentytwentyone.day20;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day20Test {
  Day20 day = new Day20();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day20/testOne.txt").getFile());
    assertEquals(35, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day20/inputOne.txt").getFile());
    assertEquals(5479, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day20/testOne.txt").getFile());
    assertEquals(3351, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day20/inputOne.txt").getFile());
    assertEquals(19012, day.solvePartTwo(file));
  }
}
