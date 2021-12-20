package twentytwentyone.day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day20 {
  public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Coordinate that = (Coordinate) o;
      return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    return getLitForEnhanceImagesAfterSteps(file, 2);
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    return getLitForEnhanceImagesAfterSteps(file, 50);
  }

  private int getLitForEnhanceImagesAfterSteps(File file, int steps) throws FileNotFoundException {
    var scanner = new Scanner(file);
    var enhancementAlgo = scanner.nextLine();
    scanner.nextLine();
    var inputImage = new HashMap<Coordinate, Boolean>();
    var y = 0;
    var maxX = 0;
    while (scanner.hasNextLine()) {
      var line = scanner.nextLine();
      var x = 0;
      for (var c : line.toCharArray()) {
        var coord = new Coordinate(x, y);
        // Only need to store lit pixels
        if (c == '#') {
          inputImage.put(coord, true);
        } else {
          inputImage.put(coord, false);
        }
        x++;
      }
      maxX = x;
      y++;
    }
    var maxY = y;
    int defaultValue = 0;
    for (var step = 0; step < steps; step++) {
      inputImage = enhanceImage(inputImage, enhancementAlgo, 0 - step, 0 - step, maxX - 1 + step, maxY - 1 + step, defaultValue);
      if (step == 0) {
        defaultValue = enhancementAlgo.charAt(0) == '#' ? 1 : 0;;
      } else {
        defaultValue = enhancementAlgo.charAt(defaultValue == 1 ? 511 : 0) == '#' ? 1 : 0;
      }
    }
    var count = 0;
    for (var val : inputImage.values()) {
      if (val) count++;
    }
    return count;
  }

  public HashMap<Coordinate, Boolean> enhanceImage(HashMap<Coordinate, Boolean> input, String enhancementAlgo, int minX,
                                          int minY, int maxX, int maxY, int defaultValue) {
    var output = new HashMap<Coordinate, Boolean>();
    for (var y = minY - 1; y <= maxY + 1; ++y) {
      for (var x = minX - 1; x <= maxX + 1; ++x) {
        var binaryBuilder = new StringBuilder();
        for (var yRegion = y - 1; yRegion <= y + 1; yRegion++) {
          for (var xRegion = x - 1; xRegion <= x + 1; xRegion++) {
            if (xRegion <= maxX && xRegion >= minX && yRegion <= maxY && yRegion >= minY) {
              if (input.get(new Coordinate(xRegion, yRegion))) {
                binaryBuilder.append("1");
              } else {
                binaryBuilder.append("0");
              }
            } else {
              // Depends on which turn this is. Because the first value in algo is on and last value is off:
              // Even turns have defaulted all infinite grid values to off, odd are on
              binaryBuilder.append(defaultValue);
            }
          }
        }
        var algoIndex = Integer.parseInt(binaryBuilder.toString(), 2);
        if (enhancementAlgo.charAt(algoIndex) == '#') {
          output.put(new Coordinate(x, y), true);
        } else {
          output.put(new Coordinate(x, y), false);
        }
      }
    }
    return output;
  }
}
