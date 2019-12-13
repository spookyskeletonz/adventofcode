package Day02;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class Day02Test {
  private final Day02 day = new Day02();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("Day02/basic.txt").getFile());
    assertEquals(3500, day.solvePartOne(file));
  }

  @Test
  public void testPartOneLargeInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("Day02/input.txt").getFile());
    assertEquals(4023471, day.solvePartOne(file));
  }
}