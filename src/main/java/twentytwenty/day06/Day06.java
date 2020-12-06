package twentytwenty.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day06 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var countSum = 0;
    var yesSet = new HashSet<Character>();
    while (scanner.hasNextLine()){
      var line = scanner.nextLine();
      if (line.isEmpty()) {
        countSum += yesSet.size();
        yesSet.clear();
        continue;
      }
      for (var c : line.toCharArray()) {
        yesSet.add(c);
      }
    }
    if (yesSet.size() != 0) {
      countSum += yesSet.size();
    }
    return countSum;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var countSum = 0;
    var yesMap = new HashMap<Character, Integer>();
    var groupMemberCount = 0;
    while (scanner.hasNextLine()){
      var line = scanner.nextLine();
      if (line.isEmpty()) {
        // check yes entries against group number to determine whether unanimous
        for (var yesEntry : yesMap.entrySet()) {
          if (yesEntry.getValue() == groupMemberCount) {
            countSum++;
          }
        }
        // clear map and count for next group
        yesMap.clear();
        groupMemberCount = 0;
        continue;
      }
      // put or increment each char in yesEntry
      for (var c : line.toCharArray()) {
        if (yesMap.containsKey(c)) {
          yesMap.put(c, yesMap.get(c)+1);
        } else {
          yesMap.put(c, 1);
        }
      }
      groupMemberCount++;
    }
    if (yesMap.size() != 0) {
      for (var yesEntry : yesMap.entrySet()) {
        if (yesEntry.getValue() == groupMemberCount) {
          countSum++;
        }
      }
    }
    return countSum;
  }
}
