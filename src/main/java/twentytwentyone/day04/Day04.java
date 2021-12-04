package twentytwentyone.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day04 {
  public class BingoSquare {
    public int value = 0;
    public boolean marked = false;
  }

  public class BingoBoard {
    BingoSquare[][] squares = new BingoSquare[5][5];
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    var drawNumbers = fileScanner.nextLine().split(",");
    // Build boards
    fileScanner.nextLine();
    var boards = new ArrayList<BingoBoard>();
    var boardNumber = 0;
    var rowNum = 0;
    boards.add(initialiseBoard());
    while (fileScanner.hasNextLine()) {
      var line = fileScanner.nextLine();
      if (line.isEmpty()) {
        boardNumber++;
        rowNum = 0;
        boards.add(initialiseBoard());
        continue;
      }
      var values = line.split(" ");
      var colNum = 0;
      for (var val : values) {
        if (val.equals("")) {
          continue;
        }
        boards.get(boardNumber).squares[rowNum][colNum].value = Integer.parseInt(val);
        colNum++;
      }
      rowNum++;
    }
    // Draw numbers and mark boards
    for (var value : drawNumbers) {
      for (var board : boards) {
        var markedCoords = markValueOnBoard(Integer.parseInt(value), board);
        if (!markedCoords.isEmpty()) {
          for (var coord : markedCoords) {
            if (boardWinsFromThisSquare(coord[0], coord[1], board)) {
              return calculateScore(board, Integer.parseInt(value));
            }
          }
        }
      }
    }
    return 0;
  }

  public BingoBoard initialiseBoard() {
    var board = new BingoBoard();
    for (var row : board.squares) {
      for (var i = 0; i < 5; i++) {
        row[i] = new BingoSquare();
      }
    }
    return board;
  }

  public int calculateScore(BingoBoard board, int value) {
    var unmarkedSum = 0;
    for (var row : board.squares) {
      for (var square : row) {
        if (!square.marked) {
          unmarkedSum += square.value;
        }
      }
    }
    return unmarkedSum * value;
  }

  // returns coords (List of [row, col]) of marked squares
  public List<int[]> markValueOnBoard(int value, BingoBoard board) {
    var coords = new ArrayList<int[]>();
    var rowNum = 0;
    for (var row : board.squares) {
      var colNum = 0;
      for (var square : row) {
        if (square.value == value) {
          square.marked = true;
          coords.add(new int[]{rowNum, colNum});
        }
        colNum++;
      }
      rowNum++;
    }
    return coords;
  }

  public boolean boardWinsFromThisSquare(int rowNum, int colNum, BingoBoard board) {
    // See if row wins
    var row = board.squares[rowNum];
    if (Arrays.stream(row).allMatch(r -> r.marked)) {
      return true;
    }
    // See if col wins
    var col = new BingoSquare[5];
    var index = 0;
    for (var boardRow : board.squares) {
      col[index] = boardRow[colNum];
      index++;
    }
    return Arrays.stream(col).allMatch(r -> r.marked);
  }
}
