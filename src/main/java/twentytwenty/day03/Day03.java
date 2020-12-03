package twentytwenty.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Day03 {

  public static ArrayList<ArrayList<Boolean>> generateTreeGrid(File file) throws FileNotFoundException {
    var treeGrid = new ArrayList<ArrayList<Boolean>>();
    Scanner fileScanner = new Scanner(file);
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      var row = new ArrayList<Boolean>();
      for (var c : line.toCharArray()) {
        if (c == '.') {
          row.add(false);
        } else if (c == '#') {
          row.add(true);
        } else {
          System.out.println("ERROR");
        }
      }
      treeGrid.add(row);
    }
    return treeGrid;
  }

  public static long findTreesForSlope(int xInc, int yInc, ArrayList<ArrayList<Boolean>> treeGrid) {
    var xIndex = 0;
    var yIndex = 0;
    var numTrees = 0L;
    while (yIndex < treeGrid.size()) {
      if (treeGrid.get(yIndex).get(xIndex)) {
        numTrees++;
      }
      xIndex = (xIndex + xInc) % (treeGrid.get(yIndex).size());
      yIndex += yInc;
    }
    return numTrees;
  }

  public long solvePartOne(File file) throws FileNotFoundException {
    var treeGrid = generateTreeGrid(file);
    return findTreesForSlope(3, 1, treeGrid);
  }

  public long solvePartTwo(File file) throws FileNotFoundException {
    var treeGrid = generateTreeGrid(file);
    return findTreesForSlope(1, 1, treeGrid)
            * findTreesForSlope(3, 1, treeGrid)
            * findTreesForSlope(5, 1, treeGrid)
            * findTreesForSlope(7, 1, treeGrid)
            * findTreesForSlope(1, 2, treeGrid);
  }
}
