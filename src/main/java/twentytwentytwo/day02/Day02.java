package twentytwentytwo.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 {
  private enum Move {
    ROCK,
    PAPER,
    SCISSORS
  }

  private Move parseMove(Character c) {
    switch (c) {
      case 'A':
      case 'X':
        return Move.ROCK;
      case 'B':
      case 'Y':
        return Move.PAPER;
      case 'C':
      case 'Z':
        return Move.SCISSORS;
      default: throw new IllegalArgumentException();
    }
  }

  private int gameResult(Move opp, Move me) {
    switch (opp) {
      case ROCK:
        switch (me) {
          case ROCK:
            return 1 + 3;
          case PAPER:
            return 2 + 6;
          case SCISSORS:
            return 3 + 0;
        }
      case PAPER:
        switch (me) {
          case ROCK:
            return 1 + 0;
          case PAPER:
            return 2 + 3;
          case SCISSORS:
            return 3 + 6;
        }
      case SCISSORS:
        switch (me) {
          case ROCK:
            return 1 + 6;
          case PAPER:
            return 2 + 0;
          case SCISSORS:
            return 3 + 3;
        }
    }
    return 0;
  }

  private Move calcMove(Move opp, char result) {
    switch (opp) {
      case ROCK:
        switch (result) {
          case 'X':
            return Move.SCISSORS;
          case 'Y':
            return Move.ROCK;
          case 'Z':
            return Move.PAPER;
        }
      case PAPER:
        switch (result) {
          case 'X':
            return Move.ROCK;
          case 'Y':
            return Move.PAPER;
          case 'Z':
            return Move.SCISSORS;
        }
      case SCISSORS:
        switch (result) {
          case 'X':
            return Move.PAPER;
          case 'Y':
            return Move.SCISSORS;
          case 'Z':
            return Move.ROCK;
        }
    }
    return Move.ROCK;
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var totalScore = 0;
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var opp = parseMove(line.charAt(0));
      var me = parseMove(line.charAt(2));
      totalScore += gameResult(opp, me);
    }
    return totalScore;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var totalScore = 0;
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var opp = parseMove(line.charAt(0));
      var me = calcMove(opp, line.charAt(2));
      totalScore += gameResult(opp, me);
    }
    return totalScore;
  }
}
