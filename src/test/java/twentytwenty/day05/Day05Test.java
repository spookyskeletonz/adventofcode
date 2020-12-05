package twentytwenty.day05;

import org.junit.Test;
import twentytwenty.day05.Day05;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Day05Test {
  private Day05 day = new Day05();

  @Test
  public void testSeat() throws Exception {
    var testSeat1 = new Day05.Seat("BFFFBBFRRR");
    var testSeat2 = new Day05.Seat("FFFBBBFRRR");
    var testSeat3 = new Day05.Seat("BBFFBBFRLL");
    assertEquals(567, testSeat1.seatId);
    assertEquals(70, testSeat1.row);
    assertEquals(7, testSeat1.column);
    assertEquals(119, testSeat2.seatId);
    assertEquals(14, testSeat2.row);
    assertEquals(7, testSeat2.column);
    assertEquals(820, testSeat3.seatId);
    assertEquals(102, testSeat3.row);
    assertEquals(4, testSeat3.column);
  }

  @Test
  public void testPartOneInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day05/inputOne.txt").getFile());
    System.out.println(day.solvePartOne(file));
  }

  @Test
  public void testPartTwoInput() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("twentytwenty/day05/inputOne.txt").getFile());
    System.out.println(day.solvePartTwo(file));
  }
}
