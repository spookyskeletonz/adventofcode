package twentytwenty.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day05 {

  static class Seat {
    int row;
    int column;
    int seatId;

    Seat(String pass) {
      row = binarySearch('F', 'B', 127, 0, 0, pass.substring(0, 7));
      column = binarySearch('L', 'R',7, 0, 0, pass.substring(7, 10));
      seatId = row * 8 + column;
    }

    private static int binarySearch(char lowChar, char upperChar, int max, int min, int val, String pass) {
      if (pass.length() == 0) return val;
      var indicator = pass.charAt(0);
      if (indicator == upperChar) {
        // Take the upper half
        var newMin = (int) Math.ceil(((double)max + (double)min)/2.0);
        return binarySearch(lowChar, upperChar, max, newMin, newMin, pass.substring(1));
      } else if (indicator == lowChar) {
        // Take the lower half
        var newMax = (int) Math.floor(((double)max + (double)min)/2.0);
        return binarySearch(lowChar, upperChar, newMax, min, newMax, pass.substring(1));
      } else {
        throw new AssertionError("Unknown pass char");
      }
    }
  }

  public long solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var highestSeat = 0L;
    while (scanner.hasNextLine()) {
      var pass = scanner.nextLine();
      var seat = new Seat(pass);
      if (seat.seatId > highestSeat) highestSeat = seat.seatId;
    }
    return highestSeat;
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var seats = new boolean[127*8+7];
    while (scanner.hasNextLine()) {
      var pass = scanner.nextLine();
      var seat = new Seat(pass);
      seats[seat.seatId] = true;
    }
    for (var i = 1; i < seats.length-1; ++i) {
      if (seats[i-1] && !seats[i] && seats[i+1]) return i;
    }
    return 0;
  }
}
