package twentytwentyone.day08;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day08Test {
  Day08 day = new Day08();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day08/testOne.txt").getFile());
    assertEquals(26, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day08/inputOne.txt").getFile());
    assertEquals(514, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoOneLine() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day08/testTwo.txt").getFile());
    assertEquals(5353, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day08/testOne.txt").getFile());
    assertEquals(61229, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day08/inputOne.txt").getFile());
    assertEquals(1012272, day.solvePartTwo(file));
  }
}
