package twentytwenty.day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day15 {

  public int solvePartOne(String input) throws FileNotFoundException {
    return solve(2020, input);
  }

  public int solve(int limit, String input) {
    // map of number to turn it was last spoken
    var numMap = new HashMap<Integer, Integer>();
    var startingNums = input.split(",");
    int index = 1;
    for (var num : startingNums) {
      numMap.put(Integer.parseInt(num), index);
      index++;
    }
    var num = 0;
    while (index < limit) {
      int nextNum;
      if (numMap.containsKey(num)) {
        nextNum = index - numMap.get(num);
      } else {
        nextNum = 0;
      }
      numMap.put(num, index);
      num = nextNum;
      index++;
    }
    return num;
  }

  public int solvePartTwo(String input) {
    return solve(30000000, input);
  }
}
