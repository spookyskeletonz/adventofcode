package twentytwenty.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day09 {

  public long solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    // A sliding window of size 25
    var numList = new ArrayList<Long>();
    Set<Long> diffSet = new HashSet<>();
    while (scanner.hasNextLong()) {
      var num = scanner.nextLong();
      if (numList.size() < 25) {
        numList.add(num);
        continue;
      }
      var foundPair = false;
      // check against each of previous 25 using a set of difference between num and a possible number in a set
      for (var possiblePair : numList) {
        if (diffSet.contains(possiblePair)) {
          foundPair = true;
          break;
        }
        diffSet.add(num - possiblePair);
      }
      if (!foundPair) {
        return num;
      }
      numList.remove(0);
      numList.add(num);
      diffSet.clear();
    }
    return 0;
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    // linear scan, remove items from list if sum > target, add if <
    var target = 1398413738;
    var scanner = new Scanner(file);
    var sumList = new ArrayList<Long>();
    var currentSum = 0L;
    while (scanner.hasNextLong()) {
      var num = scanner.nextLong();
      sumList.add(num);
      currentSum += num;
      if (currentSum > target) {
        // adding this number takes us over target, take away first numbers until under or at target
        while (currentSum > target) {
          currentSum -= sumList.get(0);
          sumList.remove(0);
          if (currentSum == target) {
            return findSmallest(sumList) + findLargest(sumList);
          }
        }
      } else if (currentSum == target) {
        // we have hit target
        return findSmallest(sumList) + findLargest(sumList);
      }
    }
    return 0;
  }

  private long findSmallest(List<Long> list) {
    var smallest = Long.MAX_VALUE;
    for (var item : list) if (item < smallest) smallest = item;
    return smallest;
  }

  private long findLargest(List<Long> list) {
    var largest = Long.MIN_VALUE;
    for (var item : list) if (item > largest) largest = item;
    return largest;
  }

}
