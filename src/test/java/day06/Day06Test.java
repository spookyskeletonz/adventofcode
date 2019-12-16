package day06;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class Day06Test {
  private final Day06 day = new Day06();

  @Test
  public void testPartOneBasic() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("Day06/basic.txt").getFile());
    assertEquals(42, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("Day06/input.txt").getFile());
    assertEquals(227612, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("Day06/basicPartTwo.txt").getFile());
    assertEquals(4, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("Day06/input.txt").getFile());
    assertEquals(454, day.solvePartTwo(file));
  }
}