package twentytwentyone.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Day13 {

  public class Dot {
    int x;
    int y;

    public Dot(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Dot dot = (Dot) o;
      return x == dot.x && y == dot.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var dots = new HashSet<Dot>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      if (line.matches("\\d+,\\d+")) {
        var coords = line.split(",");
        var x = Integer.parseInt(coords[0]);
        var y = Integer.parseInt(coords[1]);
        var dot = new Dot(x, y);
        dots.add(dot);
      } else if (!line.isEmpty()) {
        var fold = line.split(" ")[2].split("=");
        var axis = fold[0];
        var foldNum = Integer.parseInt(fold[1]);
        var newDots = new HashSet<>(dots);
        if (axis.equals("y")) {
          for (var dot : dots) {
            if (dot.y > foldNum) {
              newDots.remove(dot);
              dot.y = foldNum - (dot.y - foldNum);
              newDots.add(dot);
            }
          }
        } else {
          for (var dot : dots) {
            if (dot.x > foldNum) {
              newDots.remove(dot);
              dot.x = foldNum - (dot.x - foldNum);
              newDots.add(dot);
            }
          }
        }
        // For part one, focus on first fold.
        return newDots.size();
      }
    }

    return 0;
  }

  public void solvePartTwo(File file) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var dots = new HashSet<Dot>();
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      if (line.matches("\\d+,\\d+")) {
        var coords = line.split(",");
        var x = Integer.parseInt(coords[0]);
        var y = Integer.parseInt(coords[1]);
        var dot = new Dot(x, y);
        dots.add(dot);
      } else if (!line.isEmpty()) {
        var fold = line.split(" ")[2].split("=");
        var axis = fold[0];
        var foldNum = Integer.parseInt(fold[1]);
        var newDots = new HashSet<>(dots);
        if (axis.equals("y")) {
          for (var dot : dots) {
            if (dot.y > foldNum) {
              newDots.remove(dot);
              dot.y = foldNum - (dot.y - foldNum);
              newDots.add(dot);
            }
          }
        } else {
          for (var dot : dots) {
            if (dot.x > foldNum) {
              newDots.remove(dot);
              dot.x = foldNum - (dot.x - foldNum);
              newDots.add(dot);
            }
          }
        }
        dots = newDots;
      }
    }
    printDots(dots);
  }

  public void printDots(HashSet<Dot> dots) {
    // get max x,y so we know size of image
    var maxX = Integer.MIN_VALUE;
    var maxY = Integer.MIN_VALUE;
    for (var dot : dots) {
      if (dot.x > maxX) maxX = dot.x;
      if (dot.y > maxY) maxY = dot.y;
    }
    // boolean array (init to false)
    var grid = new boolean[maxY+1][maxX+1];
    for (var dot : dots) {
      grid[dot.y][dot.x] = true;
    }
    for (var row : grid) {
      StringBuilder rowString = new StringBuilder();
      for (var populated : row) {
        if (populated) {
          rowString.append("#");
        } else {
          rowString.append(".");
        }
      }
      System.out.println(rowString);
    }
  }
}
