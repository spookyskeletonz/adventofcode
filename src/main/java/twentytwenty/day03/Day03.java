package twentytwenty.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day03 {

  public static ArrayList<ArrayList<Boolean>> generateTreeGrid(File file) {
    var treeGrid = new ArrayList<ArrayList<Boolean>>();
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
        ++i;
      }
    }
    return treeGrid;
  }

  public int solvePartOne(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    var treeGrid = generateTreeGrid(file);
    return numValid;
  }

  public int solvePartTwo(File file) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(file);
    int numValid = 0;
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      String[] cols = line.split(" ");
      int indexOne = Integer.parseInt(cols[0].split("-")[0]) - 1;
      int indexTwo = Integer.parseInt(cols[0].split("-")[1]) - 1;
      int character = cols[1].charAt(0);
      if ((cols[2].charAt(indexOne) == character) ^ (cols[2].charAt(indexTwo) == character)) numValid++;
    }
    return numValid;
  }
}
