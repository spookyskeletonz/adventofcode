package twentytwentytwo.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Day01 {

  private final PriorityQueue<Integer> buildQueue(Scanner fileScanner) {
    var calQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
    var calForElf = 0;
    while (fileScanner.hasNextLine()) {
      var line = fileScanner.nextLine();
      if (line.isEmpty()) {
        calQueue.add(calForElf);
        calForElf = 0;
      } else {
        var cals = Integer.parseInt(line);
        calForElf += cals;
      }
    }
    calQueue.add(calForElf);
    return calQueue;
  }
  public int solvePartOne(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    var calQueue = buildQueue(fileScanner);
    return calQueue.poll();
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var fileScanner = new Scanner(file);
    var calQueue = buildQueue(fileScanner);
    return calQueue.poll() + calQueue.poll() + calQueue.poll();
  }
}
