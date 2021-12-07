package twentytwentyone.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day07 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var inputString = scanner.nextLine();
    var inputNums = Arrays.stream(inputString.split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    var max = inputNums.stream().mapToInt(x -> x).max().orElseThrow(NoSuchElementException::new);
    var min = inputNums.stream().mapToInt(x -> x).min().orElseThrow(NoSuchElementException::new);
    return findMinFuelHorizPartOne(max, min, inputNums);
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var inputString = scanner.nextLine();
    var inputNums = Arrays.stream(inputString.split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    var max = inputNums.stream().mapToInt(x -> x).max().orElseThrow(NoSuchElementException::new);
    var min = inputNums.stream().mapToInt(x -> x).min().orElseThrow(NoSuchElementException::new);
    return findMinFuelHorizPartTwo(max, min, inputNums);
  }

  public int findMinFuelHorizPartOne(int maxHoriz, int minHoriz, List<Integer> horizontals) {
    var prevFuelCount = Integer.MAX_VALUE;
    for (var step = minHoriz; step <= maxHoriz; step++) {
      final int finalStep = step;
      var fuelCount = horizontals.stream().mapToInt(x -> x).map(x -> Math.abs(x - finalStep)).sum();
      // When we get fuelCount different that is greater than the starting value then we have passed most efficient
      if (fuelCount > prevFuelCount) {
        return prevFuelCount;
      }
      prevFuelCount = fuelCount;
    }
    return 0;
  }

  public int findMinFuelHorizPartTwo(int maxHoriz, int minHoriz, List<Integer> horizontals) {
    var prevFuelCount = Integer.MAX_VALUE;
    for (var step = minHoriz; step <= maxHoriz; step++) {
      final int finalStep = step;
      var fuelCount = horizontals.stream()
          .mapToInt(x -> x)
          .map(x -> {
            var value = Math.abs(x - finalStep);
            var sum = 0;
            for (var i = 1; i <= value; ++i) {
              sum += i;
            }
            return sum;})
          .sum();
      // When we get fuelCount different that is greater than the starting value then we have passed most efficient
      if (fuelCount > prevFuelCount) {
        return prevFuelCount;
      }
      prevFuelCount = fuelCount;
    }
    return 0;
  }
}
