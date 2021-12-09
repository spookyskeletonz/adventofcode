package twentytwentyone.day09;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day09Test {
  private Day09 day = new Day09();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day09/testOne.txt").getFile());
    assertEquals(15, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day09/inputOne.txt").getFile());
    assertEquals(570, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day09/testOne.txt").getFile());
    assertEquals(1134, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day09/inputOne.txt").getFile());
    assertEquals(899392, day.solvePartTwo(file));
  }
}
