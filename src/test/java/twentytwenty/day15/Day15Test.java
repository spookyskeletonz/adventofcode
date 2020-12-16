package twentytwenty.day15;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Test {
  private Day15 day = new Day15();

  @Test
  public void testPartOneBasic() throws Exception {
    assertEquals(436, day.solvePartOne("0,3,6"));
    assertEquals(1, day.solvePartOne("1,3,2"));
    assertEquals(10, day.solvePartOne("2,1,3"));
    assertEquals(27, day.solvePartOne("1,2,3"));
    assertEquals(78, day.solvePartOne("2,3,1"));
    assertEquals(438, day.solvePartOne("3,2,1"));
    assertEquals(1836, day.solvePartOne("3,1,2"));
  }

  @Test
  public void testPartOneInput() throws Exception {
    System.out.println(day.solvePartOne("12,1,16,3,11,0"));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    System.out.println(day.solvePartTwo("12,1,16,3,11,0"));
  }
}
