package twentytwentyone.day21;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day21Test {
  Day21 day = new Day21();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day21/testOne.txt").getFile());
    assertEquals(739785, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day21/inputOne.txt").getFile());
    assertEquals(903630, day.solvePartOne(file));
  }

  @Test
  public void testPartTwoBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day21/testOne.txt").getFile());
    assertEquals(444356092776315L, day.solvePartTwo(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day21/inputOne.txt").getFile());
    assertEquals(303121579983974L, day.solvePartTwo(file));
  }
}
