package twentytwentyone.day02;

import org.junit.Test;
import twentytwentyone.day01.Day01;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Day02Test {
  private Day02 day = new Day02();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day02/testOne.txt").getFile());
    assertEquals(150, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day02/inputOne.txt").getFile());
    assertEquals(2272262, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day02/testOne.txt").getFile());
    assertEquals(900, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwentyone/day02/inputOne.txt").getFile());
    assertEquals(2134882034, day.solvePartTwo(file));
  }

}
