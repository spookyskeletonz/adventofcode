package twentytwenty.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day13 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var timestamp = Integer.parseInt(scanner.nextLine());
    var busIds = Arrays.stream(scanner.nextLine().split(","))
        .filter(i -> !i.equals("x"))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    var earliestBus = 0;
    var timeToWait = Integer.MAX_VALUE;
    for (var id : busIds) {
      var timeToNextBus = id - (timestamp % id);
      if (timeToNextBus < timeToWait) {
        timeToWait = timeToNextBus;
        earliestBus = id;
      }
    }
    return timeToWait * earliestBus;
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
  }
}
