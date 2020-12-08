package twentytwenty.day08;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day08Test {
  private Day08 day = new Day08();

  @Test
  public void testPartOneBasic() throws Exception{
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day08/testOne.txt").getFile());
    assertEquals(5, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day08/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day08/testOne.txt").getFile());
    assertEquals(8, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day08/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
