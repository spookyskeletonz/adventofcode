package twentytwentyone.day23;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day23Test {
  Day23 day = new Day23();

  @Test
  public void testPartOneBasic() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day25/testOne.txt").getFile());
    assertEquals(58, day.solvePartOne(file));
  }

  @Test
  public void testPartOneInput() throws Exception {
    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day25/inputOne.txt").getFile());
    assertEquals(591365, day.solvePartOne(file));
  }

//  @Test
//  public void testPartTwoBasic() throws Exception {
//    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day23/testOne.txt").getFile());
//    assertEquals(2758514936282235L, day.solvePartTwo(file));
//  }
//
//  @Test
//  public void testPartTwoInput() throws Exception {
//    var file = new File(getClass().getClassLoader().getResource("twentytwentyone/day23/inputOne.txt").getFile());
//    assertEquals(1211172281877240L, day.solvePartTwo(file));
//  }
}
