package twentytwentytwo.day02;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day02Test {
  private final Day02 day = new Day02();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day02/testOne.txt").getFile());
    assertEquals(15, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day02/inputOne.txt").getFile());
    assertEquals(15632, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day02/testOne.txt").getFile());
    assertEquals(12, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentytwo/day02/inputOne.txt").getFile());
    assertEquals(14416, day.solvePartTwo(file));
  }
}
