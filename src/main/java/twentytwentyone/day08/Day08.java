package twentytwentyone.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day08 {

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var simpleDigitCount = 0;
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var outputString = line.split(" \\| ")[1];
      var outputPatterns = outputString.split(" ");
      for (var pattern : outputPatterns) {
        var length = pattern.length();
        if (length == 2 || length == 3 || length == 4 | length == 7) {
          simpleDigitCount++;
        }
      }
    }
    return simpleDigitCount;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var sum = 0;
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var inputString = line.split(" \\| ")[0];
      var outputString = line.split(" \\| ")[1];
      var inputPatterns = inputString.split(" ");
      var outputPatterns = outputString.split(" ");
      List<HashSet<Character>> patterns = new ArrayList<>();
      for (var i = 0; i < 10; i++) {
        patterns.add(new HashSet<>());
      }
      // possible 2,3,5 patterns (all 5 digit) and 0,6,9 (6 digit)
      var twoThreeFiveOptions = new ArrayList<HashSet<Character>>();
      var zeroSixNineOptions = new ArrayList<HashSet<Character>>();
      // Get known patterns and options for 2,3,5 and 0,6,9
      for (var pat : inputPatterns) {
        switch (pat.length()) {
          case 5: {
            var patternSet = new HashSet<Character>();
            for (var c : pat.toCharArray()) {
              patternSet.add(c);
            }
            twoThreeFiveOptions.add(patternSet);
            break;
          }
          case 6: {
            var patternSet = new HashSet<Character>();
            for (var c : pat.toCharArray()) {
              patternSet.add(c);
            }
            zeroSixNineOptions.add(patternSet);
            break;
          }
          case 7:
            for (var c : pat.toCharArray()) {
              patterns.get(8).add(c);
            }
            break;
          case 4:
            for (var c : pat.toCharArray()) {
              patterns.get(4).add(c);
            }
            break;
          case 3:
            for (var c : pat.toCharArray()) {
              patterns.get(7).add(c);
            }
            break;
          case 2:
            for (var c : pat.toCharArray()) {
              patterns.get(1).add(c);
            }
            break;
        }
      }

      // FINDING 6
      // All the segments in 7 must be in 0 and 9 but NOT 6
      // Therefore we can find 6 knowing 7 and the options for 0,6,9
      for (var pattern : zeroSixNineOptions) {
        if (!pattern.containsAll(patterns.get(7))) {
          patterns.get(6).addAll(pattern);
          break;
        }
      }
      zeroSixNineOptions.remove(patterns.get(6));

      // FINDING 5
      // 6 contains all the segments of 5 but NOT 2 or 3
      // Therefore we can find 5 knowing 6 and the options for 2,3,5
      for (var pattern : twoThreeFiveOptions) {
        if (patterns.get(6).containsAll(pattern)) {
          patterns.get(5).addAll(pattern);
          break;
        }
      }
      twoThreeFiveOptions.remove(patterns.get(5));

      // FINDING 9
      // All the segments in 5 must be in 9 but NOT 0
      // Therefore we can find 9 knowing 5 and the options left for 0,9
      // zeroSixNine has 6 removed, only contains 0,9
      for (var pattern : zeroSixNineOptions) {
        if (pattern.containsAll(patterns.get(5))) {
          patterns.get(9).addAll(pattern);
          break;
        }
      }
      zeroSixNineOptions.remove(patterns.get(9));
      // FINDING 0
      // Last number in zeroSixNine is 0
      assert(zeroSixNineOptions.size() == 1);
      patterns.get(0).addAll(zeroSixNineOptions.get(0));

      // FINDING 3
      // 9 contains all the segments in 3 but NOT 2
      // Therefore we can find 3 knowing 9 and the options left for 2,3
      // twoThreeFive has 5 removed, only contains 2,3
      for (var pattern : twoThreeFiveOptions) {
        if (patterns.get(9).containsAll(pattern)) {
          patterns.get(3).addAll(pattern);
          break;
        }
      }
      twoThreeFiveOptions.remove(patterns.get(3));
      // FINDING 2
      // Last number in twoThreeFive is 2
      assert(twoThreeFiveOptions.size() == 1);
      patterns.get(2).addAll(twoThreeFiveOptions.get(0));

      // patterns is completely populated. Decode output
      var outputBuilder = new StringBuilder();
      for (var outputPattern : outputPatterns) {
        var pattern = new HashSet<Character>();
        for (var c : outputPattern.toCharArray()) {
          pattern.add(c);
        }
        for (var val = 0; val < patterns.size(); val++) {
          if (pattern.equals(patterns.get(val))) {
            outputBuilder.append(val);
            break;
          }
          if (val == patterns.size() - 1) {
            throw new RuntimeException("No matching pattern found!");
          }
        }
      }
      sum += Integer.parseInt(outputBuilder.toString());
    }
    return sum;
  }
}
