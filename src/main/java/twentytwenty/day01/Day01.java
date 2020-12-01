package twentytwenty.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day01 {

  public int solvePartOne(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    Map<Integer, Integer> diffMap = new HashMap<>();
    while (fileScanner.hasNextInt()) {
      int x = fileScanner.nextInt();
      if (diffMap.containsKey(x)) return diffMap.get(x) * x;
      diffMap.put(2020 - x, x);
    }
    return 0;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    List<Integer> nums = new ArrayList<>();
    while (fileScanner.hasNextInt()) {
      nums.add(fileScanner.nextInt());
    }
    for (int i = 0; i + 2 < nums.size(); ++i) {
      for (int j = i + 1; j + 1 < nums.size(); ++j) {
        for (int k = 1 + 2; k < nums.size(); ++k) {
          if (nums.get(i) + nums.get(j) + nums.get(k) == 2020) {
            return nums.get(i) * nums.get(j) * nums.get(k);
          }
        }
      }
    }
    return 0;
  }

}
