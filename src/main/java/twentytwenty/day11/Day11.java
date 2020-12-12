package twentytwenty.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {

  public enum SeatState {
    OCCUPIED,
    EMPTY,
    FLOOR
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var states = new ArrayList<ArrayList<SeatState>>();
    var scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var stateRow = new ArrayList<SeatState>();
      for (var state : line.toCharArray()) {
        switch (state) {
          case 'L':
            stateRow.add(SeatState.EMPTY);
            break;
          case '#':
            stateRow.add(SeatState.OCCUPIED);
            break;
          case '.':
            stateRow.add(SeatState.FLOOR);
            break;
          default:
            throw new AssertionError("Unknown seat state: " + state);
        }
      }
      states.add(stateRow);
    }
    var noChange = false;
    // start state machine
    while (!noChange) {
      var newStates = getNextSeatLayoutPartOne(states);
      if (states.equals(newStates)) noChange = true;
      states = newStates;
    }
    return countOccupied(states);
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var states = new ArrayList<ArrayList<SeatState>>();
    var scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var stateRow = new ArrayList<SeatState>();
      for (var state : line.toCharArray()) {
        switch (state) {
          case 'L':
            stateRow.add(SeatState.EMPTY);
            break;
          case '#':
            stateRow.add(SeatState.OCCUPIED);
            break;
          case '.':
            stateRow.add(SeatState.FLOOR);
            break;
          default:
            throw new AssertionError("Unknown seat state: " + state);
        }
      }
      states.add(stateRow);
    }
    var noChange = false;
    // start state machine
    while (!noChange) {
      var newStates = getNextSeatLayoutPartTwo(states);
      if (states.equals(newStates)) noChange = true;
      states = newStates;
    }
    return countOccupied(states);
  }

  public ArrayList<ArrayList<SeatState>> getNextSeatLayoutPartOne(ArrayList<ArrayList<SeatState>> states) {
    var newState = new ArrayList<ArrayList<SeatState>>();
    var rowNum = 0;
    var colNum = 0;
    for (var row : states) {
      var newRow = new ArrayList<SeatState>();
      for (var seat : row) {
        switch (seat) {
          case OCCUPIED: {
            var adjOccupiedCount = countAdjOccupied(states, rowNum, colNum);
            if (adjOccupiedCount >= 4) newRow.add(SeatState.EMPTY);
            else newRow.add(SeatState.OCCUPIED);
            break;
          }
          case EMPTY: {
            var adjOccupiedCount = countAdjOccupied(states, rowNum, colNum);
            if (adjOccupiedCount == 0) newRow.add(SeatState.OCCUPIED);
            else newRow.add(SeatState.EMPTY);
            break;
          }
          case FLOOR:
            newRow.add(SeatState.FLOOR);
            break;
          default:
            throw new AssertionError("Unknown state: " + seat);
        }
        colNum++;
      }
      rowNum++;
      colNum = 0;
      newState.add(newRow);
    }
    return newState;
  }

  public ArrayList<ArrayList<SeatState>> getNextSeatLayoutPartTwo(ArrayList<ArrayList<SeatState>> states) {
    var newState = new ArrayList<ArrayList<SeatState>>();
    var rowNum = 0;
    var colNum = 0;
    for (var row : states) {
      var newRow = new ArrayList<SeatState>();
      for (var seat : row) {
        switch (seat) {
          case OCCUPIED: {
            var visiOccupiedCount = countVisiOccupied(states, rowNum, colNum);
            if (visiOccupiedCount >= 5) newRow.add(SeatState.EMPTY);
            else newRow.add(SeatState.OCCUPIED);
            break;
          }
          case EMPTY: {
            var visiOccupiedCount = countVisiOccupied(states, rowNum, colNum);
            if (visiOccupiedCount == 0) newRow.add(SeatState.OCCUPIED);
            else newRow.add(SeatState.EMPTY);
            break;
          }
          case FLOOR:
            newRow.add(SeatState.FLOOR);
            break;
          default:
            throw new AssertionError("Unknown state: " + seat);
        }
        colNum++;
      }
      rowNum++;
      colNum = 0;
      newState.add(newRow);
    }
    return newState;
  }

  public int countAdjOccupied(ArrayList<ArrayList<SeatState>> state, int rowNum, int colNum) {
    var count = 0;
    for (var r = rowNum - 1; r <= rowNum + 1; ++r) {
      for (var c = colNum - 1; c <= colNum + 1; ++c) {
        if (r == rowNum && c == colNum) continue;
        if (r < 0 || r > state.size() - 1) continue;
        if (c < 0 || c > state.get(r).size() - 1) continue;
        if (state.get(r).get(c).equals(SeatState.OCCUPIED)) count++;
      }
    }
    return count;
  }

  public int countVisiOccupied(ArrayList<ArrayList<SeatState>> state, int rowNum, int colNum) {
    var count = 0;
    // NW
    if (seeDirection(state, rowNum - 1, colNum - 1, -1, -1)){
      count++;
    }
    // N
    if (seeDirection(state, rowNum - 1, colNum, -1, 0)) {
      count++;
    }
    // NE
    if (seeDirection(state, rowNum - 1, colNum + 1, -1, 1)) {
      count++;
    }
    // E
    if (seeDirection(state, rowNum, colNum + 1, 0, 1)) {
      count++;
    }
    // SE
    if (seeDirection(state, rowNum + 1, colNum + 1, 1, 1)) {
      count++;
    }
    // S
    if (seeDirection(state, rowNum + 1, colNum, 1, 0)) {
      count++;
    }
    // SW
    if (seeDirection(state, rowNum + 1, colNum - 1, 1, -1)) {
      count++;
    }
    // W
    if (seeDirection(state, rowNum, colNum - 1, 0, -1)) {
      count++;
    }
    return count;
  }

  // Recursively look in a direction (direction specified my modifier params)
  public boolean seeDirection(ArrayList<ArrayList<SeatState>> state, int rowNum, int colNum, int rowMod, int colMod) {
    if (rowNum < 0 || rowNum > state.size() - 1) return false;
    if (colNum < 0 || colNum > state.get(rowNum).size() - 1) return false;
    // hit occupied
    // hit empty
    if (state.get(rowNum).get(colNum).equals(SeatState.FLOOR)) {
      // recurse
      return seeDirection(state, rowNum + rowMod, colNum + colMod, rowMod, colMod);
    } else return state.get(rowNum).get(colNum).equals(SeatState.OCCUPIED);
  }

  public int countOccupied(ArrayList<ArrayList<SeatState>> state) {
    var count = 0;
    for (var r : state) {
      for (var seat : r) {
        if (seat.equals(SeatState.OCCUPIED)) {
          count++;
        }
      }
    }
    return count;
  }
}
