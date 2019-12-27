package day08;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class Day08Test {
  @Test
  public void testPartOneInput() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("Day08/input.txt").getFile());
    assertEquals(1584, Day08.solvePartOne(25, 6, file));
  }

  @Test
  // doesn't actually test anything, just use a test to run in test env
  public void runPartTwoInput() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("Day08/input.txt").getFile());
    Day08 day = new Day08();
    int[][] res = day.solvePartTwo(25, 6, file);
    Day08.printImage(res);
  }
}