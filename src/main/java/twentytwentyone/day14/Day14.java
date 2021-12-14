package twentytwentyone.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var polymer = scanner.nextLine();
    // empty line next
    scanner.nextLine();
    Map<String, Character> pairInsertions = new HashMap<>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var keyValue = line.split(" -> ");
      pairInsertions.put(keyValue[0], keyValue[1].toCharArray()[0]);
    }
    for (var i = 0; i < 10; i++) {
      var windowLeft = 0;
      var windowRight = 1;
      var newPolymer = new StringBuilder();
      newPolymer.append(polymer.charAt(0));
      while (windowRight < polymer.length()) {
        var pair = polymer.substring(windowLeft, windowRight + 1);
        if (pairInsertions.containsKey(pair)) {
          newPolymer.append(pairInsertions.get(pair));
        }
        newPolymer.append(pair.toCharArray()[1]);
        windowLeft++;
        windowRight++;
      }
      polymer = newPolymer.toString();
    }
    Map<Character, Integer> elementCounts = new HashMap<>();
    for (var c : polymer.toCharArray()) {
      if (elementCounts.containsKey(c)) {
        elementCounts.put(c, elementCounts.get(c)+1);
      } else {
        elementCounts.put(c, 1);
      }
    }
    var max = Integer.MIN_VALUE;
    var min = Integer.MAX_VALUE;
    for (var value : elementCounts.values()) {
      if (value > max) max = value;
      if (value < min) min = value;
    }
    return max - min;
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var polymer = scanner.nextLine();
    // Populate original pairCounts from original polymer
    Map<String, Long> pairCounts = new HashMap<>();
    var windowLeft = 0;
    var windowRight = 1;
    while (windowRight < polymer.length()) {
      var pair = polymer.substring(windowLeft, windowRight + 1);
      if (pairCounts.containsKey(pair)) {
        pairCounts.put(pair, pairCounts.get(pair) + 1);
      } else {
        pairCounts.put(pair, 1L);
      }
      windowLeft++;
      windowRight++;
    }
    // Populate original element counts from original polymer
    Map<Character, Long> elementCounts = new HashMap<>();
    for (var c : polymer.toCharArray()) {
      if (elementCounts.containsKey(c)) {
        elementCounts.put(c, elementCounts.get(c) + 1);
      } else {
        elementCounts.put(c, 1L);
      }
    }
    // empty line next
    scanner.nextLine();
    // Get pair insertion rules
    Map<String, Character> pairInsertions = new HashMap<>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var keyValue = line.split(" -> ");
      pairInsertions.put(keyValue[0], keyValue[1].toCharArray()[0]);
    }
    // At each step, using the previous map of pairCounts,
    // we can determine the next map of pairCounts using our pair insertion rules
    // while doing this we can build  our element counts
    for (var i = 0; i < 40; ++i) {
      var newPairCounts = new HashMap<String, Long>();
      for (var keyValue : pairCounts.entrySet()) {
        if (pairInsertions.containsKey(keyValue.getKey())) {
          var originalPair = keyValue.getKey();
          var count = keyValue.getValue();
          var insert = pairInsertions.get(originalPair);
          var newPairOne = "" + originalPair.charAt(0) + insert;
          var newPairTwo = "" + insert + originalPair.charAt(1);
          var newPairOneCount = newPairCounts.getOrDefault(newPairOne, 0L) + count;
          newPairCounts.put(newPairOne, newPairOneCount);
          var newPairTwoCount = newPairCounts.getOrDefault(newPairTwo, 0L) + count;
          newPairCounts.put(newPairTwo, newPairTwoCount);
          if (elementCounts.containsKey(insert)) {
            elementCounts.put(insert, elementCounts.get(insert) + count);
          } else {
            elementCounts.put(insert, count);
          }
        }
      }
      pairCounts = newPairCounts;
    }
    // Get max and min counts;
    var max = Long.MIN_VALUE;
    var min = Long.MAX_VALUE;
    for (var value : elementCounts.values()) {
      if (value > max) max = value;
      if (value < min) min = value;
    }

    return max - min;
  }
}
