package twentytwentytwo.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day04 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var overlapping = 0;
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var lowerOne = Integer.parseInt(line.split(",")[0].split("-")[0]);
      var upperOne = Integer.parseInt(line.split(",")[0].split("-")[1]);
      var lowerTwo = Integer.parseInt(line.split(",")[1].split("-")[0]);
      var upperTwo = Integer.parseInt(line.split(",")[1].split("-")[1]);
      if (lowerOne <= lowerTwo && upperOne >= upperTwo) {
        overlapping++;
      } else if (lowerTwo <= lowerOne && upperTwo >= upperOne) {
        overlapping++;
      }
    }
    return overlapping;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var overlapping = 0;
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var A = Integer.parseInt(line.split(",")[0].split("-")[0]);
      var B = Integer.parseInt(line.split(",")[0].split("-")[1]);
      var C = Integer.parseInt(line.split(",")[1].split("-")[0]);
      var D = Integer.parseInt(line.split(",")[1].split("-")[1]);
      // overlap possibilities between A--B and C--D:
      // ...A--[C]--B...
      // ...C--[A]--D...
      if (A <= C && C <= B) {
        overlapping++;
      } else if (C <= A && A <= D) {
        overlapping++;
      }
    }
    return overlapping;
  }
}
