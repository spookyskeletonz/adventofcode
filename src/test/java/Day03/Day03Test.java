package Day03;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class Day03Test {
  private final Day03 day = new Day03();

  @Test
  public void testPartOneBasic() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("Day03/basic.txt").getFile());
    assertEquals(135, day.solvePartOne(file));
  }

  @Test
  public void testPartOneLargeInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("Day03/input.txt").getFile());
    assertEquals(731, day.solvePartOne(file));
  }
}