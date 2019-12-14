package day04;

class Day04 {
  int solvePartOne(int min, int max) {
    // brute force
    int numSols = 0;
    for (int cur = min; cur <= max; cur++) {
      if (isPossiblePassword(cur)) numSols++;
    }
    return numSols;
  }

  int solvePartTwo(int min, int max) {
    // brute force
    int numSols = 0;
    for (int cur = min; cur <= max; cur++) {
      if (isPossiblePasswordPartTwo(cur)) numSols++;
    }
    return numSols;
  }

  boolean isPossiblePassword(int value) {
    // Rules: at least two adjacent digits in value must be equal, no digit can be greater than its predecessor
    boolean adj = false;
    int[] digits = String.valueOf(value).chars()
        .map(c -> Integer.parseInt(String.valueOf((char) c)))
        .toArray();
    int index = 1;
    while (index < digits.length) {
      if (digits[index] < digits[index - 1]) {
        // fails greater than predecessor rule
        return false;
      }
      if (digits[index] == digits[index - 1]) {
        adj = true;
      }
      index++;
    }
    return adj;
  }

  boolean isPossiblePasswordPartTwo(int value) {
    /* Rules:
     *  - exactly (no more no less) two adjacent digits in value must be equal
     *  - no digit can be greater than its predecessor */
    // what we will eventually return. should only be set to true and never to false from here on
    boolean finalAdj = false;
    // keeps track of the number of consecutive matches we have seen ({1,1,1} = 2, {1,1} = 1, {1,3} = 0)
    int numMatch = 0;

    int[] digits = String.valueOf(value).chars()
        .map(c -> Integer.parseInt(String.valueOf((char) c)))
        .toArray();
    // start index at second last element
    int index = digits.length - 2;
    // last element
    while (index >= 0) {
      int nextVal = digits[index + 1];
      int val = digits[index];
      // Check greater than predecessor rule
      if (val > nextVal) {
        return false;
      }
      // Check adjacency match rule
      // only process this rule if we haven't already found exactly 2 consecutive digits in a row matching
      if (!finalAdj) {
        if (val == nextVal) {
          numMatch++;
          /* if we are at the first element (last one processed) and have found a match with exactly 2 consecutive,
           * set finalAdj to true */
          if (numMatch == 1 && index == 0) finalAdj = true;
        } else {
          if (numMatch == 1) finalAdj = true;
          numMatch = 0;
        }
      }
      index--;
    }
    return finalAdj;
  }
}
