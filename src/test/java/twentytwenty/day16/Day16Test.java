package twentytwenty.day16;

import org.junit.Test;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day16Test {
  Day16 day = new Day16();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day16/testOne.txt").getFile());
    assertEquals(71, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day16/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day16/testTwo.txt").getFile());
    assertEquals(List.of(12, 11), day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day16/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
