package day09;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class Day09Test {
  Day09 day = new Day09();

  @Test
  public void testPartOneInput() throws FileNotFoundException {
    File file = new File(getClass().getClassLoader().getResource("Day09/input.txt").getFile());
    assertTrue(3063082071L == day.solvePartOne(file, new LinkedList<>(Arrays.asList(1L))));
  }
}