package day04;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day04Test {
  private final Day04 day = new Day04();

  @Test
  public void possibleSolutionPartOneTest() {
    assertTrue(day.isPossiblePassword(111111));
    assertFalse(day.isPossiblePassword(223450));
    assertFalse(day.isPossiblePassword(123789));
  }

  @Test
  public void partOneTest() {
    assertEquals(1855, day.solvePartOne(138307, 654504));
  }

  @Test
  public void possibleSolutionPartTwoTest() {
    assertTrue(day.isPossiblePasswordPartTwo(112233));
    assertTrue(day.isPossiblePasswordPartTwo(112333));
    assertFalse(day.isPossiblePasswordPartTwo(123444));
    assertTrue(day.isPossiblePasswordPartTwo(111122));
    assertTrue(day.isPossiblePasswordPartTwo(112337));
    assertFalse(day.isPossiblePasswordPartTwo(123456));
    assertTrue(day.isPossiblePasswordPartTwo(112344));
    assertTrue(day.isPossiblePasswordPartTwo(124566));
  }

  @Test
  public void partTwoTest() {
    assertEquals(1253, day.solvePartTwo(138307, 654504));
  }

}