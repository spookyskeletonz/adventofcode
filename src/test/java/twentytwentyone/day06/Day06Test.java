package twentytwentyone.day06;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day06Test {
  public Day06 day = new Day06();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day06/testOne.txt").getFile());
    assertEquals(5934, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day06/inputOne.txt").getFile());
    assertEquals(371379, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day06/testOne.txt").getFile());
    assertEquals(26984457539L, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day06/inputOne.txt").getFile());
    assertEquals(1674303997472L, day.solvePartTwo(file));
  }
}
