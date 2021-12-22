package twentytwentyone.day22;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day22Test {
  Day22 day = new Day22();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day22/testOne.txt").getFile());
    assertEquals(590784, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day22/inputOne.txt").getFile());
    assertEquals(591365, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day22/testTwo.txt").getFile());
    assertEquals(2758514936282235L, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day22/inputOne.txt").getFile());
    assertEquals(1211172281877240L, day.solvePartTwo(file));
  }
}
