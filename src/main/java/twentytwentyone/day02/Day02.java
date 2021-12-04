package twentytwentyone.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 {
  public enum Commands {
    FORWARD ,
    DOWN,
    UP;
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    var horizontal = 0;
    var depth = 0;
    while (fileScanner.hasNextLine()) {
      var instruction = fileScanner.nextLine().split(" ");
      var command = Commands.valueOf(instruction[0].toUpperCase());
      var units = Integer.parseInt(instruction[1]);
      switch (command) {
        case FORWARD:
          horizontal += units;
          break;
        case DOWN:
          depth += units;
          break;
        case UP:
          depth -= units;
          break;
      }
    }
    return horizontal * depth;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    var horizontal = 0;
    var depth = 0;
    var aim = 0;
    while (fileScanner.hasNextLine()) {
      var instruction = fileScanner.nextLine().split(" ");
      var command = Commands.valueOf(instruction[0].toUpperCase());
      var units = Integer.parseInt(instruction[1]);
      switch (command) {
        case FORWARD:
          horizontal += units;
          depth += aim * units;
          break;
        case DOWN:
          aim += units;
          break;
        case UP:
          aim -= units;
          break;
      }
    }
    return horizontal * depth;
  }
}