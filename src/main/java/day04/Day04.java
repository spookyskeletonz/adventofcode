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
    // what we will eventually return. should only be set to true and never to false
    boolean finalAdj = false;
    // keeps track of whether we should consider adjacent numbers next to each other (if there are more than 2 this will be false)
    boolean globAdj = false;
    // keeps track of if our last comparison was also adjacent
    boolean curAdj = false;

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
      if (!finalAdj) {
       // only process this rule if we haven't already found exactly 2 consecutive digits in a row matching
        if (val == nextVal && !curAdj) {
          if (index == 0) finalAdj = true;
          globAdj = true;
          curAdj = true;
        } else if (val == nextVal && curAdj) {
          globAdj = false;
        } else if (curAdj && globAdj) {
          // found exactly two matches
          curAdj = false;
          finalAdj = true;
        } else if (curAdj) {
          // no longer adjacent matches
          curAdj = false;
        }
      }
      index--;
    }
    return finalAdj;
  }
}
