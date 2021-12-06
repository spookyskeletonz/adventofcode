package twentytwentyone.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day06 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var fileScanner = new Scanner(file);
    var inputString = fileScanner.nextLine();
    var input = Arrays.stream(inputString.split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    return (int) numFishAfterDays(80, input);
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var fileScanner = new Scanner(file);
    var inputString = fileScanner.nextLine();
    var input = Arrays.stream(inputString.split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    return numFishAfterDays(256, input);
  }

  public long numFishAfterDays(int days, List<Integer> seed) {
    // Keeps count of number of fish with x many days left ie: fishCounts[8] = number of fish with 8 days left
    long[] fishTimerCounts = new long[] {0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L};
    for (var seedNum : seed) {
      fishTimerCounts[seedNum]++;
    }
    while (days != 0) {
      var fishCount0 = fishTimerCounts[0];
      // Copy values from x+1 to x where x is each index of the array (fish timers decrementing)
      System.arraycopy(fishTimerCounts, 1, fishTimerCounts, 0, fishTimerCounts.length - 1);
      // Now there are new fish with an 8 timer, from the previous 0 timer fish
      fishTimerCounts[fishTimerCounts.length-1] = fishCount0;
      // Previous 0 timer fish have been rolled back to 6
      fishTimerCounts[6] += fishCount0;
      days--;
    }
    return Arrays.stream(fishTimerCounts).sum();
  }
}
