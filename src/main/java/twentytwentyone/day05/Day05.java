package twentytwentyone.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Day05 {

  public static class Coord {
    int x;
    int y;

    public Coord(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Coord coord = (Coord) o;
      return x == coord.x && y == coord.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var coordMap = loadInputIntoCoordinateMap(scanner, true);
    var numMultiplePoints = 0;
    for (var entry : coordMap.entrySet()) {
      if (entry.getValue() > 1) numMultiplePoints++;
    }
    return numMultiplePoints;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var coordMap = loadInputIntoCoordinateMap(scanner, false);
    var numMultiplePoints = 0;
    for (var entry : coordMap.entrySet()) {
      if (entry.getValue() > 1) numMultiplePoints++;
    }
    return numMultiplePoints;
  }

  // Map = Key: Coordinate => Value: Number of lines on that point
  public Map<Coord, Integer> loadInputIntoCoordinateMap(Scanner fileScanner, boolean onlyHorizVert) {
    var coordMap = new HashMap<Coord, Integer>();
    while (fileScanner.hasNextLine()) {
      var line = fileScanner.nextLine();
      var coordStrings = line.split(" -> ");
      var lineStartCoord = new Coord(
          Integer.parseInt(coordStrings[0].split(",")[0]),
          Integer.parseInt(coordStrings[0].split(",")[1]));
      var lineEndCoord = new Coord(
          Integer.parseInt(coordStrings[1].split(",")[0]),
          Integer.parseInt(coordStrings[1].split(",")[1]));
      // Only considering horizontal or vertical lines if flag is set
      if (onlyHorizVert && lineStartCoord.x != lineEndCoord.x && lineStartCoord.y != lineEndCoord.y) continue;

      var x = lineStartCoord.x;
      var y = lineStartCoord.y;
      // Update start of line
      var key = new Coord(x, y);
      var numLines = coordMap.getOrDefault(key, 0);
      numLines++;
      coordMap.put(key, numLines);
      // Walk and update coords to end of line
      while (x != lineEndCoord.x || y != lineEndCoord.y) {
        if (x < lineEndCoord.x) {
          x++;
        } else if (x > lineEndCoord.x) {
          x--;
        }
        if (y < lineEndCoord.y) {
          y++;
        } else if (y > lineEndCoord.y) {
          y--;
        }
        key = new Coord(x, y);
        numLines = coordMap.getOrDefault(key, 0);
        numLines++;
        coordMap.put(key, numLines);
      }
    }
    return coordMap;
  }
}
