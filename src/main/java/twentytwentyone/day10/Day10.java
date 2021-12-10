package twentytwentyone.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {
  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var syntaxErrorScore = 0;
    while (scanner.hasNextLine()) {
      Stack<Character> chunkOpenings = new Stack<>();
      var input = scanner.nextLine().toCharArray();
      for (var c : input) {
        switch (c) {
          case '(':
          case '[':
          case '{':
          case '<':
            chunkOpenings.push(c);
            break;
          case ')':
            if (chunkOpenings.pop() != '(') {
              syntaxErrorScore += 3;
            }
            break;
          case ']':
            if (chunkOpenings.pop() != '[') {
              syntaxErrorScore += 57;
            }
            break;
          case '}':
            if (chunkOpenings.pop() != '{') {
              syntaxErrorScore += 1197;
            }
            break;
          case '>':
            if (chunkOpenings.pop() != '<') {
              syntaxErrorScore += 25137;
            }
            break;
        }
      }
    }
    return syntaxErrorScore;
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var completionScores = new ArrayList<Long>();
    while (scanner.hasNextLine()) {
      Stack<Character> chunkOpenings = new Stack<>();
      var input = scanner.nextLine().toCharArray();
      var completionScore = 0L;
      var breakLoop = false;
      for (var c : input) {
        if (breakLoop) {
          break;
        }
        switch (c) {
          case '(':
          case '[':
          case '{':
          case '<':
            chunkOpenings.push(c);
            break;
          case ')':
            if (chunkOpenings.pop() != '(') {
              // Skip corrupted line
              breakLoop = true;
            }
            break;
          case ']':
            if (chunkOpenings.pop() != '[') {
              // Skip corrupted line
              breakLoop = true;
            }
            break;
          case '}':
            if (chunkOpenings.pop() != '{') {
              // Skip corrupted line
              breakLoop = true;
            }
            break;
          case '>':
            if (chunkOpenings.pop() != '<') {
              // Skip corrupted line
              breakLoop = true;
            }
            break;
        }
      }
      // Skip corrupted line
      if (breakLoop) {
        continue;
      }
      // Now get completing scores for openings left on stack
      while (!chunkOpenings.isEmpty()) {
        completionScore *= 5;
        switch (chunkOpenings.pop()) {
          case '(':
            completionScore += 1;
            break;
          case '[':
            completionScore += 2;
            break;
          case '{':
            completionScore += 3;
            break;
          case '<':
            completionScore += 4;
            break;
        }
      }
      completionScores.add(completionScore);
    }
    completionScores.sort(Comparator.naturalOrder());
    return completionScores.get(completionScores.size()/2);
  }
}
