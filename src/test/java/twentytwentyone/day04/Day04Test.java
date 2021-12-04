package twentytwentyone.day04;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day04Test {
  private Day04 day = new Day04();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day04/testOne.txt").getFile());
    assertEquals(4512, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day04/inputOne.txt").getFile());
    assertEquals(72770, day.solvePartOne(file));
  }
}
