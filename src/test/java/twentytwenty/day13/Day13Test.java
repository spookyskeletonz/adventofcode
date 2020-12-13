package twentytwenty.day13;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day13Test {
  Day13 day = new Day13();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day13/testOne.txt").getFile());
    assertEquals(295, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day13/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day13/testOne.txt").getFile());
    assertEquals(1068781, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day13/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
